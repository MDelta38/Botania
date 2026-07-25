/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.block.tile.container.kami;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.container.ContainerPlayerInv;
import thaumic.tinkerer.common.block.tile.container.slot.kami.SlotSkyPearl;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;

public class ContainerWarpGate
extends ContainerPlayerInv {
    TileWarpGate gate;

    public ContainerWarpGate(TileWarpGate gate, InventoryPlayer playerInv) {
        super(playerInv);
        this.gate = gate;
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 5; ++x) {
                this.func_75146_a(new SlotSkyPearl(gate, y * 5 + x, 30 + x * 25, 27 + y * 25));
            }
        }
        this.initPlayerInv();
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return this.gate.func_70300_a(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.field_75151_b.get(par2);
        if (var4 != null && var4.func_75216_d()) {
            ItemStack var5 = var4.func_75211_c();
            if (par2 < 10 || var5 != null) {
                var3 = var5.func_77946_l();
                if (par2 < 10 ? !this.func_75135_a(var5, 10, 36, false) : var3.func_77973_b() == ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class) && !this.func_75135_a(var5, 0, 10, false)) {
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
        }
        return var3;
    }
}

