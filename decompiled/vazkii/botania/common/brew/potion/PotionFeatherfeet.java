/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package vazkii.botania.common.brew.potion;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import vazkii.botania.common.brew.potion.PotionMod;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PotionFeatherfeet
extends PotionMod {
    public PotionFeatherfeet() {
        super(ConfigHandler.potionIDFeatherfeet, "featherFeet", false, 2534911, 1);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase e = event.entityLiving;
        if (this.hasEffect(e)) {
            e.field_70143_R = 2.5f;
        }
    }
}

