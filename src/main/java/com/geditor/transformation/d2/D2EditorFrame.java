package com.geditor.transformation.d2;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class D2EditorFrame extends JFrame {
    private final D2EditorPanel d2EdtiorPanel = D2EditorPanel.getInstance();
    private final D2MenuPanel d2MenuPanel = new D2MenuPanel();

    public D2EditorFrame() throws HeadlessException {
        super("2D Transformation");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(d2EdtiorPanel);
        container.add(d2MenuPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

}

