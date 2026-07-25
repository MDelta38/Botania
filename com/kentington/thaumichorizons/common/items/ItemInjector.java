/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBlastPhial;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;

public class ItemInjector
extends ItemBow
implements IRepairable {
    public ItemInjector() {
        this.field_77777_bU = 1;
        this.func_77656_e(1000);
        this.func_77637_a(ThaumicHorizons.tabTH);
        this.func_111206_d("thaumichorizons:injector");
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.injector";
    }

    public int func_77619_b() {
        return 3;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_) {
        return EnumAction.none;
    }

    public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        int j = this.func_77626_a(null) - p_77615_4_;
        if (this.getAmmo(p_77615_1_, 0) != null) {
            float f = (float)j / 30.0f;
            if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                p_77615_1_.field_77990_d.func_74768_a("usetime", 0);
                return;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            Object projectile = null;
            if (this.getAmmo(p_77615_1_, 0).func_77960_j() == 0) {
                int l;
                int k;
                EntitySyringe entityarrow = new EntitySyringe(p_77615_2_, (EntityLivingBase)p_77615_3_, f * 2.0f, this.getAmmo((ItemStack)p_77615_1_, (int)0).field_77990_d);
                if (f == 1.0f) {
                    entityarrow.setIsCritical(true);
                }
                if ((k = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)p_77615_1_)) > 0) {
                    entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5 + 0.5);
                }
                if ((l = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)p_77615_1_)) > 0) {
                    entityarrow.setKnockbackStrength(l);
                }
                if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)p_77615_1_) > 0) {
                    entityarrow.func_70015_d(100);
                }
                projectile = entityarrow;
            } else {
                projectile = new EntityBlastPhial(p_77615_2_, (EntityLivingBase)p_77615_3_, f * 2.0f, this.getAmmo(p_77615_1_, 0));
            }
            p_77615_1_.func_77972_a(1, (EntityLivingBase)p_77615_3_);
            p_77615_2_.func_72956_a((Entity)p_77615_3_, "random.bow", 1.0f, 1.0f / (field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            if (!p_77615_2_.field_72995_K) {
                p_77615_2_.func_72838_d((Entity)projectile);
                this.rotateAmmo(p_77615_1_);
            }
            p_77615_1_.field_77990_d.func_74768_a("usetime", 0);
            p_77615_1_.field_77990_d.func_74768_a("rotationTarget", p_77615_1_.field_77990_d.func_74762_e("rotationTarget") + 90);
            if (p_77615_1_.field_77990_d.func_74762_e("rotationTarget") > 360) {
                p_77615_1_.field_77990_d.func_74768_a("rotationTarget", p_77615_1_.field_77990_d.func_74762_e("rotationTarget") - 360);
            }
        }
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (p_77659_3_.func_70093_af()) {
            p_77659_3_.openGui((Object)ThaumicHorizons.instance, 6, p_77659_2_, MathHelper.func_76128_c((double)p_77659_3_.field_70165_t), MathHelper.func_76128_c((double)p_77659_3_.field_70163_u), MathHelper.func_76128_c((double)p_77659_3_.field_70161_v));
        } else if (this.getAmmo(p_77659_1_, 0) == null) {
            this.rotateAmmo(p_77659_1_);
            p_77659_2_.func_72908_a(p_77659_3_.field_70165_t, p_77659_3_.field_70163_u + (double)p_77659_3_.func_70047_e(), p_77659_3_.field_70161_v, "random.click", 1.0f, 1.0f);
        } else {
            p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        }
        return p_77659_1_;
    }

    public ItemStack getAmmo(ItemStack stack, int slot) {
        if (stack.field_77990_d == null) {
            return null;
        }
        NBTTagList ammo = stack.field_77990_d.func_150295_c("ammo", 10);
        return ItemStack.func_77949_a((NBTTagCompound)ammo.func_150305_b(slot));
    }

    void rotateAmmo(ItemStack stack) {
        if (stack.field_77990_d == null) {
            return;
        }
        NBTTagList ammo = stack.field_77990_d.func_150295_c("ammo", 10);
        NBTTagList newAmmo = new NBTTagList();
        for (int i = 1; i < 7; ++i) {
            if (ammo.func_150305_b(i) != null) {
                newAmmo.func_74742_a(ammo.func_150305_b(i).func_74737_b());
                continue;
            }
            newAmmo.func_74742_a((NBTBase)new NBTTagCompound());
        }
        newAmmo.func_74742_a((NBTBase)new NBTTagCompound());
        stack.field_77990_d.func_74782_a("ammo", (NBTBase)newAmmo);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            stack.field_77990_d.func_74768_a("usetime", stack.field_77990_d.func_74762_e("usetime") + 1);
            stack.field_77990_d.func_74768_a("rotationTarget", stack.field_77990_d.func_74762_e("rotation"));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_94599_c(int p_94599_1_) {
        return null;
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return 72000;
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        return p_77654_1_;
    }
}

