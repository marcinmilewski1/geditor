package com.geditor.mode.edit.strategy;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.edit.mouse.LineEditMouseAdapter;

/**
 * Created by marcin on 13.03.16.
 */
public class LineEditStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public LineEditStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new LineEditMouseAdapter(editor);
    }
}
