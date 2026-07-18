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
package vazkii.botania.client.gui.bag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.gui.SlotLocked;
import vazkii.botania.client.gui.bag.InventoryFlowerBag;
import vazkii.botania.client.gui.bag.SlotFlower;

public class ContainerFlowerBag
extends Container {
    InventoryFlowerBag flowerBagInv;

    public ContainerFlowerBag(EntityPlayer player) {
        int j;
        int i;
        int slot = player.field_71071_by.field_70461_c;
        InventoryPlayer playerInv = player.field_71071_by;
        this.flowerBagInv = new InventoryFlowerBag(player, slot);
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 8; ++j) {
                int k = j + i * 8;
                this.func_75146_a(new SlotFlower(this.flowerBagInv, k, 17 + j * 18, 26 + i * 18, k));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            if (player.field_71071_by.field_70461_c == i) {
                this.func_75146_a(new SlotLocked((IInventory)playerInv, i, 8 + i * 18, 142));
                continue;
            }
            this.func_75146_a(new Slot((IInventory)playerInv, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        boolean can = this.flowerBagInv.func_70300_a(player);
        if (!can) {
            this.func_75134_a(player);
        }
        return can;
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        this.flowerBagInv.pushInventory();
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            Slot slot1;
            int i;
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (p_82846_2_ < 16 ? !this.func_75135_a(itemstack1, 16, 52, true) : (i = itemstack.func_77960_j()) < 16 && (slot1 = (Slot)this.field_75151_b.get(i)).func_75214_a(itemstack) && !this.func_75135_a(itemstack1, i, i + 1, true)) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                return null;
            }
            slot.func_82870_a(p_82846_1_, itemstack1);
        }
        return itemstack;
    }
}

