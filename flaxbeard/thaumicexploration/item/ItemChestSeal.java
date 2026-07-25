/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemChestSeal
extends Item {
    public static final String[] itemNames = new String[]{"Pale", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Dark"};

    public ItemChestSeal(int par1) {
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77625_d(64);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.func_77960_j() <= 15) {
            int j = 15 - par1ItemStack.func_77960_j();
            if (j > -1 && j < 16) {
                Color c = new Color(EntitySheep.field_70898_d[j][0], EntitySheep.field_70898_d[j][1], EntitySheep.field_70898_d[j][2]);
                return c.getRGB() & 0xFFFFFF;
            }
            Color c = new Color(EntitySheep.field_70898_d[1][0], EntitySheep.field_70898_d[1][1], EntitySheep.field_70898_d[1][2]);
            return c.getRGB() & 0xFFFFFF;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item itemID, CreativeTabs tab, List itemList) {
        for (int i = 0; i < itemNames.length; ++i) {
            itemList.add(new ItemStack(itemID, 1, i));
        }
    }

    public String func_77667_c(ItemStack item) {
        if (item.func_77960_j() <= 15) {
            return this.func_77658_a() + ":" + itemNames[15 - item.func_77960_j()];
        }
        return "";
    }
}

