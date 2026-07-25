/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCandelabra
extends BlockBaseContainer {
    public BlockCandelabra() {
        super(Material.field_151574_g, TileEntityCandelabra.class);
        this.registerWithCreateTab = false;
        this.func_149715_a(1.0f);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149777_j);
        this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
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

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Witchery.Items.GENERIC;
    }

    public int func_149692_a(int par1) {
        return Witchery.Items.GENERIC.itemCandelabra.damageValue;
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        this.func_111046_k(par1World, par2, par3, par4);
    }

    private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
        if (!this.func_149718_j(par1World, par2, par3, par4)) {
            if (!par1World.field_72995_K) {
                this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
                par1World.func_147468_f(par2, par3, par4);
            }
            return false;
        }
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return Witchery.Items.GENERIC.itemCandelabra.createStack();
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
        return !world.func_147437_c(x, y - 1, z) && material != null && material.func_76218_k() && material.func_76220_a();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        double yMid = (double)y + 1.05;
        double yOuter = (double)y + 0.9;
        double mid = 0.5;
        double near = 0.2;
        double far = 0.8;
        if (rand.nextInt(4) != 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + 0.5, yMid, (double)z + 0.5, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + 0.5, yMid, (double)z + 0.5, 0.0, 0.0, 0.0);
        }
        if (rand.nextInt(4) != 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + 0.8, yOuter, (double)z + 0.5, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + 0.8, yOuter, (double)z + 0.5, 0.0, 0.0, 0.0);
        }
        if (rand.nextInt(4) != 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + 0.2, yOuter, (double)z + 0.5, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + 0.2, yOuter, (double)z + 0.5, 0.0, 0.0, 0.0);
        }
        if (rand.nextInt(4) != 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + 0.5, yOuter, (double)z + 0.8, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + 0.5, yOuter, (double)z + 0.8, 0.0, 0.0, 0.0);
        }
        if (rand.nextInt(4) != 0) {
            world.func_72869_a(ParticleEffect.FLAME.toString(), (double)x + 0.5, yOuter, (double)z + 0.2, 0.0, 0.0, 0.0);
            world.func_72869_a(ParticleEffect.SMOKE.toString(), (double)x + 0.5, yOuter, (double)z + 0.2, 0.0, 0.0, 0.0);
        }
    }

    public static class TileEntityCandelabra
    extends TileEntity {
        public boolean canUpdate() {
            return false;
        }
    }
}

