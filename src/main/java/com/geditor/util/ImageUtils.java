package com.geditor.util;

import java.awt.*;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.*;

public class ImageUtils {
    public static BufferedImage createImageBackup(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static java.util.List<Color> getValuesFromRange(BufferedImage image, final int row, final int col, final int maskSize, final int range) {
        java.util.List<Color> colors = new ArrayList<>();
        for (int i = row - range; i <= row + range; ++i) {
            for (int j = col - range; j <= col + range; ++j) {
                colors.add(new Color(image.getRGB(i, j)));
            }
        }
        return colors;
    }
}
