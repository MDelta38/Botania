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
package thaumcraft.common.blocks;

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
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCosmeticStoneSlab
extends BlockSlab {
    public static final String[] types = new String[]{"arcane", "eldritch"};

    public BlockCosmeticStoneSlab(boolean p_i45437_1_) {
        super(p_i45437_1_, Material.field_151576_e);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149713_g(0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return (p_149691_2_ & 7) == 0 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(p_149691_1_, 7) : ((p_149691_2_ & 7) == 1 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(p_149691_1_, 11) : super.func_149691_a(p_149691_1_, p_149691_2_));
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)ConfigBlocks.blockSlabStone);
    }

    protected ItemStack func_149644_j(int p_149644_1_) {
        return new ItemStack(Item.func_150898_a((Block)ConfigBlocks.blockSlabStone), 1, p_149644_1_ & 7);
    }

    public String func_150002_b(int p_150002_1_) {
        if (p_150002_1_ < 0 || p_150002_1_ >= types.length) {
            p_150002_1_ = 0;
        }
        return super.func_149739_a() + "." + types[p_150002_1_];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        if (p_149666_1_ != Item.func_150898_a((Block)ConfigBlocks.blockDoubleSlabStone)) {
            for (int i = 0; i < types.length; ++i) {
                p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    private static boolean func_150003_a(Block p_150003_0_) {
        return p_150003_0_ == ConfigBlocks.blockSlabStone;
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return BlockCosmeticStoneSlab.func_150003_a((Block)this) ? Item.func_150898_a((Block)this) : (this == ConfigBlocks.blockDoubleSlabStone ? Item.func_150898_a((Block)ConfigBlocks.blockSlabStone) : Item.func_150898_a((Block)Blocks.field_150333_U));
    }
}

