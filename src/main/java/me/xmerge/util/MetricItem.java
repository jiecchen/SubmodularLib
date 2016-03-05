package me.xmerge.util;

/**
 * Interface for items in a metric space
 */
public interface MetricItem {
    /**
     *
     * @param other another item
     * @return distance between this item and the other
     */
    double distFrom(MetricItem other);
}
