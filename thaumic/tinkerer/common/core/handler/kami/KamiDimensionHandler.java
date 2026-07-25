/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package thaumic.tinkerer.common.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumic.tinkerer.common.item.kami.tool.ItemIchorPickAdv;

public class KamiDimensionHandler {
    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            ItemStack stack = event.entityPlayer.func_71045_bC();
            if (event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z) == Blocks.field_150357_h && stack != null && stack.func_77973_b() instanceof ItemIchorPickAdv) {
                stack.func_77973_b().onBlockStartBreak(stack, event.x, event.y, event.z, event.entityPlayer);
            }
        }
    }
}

