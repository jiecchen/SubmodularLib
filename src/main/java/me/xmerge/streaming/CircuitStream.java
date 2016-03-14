package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;



import java.util.ArrayList;

/**
 * Class for Circuit Stream Algorithm, given gamma
 */
class CircuitStreamGamma<T> implements StreamingAlgorithm<T> {

    private int maxSize;
    private ArrayList<T> emptyList;
    private SubmodularBuffer<T> func;

    private ArrayList<T> S;
    private double gamma;


    /**
     *
     * @param _func submodular function to be optimized
     * @param _maxSize cardinality constraint
     */
    public CircuitStreamGamma(SubmodularBuffer<T> _func, int _maxSize, double _gamma) {
        maxSize = _maxSize;
        S = new ArrayList<>();
        func = _func;
        emptyList = new ArrayList<>();
        gamma = _gamma;
    }

    @Override
    public void processItem(T elem) {
        if (S.size() < maxSize) {
            S.add(elem);
        }
        else if (S.size() >= maxSize) {

            // find the elem with minimum gain
            double minGain = Double.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < S.size(); ++i) {
                ArrayList<T> singleton = new ArrayList<>();
                singleton.add(S.get(i));
                double tmp = func.eval(singleton);
                if (tmp < minGain) {
                    minGain = tmp;
                    idx = i;
                }
            }
            //System.out.println("i = " + idx);
            // update the solution if necessary
            {
                ArrayList<T> singleton = new ArrayList<>();
                singleton.add(elem);
                double newGain = func.eval(singleton);
                if (newGain >= (1 + gamma) * minGain) {
                  //  System.out.println("newGain = " + newGain + ", oldGain = " + minGain);
                    S.set(idx, elem);
                }
            }
        }
    }


    /**
     *
     * @return return the solution
     */
    public ArrayList<T> getOptimalSolution() {
        return S;
    }

    /**
     *
     * @return return function value
     */
    public double getOptimalValue() {
        return func.eval(S);
    }
}





public class CircuitStream<T> implements StreamingAlgorithm<T> {
    ArrayList<CircuitStreamGamma<T>> algos;

    public CircuitStream(SubmodularBuffer<T> _func, int _maxSize) {
        algos = new ArrayList<>();
        for (double gamma = -0.8; gamma <= 5.; gamma += 0.5) {
            algos.add(new CircuitStreamGamma<>(_func, _maxSize, gamma));
        }
    }

    @Override
    public void processItem(T elem) {
        for (CircuitStreamGamma<T> alg : algos) {
            alg.processItem(elem);
        }
    }

    public ArrayList<T> getOptimalSolution() {
        double maxValue = Double.NEGATIVE_INFINITY;
        ArrayList<T> sol = new ArrayList<>();

        for (CircuitStreamGamma<T> alg : algos) {
            double tmp = alg.getOptimalValue();
            if (maxValue < tmp) {
                maxValue = tmp;
                sol = alg.getOptimalSolution();
            }
        }
        return sol;
    }

    public double getOptimalValue() {
        double maxValue = Double.NEGATIVE_INFINITY;
        for (CircuitStreamGamma<T> alg : algos) {
            double tmp = alg.getOptimalValue();
            if (maxValue < tmp) {
                maxValue = tmp;
            }
        }
        return maxValue;
    }

}