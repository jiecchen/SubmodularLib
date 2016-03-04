package me.xmerge.core;

import java.util.ArrayList;
import java.util.Deque;

/**
 * Interface for set function
 */
public interface SetFunction<T> {
    /**
     *
     * @param A the given set
     * @return the value of f(A)
     */
    double eval(ArrayList<T> A);
}
