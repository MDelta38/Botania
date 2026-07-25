/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.storage.data;

import appeng.api.features.IItemComparison;
import net.minecraft.nbt.NBTTagCompound;

public interface IAETagCompound {
    public NBTTagCompound getNBTTagCompoundCopy();

    public boolean equals(Object var1);

    public IItemComparison getSpecialComparison();
}

