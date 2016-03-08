package me.xmerge.streaming;

import me.xmerge.core.SubmodularBuffer;

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

    public RandomStreamAlpha(double _alpha, int _maxSize, SubmodularBuffer<T> _func, double _eps) {
        alpha = _alpha;
        maxSize = _maxSize;
        func = _func;
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

    public SubmodularBuffer<T> getFunc() {
        return func;
    }


}
