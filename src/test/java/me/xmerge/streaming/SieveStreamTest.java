package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.Greedy;
import me.xmerge.core.submodularFunctions.KMedoid;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tests for SieveStream
 */
public class SieveStreamTest extends TestCase {
    public void test() {
        // create test data
        ArrayList<Double> V = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 100; ++j) {
                V.add(5 * i + rnd.nextDouble());
            }

        KMedoid func1 = new KMedoid(V);

        Greedy.findOptimal(V, func1, 4);
        SieveStream<Double> sieve = new SieveStream<>(0.2, 4, new KMedoid(V));
        for (Double a: V) {
            sieve.processItem(a);
        }


        assertTrue(sieve.getOptimalFunc().getCurrentValue() < 1.3 * func1.getCurrentValue());
        assertTrue(sieve.getOptimalFunc().getCurrentValue() > 0.7 * func1.getCurrentValue());

    }
}