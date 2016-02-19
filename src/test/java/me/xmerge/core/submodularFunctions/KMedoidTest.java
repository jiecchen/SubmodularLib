package me.xmerge.core.submodularFunctions;

import junit.framework.TestCase;
import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;


import java.util.ArrayList;
import java.util.Random;

/**
 * Test for KMedoid
 * Created by cjc on 2/18/16.
 */
public class KMedoidTest extends TestCase {

    public void test() {
        ArrayList<Double> V = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 10; ++j) {
                V.add(i * 10 + rnd.nextDouble());
            }

        KMedoid func1 = new KMedoid(V);
        Greedy.findOptimal(V, func1, 4);
        KMedoid func2 = new KMedoid(V);
        GreedyLazy.findOptimal(V, func2, 4);

        assertTrue(Math.abs(func1.getCurrentValue() - func2.getCurrentValue()) < 0.2 * func1.getCurrentValue());
    }

}



