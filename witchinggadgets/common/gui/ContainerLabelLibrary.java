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
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.gui.SlotOutput;

public class ContainerLabelLibrary
extends Container {
    protected final TileEntityLabelLibrary tileEntity;

    public ContainerLabelLibrary(InventoryPlayer inventoryPlayer, TileEntityLabelLibrary te) {
        this.tileEntity = te;
        this.func_75146_a(new Slot(this.tileEntity, 0, 8, 8){

            public boolean func_75214_a(ItemStack stack) {
                if (stack == null) {
                    return true;
                }
                return stack.func_77973_b().equals(ConfigItems.itemResource) && stack.func_77960_j() == 13;
            }
        });
        this.func_75146_a(new SlotOutput(this.tileEntity, 1, 8, 51){

            public void func_82870_a(EntityPlayer player, ItemStack stack) {
                this.field_75224_c.func_70298_a(0, 1);
            }
        });
        this.bindPlayerInventory(inventoryPlayer);
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slot) {
        return null;
    }
}

