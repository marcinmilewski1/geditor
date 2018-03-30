package com.geditor.io.importer.parser.ppm.p6;

import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
public class PpmP6ParserTest {

    private File file;
    private PpmP6Parser ppmP6Parser;
    private PpmP6HeaderParser ppmP6HeaderParser;
    private byte[] fileContent;

    @Before
    public void setUp() throws Exception {
        file = new File(getClass().getResource("/mojAsciiRaw.ppmp6").toURI());
        ppmP6Parser = new PpmP6Parser();
        fileContent = Files.toByteArray(file);
        ppmP6HeaderParser = new PpmP6HeaderParser(fileContent);

    }

    @Test
    public void fileShouldExist() {
        assertTrue(file.exists());
    }

    @Test
    public void parse() throws Exception {
        BufferedImage bufferedImage = ppmP6Parser.parse(file);
        assertNotNull(bufferedImage);
    }

    @Test
    public void whenP6HeaderShouldBeValid() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertThat(ppmP6Header, is(notNullValue()));
    }

    @Test
    public void shouldBeOneComment() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertThat(ppmP6Header.getCommentLines(), is(1));
    }

    @Test
    public void widthShouldBe800AndHeight600() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertThat(ppmP6Header.getWidth(), is(800));
        assertThat(ppmP6Header.getHeight(), is(600));
    }

    @Test
    public void maxColorShouldBe255() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertThat(ppmP6Header.getMaxColor(), is(255));
    }

    @Test
    public void dataSizeShoudlBe3xWidthxHeight() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertThat(ppmP6Header.getDataSize(), is(800 * 600 * 3));
    }

    @Test
    public void indexShouldBeGraterThanZero() throws Exception {
        PpmP6Header ppmP6Header = ppmP6HeaderParser.parseHeader();
        assertTrue(ppmP6Header.getLastIndex() > 0);
    }

}