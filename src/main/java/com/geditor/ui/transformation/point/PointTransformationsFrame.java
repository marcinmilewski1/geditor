package com.geditor.ui.transformation.point;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;
import com.geditor.ui.controller.EditorController;
import com.geditor.ui.editor.Editor;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PointTransformationsFrame extends JFrame implements Observer {

    private final EditorController editorController = EditorController.getInstance();
    private final JSlider brightnessSlider = new JSlider();
    private final JSlider redShiftSlider = new JSlider();
    private final JSlider greenShiftSlider = new JSlider();
    private final JSlider blueShiftSlider = new JSlider();
    private final JLabel redShiftLabel = new JLabel("Red shift");
    private final JLabel greenShiftLabel = new JLabel("Green shift");
    private final JLabel blueShiftLabel = new JLabel("Blue shift");
    private final JLabel brightnessLabel = new JLabel("Brighteness");
    private final JLabel multiplyLabel = new JLabel("Multiply");
    private final JTextField brightnessTextfield = new JTextField();
    private final JTextField redShiftTextField = new JTextField();
    private final JTextField greenShiftTextField = new JTextField();
    private final JTextField blueShiftTextField = new JTextField();

    private final JTextField multiplyTextField = new JTextField();
    private final JButton multiplyButton = new JButton("Multiply");
    private final JButton toGrayButton = new JButton("To gray");
    private final JButton toGrayYUNButton = new JButton("To gray YUN");

    private ChangeListener brightnessChangeSliderListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    changeBrightnessTextField();
                    if (!brightnessSlider.getValueIsAdjusting()) {
                        editorController.changeBrighteness(brightnessSlider.getValue());
                    }
                }

            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };

    private ChangeListener redShiftChangeSliderListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    changeRedShiftTextField();
                    if (!redShiftSlider.getValueIsAdjusting()) {
                        editorController.addRed(redShiftSlider.getValue());
                    }
                }

            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };

    private ChangeListener greenShiftChangeSliderListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    changeGreenShiftTextField();
                    if (!greenShiftSlider.getValueIsAdjusting()) {
                        editorController.addGreen(greenShiftSlider.getValue());
                    }
                }

            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };

    private ChangeListener blueShiftChangeSliderListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    changeBlueShiftTextField();
                    if (!blueShiftSlider.getValueIsAdjusting()) {
                        editorController.addBlue(blueShiftSlider.getValue());
                    }
                }

            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };

    private void changeBrightnessTextField() {
        brightnessTextfield.setText(String.valueOf(brightnessSlider.getValue()));
    }
    private void changeRedShiftTextField() {
        redShiftTextField.setText(String.valueOf(redShiftSlider.getValue()));
    }
    private void changeGreenShiftTextField() {
        greenShiftTextField.setText(String.valueOf(greenShiftSlider.getValue()));
    }
    private void changeBlueShiftTextField() {
        blueShiftTextField.setText(String.valueOf(blueShiftSlider.getValue()));
    }

    public PointTransformationsFrame() throws HeadlessException {
        initBrightnessFields();
        initColorsShieftFields();

        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout("wrap 4"));
        contentPane.add(brightnessLabel);
        contentPane.add(brightnessSlider);
        contentPane.add(brightnessTextfield);
        contentPane.add(new JSeparator(SwingConstants.HORIZONTAL), "span");

        contentPane.add(redShiftLabel);
        contentPane.add(redShiftSlider);
        contentPane.add(redShiftTextField, "wrap");

        contentPane.add(greenShiftLabel);
        contentPane.add(greenShiftSlider);
        contentPane.add(greenShiftTextField, "wrap");

        contentPane.add(blueShiftLabel);
        contentPane.add(blueShiftSlider);
        contentPane.add(blueShiftTextField, "wrap");

        contentPane.add(new JSeparator(SwingConstants.HORIZONTAL), "span");

        multiplyTextField.setText("1.0");
        multiplyTextField.setMinimumSize(new Dimension(60, 30));
        multiplyTextField.setPreferredSize(new Dimension(60, 40));
        contentPane.add(multiplyLabel);
        contentPane.add(multiplyTextField);
        multiplyButton.addActionListener(e -> {
            editorController.multiply(Float.parseFloat(multiplyTextField.getText()));
        });
        contentPane.add(multiplyButton, "wrap");
        contentPane.add(new JSeparator(SwingConstants.HORIZONTAL), "span");

        toGrayButton.addActionListener(e -> editorController.toGray());
        toGrayYUNButton.addActionListener(e -> editorController.toGrayYUV());
        contentPane.add(toGrayButton);
        contentPane.add(toGrayYUNButton, "wrap");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();

    }

    private void initColorsShieftFields() {
        redShiftSlider.setValue(Editor.getInstance().getRedShift());
        redShiftSlider.setMinimum(-255);
        redShiftSlider.setMaximum(255);
        redShiftSlider.addChangeListener(redShiftChangeSliderListener);
        redShiftTextField.setText(String.valueOf(Editor.getInstance().getRedShift()));
        redShiftTextField.setMinimumSize(new Dimension(60, 30));
        redShiftTextField.setPreferredSize(new Dimension(60, 40));
        redShiftTextField.setEnabled(false);

        greenShiftSlider.setValue(Editor.getInstance().getGreenShift());
        greenShiftSlider.setMinimum(-255);
        greenShiftSlider.setMaximum(255);
        greenShiftSlider.addChangeListener(greenShiftChangeSliderListener);
        greenShiftTextField.setText(String.valueOf(Editor.getInstance().getGreenShift()));
        greenShiftTextField.setMinimumSize(new Dimension(60, 30));
        greenShiftTextField.setPreferredSize(new Dimension(60, 40));
        greenShiftTextField.setEnabled(false);

        blueShiftSlider.setValue(Editor.getInstance().getBlueShift());
        blueShiftSlider.setMinimum(-255);
        blueShiftSlider.setMaximum(255);
        blueShiftSlider.addChangeListener(blueShiftChangeSliderListener);
        blueShiftTextField.setText(String.valueOf(Editor.getInstance().getBlueShift()));
        blueShiftTextField.setMinimumSize(new Dimension(60, 30));
        blueShiftTextField.setPreferredSize(new Dimension(60, 40));
        blueShiftTextField.setEnabled(false);
    }

    private void initBrightnessFields() {
        brightnessSlider.setValue(Editor.getInstance().getBrightness());
        brightnessSlider.setMinimum(-255);
        brightnessSlider.setMaximum(255);
        brightnessSlider.addChangeListener(brightnessChangeSliderListener);
        brightnessTextfield.setText(String.valueOf(Editor.getInstance().getBrightness()));
        brightnessTextfield.setMinimumSize(new Dimension(60, 30));
        brightnessTextfield.setPreferredSize(new Dimension(60, 40));
        brightnessTextfield.setEnabled(false);
    }

    @Override
    public void update(Observable observable, Object object) {

    }
}
