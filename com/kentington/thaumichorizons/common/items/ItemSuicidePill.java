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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSuicidePill
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemSuicidePill() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:syringeEmpty");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.suicidePill";
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World world, EntityPlayer p) {
        p.func_70097_a(new DamageSource("suicide").func_76359_i(), 1000.0f);
        return p_77659_1_;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77960_j() == 0) {
            par3List.add("For testing purposes only");
            par3List.add("FDA approval pending");
        }
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
}

