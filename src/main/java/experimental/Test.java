package experimental;

import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.submodularFunctions.ConcaveModular;
import me.xmerge.core.submodularFunctions.KMedoid;
import me.xmerge.util.Pair;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cjc on 2/16/16.
 */
public class Test {
    public static void main(String[] args) {


        ArrayList<Double> V = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 10; ++j) {
            V.add(i * 2 + rnd.nextDouble());
        }

        KMedoid func = new KMedoid(V);


        ArrayList<Double> res = GreedyLazy.findOptimal(V, func, 4);
        System.out.println("V =");
        UtilFunctions.printArrayList(V);
        System.out.println("GreedyLazy =");
        UtilFunctions.printArrayList(res);

        func = new KMedoid(V);
        res = Greedy.findOptimal(V, func, 4);
        System.out.println("Greedy =");
        UtilFunctions.printArrayList(res);
    }
}
