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
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
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
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.renderers.models.gear.ModelHoverHarness;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.items.armor.Hover;

public class ItemHoverHarness
extends ItemArmor
implements IRepairable,
IVisDiscountGear,
IRunicArmor {
    ModelBiped model = null;
    public IIcon icon;
    public IIcon iconLightningRing;

    public ItemHoverHarness(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77656_e(400);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (this.model == null) {
            this.model = new ModelHoverHarness();
        }
        return this.model;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:hoverharness");
        this.iconLightningRing = ir.func_94245_a("thaumcraft:lightningring");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "thaumcraft:textures/models/hoverharness.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(Items.field_151043_k)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return aspect == Aspect.AIR ? 5 : 2;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K) {
            par3EntityPlayer.openGui((Object)Thaumcraft.instance, 17, par2World, MathHelper.func_76128_c((double)par3EntityPlayer.field_70165_t), MathHelper.func_76128_c((double)par3EntityPlayer.field_70163_u), MathHelper.func_76128_c((double)par3EntityPlayer.field_70161_v));
        }
        return par1ItemStack;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!player.field_71075_bZ.field_75098_d) {
            Hover.handleHoverArmor(player, player.field_71071_by.func_70440_f(2));
        }
    }

    public void func_77624_a(ItemStack is, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(is, player, list, par4);
        if (is.func_77942_o() && is.field_77990_d.func_74764_b("jar")) {
            ItemStack jar = ItemStack.func_77949_a((NBTTagCompound)is.field_77990_d.func_74775_l("jar"));
            try {
                AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
                if (aspects != null && aspects.size() > 0) {
                    for (Aspect tag : aspects.getAspectsSorted()) {
                        if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                            list.add(tag.getName() + " x " + aspects.getAmount(tag));
                            continue;
                        }
                        list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
                    }
                }
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(is, player, null) + "%");
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + " (Aer): " + this.getVisDiscount(is, player, Aspect.AIR) + "%");
    }
}

