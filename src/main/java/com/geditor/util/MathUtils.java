package com.geditor.util;

import com.geditor.commons.RectangleCustom;

import java.awt.*;

public class MathUtils {
    public static long newtonBinomial(int n, int k) {
        long res = 1;

        // Since C(n, k) = C(n, n-k)
        if ( k > n - k )
            k = n - k;

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i)
        {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }


    public static double bernsteinPolynomial(int n, int i, float t) {
        final long binomial = newtonBinomial(n, i);
        final double factor = Math.pow((1 - t), (n - i));

        return  (binomial * Math.pow(t, i) * factor);
    }

}
