package experimental;

import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.StochasticGreedy;
import me.xmerge.core.submodularFunctions.SetCover;
import me.xmerge.util.IntegersGenerator;
import me.xmerge.util.SetCoverDataGenerator;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.HashSet;

class RunGreedyAlgorithms {
    public static void Run(ArrayList<HashSet<Integer>> sets, int k, String mode) {
        System.out.println("k = " + k);

        if (mode.equals("Greedy")) {
            SetCover<HashSet<Integer>> setCover1 = new SetCover<>();
            Greedy.findOptimal(sets, setCover1, k);
            System.out.println("Greedy = " + setCover1.getCurrentValue());
        }
        else if (mode.equals("GreedyLazy")) {
            SetCover<HashSet<Integer>> setCover0 = new SetCover<>();
            GreedyLazy.findOptimal(sets, setCover0, k);
            System.out.println("GreedyLazy = " + setCover0.getCurrentValue());
        }
        else {
            SetCover<HashSet<Integer>> setCover2 = new SetCover<>();
            StochasticGreedy.findOptimal(sets, setCover2, k);
            System.out.println("StocGreedy = " + setCover2.getCurrentValue());
        }

    }
}


/**
 * Use this class to test greedy algorithms
 */
public class TestGreedyAlgorithms {



    public static void main(String[] args) {
        // generate test data set
        final ArrayList<HashSet<Integer>> sets;
        SetCoverDataGenerator<Integer> scGenerator = new SetCoverDataGenerator<>();
        int nSets = 1000;
        int nGroundSet = 1000;
        sets = scGenerator.generate(nSets, IntegersGenerator.range(nGroundSet), 100);




        System.out.println("===================Greedy==============================");
        for (int k = 5; k < 50; k += 5) {
            ArrayList<HashSet<Integer>> setsCopy = (ArrayList<HashSet<Integer>>) UtilFunctions.deepClone(sets);
            RunGreedyAlgorithms.Run(setsCopy, k, "Greedy");
        }

        System.out.println("===================GreedyLazy==============================");
        for (int k = 5; k < 50; k += 5) {
            ArrayList<HashSet<Integer>> setsCopy = (ArrayList<HashSet<Integer>>) UtilFunctions.deepClone(sets);
            RunGreedyAlgorithms.Run(setsCopy, k, "GreedyLazy");
        }

        for (int j = 0; j < 5; ++j) {
            System.out.println("===================StocGreedy==============================");
            for (int k = 5; k < 50; k += 5) {
                ArrayList<HashSet<Integer>> setsCopy = (ArrayList<HashSet<Integer>>) UtilFunctions.deepClone(sets);
                RunGreedyAlgorithms.Run(setsCopy, k, "StocGreedy");
            }
        }
    }


}
