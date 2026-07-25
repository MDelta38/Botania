/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;

public interface IHandlePreRenderLiving {
    public PotionBase getPotion();

    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World var1, EntityLivingBase var2, RenderLivingEvent.Pre var3, int var4);
}

