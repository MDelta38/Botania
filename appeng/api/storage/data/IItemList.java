/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage.data;

import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemContainer;
import java.util.Iterator;

public interface IItemList<StackType extends IAEStack>
extends IItemContainer<StackType>,
Iterable<StackType> {
    public void addStorage(StackType var1);

    public void addCrafting(StackType var1);

    public void addRequestable(StackType var1);

    public StackType getFirstItem();

    public int size();

    @Override
    public Iterator<StackType> iterator();

    public void resetStatus();
}

