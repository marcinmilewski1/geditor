package com.geditor.io.exporter.writer.ppm;

import com.geditor.io.exporter.writer.AbstractFileWriter;

import java.awt.image.Raster;

public abstract class AbstractPpmWriter extends AbstractFileWriter {
    protected int[] getData(Raster raster) {
        return raster.getPixels(0,0, raster.getWidth(), raster.getHeight(), (int[]) null);
    }

    protected abstract String getHeaderLine();

    protected String getWidthAndHeighLine(Raster raster) {
        String line = raster.getWidth() + " " + raster.getHeight();
        return line;
    }

    protected String getMaxColorLine(int[] dataTab) {
        int max = dataTab[0];
        for (int i = 1; i < dataTab.length; i++) {
            if (dataTab[i] > max) {
                max = dataTab[i];
            }
        }
        return String.valueOf(max);
    }



}
