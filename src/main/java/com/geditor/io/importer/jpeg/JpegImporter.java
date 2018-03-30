package com.geditor.io.importer.jpeg;

import com.geditor.io.importer.AbstractFileImporter;
import com.geditor.io.importer.FileImporter;
import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.jpeg.JpegParser;

public class JpegImporter extends AbstractFileImporter implements FileImporter{
    @Override
    protected FileParser getParser() {
        return new JpegParser();
    }
}
