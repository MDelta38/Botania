/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public final class TooltipHandler {
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onTooltipEvent(ItemTooltipEvent event) {
        if (event.itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150346_d) && event.itemStack.func_77960_j() == 1) {
            event.toolTip.add(StatCollector.func_74838_a((String)"botaniamisc.coarseDirt0"));
            event.toolTip.add(StatCollector.func_74838_a((String)"botaniamisc.coarseDirt1"));
        } else if (event.itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150474_ac) && event.entityPlayer.field_71075_bZ.field_75098_d) {
            event.toolTip.add(StatCollector.func_74838_a((String)"botaniamisc.spawnerTip"));
        }
        if (ItemNBTHelper.detectNBT(event.itemStack) && ItemNBTHelper.getBoolean(event.itemStack, "Botania_regenIvy", false)) {
            event.toolTip.add(StatCollector.func_74838_a((String)"botaniamisc.hasIvy"));
        }
        if (ItemNBTHelper.detectNBT(event.itemStack) && ItemNBTHelper.getBoolean(event.itemStack, "Botania_keepIvy", false)) {
            event.toolTip.add(StatCollector.func_74838_a((String)"botaniamisc.hasKeepIvy"));
        }
    }
}

