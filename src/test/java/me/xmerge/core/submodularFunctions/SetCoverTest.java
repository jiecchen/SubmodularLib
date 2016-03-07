package me.xmerge.core.submodularFunctions;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Test for SetCover function
 */
public class SetCoverTest extends TestCase {
    HashSet<Integer> groundSet;
    ArrayList<HashSet<Integer>> S;
    public void setUp() {
        groundSet = new HashSet<>();
        for (int i = 0; i < 1000; i++)
            groundSet.add(i);

        S = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            HashSet<Integer> tmp = new HashSet<>();
            for (int j = i * 100; j < i * 100 + 600; ++j)
                tmp.add(j);
            S.add(tmp);
        }

    }

    public void test() {
        SetCover<HashSet<Integer>> func = new SetCover<>();
        for (HashSet<Integer> v : S) {
            func.addToSolution(v);
        }

        assertTrue(func.getCurrentValue() == 1000);
    }
}