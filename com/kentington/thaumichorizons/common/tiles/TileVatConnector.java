/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileVatConnector
extends TileVatSlave
implements IEssentiaTransport,
ISidedInventory {
    public int func_70302_i_() {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_70302_i_();
        }
        return 0;
    }

    public ItemStack func_70301_a(int p_70301_1_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_70301_a(p_70301_1_);
        }
        return null;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_70298_a(p_70298_1_, p_70298_2_);
        }
        return null;
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        return null;
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            boss.func_70299_a(p_70299_1_, p_70299_2_);
        }
    }

    public String func_145825_b() {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_145825_b();
        }
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_70297_j_();
        }
        return 0;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_94041_b(p_94041_1_, p_94041_2_);
        }
        return false;
    }

    public int[] func_94128_d(int p_94128_1_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_94128_d(p_94128_1_);
        }
        return null;
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_102007_a(p_102007_1_, p_102007_2_, p_102007_3_);
        }
        return false;
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.func_102008_b(p_102008_1_, p_102008_2_, p_102008_3_);
        }
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getSuctionType(face);
        }
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getSuctionAmount(face);
        }
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.addEssentia(aspect, amount, face);
        }
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }
}

