package com.geditor.io.exporter.writer.jpeg;


import com.geditor.io.exporter.writer.AbstractFileWriter;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class JpegFileWriter extends AbstractFileWriter{
    @Override
    public void write(RenderedImage image, File outputFile) throws IOException {
        ImageIO.write(image, "jpg", outputFile);
    }
}
