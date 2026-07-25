/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockDust
extends Block {
    private static final String __OBFID = "CL_00000310";

    public BlockDust() {
        super(Material.field_151595_p);
        this.func_149711_c(0.5f);
        this.func_149672_a(field_149776_m);
        this.func_149663_c("ThaumicHorizons_dust");
        this.func_149658_d("ThaumicHorizons:dust");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        float f = 0.125f;
        return AxisAlignedBB.func_72330_a((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)((float)(p_149668_3_ + 1) - f), (double)(p_149668_4_ + 1));
    }

    public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
        p_149670_5_.field_70159_w *= 0.4;
        p_149670_5_.field_70179_y *= 0.4;
    }
}

