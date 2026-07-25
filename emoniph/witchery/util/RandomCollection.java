/*
 * Decompiled with CFR 0.152.
 */
package com.emoniph.witchery.util;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0.0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, E result) {
        if (weight > 0.0) {
            this.total += weight;
            this.map.put(this.total, result);
        }
    }

    public E next() {
        double value = this.random.nextDouble() * this.total;
        return this.map.ceilingEntry(value).getValue();
    }
}

