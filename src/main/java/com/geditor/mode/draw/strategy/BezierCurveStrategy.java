package com.geditor.mode.draw.strategy;

import com.geditor.mode.AbstractEditorStrategy;
import com.geditor.mode.EditorStrategy;
import com.geditor.mode.draw.mouse.BezierCurveDrawMouseAdapter;
import com.geditor.mode.draw.mouse.PolygonDrawMouseAdapter;
import com.geditor.mode.draw.mouse.RectangleDrawMouseAdapter;
import com.geditor.ui.editor.Editor;

public class BezierCurveStrategy extends AbstractEditorStrategy implements EditorStrategy {

    public BezierCurveStrategy(Editor editor) {
        super(editor);
        this.mouseAdapter = new BezierCurveDrawMouseAdapter(editor);
    }
}
