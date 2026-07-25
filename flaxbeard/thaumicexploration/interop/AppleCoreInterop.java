/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  squeek.applecore.api.AppleCoreAPI
 */
package flaxbeard.thaumicexploration.interop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squeek.applecore.api.AppleCoreAPI;

public class AppleCoreInterop {
    public static int getHeal(ItemStack itemStack) {
        return AppleCoreAPI.accessor.getFoodValues((ItemStack)itemStack).hunger;
    }

    public static float getSaturation(ItemStack itemStack) {
        return AppleCoreAPI.accessor.getFoodValues((ItemStack)itemStack).saturationModifier;
    }

    public static void setHunger(int hunger, EntityPlayer player) {
        AppleCoreAPI.mutator.setHunger(player, player.func_71024_bL().func_75116_a() + hunger);
    }

    public static void setSaturation(float saturation, EntityPlayer player) {
        AppleCoreAPI.mutator.setSaturation(player, player.func_71024_bL().func_75115_e() + saturation);
    }
}

