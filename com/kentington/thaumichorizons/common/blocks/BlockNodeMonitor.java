/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileNodeMonitor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.nodes.INode;

public class BlockNodeMonitor
extends BlockContainer {
    IIcon icon;

    public BlockNodeMonitor() {
        super(Material.field_151592_s);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149715_a(0.5f);
        this.func_149663_c("ThaumicHorizons_nodeMonitor");
        this.func_149658_d("ThaumicHorizons:nodeMonitor");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileNodeMonitor node = new TileNodeMonitor();
        return node;
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode || p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode || p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode;
    }

    public boolean func_149707_d(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149742_5_) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)p_149742_5_);
        return dir == ForgeDirection.DOWN && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode || dir == ForgeDirection.UP && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode || dir == ForgeDirection.NORTH && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode || dir == ForgeDirection.SOUTH && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode || dir == ForgeDirection.WEST && p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode || dir == ForgeDirection.EAST && p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode;
    }

    public int func_149660_a(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        if (p_149660_5_ == 0 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode) {
            return 0;
        }
        if (p_149660_5_ == 1 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode) {
            return 1;
        }
        if (p_149660_5_ == 2 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode) {
            return 2;
        }
        if (p_149660_5_ == 3 && p_149742_1_.func_147438_o(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode) {
            return 3;
        }
        if (p_149660_5_ == 4 && p_149742_1_.func_147438_o(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode) {
            return 4;
        }
        if (p_149660_5_ == 5 && p_149742_1_.func_147438_o(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode) {
            return 5;
        }
        return -1;
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149748_c(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        return ((TileNodeMonitor)p_149748_1_.func_147438_o((int)p_149748_2_, (int)p_149748_3_, (int)p_149748_4_)).activated ? 15 : 0;
    }

    public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        return ((TileNodeMonitor)p_149709_1_.func_147438_o((int)p_149709_2_, (int)p_149709_3_, (int)p_149709_4_)).activated ? 15 : 0;
    }

    public void killMe(World world, int x, int y, int z) {
        this.func_149697_b(world, x, y, z, 0, 0);
        world.func_147468_f(x, y, z);
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
        return ThaumicHorizons.blockNodeMonRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("gold_block");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }
}

