package com.geditor.io.exporter.writer.ppm.p6;

import com.geditor.io.exporter.writer.ppm.AbstractPpmWriter;
import com.google.common.io.Files;

import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP6FileWriter extends AbstractPpmWriter {
    @Override
    public void write(RenderedImage image, File outputFile) throws IOException {
        Raster raster = image.getData();
        int[] dataTab = getData(raster);
        BufferedWriter writer = Files.newWriter(outputFile, Charset.forName("ASCII"));
        CharSequence charSequence = new StringBuilder(getHeaderLine()).append('\n')
                .append(getWidthAndHeighLine(raster)).append('\n')
                .append(getMaxColorLine(getData(raster))).append('\n');
        byte[] header = charSequence.toString().getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(header.length + dataTab.length);
        byteBuffer.put(header);
        byteBuffer.put(getByteData(dataTab));

        Files.write(byteBuffer.array(), outputFile);
    }

    @Override
    protected String getHeaderLine() {
        return "P6";
    }

    public byte[] getByteData(int[] dataTab) {
        byte[] tab = new byte[dataTab.length];
        for(int i = 0 ; i < dataTab.length; i++) {
            tab[i] = (byte) (dataTab[i] & 0x000000ff);
        }
        return tab;
    }
}
