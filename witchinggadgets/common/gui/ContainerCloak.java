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
 *  net.minecraft.world.World
 */
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import witchinggadgets.common.gui.InventoryCloak;
import witchinggadgets.common.items.baubles.ItemCloak;
import witchinggadgets.common.util.Utilities;

public class ContainerCloak
extends Container {
    private World worldObj;
    public IInventory input = new InventoryCloak(this);
    ItemStack cloak = null;
    EntityPlayer player = null;
    private int pouchSlotAmount = 27;

    public ContainerCloak(InventoryPlayer iinventory, World world, ItemStack cloak) {
        this.worldObj = world;
        this.player = iinventory.field_70458_d;
        this.cloak = cloak;
        for (int a = 0; a < this.pouchSlotAmount; ++a) {
            this.func_75146_a(new Slot(this.input, a, 8 + a % 9 * 18, 9 + a / 9 * 18));
        }
        this.bindPlayerInventory(iinventory);
        if (!world.field_72995_K) {
            try {
                ((InventoryCloak)this.input).stackList = ((ItemCloak)this.cloak.func_77973_b()).getStoredItems(this.cloak);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.func_75130_a(this.input);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 82 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 140));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < this.pouchSlotAmount ? !this.func_75135_a(stackInSlot, this.pouchSlotAmount, this.field_75151_b.size(), true) : !this.func_75135_a(stackInSlot, 0, this.pouchSlotAmount, false)) {
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

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            ((ItemCloak)this.cloak.func_77973_b()).setStoredItems(this.cloak, ((InventoryCloak)this.input).stackList);
            Utilities.updateActiveMagicalCloak(this.player, this.cloak);
        }
    }
}

