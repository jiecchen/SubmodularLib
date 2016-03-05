package me.xmerge.core.submodularFunctions;


import junit.framework.TestCase;
import me.xmerge.util.UtilFunctions;


import java.util.ArrayList;

/**
 * Test for IVM
 */
public class IVMTest extends TestCase {

    public void testCreateKeneralMatrix() {
        ArrayList<Double> V = UtilFunctions.generateGaussianData(4, 2);
        ArrayList<MetricDouble> VV = new ArrayList<>();
        for (Double a: V) {
            VV.add(new MetricDouble(a));
        }

        IVM<MetricDouble> ivm = new IVM<>(0.01);
        double pre = Double.NEGATIVE_INFINITY;
        for (MetricDouble a: VV) {
            ivm.addToSolution(a);
            assertTrue(ivm.getCurrentValue() > pre);
            pre = ivm.getCurrentValue();
        }
    }

}