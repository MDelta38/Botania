/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage.data;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.data.IAEStack;
import java.util.Collection;

public interface IItemContainer<StackType extends IAEStack> {
    public void add(StackType var1);

    public StackType findPrecise(StackType var1);

    public Collection<StackType> findFuzzy(StackType var1, FuzzyMode var2);

    public boolean isEmpty();
}

