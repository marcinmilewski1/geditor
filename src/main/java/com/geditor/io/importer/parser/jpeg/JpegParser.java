package com.geditor.io.importer.parser.jpeg;

import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JpegParser implements FileParser {

    @Override
    public BufferedImage parse(File file) throws ParserException {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new ParserException(e);
        }
    }

}
