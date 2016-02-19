package me.xmerge;

import me.xmerge.core.Greedy;
import me.xmerge.core.GreedyLazy;
import me.xmerge.core.submodularFunctions.MaxValue;
import me.xmerge.util.UtilFunctions;

import java.util.ArrayList;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Double> V = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 100; ++i) {
            V.add(rnd.nextDouble());
        }

        MaxValue maxf = new MaxValue();
        ArrayList<Double> res = GreedyLazy.findOptimal(V, maxf, 4);

        System.out.println("V =");
        UtilFunctions.printArrayList(V);
        System.out.println("Greedy = " + maxf.getCurrentValue());
        UtilFunctions.printArrayList(res);
        maxf = new MaxValue();
        res = Greedy.findOptimal(V, maxf, 5);
        System.out.println("GreedyLazy = " + maxf.getCurrentValue());
        UtilFunctions.printArrayList(res);

    }
}

