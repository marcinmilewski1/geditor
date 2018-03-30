package com.geditor.commons;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.awt.*;
import java.util.List;

public class RectanglePolygon {
    @Getter
    private final Point a;
    @Getter
    private final Point b;
    @Getter
    private final Point c;
    @Getter
    private final Point d;
    @Getter
    private final Polygon polygon;

    /**
     * Rectangle
     * a -- b
     * |    |
     * d -- c
     */
    public RectanglePolygon(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        int[] xPoints = new int[4];
        xPoints[0] = a.x;
        xPoints[1] = b.x;
        xPoints[2] = c.x;
        xPoints[3] = d.x;

        int[] yPoints = new int[4];
        yPoints[0] = a.y;
        yPoints[1] = b.y;
        yPoints[2] = c.y;
        yPoints[3] = d.y;
        polygon = new Polygon(xPoints, yPoints, 4);
    }

    public RectanglePolygon(List<Point> points) {
        this(points.get(0), points.get(1), points.get(2), points.get(3));
    }

    public List<Point> getPoints() {
        return Lists.newArrayList(a, b, c, d);
    }
}
