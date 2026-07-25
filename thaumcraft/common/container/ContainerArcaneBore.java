/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.SlotLimitedByClass;
import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
import thaumcraft.common.tiles.TileArcaneBore;

public class ContainerArcaneBore
extends Container {
    private TileArcaneBore tileEntity;

    public ContainerArcaneBore(InventoryPlayer iinventory, TileArcaneBore e) {
        this.tileEntity = e;
        this.func_75146_a(new SlotLimitedByClass(ItemFocusExcavation.class, e, 0, 26, 18));
        this.func_75146_a(new SlotLimitedByClass(ItemPickaxe.class, e, 1, 74, 18));
        this.bindPlayerInventory(iinventory);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 59 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 117));
        }
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.tileEntity.func_145831_w().func_147438_o(this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e) != this.tileEntity ? false : par1EntityPlayer.func_70092_e((double)this.tileEntity.field_145851_c + 0.5, (double)this.tileEntity.field_145848_d + 0.5, (double)this.tileEntity.field_145849_e + 0.5) <= 64.0;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot <= 1 ? !this.func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true) : (slot > 1 ? (stackInSlot.func_77973_b() instanceof ItemFocusExcavation ? !this.func_75135_a(stackInSlot, 0, 1, false) : stackInSlot.func_77973_b() instanceof ItemPickaxe && !this.func_75135_a(stackInSlot, 1, 2, false)) : !this.func_75135_a(stackInSlot, 2, 38, false))) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
            if (stackInSlot.field_77994_a == stack.field_77994_a) {
                return null;
            }
        }
        return stack;
    }
}

