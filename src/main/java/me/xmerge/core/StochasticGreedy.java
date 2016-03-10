package me.xmerge.core;

import me.xmerge.util.Pair;
import me.xmerge.util.Sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Class for Stochastic Greedy algorithm with lazy evaluation
 */
public class StochasticGreedy {

    public static<T> ArrayList<T> findOptimal(ArrayList<T> V, SubmodularBuffer<T> func, int maxSize, double eps) {
        // to check if an element has been included in the solution
        HashSet<Integer> included = new HashSet<>();
        int nSamples = (int) (V.size() * Math.log(1./eps) / maxSize);
        if (nSamples < 1)
            nSamples = 1;

        // keep the upper bounds
        ArrayList<Double> upperBoundsFixed = new ArrayList<>();
        for (int i = 0; i < V.size(); ++i)
            upperBoundsFixed.add(Double.NEGATIVE_INFINITY);

        int stepCounter = 0;

        while (func.size() < maxSize && included.size() < V.size()) {
            // each loop, one element will be picked

            // filter unmarked elements
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < V.size(); i++)
                if (!included.contains(i))
                    indices.add(i);


            // sample
            indices = Sample.SampleWithoutRplmt(indices, nSamples);

            // build a heap
            PriorityQueue<Pair<Integer, Double>> upperBounds = new PriorityQueue<>();
            for (int idx : indices) {
                upperBounds.add(new Pair<>(idx, upperBoundsFixed.get(idx)));
            }

            // pick the one with largest marginal gain
            Double tmp = Double.MAX_VALUE;
            while (tmp > upperBounds.peek().getValue()) {
                stepCounter++;
                Pair<Integer, Double> p = upperBounds.poll();
                tmp = -func.marginalGain(V.get(p.getKey()));
                // update bound
                upperBoundsFixed.set(p.getKey(), tmp);
                upperBounds.add(new Pair<>(p.getKey(), tmp));
            }

            // the top actually has the largest gain
            Pair<Integer, Double> p = upperBounds.poll();
            // add to solution
            func.addToSolution(V.get(p.getKey()));

            included.add(p.getKey());
        }

        System.out.println("StochGreedy total steps = " + stepCounter);
        return func.getSolution();
    }

    public static<T> ArrayList<T> findOptimal(ArrayList<T> V, SubmodularBuffer<T> func, int maxSize) {
        return findOptimal(V, func, maxSize, 0.1);
    }

}
