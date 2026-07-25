/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.infusion.Infusion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionAbsorbMagic
extends PotionBase
implements IHandleLivingHurt {
    public PotionAbsorbMagic(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        if (!world.field_72995_K && event.source.func_82725_o()) {
            EntityPlayer player;
            int maxEnergy;
            float damageAbsorbed = event.ammount * 0.2f * (float)(amplifier + 1);
            event.ammount -= damageAbsorbed;
            if (entity instanceof EntityPlayer && (maxEnergy = Infusion.getMaxEnergy(player = (EntityPlayer)entity)) > 0 && damageAbsorbed > 1.0f) {
                int energy = Infusion.getCurrentEnergy(player);
                Infusion.setCurrentEnergy(player, Math.min(energy + (int)damageAbsorbed, maxEnergy));
            }
        }
    }

    @Override
    public boolean handleAllHurtEvents() {
        return false;
    }
}

