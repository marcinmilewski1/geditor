package com.geditor.mode.edit.mouse;

import com.geditor.commons.Polyline2D;
import com.geditor.mode.edit.strategy.PolyLineEditStrategy;
import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.edit.strategy.LineEditStrategy;
import com.geditor.mode.edit.strategy.OvalEditStrategy;
import com.geditor.mode.edit.strategy.RectangleEditStrategy;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 12.03.16.
 */
public class FigureEditMouseAdapter extends CustomMouseAdapter {
    private static final Logger logger = Logger.getLogger(FigureEditMouseAdapter.class.getName());
    private Point startPoint;
    public FigureEditMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
        editor.setShape(new Rectangle());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(startPoint.x, e.getX());
        int y = Math.min(startPoint.y, e.getY());
        int width = Math.abs(e.getX() - startPoint.x);
        int height = Math.abs(e.getY() - startPoint.y);

        ((Rectangle) editor.getShape()).setBounds(x, y, width, height);
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle rectangle = ((Rectangle) editor.getShape());
        Shape foundedShape = drawer.findShape(rectangle);
        if (foundedShape != null) {
            editor.setShape(foundedShape);
            if (foundedShape instanceof Line2D) {
                logger.info("Line2D founded " + foundedShape);
                editor.setStrategy(new LineEditStrategy(editor));
            }
            else if (foundedShape instanceof Rectangle) {
                logger.info("Rectangle founded " + foundedShape);
                editor.setStrategy(new RectangleEditStrategy(editor));
            }
            else if (foundedShape instanceof Ellipse2D) {
                logger.info("Ellipse founded " + foundedShape);
                editor.setStrategy(new OvalEditStrategy(editor));
            }
            else if (foundedShape instanceof Polyline2D) {
                logger.info("Polyline founded");
                editor.setStrategy(new PolyLineEditStrategy(editor));
            }
            else {
                logger.info("Invalid shape " + foundedShape);
            }
            editor.repaint();
        }
        else {
            editor.setShape(null);
            editor.repaint();
        }
    }
}
