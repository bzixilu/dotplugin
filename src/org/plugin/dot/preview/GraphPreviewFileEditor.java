// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.plugin.dot.preview;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import com.intellij.util.Alarm;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.parse.ParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GraphPreviewFileEditor extends UserDataHolderBase implements FileEditor {
    private final static long PARSING_CALL_TIMEOUT_MS = 1000L;
    @NotNull
    private final Alarm myPooledAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, this);

    @NotNull
    private final ImagePanel myPanel = new ImagePanel();

    public GraphPreviewFileEditor(@NotNull VirtualFile file) {
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
                myPanel.paintGraph(myPanel.getGraphics());
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

    public static class ImagePanel extends JPanel implements Disposable {

        public BufferedImage bufferedImage;
        private Document document;
        final JLabel noPreviewIsAvailable;
        final JLabel noPreviewReason;
        private MutableGraph current;

        public ImagePanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            noPreviewIsAvailable = new JLabel("<html><font color='lightgrey'>No preview is available</font></html>");
            noPreviewIsAvailable.setHorizontalAlignment(SwingConstants.CENTER);
            noPreviewReason = new JLabel("");
            noPreviewReason.setHorizontalAlignment(SwingConstants.CENTER);
            add(noPreviewIsAvailable);
            add(noPreviewReason);
            noPreviewIsAvailable.setVisible(false);
            noPreviewReason.setVisible(false);
        }

        public synchronized void addImage(@NotNull Document document) {
            this.document = document;
            bufferedImage = null;
        }

        private void paintGraph(Graphics g) {
            try (InputStream dot = new ByteArrayInputStream(document.getText().getBytes(StandardCharsets.UTF_8))) {
                final MutableGraph mutableGraph = new Parser().read(dot);
                if (bufferedImage == null || !mutableGraph.equals(current)) {
                    Graphviz graphviz = Graphviz.fromGraph(mutableGraph);
                    bufferedImage = graphviz.width(this.getWidth() - 100).height(this.getHeight() - 100).render(Format.PNG).toImage();
                    current = mutableGraph;
                }
                noPreviewIsAvailable.setVisible(false);
                noPreviewReason.setVisible(false);
                if (bufferedImage != null) {
                    g.setColor(JBColor.WHITE);
                    g.fillRect(50, 50, this.getWidth() - 100, this.getHeight() - 100);
                    g.drawImage(bufferedImage, 50, 50, this.getWidth() - 100, this.getHeight() - 100, this);
                }
            } catch (IOException | ParserException | GraphvizException | NoClassDefFoundError e) {
                noPreviewReason.setText("<html><font color='grey'>Reason: " + e.getMessage() +"</font></html>");
                noPreviewReason.setVisible(true);
                noPreviewIsAvailable.setVisible(true);
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
}