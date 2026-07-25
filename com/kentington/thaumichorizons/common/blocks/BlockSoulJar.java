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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulJar
extends BlockContainer {
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;

    public BlockSoulJar() {
        super(Material.field_151592_s);
        this.func_149711_c(0.3f);
        this.func_149752_b(1.0f);
        this.func_149663_c("ThaumicHorizons_soulJar");
        this.func_149658_d("ThaumicHorizons:soulJar");
        this.func_149647_a(ThaumicHorizons.tabTH);
        this.func_149715_a(0.66f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconJarSide = ir.func_94245_a("thaumcraft:jar_side");
        this.iconJarTop = ir.func_94245_a("thaumcraft:jar_top");
        this.iconJarBottom = ir.func_94245_a("thaumcraft:jar_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side == 1 ? this.iconJarTop : (side == 0 ? this.iconJarBottom : this.iconJarSide);
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockJarRI;
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.1875f, 0.0f, 0.1875f, 0.8125f, 0.75f, 0.8125f);
        super.func_149719_a(world, i, j, k);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileSoulJar te = new TileSoulJar();
        return te;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileSoulJar) {
            ItemStack drop = new ItemStack((Block)this);
            drop.func_77982_d(((TileSoulJar)te).jarTag);
            drops.add(drop);
        }
        return drops;
    }
}

