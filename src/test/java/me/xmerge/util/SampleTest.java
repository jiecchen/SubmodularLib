package me.xmerge.util;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Tests for sampling
 */
public class SampleTest extends TestCase {
    public void testSampleWithoutRplmt() {
        ArrayList<Integer> V = new ArrayList<>();
        for (int i = 0; i < 100; ++i)
            V.add(i);

        ArrayList<Integer> samples = Sample.SampleWithoutRplmt(V, 10);
        assertTrue(samples.size() == 10);
    }
}