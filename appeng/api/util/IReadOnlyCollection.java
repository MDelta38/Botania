/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.util;

public interface IReadOnlyCollection<T>
extends Iterable<T> {
    public int size();

    public boolean isEmpty();

    public boolean contains(Object var1);
}

