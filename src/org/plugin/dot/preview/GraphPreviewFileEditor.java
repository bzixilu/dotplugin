// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.plugin.dot.preview;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.colorpicker.CommonButton;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.Alarm;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.parse.ParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.DotIcons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.intellij.openapi.util.text.StringUtil.trimStart;

public class GraphPreviewFileEditor extends UserDataHolderBase implements FileEditor {
    private final static long PARSING_CALL_TIMEOUT_MS = 1000L;
    @NotNull
    private final Alarm myPooledAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, this);

    @NotNull
    private final ImagePanel myPanel;

    public GraphPreviewFileEditor(@NotNull VirtualFile file, Project project) {
        myPanel = new ImagePanel(project);
        Document myDocument = FileDocumentManager.getInstance().getDocument(file);
        if (myDocument != null) {
            myPanel.addImage(myDocument);
            myDocument.addDocumentListener(new DocumentListener() {

                @Override
                public void bulkUpdateFinished(@NotNull Document document) {
                    myPooledAlarm.cancelAllRequests();
                    myPooledAlarm.addRequest(() -> myPanel.paintGraph(myPanel.getGraphics()), PARSING_CALL_TIMEOUT_MS);
                }

                @Override
                public void documentChanged(@NotNull final DocumentEvent e) {
                    myPooledAlarm.cancelAllRequests();
                    myPooledAlarm.addRequest(() -> myPanel.paintGraph(myPanel.getGraphics()), PARSING_CALL_TIMEOUT_MS);
                }
            }, this);
        }
        myPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                myPanel.bufferedImage = null;
                myPooledAlarm.cancelAllRequests();
                myPooledAlarm.addRequest(() -> myPanel.paintGraph(myPanel.getGraphics()), PARSING_CALL_TIMEOUT_MS);
            }
        });
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return myPanel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return myPanel;
    }

    @NotNull
    @Override
    public String getName() {
        return "Graph Preview";
    }

    @Override
    public void setState(@NotNull FileEditorState state) {
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void deselectNotify() {
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Override
    
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
        myPanel.dispose();
    }

    @Override
    public void selectNotify() {
    }

    public static class ImagePanel extends JBPanel implements Disposable {

        public BufferedImage bufferedImage;
        private Document document;
        final JLabel noPreviewReason;
        private MutableGraph current;

        public ImagePanel(Project project) {
            final GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            final JToolBar toolBar = new JToolBar();
            toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            toolBar.setMaximumSize(new Dimension(100, 50));
            final JPanel actionsToolBar = new JPanel();
            toolBar.add(actionsToolBar);

            final CommonButton copyToClipboard = new CommonButton(AllIcons.Actions.Copy) {
                @Override
                public boolean isEnabled() {
                    return bufferedImage != null;
                }
            };
            copyToClipboard.setDisabledIcon(IconLoader.getDisabledIcon(AllIcons.Actions.Copy));
            copyToClipboard.addActionListener(it -> {
                if (bufferedImage != null) {
                    CopyPasteManager.getInstance().setContents(new ImageTransferable(bufferedImage));
                }
            });

            copyToClipboard.setToolTipText("Copy graph preview image to clipboard");
            actionsToolBar.add(copyToClipboard);

            final CommonButton saveAs = new CommonButton(DotIcons.SAVE) {
                @Override
                public boolean isEnabled() {
                    return bufferedImage != null;
                }
            };
            saveAs.setDisabledIcon(IconLoader.getDisabledIcon(DotIcons.SAVE));
            saveAs.addActionListener(it -> {
                if (bufferedImage != null) {
                    FileSaverDescriptor descriptor = new FileSaverDescriptor("Save Graph Preview Image", "", "png");
                    final FileSaverDialog saveFileDialog = FileChooserFactory.getInstance().createSaveFileDialog(descriptor, project);
                    final VirtualFileWrapper save = saveFileDialog.save(null);
                    if (save != null) {
                        try {
                            ImageIO.write(bufferedImage, "png", save.getFile());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            saveAs.setToolTipText("Save graph preview image to PNG file");
            actionsToolBar.add(saveAs);

            noPreviewReason = new JLabel("");
            noPreviewReason.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.NORTH;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.weighty = 1;
            
            add(toolBar, c);
            layout.setConstraints(toolBar, c);
            c.weightx = 2;
            c.weighty = 2;
            c.anchor = GridBagConstraints.LAST_LINE_END;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 2;
            add(noPreviewReason, c);
            noPreviewReason.setVisible(false);
        }

        public synchronized void addImage(@NotNull Document document) {
            this.document = document;
            bufferedImage = null;
        }

        private void paintGraph(Graphics g) {
            String text = document.getText();
            if (text.startsWith("#!")) {
                text = trimStart(text, text.substring(0, text.indexOf("\n")));
            }
            try (InputStream dot = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))) {
                final MutableGraph mutableGraph = new Parser().read(dot);
                if (bufferedImage == null || !mutableGraph.equals(current)) {
                    Graphviz graphviz = Graphviz.fromGraph(mutableGraph);
                    bufferedImage = graphviz.width(this.getWidth() - 100).height(this.getHeight() - 100).render(Format.PNG).toImage();
                    current = mutableGraph;
                }
                noPreviewReason.setVisible(false);
                if (bufferedImage != null) {
                    noPreviewReason.setVisible(false);
                    g.setColor(JBColor.WHITE);
                    g.fillRect(50, 75, this.getWidth() - 100, this.getHeight() - 100);
                    g.drawImage(bufferedImage, 50, 75, this.getWidth() - 100, this.getHeight() - 100, this);
                }
            } catch (IOException | ParserException | GraphvizException | NoClassDefFoundError e) {
                noPreviewReason.setText("<html><font color='grey'>Problem: " + e.getMessage() + "</font></html>");
                noPreviewReason.setVisible(true);
                bufferedImage = null;
                if (g != null) {
                    g.setColor(JBColor.WHITE);
                    g.fillRect(50, 75, this.getWidth() - 100, this.getHeight() - 100);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintGraph(g);
        }

        @Override
        public void dispose() {
        }
    }

    private static class ImageTransferable implements Transferable {
        private final BufferedImage myImage;

        public ImageTransferable(@NotNull BufferedImage image) {
            myImage = image;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
            return DataFlavor.imageFlavor.equals(dataFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor dataFlavor) throws UnsupportedFlavorException {
            if (!DataFlavor.imageFlavor.equals(dataFlavor)) {
                throw new UnsupportedFlavorException(dataFlavor);
            }
            return myImage;
        }
    }
}