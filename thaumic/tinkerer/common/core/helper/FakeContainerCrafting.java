/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FakeContainerCrafting
extends Container {
    public FakeContainerCrafting(ItemStack item) {
        for (int i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)new FakeInventory(item), i, 0, 0));
            this.field_75153_a.set(i, item.func_77946_l());
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return false;
    }

    private class FakeInventory
    implements IInventory {
        ItemStack itemStack;

        FakeInventory(ItemStack item) {
            this.itemStack = item;
        }

        public int func_70302_i_() {
            return 1;
        }

        public ItemStack func_70301_a(int p_70301_1_) {
            return this.itemStack;
        }

        public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
            return null;
        }

        public ItemStack func_70304_b(int p_70304_1_) {
            return this.itemStack;
        }

        public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        }

        public String func_145825_b() {
            return "FakeInventory";
        }

        public boolean func_145818_k_() {
            return false;
        }

        public int func_70297_j_() {
            return 64;
        }

        public void func_70296_d() {
        }

        public boolean func_70300_a(EntityPlayer p_70300_1_) {
            return false;
        }

        public void func_70295_k_() {
        }

        public void func_70305_f() {
        }

        public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
            return true;
        }
    }
}

