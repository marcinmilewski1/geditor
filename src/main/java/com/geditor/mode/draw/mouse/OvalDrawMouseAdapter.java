package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by marcin on 06.03.16.
 */
public class OvalDrawMouseAdapter extends CustomMouseAdapter {
    private Point startPoint;
    private Ellipse2D ellipse2D;

    public OvalDrawMouseAdapter(Editor editor) {
        super(editor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.min(startPoint.x, e.getX());
        int y = Math.min(startPoint.y, e.getY());
        int width = Math.abs(e.getX() - startPoint.x);
        int height = Math.abs(e.getY() - startPoint.y);

        ellipse2D = new Ellipse2D.Double(x, y, width, height);
        editor.setShape(ellipse2D);
        editor.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawer.addEditable(ellipse2D);
        drawer.draw(ellipse2D);
        editor.repaint();

        editor.setShape(null);
    }
}
