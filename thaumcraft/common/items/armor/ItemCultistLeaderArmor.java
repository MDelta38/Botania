/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.client.renderers.models.gear.ModelLeaderArmor;
import thaumcraft.common.Thaumcraft;

public class ItemCultistLeaderArmor
extends ItemArmor
implements IRepairable,
IRunicArmor {
    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconChestOver;
    public IIcon iconLegsOver;
    public IIcon iconBlank;
    ModelBiped model1 = null;
    ModelBiped model2 = null;
    ModelBiped model = null;

    public ItemCultistLeaderArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconHelm = ir.func_94245_a("thaumcraft:cultistplateleaderhelm");
        this.iconChest = ir.func_94245_a("thaumcraft:cultistplateleaderchest");
        this.iconLegs = ir.func_94245_a("thaumcraft:cultistplateleaderlegs");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 0 ? this.iconHelm : (this.field_77881_a == 1 ? this.iconChest : this.iconLegs);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumcraft:textures/models/cultist_leader_armor.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
        if (this.model1 == null) {
            this.model1 = new ModelLeaderArmor(1.0f);
        }
        if (this.model2 == null) {
            this.model2 = new ModelLeaderArmor(0.5f);
        }
        this.model = type == 1 || type == 3 ? this.model1 : this.model2;
        if (this.model != null) {
            this.model.field_78116_c.field_78806_j = armorSlot == 0;
            this.model.field_78114_d.field_78806_j = armorSlot == 0;
            this.model.field_78115_e.field_78806_j = armorSlot == 1 || armorSlot == 2;
            this.model.field_78112_f.field_78806_j = armorSlot == 1;
            this.model.field_78113_g.field_78806_j = armorSlot == 1;
            this.model.field_78123_h.field_78806_j = armorSlot == 2;
            this.model.field_78124_i.field_78806_j = armorSlot == 2;
            this.model.field_78117_n = entityLiving.func_70093_af();
            this.model.field_78093_q = entityLiving.func_70115_ae();
            this.model.field_78091_s = entityLiving.func_70631_g_();
            this.model.field_78118_o = false;
            int n = this.model.field_78120_m = entityLiving.func_70694_bm() != null ? 1 : 0;
            if (entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).func_71057_bx() > 0) {
                EnumAction enumaction = ((EntityPlayer)entityLiving).func_71011_bu().func_77975_n();
                if (enumaction == EnumAction.block) {
                    this.model.field_78120_m = 3;
                } else if (enumaction == EnumAction.bow) {
                    this.model.field_78118_o = true;
                }
            }
        }
        return this.model;
    }
}

