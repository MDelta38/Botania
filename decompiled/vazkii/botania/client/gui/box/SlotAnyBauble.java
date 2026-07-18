/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.IBauble
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.gui.box;

import baubles.api.IBauble;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.gui.box.InventoryBaubleBox;

public class SlotAnyBauble
extends Slot {
    InventoryBaubleBox inv;

    public SlotAnyBauble(InventoryBaubleBox p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super((IInventory)p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.inv = p_i1824_1_;
    }

    public void func_75220_a(ItemStack oldStack, ItemStack newStack) {
        this.inv.func_70299_a(this.field_75222_d, newStack);
    }

    public boolean func_75214_a(ItemStack stack) {
        return stack.func_77973_b() instanceof IBauble || stack.func_77973_b() instanceof IManaItem;
    }
}

