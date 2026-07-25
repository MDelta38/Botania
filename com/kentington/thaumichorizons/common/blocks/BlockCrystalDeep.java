/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCrystalDeep
extends BlockBreakable {
    public IIcon icon;

    public BlockCrystalDeep() {
        super("glass", Material.field_151592_s, false);
        this.func_149647_a(ThaumicHorizons.tabTH);
        this.func_149663_c("ThaumicHorizons_deepcrystal");
        this.func_149711_c(2.0f);
        this.func_149672_a(Block.field_149778_k);
        this.func_149715_a(1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalDeep");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }
}

