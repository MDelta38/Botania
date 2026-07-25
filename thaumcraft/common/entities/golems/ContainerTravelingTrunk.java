/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.golems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.golems.InventoryTrunk;

public class ContainerTravelingTrunk
extends Container {
    private InventoryTrunk mobInv;
    private EntityTravelingTrunk trunk;
    private int numRows;

    public ContainerTravelingTrunk(IInventory iinventory, World par3World, EntityTravelingTrunk trunk) {
        this.trunk = trunk;
        this.mobInv = trunk.inventory;
        this.numRows = trunk.getRows();
        for (int j = 0; j < this.numRows; ++j) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.func_75146_a(new Slot((IInventory)this.mobInv, i1 + j * 9, 8 + i1 * 18, 15 + j * 23));
            }
        }
        for (int k = 0; k < 3; ++k) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.func_75146_a(new Slot(iinventory, j1 + k * 9 + 9, 8 + j1 * 18, 118 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.func_75146_a(new Slot(iinventory, l, 8 + l * 18, 176));
        }
        trunk.setOpen(true);
        trunk.field_70170_p.func_72956_a((Entity)trunk, "random.chestopen", 0.5f, trunk.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button) {
        if (button == 1) {
            this.trunk.setStay(!this.trunk.getStay());
            return true;
        }
        return false;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < this.numRows * 9 ? !this.func_75135_a(itemstack1, this.numRows * 9, this.field_75151_b.size(), true) : !this.func_75135_a(itemstack1, 0, this.numRows * 9, false)) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
        }
        return itemstack;
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        this.trunk.setOpen(false);
        this.trunk.field_70170_p.func_72956_a((Entity)this.trunk, "random.chestclosed", 0.5f, this.trunk.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
    }
}

