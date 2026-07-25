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
 *  net.minecraft.item.ItemEgg
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityEggIncubated;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEggIncubated
extends ItemEgg {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemEggIncubated() {
        this.field_77777_bU = 16;
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (!p_77659_3_.field_71075_bZ.field_75098_d) {
            --p_77659_1_.field_77994_a;
        }
        p_77659_2_.func_72956_a((Entity)p_77659_3_, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!p_77659_2_.field_72995_K) {
            p_77659_2_.func_72838_d((Entity)new EntityEggIncubated(p_77659_2_, (EntityLivingBase)p_77659_3_));
        }
        return p_77659_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:eggincubated");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.eggIncubated";
    }
}

