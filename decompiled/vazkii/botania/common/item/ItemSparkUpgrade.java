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
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemSparkUpgrade
extends ItemMod {
    private static final int VARIANTS = 4;
    public static IIcon[] worldIcons;
    IIcon[] invIcons;

    public ItemSparkUpgrade() {
        this.func_77655_b("sparkUpgrade");
        this.func_77627_a(true);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        worldIcons = new IIcon[4];
        this.invIcons = new IIcon[4];
        for (int i = 0; i < 4; ++i) {
            ItemSparkUpgrade.worldIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, "L" + i);
            this.invIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int meta) {
        return this.invIcons[Math.min(this.invIcons.length - 1, meta)];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }
}

