package com.geditor.mode.edit.strategy;

import com.geditor.ui.editor.Editor;
import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.edit.mouse.FigureEditMouseAdapter;

/**
 * Created by marcin on 12.03.16.
 */
public class FigureEditStrategy extends AbstractEditorStrategy implements EditorStrategy {
    public FigureEditStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new FigureEditMouseAdapter(editor);
    }

    @Override
    public void activate() {
        super.activate();
        editor.setDottedStroke();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        editor.setSolidStroke();
    }
}
