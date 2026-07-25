/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;

public class TileThaumcraftInventory
extends TileThaumcraft
implements ISidedInventory {
    protected ItemStack[] itemStacks = new ItemStack[1];
    protected String customName;
    protected int[] syncedSlots = new int[0];

    public int func_70302_i_() {
        return this.itemStacks.length;
    }

    public ItemStack func_70301_a(int par1) {
        return this.itemStacks[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.itemStacks[par1] != null) {
            if (this.itemStacks[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.itemStacks[par1] != null) {
            ItemStack itemstack = this.itemStacks[par1];
            this.itemStacks[par1] = null;
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.itemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.thaumcraft";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    private boolean isSyncedSlot(int slot) {
        for (int s : this.syncedSlots) {
            if (s != slot) continue;
            return true;
        }
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbtCompound) {
        NBTTagList nbttaglist = nbtCompound.func_150295_c("ItemsSynced", 10);
        this.itemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1;
            byte b0;
            if (!this.isSyncedSlot(i) || (b0 = (nbttagcompound1 = nbttaglist.func_150305_b(i)).func_74771_c("Slot")) < 0 || b0 >= this.itemStacks.length) continue;
            this.itemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbtCompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] == null || !this.isSyncedSlot(i)) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.itemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbtCompound.func_74782_a("ItemsSynced", (NBTBase)nbttaglist);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        if (nbtCompound.func_74764_b("CustomName")) {
            this.customName = nbtCompound.func_74779_i("CustomName");
        }
        NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1;
            byte b0;
            if (this.isSyncedSlot(i) || (b0 = (nbttagcompound1 = nbttaglist.func_150305_b(i)).func_74771_c("Slot")) < 0 || b0 >= this.itemStacks.length) continue;
            this.itemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        if (this.func_145818_k_()) {
            nbtCompound.func_74778_a("CustomName", this.customName);
        }
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] == null || this.isSyncedSlot(i)) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.itemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbtCompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public int[] func_94128_d(int par1) {
        return new int[]{0};
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.func_94041_b(par1, par2ItemStack);
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }
}

