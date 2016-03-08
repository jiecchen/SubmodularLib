package me.xmerge.core.submodularFunctions;

import junit.framework.TestCase;
import me.xmerge.util.UtilFunctions;


import java.util.ArrayList;

/**
 * Test for MaxValue
 */
public class MaxValueTest extends TestCase {
    private ArrayList<Double> V;

    public void setUp() {
        V = UtilFunctions.generateGaussianData(4, 10);
    }

    public void test() {
        MaxValue func = new MaxValue();
        for (Double a : V)
            assertTrue(a == func.marginalGain(a));


    }
}