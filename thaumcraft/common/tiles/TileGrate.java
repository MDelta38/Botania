/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.entities.EntityItemGrate;

public class TileGrate
extends TileEntity
implements ISidedInventory {
    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return null;
    }

    public ItemStack func_70298_a(int par1, int par2) {
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        return null;
    }

    public void func_70299_a(int par1, ItemStack stack) {
        if (!this.field_145850_b.field_72995_K) {
            EntityItemGrate ei = new EntityItemGrate(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.6, (double)this.field_145849_e + 0.5, stack.func_77946_l());
            ei.field_70181_x = -0.1;
            ei.field_70159_w = 0.0;
            ei.field_70179_y = 0.0;
            this.field_145850_b.func_72838_d((Entity)ei);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5;
    }

    public int[] func_94128_d(int par1) {
        int[] nArray;
        if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5 && par1 == ForgeDirection.UP.ordinal()) {
            int[] nArray2 = new int[1];
            nArray = nArray2;
            nArray2[0] = 0;
        } else {
            nArray = new int[]{};
        }
        return nArray;
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5 && par3 == ForgeDirection.UP.ordinal();
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return false;
    }

    public String func_145825_b() {
        return "thaumcraft.grate";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean canUpdate() {
        return false;
    }
}

