package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.RawPair;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Weighted Vertex Function
 */
public class WeightedVertex<T> extends SubmodularBuffer<RawPair<T, T>> {
    private HashSet<T> vertices;
    private RealValueFunction<T> func;


    public WeightedVertex(RealValueFunction<T> weightFunction) {
        this.currentValue = 0;
        func = weightFunction;
        vertices = new HashSet<>();
    }

    @Override
    public double eval(ArrayList<RawPair<T, T>> A) {
        double tot_weight = 0.;
        for (T v : vertices)
            tot_weight += func.eval(v);
        return tot_weight;
    }

    @Override
    public void addToSolution(RawPair<T, T> edge) {
        int vertexSizeBak = vertices.size();
        vertices.add(edge.getFirst());
        if (vertices.size() > vertexSizeBak) {
            vertexSizeBak++;
            this.currentValue += func.eval(edge.getFirst());
        }
        vertices.add(edge.getSecond());
        if (vertices.size() > vertexSizeBak) {
            this.currentValue += func.eval(edge.getSecond());
        }
        this.S.add(edge);
    }

}
