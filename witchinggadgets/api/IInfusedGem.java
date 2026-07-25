/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public interface IInfusedGem {
    public boolean performEffect(String var1, Aspect var2, int var3, int var4, EntityPlayer var5);

    public int getConsumedCharge(String var1, Aspect var2, EntityPlayer var3);

    public boolean isGemEnchantable(ItemStack var1);
}

