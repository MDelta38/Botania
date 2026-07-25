/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.util;

import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;

public interface IConfigManager {
    public Set<Enum> getSettings();

    public void registerSetting(Enum var1, Enum var2);

    public Enum getSetting(Enum var1);

    public Enum putSetting(Enum var1, Enum var2);

    public void writeToNBT(NBTTagCompound var1);

    public void readFromNBT(NBTTagCompound var1);
}

