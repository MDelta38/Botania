/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.IHighlightHandler
 *  codechicken.nei.api.ItemInfo$Layout
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.integration;

import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.api.ItemInfo;
import com.emoniph.witchery.Witchery;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class NEIHighlightHandler
implements IHighlightHandler {
    private static final ItemStack yellowPlant = new ItemStack((Block)Blocks.field_150327_N);
    private static final ItemStack redPlant = new ItemStack((Block)Blocks.field_150328_O);
    private static final ItemStack shrubPlant = new ItemStack((Block)Blocks.field_150330_I);
    private static final ItemStack door = new ItemStack(Blocks.field_150466_ao);
    private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
    private static final ItemStack grass = new ItemStack((Block)Blocks.field_150349_c);
    private final Block block;

    public NEIHighlightHandler(Block block) {
        this.block = block;
    }

    public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop) {
        if (this.block == Witchery.Blocks.TRAPPED_PLANT) {
            if (mop == null || mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
                return null;
            }
            int foundMeta = world.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (foundMeta == 5 || foundMeta == 6 || foundMeta == 7 || foundMeta == 4) {
                return yellowPlant;
            }
            if (foundMeta == 1 || foundMeta == 2 || foundMeta == 3 || foundMeta == 0) {
                return redPlant;
            }
            if (foundMeta == 9 || foundMeta == 10 || foundMeta == 11 || foundMeta == 8) {
                return shrubPlant;
            }
        } else {
            if (this.block == Witchery.Blocks.DOOR_ALDER) {
                return door;
            }
            if (this.block == Witchery.Blocks.PIT_DIRT) {
                return dirt;
            }
            if (this.block == Witchery.Blocks.PIT_GRASS) {
                return grass;
            }
        }
        return null;
    }

    public List<String> handleTextData(ItemStack itemStack, World world, EntityPlayer player, MovingObjectPosition mop, List<String> currenttip, ItemInfo.Layout layout) {
        return null;
    }
}

