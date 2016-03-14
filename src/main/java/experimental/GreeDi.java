package experimental;

import me.xmerge.util.Sample;

import java.util.ArrayList;

/**
 * Algorithm for GreeDi
 */
public class GreeDi<T> {
    int nMachines;
    int C;
    ArrayList<ArrayList<T>> datasets;

    /**
     *
     * @param _nMachines # of machines
     * @param _C each element will be randomly assigned to _C of nMachines
     */
    public GreeDi(int _nMachines, int _C) {
        assert _C <= _nMachines;
        nMachines = _nMachines;
        C = _C;
        datasets = new ArrayList<>();
    }


    public void shuffleDataset(final ArrayList<T> V) {
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < nMachines; ++i)
            idx.add(i);

        for (int i = 0; i < nMachines; ++i)
            datasets.add(new ArrayList<T>());
        for (T v : V) {
            for (int i : Sample.SampleWithoutRplmt(idx, C)) {
                datasets.get(i).add(v);
            }
        }

    }

    public void printDatasets() {
        for (ArrayList<T> arr : datasets) {
            System.out.println(arr);
        }
    }
}
