/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockCrystal
extends BlockBreakable {
    public IIcon[] icon = new IIcon[16];

    public BlockCrystal() {
        super("thaumichorizons:blockCrystalRed", Material.field_151592_s, false);
        this.func_149647_a(ThaumicHorizons.tabTH);
        this.func_149711_c(1.75f);
        this.func_149752_b(0.5f);
        this.func_149672_a(Block.field_149778_k);
        this.func_149713_g(1);
        this.func_149663_c("ThaumicHorizons_crystal");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalWhite");
        this.icon[1] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalOrange");
        this.icon[2] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalMagenta");
        this.icon[3] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalLightBlue");
        this.icon[4] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalYellow");
        this.icon[5] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalLime");
        this.icon[6] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalPink");
        this.icon[7] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalGray");
        this.icon[8] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalLightGray");
        this.icon[9] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalCyan");
        this.icon[10] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalPurple");
        this.icon[11] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalBlue");
        this.icon[12] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalBrown");
        this.icon[13] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalGreen");
        this.icon[14] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalRed");
        this.icon[15] = par1IconRegister.func_94245_a("thaumichorizons:blockCrystalBlack");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.icon[par2];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < 16; ++var4) {
            par3List.add(new ItemStack((Block)this, 1, var4));
        }
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public MapColor func_149728_f(int p_149728_1_) {
        return MapColor.func_151644_a((int)p_149728_1_);
    }
}

