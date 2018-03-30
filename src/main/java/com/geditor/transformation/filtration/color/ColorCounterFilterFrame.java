package com.geditor.transformation.filtration.color;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ColorCounterFilterFrame  extends JFrame{
    private final JLabel thresholdLabel = new JLabel("threshold");
    private final JLabel thresholdDescriptionLabel = new JLabel("(100 - only clear color pixels)");
    private final JTextField thresholdTextField = new JTextField("80");
    private final JButton greenCountButton = new JButton("Green");

    public ColorCounterFilterFrame() throws HeadlessException {
        super("Color counter");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(thresholdLabel);
        container.add(thresholdTextField);
        container.add(thresholdDescriptionLabel, "wrap");
        container.add(greenCountButton);
        thresholdTextField.setMinimumSize(new Dimension(50, 40));
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        greenCountButton.addActionListener(e -> EditorController.getInstance().countGreen(Integer.parseInt(thresholdTextField.getText())));
    }
}
