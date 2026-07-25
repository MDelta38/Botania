/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.block.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.block.tile.container.ContainerPlayerInv;
import thaumic.tinkerer.common.block.tile.container.slot.SlotTool;
import thaumic.tinkerer.common.block.tile.container.slot.SlotWand;

public class ContainerEnchanter
extends ContainerPlayerInv {
    TileEnchanter enchanter;

    public ContainerEnchanter(TileEnchanter enchanter, InventoryPlayer playerInv) {
        super(playerInv);
        this.enchanter = enchanter;
        this.func_75146_a(new SlotTool(enchanter, 0, 6, 6));
        this.func_75146_a(new SlotWand((IInventory)enchanter, 1, 6, 31));
        this.initPlayerInv();
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.field_75151_b.get(par2);
        if (var4 != null && var4.func_75216_d()) {
            ItemStack var5 = var4.func_75211_c();
            if (var5 != null && var5.func_77973_b() == Items.field_151122_aG) {
                return null;
            }
            boolean wand = ((Slot)this.field_75151_b.get(1)).func_75214_a(var5);
            var3 = var5.func_77946_l();
            if (par2 < 2 ? !this.func_75135_a(var5, 2, 38, false) : (wand ? !this.func_75135_a(var5, 1, 2, false) : var5.func_77973_b().func_77616_k(var5) && !this.func_75135_a(var5, 0, 1, false))) {
                return null;
            }
            if (var5.field_77994_a == 0) {
                var4.func_75215_d(null);
            } else {
                var4.func_75218_e();
            }
            if (var5.field_77994_a == var3.field_77994_a) {
                return null;
            }
            var4.func_82870_a(par1EntityPlayer, var5);
        }
        return var3;
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return this.enchanter.func_70300_a(entityplayer);
    }
}

