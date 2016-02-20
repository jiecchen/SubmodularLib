package me.xmerge.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class for a submodular function
 * + Author: Jiecao Chen
 * + Date: 2/16/16
 */
public abstract class SubmodularBuffer<T> implements Serializable {
    protected ArrayList<T> S; // solution buffer
    protected double currentValue; // current function value

    public SubmodularBuffer() {
        S = new ArrayList<>();

        // a class extends this class should
        // also initialize currentValue if necessary
        currentValue = 0;
    }

    /**
     * Add a new elem to the solution set,
     * and update the internal states such as currentValue
     * @param elem the element to be added
     */
    public abstract void addToSolution(T elem);

    /**
     *
     * @return a set as the current solution
     */
    public ArrayList<T> getSolution() {
        return S;
    }

    /**
     *
     * @return size of current solution
     */
    public int size() {
        return S.size();
    }

    /**
     *
     * @return current function value f(S)
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     *
     * @param elem the new element
     * @return marginal gain by adding the new element
     */
    public abstract double marginalGain(T elem);
}
