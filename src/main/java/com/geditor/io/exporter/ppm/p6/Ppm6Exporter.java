package com.geditor.io.exporter.ppm.p6;

import com.geditor.io.exporter.ppm.AbstractPpmExporter;
import com.geditor.io.exporter.writer.FileWriter;
import com.geditor.io.exporter.writer.ppm.p6.PpmP6FileWriter;

/**
 * Created by marcin on 13.03.16.
 */
public class Ppm6Exporter extends AbstractPpmExporter{

    @Override
    protected FileWriter getFileWriter() {
        return new PpmP6FileWriter();
    }
}
