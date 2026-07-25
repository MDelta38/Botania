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
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.container.SlotLimitedByClass;
import thaumcraft.common.tiles.TileFocalManipulator;

public class ContainerFocalManipulator
extends Container {
    private TileFocalManipulator table;
    private int lastBreakTime;

    public ContainerFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator tileEntity) {
        int i;
        this.table = tileEntity;
        this.func_75146_a(new SlotLimitedByClass(ItemFocusBasic.class, (IInventory)tileEntity, 0, 88, 60));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, i, 16 + i * 18, 209));
        }
    }

    public boolean func_75140_a(EntityPlayer p, int button) {
        if (button >= 0 && !this.table.startCraft(button, p)) {
            this.table.func_145831_w().func_72908_a((double)this.table.field_145851_c, (double)this.table.field_145848_d, (double)this.table.field_145849_e, "thaumcraft:craftfail", 0.33f, 1.0f);
        }
        return false;
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.table.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 != 0 ? (itemstack1.func_77973_b() instanceof ItemFocusBasic ? !this.func_75135_a(itemstack1, 0, 1, false) : (par2 >= 1 && par2 < 28 ? !this.func_75135_a(itemstack1, 28, 37, false) : par2 >= 28 && par2 < 37 && !this.func_75135_a(itemstack1, 1, 28, false))) : !this.func_75135_a(itemstack1, 1, 37, false)) {
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
            slot.func_82870_a(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}

