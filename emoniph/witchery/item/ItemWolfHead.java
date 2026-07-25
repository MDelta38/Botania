/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockWolfHead;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.MultiItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemWolfHead
extends MultiItemBlock {
    private static final String[] skullTypes = new String[]{"wolf", "hellhound"};
    public static final String[] field_94587_a = new String[]{"wolf", "hellhound"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] field_94586_c;

    public ItemWolfHead(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_111206_d("witchery:wolfhead");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side == 0) {
            return false;
        }
        if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
            return false;
        }
        if (side == 1) {
            ++y;
        }
        if (side == 2) {
            --z;
        }
        if (side == 3) {
            ++z;
        }
        if (side == 4) {
            --x;
        }
        if (side == 5) {
            ++x;
        }
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (!Witchery.Blocks.WOLFHEAD.func_149742_c(world, x, y, z)) {
            return false;
        }
        if (!world.field_72995_K) {
            BlockWolfHead.TileEntityWolfHead tile;
            world.func_147465_d(x, y, z, Witchery.Blocks.WOLFHEAD, side, 3);
            int i1 = 0;
            if (side == 1) {
                i1 = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 16.0f / 360.0f) + 0.5)) & 0xF;
            }
            if ((tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, BlockWolfHead.TileEntityWolfHead.class)) != null) {
                tile.setSkullType(stack.func_77960_j());
                tile.setRotation(i1);
            }
        }
        --stack.field_77994_a;
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < skullTypes.length; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @Override
    public int func_77647_b(int p_77647_1_) {
        return p_77647_1_;
    }

    @Override
    public String func_77667_c(ItemStack p_77667_1_) {
        int i = p_77667_1_.func_77960_j();
        if (i < 0 || i >= skullTypes.length) {
            i = 0;
        }
        return super.func_77658_a() + "." + skullTypes[i];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_) {
        if (p_77617_1_ < 0 || p_77617_1_ >= skullTypes.length) {
            p_77617_1_ = 0;
        }
        return this.field_94586_c[p_77617_1_];
    }

    public String func_77653_i(ItemStack p_77653_1_) {
        return super.func_77653_i(p_77653_1_);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_) {
        this.field_94586_c = new IIcon[field_94587_a.length];
        for (int i = 0; i < field_94587_a.length; ++i) {
            this.field_94586_c[i] = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + field_94587_a[i]);
        }
    }

    @Override
    protected String[] getNames() {
        return skullTypes;
    }
}

