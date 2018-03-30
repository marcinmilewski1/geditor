package com.geditor.io.exporter;

import com.geditor.io.exporter.exception.FileExportException;
import com.geditor.io.exporter.writer.FileWriter;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractFileExporter implements FileExporter{
    @Override
    public void export(RenderedImage image, File outputFile) throws FileExportException {
        try {
            getFileWriter().write(image, outputFile);
        } catch (IOException e) {
            throw new FileExportException(e);
        }
    }

    abstract protected FileWriter getFileWriter();
}
