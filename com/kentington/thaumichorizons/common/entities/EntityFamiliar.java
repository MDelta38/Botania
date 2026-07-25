/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.baubles.ItemAmuletVis
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.entities;

import baubles.api.BaublesApi;
import java.util.List;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;

public class EntityFamiliar
extends EntityOcelot {
    public EntityFamiliar(World p_i1688_1_) {
        super(p_i1688_1_);
    }

    public String func_70005_c_() {
        return this.func_94056_bM() ? this.func_94057_bL() : (this.func_70909_n() ? StatCollector.func_74838_a((String)"entity.ThaumicHorizons.Familiar.name") : super.func_70005_c_());
    }

    public void func_70629_bd() {
        super.func_70629_bd();
        if (this.field_70173_aa % 10 == 0) {
            List players = this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 5.0), (double)(this.field_70163_u - 5.0), (double)(this.field_70161_v - 5.0), (double)(this.field_70165_t + 5.0), (double)(this.field_70163_u + 5.0), (double)(this.field_70161_v + 5.0)));
            for (EntityPlayer player : players) {
                if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
                    ItemWandCasting wand = (ItemWandCasting)player.func_70694_bm().func_77973_b();
                    AspectList al = wand.getAspectsWithRoom(player.func_70694_bm());
                    Aspect[] aspectArray = al.getAspects();
                    int n = aspectArray.length;
                    for (int i = 0; i < n; ++i) {
                        Aspect aspect = aspectArray[i];
                        if (aspect == null) continue;
                        wand.storeVis(player.func_70694_bm(), aspect, this.getVis(player.func_70694_bm(), aspect) + 1);
                    }
                }
                if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(0) == null || BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(0).func_77973_b() != ConfigItems.itemAmuletVis) continue;
                AspectList al = ((ItemAmuletVis)ConfigItems.itemAmuletVis).getAspectsWithRoom(BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(0));
                for (Aspect aspect : al.getAspects()) {
                    if (aspect == null) continue;
                    ((ItemAmuletVis)ConfigItems.itemAmuletVis).addRealVis(BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(0), aspect, 1, true);
                }
            }
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        byte b0 = this.field_70180_af.func_75683_a(16);
        this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 4)));
    }

    public boolean func_70909_n() {
        return true;
    }

    public int getVis(ItemStack is, Aspect aspect) {
        int out = 0;
        if (is.func_77942_o() && is.field_77990_d.func_74764_b(aspect.getTag())) {
            out = is.field_77990_d.func_74762_e(aspect.getTag());
        }
        return out;
    }
}

