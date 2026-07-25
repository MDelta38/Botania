/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.tiles.TileNodeEnergized
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileTransductionAmplifier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileNodeEnergized;

public class BlockTransductionAmplifier
extends BlockContainer {
    IIcon icon;

    public BlockTransductionAmplifier() {
        super(Material.field_151576_e);
        this.func_149663_c("ThaumicHorizons_transductionAmplifier");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileTransductionAmplifier();
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized || p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof TileNodeEnergized || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof TileNodeEnergized;
    }

    public boolean func_149707_d(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149742_5_) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)p_149742_5_);
        return dir == ForgeDirection.NORTH && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized || dir == ForgeDirection.SOUTH && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized || dir == ForgeDirection.WEST && p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized || dir == ForgeDirection.EAST && p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized;
    }

    public int func_149660_a(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        if (p_149660_5_ == 0 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof TileNodeEnergized) {
            return 0;
        }
        if (p_149660_5_ == 1 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof TileNodeEnergized) {
            return 1;
        }
        if (p_149660_5_ == 2 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized) {
            return 2;
        }
        if (p_149660_5_ == 3 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized) {
            return 3;
        }
        if (p_149660_5_ == 4 && p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized) {
            return 4;
        }
        if (p_149660_5_ == 5 && p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized) {
            return 5;
        }
        return -1;
    }

    public void killMe(World world, int x, int y, int z, boolean drop) {
        if (((TileTransductionAmplifier)world.func_147438_o((int)x, (int)y, (int)z)).activated) {
            ((TileTransductionAmplifier)world.func_147438_o(x, y, z)).unBoostNode(x, y, z);
        }
        if (drop) {
            this.func_149697_b(world, x, y, z, 0, 0);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockTransducerRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("iron_block");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    public void func_149725_f(World world, int x, int y, int z, int md) {
        this.killMe(world, x, y, z, false);
    }

    public void func_149695_a(World world, int x, int y, int z, Block nbid) {
        TileTransductionAmplifier tile = (TileTransductionAmplifier)world.func_147438_o(x, y, z);
        if (tile.activated && !world.func_72864_z(x, y, z)) {
            tile.shouldActivate = false;
        } else if (!tile.activated && world.func_72864_z(x, y, z)) {
            tile.shouldActivate = true;
        }
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        switch (world.func_72805_g(x, y, z)) {
            case 0: {
                return side == ForgeDirection.DOWN;
            }
            case 1: {
                return side == ForgeDirection.UP;
            }
            case 2: {
                return side == ForgeDirection.NORTH;
            }
            case 3: {
                return side == ForgeDirection.SOUTH;
            }
            case 4: {
                return side == ForgeDirection.WEST;
            }
            case 5: {
                return side == ForgeDirection.EAST;
            }
        }
        return false;
    }
}

