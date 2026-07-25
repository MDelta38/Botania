/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockChocolate
extends Block {
    public IIcon coloredGrass;

    public BlockChocolate() {
        super(Material.field_151568_F);
        this.func_149711_c(0.5f);
        this.func_149752_b(0.5f);
        this.func_149663_c("ThaumicHorizons_chocolate");
        this.func_149658_d("ThaumicHorizons:chocolate");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a("thaumichorizons:chocolate");
        this.coloredGrass = register.func_94245_a("thaumichorizons:grasscolorized");
    }
}

