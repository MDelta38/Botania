/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.block.tile.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.block.tile.TileEnchanter;

public class SlotTool
extends Slot {
    TileEnchanter enchanter;

    public SlotTool(TileEnchanter enchanter, int par2, int par3, int par4) {
        super((IInventory)enchanter, par2, par3, par4);
        this.enchanter = enchanter;
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return !this.enchanter.working && par1ItemStack.func_77973_b() != Items.field_151122_aG && par1ItemStack.func_77973_b().func_77616_k(par1ItemStack);
    }

    public int func_75219_a() {
        return 1;
    }

    public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
        return !this.enchanter.working;
    }
}

