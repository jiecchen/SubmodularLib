package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;
import java.util.ArrayList;

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
        minDist = new Double[V.size()];
        for (int i = 0; i < minDist.length; ++i)
            minDist[i] = Double.MAX_VALUE;

    }

    public void addToSolution(Double elem) {
        currentValue = 0;

        for (int i = 0; i < groundSet.size(); ++i) {
            Double dist = Math.abs(groundSet.get(i) - elem);
            if (dist < minDist[i])
                minDist[i] = dist;
            currentValue += minDist[i];
        }
        S.add(elem);

    }

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
