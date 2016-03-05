package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;



import java.util.ArrayList;

/**
 * Class for Circuit Stream Algorithm
 */
public class CircuitStream<T> implements StreamingAlgorithm<T> {

    private int maxSize;
    private ArrayList<T> emptyList;
    private SubmodularBuffer<T> func;

    private ArrayList<T> S;


    /**
     *
     * @param _func submodular function to be optimized
     * @param _maxSize cardinality constraint
     */
    public CircuitStream(SubmodularBuffer<T> _func, int _maxSize) {
        maxSize = _maxSize;
        S = new ArrayList<>();
        func = _func;
        emptyList = new ArrayList<>();
    }

    @Override
    public void processItem(T elem) {
        if (S.size() < maxSize)
            S.add(elem);
        else {

            // find the elem with minimum gain
            double minGain = Double.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < S.size(); ++i) {
                double tmp = func.marginalGain(emptyList, S.get(i));
                if (tmp < minGain) {
                    minGain = tmp;
                    idx = i;
                }
            }

            // update the solution if necessary
            if (func.marginalGain(emptyList, elem) > 2 * minGain) {
                S.set(idx, elem);
            }
        }
    }


    /**
     *
     * @return return the solution
     */
    public ArrayList<T> getOptimalSolution() {
        return S;
    }
}
