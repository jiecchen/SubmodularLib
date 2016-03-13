package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;

import java.util.ArrayList;

/**
 * Baseline uses random sampling
 */
public class BaselineStream<T> implements StreamingAlgorithm<T> {
    ReservoirSampling<T> rSampling;
    SubmodularBuffer<T> func;

    public BaselineStream(SubmodularBuffer<T> _func, int _maxSize) {
        func = _func;
        rSampling = new ReservoirSampling<>(_maxSize);
    }

    @Override
    public void processItem(T elem) {
        rSampling.processItem(elem);
    }

    public ArrayList<T> getSamples() {
        return rSampling.getSamples();
    }

    public double getValue() {
        return func.eval(getSamples());
    }
}
