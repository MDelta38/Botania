/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemWandCastingDisposable
extends ItemWandCasting {
    public ItemStack wand;

    public ItemWandCastingDisposable() {
        this.field_77777_bU = 1;
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77637_a(ThaumicHorizons.tabTH);
        ItemStack w1 = new ItemStack((Item)this);
        this.setCap(w1, ThaumicHorizons.CAP_CRYSTAL);
        this.setRod(w1, ThaumicHorizons.ROD_CRYSTAL);
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            this.storeVis(w1, aspect, 25000);
        }
        this.wand = w1;
    }

    public int getMaxVis(ItemStack stack) {
        StackTraceElement[] above;
        for (StackTraceElement el : above = Thread.currentThread().getStackTrace()) {
            if (!el.getMethodName().equals("onWornTick") && !el.getMethodName().equals("updateEntity")) continue;
            return 0;
        }
        return 25000;
    }

    public AspectList getAspectsWithRoom(ItemStack wandstack) {
        return new AspectList();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(this.wand);
    }
}

