/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  thaumcraft.common.container.ContainerDummy
 *  thaumcraft.common.container.SlotCraftingArcaneWorkbench
 *  thaumcraft.common.container.SlotLimitedByWand
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.container.InventoryFingers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.common.container.ContainerDummy;
import thaumcraft.common.container.SlotCraftingArcaneWorkbench;
import thaumcraft.common.container.SlotLimitedByWand;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class ContainerFingers
extends Container {
    private InventoryPlayer ip;
    public InventoryFingers tileEntity;

    public ContainerFingers(InventoryPlayer par1InventoryPlayer) {
        int var7;
        int var6;
        this.ip = par1InventoryPlayer;
        this.tileEntity = new InventoryFingers();
        this.tileEntity.eventHandler = this;
        this.func_75146_a((Slot)new SlotCraftingArcaneWorkbench(par1InventoryPlayer.field_70458_d, (IInventory)this.tileEntity, (IInventory)this.tileEntity, 9, 160, 64));
        this.func_75146_a((Slot)new SlotLimitedByWand((IInventory)this.tileEntity, 10, 160, 24));
        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 3; ++var7) {
                this.func_75146_a(new Slot((IInventory)this.tileEntity, var7 + var6 * 3, 40 + var7 * 24, 40 + var6 * 24));
            }
        }
        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 9; ++var7) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, var7 + var6 * 9 + 9, 16 + var7 * 18, 151 + var6 * 18));
            }
        }
        for (var6 = 0; var6 < 9; ++var6) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, var6, 16 + var6 * 18, 209));
        }
        this.func_75130_a(this.tileEntity);
    }

    public void func_75130_a(IInventory par1IInventory) {
        ItemWandCasting wand;
        InventoryCrafting ic = new InventoryCrafting((Container)new ContainerDummy(), 3, 3);
        for (int a = 0; a < 9; ++a) {
            ic.func_70299_a(a, this.tileEntity.func_70301_a(a));
        }
        this.tileEntity.setInventorySlotContentsSoftly(9, CraftingManager.func_77594_a().func_82787_a(ic, this.ip.field_70458_d.field_70170_p));
        if (this.tileEntity.func_70301_a(9) == null && this.tileEntity.func_70301_a(10) != null && this.tileEntity.func_70301_a(10).func_77973_b() instanceof ItemWandCasting && (wand = (ItemWandCasting)this.tileEntity.func_70301_a(10).func_77973_b()).consumeAllVisCrafting(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d), false)) {
            this.tileEntity.setInventorySlotContentsSoftly(9, ThaumcraftCraftingManager.findMatchingArcaneRecipe((IInventory)this.tileEntity, (EntityPlayer)this.ip.field_70458_d));
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.ip.field_70458_d.field_70170_p.field_72995_K) {
            this.tileEntity.eventHandler = null;
        }
        if (!this.ip.field_70458_d.field_70170_p.field_72995_K) {
            for (int i = 0; i < 11; ++i) {
                ItemStack itemstack = this.tileEntity.func_70304_b(i);
                if (itemstack == null) continue;
                this.ip.field_70458_d.func_71019_a(itemstack, false);
            }
        }
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.field_75151_b.get(par1);
        if (var3 != null && var3.func_75216_d()) {
            ItemStack var4 = var3.func_75211_c();
            var2 = var4.func_77946_l();
            if (par1 == 0) {
                if (!this.func_75135_a(var4, 11, 47, true)) {
                    return null;
                }
                var3.func_75220_a(var4, var2);
            } else if (par1 >= 11 && par1 < 38) {
                if (var4.func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)var4.func_77973_b()).isStaff(var4)) {
                    if (!this.func_75135_a(var4, 1, 2, false)) {
                        return null;
                    }
                    var3.func_75220_a(var4, var2);
                } else if (!this.func_75135_a(var4, 38, 47, false)) {
                    return null;
                }
            } else if (par1 >= 38 && par1 < 47) {
                if (var4.func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)var4.func_77973_b()).isStaff(var4)) {
                    if (!this.func_75135_a(var4, 1, 2, false)) {
                        return null;
                    }
                    var3.func_75220_a(var4, var2);
                } else if (!this.func_75135_a(var4, 11, 38, false)) {
                    return null;
                }
            } else if (!this.func_75135_a(var4, 11, 47, false)) {
                return null;
            }
            if (var4.field_77994_a == 0) {
                var3.func_75215_d((ItemStack)null);
            } else {
                var3.func_75218_e();
            }
            if (var4.field_77994_a == var2.field_77994_a) {
                return null;
            }
            var3.func_82870_a(this.ip.field_70458_d, var4);
        }
        return var2;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par3 == 4) {
            par2 = 1;
            return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
        }
        if ((par1 == 0 || par1 == 1) && par2 > 0) {
            par2 = 0;
        }
        return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
        return par2Slot.field_75224_c != this.tileEntity && super.func_94530_a(par1ItemStack, par2Slot);
    }
}

