/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerWorkbench
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotCrafting
 *  net.minecraft.world.World
 */
package vazkii.botania.client.gui.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.world.World;
import vazkii.botania.client.gui.crafting.InventoryCraftingHalo;

public class ContainerCraftingHalo
extends ContainerWorkbench {
    public ContainerCraftingHalo(InventoryPlayer p_i1808_1_, World p_i1808_2_) {
        super(p_i1808_1_, p_i1808_2_, 0, 0, 0);
        int i1;
        int l;
        this.field_75162_e = new InventoryCraftingHalo((Container)this, 3, 3);
        this.field_75151_b.clear();
        this.field_75153_a.clear();
        this.func_75146_a((Slot)new SlotCrafting(p_i1808_1_.field_70458_d, (IInventory)this.field_75162_e, this.field_75160_f, 0, 124, 35));
        for (l = 0; l < 3; ++l) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.func_75146_a(new Slot((IInventory)this.field_75162_e, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }
        for (l = 0; l < 3; ++l) {
            for (i1 = 0; i1 < 9; ++i1) {
                this.func_75146_a(new Slot((IInventory)p_i1808_1_, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }
        for (l = 0; l < 9; ++l) {
            this.func_75146_a(new Slot((IInventory)p_i1808_1_, l, 8 + l * 18, 142));
        }
        this.func_75130_a((IInventory)this.field_75162_e);
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return true;
    }
}

