package com.geditor.io.exporter.writer.ppm.p3;

import com.geditor.io.exporter.writer.ppm.AbstractPpmWriter;
import com.google.common.collect.Lists;

import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marcin on 13.03.16.
 */
public class PpmP3FileWriter extends AbstractPpmWriter{
    private Collection<? extends String> dataLines;

    @Override
    public void write(RenderedImage image, File outputFile) throws IOException {
        Raster raster = image.getData();
        int[] dataTab = getData(raster);

        List<String> lines = Lists.newArrayList(
                getHeaderLine(),
                getWidthAndHeighLine(raster),
                getMaxColorLine(dataTab)
        );
        lines.addAll(getDataLines(dataTab));
        Files.write(Paths.get(outputFile.getAbsolutePath()), lines, Charset.forName("ASCII"));
    }

    @Override
    protected String getHeaderLine() {
        return "P3";
    }

    public List<String> getDataLines(int[] tab) {
        return Arrays.stream(tab).boxed().map(String::valueOf).collect(Collectors.toList());
    }
}
