package com.geditor.io.importer.factory;

import com.geditor.io.importer.FileImporter;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.importer.jpeg.JpegImporter;
import com.geditor.io.importer.ppm.p3.Ppm3Importer;
import com.geditor.io.importer.ppm.p6.Ppm6Importer;
import com.geditor.io.util.FileExtension;

/**
 * Created by marcin on 19.03.16.
 */
public class FileImporterFactory {
    public static FileImporter getFileImporter(FileExtension extension) throws InvalidExtensionException {
        if (extension.equals(FileExtension.PPMP3)) {
            return new Ppm3Importer();
        }
        else if (extension.equals(FileExtension.PPMP6)) {
            return new Ppm6Importer();
        }
        else if (extension.equals(FileExtension.JPG) || extension.equals(FileExtension.JPEG)) {
            return new JpegImporter();
        }
        else {
            throw new InvalidExtensionException();
        }
    }
}
