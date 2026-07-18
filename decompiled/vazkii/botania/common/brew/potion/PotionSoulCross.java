/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package vazkii.botania.common.brew.potion;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.botania.common.brew.potion.PotionMod;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PotionSoulCross
extends PotionMod {
    public PotionSoulCross() {
        super(ConfigHandler.potionIDSoulCross, "soulCross", false, 4670781, 0);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityKill(LivingDeathEvent event) {
        EntityLivingBase living;
        Entity e = event.source.func_76346_g();
        if (e != null && e instanceof EntityLivingBase && this.hasEffect(living = (EntityLivingBase)e)) {
            living.func_70691_i(event.entityLiving.func_110138_aP() / 20.0f);
        }
    }
}

