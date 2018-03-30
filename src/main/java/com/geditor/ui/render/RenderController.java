package com.geditor.ui.render;

import com.geditor.ui.render.enums.Figure;

public class RenderController {
    public void render(Figure figure) {
        switch (figure) {
            case CONE: new HSVConeView(); break;
            case CUBE: new RGBCubeView(); break;
            default: throw new IllegalArgumentException();
        }
    }

}
