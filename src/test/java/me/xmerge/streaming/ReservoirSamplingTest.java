package me.xmerge.streaming;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Test for Reservoir Sampling Algorithm
 */
public class ReservoirSamplingTest extends TestCase {
    ArrayList<Integer> dataStream;

    public void setUp() {
        dataStream = new ArrayList<>();
        for (int i = 0; i < 100; ++i)
            dataStream.add(i);
    }


    public void test() {
        ReservoirSampling<Integer> RS = new ReservoirSampling<>(10);
        for (Integer a : dataStream)
            RS.processItem(a);

        System.out.println(RS.getSamples());

    }

}