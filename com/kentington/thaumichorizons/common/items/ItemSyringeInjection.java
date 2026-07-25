/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.google.common.collect.HashMultimap;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBlastPhial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSyringeInjection
extends ItemPotion {
    private IIcon field_94590_d;
    private IIcon field_94591_c;
    private IIcon field_94592_ct;

    public ItemSyringeInjection() {
        this.func_77627_a(true);
        this.func_77625_d(1);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return 8;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    public String func_77653_i(ItemStack p_77653_1_) {
        return StatCollector.func_74838_a((String)("item.injection." + p_77653_1_.func_77960_j() + ".name"));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.field_94590_d = ir.func_94245_a("thaumichorizons:phialBlood");
        this.field_94591_c = ir.func_94245_a("thaumichorizons:phialBlood");
        this.field_94592_ct = ir.func_94245_a("thaumichorizons:phialBlood");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_) {
        return ItemSyringeInjection.func_77831_g((int)p_77617_1_) ? this.field_94591_c : this.field_94590_d;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_) {
        return p_77618_2_ == 0 ? this.field_94592_ct : super.func_77618_c(p_77618_1_, p_77618_2_);
    }

    @SideOnly(value=Side.CLIENT)
    public static IIcon func_94589_d(String p_94589_0_) {
        return p_94589_0_.equals("bottle_drinkable") ? ((ItemSyringeInjection)ThaumicHorizons.itemSyringeInjection).field_94590_d : (p_94589_0_.equals("bottle_splash") ? ((ItemSyringeInjection)ThaumicHorizons.itemSyringeInjection).field_94591_c : (p_94589_0_.equals("overlay") ? ((ItemSyringeInjection)ThaumicHorizons.itemSyringeInjection).field_94592_ct : null));
    }

    public boolean isPhial(int p_77831_0_) {
        return p_77831_0_ != 0;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (this.isPhial(p_77659_1_.func_77960_j())) {
            if (!p_77659_3_.field_71075_bZ.field_75098_d) {
                --p_77659_1_.field_77994_a;
            }
            p_77659_2_.func_72956_a((Entity)p_77659_3_, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
            if (!p_77659_2_.field_72995_K) {
                p_77659_2_.func_72838_d((Entity)new EntityBlastPhial(p_77659_2_, (EntityLivingBase)p_77659_3_, 0.5f, p_77659_1_));
            }
            return p_77659_1_;
        }
        p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        return p_77659_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        List list1 = Items.field_151068_bn.func_77832_l(p_77624_1_);
        HashMultimap hashmultimap = HashMultimap.create();
        if (list1 != null && !list1.isEmpty()) {
            for (PotionEffect potioneffect : list1) {
                String s1 = StatCollector.func_74838_a((String)potioneffect.func_76453_d()).trim();
                Potion potion = Potion.field_76425_a[potioneffect.func_76456_a()];
                Map map = potion.func_111186_k();
                if (map != null && map.size() > 0) {
                    for (Map.Entry entry : map.entrySet()) {
                        AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.func_111166_b(), potion.func_111183_a(potioneffect.func_76458_c(), attributemodifier), attributemodifier.func_111169_c());
                        hashmultimap.put((Object)((IAttribute)entry.getKey()).func_111108_a(), (Object)attributemodifier1);
                    }
                }
                if (potioneffect.func_76458_c() > 0) {
                    s1 = s1 + " " + StatCollector.func_74838_a((String)("potion.potency." + potioneffect.func_76458_c())).trim();
                }
                if (potioneffect.func_76459_b() > 20) {
                    s1 = s1 + " (" + Potion.func_76389_a((PotionEffect)potioneffect) + ")";
                }
                if (potion.func_76398_f()) {
                    p_77624_3_.add(EnumChatFormatting.RED + s1);
                    continue;
                }
                p_77624_3_.add(EnumChatFormatting.GRAY + s1);
            }
        } else {
            String s = StatCollector.func_74838_a((String)"potion.empty").trim();
            p_77624_3_.add(EnumChatFormatting.GRAY + s);
        }
        if (!hashmultimap.isEmpty()) {
            p_77624_3_.add("");
            p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"potion.effects.whenDrank"));
            for (Map.Entry entry1 : hashmultimap.entries()) {
                AttributeModifier attributemodifier2 = (AttributeModifier)entry1.getValue();
                double d0 = attributemodifier2.func_111164_d();
                double d1 = attributemodifier2.func_111169_c() != 1 && attributemodifier2.func_111169_c() != 2 ? attributemodifier2.func_111164_d() : attributemodifier2.func_111164_d() * 100.0;
                if (d0 > 0.0) {
                    p_77624_3_.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a((String)("attribute.modifier.plus." + attributemodifier2.func_111169_c()), (Object[])new Object[]{ItemStack.field_111284_a.format(d1), StatCollector.func_74838_a((String)("attribute.name." + (String)entry1.getKey()))}));
                    continue;
                }
                if (!(d0 < 0.0)) continue;
                p_77624_3_.add(EnumChatFormatting.RED + StatCollector.func_74837_a((String)("attribute.modifier.take." + attributemodifier2.func_111169_c()), (Object[])new Object[]{ItemStack.field_111284_a.format(d1 *= -1.0), StatCollector.func_74838_a((String)("attribute.name." + (String)entry1.getKey()))}));
            }
        }
    }

    public boolean func_77644_a(ItemStack is, EntityLivingBase target, EntityLivingBase hitter) {
        List list;
        if (!target.field_70170_p.field_72995_K && (list = this.func_77832_l(is)) != null) {
            for (PotionEffect potioneffect : list) {
                target.func_70690_d(new PotionEffect(potioneffect));
            }
        }
        --is.field_77994_a;
        return super.func_77644_a(is, target, hitter);
    }

    public int func_82790_a(ItemStack stack, int p_82790_2_) {
        if (stack.func_77973_b() == ThaumicHorizons.itemSyringeInjection) {
            if (stack.func_77942_o()) {
                return stack.func_77978_p().func_74762_e("color");
            }
        } else if (stack.func_77973_b() != ThaumicHorizons.itemSyringeEmpty) {
            return Color.RED.getRGB();
        }
        return 0xFFFFFF;
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        List list;
        if (!p_77654_3_.field_71075_bZ.field_75098_d) {
            --p_77654_1_.field_77994_a;
        }
        if (!p_77654_2_.field_72995_K && (list = this.func_77832_l(p_77654_1_)) != null) {
            for (PotionEffect potioneffect : list) {
                p_77654_3_.func_70690_d(new PotionEffect(potioneffect));
            }
        }
        if (p_77654_1_.field_77994_a <= 0) {
            return null;
        }
        return p_77654_1_;
    }
}

