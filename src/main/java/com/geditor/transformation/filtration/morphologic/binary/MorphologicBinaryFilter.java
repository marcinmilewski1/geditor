package com.geditor.transformation.filtration.morphologic.binary;

import com.geditor.commons.BinaryMaskMatrix;
import com.geditor.util.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MorphologicBinaryFilter {

    private final static boolean getPixelValue(Color color) {
        return color.getRed() == 0 ? true : false;
    }

    public static BufferedImage dilatationFilter(BufferedImage image, boolean[][] mask) {
        BufferedImage result = ImageUtils.createImageBackup(image);
        final List<Boolean> maskMatrix = new BinaryMaskMatrix(mask).getMaskList();
        final int margin = mask.length / 2;
        final int maskSize = mask.length * mask.length;

        IntStream.range(margin, image.getHeight() - margin)
                .parallel()
                .forEach(i -> {
                        IntStream.range(margin, image.getWidth() - margin).parallel().forEach(j -> {
                            List<Boolean> imageWindow = ImageUtils.getValuesFromRange(image, j, i, maskSize, margin).stream().map(MorphologicBinaryFilter::getPixelValue).collect(Collectors.toList());
                            result.setRGB(j, i, Color.WHITE.getRGB());
                            for (int index = 0; index < maskSize; ++index) {
                                if (imageWindow.get(index) == true && maskMatrix.get(index) == true) {
                                    result.setRGB(j, i, Color.BLACK.getRGB());
                                    break;
                                }
                            }
                        });
                });
        return result;
    }
    public static BufferedImage erosionFilter(BufferedImage image, boolean[][] mask) {
        BufferedImage result = ImageUtils.createImageBackup(image);
        final List<Boolean> maskMatrix = new BinaryMaskMatrix(mask).getMaskList();
        final int margin = mask.length / 2;
        final int maskSize = mask.length * mask.length;

        IntStream.range(margin, image.getHeight() - margin)
                .parallel()
                .forEach(i -> {
                        IntStream.range(margin, image.getWidth() - margin).parallel().forEach(j -> {
                            List<Boolean> imageWindow = ImageUtils.getValuesFromRange(image, j, i, maskSize, margin).stream().map(MorphologicBinaryFilter::getPixelValue).collect(Collectors.toList());
                            result.setRGB(j, i, Color.BLACK.getRGB());
                            for (int index = 0; index < maskSize; ++index) {
                                if (imageWindow.get(index) == false && maskMatrix.get(index) == false) {
                                    result.setRGB(j, i, Color.WHITE.getRGB());
                                    break;
                                }
                            }
                        });
                });
        return result;
    }

    public static BufferedImage openingFilter(BufferedImage image, boolean[][] dilatationMask, boolean[][] erosionMask) {
        BufferedImage afterErosion = erosionFilter(image, erosionMask);
        return dilatationFilter(afterErosion, dilatationMask);
    }

    public static BufferedImage closingFilter(BufferedImage image, boolean[][] dilatationMask, boolean[][] erosionMask) {
        BufferedImage afterDilatation = dilatationFilter(image, dilatationMask);
        return erosionFilter(afterDilatation, erosionMask);
    }

    public static BufferedImage thickening(BufferedImage bufferedImage) {
        return new ThickeningFilter().filterImage(bufferedImage);
    }
    public static BufferedImage thinning(BufferedImage bufferedImage) {
        return new ThinningFilter().filterImage(bufferedImage);
    }
}
