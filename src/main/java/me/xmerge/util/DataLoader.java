package me.xmerge.util;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * To Load Data
 */
public class DataLoader {
    /**
     * read unweighted graph
     * @param fileName name of the file to be open
     * @return edges
     */
    public static ArrayList<RawPair<Integer, Integer>> loadEdges(String fileName) {
        ArrayList<RawPair<Integer, Integer>> edges = new ArrayList<>();
        Scanner scanner;
        try {
            System.out.println("Opening file ...");
            scanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: file not found!");
            return new ArrayList<>();
        }

        while (scanner.hasNext()) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new RawPair<>(u, v));
        }

        return edges;
    }
}
