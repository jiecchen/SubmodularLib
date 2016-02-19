package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;

/**
 * SieveStreaming algorithm while OPT is known in advance
 * Created by cjc on 2/18/16.
 */
public class SieveStreamOPT<T> implements StreamingAlgorithm<T> {
    private double OPT;
    private int maxSize;
    private SubmodularBuffer<T> func;

    public SieveStreamOPT(Double opt, int _maxSize, SubmodularBuffer<T> _func) {
        OPT = opt;
        maxSize = _maxSize;
        func = _func;
    }

    public void processItem(T elem) {
        if (func.size() >= maxSize)
            return;

        if (func.marginalGain(elem) >=
                (OPT / 2. - func.getCurrentValue()) / (maxSize - func.size()) )
            func.addToSolution(elem);
    }


    public SubmodularBuffer<T> getFunc() {
        return func;
    }
}
