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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchVine
extends BlockBase {
    public BlockWitchVine() {
        super(new Material(Material.field_151582_l.func_151565_r()){
            {
                this.func_76226_g();
                this.func_76219_n();
            }
        });
        this.registerWithCreateTab = false;
        this.func_149711_c(0.2f);
        this.func_149672_a(field_149779_h);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        this.setBoundsBasedOnMetadata(world.func_72805_g(x, y, z));
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149633_g(world, x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149635_D() {
        return ColorizerFoliage.func_77468_c();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int meta) {
        return ColorizerFoliage.func_77468_c();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        return world.func_72807_a(x, z).func_150571_c(x, y, z);
    }

    public void setBoundsBasedOnMetadata(int meta) {
        float f = 0.125f;
        if (meta == 2) {
            this.func_149676_a(0.0f, 0.0f, 1.0f - f, 1.0f, 1.0f, 1.0f);
        } else if (meta == 3) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f);
        } else if (meta == 4) {
            this.func_149676_a(1.0f - f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else if (meta == 5) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, f, 1.0f, 1.0f);
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return Witchery.proxy.getVineRenderId();
    }

    public Item func_149650_a(int metadata, Random rand, int fortune) {
        return null;
    }

    public int func_149745_a(Random rand) {
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return true;
    }
}

