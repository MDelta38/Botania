/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSkull
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockMod;

public class BlockGaiaHead
extends BlockSkull {
    public BlockGaiaHead() {
        this.func_149663_c("gaiaHeadBlock");
        this.func_149711_c(1.0f);
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ModItems.gaiaHead;
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    public ArrayList<ItemStack> getDrops(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, int p_149749_6_, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if ((p_149749_6_ & 8) == 0) {
            ItemStack itemstack = new ItemStack(ModItems.gaiaHead, 1);
            TileEntitySkull tileentityskull = (TileEntitySkull)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_);
            if (tileentityskull == null) {
                return ret;
            }
            ret.add(itemstack);
        }
        return ret;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ModItems.gaiaHead;
    }

    public int func_149643_k(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_) {
        return 0;
    }

    public int func_149692_a(int p_149692_1_) {
        return 0;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileGaiaHead();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return Blocks.field_150402_ci.func_149733_h(p_149691_1_);
    }
}

