package com.geditor.io.importer.parser;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryParser {
    private final byte[] bytes;
    protected int index = 0;

    public boolean isComment(String line, char commentMarker) {
        if (line.startsWith(String.valueOf(commentMarker))) {
            return true;
        }
        else return false;
    }

    public String getLine() {
        StringBuffer sb = new StringBuffer();
        while (getCurrentChar() != '\n') {
            sb.append(getCurrentChar());
            moveToNextByte();
        }
        moveToNextLine();
        return sb.toString();
    }

    public void moveToNextLine() {
        char c = getCurrentChar();
        boolean firstCharIsNotNewLine = false;
        while (c != '\n') {
            moveToNextByte();
            firstCharIsNotNewLine = true;
        }
        if (firstCharIsNotNewLine == false) {
            moveToNextByte();
        }
    }

    public void moveToNextByte() {
        ++index;
    }

    public char getCurrentChar() {
        return (char) (bytes[index] & 0xFF);
    }

}
