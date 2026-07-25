/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.TransformCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class EntitySizeInfo {
    public final float defaultWidth;
    public final float defaultHeight;
    public final float eyeHeight;
    public final float stepSize;
    public final boolean isDefault;
    public final TransformCreature creature;

    public EntitySizeInfo(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.creature = ExtendedPlayer.get(player).getCreatureType();
            NBTTagCompound nbtEntity = entity.getEntityData();
            switch (this.creature) {
                default: {
                    this.isDefault = true;
                    this.defaultWidth = 0.6f;
                    this.defaultHeight = 1.8f;
                    this.stepSize = 0.5f;
                    this.eyeHeight = player.getDefaultEyeHeight();
                    break;
                }
                case WOLF: {
                    this.isDefault = false;
                    this.defaultWidth = 0.6f;
                    this.defaultHeight = 0.8f;
                    this.eyeHeight = this.defaultHeight * 0.92f;
                    this.stepSize = 1.0f;
                    break;
                }
                case WOLFMAN: {
                    this.isDefault = true;
                    this.defaultWidth = 0.6f;
                    this.defaultHeight = 1.8f;
                    this.eyeHeight = player.getDefaultEyeHeight();
                    this.stepSize = 1.0f;
                    break;
                }
                case BAT: {
                    this.isDefault = false;
                    this.defaultWidth = 0.3f;
                    this.defaultHeight = 0.6f;
                    this.eyeHeight = this.defaultHeight * 0.8f;
                    this.stepSize = 0.5f;
                    break;
                }
                case TOAD: {
                    this.isDefault = false;
                    this.defaultWidth = 0.3f;
                    this.defaultHeight = 0.5f;
                    this.eyeHeight = this.defaultHeight * 0.92f;
                    this.stepSize = 0.5f;
                    break;
                }
            }
        } else {
            NBTTagCompound nbtEntity = entity.getEntityData();
            this.defaultWidth = nbtEntity.func_74760_g("WITCInitialWidth");
            this.defaultHeight = nbtEntity.func_74760_g("WITCInitialHeight");
            this.stepSize = entity instanceof EntityHorse || entity instanceof EntityEnderman ? 1.0f : 0.5f;
            this.eyeHeight = 0.12f;
            this.isDefault = true;
            this.creature = TransformCreature.NONE;
        }
    }
}

