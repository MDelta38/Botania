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
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortexStabilizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockVortexStabilizer
extends BlockContainer {
    IIcon icon;

    public BlockVortexStabilizer() {
        super(Material.field_151573_f);
        this.func_149663_c("ThaumicHorizons_vortexStabilizer");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public int func_149660_a(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        return p_149660_5_;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileVortexStabilizer();
    }

    public void killMe(World world, int x, int y, int z, boolean drop) {
        if (((TileVortexStabilizer)world.func_147438_o((int)x, (int)y, (int)z)).hasTarget) {
            ((TileVortexStabilizer)world.func_147438_o(x, y, z)).reHungrifyTarget();
        }
        if (drop) {
            this.func_149697_b(world, x, y, z, 0, 0);
        }
    }

    public void func_149725_f(World world, int x, int y, int z, int md) {
        this.killMe(world, x, y, z, false);
    }

    public void func_149695_a(World world, int x, int y, int z, Block nbid) {
        TileVortexStabilizer tile = (TileVortexStabilizer)world.func_147438_o(x, y, z);
        tile.redstoned = world.func_72864_z(x, y, z);
        if (!tile.redstoned && !world.func_72864_z(x, y, z) || tile.redstoned && world.func_72864_z(x, y, z)) {
            tile.func_70296_d();
            tile.func_145831_w().func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
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
        return ThaumicHorizons.blockVortexStabilizerRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("iron_block");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }
}

