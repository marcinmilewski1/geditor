package com.geditor.bezier;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BezierCurveTest {
    public final Point a = new Point(5, 5);
    public final Point b = new Point(10, 10);
    public final Point c = new Point(15, 15);
    public final Point d = new Point(20, 20);
    public final List<Point> points = Lists.newArrayList(a, b, c, d);

    @Test
    public void creationTest() throws Exception {
        BezierCurve bezierCurve = new BezierCurve(points, 100);
        assertThat(bezierCurve.getCurve().size(), is(100));
    }

}