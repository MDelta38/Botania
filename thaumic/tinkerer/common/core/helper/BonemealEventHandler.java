/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.event.entity.player.BonemealEvent
 */
package thaumic.tinkerer.common.core.helper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumic.tinkerer.common.block.BlockInfusedGrain;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public class BonemealEventHandler {
    @SubscribeEvent
    public void onBonemeal(BonemealEvent event) {
        if (event.world.func_147439_a(event.x, event.y, event.z) instanceof BlockInfusedGrain && !ConfigHandler.cropsAllowBonemeal) {
            event.setCanceled(true);
        }
    }
}

