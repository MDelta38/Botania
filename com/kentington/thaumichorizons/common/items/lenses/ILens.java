/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.kentington.thaumichorizons.common.items.lenses;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface ILens {
    public String lensName();

    @SideOnly(value=Side.CLIENT)
    public void handleRender(Minecraft var1, float var2);

    public void handleRemoval(EntityPlayer var1);
}

