package me.xmerge.core.submodularFunctions;

import me.xmerge.core.SubmodularBuffer;
import me.xmerge.util.RawPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Weighted Vertex Function
 */
public class WeightedVertex<T> extends SubmodularBuffer<RawPair<T, T>> {
    private HashSet<T> vertices;
    private HashMap<T, Double> func;


    public WeightedVertex(HashMap<T, Double> weightFunc) {
        this.currentValue = 0;
        func = weightFunc;
        vertices = new HashSet<>();
    }

    @Override
    public double eval(ArrayList<RawPair<T, T>> A) {
        double tot_weight = 0.;
        HashSet<T> tmpVertices = new HashSet<>();
        for (RawPair<T, T> p : A) {
            tmpVertices.add(p.getFirst());
            tmpVertices.add(p.getSecond());
        }

        for (T v : tmpVertices)
            tot_weight += func.get(v);
        return tot_weight;
    }

    @Override
    public void addToSolution(RawPair<T, T> edge) {
        int vertexSizeBak = vertices.size();
        vertices.add(edge.getFirst());
        if (vertices.size() > vertexSizeBak) {
            vertexSizeBak++;
            this.currentValue += func.get(edge.getFirst());
        }
        vertices.add(edge.getSecond());
        if (vertices.size() > vertexSizeBak) {
            this.currentValue += func.get(edge.getSecond());
        }
        this.S.add(edge);
    }

//    @Override
//    public double marginalGain(RawPair<T, T> elem) {
//        double tot_gain = 0.;
//        if (!vertices.contains(elem.getFirst())) {
//            tot_gain += func.get(elem.getSecond());
//        }
//        // assume first != second
//        if (!vertices.contains(elem.getSecond())) {
//            tot_gain += func.get(elem.getSecond());
//        }
//        return tot_gain;
//    }

}
