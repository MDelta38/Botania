/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;

public class ItemCultistBoots
extends ItemArmor
implements IRepairable,
IRunicArmor,
IWarpingGear,
IVisDiscountGear {
    public IIcon icon;

    public ItemCultistBoots(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:cultistboots");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumcraft:textures/models/cultistboots.png";
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 1;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 1;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
    }
}

