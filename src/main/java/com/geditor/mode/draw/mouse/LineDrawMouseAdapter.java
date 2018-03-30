package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Created by marcin on 06.03.16.
 */
public class LineDrawMouseAdapter extends CustomMouseAdapter {
    private Point startPoint;

    public LineDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
            startPoint = new Point(e.getX(), e.getY());
            editor.setShape(new Line2D.Double());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ((Line2D) editor.getShape()).setLine(startPoint, new Point(e.getX(), e.getY()));
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawer.addEditable(editor.getShape());
        drawer.draw(editor.getShape());
        editor.repaint();
        editor.setShape(null);
    }
}
