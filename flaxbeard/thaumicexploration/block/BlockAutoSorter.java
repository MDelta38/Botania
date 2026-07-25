/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.block;

import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAutoSorter
extends BlockContainer {
    public BlockAutoSorter(int par1, Material par2Material) {
        super(par2Material);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityAutoSorter();
    }
}

