package me.xmerge.util;

import javax.annotation.Nonnull;

/**
 * Generic key-value Pair
 * Created by cjc on 2/16/16.
 */


public class Pair<T1, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
    private T1 key;
    private T2 value;

    public Pair(T1 k, T2 v) {
        key = k;
        value = v;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }

    @Override
    public int compareTo(@Nonnull Pair<T1, T2> other) {
        return this.value.compareTo(other.getValue());
    }

    @Override
    public String toString() {
        return getKey() + " " + getValue();
    }
}
