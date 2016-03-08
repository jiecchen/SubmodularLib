package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.submodularFunctions.MaxValue;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Test for RandomStreamAlpha
 */
public class RandomStreamAlphaTest extends TestCase {
    private ArrayList<Double> dataStream;

    public void setUp() {
        dataStream = UtilFunctions.generateGaussianData(5, 100);
    }

    public void test() {
        MaxValue func = new MaxValue();
        double alpha = Collections.max(dataStream) / 10.;
        RandomStreamAlpha<Double> randomStreamAlpha = new RandomStreamAlpha<>(alpha, 5, func, 0.1);
        for (Double a : dataStream)
            randomStreamAlpha.processItem(a);
        assertTrue(Collections.max(dataStream) * 0.9 <= randomStreamAlpha.getFunc().getCurrentValue());
    }

}