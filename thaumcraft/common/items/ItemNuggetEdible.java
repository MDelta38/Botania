/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;

public class ItemNuggetEdible
extends ItemFood {
    public final int field_77855_a;
    public final String iconName;
    public IIcon icon;

    public ItemNuggetEdible(String iconName) {
        super(1, 0.3f, false);
        this.field_77855_a = 10;
        this.iconName = iconName;
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return this.field_77855_a;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:" + this.iconName);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.icon;
    }
}

