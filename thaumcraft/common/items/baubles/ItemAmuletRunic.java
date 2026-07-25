/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.ItemRunic;
import thaumcraft.common.Thaumcraft;

public class ItemAmuletRunic
extends ItemRunic
implements IBauble,
IRunicArmor {
    public IIcon[] icon = new IIcon[2];

    public ItemAmuletRunic() {
        super(8);
        this.field_77777_bU = 1;
        this.canRepair = false;
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:runic_amulet");
        this.icon[1] = ir.func_94245_a("thaumcraft:runic_amulet_emergency");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon[par1];
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return itemstack.func_77960_j() == 0 ? 8 : 7;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        Thaumcraft.instance.runicEventHandler.isDirty = true;
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        Thaumcraft.instance.runicEventHandler.isDirty = true;
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}

