/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package drunkmafia.thaumicinfusion.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class InfusionContainer
extends Container {
    public InfusionContainer(InventoryPlayer inventoryPlayer) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, x + y * 9 + 9, 14 + x * 18, 145 + y * 18));
            }
        }
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, x, 14 + x * 18, 203));
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return true;
    }
}

