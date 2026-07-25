/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.entities.golems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.ContainerGhostSlots;
import thaumcraft.common.container.SlotGhost;
import thaumcraft.common.container.SlotGhostFluid;
import thaumcraft.common.entities.InventoryMob;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.ItemGolemCore;

public class ContainerGolem
extends ContainerGhostSlots {
    public InventoryMob mobInv;
    public InventoryPlayer playerInv;
    public int currentScroll = 0;
    public int maxScroll = 0;

    public ContainerGolem(InventoryPlayer iinventory, InventoryMob iinventory1) {
        this.mobInv = iinventory1;
        this.playerInv = iinventory;
        ((EntityGolemBase)this.mobInv.ent).paused = true;
        if (ItemGolemCore.hasInventory(((EntityGolemBase)this.mobInv.ent).getCore())) {
            this.bindGolemInventory();
        }
        this.bindPlayerInventory();
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        ((EntityGolemBase)this.mobInv.ent).paused = false;
    }

    protected void bindGolemInventory() {
        int slots = this.mobInv.slotCount;
        this.maxScroll = slots / 6 - 1;
        for (int a = 0; a < Math.min(6, slots); ++a) {
            if (((EntityGolemBase)this.mobInv.ent).getCore() == 0) {
                this.func_75146_a(new SlotGhost(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31));
            }
            if (((EntityGolemBase)this.mobInv.ent).getCore() == 5) {
                this.func_75146_a(new SlotGhostFluid(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31));
                continue;
            }
            this.func_75146_a(new SlotGhost(this.mobInv, a + this.currentScroll * 6, 100 + a / 2 * 28, 16 + a % 2 * 31, 1));
        }
    }

    public void refreshInventory() {
        this.field_75153_a.clear();
        this.field_75151_b.clear();
        if (ItemGolemCore.hasInventory(((EntityGolemBase)this.mobInv.ent).getCore())) {
            this.bindGolemInventory();
        }
        this.bindPlayerInventory();
    }

    protected void bindPlayerInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)this.playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)this.playerInv, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button) {
        int c;
        if (button == 66 && this.currentScroll > 0) {
            --this.currentScroll;
            this.refreshInventory();
        }
        if (button == 67 && this.currentScroll < this.maxScroll) {
            ++this.currentScroll;
            this.refreshInventory();
        }
        if (button >= 50 && button <= 57) {
            ((EntityGolemBase)this.mobInv.ent).setToggle(button - 50, !((EntityGolemBase)this.mobInv.ent).getToggles()[button - 50]);
        }
        int slots = this.mobInv.slotCount;
        if (button >= 0 && button < slots) {
            c = ((EntityGolemBase)this.mobInv.ent).getColors(button) - 1;
            if (c < -1) {
                c = 15;
            }
            ((EntityGolemBase)this.mobInv.ent).setColors(button, c);
        }
        if (button >= slots && button < slots * 2) {
            c = ((EntityGolemBase)this.mobInv.ent).getColors(button - slots) + 1;
            if (c > 15) {
                c = -1;
            }
            ((EntityGolemBase)this.mobInv.ent).setColors(button - slots, c);
        }
        this.mobInv.ent.field_70170_p.func_72908_a(this.mobInv.ent.field_70165_t, this.mobInv.ent.field_70163_u, this.mobInv.ent.field_70161_v, "random.click", 0.2f, 0.8f);
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        int slots = this.mobInv.slotCount;
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < slots ? !this.func_75135_a(stackInSlot, slots, this.field_75151_b.size(), true) : !this.func_75135_a(stackInSlot, 0, slots, false)) {
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

    @Override
    public boolean func_75145_c(EntityPlayer var1) {
        return this.mobInv.canInteractWith(var1);
    }
}

