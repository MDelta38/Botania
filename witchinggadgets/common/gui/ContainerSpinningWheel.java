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
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.gui.SlotOutput;

public class ContainerSpinningWheel
extends Container {
    protected TileEntitySpinningWheel tileEntity;
    private int slotCount;

    public ContainerSpinningWheel(InventoryPlayer inventoryPlayer, TileEntitySpinningWheel te) {
        this.tileEntity = te;
        for (int i = 0; i < 5; ++i) {
            this.func_75146_a(new Slot((IInventory)this.tileEntity, i, 16, 16 + 24 * i));
        }
        this.func_75146_a(new SlotOutput(this.tileEntity, 5, 142, 64));
        this.slotCount = 6;
        this.bindPlayerInventory(inventoryPlayer);
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 16 + i * 18, 209));
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < this.slotCount ? !this.func_75135_a(stackInSlot, this.slotCount, this.slotCount + 36, true) : !this.func_75135_a(stackInSlot, 0, this.slotCount, false)) {
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
            slotObject.func_82870_a(player, stackInSlot);
        }
        return stack;
    }
}

