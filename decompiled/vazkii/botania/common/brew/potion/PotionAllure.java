/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package vazkii.botania.common.brew.potion;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import vazkii.botania.common.brew.potion.PotionMod;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PotionAllure
extends PotionMod {
    public PotionAllure() {
        super(ConfigHandler.potionIDAllure, "allure", false, 13540, 5);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityFishHook hook;
        EntityLivingBase e = event.entityLiving;
        if (e instanceof EntityPlayer && this.hasEffect(e) && (hook = ((EntityPlayer)e).field_71104_cf) != null) {
            hook.func_70071_h_();
        }
    }
}

