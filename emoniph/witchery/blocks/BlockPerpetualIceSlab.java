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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPerpetualIceSlab
extends BlockSlab {
    public BlockPerpetualIceSlab(boolean doubleSlab) {
        super(doubleSlab, Material.field_151588_w);
        this.func_149713_g(3);
        this.func_149711_c(2.0f);
        this.func_149752_b(5.0f);
        this.func_149647_a(WitcheryCreativeTab.INSTANCE);
        this.field_149787_q = false;
    }

    public Block func_149663_c(String blockName) {
        BlockUtil.registerBlock((Block)this, null, blockName);
        super.func_149663_c(blockName);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess block, int x, int y, int z, int side) {
        boolean flag;
        if (this.field_150004_a) {
            return super.func_149646_a(block, x, y, z, side);
        }
        if (side != 1 && side != 0 && !super.func_149646_a(block, x, y, z, side)) {
            return false;
        }
        int i1 = x + Facing.field_71586_b[Facing.field_71588_a[side]];
        int j1 = y + Facing.field_71587_c[Facing.field_71588_a[side]];
        int k1 = z + Facing.field_71585_d[Facing.field_71588_a[side]];
        boolean bl = flag = (block.func_72805_g(i1, j1, k1) & 8) != 0;
        return flag ? (side == 0 ? true : (side == 1 && super.func_149646_a(block, x, y, z, side) ? true : !this.func_150003_aa(block.func_147439_a(x, y, z)) || (block.func_72805_g(x, y, z) & 8) == 0)) : (side == 1 ? true : (side == 0 && super.func_149646_a(block, x, y, z, side) ? true : !this.func_150003_aa(block.func_147439_a(x, y, z)) || (block.func_72805_g(x, y, z) & 8) != 0));
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    private boolean func_150003_aa(Block p_150003_0_) {
        return p_150003_0_ == Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE || p_150003_0_ == Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return Witchery.Blocks.PERPETUAL_ICE.func_149691_a(p_149691_1_, p_149691_2_ & 7);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE);
    }

    protected ItemStack func_149644_j(int p_149644_1_) {
        return new ItemStack(Item.func_150898_a((Block)Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE), 2, p_149644_1_ & 7);
    }

    public String func_150002_b(int p_150002_1_) {
        return super.func_149739_a();
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        if (this.field_150004_a) {
            return Witchery.Items.PERPERTUAL_ICE_SLAB_DOUBLE;
        }
        return Witchery.Items.PERPERTUAL_ICE_SLAB_SINGLE;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
    }
}

