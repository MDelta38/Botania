/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemVoidArmor
extends ItemArmor
implements IRepairable,
IRunicArmor,
IWarpingGear {
    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconBoots;

    public ItemVoidArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconHelm = ir.func_94245_a("thaumcraft:voidhelm");
        this.iconChest = ir.func_94245_a("thaumcraft:voidchest");
        this.iconLegs = ir.func_94245_a("thaumcraft:voidlegs");
        this.iconBoots = ir.func_94245_a("thaumcraft:voidboots");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 0 ? this.iconHelm : (this.field_77881_a == 1 ? this.iconChest : (this.field_77881_a == 2 ? this.iconLegs : this.iconBoots));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.func_77973_b() == ConfigItems.itemHelmetVoid || stack.func_77973_b() == ConfigItems.itemChestVoid || stack.func_77973_b() == ConfigItems.itemBootsVoid) {
            return "thaumcraft:textures/models/void_1.png";
        }
        if (stack.func_77973_b() == ConfigItems.itemLegsVoid) {
            return "thaumcraft:textures/models/void_2.png";
        }
        return "thaumcraft:textures/models/void_1.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 16)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
        if (!world.field_72995_K && stack.func_77951_h() && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
            stack.func_77972_a(-1, (EntityLivingBase)entity);
        }
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        super.onArmorTick(world, player, armor);
        if (!world.field_72995_K && armor.func_77960_j() > 0 && player.field_70173_aa % 20 == 0) {
            armor.func_77972_a(-1, (EntityLivingBase)player);
        }
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 1;
    }
}

