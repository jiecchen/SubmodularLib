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
    public RandomStream(double _eps, int _maxSize, SubmodularBuffer<T> _emptyFunc, double alpha_llimit, double alpha_rlimit) {
        assert alpha_llimit < alpha_rlimit;
        assert alpha_llimit > 0;

        optValues = new ArrayDeque<>();
        emptyFunc = _emptyFunc;
        for (double i = alpha_llimit; i < alpha_rlimit; i *= 1.5) {
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
    public ArrayList<T> getOptimalSolution() {
        // find solution with optimal function value
        Double maxValue = Double.NEGATIVE_INFINITY;
        ArrayList<T> sol = new ArrayList<>();

        for (RandomStreamAlpha<T> ssAlpha: optValues) {
            if (ssAlpha.getCurrentValue() > maxValue) {
                maxValue = ssAlpha.getCurrentValue();
                sol = ssAlpha.getOptimalSolution();
            }
        }

        return sol;
    }

    /**
     *
     * @return the function value
     */
    public double getOptimalValue() {
        return emptyFunc.eval(getOptimalSolution());
    }







}
