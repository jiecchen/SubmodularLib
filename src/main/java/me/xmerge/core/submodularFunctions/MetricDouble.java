package me.xmerge.core.submodularFunctions;

import me.xmerge.util.MetricItem;

/**
 * A wrapper for double than extends MetricItem
 */
public class MetricDouble implements MetricItem {
    private double data;

    public MetricDouble(double _data) {
        data = _data;
    }

    public double distFrom(MetricItem other) {
        MetricDouble o = (MetricDouble) other;
        return Math.abs(data - o.data);
    }
}
