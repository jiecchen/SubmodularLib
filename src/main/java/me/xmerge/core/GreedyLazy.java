package me.xmerge.core;


import me.xmerge.util.Pair;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Lazy Greedy algorithm for monotone submodular maximization under cardinality constraint
 *
 * Reference: Minoux, M. 1978. Accelerated greedy algorithms for maximizing submodular
 *            set functions. Optimization Techniques, LNCS 234–243
 * Created by cjc on 2/16/16.
 */



public class GreedyLazy {
    /**
     * Lazy Greedy Algorithm for monotone submodular function maximization under cardinality constraint
     * @param V the Ground set
     * @param func a non-decreasing submodular function
     * @param maxSize the cardinality constraint
     * @param <T> type of each element in V
     * @return S, a subset of V that approximately maximizes func(S) s.t. |S| <= maxSize
     */
    public static<T> ArrayList<T> findOptimal(final ArrayList<T> V, SubmodularBuffer<T> func, final int maxSize) {
        PriorityQueue<Pair<T, Double>> upperBounds = new PriorityQueue<>(); // keep upper bounds (its negative) for marginal gain

        // initialize upperBounds
        for (T elem: V) {
            upperBounds.add(new Pair<>(elem, Double.NEGATIVE_INFINITY));
        }

        while (func.size() < maxSize && upperBounds.size() > 0) {
            Double tmp = Double.MAX_VALUE;

            while (tmp > upperBounds.peek().getValue()) {
                Pair<T, Double> p = upperBounds.poll();
                tmp = -func.marginalGain(p.getKey());
                upperBounds.add(new Pair<>(p.getKey(), tmp));
            }

            // the top actually has the largest gain
            Pair<T, Double> p = upperBounds.poll();
            // add to solution
            func.addToSolution(p.getKey());
        }

        return func.getSolution();
    }
}
