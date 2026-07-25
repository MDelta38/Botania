/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockLeviathan
extends Block {
    public BlockLeviathan() {
        super(Material.field_151576_e);
        this.func_149711_c(10.0f);
        this.func_149752_b(10.0f);
        this.func_149663_c("ThaumicHorizons_leviathan");
        this.func_149658_d("ThaumicHorizons:leviathan");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        this.func_150186_m(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_);
    }

    private void func_150186_m(World p_150186_1_, int p_150186_2_, int p_150186_3_, int p_150186_4_) {
        Random random = p_150186_1_.field_73012_v;
        double d0 = 0.0625;
        for (int l = 0; l < 6; ++l) {
            double d1 = (float)p_150186_2_ + random.nextFloat();
            double d2 = (float)p_150186_3_ + random.nextFloat();
            double d3 = (float)p_150186_4_ + random.nextFloat();
            if (l == 0 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_ + 1, p_150186_4_).func_149662_c()) {
                d2 = (double)(p_150186_3_ + 1) + d0;
            }
            if (l == 1 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_ - 1, p_150186_4_).func_149662_c()) {
                d2 = (double)(p_150186_3_ + 0) - d0;
            }
            if (l == 2 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_, p_150186_4_ + 1).func_149662_c()) {
                d3 = (double)(p_150186_4_ + 1) + d0;
            }
            if (l == 3 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_, p_150186_4_ - 1).func_149662_c()) {
                d3 = (double)(p_150186_4_ + 0) - d0;
            }
            if (l == 4 && !p_150186_1_.func_147439_a(p_150186_2_ + 1, p_150186_3_, p_150186_4_).func_149662_c()) {
                d1 = (double)(p_150186_2_ + 1) + d0;
            }
            if (l == 5 && !p_150186_1_.func_147439_a(p_150186_2_ - 1, p_150186_3_, p_150186_4_).func_149662_c()) {
                d1 = (double)(p_150186_2_ + 0) - d0;
            }
            if (random.nextInt(10) != 0 || !(d1 < (double)p_150186_2_ || d1 > (double)(p_150186_2_ + 1) || d2 < 0.0 || d2 > (double)(p_150186_3_ + 1) || d3 < (double)p_150186_4_) && !(d3 > (double)(p_150186_4_ + 1))) continue;
            p_150186_1_.func_72869_a("smoke", d1, d2, d3, 0.0, 0.0, 0.0);
        }
    }
}

