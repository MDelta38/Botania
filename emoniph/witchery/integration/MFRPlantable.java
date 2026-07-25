/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 *  powercrystals.minefactoryreloaded.api.IFactoryPlantable
 *  powercrystals.minefactoryreloaded.api.ReplacementBlock
 */
package com.emoniph.witchery.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;

public class MFRPlantable
implements IFactoryPlantable {
    protected Item seedItemID;
    protected Block cropBlockID;

    public MFRPlantable(Item seeds, Block plantedBlock) {
        this.seedItemID = seeds;
        this.cropBlockID = plantedBlock;
    }

    public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
        Block blockID = world.func_147439_a(x, y - 1, z);
        if (world.func_147437_c(x, y, z)) {
            return this.cropBlockID.func_149742_c(world, x, y, z) && this.cropBlockID.func_149718_j(world, x, y, z) || this.cropBlockID instanceof IPlantable && this.cropBlockID.canSustainPlant((IBlockAccess)world, x, y, z, ForgeDirection.UP, (IPlantable)this.cropBlockID);
        }
        return false;
    }

    public void prePlant(World world, int x, int y, int z, ItemStack stack) {
    }

    public void postPlant(World world, int x, int y, int z, ItemStack stack) {
    }

    public Item getSeed() {
        return this.seedItemID;
    }

    public boolean canBePlanted(ItemStack stack, boolean forFermenting) {
        return stack.func_77973_b() == this.seedItemID;
    }

    public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack) {
        return new ReplacementBlock(this.cropBlockID);
    }
}

