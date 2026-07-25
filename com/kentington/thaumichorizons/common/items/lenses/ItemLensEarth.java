/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.items.lenses;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemLensEarth
extends Item
implements ILens {
    IIcon icon;

    public ItemLensEarth() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @Override
    public String lensName() {
        return "LensEarth";
    }

    @Override
    public void handleRender(Minecraft mc, float partialTicks) {
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.LensEarth";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:lensearth");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @Override
    public void handleRemoval(EntityPlayer p) {
    }
}

