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
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.gui.SlotOutput;

public class ContainerCuttingTable
extends Container {
    protected final TileEntityCuttingTable tileEntity;
    private int slotCount;

    public ContainerCuttingTable(InventoryPlayer inventoryPlayer, TileEntityCuttingTable te) {
        this.tileEntity = te;
        this.func_75146_a(new Slot((IInventory)this.tileEntity, 0, 42, 24));
        for (int i = 0; i < 3; ++i) {
            this.func_75146_a(new Slot(this.tileEntity, 1 + i, 60 + 20 * i, 56){

                public boolean func_75214_a(ItemStack stack) {
                    if (stack == null) {
                        return true;
                    }
                    if (stack.func_77973_b().equals(ConfigItems.itemEssence) || stack.func_77973_b().equals(ConfigItems.itemWispEssence)) {
                        AspectList aspects = new AspectList();
                        aspects.readFromNBT(stack.func_77978_p());
                        return ContainerCuttingTable.this.tileEntity.canAcceptAspect(aspects.getAspectsSortedAmount()[0]);
                    }
                    return false;
                }
            });
        }
        this.func_75146_a(new SlotOutput(this.tileEntity, 4, 118, 24){

            public void func_82870_a(EntityPlayer player, ItemStack stack) {
                this.field_75224_c.func_70298_a(0, 1);
                this.field_75224_c.func_70298_a(1, 1);
                this.field_75224_c.func_70298_a(2, 1);
                this.field_75224_c.func_70298_a(3, 1);
            }
        });
        this.slotCount = 5;
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
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < this.slotCount) {
                if (slot == 4) {
                    int maxStuff;
                    int n = maxStuff = ((Slot)this.field_75151_b.get(0)).func_75216_d() ? ((Slot)this.field_75151_b.get((int)0)).func_75211_c().field_77994_a : 0;
                    if (((Slot)this.field_75151_b.get(1)).func_75216_d()) {
                        maxStuff = Math.min(maxStuff, ((Slot)this.field_75151_b.get((int)1)).func_75211_c().field_77994_a);
                    }
                    if (((Slot)this.field_75151_b.get(2)).func_75216_d()) {
                        maxStuff = Math.min(maxStuff, ((Slot)this.field_75151_b.get((int)2)).func_75211_c().field_77994_a);
                    }
                    if (((Slot)this.field_75151_b.get(3)).func_75216_d()) {
                        maxStuff = Math.min(maxStuff, ((Slot)this.field_75151_b.get((int)3)).func_75211_c().field_77994_a);
                    }
                    stackInSlot.field_77994_a = maxStuff;
                    if (!this.func_75135_a(stackInSlot, this.slotCount, this.slotCount + 36, true)) {
                        return null;
                    }
                    for (int i = 0; i <= 4; ++i) {
                        this.tileEntity.func_70298_a(i, maxStuff);
                    }
                } else if (!this.func_75135_a(stackInSlot, this.slotCount, this.slotCount + 36, true)) {
                    return null;
                }
            } else if (this.func_75139_a(1).func_75214_a(stackInSlot) ? !this.func_75135_a(stackInSlot, 1, 4, false) : !this.func_75135_a(stackInSlot, 0, 1, false)) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
            if (stackInSlot.field_77994_a == stack.field_77994_a) {
                return null;
            }
            slotObject.func_82870_a(player, stackInSlot);
        }
        return stack;
    }
}

