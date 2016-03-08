package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.UtilFunctions;


import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Random Stream
 */
public class RandomStream<T> implements StreamingAlgorithm<T> {
    private ArrayDeque<RandomStreamAlpha<T>> optValues;
    private final SubmodularBuffer<T> emptyFunc;


    /**
     *
     * @param _eps parameter to control the approximation ratio: (1/2 - _eps) / (2 + _eps) to the optimum
     * @param _emptyFunc an instance of FuncT (Submodular function) with empty buffer
     */
    @SuppressWarnings("unchecked")
    public RandomStream(double _eps, int _maxSize, SubmodularBuffer<T> _emptyFunc) {
        optValues = new ArrayDeque<>();
        emptyFunc = _emptyFunc;
        for (double i = 0.0001; i < 10000.; i *= 1.5) {
            SubmodularBuffer<T> func = (SubmodularBuffer<T>) UtilFunctions.deepClone(emptyFunc);
            RandomStreamAlpha<T> randStreamAlpha = new RandomStreamAlpha<>(i, _maxSize, func, _eps);
            optValues.add(randStreamAlpha);
        }
    }



    @SuppressWarnings("unchecked")
    public void processItem(T elem) {
        for (RandomStreamAlpha<T> ssAlpha: optValues) {
            ssAlpha.processItem(elem);
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

        for (RandomStreamAlpha<T> ssAlpha: optValues) {
            if (ssAlpha.getFunc().getCurrentValue() > maxValue) {
                maxValue = ssAlpha.getFunc().getCurrentValue();
                ans = ssAlpha.getFunc();
            }
        }
        System.out.println(optValues.size());
        System.out.println("maxValue = " + maxValue);

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
