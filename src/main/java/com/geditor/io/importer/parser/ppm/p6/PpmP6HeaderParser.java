package com.geditor.io.importer.parser.ppm.p6;

import com.geditor.io.importer.parser.BinaryParser;
import com.geditor.io.importer.parser.exception.ParserException;

public class PpmP6HeaderParser extends BinaryParser {

    private PpmP6Header ppmP6Header;

    public PpmP6HeaderParser(byte[] bytes) {
        super(bytes);
    }

    public PpmP6Header parseHeader() throws ParserException {
        ppmP6Header = new PpmP6Header();
        String currentLine = getLine();
        checkP6Marker(currentLine);

        int comments = 0;
        while (isComment(currentLine = getLine(), '#')) {
            ++comments;
        }
        ppmP6Header.setCommentLines(comments);
        getWidthAndHeight(currentLine);
        ppmP6Header.setMaxColor(getMaxColorValue(currentLine = getLine()));
        ppmP6Header.setDataSize(ppmP6Header.getWidth() * ppmP6Header.getHeight() * 3);
        ppmP6Header.setLastIndex(index);

        return ppmP6Header;
    }

    private void getWidthAndHeight(String currentLine) throws ParserException {
        String[] parts = currentLine.split("\\s+");
        if (parts.length != 2) {
            throw new ParserException("Wrong header - width and height");
        } else {
            ppmP6Header.setWidth(Integer.parseInt(parts[0]));
            ppmP6Header.setHeight(Integer.parseInt(parts[1]));
        }
    }


    private void checkP6Marker(String firstLine) throws ParserException {
        if (!firstLine.equals("P6")) throw new ParserException("Wrong header.");
    }

    private int getMaxColorValue(String line) throws ParserException {
        int maxColorValue = Integer.parseInt(line);
        if (maxColorValue < 0 && maxColorValue > 255) throw new ParserException("Wrong max color value");
        return maxColorValue;
    }

}
