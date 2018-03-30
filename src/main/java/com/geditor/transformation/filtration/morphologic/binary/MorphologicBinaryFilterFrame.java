package com.geditor.transformation.filtration.morphologic.binary;

import com.geditor.ui.controller.EditorController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MorphologicBinaryFilterFrame  extends JFrame{
    private final EditorController editorController = EditorController.getInstance();
    private final JButton dilatation3x3CrossButton = new JButton("Dilatation (3x3 cross mask)");
    private final JButton dilatation5x5CircuralButton = new JButton("Dilatation (5x5 circural mask)");
    private final JButton erosion3x3CrossButton = new JButton("Erosion (3x3 cross mask)");
    private final JButton erosion5x5CircuralButton = new JButton("Erosion (5x5 circural mask)");
    private final JButton opening3x3CrossButton = new JButton("Opening (3x3 cross mask)");
    private final JButton opening5x5CircuralButton = new JButton("Opening (5x5 circural mask)");
    private final JButton closing3x3CrossButton = new JButton("Closing (3x3 cross mask)");
    private final JButton closing5x5CircuralButton = new JButton("Closing (5x5 circural mask)");
    private final JButton thickeningButton = new JButton("Thickening");
    private final JButton thinningButton = new JButton("Thinning");

    public MorphologicBinaryFilterFrame() throws HeadlessException {
        super("Morphologic filters");
        Container container = getContentPane();
        container.setLayout(new MigLayout());
        container.add(dilatation3x3CrossButton);
        container.add(dilatation5x5CircuralButton, "wrap");
        container.add(erosion3x3CrossButton);
        container.add(erosion5x5CircuralButton, "wrap");
        container.add(opening3x3CrossButton);
        container.add(opening5x5CircuralButton, "wrap");
        container.add(closing3x3CrossButton);
        container.add(closing5x5CircuralButton, "wrap");
        container.add(thickeningButton, "wrap");
        container.add(thinningButton, "wrap");
        addListeners();
        setSize(new Dimension(800, 600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addListeners() {
        dilatation3x3CrossButton.addActionListener(e -> editorController.dilatationFilter(MorphologicBinaryMask.cross3x3));
        dilatation5x5CircuralButton.addActionListener(e -> editorController.dilatationFilter(MorphologicBinaryMask.circural5x5));
        erosion3x3CrossButton.addActionListener(e-> editorController.erosionFilter(MorphologicBinaryMask.inverseCross3x3));
        erosion5x5CircuralButton.addActionListener(e -> editorController.erosionFilter(MorphologicBinaryMask.inverseCircural5x5));
        opening3x3CrossButton.addActionListener(e -> editorController.openingFilter(MorphologicBinaryMask.cross3x3, MorphologicBinaryMask.inverseCross3x3));
        opening5x5CircuralButton.addActionListener(e -> editorController.openingFilter(MorphologicBinaryMask.circural5x5, MorphologicBinaryMask.inverseCircural5x5));
        closing3x3CrossButton.addActionListener(e -> editorController.closingFilter(MorphologicBinaryMask.cross3x3, MorphologicBinaryMask.inverseCross3x3));
        closing5x5CircuralButton.addActionListener(e -> editorController.closingFilter(MorphologicBinaryMask.circural5x5, MorphologicBinaryMask.inverseCircural5x5));
        thickeningButton.addActionListener(e -> editorController.thickeningFilter());
        thinningButton.addActionListener(e -> editorController.thinningFilter());
    }
}
