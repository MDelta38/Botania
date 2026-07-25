/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class BlockTaintFibresItem
extends ItemBlock {
    public BlockTaintFibresItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return ThaumcraftWorldGenerator.biomeTaint.field_76790_z;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return ((BlockTaintFibres)ConfigBlocks.blockTaintFibres).icon[meta];
    }
}

