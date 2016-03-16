package me.xmerge.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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


    /**
     * This method makes a "deep clone" of any Java object it is given.
     */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param nCenters number of centers of the multi-Gaussian dist
     * @param nRepeats number of points generated from each center
     * @return nCenters * nRepeats points
     */
    public static ArrayList<Double> generateGaussianData(int nCenters, int nRepeats) {
        Random rnd = new Random();
        ArrayList<Double> V = new ArrayList<>();
        for (int i = 0; i < nCenters; ++i)
            for (int j = 0; j < nRepeats; ++j) {
                V.add(10 * i + rnd.nextDouble());
            }

        return V;
    }


    public static<T> ArrayList<T> removeDuplicate(ArrayList<T> arr) {
        HashSet<T> st = new HashSet<>();
        st.addAll(arr);
        ArrayList<T> res = new ArrayList<>();
        res.addAll(st);
        return res;
    }
}
