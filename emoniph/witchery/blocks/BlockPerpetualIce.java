/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockIce
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPerpetualIce
extends BlockIce {
    public BlockPerpetualIce() {
        this.func_149675_a(false);
        this.field_149765_K = 0.98f;
        this.func_149713_g(3);
        this.func_149711_c(2.0f);
        this.func_149752_b(5.0f);
        this.func_149647_a(WitcheryCreativeTab.INSTANCE);
    }

    public Block func_149663_c(String blockName) {
        BlockUtil.registerBlock((Block)this, blockName);
        return super.func_149663_c(blockName);
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public boolean func_149730_j() {
        return true;
    }
}

