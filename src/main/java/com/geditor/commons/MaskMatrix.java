package com.geditor.commons;

import com.google.common.collect.Lists;

import java.util.List;

public class MaskMatrix{
    private final List<Float> maskList;

    public MaskMatrix(float[][] mask) {
        maskList = Lists.newArrayList();
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                maskList.add(mask[i][j]);
            }
        }
    }

    public List<Float> getMaskList() {
        return maskList;
    }
}
