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
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.Alarm;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizException;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.parse.ParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GraphPreviewFileEditor extends UserDataHolderBase implements FileEditor {
    private final static long PARSING_CALL_TIMEOUT_MS = 50L;
    @NotNull
    private final Alarm myPooledAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, this);

    @NotNull
    private ImagePanel myPanel = new ImagePanel();

    public GraphPreviewFileEditor(@NotNull VirtualFile file, Project project) {
        Document myDocument = FileDocumentManager.getInstance().getDocument(file);
        if (myDocument != null) {
            myPanel.addImage(myDocument);
            myDocument.addDocumentListener(new DocumentListener() {

                @Override
                public void beforeDocumentChange(@NotNull DocumentEvent e) {
                    myPooledAlarm.cancelAllRequests();
                }

                @Override
                public void documentChanged(@NotNull final DocumentEvent e) {
                    myPooledAlarm.addRequest(() -> myPanel.addImage(myDocument), PARSING_CALL_TIMEOUT_MS);
                }
            }, this);
        }
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

    public static class ImagePanel extends JBPanel implements Disposable {

        BufferedImage bufferedImage;
        private Document image;
        final JLabel noPreviewIsAvailable;

        public ImagePanel() {
            noPreviewIsAvailable = new JLabel("No preview is available");
            add(noPreviewIsAvailable);
            noPreviewIsAvailable.setVisible(false);
        }

        public synchronized void addImage(@NotNull Document image) {
            this.image = image;
            bufferedImage = null;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            try {
                if (bufferedImage == null) {
                    try (InputStream dot = new ByteArrayInputStream(image.getText().getBytes(StandardCharsets.UTF_8))) {
                        Graphviz graphviz = Graphviz.fromGraph(new Parser().read(dot));
                        bufferedImage = graphviz.width(this.getWidth() - 100).height(this.getHeight() - 100).render(Format.PNG).toImage();
                        noPreviewIsAvailable.setVisible(false);
                    } catch (IOException | ParserException | GraphvizException e) {
                        noPreviewIsAvailable.setVisible(true);
                    }
                }
                if (bufferedImage != null) {
                    g.drawImage(bufferedImage, 50, 50, this.getWidth() - 100, this.getHeight() - 100, this);
                }
            } catch (GraphvizException ignored) {

            }
        }

        @Override
        public void dispose() {
        }
    }
}