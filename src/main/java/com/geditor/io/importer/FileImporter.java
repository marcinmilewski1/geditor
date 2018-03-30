package com.geditor.io.importer;

import com.geditor.io.importer.exception.ImportFileException;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileImporter {
    BufferedImage importImage(File file) throws ImportFileException;
}
