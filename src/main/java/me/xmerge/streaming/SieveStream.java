package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * Streaming algorithm for maximizing a non-negative monotone submodular function
 * under cardinality constraint.
 *
 * + Author: Jiecao Chen
 * + Date: 2/16/16
 *
 * ## Reference
 * + Badanidiyuru, Ashwinkumar, et al. "Streaming submodular maximization: Massive data sum
 *   marization on the fly." KDD'14
 *
 */

public class SieveStream<T> implements StreamingAlgorithm<T>{
    private ArrayDeque<SieveStreamOPT<T>> optValues;
    private final double eps;
    private double m; // largest value of func({elem})
    private final SubmodularBuffer<T> emptyFunc;
    private final int maxSize;
    /**
     *
     * @param _eps parameter to control the approximation ratio: (1/2 - _eps) to the optimum
     * @param _emptyFunc an instance of FuncT (Submodular function) with empty buffer
     */
    public SieveStream(double _eps, int _maxSize, SubmodularBuffer<T> _emptyFunc) {
        eps = _eps;
        optValues = new ArrayDeque<>();
        m = 0.;
        emptyFunc = _emptyFunc;
        maxSize = _maxSize;
    }



    @SuppressWarnings("unchecked")
    public void processItem(T elem) {
        double mGain = emptyFunc.marginalGain(elem);
        if (mGain > m)
            m = mGain;
        // remove those have OPT < m from optValues
        while (optValues.size() > 0 && optValues.getFirst().getOPT() < m) {
            optValues.removeFirst();
        }

        // add new SieveStreamOPT instance
        while (optValues.isEmpty() || optValues.getLast().getOPT() * (1 + eps) <= 2 * maxSize * m) {
            // create a new SieveStreamOPT instance
            double newOpt;
            if (optValues.isEmpty())
                newOpt = m;
            else
                newOpt = optValues.getLast().getOPT() * (1 + eps);
            SubmodularBuffer<T> newFunc = (SubmodularBuffer<T>) UtilFunctions.deepClone(emptyFunc);
            SieveStreamOPT<T> knowOpt = new SieveStreamOPT<>(newOpt, maxSize, newFunc);
            // add it to optValues
            optValues.add(knowOpt);

            // now update each instance in optValues
            for (SieveStreamOPT<T> ssOpt: optValues) {
                ssOpt.processItem(elem);
            }
        }
    }

    /**
     *
     * @return func with max function value
     */
    public SubmodularBuffer<T> getOptimalFunc() {
        // find solution with optimal function value
        Double maxValue = Double.NEGATIVE_INFINITY;
        SubmodularBuffer<T> ans = emptyFunc;

        for (SieveStreamOPT<T> ssOpt: optValues) {
            if (ssOpt.getFunc().getCurrentValue() > maxValue) {
                maxValue = ssOpt.getFunc().getCurrentValue();
                ans = ssOpt.getFunc();
            }
        }

        return ans;
    }

    /**
     *
     * @return the best possible solution of the constrained submodular maximization problem
     */
    public ArrayList<T> getOptimalSolution() {
        return getOptimalFunc().getSolution();
    }
}
