package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.submodularFunctions.MaxValue;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Test for RandomStream
 */
public class RandomStreamTest extends TestCase {
    ArrayList<Double> dataStream;
    public void setUp() {
        dataStream = UtilFunctions.generateGaussianData(5, 100);
    }

    public void test() {
        MaxValue func = new MaxValue();
        RandomStream<Double> randomStream = new RandomStream<>(0.1, 5, func);
        for (Double a : dataStream)
            randomStream.processItem(a);
        System.out.println("real maxVale = " + Collections.max(dataStream));
        System.out.println(randomStream.getOptimalSolution());
    }

}