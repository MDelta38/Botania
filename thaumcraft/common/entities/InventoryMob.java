/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.common.entities;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class InventoryMob
implements IInventory {
    public ItemStack[] inventory;
    public Entity ent;
    public boolean inventoryChanged;
    public int slotCount;
    public int stacklimit = 64;

    public InventoryMob(Entity entity, int slots) {
        this.slotCount = slots;
        this.inventory = new ItemStack[this.slotCount];
        this.inventoryChanged = false;
        this.ent = entity;
    }

    public InventoryMob(Entity entity, int slots, int lim) {
        this.slotCount = slots;
        this.inventory = new ItemStack[this.slotCount];
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

    public int storeItemStack(ItemStack itemstack) {
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null || this.inventory[i].func_77973_b() != itemstack.func_77973_b() || !this.inventory[i].func_77985_e() || this.inventory[i].field_77994_a >= this.inventory[i].func_77976_d() || this.inventory[i].field_77994_a >= this.func_70297_j_() || this.inventory[i].func_77981_g() && this.inventory[i].func_77960_j() != itemstack.func_77960_j()) continue;
            return i;
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

    public int storePartialItemStack(ItemStack itemstack) {
        int l;
        Item i = itemstack.func_77973_b();
        int j = itemstack.field_77994_a;
        int k = this.storeItemStack(itemstack);
        if (k < 0) {
            k = this.getFirstEmptyStack();
        }
        if (k < 0) {
            return j;
        }
        if (this.inventory[k] == null) {
            this.inventory[k] = new ItemStack(i, 0, itemstack.func_77960_j());
        }
        if ((l = j) > this.inventory[k].func_77976_d() - this.inventory[k].field_77994_a) {
            l = this.inventory[k].func_77976_d() - this.inventory[k].field_77994_a;
        }
        if (l > this.func_70297_j_() - this.inventory[k].field_77994_a) {
            l = this.func_70297_j_() - this.inventory[k].field_77994_a;
        }
        if (l == 0) {
            return j;
        }
        this.inventory[k].field_77994_a += l;
        this.inventory[k].field_77992_b = 5;
        return j -= l;
    }

    public boolean addItemStackToInventory(ItemStack itemstack) {
        if (!itemstack.func_77951_h()) {
            int i;
            do {
                i = itemstack.field_77994_a;
                itemstack.field_77994_a = this.storePartialItemStack(itemstack);
            } while (itemstack.field_77994_a > 0 && itemstack.field_77994_a < i);
            return itemstack.field_77994_a < i;
        }
        int j = this.getFirstEmptyStack();
        if (j >= 0) {
            this.inventory[j] = ItemStack.func_77944_b((ItemStack)itemstack);
            itemstack.field_77994_a = 0;
            return true;
        }
        return false;
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
        this.inventory = new ItemStack[this.slotCount];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
            ItemStack itemstack = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound);
            if (itemstack.func_77973_b() == null || j < 0 || j >= this.inventory.length) continue;
            this.inventory[j] = itemstack;
        }
    }

    public int func_70302_i_() {
        return this.inventory.length + 1;
    }

    public ItemStack func_70301_a(int i) {
        ItemStack[] aitemstack = this.inventory;
        return aitemstack[i];
    }

    public int func_70297_j_() {
        return this.stacklimit;
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        if (this.ent.field_70128_L) {
            return false;
        }
        return entityplayer.func_70068_e(this.ent) <= 64.0;
    }

    public boolean func_28018_c(ItemStack itemstack) {
        for (int j = 0; j < this.inventory.length; ++j) {
            if (this.inventory[j] == null || !ItemStack.func_77989_b((ItemStack)this.inventory[j], (ItemStack)itemstack)) continue;
            return true;
        }
        return false;
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

    public boolean hasSomething() {
        for (int a = 0; a < this.slotCount; ++a) {
            if (this.inventory[a] == null) continue;
            return true;
        }
        return false;
    }

    public boolean allEmpty() {
        for (int a = 0; a < this.slotCount; ++a) {
            if (this.inventory[a] == null) continue;
            return false;
        }
        return true;
    }

    public int getAmountNeeded(ItemStack stackInSlot) {
        int amt = 0;
        for (int a = 0; a < this.slotCount; ++a) {
            if (this.inventory[a] == null || !this.inventory[a].func_77969_a(stackInSlot)) continue;
            amt += this.inventory[a].field_77994_a;
        }
        return amt;
    }

    public int getAmountNeededSmart(ItemStack stackInSlot, boolean fuzzy) {
        int amt = 0;
        for (int a = 0; a < this.slotCount; ++a) {
            if (this.inventory[a] == null) continue;
            if (fuzzy) {
                ItemStack[] ores;
                if (this.inventory[a].func_77969_a(stackInSlot)) {
                    amt += this.inventory[a].field_77994_a;
                    continue;
                }
                int od = OreDictionary.getOreID((ItemStack)this.inventory[a]);
                if (od == -1 || !ThaumcraftApiHelper.containsMatch(false, new ItemStack[]{stackInSlot}, ores = OreDictionary.getOres((Integer)od).toArray(new ItemStack[0]))) continue;
                amt += this.inventory[a].field_77994_a;
                continue;
            }
            if (!this.inventory[a].func_77969_a(stackInSlot) || !ItemStack.func_77970_a((ItemStack)this.inventory[a], (ItemStack)stackInSlot)) continue;
            amt += this.inventory[a].field_77994_a;
        }
        return amt;
    }

    public ArrayList<ItemStack> getItemsNeeded(boolean fuzzy) {
        boolean amt = false;
        ArrayList<ItemStack> needed = new ArrayList<ItemStack>();
        for (int a = 0; a < this.slotCount; ++a) {
            if (this.inventory[a] == null) continue;
            if (fuzzy) {
                int od = OreDictionary.getOreID((ItemStack)this.inventory[a]);
                if (od != -1) {
                    ItemStack[] ores;
                    for (ItemStack ore : ores = OreDictionary.getOres((Integer)od).toArray(new ItemStack[0])) {
                        needed.add(ore.func_77946_l());
                    }
                    continue;
                }
                needed.add(this.inventory[a].func_77946_l());
                continue;
            }
            needed.add(this.inventory[a].func_77946_l());
        }
        return needed;
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
            ((EntityTravelingTrunk)this.ent).setOpen(true);
        }
    }

    public void func_70305_f() {
        if (this.ent instanceof EntityTravelingTrunk) {
            ((EntityTravelingTrunk)this.ent).setOpen(false);
        }
    }
}

