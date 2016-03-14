package me.xmerge.streaming;

import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random Stream Algorithm,  Alpha is given
 *
 * ## Reference
 * C. Chekuri, S. Gupta, and K. Quanrud. Streaming algorithms for submodular function max-
   imization. arXiv preprint arXiv:1504.08024, 2015.
 */
public class RandomStreamAlpha<T> implements StreamingAlgorithm<T> {
    double alpha;
    SubmodularBuffer<T> func;
    int maxSize;
    ArrayList<T> buffer;
    double eps;
    Random rand;
    SubmodularBuffer<T> emptyFunc;

    public RandomStreamAlpha(double _alpha, int _maxSize, SubmodularBuffer<T> _func, double _eps) {
        alpha = _alpha;
        maxSize = _maxSize;
        func = _func;
        emptyFunc = (SubmodularBuffer<T>) UtilFunctions.deepClone(func);
        eps = _eps;
        rand = new Random();
        buffer = new ArrayList<>();
    }

    public double getAlpha() {
        return alpha;
    }

    @Override
    public void processItem(T elem) {
        if (func.size() < maxSize && func.marginalGain(elem) > alpha) {
            buffer.add(elem);
        }
        if (buffer.size() > maxSize / eps) {
            // randomly sample from buffer
            int i = rand.nextInt(buffer.size());
            func.addToSolution(buffer.get(i));
            // remove those with small marginal gain
            ArrayList<T> tmp = new ArrayList<>();
            for (int j = 0; j < buffer.size(); ++j)
                if (j != i && func.marginalGain(buffer.get(j)) > alpha) {
                    tmp.add(buffer.get(j));
                }
            buffer = tmp;
        }
    }



    public ArrayList<T> getOptimalSolution() {
        SubmodularBuffer<T> tmpFunc = (SubmodularBuffer<T>) UtilFunctions.deepClone(emptyFunc);
        ArrayList<T> SS;
        if (buffer.size() <= maxSize) {
            SS = buffer;
        }
        else {
            SS = Greedy.findOptimal(buffer, tmpFunc, maxSize);
        }
        if (func.getCurrentValue() > tmpFunc.eval(SS)) {
            return func.getSolution();
        }
        else {
            return SS;
        }
    }

    public double getCurrentValue() {
        ArrayList<T> SS = getOptimalSolution();
        return emptyFunc.eval(SS);
    }


}
