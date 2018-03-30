package com.geditor.ui.color;

import com.geditor.commons.Observable;
import com.geditor.commons.Observer;
import com.google.common.collect.Lists;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class ColorConverterCMYKSliderPanel extends JPanel implements Observable {
    private List<Observer> observers = Lists.newArrayList();
    private JSlider cyanSlider = new JSlider();
    private JSlider magentaSlider = new JSlider();
    private JSlider yellowSlider = new JSlider();
    private JSlider blackSlider = new JSlider();
    private JTextField cyanJTextField = new JTextField();
    private JTextField magentaJTextField = new JTextField();
    private JTextField yellowJTextField = new JTextField();
    private JTextField blackJTextField = new JTextField();

    private ChangeListener changeListener = new ChangeListener() {
        private void assistStateChanged() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    updateTextValues();
                    notifyObservers();
                }
            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            assistStateChanged();
        }
    };


    private FocusListener textFieldFocusListener = new FocusListener() {
        private void focusLostAssist() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
                    updateSlidersValues();
                }
            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            focusLostAssist();
        }
    };

    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            assistDateText();
        }

        private void assistDateText() {
            Runnable doAssist = new Runnable() {
                @Override
                public void run() {
//                    updateSlidersValues();
                }
            };
            SwingUtilities.invokeLater(doAssist);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    };

    private void updateSlidersValues() {
        cyanSlider.setValue((int)(Float.parseFloat(cyanJTextField.getText()) * 1000));
        magentaSlider.setValue((int)(Float.parseFloat(magentaJTextField.getText()) * 1000));
        yellowSlider.setValue((int)(Float.parseFloat(yellowJTextField.getText()) * 1000));
        blackSlider.setValue((int)(Float.parseFloat(blackJTextField.getText()) * 1000));
    }

    private void updateTextValues() {
        cyanJTextField.setText(String.valueOf((float)cyanSlider.getValue() / (float)1000));
        magentaJTextField.setText(String.valueOf((float)magentaSlider.getValue() / (float)1000));
        yellowJTextField.setText(String.valueOf((float)yellowSlider.getValue() / (float)1000));
        blackJTextField.setText(String.valueOf((float)blackSlider.getValue() / (float)1000));
    }

    public ColorConverterCMYKSliderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("CMYK"));
        addSlider(cyanSlider, new JLabel("Cyan"), cyanJTextField);
        addSlider(magentaSlider, new JLabel("Magenta"), magentaJTextField);
        addSlider(yellowSlider, new JLabel("Yellow"), yellowJTextField);
        addSlider(blackSlider, new JLabel("Black"), blackJTextField);

    }

    public CMYKStructure getValues() {
        return new CMYKStructure(
                (float)cyanSlider.getValue() /(float) 1000,
                (float)magentaSlider.getValue() / (float) 1000,
                (float)yellowSlider.getValue() /(float) 1000,
                (float)blackSlider.getValue()/ (float) 1000);
    }

    public void setValues(CMYKStructure cmykStructure) {
        removeSlidersChangeEventListeners();
        updateSliderValues(cmykStructure);
        updateTextValues();
        addSlidersChangeEventListeners();
    }

    private void addSlidersChangeEventListeners() {
        cyanSlider.addChangeListener(changeListener);
        magentaSlider.addChangeListener(changeListener);
        yellowSlider.addChangeListener(changeListener);
        blackSlider.addChangeListener(changeListener);
    }

    private void removeSlidersChangeEventListeners() {
        cyanSlider.removeChangeListener(changeListener);
        magentaSlider.removeChangeListener(changeListener);
        yellowSlider.removeChangeListener(changeListener);
        blackSlider.removeChangeListener(changeListener);
    }

    private void updateSliderValues(CMYKStructure cmykStructure) {
        cyanSlider.setValue((int)(cmykStructure.c * 1000));
        magentaSlider.setValue((int)(cmykStructure.m * 1000));
        yellowSlider.setValue((int)(cmykStructure.y * 1000));
        blackSlider.setValue((int)(cmykStructure.k * 1000));
    }

    private void addSlider(JSlider slider, JLabel label, JTextField jTextField) {
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(1000);
        slider.addChangeListener(changeListener);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(slider);
        jTextField.setText("0");
        jTextField.setMinimumSize(new Dimension(50,30));
        jTextField.setPreferredSize(new Dimension(50,30));
        jTextField.getDocument().addDocumentListener(documentListener);
        jTextField.addFocusListener(textFieldFocusListener);
        panel.add(jTextField);
        add(panel);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(e-> e.update(this, getValues()));
    }

}
