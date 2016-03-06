package me.xmerge.util;



import java.util.ArrayList;
import java.util.Collections;


/**
 * Class to do sampling
 */
public class Sample<T> {
    /**
     *
     * @param V the ground set
     * @param nSamples number of samples
     * @param <T> type in V
     * @return k samples (without replacement) from V, if k > V.size(), return V. Note V will be shuffled
     *
     */
    public static<T> ArrayList<T> SampleWithoutRplmt(ArrayList<T> V, int nSamples) {
        assert nSamples > 0;
        if (nSamples > V.size())
            return V;
        Collections.shuffle(V);
        ArrayList<T> samples = new ArrayList<>();
        for (int i = 0; i < nSamples; ++i)
            samples.add(V.get(i));
        return samples;
    }
}
