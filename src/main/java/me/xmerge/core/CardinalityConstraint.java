package me.xmerge.core;

import me.xmerge.core.Constraint;

import java.util.ArrayList;

/**
 * Class for Cardinality Constraint
 */
public class CardinalityConstraint<T> implements Constraint<T> {
    int k;
    public CardinalityConstraint(int maxSize) {
        k = maxSize;
    }

    @Override
    public boolean isIn(ArrayList<T> S) {
        return S.size() <= k;
    }
}
