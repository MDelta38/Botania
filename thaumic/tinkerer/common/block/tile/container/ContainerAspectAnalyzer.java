/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.container.SlotLimitedHasAspects
 */
package thaumic.tinkerer.common.block.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.SlotLimitedHasAspects;
import thaumic.tinkerer.common.block.tile.TileAspectAnalyzer;
import thaumic.tinkerer.common.block.tile.container.ContainerPlayerInv;

public class ContainerAspectAnalyzer
extends ContainerPlayerInv {
    public TileAspectAnalyzer analyzer;
    Slot slot;

    public ContainerAspectAnalyzer(TileAspectAnalyzer analyzer, InventoryPlayer playerInv) {
        super(playerInv);
        this.analyzer = analyzer;
        this.slot = new SlotLimitedHasAspects((IInventory)analyzer, 0, 20, 30);
        this.func_75146_a(this.slot);
        this.initPlayerInv();
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return this.analyzer.func_70300_a(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.field_75151_b.get(par2);
        if (var4 != null && var4.func_75216_d()) {
            ItemStack var5 = var4.func_75211_c();
            if (par2 == 0 || var5 != null && this.slot.func_75214_a(var5)) {
                var3 = var5.func_77946_l();
                if (par2 < 1 ? !this.func_75135_a(var5, 1, 37, false) : !this.func_75135_a(var5, 0, 1, false)) {
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

