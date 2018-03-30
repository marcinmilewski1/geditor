package com.geditor.ui.color;

import com.geditor.util.ColorConverter;

public class ColorConverterController {

    private ColorConverterCMYKSliderPanel cmykSliderPanel;
    private ColorConverterRGBSliderPanel rgbSliderPanel;

    public ColorConverterController(ColorConverterCMYKSliderPanel cmykSliderPanel, ColorConverterRGBSliderPanel rgbSliderPanel) {
        this.cmykSliderPanel = cmykSliderPanel;
        this.rgbSliderPanel = rgbSliderPanel;
    }

    public RGBStructure convertCMYKtoRGB(CMYKStructure cmykStructure) {
        int[] rgb = ColorConverter.cmykToRgb(cmykStructure.c, cmykStructure.m, cmykStructure.y, cmykStructure.k);
        RGBStructure rgbStructure = new RGBStructure(rgb[0], rgb[1], rgb[2]);
        rgbSliderPanel.setValues(rgbStructure);
        return rgbStructure;
    }

    public CMYKStructure convertRGBtoCMYK(RGBStructure rgbStructure) {
        float[] cmyk = ColorConverter.rgbToCmyk(rgbStructure.r, rgbStructure.g, rgbStructure.b);
        CMYKStructure cmykStructure = new CMYKStructure(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
        cmykSliderPanel.setValues(cmykStructure);
        return cmykStructure;
    }
}
