/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.common.item.brew;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewContainer;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.brew.ItemBrewBase;

public class ItemVial
extends ItemMod
implements IBrewContainer {
    public static IIcon flaskIcon;
    public static IIcon vialIcon;

    public ItemVial() {
        this("vial");
    }

    public ItemVial(String name) {
        this.func_77627_a(true);
        this.func_77655_b(name);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        vialIcon = IconHelper.forName(par1IconRegister, "vial0");
        flaskIcon = IconHelper.forName(par1IconRegister, "flask0");
    }

    public IIcon func_77617_a(int i) {
        return i == 0 ? vialIcon : flaskIcon;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    @Override
    public ItemStack getItemForBrew(Brew brew, ItemStack stack) {
        ItemStack brewStack = new ItemStack(stack.func_77960_j() == 1 ? ModItems.brewFlask : ModItems.brewVial);
        ItemBrewBase.setBrew(brewStack, brew);
        return brewStack;
    }

    @Override
    public int getManaCost(Brew brew, ItemStack stack) {
        return brew.getManaCost() * (stack.func_77960_j() + 1);
    }
}

