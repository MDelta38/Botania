/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDemonHeart
extends BlockBaseContainer {
    public BlockDemonHeart() {
        super(Material.field_151578_c, TileEntityDemonHeart.class);
        this.registerWithCreateTab = false;
        this.func_149715_a(0.2f);
        this.func_149711_c(1.0f);
        this.func_149672_a(field_149767_g);
        this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 0.8f, 0.75f);
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            par1World.func_72921_c(par2, par3, par4, 2, 2);
        }
        if (l == 1) {
            par1World.func_72921_c(par2, par3, par4, 5, 2);
        }
        if (l == 2) {
            par1World.func_72921_c(par2, par3, par4, 3, 2);
        }
        if (l == 3) {
            par1World.func_72921_c(par2, par3, par4, 4, 2);
        }
    }

    @Override
    public TileEntity func_149915_a(World world, int metadata) {
        return new TileEntityDemonHeart();
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149745_a(Random rand) {
        return 1;
    }

    public Item func_149650_a(int par1, Random rand, int fortune) {
        return Witchery.Items.GENERIC;
    }

    public int func_149692_a(int par1) {
        return Witchery.Items.GENERIC.itemDemonHeart.damageValue;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return Witchery.Items.GENERIC.itemDemonHeart.createStack();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        double yMid = (double)y + 0.8;
        double mid1 = 0.35 + 0.3 * rand.nextDouble();
        double mid2 = 0.35 + 0.3 * rand.nextDouble();
        if (rand.nextInt(10) == 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + mid1, yMid, (double)z + mid2, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + mid1, yMid, (double)z + mid2, 0.0, 0.0, 0.0);
        }
    }

    public static class TileEntityDemonHeart
    extends TileEntityBase {
        public long totalTicks() {
            return this.ticks;
        }

        @Override
        public void func_145845_h() {
            super.func_145845_h();
            if (this.field_145850_b.field_72995_K && this.ticks % 25L == 0L) {
                this.field_145850_b.func_72980_b(0.5 + (double)this.field_145851_c, 0.5 + (double)this.field_145848_d, 0.5 + (double)this.field_145849_e, "witchery:random.heartbeat", 0.8f, 1.0f, false);
            }
        }
    }
}

