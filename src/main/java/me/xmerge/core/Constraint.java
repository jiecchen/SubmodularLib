package me.xmerge.core;

import java.util.ArrayList;

/**
 * interface for a constraint
 */
public interface Constraint<T> {
    /**
     *
     * @param S the given set
     * @return true if S satisfies the constraint, false otherwise
     */
    boolean isIn(ArrayList<T> S);
}
