/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntitySarcophagus
extends TileEntityWGBase
implements IInventory {
    public int facing = 0;
    public boolean open = false;
    public boolean dummyLeft = false;
    public boolean dummyRight = false;
    public ItemStack[] inv = new ItemStack[6];

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        NBTTagList tagList = tags.func_150295_c("Inventory", 10);
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound tag = tagList.func_150305_b(i);
            byte slot = tag.func_74771_c("Slot");
            if (slot < 0 || slot >= this.inv.length) continue;
            this.inv[slot] = ItemStack.func_77949_a((NBTTagCompound)tag);
        }
        this.open = tags.func_74767_n("open");
        this.dummyLeft = tags.func_74767_n("dummyLeft");
        this.dummyRight = tags.func_74767_n("dummyRight");
        this.facing = tags.func_74762_e("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.inv.length; ++i) {
            ItemStack stack = this.inv[i];
            if (stack == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            stack.func_77955_b(tag);
            itemList.func_74742_a((NBTBase)tag);
        }
        tags.func_74782_a("Inventory", (NBTBase)itemList);
        tags.func_74757_a("open", this.open);
        tags.func_74757_a("dummyLeft", this.dummyLeft);
        tags.func_74757_a("dummyRight", this.dummyRight);
        tags.func_74768_a("facing", this.facing);
    }

    public int func_70302_i_() {
        return this.inv.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inv[i];
    }

    public ItemStack func_70298_a(int slot, int amt) {
        ItemStack stack = this.func_70301_a(slot);
        if (stack != null) {
            if (stack.field_77994_a <= amt) {
                this.func_70299_a(slot, null);
            } else {
                stack = stack.func_77979_a(amt);
                if (stack.field_77994_a == 0) {
                    this.func_70299_a(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack func_70304_b(int slot) {
        ItemStack stack = this.func_70301_a(slot);
        if (stack != null) {
            this.func_70299_a(slot, null);
        }
        return stack;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        this.inv[slot] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
    }

    public String func_145825_b() {
        return "WG.Sarcophagus";
    }

    public boolean func_145818_k_() {
        return true;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) < 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }
}

