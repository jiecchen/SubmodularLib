package me.xmerge.streaming;

import junit.framework.TestCase;
import me.xmerge.core.Greedy;
import me.xmerge.core.submodularFunctions.KMedoid;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;

/**
 * Tests for Circuit Stream
 */
public class CircuitStreamTest extends TestCase {

    public void test() {
        ArrayList<Double> V = UtilFunctions.generateGaussianData(4, 100);
        KMedoid func = new KMedoid(V);
        KMedoid func1 = new KMedoid(V);
        CircuitStream<Double> cstream = new CircuitStream<>(func, 4);
        for (Double a: V)
            cstream.processItem(a);
        ArrayList<Double> sol = cstream.getOptimalSolution();
        System.out.println(sol);
        ArrayList<Double> sol1 = Greedy.findOptimal(V, func1, 4);
        System.out.println(sol1);
        assertTrue(func.eval(sol) > 0.5 * func.eval(sol1));

    }

}