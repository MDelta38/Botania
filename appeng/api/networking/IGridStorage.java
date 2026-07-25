/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.networking;

import net.minecraft.nbt.NBTTagCompound;

public interface IGridStorage {
    public NBTTagCompound dataObject();

    public long getID();
}

