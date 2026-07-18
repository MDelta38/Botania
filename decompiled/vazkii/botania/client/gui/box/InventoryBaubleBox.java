/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.gui.box;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.ItemBaubleBox;
import vazkii.botania.common.item.ModItems;

public class InventoryBaubleBox
implements IInventory {
    private static final ItemStack[] FALLBACK_INVENTORY = new ItemStack[16];
    EntityPlayer player;
    int slot;
    ItemStack[] stacks = null;
    boolean invPushed = false;
    ItemStack storedInv = null;

    public InventoryBaubleBox(EntityPlayer player, int slot) {
        this.player = player;
        this.slot = slot;
    }

    public static boolean isBaubleBox(ItemStack stack) {
        return stack != null && stack.func_77973_b() == ModItems.baubleBox;
    }

    ItemStack getStack() {
        ItemStack stack = this.player.field_71071_by.func_70301_a(this.slot);
        if (stack != null) {
            this.storedInv = stack;
        }
        return stack;
    }

    ItemStack[] getInventory() {
        if (this.stacks != null) {
            return this.stacks;
        }
        ItemStack stack = this.getStack();
        if (InventoryBaubleBox.isBaubleBox(this.getStack())) {
            this.stacks = ItemBaubleBox.loadStacks(stack);
            return this.stacks;
        }
        return FALLBACK_INVENTORY;
    }

    public void pushInventory() {
        if (this.invPushed) {
            return;
        }
        ItemStack stack = this.getStack();
        if (stack == null) {
            stack = this.storedInv;
        }
        if (stack != null) {
            ItemStack[] inv = this.getInventory();
            ItemBaubleBox.setStacks(stack, inv);
        }
        this.invPushed = true;
    }

    public int func_70302_i_() {
        return 16;
    }

    public ItemStack func_70301_a(int i) {
        return this.getInventory()[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        ItemStack[] inventorySlots = this.getInventory();
        if (inventorySlots[i] != null) {
            if (inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = inventorySlots[i];
                inventorySlots[i] = null;
                return stackAt;
            }
            ItemStack stackAt = inventorySlots[i].func_77979_a(j);
            if (inventorySlots[i].field_77994_a == 0) {
                inventorySlots[i] = null;
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int slot, ItemStack itemstack) {
        ItemStack[] inventorySlots = this.getInventory();
        inventorySlots[slot] = itemstack;
    }

    public int func_70297_j_() {
        return InventoryBaubleBox.isBaubleBox(this.getStack()) ? 64 : 0;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return InventoryBaubleBox.isBaubleBox(this.getStack());
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return InventoryBaubleBox.isBaubleBox(this.getStack());
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public String func_145825_b() {
        return "baubleBox";
    }

    public void func_70296_d() {
    }
}

