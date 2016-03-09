package experimental;

import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.StochasticGreedy;
import me.xmerge.core.submodularFunctions.SetCover;
import me.xmerge.util.IntegersGenerator;
import me.xmerge.util.SetCoverDataGenerator;

import java.util.ArrayList;
import java.util.HashSet;

class RunGreedyAlgorithms {
    public static void Run(ArrayList<HashSet<Integer>> sets, int k) {
        System.out.println("k = " + k);

        // create functions
        SetCover<HashSet<Integer>> setCover0 = new SetCover<>();
        SetCover<HashSet<Integer>> setCover1 = new SetCover<>();
        SetCover<HashSet<Integer>> setCover2 = new SetCover<>();


        // run algorithms
        GreedyLazy.findOptimal(sets, setCover0, k);
        Greedy.findOptimal(sets, setCover1, k);
        StochasticGreedy.findOptimal(sets, setCover2, k);

        // output results
        System.out.println("GreedyLazy = " + setCover0.getCurrentValue());
        System.out.println("Greedy = " + setCover1.getCurrentValue());
        System.out.println("StocGreedy = " + setCover2.getCurrentValue());

    }
}


/**
 * Use this class to test greedy algorithms
 */
public class TestGreedyAlgorithms {



    public static void main(String[] args) {
        // generate test data set
        ArrayList<HashSet<Integer>> sets;
        SetCoverDataGenerator<Integer> scGenerator = new SetCoverDataGenerator<>();
        int nSets = 500;
        int nGroundSet = 1000;
        sets = scGenerator.generate(nSets, IntegersGenerator.range(nGroundSet));

        for (int k = 5; k < 20; k += 5) {
            RunGreedyAlgorithms.Run(sets, k);
        }

    }


}
