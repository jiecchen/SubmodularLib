package me.xmerge.util;

import java.util.ArrayList;

/**
 * Helper functions
 * Created by cjc on 2/16/16.
 */
public class UtilFunctions {

    public static<T> void printArrayList (ArrayList<T> arr) {
        for (T tmp: arr) {
            System.out.println(tmp);
        }
    }
}
