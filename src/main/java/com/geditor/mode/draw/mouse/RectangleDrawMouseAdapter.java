package com.geditor.mode.draw.mouse;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.CustomMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marcin on 06.03.16.
 */
public class RectangleDrawMouseAdapter extends CustomMouseAdapter {
    private Point startPoint;

    public RectangleDrawMouseAdapter(Editor editor) {
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
        if (rectangle.width != 0 || rectangle.height != 0) {
            drawer.addEditable(rectangle);
            drawer.draw(rectangle);
            editor.repaint();
        }
    }
}
