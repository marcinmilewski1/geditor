package com.geditor.mode;

import com.geditor.ui.editor.Editor;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public abstract class AbstractEditorStrategy implements EditorStrategy {
    protected final Editor editor;
    protected MouseAdapter mouseAdapter;
    protected KeyAdapter keyAdapter;

    public AbstractEditorStrategy(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void activate() {
        editor.addMouseListener(mouseAdapter);
        editor.addMouseMotionListener(mouseAdapter);
        editor.addMouseWheelListener(mouseAdapter);
    }

    @Override
    public void deactivate() {
        editor.removeMouseListener(mouseAdapter);
        editor.removeMouseMotionListener(mouseAdapter);
        editor.removeMouseWheelListener(mouseAdapter);
    }
}
