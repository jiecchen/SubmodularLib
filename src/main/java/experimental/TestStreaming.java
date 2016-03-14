package experimental;


import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.SubmodularBuffer;
import me.xmerge.core.submodularFunctions.RealValueFunction;
import me.xmerge.core.submodularFunctions.WeightedVertex;
import me.xmerge.streaming.BaselineStream;
import me.xmerge.streaming.CircuitStream;
import me.xmerge.streaming.RandomStream;
import me.xmerge.streaming.SieveStream;

import me.xmerge.util.DataLoader;
import me.xmerge.util.RawPair;
import me.xmerge.util.UtilFunctions;


import java.util.*;


/**
 * Class to test streaming algorithm
 */

//class WeightedFunction implements RealValueFunction<Integer> {
//    ArrayList<Double> values;
//
//    public WeightedFunction(ArrayList<Double> _funcValues) {
//        values = _funcValues;
//    }
//
//    @Override
//    public double eval(Integer idx) {
//        assert values.size() > idx;
//        return values.get(idx);
//    }
//}


public class TestStreaming {



    public static double runWeightedVertex(int k, ArrayList<RawPair<Integer, Integer>> edges,
                                         HashMap<Integer, Double> values, String mode) {
        WeightedVertex<Integer> WVFunc = new WeightedVertex<>(values);
        if (mode.equals("SieveStream")) {
            // construct streaming algorithms
            SieveStream<RawPair<Integer, Integer>> sieveStream = new SieveStream<>(0.1, k, WVFunc);
            // process data stream
            for (RawPair<Integer, Integer> edge : edges) {
                sieveStream.processItem(edge);
            }
            double output = sieveStream.getOptimalFunc().getCurrentValue();
            System.out.println("sieveStream = " + output);
            return output;
        }
        else if (mode.equals("RandomStream")) {
            RandomStream<RawPair<Integer, Integer>> randomStream = new RandomStream<>(200, k, WVFunc, 1., 200000.);
            // process data stream
            for (RawPair<Integer, Integer> edge : edges) {
                randomStream.processItem(edge);
            }
            System.out.println("randomStream = " + randomStream.getOptimalValue());
            return randomStream.getOptimalValue();
        }
        else if (mode.equals("CircuitStream")) {
            CircuitStream<RawPair<Integer, Integer>> circuitStream = new CircuitStream<>(WVFunc, k);
            // process data stream
            for (RawPair<Integer, Integer> edge : edges) {
                circuitStream.processItem(edge);
            }
            double output = circuitStream.getOptimalValue();
            System.out.println("circuitStream = " + output);
            return output;
        }
        else if (mode.equals("Baseline")) {
            BaselineStream<RawPair<Integer, Integer>> baselineStream = new BaselineStream<>(WVFunc, k);
            // process data stream
            for (RawPair<Integer, Integer> edge : edges) {
                baselineStream.processItem(edge);
            }
            double output = baselineStream.getValue();
            System.out.println("Baseline = " + output);
            return output;
        }
        else { // offline
            WeightedVertex<Integer> WVFuncCopy = (WeightedVertex<Integer>) UtilFunctions.deepClone(WVFunc);
            Greedy.findOptimal(edges, WVFuncCopy, k);
            System.out.println("Offline = " + WVFuncCopy.getCurrentValue());
            return WVFuncCopy.getCurrentValue();
        }

    }



    public static void runWeightedVertex() {
        // parameters
        int k = 100;


        // load dataset
        ArrayList<RawPair<Integer, Integer>> edges = DataLoader.loadEdges("datasets/facebook.txt");
        // Collections.shuffle(edges);

        // construct weighted function
        HashMap<Integer, Double> values = new HashMap<>();
        for (RawPair<Integer, Integer> p : edges) {
            int i = p.getFirst();
            if (values.containsKey(i))
                values.put(i, values.get(i) + 1.0);
            else
                values.put(i, 1.0);
            i = p.getSecond();
            if (values.containsKey(i))
                values.put(i, values.get(i) + 1.0);
            else
                values.put(i, 1.0);
        }





        // runWeightedVertex(k, edges, values, "SieveStream");
        for (int kk = 150; kk < 500; kk += 50)
            runWeightedVertex(kk, edges, values, "CircuitStream");
        // runWeightedVertex(k, edges, values, "Offline");

//        {
//            ArrayList<Double> vv = new ArrayList<>();
//            for (int i = 0; i < 10; ++i) {
//                vv.add(runWeightedVertex(k, edges, values, "Baseline"));
//            }
//            System.out.println(vv);
//        }
//        {
//            ArrayList<Double> vv = new ArrayList<>();
//            for (int i = 0; i < 10; ++i) {
//                vv.add(runWeightedVertex(k, edges, values, "RandomStream"));
//            }
//            System.out.println(vv);
//        }
    }

    public static void main(String args[]) {
        runWeightedVertex();

    }
}
