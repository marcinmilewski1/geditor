package com.geditor.io.importer.parser.ppm.p6;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PpmP6Header {
    private int width;
    private int height;
    private int maxColor;
    private int lastIndex;
    private int dataSize;
    private int commentLines;
}
