/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package drunkmafia.thaumicinfusion.common.world;

import net.minecraft.nbt.NBTTagCompound;

public interface ISavable {
    public void writeNBT(NBTTagCompound var1);

    public void readNBT(NBTTagCompound var1);
}

