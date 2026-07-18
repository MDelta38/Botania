/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package vazkii.botania.common.item.equipment.tool;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;

public class ItemGlassPick
extends ItemManasteelPick {
    private static final int MANA_PER_DAMAGE = 160;
    private static final Item.ToolMaterial MATERIAL = EnumHelper.addToolMaterial((String)"MANASTEEL_GLASS", (int)0, (int)125, (float)4.8f, (float)1.0f, (int)10);

    public ItemGlassPick() {
        super(MATERIAL, "glassPick");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && event.block != null && event.drops.isEmpty() && event.harvester.func_71045_bC() != null && event.harvester.func_71045_bC().func_77973_b() == this && event.block.func_149688_o() == Material.field_151592_s && event.block.canSilkHarvest(event.world, event.harvester, event.x, event.y, event.z, event.blockMetadata)) {
            event.drops.add(new ItemStack(event.block, 1, event.blockMetadata));
        }
    }

    @Override
    public int getManaPerDmg() {
        return 160;
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150359_w) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public int getSortingPriority(ItemStack stack) {
        return 0;
    }
}

