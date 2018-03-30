package com.geditor.mode.draw.strategy;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.RectangleDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class RectangleDrawStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public RectangleDrawStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new RectangleDrawMouseAdapter(editor);
    }
}
