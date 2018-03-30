package com.geditor.ui.editor;

/**
 * Created by marcin on 23.02.16.
 */

import com.geditor.graphic.Drawer;
import com.geditor.transformation.histogram.HistogramModel;
import com.geditor.mode.EditorStrategy;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

public class Editor extends JPanel{
    private static final Logger logger = Logger.getLogger(Editor.class.getName());
    private static final Editor instance = new Editor();
    @Getter private BufferedImage image;
    @Getter @Setter private BufferedImage imageCopy;
    @Getter @Setter private Drawer drawer;
    private EditorStrategy strategy;
    private Shape shape;
    private Graphics2D currentGraphics;
    private Stroke stroke = new BasicStroke();
    @Getter @Setter private int brightness = 0;
    @Getter @Setter private int redShift = 0;
    @Getter @Setter private int greenShift = 0;
    @Getter @Setter private int blueShift = 0;
    @Getter @Setter private HistogramModel histogramModel;
    @Getter @Setter private Set<Point> controlPoints;

    private Editor() {
        setDoubleBuffered(false);
    }

    public static Editor getInstance() {
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D) g;
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            drawer = new Drawer((Graphics2D) image.getGraphics());
            drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawer.setBackgroundColor(Color.WHITE);
            clearDrawArea();
        }

        g.drawImage(image, 0, 0, null);
        currentGraphics.setStroke(stroke);
            if (shape != null)
            {
                drawer.draw(shape, (Graphics2D) g);
                drawControlPoints((Graphics2D) g);
            }
    }

    private void drawControlPoints(Graphics2D g) {
        if (controlPoints != null) {
            for (Point controlPoint : controlPoints) {
                g.setStroke(new BasicStroke(10));
                g.drawLine(controlPoint.x, controlPoint.y, controlPoint.x, controlPoint.y);
            }
        }
    }


    public void clearDrawArea() {
        drawer.setColor(Color.white);
        drawer.fillRect(0, 0, getSize().width, getSize().height);
        drawer.setColor(Color.black);
        shape = null;
        controlPoints = null;
        repaint();
    }

    public void clearBuffer() {
        drawer.clear();
    }

    public void clearAll() {
        clearBuffer();
        clearDrawArea();
        shape = null;
        controlPoints = null;
    }

    public void setColor(Color color) {
        drawer.setColor(color);
    }

    public void setStrategy(EditorStrategy strategy) {
        deactivateStrategy();
        this.strategy = strategy;
        strategy.activate();
        logger.info("strategy: " + strategy);
    }

    private void deactivateStrategy() {
        if (strategy != null) {
            logger.info("current strategy: deactivated.");
            strategy.deactivate();
        }
        setSolidStroke();
    }

    public void setDottedStroke() {
        stroke = new BasicStroke(
                1f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[] {2f},
                0f);
    }

    public void setImage(BufferedImage image) {
        clearDrawArea();
        this.image = image;
        drawer = new Drawer((Graphics2D) image.getGraphics());
        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawer.setColor(Color.black);
        repaint();
    }

    public void setSolidStroke() {
        stroke = new BasicStroke();
    }

    public synchronized Shape getShape() {
        return shape;
    }

    public synchronized void setShape(Shape shape) {
        this.shape = shape;
    }

    public void redrawAll() {
        clearDrawArea();
        drawer.redrawAll();
        repaint();
    }


}