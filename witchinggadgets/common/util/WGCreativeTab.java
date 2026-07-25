/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import witchinggadgets.common.WGContent;

public class WGCreativeTab
extends CreativeTabs {
    public WGCreativeTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    public Item func_78016_d() {
        return new ItemStack(WGContent.BlockWallMirror).func_77973_b();
    }
}

