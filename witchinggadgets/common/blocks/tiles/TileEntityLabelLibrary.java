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
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityLabelLibrary
extends TileEntityWGBase
implements IInventory {
    ItemStack[] inventory = new ItemStack[2];
    public Aspect aspect;
    public int facing = 2;

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            this.inventory[1] = this.getOutput();
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        NBTTagList tagList = tag.func_150295_c("Inventory", 10);
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound itemTag = tagList.func_150305_b(i);
            byte slot = itemTag.func_74771_c("Slot");
            if (slot < 0 || slot >= this.inventory.length) continue;
            this.inventory[slot] = ItemStack.func_77949_a((NBTTagCompound)itemTag);
        }
        if (tag.func_74764_b("aspect")) {
            this.aspect = Aspect.getAspect((String)tag.func_74779_i("aspect"));
        }
        this.facing = tag.func_74762_e("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        if (this.inventory != null) {
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < this.inventory.length; ++i) {
                ItemStack stack = this.inventory[i];
                if (stack == null) continue;
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.func_74774_a("Slot", (byte)i);
                stack.func_77955_b(itemTag);
                itemList.func_74742_a((NBTBase)itemTag);
            }
            tag.func_74782_a("Inventory", (NBTBase)itemList);
        }
        if (this.aspect != null) {
            tag.func_74778_a("aspect", this.aspect.getTag());
        }
        tag.func_74768_a("facing", this.facing);
    }

    public ItemStack getOutput() {
        if (this.aspect != null && this.inventory[0] != null) {
            ItemStack stack = new ItemStack(ConfigItems.itemResource, 1, 13);
            ((IEssentiaContainerItem)stack.func_77973_b()).setAspects(stack, new AspectList().add(this.aspect, 0));
            return stack;
        }
        return null;
    }

    public int func_70302_i_() {
        return this.inventory.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventory[i];
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
        this.inventory[slot] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
    }

    public String func_145825_b() {
        return "LabelLibrary";
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

