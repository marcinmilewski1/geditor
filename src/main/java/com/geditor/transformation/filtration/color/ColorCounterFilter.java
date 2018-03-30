package com.geditor.transformation.filtration.color;

import com.geditor.util.ImageUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ColorCounterFilter {
    private volatile int greenCounter;

    public Pair<BufferedImage, Double> countGreen(BufferedImage image, int threshold) {
        if (threshold < 0 || threshold > 100) throw new IllegalArgumentException("Invalid threshold");
        final float thresholdPercentage = threshold == 0 ? 0 : (float)threshold/ 100;
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        AtomicInteger atomicCounter = new AtomicInteger(0);
        IntStream.range(0, image.getHeight())
                .parallel()
                .forEach(i -> {
                    IntStream.range(0, image.getWidth()).
                            parallel().forEach(j -> {
                        Color color = new Color(image.getRGB(j, i));
                        int green = color.getGreen();
                        int red = color.getRed();
                        int blue = color.getBlue();
                        if (green / (float)(green + red + blue) >= thresholdPercentage) {
                            greenCounter = atomicCounter.incrementAndGet();
                            result.setRGB(j, i, color.getRGB());
                        }
                    });
                });
        return new ImmutablePair<>(result, ((float)greenCounter/(image.getWidth() * image.getHeight())) * 100.0);
    }

}
