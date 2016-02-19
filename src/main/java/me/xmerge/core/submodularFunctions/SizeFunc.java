package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;


/**
 * Demo: size function as (sub)modular function
 * Created by cjc on 2/16/16.
 */
public class SizeFunc<T> extends SubmodularBuffer<T> {

    public void addToSolution(T elem) {
        S.add(elem);
        currentValue += 1;
    }


    public double marginalGain(T elem) {
        return 1;
    }
}
