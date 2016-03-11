package me.xmerge.util;

/**
 * Raw Pair
 */
public class RawPair<T1, T2> {
    private T1 first;
    private T2 second;
    public RawPair(T1 _first, T2 _second) {
        first = _first;
        second = _second;
    }

    public T1 getFirst() {
        return  first;
    }

    public T2 getSecond() {
        return second;
    }
}
