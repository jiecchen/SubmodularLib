package me.xmerge.core;

import java.util.ArrayList;

/**
 * Interface for stateless Submodular functions
 * a class implements this function should contains no states
 */
public interface SubmodularStateless<T> extends SetFunction<T> {
    /**
     *
     * @param S base set
     * @param elem given element
     * @return marginalGain by adding elem to S
     */
    double marginalGain(ArrayList<T> S, T elem);
}
