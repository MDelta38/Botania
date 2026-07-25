/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.tiles.TileVat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.wands.IWandable;

public class TileVatMatrix
extends TileVisNode
implements IWandable,
IAspectContainer {
    public TileVat getVat() {
        if (this.field_145850_b == null) {
            return null;
        }
        TileEntity t = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        return t != null && t instanceof TileVat ? (TileVat)t : null;
    }

    @Override
    public AspectList getAspects() {
        TileVat t = this.getVat();
        if (t != null) {
            return t.getAspects();
        }
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        this.onWandRightClick(world, wandstack, player);
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        TileVat t = this.getVat();
        if (t != null && (t.isValidInfusionTarget() && t.mode == 0 || t.mode == 4)) {
            t.startInfusion(player);
        }
        return wandstack;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    @Override
    public int getRange() {
        return 8;
    }

    @Override
    public boolean isSource() {
        return false;
    }
}

