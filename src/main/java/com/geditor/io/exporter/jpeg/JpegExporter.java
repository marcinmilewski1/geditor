package com.geditor.io.exporter.jpeg;

import com.geditor.io.exporter.AbstractFileExporter;
import com.geditor.io.exporter.writer.FileWriter;
import com.geditor.io.exporter.writer.jpeg.JpegFileWriter;

public class JpegExporter extends AbstractFileExporter {
    @Override
    protected FileWriter getFileWriter() {
        return new JpegFileWriter();
    }
}
