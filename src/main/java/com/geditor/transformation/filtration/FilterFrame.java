package com.geditor.transformation.filtration;

import com.geditor.ui.controller.EditorController;
import com.google.common.collect.Lists;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilterFrame extends JFrame{

    private final JButton smoothButton = new JButton("Smooth filter");
    private final JButton sobelHorizontalButton = new JButton("Sobel horizontal");
    private final JButton sobelVerticalButton = new JButton("Sobel vertical");
    private final JButton medianButton = new JButton("Median filter");
    private final JLabel matrixSize = new JLabel("Matrix size:");
    private final JTextField medianTextField = new JTextField("3");
    private final EditorController editorController = EditorController.getInstance();
    private final JButton highPassFilter1 = new JButton("High pass filter 1");
    private final JButton highPassFilter2 = new JButton("High pass filter 2");
    private final JButton gauss1 = new JButton("Gauss 1");
    private final JButton gauss2 = new JButton("Gauss 2");
    private final JButton customFilterButton = new JButton("Custom filter");
    private final TextArea customFilterTextArea = new TextArea("0,0,-1 ; 0,1,0 ; 0,0,0");

    public FilterFrame() throws HeadlessException {
        super("Filters");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(smoothButton, "wrap");
        container.add(sobelHorizontalButton);
        container.add(sobelVerticalButton, "wrap");
        container.add(medianButton);
        container.add(matrixSize);
        medianTextField.setMinimumSize(new Dimension(50, 40));
        medianTextField.setPreferredSize(new Dimension(50, 40));
        container.add(medianTextField, "wrap");
        container.add(highPassFilter1);
        container.add(highPassFilter2, "wrap");
        container.add(gauss1);
        container.add(gauss2, "wrap");
        container.add(customFilterButton);
        container.add(customFilterTextArea, "wrap");
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        smoothButton.addActionListener(e -> editorController.filter(FilterUtils.getSmoothMask()));
        sobelHorizontalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelHorizontalMask()));
        sobelVerticalButton.addActionListener(e -> editorController.filter(FilterUtils.getSobelVerticalMask()));
        medianButton.addActionListener(e -> editorController.medianFilter(Integer.parseInt(medianTextField.getText())));
        highPassFilter1.addActionListener(e -> editorController.filter(FilterUtils.getHighPass1FilterMask()));
        highPassFilter2.addActionListener(e -> editorController.filter(FilterUtils.getHighPass2FilterMask()));
        gauss1.addActionListener(e -> editorController.filter(FilterUtils.getGauss1FilterMask()));
        gauss2.addActionListener(e -> editorController.filter(FilterUtils.getGauss2FilterMask()));
        customFilterButton.addActionListener(e -> editorController.filter(parseMask()));
    }

    private float[][] parseMask() {
        String content = customFilterTextArea.getText();
        content.replaceAll("\\s+", "");
        String[] rows = content.split(";");

        List<List<Float>> mask = Lists.newArrayList();

        for (int i = 0; i < rows.length; i++) {
            mask.add(getNumbersFromRow(rows[i]));
        }

        float[][] array = new float[mask.size()][];

        for (int i = 0; i < mask.size(); i++) {
            List<Float> row = mask.get(i);
            array[i] = new float[row.size()];
            for (int j = 0; j < array.length; j++) {
                array[i][j] = row.get(j).floatValue();
            }
        }

        return array;
    }

    private List<Float> getNumbersFromRow(String row) {
        List<Float> result = Lists.newArrayList();
        String[] numbers = row.split(",");
        for (int i = 0; i < numbers.length; i++) {
            result.add(Float.valueOf(numbers[i]));
        }
        return result;
    }
}
