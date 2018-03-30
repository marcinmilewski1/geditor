package com.geditor.mode.draw.strategy;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.draw.mouse.PointDrawMouseAdapter;

/**
 * Created by marcin on 06.03.16.
 */
public class PointDrawStrategy extends AbstractEditorStrategy implements EditorStrategy {

    public PointDrawStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new PointDrawMouseAdapter(editor);
    }

}
