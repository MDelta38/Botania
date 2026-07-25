/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionSinking
extends PotionBase
implements IHandleLivingUpdate {
    public PotionSinking(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void postContructInitialize() {
        this.setPermenant();
        this.setIncurable();
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (world.field_72995_K) {
                if (player.func_70090_H()) {
                    if (player.field_70181_x < -0.03 && !player.field_70122_E) {
                        player.field_70181_x *= 1.5 + Math.min(0.05 * (double)amplifier, 0.2);
                    } else if (!player.field_70122_E && player.func_70055_a(Material.field_151586_h) && player.field_70181_x > 0.0) {
                        // empty if block
                    }
                } else if (!player.field_71075_bZ.field_75098_d && player.field_71075_bZ.field_75101_c && player.field_71075_bZ.field_75100_b) {
                    player.field_70181_x = -0.2f;
                }
            }
        } else if (world.field_72995_K && world.func_82737_E() % 20L == 3L && entity.func_70090_H()) {
            if (entity.field_70181_x < 0.0) {
                entity.field_70181_x *= 1.0 + Math.min(0.1 * (double)(amplifier + 2), 0.4);
            } else if (entity.field_70181_x > 0.0) {
                entity.field_70181_x *= 1.0 - Math.min(0.1 * (double)(amplifier + 2), 0.4);
            }
        }
    }
}

