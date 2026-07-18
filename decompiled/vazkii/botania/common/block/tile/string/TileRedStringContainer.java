/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.common.block.tile.string;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.core.helper.InventoryHelper;

public class TileRedStringContainer
extends TileRedString
implements ISidedInventory {
    @Override
    public boolean acceptBlock(int x, int y, int z) {
        TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
        if (tile != null && tile instanceof IInventory) {
            IInventory inv = (IInventory)tile;
            if (inv instanceof ISidedInventory) {
                ISidedInventory sidedInv = (ISidedInventory)inv;
                for (int i = 0; i < 6; ++i) {
                    if (sidedInv.func_94128_d(i).length == 0) continue;
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public int func_70302_i_() {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70302_i_() : 0;
    }

    public ItemStack func_70301_a(int slot) {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70301_a(slot) : null;
    }

    public ItemStack func_70298_a(int slot, int count) {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70298_a(slot, count) : null;
    }

    public ItemStack func_70304_b(int slot) {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70304_b(slot) : null;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        IInventory inv = this.getInventory();
        if (inv != null) {
            inv.func_70299_a(slot, stack);
        }
    }

    public String func_145825_b() {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_145825_b() : "redStringContainer";
    }

    public boolean func_145818_k_() {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_145818_k_() : false;
    }

    public int func_70297_j_() {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70297_j_() : 0;
    }

    public boolean func_70300_a(EntityPlayer player) {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_70300_a(player) : false;
    }

    public void func_70295_k_() {
        IInventory inv = this.getInventory();
        if (inv != null) {
            inv.func_70295_k_();
        }
    }

    public void func_70305_f() {
        IInventory inv = this.getInventory();
        if (inv != null) {
            inv.func_70305_f();
        }
    }

    public boolean func_94041_b(int slot, ItemStack stack) {
        IInventory inv = this.getInventory();
        return inv != null ? inv.func_94041_b(slot, stack) : false;
    }

    public int[] func_94128_d(int side) {
        IInventory inv = this.getInventory();
        return inv instanceof ISidedInventory ? ((ISidedInventory)inv).func_94128_d(side) : (inv instanceof IInventory ? InventoryHelper.buildSlotsForLinearInventory(inv) : new int[]{});
    }

    public boolean func_102007_a(int slot, ItemStack stack, int side) {
        IInventory inv = this.getInventory();
        return inv instanceof ISidedInventory ? ((ISidedInventory)inv).func_102007_a(slot, stack, side) : true;
    }

    public boolean func_102008_b(int slot, ItemStack stack, int side) {
        IInventory inv = this.getInventory();
        return inv instanceof ISidedInventory ? ((ISidedInventory)inv).func_102008_b(slot, stack, side) : true;
    }

    public void func_70296_d() {
        super.func_70296_d();
        TileEntity tile = this.getTileAtBinding();
        if (tile != null) {
            tile.func_70296_d();
        }
    }

    IInventory getInventory() {
        TileEntity tile = this.getTileAtBinding();
        if (tile == null || !(tile instanceof IInventory)) {
            return null;
        }
        return InventoryHelper.getInventory((IInventory)tile);
    }
}

