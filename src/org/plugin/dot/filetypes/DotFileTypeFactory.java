package org.plugin.dot.filetypes;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Factory for dot language file types
 * Currently .dot and .gv are considered as corresponding to dot language
 */
public class DotFileTypeFactory extends FileTypeFactory {

    /**
     * The method registers the file types corresponding to dot language
     * The file types .dot and .gv are considered as dot language related
     */
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(DotFileType.INSTANCE, "dot");
        fileTypeConsumer.consume(DotFileType.INSTANCE, "gv");
    }
}