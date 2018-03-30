package com.geditor.io.importer;

import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public abstract class AbstractFileImporter implements FileImporter{
    protected final FileParser parser;
    public AbstractFileImporter() {
        parser = getParser();
    }

    @Override
    public BufferedImage importImage(File file) throws ImportFileException {
        BufferedImage bufferedImage;
        try {
            bufferedImage = parser.parse(file);
        } catch (ParserException e) {
            throw new ImportFileException(e);
        }
        return bufferedImage;
    }

    protected abstract FileParser getParser();

}
