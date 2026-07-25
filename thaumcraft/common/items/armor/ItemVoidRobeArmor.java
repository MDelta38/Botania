/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.client.renderers.models.gear.ModelRobe;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemVoidRobeArmor
extends ItemArmor
implements IRepairable,
IRunicArmor,
IVisDiscountGear,
IGoggles,
IRevealer,
ISpecialArmor,
IWarpingGear {
    public IIcon iconHelm;
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconChestOver;
    public IIcon iconLegsOver;
    public IIcon iconBlank;
    ModelBiped model1 = null;
    ModelBiped model2 = null;
    ModelBiped model = null;

    public ItemVoidRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconHelm = ir.func_94245_a("thaumcraft:voidrobehelm");
        this.iconBlank = ir.func_94245_a("thaumcraft:blank");
        this.iconChest = ir.func_94245_a("thaumcraft:voidrobechestover");
        this.iconLegs = ir.func_94245_a("thaumcraft:voidrobelegsover");
        this.iconChestOver = ir.func_94245_a("thaumcraft:voidrobechest");
        this.iconLegsOver = ir.func_94245_a("thaumcraft:voidrobelegs");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return type == null ? "thaumcraft:textures/models/void_robe_armor_overlay.png" : "thaumcraft:textures/models/void_robe_armor.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
        super.func_77624_a(stack, player, list, par4);
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
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @Override
    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
        return type == 0;
    }

    @Override
    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        int type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
        return type == 0;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 5;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        int type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
        if (this.model1 == null) {
            this.model1 = new ModelRobe(1.0f);
        }
        if (this.model2 == null) {
            this.model2 = new ModelRobe(0.5f);
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

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public boolean func_82816_b_(ItemStack par1ItemStack) {
        return true;
    }

    public IIcon func_77618_c(int par1, int par2) {
        return par2 == 0 ? (this.field_77881_a == 1 ? this.iconChest : (this.field_77881_a == 2 ? this.iconLegs : this.iconHelm)) : (this.field_77881_a == 1 ? this.iconChestOver : (this.field_77881_a == 2 ? this.iconLegsOver : this.iconBlank));
    }

    public int func_82814_b(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            return 6961280;
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        return nbttagcompound1 == null ? 6961280 : (nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : 6961280);
    }

    public void func_82815_c(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound != null && (nbttagcompound1 = nbttagcompound.func_74775_l("display")).func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
        }
    }

    public void func_82813_b(ItemStack par1ItemStack, int par2) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            par1ItemStack.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        if (!nbttagcompound.func_74764_b("display")) {
            nbttagcompound.func_74782_a("display", (NBTBase)nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", par2);
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int priority = 0;
        double ratio = (double)this.field_77879_b / 25.0;
        if (source.func_82725_o()) {
            priority = 1;
            ratio = (double)this.field_77879_b / 35.0;
        } else if (source.func_76363_c()) {
            priority = 0;
            ratio = 0.0;
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

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K && world.func_147439_a(x, y, z) == Blocks.field_150383_bp && world.func_72805_g(x, y, z) > 0) {
            this.func_82815_c(stack);
            world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 1, 2);
            world.func_147453_f(x, y, z, (Block)Blocks.field_150383_bp);
            return true;
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 2;
    }
}

