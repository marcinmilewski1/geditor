package com.geditor.ui.render.model;


import com.geditor.ui.render.color.Color3fConstants;

import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class HSVCone extends Shape3D {

    private final TriangleArray cone;

    private final Color3f[] hsvColors = {
            Color3fConstants.red,
            Color3fConstants.yellow,
            Color3fConstants.white,

            Color3fConstants.yellow,
            Color3fConstants.green,
            Color3fConstants.white,

            Color3fConstants.green,
            Color3fConstants.cyan,
            Color3fConstants.white,

            Color3fConstants.cyan,
            Color3fConstants.blue,
            Color3fConstants.white,

            Color3fConstants.blue,
            Color3fConstants.magenta,
            Color3fConstants.white,

            Color3fConstants.magenta,
            Color3fConstants.red,
            Color3fConstants.white,

            Color3fConstants.red,
            Color3fConstants.yellow,
            Color3fConstants.black,

            Color3fConstants.yellow,
            Color3fConstants.green,
            Color3fConstants.black,

            Color3fConstants.green,
            Color3fConstants.cyan,
            Color3fConstants.black,

            Color3fConstants.cyan,
            Color3fConstants.blue,
            Color3fConstants.black,

            Color3fConstants.blue,
            Color3fConstants.magenta,
            Color3fConstants.black,

            Color3fConstants.magenta,
            Color3fConstants.red,
            Color3fConstants.black,


    };
    public HSVCone() {
        Point3f center = new Point3f(0.0f, 1.0f, 0.0f); // white
        Point3f d0 = new Point3f(1.0f, 1.0f, 0.0f); // red
        Point3f d60 = new Point3f(0.5f, 1.0f, 0.86f); // yellow
        Point3f d120 = new Point3f(-0.5f, 1.0f, 0.86f); // green
        Point3f d180 = new Point3f(-1.0f, 1.0f, 0.0f); // cyan
        Point3f d240 = new Point3f(-0.5f, 1.0f, -0.86f); // blue
        Point3f d300 = new Point3f(0.5f, 1.0f, -0.86f); // magenta
        Point3f peak = new Point3f(0.0f, 0.0f, 0.0f); // black

        cone = new TriangleArray(36,
                TriangleArray.COORDINATES | TriangleArray.COLOR_3);
        // base
        cone.setCoordinate(0, d0);
        cone.setCoordinate(1, d60);
        cone.setCoordinate(2, center);

        cone.setCoordinate(3, d60);
        cone.setCoordinate(4, d120);
        cone.setCoordinate(5, center);

        cone.setCoordinate(6, d120);
        cone.setCoordinate(7, d180);
        cone.setCoordinate(8, center);

        cone.setCoordinate(9, d180);
        cone.setCoordinate(10, d240);
        cone.setCoordinate(11, center);

        cone.setCoordinate(12, d240);
        cone.setCoordinate(13, d300);
        cone.setCoordinate(14, center);

        cone.setCoordinate(15, d300);
        cone.setCoordinate(16, d0);
        cone.setCoordinate(17, center);

        // walls
        cone.setCoordinate(18, d0);
        cone.setCoordinate(19, d60);
        cone.setCoordinate(20, peak);

        cone.setCoordinate(21, d60);
        cone.setCoordinate(22, d120);
        cone.setCoordinate(23, peak);

        cone.setCoordinate(24, d120);
        cone.setCoordinate(25, d180);
        cone.setCoordinate(26, peak);

        cone.setCoordinate(27, d180);
        cone.setCoordinate(28, d240);
        cone.setCoordinate(29, peak);

        cone.setCoordinate(30, d240);
        cone.setCoordinate(31, d300);
        cone.setCoordinate(32, peak);

        cone.setCoordinate(33, d300);
        cone.setCoordinate(34, d0);
        cone.setCoordinate(35, peak);

        cone.setColor(0, Color3fConstants.red);
        cone.setColor(1, Color3fConstants.yellow);
        cone.setColor(2, Color3fConstants.white);

        cone.setColor(3, Color3fConstants.yellow);
        cone.setColor(4, Color3fConstants.green);
        cone.setColor(5, Color3fConstants.white);

        cone.setColor(6, Color3fConstants.green);
        cone.setColor(7, Color3fConstants.cyan);
        cone.setColor(8, Color3fConstants.white);

        cone.setColor(9, Color3fConstants.cyan);
        cone.setColor(10, Color3fConstants.blue);
        cone.setColor(11, Color3fConstants.white);

        cone.setColor(12, Color3fConstants.blue);
        cone.setColor(13, Color3fConstants.magenta);
        cone.setColor(14, Color3fConstants.white);

        cone.setColor(15, Color3fConstants.magenta);
        cone.setColor(16, Color3fConstants.red);
        cone.setColor(17, Color3fConstants.white);

        // walls
        cone.setColor(18, Color3fConstants.red);
        cone.setColor(19, Color3fConstants.yellow);
        cone.setColor(20, Color3fConstants.black);

        cone.setColor(21, Color3fConstants.yellow);
        cone.setColor(22, Color3fConstants.green);
        cone.setColor(23, Color3fConstants.black);

        cone.setColor(24, Color3fConstants.green);
        cone.setColor(25, Color3fConstants.cyan);
        cone.setColor(26, Color3fConstants.black);

        cone.setColor(27, Color3fConstants.cyan);
        cone.setColor(28, Color3fConstants.blue);
        cone.setColor(29, Color3fConstants.black);

        cone.setColor(30, Color3fConstants.blue);
        cone.setColor(31, Color3fConstants.magenta);
        cone.setColor(32, Color3fConstants.black);

        cone.setColor(33, Color3fConstants.magenta);
        cone.setColor(34, Color3fConstants.red);
        cone.setColor(35, Color3fConstants.black);

    }

    public TriangleArray getGeometryArray() {
        return cone;
    }
}
