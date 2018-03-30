package com.geditor.ui.controller;

import com.geditor.transformation.binarization.BinarizationUtils;
import com.geditor.transformation.filtration.FilterUtils;
import com.geditor.transformation.filtration.color.ColorCounterFilter;
import com.geditor.transformation.filtration.color.ColorCounterFilteredImageFrame;
import com.geditor.transformation.filtration.morphologic.binary.MorphologicBinaryFilter;
import com.geditor.transformation.histogram.HistogramModel;
import com.geditor.transformation.histogram.HistogramUtils;
import com.geditor.transformation.point.PointTransformations;
import com.geditor.ui.editor.Editor;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class EditorController {
    private static final Editor editor = Editor.getInstance();
    private static EditorController instance = new EditorController();

    private EditorController() {
    }

    public static EditorController getInstance() {
        return instance;
    }

    public void changeBrighteness(int value) {
        int currentBrightness = Editor.getInstance().getBrightness();
        editor.setImage(PointTransformations.changeBrighteness(editor.getImage(), value - currentBrightness));
        editor.setBrightness(value);
        editor.repaint();
    }

    public void addRed(int value) {
        int currentRed = Editor.getInstance().getRedShift();
        editor.setImage(PointTransformations.addRed(editor.getImage(), value - currentRed));
        editor.setRedShift(value);
        editor.repaint();
    }

    public void addGreen(int value) {
        int currentGreen = Editor.getInstance().getGreenShift();
        editor.setImage(PointTransformations.addGreen(editor.getImage(), value - currentGreen));
        editor.setGreenShift(value);
        editor.repaint();
    }

    public void addBlue(int value) {
        int currentBlue = Editor.getInstance().getBlueShift();
        editor.setImage(PointTransformations.addBlue(editor.getImage(), value - currentBlue));
        editor.setBlueShift(value);
        editor.repaint();
    }

    public void multiply(float value) {
        editor.setImage(PointTransformations.multiply(editor.getImage(), value));
        editor.repaint();
    }

    public void toGray() {
        editor.setImage(PointTransformations.toGray(editor.getImage()));
        editor.repaint();
    }

    public void toGrayYUV() {
        editor.setImage(PointTransformations.toGrayYUV(editor.getImage()));
        editor.repaint();
    }

    public HistogramModel createHistogram() {
        long startTime = System.currentTimeMillis();
        HistogramModel histogramModel = HistogramUtils.createHistogram(editor.getImage());
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        editor.setHistogramModel(histogramModel);
        editor.repaint();
        return histogramModel;
    }

    public void stretchHistogram() {
        BufferedImage image = HistogramUtils.stretchHistogram(editor.getHistogramModel(), editor.getImage());
        editor.setImage(image);
        editor.repaint();
    }

    public void equalizeHistogram() {
        BufferedImage image = HistogramUtils.equalizeHistogram(editor.getImage());
        editor.setImage(image);
        editor.repaint();
    }

    public void manualBinarization(int threshold) {
        BufferedImage image = BinarizationUtils.manual(editor.getImageCopy(), threshold);
        editor.setImage(image);
        editor.repaint();
    }

    public void percentBlackSelectionBinarization(int percent) {
        BufferedImage image = BinarizationUtils.percentBlackSelection(editor.getImageCopy(), percent);
        editor.setImage(image);
        editor.repaint();
    }

    public void meanIterativeSelection() {
        BufferedImage image = BinarizationUtils.meanIterativeSelection(editor.getImageCopy());
        editor.setImage(image);
        editor.repaint();
    }

    public void entropySelection() {
        BufferedImage image = BinarizationUtils.entropySelection(editor.getImageCopy());
        editor.setImage(image);
        editor.repaint();
    }

    public void minErrorBinarization() {
        BufferedImage image = BinarizationUtils.minError(editor.getImageCopy());
        editor.setImage(image);
        editor.repaint();
    }

    public void createImageBackup() {
        ColorModel cm = editor.getImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = editor.getImage().copyData(null);
        editor.setImageCopy(new BufferedImage(cm, raster, isAlphaPremultiplied, null));
    }

    public void filter(float[][] mask) {
        createImageBackup();
        BufferedImage image = FilterUtils.filter(editor.getImageCopy(), mask);
        editor.setImage(image);
        editor.repaint();
    }

    public void medianFilter(int matrixSize) {
        createImageBackup();
        BufferedImage image = FilterUtils.medianFilter(editor.getImageCopy(), matrixSize);
        editor.setImage(image);
        editor.repaint();
    }

    public void dilatationFilter(boolean[][] mask) {
        BufferedImage image = MorphologicBinaryFilter.dilatationFilter(editor.getImage(), mask);
        editor.setImage(image);
        editor.repaint();
    }

    public void getImageBackup() {
        editor.setImage(editor.getImageCopy());
        editor.repaint();
    }

    public void erosionFilter(boolean[][] mask) {
        BufferedImage image = MorphologicBinaryFilter.erosionFilter(editor.getImage(), mask);
        editor.setImage(image);
        editor.repaint();
    }

    public void openingFilter(boolean[][] dilatationMask, boolean[][] erosionMask) {
        BufferedImage image = MorphologicBinaryFilter.openingFilter(editor.getImage(), dilatationMask, erosionMask);
        editor.setImage(image);
        editor.repaint();
    }

    public void closingFilter(boolean[][] dilatationMask, boolean[][] erosionMask) {
        BufferedImage image = MorphologicBinaryFilter.closingFilter(editor.getImage(), dilatationMask, erosionMask);
        editor.setImage(image);
        editor.repaint();
    }

    public void thickeningFilter() {
        BufferedImage image = MorphologicBinaryFilter.thickening(editor.getImage());
        editor.setImage(image);
        editor.repaint();
    }

    public void thinningFilter() {
        BufferedImage image = MorphologicBinaryFilter.thinning(editor.getImage());
        editor.setImage(image);
        editor.repaint();
    }

    public void countGreen(int threshold) {
        ColorCounterFilter colorCounterFilter = new ColorCounterFilter();
        Pair<BufferedImage, Double> imageColorCountPair = colorCounterFilter.countGreen(editor.getImage(), threshold);
        JOptionPane.showMessageDialog(null, imageColorCountPair.getValue() + "%", "Percent Green Counter", JOptionPane.INFORMATION_MESSAGE);
        ColorCounterFilteredImageFrame colorCounterFilteredImageFrame = new ColorCounterFilteredImageFrame(imageColorCountPair.getKey());
        colorCounterFilteredImageFrame.repaint();
    }

}
