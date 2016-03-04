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


    ////////////////  abstract methods //////////////////////////////////

    /**
     * Add a new elem to the solution set,
     * and update the internal states such as currentValue
     * @param elem the element to be added
     */
    public abstract void addToSolution(T elem);


    /**
     *
     * @param A the given set
     * @return value of f(A), internal states of "this" is not changed
     */
    public abstract double eval(ArrayList<T> A);

    /**
     *
     * @return current function value f(S)
     */

    ////////////////////////  methods with implementation //////////////////////////////

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



    public double getCurrentValue() {
        return currentValue;
    }

    /**
     *
     * @param BaseSet base set
     * @param elem new element
     * @return return marginal gain of adding elem to BaseSet, this function should not modify any states
     */
    public double marginalGain(ArrayList<T> BaseSet, T elem) {
        double oldV = eval(BaseSet);
        BaseSet.add(elem);
        double newV = eval(BaseSet);
        BaseSet.remove(BaseSet.size() - 1);
        return newV - oldV;
    }

    /**
     *
     * @param elem the new element
     * @return marginal gain by adding the new element
     */
    public double marginalGain(T elem) {
        return marginalGain(S, elem);
    }
}
