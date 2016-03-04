package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;

import java.util.ArrayList;


/**
 * Demo: size function as (sub)modular function
 * Created by cjc on 2/16/16.
 */
public class SizeFunc<T> extends SubmodularBuffer<T> {

    @Override
    public void addToSolution(T elem) {
        S.add(elem);
        currentValue += 1;
    }


    @Override
    public double eval(ArrayList<T> A) {
        return A.size();
    }

    @Override
    public double marginalGain(T elem) {
        return 1;
    }
}
