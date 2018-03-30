package com.geditor.transformation.d2;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class D2MenuPanel extends JPanel {
    private final D2TransformationController d2TransformationController = D2TransformationController.getInstance();
    private final JButton resetButton = new JButton("Reset");
    private final JButton translationButton = new JButton("Translation");
    private final JButton rotationButton = new JButton("Rotation");
    private final JButton scaleButton = new JButton("Scale");

    private final JLabel translationLabel = new JLabel("Translation:");
    private final JLabel rotationLabel = new JLabel("Rotation:");
    private final JLabel scaleLabel = new JLabel("Scale:");
    private final JLabel xTranslationLabel = new JLabel("x");
    private final JLabel yTranslationLabel = new JLabel("y");
    private final JLabel alphaLabel = new JLabel("alpha");
    private final JLabel pivotLabel = new JLabel("pivot:");
    private final JLabel xRotationLabel = new JLabel("x");
    private final JLabel yRotationLabel = new JLabel("y");
    private final JLabel kScaleLabel = new JLabel("k");
    private final JLabel scaleOrigin = new JLabel("origin:");
    private final JLabel xScaleLabel = new JLabel("x");
    private final JLabel xScaleValueLabel = new JLabel("x");
    private final JLabel yScaleLabel = new JLabel("y");
    private final JLabel yScaleValueLabel = new JLabel("y");


    private final JTextField xTranslationTextField = new JTextField();
    private final JTextField yTranslationTextField = new JTextField();
    private final JTextField angleRotationTextField = new JTextField("0");
    private final JTextField xRotationTextField = new JTextField("-1");
    private final JTextField yRotationTextField = new JTextField("-1");
    private final JTextField xScaleTextField = new JTextField("0");
    private final JTextField yScaleTextField = new JTextField("0");
    private final JTextField xScaleValueTextField = new JTextField("1");
    private final JTextField yScaleValueTextField = new JTextField("1");

    private final Dimension textFieldDimension = new Dimension(60, 30);
    public D2MenuPanel() {
        resetButton.addActionListener(e -> {
            d2TransformationController.reset();
        });
        translationButton.addActionListener(e -> {
            double dx = Double.parseDouble((xTranslationTextField.getText()));
            double dy = Double.parseDouble((yTranslationTextField.getText()));
            d2TransformationController.translate(dx, dy);
        });

        rotationButton.addActionListener(e -> {
            double angle = Double.parseDouble(angleRotationTextField.getText());
            double xPivot = Double.parseDouble(xRotationTextField.getText());
            double yPivot = Double.parseDouble(yRotationTextField.getText());
            d2TransformationController.rotate(-Math.toRadians(angle), xPivot, -yPivot);
        });

        scaleButton.addActionListener(e -> {
            double xScaleValue = Double.parseDouble(xScaleValueTextField.getText());
            double yScaleValue = Double.parseDouble(yScaleValueTextField.getText());
            double xOrigin = Double.parseDouble(xScaleTextField.getText());
            double yOrigin = Double.parseDouble(yScaleTextField.getText());
            d2TransformationController.scale(xScaleValue,yScaleValue, xOrigin, -yOrigin);
        });

        xTranslationTextField.setPreferredSize(textFieldDimension);
        yTranslationTextField.setPreferredSize(textFieldDimension);
        angleRotationTextField.setPreferredSize(textFieldDimension);
        xRotationTextField.setPreferredSize(textFieldDimension);
        yRotationTextField.setPreferredSize(textFieldDimension);
        xScaleTextField.setPreferredSize(textFieldDimension);
        yScaleTextField.setPreferredSize(textFieldDimension);
        xScaleValueTextField.setPreferredSize(textFieldDimension);
        yScaleValueTextField.setPreferredSize(textFieldDimension);

        setLayout(new MigLayout());
        add(resetButton, "wrap");
        add(translationLabel, "wrap");
        add(xTranslationLabel);
        add(xTranslationTextField, "wrap");
        add(yTranslationLabel);
        add(yTranslationTextField, "wrap");
        add(translationButton, "wrap");
        add(new JSeparator(SwingConstants.HORIZONTAL), "wrap");

        add(rotationLabel, "wrap");
        add(alphaLabel);
        add(angleRotationTextField, "wrap");
        add(pivotLabel, "wrap");
        add(xRotationLabel);
        add(xRotationTextField, "wrap");
        add(yRotationLabel);
        add(yRotationTextField, "wrap");
        add(rotationButton, "wrap");
        add(new JSeparator(SwingConstants.HORIZONTAL), "wrap");

        add(scaleLabel, "wrap");
        add(xScaleValueLabel);
        add(xScaleValueTextField,"wrap");
        add(yScaleValueLabel);
        add(yScaleValueTextField, "wrap");
        add(scaleOrigin, "wrap");
        add(xScaleLabel);
        add(xScaleTextField, "wrap");
        add(yScaleLabel);
        add(yScaleTextField, "wrap");

        add(scaleButton, "wrap");
    }
}