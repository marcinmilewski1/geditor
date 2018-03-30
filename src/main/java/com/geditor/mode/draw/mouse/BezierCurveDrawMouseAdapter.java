package com.geditor.mode.draw.mouse;

import com.geditor.bezier.BezierCurve;
import com.geditor.commons.Polyline2D;
import com.geditor.commons.RectangleCustom;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.ui.editor.Editor;
import com.google.common.base.Joiner;
import lombok.extern.log4j.Log4j;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

@Log4j
public class BezierCurveDrawMouseAdapter extends CustomMouseAdapter {
    private Set<Point> controlPoints;
    private Polyline2D polyLineCurve;
    private boolean isEdition = false;
    private boolean isEditionProgress = false;
    private Point editionPoint;

    public BezierCurveDrawMouseAdapter(Editor editor) {
        super(editor);
        controlPoints = new HashSet<>();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point currentPoint = new Point(e.getX(), e.getY());
        checkIfEdition(currentPoint);
        if (isEdition && !isEditionProgress) {
            isEditionProgress = true;
        } else if (isEdition && isEditionProgress) {
            log.debug("Bezier curve edited");
            isEdition = false;
            isEditionProgress = false;
        }
        else {
            if (polyLineCurve == null) {
                polyLineCurve = new Polyline2D();
                addToControlPoints(new Point(e.getX(), e.getY()));
                log.debug("Add point: x= " + e.getX() + ",y= " + e.getY());
            } else {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    addToControlPoints(new Point(e.getX(), e.getY()));
                    log.debug("Add point: x= " + e.getX() + ",y= " + e.getY());
                    log.debug("Bezier curve painting, c points=" + Joiner.on("\t").join(controlPoints));
                    BezierCurve bezierCurve = new BezierCurve(controlPoints.stream().collect(Collectors.toList()), 100);
                    polyLineCurve = new Polyline2D(bezierCurve.getCurve());
                    editor.setShape(polyLineCurve);
                    editor.setControlPoints(controlPoints);
                    editor.repaint();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    log.debug("Bezier curve saved, c points=" + Joiner.on("\t").join(controlPoints));
                    editor.setShape(polyLineCurve);
                    drawer.addEditable(polyLineCurve);
                    drawer.draw(polyLineCurve);
                    editor.repaint();
                    editor.setShape(null);
                    editor.setControlPoints(null);
                }
            }
        }
        log.debug("C points=" + Joiner.on("\t").join(controlPoints));

    }

    private void addToControlPoints(Point point) {
        controlPoints.add(point);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isEditionProgress) {
            editionPoint.x = e.getX();
            editionPoint.y = e.getY();
            log.debug("New point value: x= " + e.getX() + ",y= " + e.getY());
            log.debug("Bezier curve painting, c points=" + Joiner.on("\t").join(controlPoints));
            BezierCurve bezierCurve = new BezierCurve(controlPoints.stream().collect(Collectors.toList()), 100);
            polyLineCurve = new Polyline2D(bezierCurve.getCurve());
            editor.setShape(polyLineCurve);
            editor.setControlPoints(controlPoints);
            editor.repaint();
        }
    }


    public void checkIfEdition(Point p) {
        RectangleCustom rectangle = new RectangleCustom(new Point(p.x - 10, p.y - 10), new Point(p.x + 10, p.y - 10),
                new Point(p.x - 10, p.y + 10), new Point(p.x + 10, p.y + 10));
        for (Point controlPoint : controlPoints) {
            if (rectangle.contains(controlPoint)) {
                log.debug("Edition started");
                editionPoint = findEditionPoint(new Point(controlPoint.x, controlPoint.y));
                log.debug("Edition point: " + editionPoint);
                isEdition = true;
            }
        }
    }

    private Point findEditionPoint(Point p) {
        for (Point controlPoint : controlPoints) {
            if (p.equals(controlPoint)) return controlPoint;
        }
        return null;
    }

}
