package com.geditor.transformation.binarization;

import com.geditor.transformation.histogram.HistogramUtils;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.log10;

@Log4j
public class BinarizationUtils {
    public static BufferedImage manual(BufferedImage bufferedImage, int threshold) {

        int lut[] = createManualBinaryThresholdLUT(threshold);
        return createImage(bufferedImage, lut);
    }

    private static BufferedImage createImage(BufferedImage bufferedImage, int[] lut) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        ExecutorService executor = Executors.newWorkStealingPool();

        for (int i = 0; i < bufferedImage.getHeight(); ++i) {
            int finalI = i;
            executor.execute(() -> {
                for (int j = 0; j < bufferedImage.getWidth(); ++j) {
                    int finalJ = j;
                    Color color = new Color(bufferedImage.getRGB(finalJ, finalI));
                    result.setRGB(finalJ, finalI, new Color(
                            lut[color.getRed()],
                            lut[color.getGreen()],
                            lut[color.getBlue()]).getRGB());
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error(e);
        }
        return result;
    }

    private static int[] createManualBinaryThresholdLUT(int threshold) {
        int[] lut = new int[256];
        for (int i = 0; i < lut.length; i++) {
            if (i >= threshold) {
                lut[i] = 255;
            }
        }
        return lut;
    }

    public static BufferedImage percentBlackSelection(BufferedImage bufferedImage, int percent) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int[] grayChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel(); // red is the same as blue and green in gray picture

        int lut[] = createBlackPercentLUT(percent, bufferedImage.getHeight() * bufferedImage.getWidth(), grayChannel);
        return createImage(bufferedImage, lut);
    }

    public static BufferedImage meanIterativeSelection(BufferedImage bufferedImage) {
        int[] grayChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel();
        final int histogramLength = grayChannel.length;
        int estimatedThreshold = computeHistogramMean(grayChannel);
        int leftPartMean = 0;
        int rightPartMean = 0;
        int previousChange = 0;
        int currentChange = 1;
        do {
            previousChange = currentChange;
            leftPartMean = computeHistogramMean(0, estimatedThreshold, grayChannel);
            rightPartMean = computeHistogramMean(estimatedThreshold, histogramLength, grayChannel);
            estimatedThreshold = (leftPartMean + rightPartMean) / 2;
            currentChange = rightPartMean - leftPartMean;
        } while (previousChange != currentChange);

        log.debug("Iterative selection threshold: " + estimatedThreshold);
        int lut[] = createManualBinaryThresholdLUT(estimatedThreshold);
        return createImage(bufferedImage, lut);
    }

    public static BufferedImage minError(BufferedImage bufferedImage) {
        // Kittler and J. Illingworth, "Minimum error thresholding," Pattern Recognition, vol. 19, pp. 41-47, 1986.
        // wageSquareSum. simpleSum. Glasbey, "An analysis of histogram-based thresholding algorithms," CVGIP: Graphical Models and Image Processing, vol. 55, pp. 532-537, 1993.
        // Ported to ImageJ plugin by G.Landini from Antti Niemisto's Matlab code (GPL)
        // Original Matlab code Copyright (wageSquareSum) 2004 Antti Niemisto
        // See http://www.cs.tut.fi/~ant/histthresh/ for an excellent slide presentation
        // and the original Matlab code.

        int[] grayChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel();
        final int histogramLength = grayChannel.length;
        int estimatedThreshold = computeHistogramMean(grayChannel);
        int previousThreshold = -1;


        double mu, nu, p = 0.0, q, sigma2, tau2, w0, w1, w2, sqterm, temp;
        while (estimatedThreshold != previousThreshold) {
            //Calculate some statistics.
            mu = wageSum(grayChannel, (int) p) / simpleSum(grayChannel, (int)p);
            nu = (wageSum(grayChannel, 255) - wageSum(grayChannel,(int) p)) / (simpleSum(grayChannel, 255) - simpleSum(grayChannel, (int) p));
            p = simpleSum(grayChannel, (int)p) / simpleSum(grayChannel, 255);
            q = (simpleSum(grayChannel, 255) - simpleSum(grayChannel, (int)p)) / simpleSum(grayChannel, 255);
            sigma2 = wageSquareSum(grayChannel,(int) p) / simpleSum(grayChannel,(int) p) - (mu * mu);
            tau2 = (wageSquareSum(grayChannel, 255) - wageSquareSum(grayChannel,(int) p)) / (simpleSum(grayChannel, 255) - simpleSum(grayChannel, (int)p)) - (nu * nu);

            //The terms of the quadratic equation to be solved.
            w0 = 1.0 / sigma2 - 1.0 / tau2;
            w1 = mu / sigma2 - nu / tau2;
            w2 = (mu * mu) / sigma2 - (nu * nu) / tau2 + log10((sigma2 * (q * q)) / (tau2 * (p * p)));

            //If the next threshold would be imaginary, return with the current one.
            sqterm = (w1 * w1) - w0 * w2;
            if (sqterm < 0) {
                log.debug("MinError(I): not converging. Try \'Ignore black/white\' options");
                break;
            }

            //The updated threshold is the integer part of the solution of the quadratic equation.
            previousThreshold = estimatedThreshold;
            temp = (w1 + Math.sqrt(sqterm)) / w0;

            if (Double.isNaN(temp)) {
                estimatedThreshold = previousThreshold;
            } else
                estimatedThreshold = (int) Math.floor(temp);
        }

        log.debug("Min error threshold: " + estimatedThreshold);
        int lut[] = createManualBinaryThresholdLUT(estimatedThreshold);
        return createImage(bufferedImage, lut);
    }

    private static double simpleSum(int[] y, int j) {
        double x = 0;
        for (int i = 0; i <= j; i++)
            x += y[i];
        return x;
    }

    private static double wageSum(int[] y, int j) {
        double x = 0;
        for (int i = 0; i <= j; i++)
            x += i * y[i];
        return x;
    }

    private static double wageSquareSum(int[] y, int j) {
        double x = 0;
        for (int i = 0; i <= j; i++)
            x += i * i * y[i];
        return x;
    }

    public static BufferedImage entropySelection(BufferedImage bufferedImage) {
        double[] histogramNormalizedTable = new double[256];
        double[] entropyTable = new double[256];
        int[] histogramChannel = HistogramUtils.createHistogram(bufferedImage).getRedChannel();
        final long imageSize = bufferedImage.getHeight() * bufferedImage.getWidth();

        for (int i = 0; i < histogramNormalizedTable.length; i++) {
            histogramNormalizedTable[i] = (double) histogramChannel[i] / (double) imageSize;
        }

        for (int i = 0; i < histogramNormalizedTable.length; i++) {
            entropyTable[i] = histogramNormalizedTable[i] * Math.log(histogramNormalizedTable[i]);
        }
        log.debug("Histogram normalized table: " + Arrays.toString(histogramNormalizedTable));
        log.debug("Histogram entropy table: " + Arrays.toString(entropyTable));
        double entropyMin = entropyTable[0];
        int threshold = 0;

        for (int i = 0; i < histogramNormalizedTable.length; i++) {
            if (entropyMin > entropyTable[i]) {
                entropyMin = entropyTable[i];
                threshold = i;
            }
        }
        log.debug("Entropy selection threshold:  " + threshold);
        int lut[] = createManualBinaryThresholdLUT(threshold);
        return createImage(bufferedImage, lut);
    }

    private static long computeHistogramSize(int from, int to, int[] histogramChannel) {
        long result = 0;
        for (int i = from; i < to; i++) {
            result += histogramChannel[i];
        }
        return result;
    }

    private static long computeWageHistogramSum(int from, int end, int[] histogramChannel) {
        if (from > end || histogramChannel.length < end) throw new IllegalArgumentException();
        long result = 0;
        for (int i = from; i < end; i++) {
            result += (i * histogramChannel[i]);
        }
        return result;
    }

    private static int computeHistogramMean(int from, int end, int[] histogramChannel) {
        long sum = computeWageHistogramSum(from, end, histogramChannel);
        long totalSize = computeHistogramSize(from, end, histogramChannel);
        return (int) (sum / (totalSize));
    }

    private static int computeHistogramMean(int[] histogramChannel) {
        long sum = computeWageHistogramSum(0, histogramChannel.length, histogramChannel);
        long totalSize = computeHistogramSize(0, histogramChannel.length, histogramChannel);
        return (int) (sum / totalSize);
    }

    private static int[] createBlackPercentLUT(int percent, int size, int[] grayChannel) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Wrong percent value.");
        int[] lut = new int[256];
        int sum = 0;
        int threshold = (int) (percent * size * 0.01);
        for (int i = 0; i < lut.length; i++) {
            sum += grayChannel[i];
            if (sum >= threshold) {
                lut[i] = 255;
            }
        }
        return lut;
    }


}
