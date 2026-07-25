/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.common.ExtendedPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderOtherPlayer
extends RenderPlayer {
    protected ResourceLocation func_110775_a(AbstractClientPlayer entity) {
        AbstractClientPlayer player = entity;
        ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)player);
        return playerEx.getOtherPlayerSkinLocation();
    }
}

