package me.xmerge.core;

import me.xmerge.util.Sample;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class for Stochastic Greedy algorithm
 */
public class StochasticGreedy {

    public static<T> ArrayList<T> findOptimal(ArrayList<T> V, SubmodularBuffer<T> func, int maxSize, double eps) {
        // to check if an element has been included in the solution
        HashSet<Integer> included = new HashSet<>();
        int nSamples = (int) (V.size() * Math.log(1./eps) / maxSize);
        if (nSamples < 1)
            nSamples = 1;


        while (func.size() < maxSize && included.size() < V.size()) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < V.size(); i++)
                if (!included.contains(i))
                    indices.add(i);
            indices = Sample.SampleWithoutRplmt(indices, nSamples);
            int m = -1;
            double m_gain = -1e200;
            for (int j : indices) {
                double tmp = func.marginalGain(V.get(j));
                if (m == -1 || tmp > m_gain) {
                    m = j;
                    m_gain = tmp;
                }
            }
            func.addToSolution(V.get(m));
            included.add(m);
        }

        return func.getSolution();
    }

    public static<T> ArrayList<T> findOptimal(ArrayList<T> V, SubmodularBuffer<T> func, int maxSize) {
        return findOptimal(V, func, maxSize, 0.1);
    }

}
