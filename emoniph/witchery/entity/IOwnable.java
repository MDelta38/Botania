/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.entity;

import net.minecraft.entity.player.EntityPlayer;

public interface IOwnable {
    public String getOwnerName();

    public void setOwner(String var1);

    public EntityPlayer getOwnerEntity();
}

