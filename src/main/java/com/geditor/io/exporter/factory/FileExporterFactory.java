package com.geditor.io.exporter.factory;

import com.geditor.io.exporter.FileExporter;
import com.geditor.io.exporter.jpeg.JpegExporter;
import com.geditor.io.exporter.ppm.p3.Ppm3Exporter;
import com.geditor.io.exporter.ppm.p6.Ppm6Exporter;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.util.FileExtension;

import static com.geditor.io.util.FileExtension.PPMP6;

public class FileExporterFactory {
    public static FileExporter getFileExporter(FileExtension extension) throws InvalidExtensionException {
        if (extension.equals(FileExtension.JPG)) {
            return new JpegExporter();
        } else if (extension.equals(FileExtension.PPMP3)) {
            return new Ppm3Exporter();
        } else if (extension.equals(PPMP6)) {
            return new Ppm6Exporter();
        } else {
            throw new InvalidExtensionException();
        }
    }
}
