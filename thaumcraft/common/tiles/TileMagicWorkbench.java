/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileMagicWorkbench
extends TileThaumcraft
implements IInventory,
ISidedInventory {
    public ItemStack[] stackList = new ItemStack[11];
    public Container eventHandler;
    protected int count;

    public int func_70302_i_() {
        return this.stackList.length;
    }

    public ItemStack func_70301_a(int par1) {
        return par1 >= this.func_70302_i_() ? null : this.stackList[par1];
    }

    public ItemStack getStackInRowAndColumn(int par1, int par2) {
        if (par1 >= 0 && par1 < 3) {
            int var3 = par1 + par2 * 3;
            return this.func_70301_a(var3);
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            this.func_70296_d();
            return var2;
        }
        return null;
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.stackList[par1] != null) {
            if (this.stackList[par1].field_77994_a <= par2) {
                ItemStack var3 = this.stackList[par1];
                this.stackList[par1] = null;
                if (this.eventHandler != null) {
                    this.eventHandler.func_75130_a((IInventory)this);
                }
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return var3;
            }
            ItemStack var3 = this.stackList[par1].func_77979_a(par2);
            if (this.stackList[par1].field_77994_a == 0) {
                this.stackList[par1] = null;
            }
            if (this.eventHandler != null) {
                this.eventHandler.func_75130_a((IInventory)this);
            }
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return var3;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.eventHandler != null) {
            this.eventHandler.func_75130_a((IInventory)this);
        }
    }

    public void setInventorySlotContentsSoftly(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Inventory", 10);
        this.stackList = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            int var5 = var4.func_74771_c("Slot") & 0xFF;
            if (var5 < 0 || var5 >= this.stackList.length) continue;
            this.stackList[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.stackList.length; ++var3) {
            if (this.stackList[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.stackList[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Inventory", (NBTBase)var2);
    }

    public String func_145825_b() {
        return null;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        if (i == 10 && itemstack != null) {
            if (!(itemstack.func_77973_b() instanceof ItemWandCasting)) {
                return false;
            }
            ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
            return !wand.isStaff(itemstack);
        }
        return true;
    }

    public int[] func_94128_d(int var1) {
        return new int[]{10};
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        if (i != 10 || itemstack == null || !(itemstack.func_77973_b() instanceof ItemWandCasting)) {
            return false;
        }
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        return !wand.isStaff(itemstack);
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return i == 10;
    }
}

