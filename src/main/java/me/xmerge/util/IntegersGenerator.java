package me.xmerge.util;

import java.util.ArrayList;

/**
 * generate Intergers
 */
public class IntegersGenerator {
    /**
     *
     * @param l left bound
     * @param r right bound
     * @return integers in [l, r)
     */
    public static ArrayList<Integer> range(int l, int r) {
        assert l < r;
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = l; i < r; ++i)
            toReturn.add(i);
        return toReturn;
    }

    /**
     *
     * @param r right bound
     * @return integers in [0, r)
     */
    public static ArrayList<Integer> range(int r) {
        assert 0 < r;
        return range(0, r);
    }
}
