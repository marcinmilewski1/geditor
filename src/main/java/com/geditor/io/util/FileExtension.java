package com.geditor.io.util;

import java.util.Arrays;

/**
 * Created by marcin on 19.03.16.
 */
public enum FileExtension {
    PPMP3,
    PPMP6,
    JPG, JPEG;

    public static FileExtension valueOfIgnoreCase(String value) {
        return Arrays.stream(values()).filter(fileExtension -> fileExtension.name().equalsIgnoreCase(value)).findAny().orElse(null);
    }

    public static FileExtension valueOfIgnoreCase(javax.swing.filechooser.FileFilter fileFilter) {
        return Arrays.stream(values()).filter(value -> value.name().equalsIgnoreCase(fileFilter.getDescription())).findAny().orElse(null);
    }
}
