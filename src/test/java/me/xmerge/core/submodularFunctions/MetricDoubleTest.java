package me.xmerge.core.submodularFunctions;

import junit.framework.TestCase;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;

/**
 * Test for MetricDouble
 */
public class MetricDoubleTest extends TestCase {

    public void test() {
        ArrayList<Double> V = UtilFunctions.generateGaussianData(1, 10);
        MetricDouble zero = new MetricDouble(0.);
        for (Double a: V) {
            assertTrue(zero.distFrom(new MetricDouble(a)) == Math.abs(a));
        }
    }
}