/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemThaumiumArmor
extends ItemArmor
implements IRepairable,
IRunicArmor {
    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconBoots;

    public ItemThaumiumArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconHelm = ir.func_94245_a("thaumcraft:thaumiumhelm");
        this.iconChest = ir.func_94245_a("thaumcraft:thaumiumchest");
        this.iconLegs = ir.func_94245_a("thaumcraft:thaumiumlegs");
        this.iconBoots = ir.func_94245_a("thaumcraft:thaumiumboots");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 0 ? this.iconHelm : (this.field_77881_a == 1 ? this.iconChest : (this.field_77881_a == 2 ? this.iconLegs : this.iconBoots));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.func_77973_b() == ConfigItems.itemHelmetThaumium || stack.func_77973_b() == ConfigItems.itemChestThaumium || stack.func_77973_b() == ConfigItems.itemBootsThaumium) {
            return "thaumcraft:textures/models/thaumium_1.png";
        }
        if (stack.func_77973_b() == ConfigItems.itemLegsThaumium) {
            return "thaumcraft:textures/models/thaumium_2.png";
        }
        return "thaumcraft:textures/models/thaumium_1.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }
}

