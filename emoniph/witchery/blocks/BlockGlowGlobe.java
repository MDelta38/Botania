/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.blocks.BlockBase;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowGlobe
extends BlockBase {
    public BlockGlowGlobe() {
        super(Material.field_151592_s);
        this.registerWithCreateTab = false;
        this.func_149711_c(0.0f);
        this.func_149715_a(0.9375f);
        this.func_149649_H();
        float f = 0.1f;
        this.func_149676_a(0.4f, 0.4f, 0.4f, 0.6f, 0.6f, 0.6f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public int func_149645_b() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(3) != 0) {
            double d0 = (float)x + 0.45f + (float)rand.nextInt(3) * 0.05f;
            double d1 = (float)y + 0.4f + (float)rand.nextInt(4) * 0.1f;
            double d2 = (float)z + 0.45f + (float)rand.nextInt(3) * 0.05f;
            world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }
}

