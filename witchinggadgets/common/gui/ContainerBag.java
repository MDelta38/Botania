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
import witchinggadgets.common.gui.InventoryBag;
import witchinggadgets.common.items.tools.ItemBag;

public class ContainerBag
extends Container {
    private World worldObj;
    private int blockedSlot;
    public IInventory input = new InventoryBag(this);
    ItemStack pouch = null;
    EntityPlayer player = null;
    private int pouchSlotAmount = 18;

    public ContainerBag(InventoryPlayer iinventory, World world) {
        this.worldObj = world;
        this.player = iinventory.field_70458_d;
        this.pouch = iinventory.func_70448_g();
        this.blockedSlot = iinventory.field_70461_c + 45;
        for (int a = 0; a < this.pouchSlotAmount; ++a) {
            this.func_75146_a(new Slot(this.input, a, 35 + a % 6 * 18, 9 + a / 6 * 18));
        }
        this.bindPlayerInventory(iinventory);
        if (!world.field_72995_K) {
            try {
                ((InventoryBag)this.input).stackList = ((ItemBag)this.pouch.func_77973_b()).getStoredItems(this.pouch);
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

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockedSlot || par2 == 0 && par3 == this.blockedSlot) {
            return null;
        }
        ((ItemBag)this.pouch.func_77973_b()).setStoredItems(this.pouch, ((InventoryBag)this.input).stackList);
        return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            ((ItemBag)this.pouch.func_77973_b()).setStoredItems(this.pouch, ((InventoryBag)this.input).stackList);
            if (!this.player.func_71045_bC().equals(this.pouch)) {
                this.player.func_70062_b(0, this.pouch);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }
}

