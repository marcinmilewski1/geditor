package com.geditor.io.importer.parser;

import com.geditor.io.importer.parser.exception.ParserException;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileParser {
    BufferedImage parse(File file) throws ParserException;
}
