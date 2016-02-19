package me.xmerge.core;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Naive Greedy Algorithm for monotone submodular function maximization under cardinality constraint
 * Reference: Nemhauser, G. L., and Wolsey, L. A. 1978. Best algorithms for approximating
 *            the maximum of a submodular set function. Math. Oper. Research.
 * Created by cjc on 2/16/16.
 */

public class Greedy {
    /**
     * Naive Greedy Algorithm for monotone submodular function maximization under cardinality constraint
     * @param V the ground set
     * @param func a non-decreasing submodular function
     * @param maxSize the cardinality constraint
     * @param <T> type of each element in V
     * @return S, a subset of V that approximately maximizes func(S) s.t. |S| <= maxSize
     */
    public static<T> ArrayList<T> findOptimal(ArrayList<T> V, SubmodularBuffer<T> func, int maxSize) {
        // to check if an element has been included in the solution
        HashSet<Integer> included = new HashSet<>();

        // grow the solution
        while (func.size() < maxSize && included.size() < V.size()) {
            // find the element that maximizes the marginal gain
            int m = -1;
            double m_gain = -1e100;
            for (int j = 0; j < V.size(); ++j) {
                if (!included.contains(j)) {
                    double tmp = func.marginalGain(V.get(j));
                    if (m == -1 || tmp > m_gain) {
                        m = j;
                        m_gain = tmp;
                    }
                }
            }
            func.addToSolution(V.get(m));
            included.add(m);
        }

        return func.getSolution();
    }




}
