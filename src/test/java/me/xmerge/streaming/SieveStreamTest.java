package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.Greedy;
import me.xmerge.core.submodularFunctions.KMedoid;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;


/**
 * Tests for SieveStream
 */
public class SieveStreamTest extends TestCase {
    public void test() {
        // create test data
        ArrayList<Double> V = UtilFunctions.generateGaussianData(4, 100);


        KMedoid func1 = new KMedoid(V);

        Greedy.findOptimal(V, func1, 4);
        SieveStream<Double> sieve = new SieveStream<>(0.02, 4, new KMedoid(V));
        for (Double a: V) {
            sieve.processItem(a);
        }



        assertTrue(sieve.getOptimalFunc().getCurrentValue() < 1.3 * func1.getCurrentValue());
        assertTrue(sieve.getOptimalFunc().getCurrentValue() > 0.7 * func1.getCurrentValue());

    }
}