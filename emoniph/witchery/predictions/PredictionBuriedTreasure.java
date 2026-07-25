/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ChestGenHooks
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.predictions.PredictionAlwaysForced;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.event.world.BlockEvent;

public class PredictionBuriedTreasure
extends PredictionAlwaysForced {
    protected final String chestGenHook;

    public PredictionBuriedTreasure(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, String chestGenHook) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
        this.chestGenHook = chestGenHook;
    }

    @Override
    public boolean shouldTrySelfFulfill(World world, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean doSelfFulfillment(World world, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld) {
        if (!(event.isCanceled() || event.block != Blocks.field_150349_c && event.block != Blocks.field_150346_d && event.block != Blocks.field_150354_m && event.block != Blocks.field_150391_bh || event.y <= 6 || !this.shouldWeActivate(world, player, isPastDue) || world.func_147437_c(event.x + 1, event.y - 1, event.z) || world.func_147437_c(event.x - 1, event.y - 1, event.z) || world.func_147437_c(event.x, event.y - 1, event.z + 1) || world.func_147437_c(event.x, event.y - 1, event.z - 1) || world.func_147437_c(event.x, event.y - 2, event.z))) {
            world.func_147449_b(event.x, event.y - 1, event.z, (Block)Blocks.field_150486_ae);
            TileEntity tile = world.func_147438_o(event.x, event.y - 1, event.z);
            if (tile != null && tile instanceof TileEntityChest) {
                TileEntityChest chest = (TileEntityChest)tile;
                ChestGenHooks info = ChestGenHooks.getInfo((String)this.chestGenHook);
                WeightedRandomChestContent.func_76293_a((Random)world.field_73012_v, (WeightedRandomChestContent[])info.getItems(world.field_73012_v), (IInventory)chest, (int)info.getCount(world.field_73012_v));
            }
            return true;
        }
        return false;
    }
}

