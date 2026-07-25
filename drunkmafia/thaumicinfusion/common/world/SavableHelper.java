/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package drunkmafia.thaumicinfusion.common.world;

import drunkmafia.thaumicinfusion.common.world.ISavable;
import net.minecraft.nbt.NBTTagCompound;

public class SavableHelper {
    public static <T> T loadDataFromNBT(NBTTagCompound tag) {
        if (tag == null || !tag.func_74764_b("class")) {
            return null;
        }
        try {
            Class<?> c = Class.forName(tag.func_74779_i("class"));
            if (ISavable.class.isAssignableFrom(c)) {
                ISavable data = (ISavable)c.newInstance();
                data.readNBT(tag);
                return (T)data;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    public static NBTTagCompound saveDataToNBT(ISavable savable) {
        NBTTagCompound tag = new NBTTagCompound();
        if (savable == null) {
            return tag;
        }
        tag.func_74778_a("class", savable.getClass().getCanonicalName());
        savable.writeNBT(tag);
        return tag;
    }
}

