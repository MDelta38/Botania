/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.item.equipment.tool.elementium;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public class ItemElementiumPick
extends ItemManasteelPick {
    public ItemElementiumPick() {
        super(BotaniaAPI.elementiumToolMaterial, "elementiumPick");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        ItemStack stack;
        if (event.harvester != null && (stack = event.harvester.func_71045_bC()) != null && (stack.func_77973_b() == this || stack.func_77973_b() == ModItems.terraPick && ItemTerraPick.isTipped(stack))) {
            for (int i = 0; i < event.drops.size(); ++i) {
                Block block;
                ItemStack drop = (ItemStack)event.drops.get(i);
                if (drop == null || (block = Block.func_149634_a((Item)drop.func_77973_b())) == null || !ItemElementiumPick.isDisposable(block) && (!ItemElementiumPick.isSemiDisposable(block) || event.harvester.func_70093_af())) continue;
                event.drops.remove(i);
            }
        }
    }

    public static boolean isDisposable(Block block) {
        for (int id : OreDictionary.getOreIDs((ItemStack)new ItemStack(block))) {
            String name = OreDictionary.getOreName((int)id);
            if (!BotaniaAPI.disposableBlocks.contains(name)) continue;
            return true;
        }
        return false;
    }

    public static boolean isSemiDisposable(Block block) {
        for (int id : OreDictionary.getOreIDs((ItemStack)new ItemStack(block))) {
            String name = OreDictionary.getOreName((int)id);
            if (!BotaniaAPI.semiDisposableBlocks.contains(name)) continue;
            return true;
        }
        return false;
    }
}

