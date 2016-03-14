package experimental;

import me.xmerge.core.GreedyLazy;
import me.xmerge.core.SubmodularBuffer;
import me.xmerge.core.submodularFunctions.WeightedVertex;
import me.xmerge.distributed.GreeDi;
import me.xmerge.util.DataLoader;
import me.xmerge.util.RawPair;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class TestDistributed {


    public static<T> double runGreeDi(int nMachines, int kk, int k, int C, ArrayList<T> V,
                                    SubmodularBuffer<T> func) {
        GreeDi<T> greeDi = new GreeDi<>(nMachines, C);
        greeDi.shuffleDataset(V);
        greeDi.runGreedy(kk, k, func);
        return func.getCurrentValue();
    }

    public static void main(String args[]) {
        int kk = 20;
        int k = 10;
        int C = 2;
        int nMachines = 5;

        // load dataset
        ArrayList<RawPair<Integer, Integer>> edges = DataLoader.loadEdges("datasets/facebook.txt");
        // Collections.shuffle(edges);

        // construct weighted function
        HashMap<Integer, Double> values = new HashMap<>();
        for (RawPair<Integer, Integer> p : edges) {
            int i = p.getFirst();
            if (values.containsKey(i))
                values.put(i, values.get(i) + 1.);
            else
                values.put(i, 1.0);
            i = p.getSecond();
            if (values.containsKey(i))
                values.put(i, values.get(i) + 1.);
            else
                values.put(i, 1.0);
        }

        // construct submodular function
        WeightedVertex<Integer> WVFunc = new WeightedVertex<>(values);

        {
            WeightedVertex<Integer> funcCopy = (WeightedVertex<Integer>) UtilFunctions.deepClone(WVFunc);
            GreedyLazy.findOptimal(edges, funcCopy, k);
            System.out.println("Offline = " + funcCopy.getCurrentValue());

            ArrayList<Double> results = new ArrayList<>();
            results.add(runGreeDi(nMachines, kk, k, C, edges, WVFunc));
            System.out.println("Distributed = " + results);

        }

    }
}
