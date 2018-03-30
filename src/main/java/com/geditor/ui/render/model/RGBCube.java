package com.geditor.ui.render.model;


/*
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 *
 */

import com.geditor.ui.render.color.Color3fConstants;

import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;

/**
 * Simple color-per-vertex cube with a different color for each face
 */
public class RGBCube extends Shape3D {
    private static final float[] verts = {
            // front face
            1.0f, -1.0f,  1.0f,   // 0. yellow
            1.0f,  1.0f,  1.0f,   // 1. white
            -1.0f,  1.0f,  1.0f,  // 2. magenta
            -1.0f, -1.0f,  1.0f,  // 3. red
            // back face
            -1.0f, -1.0f, -1.0f, // 4. black
            -1.0f,  1.0f, -1.0f, // 5. blue
            1.0f,  1.0f, 255-1.0f, // 6. cyan
            1.0f, -1.0f, -1.0f, // 7. green
            // right face
            1.0f, -1.0f, -1.0f, // 8. green
            1.0f,  1.0f, -1.0f, // 9. cyan
            1.0f,  1.0f,  1.0f, // 10. white
            1.0f, -1.0f,  1.0f, // 11. yellow
            // left face
            -1.0f, -1.0f,  1.0f, // 12. red
            -1.0f,  1.0f,  1.0f, // 13. magenta
            -1.0f,  1.0f, -1.0f, // 14. blue
            -1.0f, -1.0f, -1.0f, // 15. black
            // top face
            1.0f,  1.0f,  1.0f, //16. white
            1.0f,  1.0f, -1.0f, //17. cyan
            -1.0f,  1.0f, -1.0f,//18. blue
            -1.0f,  1.0f,  1.0f, //19. magenta
            // bottom face
            -1.0f, -1.0f,  1.0f, // 20. red
            -1.0f, -1.0f, -1.0f, // 21. black
            1.0f, -1.0f, -1.0f, // 22. green
            1.0f, -1.0f,  1.0f, // 23. yellow
    };

    private static final Color3f[] rgbColors = {
            Color3fConstants.yellow,
            Color3fConstants.white,
            Color3fConstants.magenta,
            Color3fConstants.red,
            Color3fConstants.black,
            Color3fConstants.blue,
            Color3fConstants.cyan,
            Color3fConstants.green,
            Color3fConstants.green,
            Color3fConstants.cyan,
            Color3fConstants.white,
            Color3fConstants.yellow,
            Color3fConstants.red,
            Color3fConstants.magenta,
            Color3fConstants.blue,
            Color3fConstants.black,
            Color3fConstants.white,
            Color3fConstants.cyan,
            Color3fConstants.blue,
            Color3fConstants.magenta,
            Color3fConstants.red,
            Color3fConstants.black,
            Color3fConstants.green,
            Color3fConstants.yellow,

    };

//    private static final float[] rgbColors = {
//            // front face (red)
//            1.0f, 0.0f, 0.0f,
//            1.0f, 0.0f, 0.0f,
//            1.0f, 0.0f, 0.0f,
//            1.0f, 0.0f, 0.0f,
//            // back face (green)
//            0.0f, 1.0f, 0.0f,
//            0.0f, 1.0f, 0.0f,
//            0.0f, 1.0f, 0.0f,
//            0.0f, 1.0f, 0.0f,
//            // right face (blue)
//            0.0f, 0.0f, 1.0f,
//            0.0f, 0.0f, 1.0f,
//            0.0f, 0.0f, 1.0f,
//            0.0f, 0.0f, 1.0f,
//            // left face (yellow)
//            1.0f, 1.0f, 0.0f,
//            1.0f, 1.0f, 0.0f,
//            1.0f, 1.0f, 0.0f,
//            1.0f, 1.0f, 0.0f,
//            // top face (magenta)
//            1.0f, 0.0f, 1.0f,
//            1.0f, 0.0f, 1.0f,
//            1.0f, 0.0f, 1.0f,
//            1.0f, 0.0f, 1.0f,
//            // bottom face (cyan)
//            0.0f, 1.0f, 1.0f,
//            0.0f, 1.0f, 1.0f,
//            0.0f, 1.0f, 1.0f,
//            0.0f, 1.0f, 1.0f,
//    };

    double scale;
    private QuadArray cube;

    /**
     * Constructs a color cube with unit scale.  The corners of the
     * color cube are [-1,-1,-1] and [1,1,1].
     */
    public RGBCube() {
        QuadArray cube = new QuadArray(24, QuadArray.COORDINATES |
                QuadArray.COLOR_3);

        cube.setCoordinates(0, verts);
        cube.setColors(0, rgbColors);

        this.setGeometry(cube);

        scale = 1.0;
    }


    /**
     * Constructs a color cube with the specified scale.  The corners of the
     * color cube are [-scale,-scale,-scale] and [scale,scale,scale].
     * @param scale the scale of the cube
     */
    public RGBCube(double scale, Color3f color3f) {
        cube = new QuadArray(24, QuadArray.COORDINATES |
                QuadArray.COLOR_3 | QuadArray.NORMALS);

        float scaledVerts[] = new float[verts.length];
        for (int i = 0; i < verts.length; i++)
            scaledVerts[i] = verts[i] * (float)scale;

        cube.setCoordinates(0, scaledVerts);
        for (int i = 0; i < 24; i++) {
            cube.setColor(i,color3f);
        }

        this.setGeometry(cube);

        this.scale = scale;
    }

    public RGBCube(double scale) {
        cube = new QuadArray(24, QuadArray.COORDINATES |
                QuadArray.COLOR_3 | QuadArray.NORMALS);

        float scaledVerts[] = new float[verts.length];
        for (int i = 0; i < verts.length; i++)
            scaledVerts[i] = verts[i] * (float)scale;

        cube.setCoordinates(0, scaledVerts);
            cube.setColors(0, rgbColors);

        this.setGeometry(cube);

        this.scale = scale;
    }
    /**
     * @deprecated ColorCube now extends shape so it is no longer necessary
     * to call this method.
     */
    public Shape3D getShape() {
        return this;
    }

    /**
     * Returns the scale of the Cube
     *
     * @since Java 3D 1.2.1
     */
    public double getScale() {
        return scale;
    }

    public QuadArray getGeometryArray() {
        return cube;
    }
}
