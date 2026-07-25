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
import thaumcraft.common.container.SlotLimitedByClass;
import thaumcraft.common.items.ItemBathSalts;
import thaumcraft.common.tiles.TileSpa;

public class ContainerSpa
extends Container {
    private TileSpa spa;
    private int lastBreakTime;

    public ContainerSpa(InventoryPlayer par1InventoryPlayer, TileSpa tileEntity) {
        int i;
        this.spa = tileEntity;
        this.func_75146_a(new SlotLimitedByClass(ItemBathSalts.class, (IInventory)tileEntity, 0, 65, 31));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75140_a(EntityPlayer p, int button) {
        if (button == 1) {
            this.spa.toggleMix();
        }
        return false;
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.spa.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot == 0 ? !this.spa.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true) : !this.spa.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 0, 1, false)) {
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
}

