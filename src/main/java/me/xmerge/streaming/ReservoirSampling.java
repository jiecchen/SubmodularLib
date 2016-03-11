package me.xmerge.streaming;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random sampling from a data stream using Reservior Sampling
 */

public class ReservoirSampling<T> implements StreamingAlgorithm<T> {
    private ArrayList<T> samples;
    private int maxSize;
    Random rand;
    int streamLength;

    /**
     *
     * @param _maxSize max size to sample
     */
    public ReservoirSampling(int _maxSize) {
        assert _maxSize > 0;
        maxSize = _maxSize;
        samples = new ArrayList<>();
        rand = new Random();
        streamLength = 0;
    }


    @Override
    public void processItem(T elem) {
        streamLength++;
        if (samples.size() < maxSize) {
            samples.add(elem);
        }
        else {
            int idx = rand.nextInt(streamLength);
            if (idx < samples.size())
                samples.set(idx, elem);
        }
    }

    public ArrayList<T> getSamples() {
        return samples;
    }
}
