/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockBone
extends Block {
    public BlockBone() {
        super(Material.field_151576_e);
        this.func_149711_c(2.5f);
        this.func_149752_b(2.5f);
        this.func_149663_c("ThaumicHorizons_bone");
        this.func_149658_d("ThaumicHorizons:bone");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Items.field_151103_aS;
    }

    public int func_149679_a(int p_149679_1_, Random p_149679_2_) {
        return this.func_149745_a(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1);
    }

    public int func_149745_a(Random p_149745_1_) {
        return 3 + p_149745_1_.nextInt(3);
    }
}

