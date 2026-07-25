/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package thaumic.tinkerer.common.block.tile.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public abstract class ContainerPlayerInv
extends Container {
    InventoryPlayer playerInv;

    public ContainerPlayerInv(InventoryPlayer playerInv) {
        this.playerInv = playerInv;
    }

    public void initPlayerInv() {
        int x;
        int ys = this.getInvYStart();
        int xs = this.getInvXStart();
        for (x = 0; x < 3; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.func_75146_a(new Slot((IInventory)this.playerInv, y + x * 9 + 9, xs + y * 18, ys + x * 18));
            }
        }
        for (x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)this.playerInv, x, xs + x * 18, ys + 58));
        }
    }

    public int getInvYStart() {
        return 84;
    }

    public int getInvXStart() {
        return 8;
    }
}

