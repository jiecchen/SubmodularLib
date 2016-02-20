package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.Greedy;
import me.xmerge.core.submodularFunctions.KMedoid;


import java.util.ArrayList;
import java.util.Random;

/**
 * Tests for SieveStreamOPT
 */
public class SieveStreamOPTTest extends TestCase {

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
        double opt = func1.getCurrentValue();
        SieveStreamOPT<Double> knowOpt = new SieveStreamOPT<>(opt, 4, new KMedoid(V));
        for (Double a: V) {
            knowOpt.processItem(a);
        }

        // System.out.println("value = " + knowOpt.getFunc().getCurrentValue() + "\n" + knowOpt.getFunc().getSolution());
        // System.out.println("value = " + func1.getCurrentValue() + "\n" + func1.getSolution());
        assertTrue(knowOpt.getFunc().getCurrentValue() < 2. * func1.getCurrentValue());
        assertTrue(knowOpt.getFunc().getCurrentValue() > 0.5 * func1.getCurrentValue());
    }
}