/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.common.item.material;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.IPickupAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.item.ItemMod;

public class ItemRune
extends ItemMod
implements IFlowerComponent,
IPickupAchievement {
    IIcon[] icons;

    public ItemRune() {
        this.func_77627_a(true);
        this.func_77655_b("rune");
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[16];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }

    @Override
    public boolean canFit(ItemStack stack, IInventory apothecary) {
        return true;
    }

    @Override
    public int getParticleColor(ItemStack stack) {
        return 0xA8A8A8;
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return ModAchievements.runePickup;
    }
}

