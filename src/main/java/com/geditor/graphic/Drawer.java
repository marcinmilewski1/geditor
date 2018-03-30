package com.geditor.graphic;

import com.geditor.bezier.BezierCurve;
import com.geditor.commons.Polyline2D;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marcin on 05.03.16.
 * PS6-MarcinMilewski-Zadanie1-Java
 */
@RequiredArgsConstructor
public class Drawer {
    private static final Logger logger = Logger.getLogger(Drawer.class.getName());
    private final Graphics2D graphics;
    private List<Shape> editableFigures = Lists.newArrayList();
    private List<Shape> nonEditableFigures = Lists.newArrayList();

    public void setRenderingHint(RenderingHints.Key keyAntialiasing, Object valueAntialiasOn) {
        graphics.setRenderingHint(keyAntialiasing, valueAntialiasOn);
    }

    public void setColor(Color paint) {
        graphics.setPaint(paint);
    }
    public void setBackgroundColor(Color color) {
        graphics.setBackground(color);
    }

    public void fillRect(int i, int i1, int width, int height) {
        graphics.fillRect(i, i1, width, height);
    }

    public Shape findShape(Rectangle rectangle) {
        List<Shape> shapes = editableFigures.stream().filter(shape -> shape.intersects(rectangle)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(shapes)) {
            return shapes.get(0);
        }
        else {
            return null;
        }
    }

    public void draw(Shape shape) {
        draw(shape, graphics);
    }

    public void draw(Shape shape, Graphics2D g) {
        if (shape != null) {
            if (shape instanceof Polyline2D) {
                logger.debug("Polyline 2d painting");
                drawPolyline((Polyline2D) shape, g);
            } else {
                ((Graphics2D)g).draw(shape);
            }
        }
    }

    private void drawPolyline(Polyline2D polyLine, Graphics2D g) {
        List<Point2D.Float> points = polyLine.getPoints();

        for (int i = 0; i < points.size() -1; i++) {
            Point2D start = points.get(i);
            Point2D end = points.get(i+1);
            g.draw(new Line2D.Double(start.getX(), start.getY(), end.getX(), end.getY()));
//            g.drawLine((int)start.getX(),(int) start.getY(),(int) end.getX(),(int) end.getY());
        }
    }

    public void addEditable(Shape shape) {
        editableFigures.add(shape);
    }

    public void addNonEditable(Shape shape) {
        nonEditableFigures.add(shape);
    }

    public void removeNonEditable(Shape shape) {
        nonEditableFigures.remove(shape);
    }

    public void removeEditable(Shape shape) {
        editableFigures.remove(shape);
    }

    public void redrawAll() {
        for (Shape editableFigure : editableFigures) {
            draw(editableFigure);
        }
        for (Shape nonEditableFigure : nonEditableFigures) {
            draw(nonEditableFigure);
        }
    }

    public void clear() {
        editableFigures.clear();
        nonEditableFigures.clear();
    }
}
