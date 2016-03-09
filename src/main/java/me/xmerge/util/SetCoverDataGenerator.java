package me.xmerge.util;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Data generator for SetCover problem
 */
public class SetCoverDataGenerator<T> {
    /**
     *
     * @param nSets number of sets
     * @param groundSet the size of the ground set
     * @return a list of sets
     */
    public ArrayList<HashSet<T>> generate(int nSets, ArrayList<T> groundSet) {
        assert groundSet.size() != 0;

        ArrayList<HashSet<T>> toReturn = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < nSets; ++i) {
            HashSet<T> tmp = new HashSet<>();
            // create interval [l, r]
            int r = rand.nextInt(groundSet.size());
            if (r < 1)
                ++r;
            int l = rand.nextInt(r);
            // add intervel to tmp
            for (int j = l; j <= r; ++j)
                tmp.add(groundSet.get(j));

            // add tmp to toReturn
            toReturn.add(tmp);
        }

        return toReturn;
    }
}
