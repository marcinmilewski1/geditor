package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawMouseAdapter extends CustomMouseAdapter {
    Point old;

    public PointDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        old = new Point(e.getX(), e.getY());
        Shape shape = new Line2D.Double(old.x, old.y, old.x, old.y);
        drawer.addNonEditable(shape);
        drawer.draw(shape);
        editor.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Shape shape = new Line2D.Double(old.x, old.y, e.getX(), e.getY());
        drawer.addNonEditable(shape);
        drawer.draw(shape);
        editor.repaint();
        old = new Point(e.getX(), e.getY());
    }


}
