/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;

public class ItemSyringeBloodSample
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemSyringeBloodSample() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:syringeBlood");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77653_i(ItemStack stack) {
        if (stack.func_77942_o()) {
            return StatCollector.func_74838_a((String)"item.syringeSample.name") + ": " + stack.func_77978_p().func_74779_i("critterName");
        }
        return StatCollector.func_74838_a((String)"item.syringeSample.name") + ": INVALID";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
    }

    public void func_77624_a(ItemStack sample, EntityPlayer player, List list, boolean par4) {
        NBTTagCompound tlist;
        list.add("Essentia required to clone:");
        AspectList asp = new AspectList().add(Aspect.LIFE, 4);
        if (sample.func_77942_o() && sample.field_77990_d.func_74775_l("critter") != null && sample.field_77990_d.func_74775_l("critter").func_74775_l("CreatureInfusion") != null && (tlist = sample.field_77990_d.func_74775_l("critter").func_74775_l("CreatureInfusion").func_74775_l("InfusionCosts")) != null && tlist.func_74764_b("Aspects")) {
            NBTTagList aspex = tlist.func_150295_c("Aspects", 10);
            for (int j = 0; j < aspex.func_74745_c(); ++j) {
                NBTTagCompound rs = aspex.func_150305_b(j);
                if (!rs.func_74764_b("key")) continue;
                asp.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
            }
        }
        for (Aspect tag : asp.getAspectsSorted()) {
            if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                list.add(tag.getName() + " x" + asp.getAmount(tag));
                continue;
            }
            list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
        }
    }
}

