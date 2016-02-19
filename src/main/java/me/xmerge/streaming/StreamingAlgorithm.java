package me.xmerge.streaming;

/**
 * Interface for streaming algorithm
 * Created by cjc on 2/18/16.
 */
public interface StreamingAlgorithm<T> {
    void processItem(T elem);
}
