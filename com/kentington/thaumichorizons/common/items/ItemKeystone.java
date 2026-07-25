/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemKeystone
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemKeystone() {
        this.func_77637_a(ThaumicHorizons.tabTH);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:keystone");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        if (par1ItemStack.func_77978_p() != null && par1ItemStack.func_77978_p().func_74762_e("dimension") != 0) {
            return "item.keystoneTH";
        }
        return "item.keystoneBlank";
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77978_p() != null && par1ItemStack.func_77978_p().func_74762_e("dimension") != 0) {
            par3List.add(PocketPlaneData.planes.get((int)par1ItemStack.func_77978_p().func_74762_e((String)"dimension")).name);
        }
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int p_82790_2_) {
        if (par1ItemStack.func_77978_p() != null) {
            return PocketPlaneData.planes.get((int)par1ItemStack.func_77978_p().func_74762_e((String)"dimension")).color;
        }
        return 0xFFFFFF;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer p) {
        if (stack.func_77978_p() == null && p.field_71093_bK == ThaumicHorizons.dimensionPocketId) {
            ItemStack newStack = new ItemStack(ThaumicHorizons.itemKeystone);
            newStack.field_77990_d = new NBTTagCompound();
            newStack.field_77990_d.func_74768_a("dimension", ((int)p.field_70161_v + 128) / 256);
            return newStack;
        }
        return stack;
    }
}

