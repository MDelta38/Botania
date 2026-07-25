/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.networking.crafting;

import net.minecraft.nbt.NBTTagCompound;

public interface ICraftingLink {
    public boolean isCanceled();

    public boolean isDone();

    public void cancel();

    public boolean isStandalone();

    public void writeToNBT(NBTTagCompound var1);

    public String getCraftingID();
}

