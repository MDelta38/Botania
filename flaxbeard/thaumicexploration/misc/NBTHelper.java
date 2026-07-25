/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package flaxbeard.thaumicexploration.misc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    public static NBTTagCompound getItemStackTag(ItemStack stack) {
        NBTTagCompound cmp = stack.func_77978_p();
        if (cmp == null) {
            stack.func_77982_d(new NBTTagCompound());
            cmp = stack.func_77978_p();
        }
        return cmp;
    }
}

