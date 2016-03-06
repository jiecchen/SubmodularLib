package me.xmerge.core;

import junit.framework.TestCase;
import me.xmerge.core.submodularFunctions.KMedoid;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;

/**
 * Test for Lazy Greedy
 */
public class GreedyLazyTest extends TestCase {
    public void test() {
        ArrayList<Double> V = UtilFunctions.generateGaussianData(4, 100);
        KMedoid func1 = new KMedoid(V);
        KMedoid func2 = new KMedoid(V);
        Greedy.findOptimal(V, func1, 4);
        GreedyLazy.findOptimal(V, func2, 4);
        assertTrue(func2.getCurrentValue() > 0.99 * func1.getCurrentValue());

    }
}