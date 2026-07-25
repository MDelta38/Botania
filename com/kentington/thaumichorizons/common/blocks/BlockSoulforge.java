/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSoulforge
extends BlockContainer {
    public IIcon iconLiquid;
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;

    public BlockSoulforge() {
        super(Material.field_151592_s);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149663_c("ThaumicHorizons_soulforge");
        this.func_149658_d("ThaumicHorizons:soulforge");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileSoulforge te = new TileSoulforge();
        return te;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof TileSoulforge) {
            return ((TileSoulforge)tile).activate(world, player);
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconJarSide = ir.func_94245_a("thaumichorizons:jar_side");
        this.iconJarTop = ir.func_94245_a("thaumichorizons:jar_top");
        this.iconJarBottom = ir.func_94245_a("thaumichorizons:jar_bottom");
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
        return ThaumicHorizons.blockSoulforgeRI;
    }
}

