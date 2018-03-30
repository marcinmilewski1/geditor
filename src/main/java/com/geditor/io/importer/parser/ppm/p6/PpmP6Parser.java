package com.geditor.io.importer.parser.ppm.p6;

import com.geditor.io.importer.parser.FileParser;
import com.geditor.io.importer.parser.exception.ParserException;
import com.geditor.io.importer.parser.ppm.AbstractPpmParser;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP6Parser extends AbstractPpmParser implements FileParser{

    private int[] dataArray;
    private int dataIndex = 0;
    private int dataSize = 0;

    private byte[] fileContent;
    private int fileLength  = 0;
    private int bodyIndex = 0;
    private PpmP6Header ppmP6Header;

    @Override
    public BufferedImage parse(File file) throws ParserException {

        BufferedImage bufferedImage = null;
        try {
            ByteSource byteSource = Files.asByteSource(file);
            fileContent = byteSource.read();
            fileLength = Math.toIntExact(byteSource.size());
            fileContent = byteSource.read();
            PpmP6HeaderParser ppmP6HeaderParser =  new PpmP6HeaderParser(fileContent);
            ppmP6Header = ppmP6HeaderParser.parseHeader();
            bodyIndex = ppmP6Header.getLastIndex();
            allocatDataArray();
            parseBody();
            bufferedImage = convertToBufferedImage();
        } catch (IOException e) {
            throw new ParserException(e);
        } catch (NoSuchElementException e) {
            throw new ParserException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException(e);
        } catch (NumberFormatException e) {
            throw new ParserException(e);
        }
        return bufferedImage;
    }

    private void allocatDataArray() {
        dataArray = new int[fileLength - ppmP6Header.getLastIndex()];
    }

    private void parseBody() throws ParserException {
        for (int i = bodyIndex; i < fileLength; i++) {
            parseBodyRow(fileContent[i]);
        }
    }

    private void parseBodyRow(byte aByte) throws ParserException {
            int value =  aByte & 0x000000ff;
            if (value < 0 || value > ppmP6Header.getMaxColor()) throw new ParserException("Wrong byte value");
            dataArray[dataIndex++] = value;
    }

    private BufferedImage convertToBufferedImage() {
        BufferedImage image = new BufferedImage(ppmP6Header.getWidth(), ppmP6Header.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.getRaster().setPixels(0,0,ppmP6Header.getWidth(),ppmP6Header.getHeight(),dataArray);
        return image;
    }


}
