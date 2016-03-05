package me.xmerge.core.submodularFunctions;

import Jama.Matrix;
import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.MetricItem;

import java.util.ArrayList;

/**
 * Class for Informative Vector Machine
 */
public class IVM<T extends MetricItem> extends SubmodularBuffer<T> {

    private double sigma;
    private ArrayList<T> S;

    public IVM(double _sigma) {
        sigma = _sigma;
        S = new ArrayList<>();
        currentValue = 0;
    }

    @Override
    public void addToSolution(T elem) {
        S.add(elem);
        currentValue = eval(S);
    }



    @Override
    public double eval(ArrayList<T> A) {
        Matrix mat = createKeneralMatrix(A);
        return Math.log(mat.det());
    }


    public Matrix createKeneralMatrix(ArrayList<T> A) {
        int N = A.size();
        double mat[][] = new double[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) {
                double dist = A.get(i).distFrom(A.get(j));
                double diag = 0;
                if (i == j)
                    diag = 1.;
                mat[i][j] = diag + Math.exp(-dist * dist / 10.) * Math.pow(sigma, 2);
            }

        return new Matrix(mat);
    }
}
