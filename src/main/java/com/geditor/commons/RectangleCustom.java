package com.geditor.commons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.awt.*;

@RequiredArgsConstructor
public class RectangleCustom {
    @Getter
    private final Point a;
    @Getter private final Point b;
    @Getter private final Point c;
    @Getter private final Point d;

    public boolean contains(Point p) {
        if (p.x >= a.getX() && p.x <= b.getX()
                && p.y >= b.getY() && p.y <= d.getY()) {
            return true;
        } else {
            return false;
        }
    }

}
