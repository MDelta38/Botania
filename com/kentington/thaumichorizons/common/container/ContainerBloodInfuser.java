/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.container.SlotOutput
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.container.SlotRestricted;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.container.SlotOutput;

public class ContainerBloodInfuser
extends Container {
    TileBloodInfuser tile;
    EntityPlayer player;
    AspectList aspectsKnown;

    public ContainerBloodInfuser(EntityPlayer p, TileBloodInfuser tileEntity) {
        int i;
        this.player = p;
        InventoryPlayer inv = p.field_71071_by;
        this.tile = tileEntity;
        this.aspectsKnown = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(p.func_70005_c_());
        this.func_75146_a(new SlotRestricted((IInventory)this.tile, 0, 16, 37, new ItemStack(ThaumicHorizons.itemSyringeHuman)));
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.func_75146_a((Slot)new SlotOutput((IInventory)this.tile, x * 3 + y + 1, 108 + x * 18, 19 + y * 18));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 137 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inv, i, 8 + i * 18, 195));
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return this.tile.func_70300_a(p_75145_1_);
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (p_82846_2_ < 10) {
                if (!this.func_75135_a(itemstack1, 10, 46, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack1, itemstack);
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
                slot.func_82870_a(p_82846_1_, itemstack1);
            } else {
                if (itemstack1.func_77973_b() != ThaumicHorizons.itemSyringeHuman || !this.func_75135_a(itemstack1, 0, 1, false)) {
                    return null;
                }
                slot.func_75220_a(itemstack1, itemstack);
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
            }
        }
        return itemstack;
    }

    public void func_75142_b() {
        super.func_75142_b();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        super.func_75137_b(par1, par2);
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button) {
        if (button < 0) {
            button = -1 - button;
            this.tile.aspectsSelected.add(this.aspectsKnown.getAspectsSorted()[button], 1);
            this.tile.func_70296_d();
            return true;
        }
        if (button > 2) {
            this.tile.aspectsSelected.remove(this.aspectsKnown.getAspectsSorted()[button -= 3], 1);
            this.tile.func_70296_d();
            return true;
        }
        switch (button) {
            case 0: {
                this.tile.mode = 0;
                this.tile.func_70296_d();
                return true;
            }
            case 1: {
                this.tile.mode = 1;
                this.tile.func_70296_d();
                return true;
            }
            case 2: {
                this.tile.mode = 2;
                this.tile.func_70296_d();
                return true;
            }
        }
        return false;
    }
}

