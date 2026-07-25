/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionSunAllergy
extends PotionBase
implements IHandleLivingUpdate {
    public PotionSunAllergy(int id, int color) {
        super(id, true, color);
        this.setIncurable();
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        float f;
        if (!world.field_72995_K && world.func_72820_D() % 20L == 0L && world.func_72935_r() && (f = entity.func_70013_c(1.0f)) > 0.5f && !entity.func_70090_H() && world.field_73012_v.nextFloat() < f - 0.45f && world.func_72937_j(MathHelper.func_76128_c((double)entity.field_70165_t), MathHelper.func_76128_c((double)entity.field_70163_u), MathHelper.func_76128_c((double)entity.field_70161_v))) {
            boolean burnEntity = true;
            ItemStack itemstack = entity.func_71124_b(4);
            if (itemstack != null && !(entity instanceof EntityPlayer)) {
                if (itemstack.func_77984_f()) {
                    itemstack.func_77964_b(itemstack.func_77952_i() + world.field_73012_v.nextInt(2));
                    if (itemstack.func_77952_i() >= itemstack.func_77958_k()) {
                        entity.func_70669_a(itemstack);
                        entity.func_70062_b(4, (ItemStack)null);
                    }
                }
                burnEntity = false;
            }
            if (burnEntity) {
                if (entity instanceof EntityPlayer) {
                    entity.func_70097_a(DamageSource.field_76380_i, amplifier >= 3 ? 2.0f : 1.0f);
                } else {
                    entity.func_70015_d(8);
                }
            }
        }
    }
}

