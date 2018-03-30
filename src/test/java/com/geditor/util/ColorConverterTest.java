package com.geditor.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ColorConverterTest {
    @Test
    public void rgbBlackToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,0,0,1}, 0.001f);
    }

    @Test
    public void rgbRed1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(1, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,1,1,0.996f}, 0.001f);
    }

    @Test
    public void rgbRed2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(255, 0, 0);
        assertArrayEquals(cmyk, new float[] {0,1,1,0}, 0.001f);
    }

    @Test
    public void rgbGreen1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 7, 0);
        assertArrayEquals(cmyk, new float[] {1,0,1,0.973f}, 0.001f);
    }

    @Test
    public void rgbGreen2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 255, 0);
        assertArrayEquals(cmyk, new float[] {1,0,1,0}, 0.001f);
    }

    @Test
    public void rgbBlue1ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 255);
        assertArrayEquals(cmyk, new float[] {1,1,0,0}, 0.001f);
    }

    @Test
    public void rgbBlue2ToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(0, 0, 9);
        assertArrayEquals(cmyk, new float[] {1,1,0,0.965f}, 0.001f);
    }
    @Test
    public void rgbWhiteToCmyk() throws Exception {
        float[] cmyk = ColorConverter.rgbToCmyk(255, 255, 255);
        assertArrayEquals(cmyk, new float[] {0,0,0,0}, 0.001f);
    }


    @Test
    public void cmykWhiteToRgb() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(0,0,0,0);
        assertArrayEquals(rgb, new int[] {255,255,255});
    }

    @Test
    public void cmykBlackToRgb() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(0,0,0,1);
        assertArrayEquals(rgb, new int[] {0,0,0});
    }

    @Test
    public void cmykRed1ToRgb() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(0,1,1,0.996f);
        assertArrayEquals(rgb, new int[] {1, 0, 0});
    }

    @Test
    public void cmykRed2ToRgb() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(0,1,1,0);
        assertArrayEquals(rgb, new int[] {255, 0, 0});
    }

    @Test
    public void cmykBlue1ToRGB() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(1,1,0,0.965f);
        assertArrayEquals(rgb, new int[] {0, 0, 8});
    }

    @Test
    public void cmykBlue2ToRGB() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(1,1,0,0);
        assertArrayEquals(rgb, new int[] {0, 0, 255});
    }

    @Test
    public void cmykGreen1ToRGB() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(1,0,1,0.973f);
        assertArrayEquals(rgb, new int[] {0, 6, 0});
    }

    @Test
    public void cmykGreen2ToRGB() throws Exception {
        int[] rgb = ColorConverter.cmykToRgb(1,0,1,0);
        assertArrayEquals(rgb, new int[] {0, 255, 0});
    }


}