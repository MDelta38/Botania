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
package vazkii.botania.common.item.material;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemMod;

public class ItemQuartz
extends ItemMod
implements IElvenItem {
    private static final int SUBTYPES = 7;
    IIcon[] icons;

    public ItemQuartz() {
        this.func_77655_b("quartz");
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        int i;
        int n = i = ConfigHandler.darkQuartzEnabled ? 0 : 1;
        while (i < 7) {
            list.add(new ItemStack(item, 1, i));
            ++i;
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[7];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }

    @Override
    public boolean isElvenItem(ItemStack stack) {
        return stack.func_77960_j() == 5;
    }
}

