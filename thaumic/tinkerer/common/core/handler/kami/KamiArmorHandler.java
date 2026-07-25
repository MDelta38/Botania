/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package thaumic.tinkerer.common.core.handler.kami;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class KamiArmorHandler {
    private static final String COMPOUND = "ThaumicTinkerer";
    private static final String TAG_STATUS = "GemArmor";

    private static NBTTagCompound getCompoundToSet(EntityPlayer player) {
        NBTTagCompound cmp = player.getEntityData();
        if (!cmp.func_74764_b(COMPOUND)) {
            cmp.func_74782_a(COMPOUND, (NBTBase)new NBTTagCompound());
        }
        return cmp.func_74775_l(COMPOUND);
    }

    public static boolean getArmorStatus(EntityPlayer player) {
        NBTTagCompound cmp = KamiArmorHandler.getCompoundToSet(player);
        return !cmp.func_74764_b(TAG_STATUS) || cmp.func_74767_n(TAG_STATUS);
    }

    public static void setArmorStatus(EntityPlayer player, boolean status) {
        NBTTagCompound cmp = KamiArmorHandler.getCompoundToSet(player);
        cmp.func_74757_a(TAG_STATUS, status);
    }
}

