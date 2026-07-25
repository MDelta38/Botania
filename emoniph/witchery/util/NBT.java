/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.emoniph.witchery.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBT {
    public static NBTTagCompound get(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p();
    }

    public static NBTTagCompound get(EntityItem entity) {
        return entity.getEntityData();
    }

    public static NBTTagCompound get(EntityLiving entity) {
        return entity.getEntityData();
    }

    public static NBTTagCompound get(EntityPlayer player) {
        NBTTagCompound nbtPlayer = player.getEntityData();
        NBTTagCompound nbtPersistant = nbtPlayer.func_74775_l("PlayerPersisted");
        if (!nbtPlayer.func_74764_b("PlayerPersisted")) {
            nbtPlayer.func_74782_a("PlayerPersisted", (NBTBase)nbtPersistant);
        }
        return nbtPersistant;
    }
}

