package me.xmerge.streaming;

/**
 * Interface for streaming algorithm
 * + Author: Jiecao Chen
 * + Date: 2/18/16.
 */
public interface StreamingAlgorithm<T> {
    void processItem(T elem);
}
