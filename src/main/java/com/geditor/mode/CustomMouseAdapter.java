package com.geditor.mode;

import com.geditor.ui.editor.Editor;
import com.geditor.graphic.Drawer;

import java.awt.event.MouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class CustomMouseAdapter extends MouseAdapter {
    protected final Editor editor;
    protected final Drawer drawer;

    public CustomMouseAdapter(Editor editor) {
        this.editor = editor;
        if (editor != null ){
            drawer = editor.getDrawer();
        }
        else {
            drawer = null;
        }
    }
}
