/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package thaumcraft.common.entities.golems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class InventoryTrunk
implements IInventory {
    public ItemStack[] inventory;
    public EntityTravelingTrunk ent;
    public boolean inventoryChanged;
    public int slotCount;
    public int stacklimit = 64;

    public InventoryTrunk(EntityTravelingTrunk entity, int slots) {
        this.slotCount = slots;
        this.inventory = new ItemStack[36];
        this.inventoryChanged = false;
        this.ent = entity;
    }

    public InventoryTrunk(EntityTravelingTrunk entity, int slots, int lim) {
        this.slotCount = slots;
        this.inventory = new ItemStack[36];
        this.inventoryChanged = false;
        this.stacklimit = lim;
        this.ent = entity;
    }

    public int getInventorySlotContainItem(Item i) {
        for (int j = 0; j < this.inventory.length; ++j) {
            if (this.inventory[j] == null || this.inventory[j].func_77973_b() != i) continue;
            return j;
        }
        return -1;
    }

    public int getFirstEmptyStack() {
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) continue;
            return i;
        }
        return -1;
    }

    public ItemStack func_70298_a(int i, int j) {
        ItemStack[] aitemstack = this.inventory;
        if (aitemstack[i] != null) {
            if (aitemstack[i].field_77994_a <= j) {
                ItemStack itemstack = aitemstack[i];
                aitemstack[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = aitemstack[i].func_77979_a(j);
            if (aitemstack[i].field_77994_a == 0) {
                aitemstack[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        ItemStack[] aitemstack = this.inventory;
        aitemstack[i] = itemstack;
    }

    public NBTTagList writeToNBT(NBTTagList nbttaglist) {
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.inventory[i].func_77955_b(nbttagcompound);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public void readFromNBT(NBTTagList nbttaglist) {
        this.inventory = new ItemStack[this.inventory.length];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
            ItemStack itemstack = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound);
            if (itemstack.func_77973_b() == null || j < 0 || j >= this.inventory.length) continue;
            this.inventory[j] = itemstack;
        }
    }

    public int func_70302_i_() {
        return this.slotCount;
    }

    public ItemStack func_70301_a(int i) {
        ItemStack[] aitemstack = this.inventory;
        return aitemstack[i];
    }

    public int func_70297_j_() {
        return this.stacklimit;
    }

    public void dropAllItems() {
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            this.ent.func_70099_a(this.inventory[i], 0.0f);
            this.inventory[i] = null;
        }
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return false;
    }

    public ItemStack func_70304_b(int var1) {
        return null;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return "Inventory";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70296_d() {
        this.inventoryChanged = true;
    }

    public void func_70295_k_() {
        if (this.ent instanceof EntityTravelingTrunk) {
            this.ent.setOpen(true);
        }
    }

    public void func_70305_f() {
        if (this.ent instanceof EntityTravelingTrunk) {
            this.ent.setOpen(false);
        }
    }
}

