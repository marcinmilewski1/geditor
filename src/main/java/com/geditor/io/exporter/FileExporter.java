package com.geditor.io.exporter;

import com.geditor.io.exporter.exception.FileExportException;

import java.awt.image.RenderedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileExporter {
    void export(RenderedImage image, File outputFile) throws FileExportException;
}
