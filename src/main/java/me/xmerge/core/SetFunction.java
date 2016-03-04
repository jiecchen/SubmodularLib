package me.xmerge.core;

import java.util.Deque;

/**
 * Interface for set function
 */
public interface SetFunction<T> {
    double eval(Deque<T> S);
}
