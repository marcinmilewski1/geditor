package com.geditor.ui.main;

import com.geditor.mode.DefaultStrategy;
import com.geditor.mode.draw.strategy.*;
import com.geditor.mode.edit.strategy.FigureEditStrategy;
import com.geditor.ui.editor.Editor;
import com.geditor.ui.menu.MenuComponent;
import com.geditor.ui.menu.io.FileExportView;
import com.geditor.ui.menu.io.importing.FileImportView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcin on 23.02.16.
 */
public class MainView {
    private JButton clearButton, lineButton, rectangleButton,
            ovalButton, pointButton, editButton
            ,exportButton, importButton, bezierButton;
    private Editor editor = Editor.getInstance();
    private Container content;
    private JPanel root;
    private JPanel editPanel;
    private JMenuBar menuBar;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                editor.clearBuffer();
                editor.clearAll();
                editor.setStrategy(new DefaultStrategy(editor));
            } else if(e.getSource() == pointButton) {
                editor.setStrategy(new PointDrawStrategy(editor));
            } else if (e.getSource() == lineButton) {
                editor.setStrategy(new LineDrawStrategy(editor));
            } else if (e.getSource() == rectangleButton) {
                editor.setStrategy(new RectangleDrawStrategy(editor));
            } else if (e.getSource() == ovalButton) {
                editor.setStrategy(new OvalDrawStrategy(editor));
            } else if (e.getSource() == editButton) {
                editor.setStrategy(new FigureEditStrategy(editor));
            } else if (e.getSource() == exportButton) {
                createSaveFileChooserFrame();
            } else if (e.getSource() == importButton) {
                createOpenFileChooserFrame();
            } else if (e.getSource() == bezierButton) {
                editor.setStrategy(new BezierCurveStrategy(editor));
            }
        }
    };

    private void createSaveFileChooserFrame() {
        JFrame frame = new JFrame("Save");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new FileExportView(editor));

        frame.pack();
        frame.setVisible(true);
    }

    private void createOpenFileChooserFrame() {
        JFrame frame = new JFrame("Open");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new FileImportView(editor));

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainView().show();
        });
    }

    public void show() {
        JFrame frame = new JFrame("Graphic editor");
        frame.setFocusable(true);
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        content.add(editor, BorderLayout.CENTER);

        root = new JPanel();

        frame.setJMenuBar(new MenuComponent());
        createButtons();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        frame.pack();
    }

    private void createButtons() {
        clearButton = new JButton("Clear");
        clearButton.addActionListener(actionListener);
        lineButton = new JButton("Line");
        lineButton.addActionListener(actionListener);
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(actionListener);
        ovalButton = new JButton("Ellipse");
        ovalButton.addActionListener(actionListener);
        pointButton = new JButton("Pencil");
        pointButton.addActionListener(actionListener);
        editButton = new JButton("Edit");
        editButton.addActionListener(actionListener);
        exportButton = new JButton("Export");
        exportButton.addActionListener(actionListener);
        importButton = new JButton("Import");
        importButton.addActionListener(actionListener);
        bezierButton = new JButton("Bezier");
        bezierButton.addActionListener(actionListener);
        root.add(pointButton);
        root.add(ovalButton);
        root.add(rectangleButton);
        root.add(lineButton);
        root.add(clearButton);
        root.add(bezierButton);
        root.add(editButton);
        root.add(importButton);
        root.add(exportButton);


        content.add(root, BorderLayout.NORTH);
    }
}
