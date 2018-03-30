package com.geditor.mode.edit.mouse;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;
import com.geditor.mode.edit.strategy.FigureEditStrategy;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by marcin on 13.03.16.
 */
public class OvalEditMouseAdapter extends CustomMouseAdapter{
    private static final Logger logger = Logger.getLogger(RectangleEditMouseAdapter.class.getName());
    private Point startPoint;
    private Ellipse2D editShape;

    public OvalEditMouseAdapter(Editor editor) {
        super(editor);
        editShape = (Ellipse2D) editor.getShape();
        startPoint = new Point((int) editShape.getX(), (int) editShape.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        logger.info("Edit started");
        drawer.removeEditable(editShape);
        editor.redrawAll();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(startPoint.x, e.getX());
        int y = Math.min(startPoint.y, e.getY());
        int width = Math.abs(e.getX() - startPoint.x);
        int height = Math.abs(e.getY() - startPoint.y);

        editShape = new Ellipse2D.Double(x, y, width, height);
        editor.setShape(editShape);
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        logger.info("Edit finished");
        drawer.addEditable(editShape);
        editor.setShape(null);
        editor.redrawAll();
        editor.setStrategy(new FigureEditStrategy(editor));
    }

}
