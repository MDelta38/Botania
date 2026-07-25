/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public interface IChampionModifierEffect {
    public float performEffect(EntityLivingBase var1, EntityLivingBase var2, DamageSource var3, float var4);

    @SideOnly(value=Side.CLIENT)
    public void showFX(EntityLivingBase var1);
}

