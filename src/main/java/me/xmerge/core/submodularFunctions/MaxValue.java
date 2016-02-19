package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;



/**
 * Demo: f(S) = max S, i.e. the max element in S is submodular
 * Created by cjc on 2/16/16.
 */
public class MaxValue extends SubmodularBuffer<Double> {
    Double max_value;

    public MaxValue() {
        super();
        max_value = Double.NEGATIVE_INFINITY;
    }

    public double marginalGain(Double elem) {
        if (elem > max_value) {
            if (max_value != Double.MIN_VALUE)
                return elem - max_value;
            else
                return elem;
        }
        else
            return 0;
    }


    public void addToSolution(Double elem) {
        if (elem > max_value) {
            max_value = elem;
        }
        S.add(elem);
        currentValue = max_value;
    }

}
