/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.config.AccessRestriction;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.data.IAEStack;

public interface IMEInventoryHandler<StackType extends IAEStack>
extends IMEInventory<StackType> {
    public AccessRestriction getAccess();

    public boolean isPrioritized(StackType var1);

    public boolean canAccept(StackType var1);

    public int getPriority();

    public int getSlot();

    public boolean validForPass(int var1);
}

