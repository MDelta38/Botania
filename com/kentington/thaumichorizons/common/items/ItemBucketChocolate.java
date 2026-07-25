/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBucketMilk
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBucketChocolate
extends ItemBucketMilk {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemBucketChocolate() {
        this.func_77625_d(1);
        this.func_77642_a(Items.field_151133_ar);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        if (!p_77654_3_.field_71075_bZ.field_75098_d) {
            --p_77654_1_.field_77994_a;
        }
        if (!p_77654_2_.field_72995_K) {
            p_77654_3_.curePotionEffects(new ItemStack(Items.field_151117_aB));
        }
        return p_77654_1_.field_77994_a <= 0 ? new ItemStack(Items.field_151133_ar) : p_77654_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:bucket_chocolatemilk");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.chocolateMilk";
    }
}

