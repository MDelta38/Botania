/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCosmeticStairs
extends BlockStairs {
    Block refBlock;
    int refMeta;

    public BlockCosmeticStairs(Block p_i45428_1_, int p_i45428_2_) {
        super(p_i45428_1_, p_i45428_2_);
        this.refBlock = p_i45428_1_;
        this.refMeta = p_i45428_2_;
        this.func_149713_g(0);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side) {
        if (this.refBlock == ConfigBlocks.blockCosmeticSolid && this.refMeta == 11) {
            return this.refBlock.func_149673_e(ba, x, y, z, side + 100);
        }
        return this.refBlock.func_149691_a(side, this.refMeta);
    }
}

