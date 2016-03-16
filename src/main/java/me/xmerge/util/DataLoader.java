package me.xmerge.util;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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

    /**
     *
     * @param fileName the name of the  file
     * @return sets the sets to be returned
     */
    public static ArrayList<HashSet<String>> loadSets(String fileName) {
        ArrayList<HashSet<String>> sets = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: file not found!");
            return new ArrayList<>();
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            HashSet<String> st = new HashSet<>();
            for (String s : line.split(" ")) {
                st.add(s);
            }
            sets.add(st);
        }

        return sets;
    }
}
