// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.plugin.dot.preview;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBPanel;
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
import java.io.IOException;
import java.io.InputStream;

public class GraphPreviewFileEditor extends UserDataHolderBase implements FileEditor {
    @NotNull
    private ImagePanel myPanel = new ImagePanel();

    public GraphPreviewFileEditor(@NotNull VirtualFile file, Project project) {
        myPanel.addImage(file);
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
        private VirtualFile image;

        public ImagePanel() {
        }

        public void addImage(VirtualFile image) {
            this.image = image;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            try {
                if (bufferedImage == null) {
                    try (InputStream dot = image.getInputStream()) {
                        Graphviz graphviz = Graphviz.fromGraph(new Parser().read(dot));
                        bufferedImage = graphviz.width(this.getWidth() - 100).height(this.getHeight() - 100).render(Format.PNG).toImage();
                    } catch (IOException | ParserException | GraphvizException e) {
                        add(new JLabel("No preview is available"));
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
            bufferedImage = null;
            image = null;
        }
    }
}