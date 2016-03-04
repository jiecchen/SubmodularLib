package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Demo: the objective function for k-medoid problem is supermodular, so its negative is submodular.
 *       for the purpose of demonstration, we only consider 1D data.
 * Created by cjc on 2/18/16.
 */


public class KMedoid extends SubmodularBuffer<Double> {

    private ArrayList<Double> groundSet;
    protected Double[] minDist;

    public KMedoid(ArrayList<Double> V) {
        groundSet = V;
        Double minValue = Collections.min(V) - Collections.max(V);
        minDist = new Double[V.size()];
        for (int i = 0; i < minDist.length; ++i)
            minDist[i] = V.get(i) - minValue;

    }


    @Override
    public void addToSolution(Double elem) {

        for (int i = 0; i < groundSet.size(); ++i) {
            Double dist = Math.abs(groundSet.get(i) - elem);
            if (dist < minDist[i]) {
                currentValue += (minDist[i] - dist);
                minDist[i] = dist;
            }
        }
        S.add(elem);
    }


    @Override
    public double eval(ArrayList<Double> A) {
        KMedoid tmp = new KMedoid(groundSet);
        for (Double a: A)
            tmp.addToSolution(a);
        return tmp.getCurrentValue();
    }

    @Override
    public double marginalGain(Double elem) {
        Double tot_gain = 0.;
        for (int i = 0; i < groundSet.size(); ++i) {
            Double dist = Math.abs(groundSet.get(i) - elem);
            if (dist < minDist[i])
                tot_gain += (minDist[i] - dist);
        }

        return tot_gain;
    }

}
