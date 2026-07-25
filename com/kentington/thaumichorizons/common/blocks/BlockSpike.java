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
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSpike;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSpike
extends BlockContainer {
    int type;
    IIcon icon;
    IIcon iconWood;
    IIcon iconTooth;

    public BlockSpike(int spikeType, Material mat, String name) {
        super(mat);
        this.type = spikeType;
        this.func_149711_c(3.0f);
        this.func_149752_b(3.0f);
        this.func_149663_c("ThaumicHorizons_" + name);
        this.func_149658_d("ThaumicHorizons:spike");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockSpikeRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("iron_block");
        this.iconWood = ir.func_94245_a("thaumcraft:greatwoodtop");
        this.iconTooth = ir.func_94245_a("thaumichorizons:bone");
    }

    public IIcon func_149691_a(int par1, int par2) {
        switch (this.type) {
            case 1: {
                return this.iconWood;
            }
            case 2: {
                return this.iconTooth;
            }
        }
        return this.icon;
    }

    public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
        p_149670_5_.func_70097_a(DamageSource.field_76367_g, 1.0f);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileSpike spike = new TileSpike((byte)metadata, (byte)this.type);
        return spike;
    }

    public boolean func_149707_d(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_, int p_149742_5_) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)p_149742_5_);
        return dir == ForgeDirection.DOWN && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN) || dir == ForgeDirection.UP && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP) || dir == ForgeDirection.NORTH && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH) || dir == ForgeDirection.SOUTH && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH) || dir == ForgeDirection.WEST && p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST) || dir == ForgeDirection.EAST && p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST);
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST) || p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN);
    }

    public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int b0 = -1;
        if (p_149660_5_ == 0 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ + 1, p_149660_4_, ForgeDirection.DOWN)) {
            b0 = 0;
        }
        if (p_149660_5_ == 1 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ - 1, p_149660_4_, ForgeDirection.UP)) {
            b0 = 1;
        }
        if (p_149660_5_ == 2 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, ForgeDirection.NORTH)) {
            b0 = 2;
        }
        if (p_149660_5_ == 3 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, ForgeDirection.SOUTH)) {
            b0 = 3;
        }
        if (p_149660_5_ == 4 && p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, ForgeDirection.WEST)) {
            b0 = 4;
        }
        if (p_149660_5_ == 5 && p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, ForgeDirection.EAST)) {
            b0 = 5;
        }
        return b0;
    }

    public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        this.func_149820_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
    }

    private void func_149820_e(World p_149820_1_, int p_149820_2_, int p_149820_3_, int p_149820_4_) {
        if (!this.func_149707_d(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.func_72805_g(p_149820_2_, p_149820_3_, p_149820_4_))) {
            this.func_149697_b(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.func_72805_g(p_149820_2_, p_149820_3_, p_149820_4_), 0);
            p_149820_1_.func_147468_f(p_149820_2_, p_149820_3_, p_149820_4_);
        }
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        float f = 0.0625f;
        int md = p_149668_1_.func_72805_g(p_149668_2_, p_149668_3_, p_149668_4_);
        if (md == 0) {
            return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)((float)p_149668_3_ + f), (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 1) {
            return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)((float)(p_149668_3_ + 1) - f), (double)(p_149668_4_ + 1));
        }
        if (md == 4) {
            return AxisAlignedBB.func_72330_a((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 5) {
            return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)((float)(p_149668_2_ + 1) - f), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 2) {
            return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 3) {
            return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)((float)(p_149668_4_ + 1) - f));
        }
        return AxisAlignedBB.func_72330_a((double)((float)p_149668_2_ + f), (double)p_149668_3_, (double)((float)p_149668_4_ + f), (double)((float)(p_149668_2_ + 1) - f), (double)((float)(p_149668_3_ + 1) - f), (double)((float)(p_149668_4_ + 1) - f));
    }
}

