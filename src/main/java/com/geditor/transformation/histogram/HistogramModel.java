package com.geditor.transformation.histogram;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class HistogramModel {
    private final int[] redChannel;
    private final int[] greenChannel;
    private final int[] blueChannel;
}
