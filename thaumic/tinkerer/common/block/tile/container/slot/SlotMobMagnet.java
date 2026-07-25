/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.block.tile.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.item.ItemSoulMould;

public class SlotMobMagnet
extends Slot {
    public SlotMobMagnet(TileMobMagnet mobMagnet, int par2, int par3, int par4) {
        super((IInventory)mobMagnet, par2, par3, par4);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return par1ItemStack.func_77973_b() instanceof ItemSoulMould;
    }

    public int func_75219_a() {
        return 1;
    }
}

