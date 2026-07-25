/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockIce
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockBootsIce
extends BlockIce {
    public BlockBootsIce(int par1) {
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a((Block)Blocks.field_150432_aD);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        par2EntityPlayer.func_71020_j(0.025f);
        if (par1World.field_73011_w.field_76575_d) {
            par1World.func_147468_f(par3, par4, par5);
            return;
        }
        int i1 = EnchantmentHelper.func_77517_e((EntityLivingBase)par2EntityPlayer);
        this.func_149697_b(par1World, par3, par4, par5, par6, i1);
        Material material = par1World.func_147439_a(par3, par4 - 1, par5).func_149688_o();
        if (material.func_76230_c() || material.func_76224_d()) {
            par1World.func_147449_b(par3, par4, par5, Blocks.field_150355_j);
        }
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.field_73011_w.field_76575_d) {
            par1World.func_147468_f(par2, par3, par4);
            return;
        }
        this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
        par1World.func_147449_b(par2, par3, par4, Blocks.field_150355_j);
    }
}

