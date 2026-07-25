/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWitchWoodSlab
extends BlockSlab {
    public static final String[] BLOCK_TYPES = new String[]{"rowan", "alder", "hawthorn"};

    public BlockWitchWoodSlab(boolean doubleSlab) {
        super(doubleSlab, Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149752_b(5.0f);
        this.func_149647_a(WitcheryCreativeTab.INSTANCE);
    }

    public Block func_149663_c(String blockName) {
        BlockUtil.registerBlock((Block)this, null, blockName);
        super.func_149663_c(blockName);
        Blocks.field_150480_ab.setFireInfo((Block)this, 5, 20);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return Witchery.Blocks.PLANKS.func_149691_a(p_149691_1_, p_149691_2_ & 7);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)Witchery.Blocks.WOOD_SLAB_SINGLE);
    }

    protected ItemStack func_149644_j(int p_149644_1_) {
        return new ItemStack(Item.func_150898_a((Block)Witchery.Blocks.WOOD_SLAB_SINGLE), 2, p_149644_1_ & 7);
    }

    public String func_149739_a() {
        return super.func_149739_a();
    }

    public String func_150002_b(int p_150002_1_) {
        if (p_150002_1_ < 0 || p_150002_1_ >= BLOCK_TYPES.length) {
            p_150002_1_ = 0;
        }
        return super.func_149739_a() + "." + BLOCK_TYPES[p_150002_1_];
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        if (this.field_150004_a) {
            return Witchery.Items.SLAB_DOUBLE;
        }
        return Witchery.Items.SLAB_SINGLE;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        if (p_149666_1_ != Item.func_150898_a((Block)Witchery.Blocks.WOOD_SLAB_DOUBLE)) {
            // empty if block
        }
        for (int i = 0; i < BLOCK_TYPES.length; ++i) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
    }
}

