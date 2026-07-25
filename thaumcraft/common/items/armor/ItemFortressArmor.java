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
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.client.renderers.models.gear.ModelFortressArmor;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemFortressArmor
extends ItemArmor
implements IRepairable,
IRunicArmor,
ISpecialArmor,
IGoggles,
IRevealer {
    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    ModelBiped model1 = null;
    ModelBiped model2 = null;
    ModelBiped model = null;

    public ItemFortressArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconHelm = ir.func_94245_a("thaumcraft:thaumiumfortresshelm");
        this.iconChest = ir.func_94245_a("thaumcraft:thaumiumfortresschest");
        this.iconLegs = ir.func_94245_a("thaumcraft:thaumiumfortresslegs");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 0 ? this.iconHelm : (this.field_77881_a == 1 ? this.iconChest : this.iconLegs);
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
        if (this.model1 == null) {
            this.model1 = new ModelFortressArmor(1.0f);
        }
        if (this.model2 == null) {
            this.model2 = new ModelFortressArmor(0.5f);
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

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumcraft:textures/models/fortress_armor.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("goggles")) {
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemGoggles.name"));
        }
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("mask")) {
            list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)("item.HelmetFortress.mask." + stack.field_77990_d.func_74762_e("mask"))));
        }
        super.func_77624_a(stack, player, list, par4);
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int priority = 0;
        double ratio = (double)this.field_77879_b / 25.0;
        if (source.func_82725_o()) {
            priority = 1;
            ratio = (double)this.field_77879_b / 35.0;
        } else if (source.func_76347_k() || source.func_94541_c()) {
            priority = 1;
            ratio = (double)this.field_77879_b / 20.0;
        } else if (source.func_76363_c()) {
            priority = 0;
            ratio = 0.0;
        }
        if (player instanceof EntityPlayer) {
            double set = 0.875;
            for (int a = 1; a < 4; ++a) {
                ItemStack piece = ((EntityPlayer)player).field_71071_by.field_70460_b[a];
                if (piece == null || !(piece.func_77973_b() instanceof ItemFortressArmor)) continue;
                set += 0.125;
                if (!piece.func_77942_o() || !piece.field_77990_d.func_74764_b("mask")) continue;
                set += 0.05;
            }
            ratio *= set;
        }
        return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77960_j());
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return this.field_77879_b;
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        if (source != DamageSource.field_76379_h) {
            stack.func_77972_a(damage, entity);
        }
    }

    @Override
    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("goggles");
    }

    @Override
    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("goggles");
    }
}

