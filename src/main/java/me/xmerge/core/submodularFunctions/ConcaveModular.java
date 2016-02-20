package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;

/**
 * ## ConcaveModular
 *
 * + Author: Jiecao Chen
 * + Date: 2/16/16
 *
 * Assume S is a set of positive numbers,
 *       $f(S) = (\sum_{a\in S} a)^{\alpha}$ is a submodular function when $\alpha > 1$
 *
 *
 */
public class ConcaveModular extends SubmodularBuffer<Double> {
    private double alpha;
    double sum;
    public ConcaveModular() {
        super();
        alpha = 2;
        sum = 0;
    }

    public ConcaveModular(double a) {
        super();
        if (a < 1)
            alpha = 1;
        else
            alpha = a;
        sum = 0;
    }

    public void addToSolution(Double elem) {
        sum += elem;
        S.add(elem);
        currentValue = Math.pow(sum, alpha);
    }

    public double marginalGain(Double elem) {

        return Math.pow(sum + elem, alpha) - Math.pow(sum, alpha);
    }

}
