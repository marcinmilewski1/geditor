package com.geditor.io.exporter.ppm.p3;

import com.geditor.io.exporter.ppm.AbstractPpmExporter;
import com.geditor.io.exporter.writer.FileWriter;
import com.geditor.io.exporter.writer.ppm.p3.PpmP3FileWriter;

/**
 * Created by marcin on 13.03.16.
 */
public class Ppm3Exporter extends AbstractPpmExporter{

    @Override
    protected FileWriter getFileWriter() {
        return new PpmP3FileWriter();
    }
}
