/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class CreativeTabTH
extends CreativeTabs {
    public CreativeTabTH(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_78016_d() {
        return ThaumicHorizons.itemDummyVat;
    }
}

