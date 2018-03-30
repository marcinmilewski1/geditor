package com.geditor.transformation.d2;

import com.geditor.commons.RectanglePolygon;
import com.geditor.ui.editor.Editor;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class D2EditorPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private static final D2EditorPanel instance = new D2EditorPanel();
    @Getter private static final int X_CENTER = 400;
    @Getter private static final int Y_CENTER = X_CENTER;
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    @Getter private static final int DECIMAL_PART = WIDTH / 10;

    private Graphics2D currentGraphics;
    private Graphics2D imageGraphics;
    @Getter private BufferedImage image;

    @Getter @Setter
    private RectanglePolygon shape;

    private D2EditorPanel() {

        setDoubleBuffered(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    public static D2EditorPanel getInstance() {
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D) g;
        currentGraphics.setStroke(new BasicStroke(3f));

        if (image == null) {
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            imageGraphics = (Graphics2D) image.getGraphics();
            imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            imageGraphics.setBackground(Color.WHITE);
            clearDrawArea();
            drawCoordinateSystem();
            initShape();
        }
        g.drawImage(image, 0, 0, null);

        if (shape != null) {
            currentGraphics.drawPolygon(shape.getPolygon());
        }
    }

    private void initShape() {
        Point a = new Point(X_CENTER - DECIMAL_PART, Y_CENTER - DECIMAL_PART);
        Point b = new Point(X_CENTER + DECIMAL_PART, Y_CENTER - DECIMAL_PART);
        Point c = new Point(X_CENTER + DECIMAL_PART, Y_CENTER + DECIMAL_PART);
        Point d = new Point(X_CENTER -DECIMAL_PART, Y_CENTER + DECIMAL_PART);
        shape = new RectanglePolygon(a, b, c, d);
    }

    private void drawCoordinateSystem() {
        imageGraphics.drawLine(0, Y_CENTER, WIDTH, Y_CENTER); // x axis
        imageGraphics.drawLine(X_CENTER, 0, X_CENTER, HEIGHT); // y axis
        imageGraphics.drawString("0", X_CENTER, Y_CENTER);

        for (int i = X_CENTER + DECIMAL_PART, j = 1; i <= WIDTH; i += DECIMAL_PART, j++) {
            imageGraphics.drawString(String.valueOf(j), i, Y_CENTER);
            setDottedStroke();
            imageGraphics.drawLine(i, 0, i, HEIGHT);
            setBasicStroke();
        }

        for (int i = X_CENTER - DECIMAL_PART, j = -1; i >= 0; i -= DECIMAL_PART, j--) {
            imageGraphics.drawString(String.valueOf(j), i, Y_CENTER);
            setDottedStroke();
            imageGraphics.drawLine(i, 0, i, HEIGHT);
            setBasicStroke();
        }

        for (int i = Y_CENTER - DECIMAL_PART, j = 1; i >= 0; i -= DECIMAL_PART, j++) {
            imageGraphics.drawString(String.valueOf(j), X_CENTER, i);
            setDottedStroke();
            imageGraphics.drawLine(0, i, WIDTH, i);
            setBasicStroke();
        }

        for (int i = Y_CENTER + DECIMAL_PART, j = -1; i <= HEIGHT; i += DECIMAL_PART, j--) {
            imageGraphics.drawString(String.valueOf(j), X_CENTER, i);
            setDottedStroke();
            imageGraphics.drawLine(0, i, WIDTH, i);
            setBasicStroke();
        }


    }

    public void clearDrawArea() {
        imageGraphics.setColor(Color.white);
        imageGraphics.fillRect(0, 0, getSize().width, getSize().height);
        imageGraphics.setColor(Color.black);
    }

    public void reset() {
        initShape();
        repaint();
    }

    private void setDottedStroke() {
        imageGraphics.setStroke(new BasicStroke(
                0.2f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[] {2f},
                0f));
    }

    private void setBasicStroke() {
        imageGraphics.setStroke(new BasicStroke());
    }

}
