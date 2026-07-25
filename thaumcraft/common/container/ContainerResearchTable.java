/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.container.SlotLimitedByClass;
import thaumcraft.common.items.ItemResearchNotes;
import thaumcraft.common.tiles.TileResearchTable;

public class ContainerResearchTable
extends Container {
    public TileResearchTable tileEntity;
    String[] aspects;
    EntityPlayer player;

    public ContainerResearchTable(InventoryPlayer iinventory, TileResearchTable iinventory1) {
        this.player = iinventory.field_70458_d;
        this.tileEntity = iinventory1;
        this.aspects = Aspect.aspects.keySet().toArray(new String[0]);
        this.func_75146_a(new SlotLimitedByClass(IScribeTools.class, iinventory1, 0, 14, 10));
        this.func_75146_a(new SlotLimitedByClass(ItemResearchNotes.class, iinventory1, 1, 70, 10));
        this.bindPlayerInventory(iinventory);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 175 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 48 + i * 18, 233));
        }
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button) {
        if (button == 1) {
            return true;
        }
        if (button == 5) {
            this.tileEntity.duplicate(par1EntityPlayer);
            return true;
        }
        return false;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < 2 ? !this.func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true) : !this.func_75135_a(stackInSlot, 0, 2, false)) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
        }
        return stack;
    }

    protected boolean func_75135_a(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
        ItemStack var8;
        Slot var7;
        boolean var5 = false;
        int var6 = par2;
        if (par4) {
            var6 = par3 - 1;
        }
        if (par1ItemStack.func_77985_e()) {
            while (par1ItemStack.field_77994_a > 0 && (!par4 && var6 < par3 || par4 && var6 >= par2)) {
                var7 = (Slot)this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();
                if (var8 != null && var7.func_75214_a(par1ItemStack) && var8.func_77973_b() == par1ItemStack.func_77973_b() && (!par1ItemStack.func_77981_g() || par1ItemStack.func_77960_j() == var8.func_77960_j()) && ItemStack.func_77970_a((ItemStack)par1ItemStack, (ItemStack)var8)) {
                    int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
                    if (var9 <= par1ItemStack.func_77976_d()) {
                        par1ItemStack.field_77994_a = 0;
                        var8.field_77994_a = var9;
                        var7.func_75218_e();
                        var5 = true;
                    } else if (var8.field_77994_a < par1ItemStack.func_77976_d()) {
                        par1ItemStack.field_77994_a -= par1ItemStack.func_77976_d() - var8.field_77994_a;
                        var8.field_77994_a = par1ItemStack.func_77976_d();
                        var7.func_75218_e();
                        var5 = true;
                    }
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        if (par1ItemStack.field_77994_a > 0) {
            var6 = par4 ? par3 - 1 : par2;
            while (!par4 && var6 < par3 || par4 && var6 >= par2) {
                var7 = (Slot)this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();
                if (var8 == null && var7.func_75214_a(par1ItemStack)) {
                    var7.func_75215_d(par1ItemStack.func_77946_l());
                    var7.func_75218_e();
                    par1ItemStack.field_77994_a = 0;
                    var5 = true;
                    break;
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        return var5;
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }
}

