/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item;

import java.awt.Color;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.ItemMod;

public class Item16Colors
extends ItemMod {
    public Item16Colors(String name) {
        this.func_77627_a(true);
        this.func_77655_b(name);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.func_77960_j() >= EntitySheep.field_70898_d.length) {
            return 0xFFFFFF;
        }
        float[] color = EntitySheep.field_70898_d[par1ItemStack.func_77960_j()];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public void func_150895_a(Item item, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(item, 1, i));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }
}

