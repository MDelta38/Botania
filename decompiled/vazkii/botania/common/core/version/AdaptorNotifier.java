/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package vazkii.botania.common.core.version;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.botania.common.core.handler.ConfigHandler;

public final class AdaptorNotifier {
    boolean triedToWarnPlayer;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!this.triedToWarnPlayer && Minecraft.func_71410_x().field_71439_g != null) {
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            ConfigHandler.adaptor.tellChanges((EntityPlayer)player);
            this.triedToWarnPlayer = true;
        }
    }
}

