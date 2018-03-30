package com.geditor.transformation.d2;

import com.geditor.commons.RectanglePolygon;
import com.geditor.math.Matrix2D;
import com.geditor.math.Vector2D;
import com.geditor.transformation.point.PointTransformations;
import com.geditor.ui.editor.Editor;
import com.google.common.collect.Lists;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D2TransformationController {
    private static final D2TransformationController instance = new D2TransformationController();
    private static final D2EditorPanel d2Editor = D2EditorPanel.getInstance();
    private static final int SCALE = d2Editor.getDECIMAL_PART();
    private static final int CENTER_X = d2Editor.getX_CENTER();
    private static final int CENTER_Y = d2Editor.getY_CENTER();
    private D2TransformationController() {

    };

    public static D2TransformationController getInstance() {
        return instance;
    }

    public void translate(double dx, double dy) {
        final double dxCp = dx * SCALE;
        final double dyCp = - (dy * SCALE);

        RectanglePolygon current = d2Editor.getShape();
        List<Point> points = current.getPoints();

        List<Point> translatedPoints = Lists.newArrayList();
        points.stream().parallel().forEachOrdered(point -> {
            Vector2D pointVector = new Vector2D(point.getX(), point.getY());
            Matrix2D translationMatrix = new Matrix2D();
            translationMatrix.translate(dxCp, dyCp);
            Vector2D translatedPointVector =  translationMatrix.vec_postmultiply(pointVector);
            synchronized (this) {
                translatedPoints.add(new Point((int)translatedPointVector.get_x(), (int)translatedPointVector.get_y()));
            }
        });

        d2Editor.setShape(new RectanglePolygon(translatedPoints));
        d2Editor.repaint();
    }

    public void rotate(double radians, double xPivot, double yPivot) {
        final double radiansCp = radians;
        final double xPivotScaled = CENTER_X + (xPivot * SCALE);
        final double yPivotScaled = CENTER_Y + (yPivot * SCALE);
        RectanglePolygon current = d2Editor.getShape();
        List<Point> points = current.getPoints();

        List<Point> rotatedPoints = Lists.newArrayList();
        points.stream().parallel().forEachOrdered(point -> {
            Vector2D pointVector = new Vector2D(point.getX(), point.getY());
            Matrix2D rotationMatrix = new Matrix2D();
            rotationMatrix.rotate(radiansCp);

            Matrix2D translationToPivotMatrix = new Matrix2D();
            translationToPivotMatrix.translate(-xPivotScaled, -yPivotScaled);
            Vector2D translatedtoPivotVector = translationToPivotMatrix.vec_postmultiply(pointVector);

            Vector2D rotatedPointVector =  rotationMatrix.vec_postmultiply(translatedtoPivotVector);

            Matrix2D translationToLocalMatrix = new Matrix2D();
            translationToLocalMatrix.translate(xPivotScaled, yPivotScaled);
            Vector2D translatedToLocalVector = translationToLocalMatrix.vec_postmultiply(rotatedPointVector);
            synchronized (this) {
                rotatedPoints.add(new Point((int)translatedToLocalVector.get_x(), (int)translatedToLocalVector.get_y()));
            }
        });

        d2Editor.setShape(new RectanglePolygon(rotatedPoints));
        d2Editor.repaint();
    }

    public void reset() {
        d2Editor.reset();
    }

    public void scale(double xScale, double yScale, double xOrigin, double yOrigin) {
        final double kCp = xScale;
        final double xOriginScaled = CENTER_X + (xOrigin * SCALE);
        final double yOriginScaled = CENTER_Y + (yOrigin * SCALE);

        RectanglePolygon current = d2Editor.getShape();
        List<Point> points = current.getPoints();

        List<Point> scaledPoints = Lists.newArrayList();
        points.stream().parallel().forEachOrdered(point -> {
            Vector2D pointVector = new Vector2D(point.getX(), point.getY());
            Matrix2D scaleMatrix = new Matrix2D();
            scaleMatrix.scale(xScale, yScale);

            Matrix2D translationToOriginMatrix = new Matrix2D();
            translationToOriginMatrix.translate(-xOriginScaled, -yOriginScaled);
            Vector2D translatedToOriginVector = translationToOriginMatrix.vec_postmultiply(pointVector);

            Vector2D scaledPointVector =  scaleMatrix.vec_postmultiply(translatedToOriginVector);

            Matrix2D translationToLocalMatrix = new Matrix2D();
            translationToLocalMatrix.translate(xOriginScaled, yOriginScaled);
            Vector2D translatedToLocal = translationToLocalMatrix.vec_postmultiply(scaledPointVector);
            synchronized (this) {
                scaledPoints.add(new Point((int)translatedToLocal.get_x(), (int)translatedToLocal.get_y()));
            }
        });

        d2Editor.setShape(new RectanglePolygon(scaledPoints));
        d2Editor.repaint();

    }
}
