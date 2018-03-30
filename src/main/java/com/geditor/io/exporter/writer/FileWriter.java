package com.geditor.io.exporter.writer;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by marcin on 13.03.16.
 */
public interface FileWriter {
    void write(RenderedImage image, File outputFile) throws IOException;
}
