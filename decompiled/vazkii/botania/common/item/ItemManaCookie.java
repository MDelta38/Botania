/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.BotaniaCreativeTab;

public class ItemManaCookie
extends ItemFood {
    private IIcon totalBiscuitIcon;

    public ItemManaCookie() {
        super(0, 0.1f, false);
        this.func_77844_a(Potion.field_76443_y.field_76415_H, 1, 0, 1.0f);
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b("manaCookie");
    }

    protected void func_77849_c(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
        super.func_77849_c(p_77849_1_, p_77849_2_, p_77849_3_);
        p_77849_3_.func_71064_a((StatBase)ModAchievements.manaCookieEat, 1);
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item.", "item.botania:");
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public IIcon func_77650_f(ItemStack stack) {
        return stack.func_82833_r().toLowerCase().equals("totalbiscuit") ? this.totalBiscuitIcon : super.func_77650_f(stack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
        this.totalBiscuitIcon = IconHelper.forName(par1IconRegister, "totalBiscuit");
    }
}

