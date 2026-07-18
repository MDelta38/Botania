/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.container.SlotBauble
 *  baubles.common.lib.PlayerHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.gui.box;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.container.SlotBauble;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.gui.SlotLocked;
import vazkii.botania.client.gui.box.InventoryBaubleBox;
import vazkii.botania.client.gui.box.SlotAnyBauble;

public class ContainerBaubleBox
extends Container {
    InventoryBaubleBox baubleBoxInv;
    InventoryBaubles baubles;

    public ContainerBaubleBox(EntityPlayer player) {
        int j;
        int i;
        int slot = player.field_71071_by.field_70461_c;
        InventoryPlayer playerInv = player.field_71071_by;
        this.baubleBoxInv = new InventoryBaubleBox(player, slot);
        this.baubles = new InventoryBaubles(player);
        this.baubles.setEventHandler((Container)this);
        if (!player.field_70170_p.field_72995_K) {
            this.baubles.stackList = PlayerHandler.getPlayerBaubles((EntityPlayer)player).stackList;
        }
        this.func_75146_a((Slot)new SlotBauble((IInventory)this.baubles, BaubleType.AMULET, 0, 8, 8));
        this.func_75146_a((Slot)new SlotBauble((IInventory)this.baubles, BaubleType.RING, 1, 8, 26));
        this.func_75146_a((Slot)new SlotBauble((IInventory)this.baubles, BaubleType.RING, 2, 8, 44));
        this.func_75146_a((Slot)new SlotBauble((IInventory)this.baubles, BaubleType.BELT, 3, 8, 62));
        for (i = 0; i < 4; ++i) {
            for (j = 0; j < 6; ++j) {
                int k = j + i * 6;
                this.func_75146_a(new SlotAnyBauble(this.baubleBoxInv, k, 62 + j * 18, 8 + i * 18));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            if (player.field_71071_by.field_70461_c == i) {
                this.func_75146_a(new SlotLocked((IInventory)playerInv, i, 8 + i * 18, 142));
                continue;
            }
            this.func_75146_a(new Slot((IInventory)playerInv, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        boolean can = this.baubleBoxInv.func_70300_a(player);
        if (!can) {
            this.func_75134_a(player);
        }
        return can;
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        this.baubleBoxInv.pushInventory();
        if (!player.field_70170_p.field_72995_K) {
            PlayerHandler.setPlayerBaubles((EntityPlayer)player, (InventoryBaubles)this.baubles);
        }
    }

    public void func_75131_a(ItemStack[] p_75131_1_) {
        this.baubles.blockEvents = true;
        super.func_75131_a(p_75131_1_);
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            System.out.println(p_82846_2_ + " " + itemstack);
            if (p_82846_2_ < 28 ? !this.func_75135_a(itemstack1, 28, 64, true) : itemstack1 != null && (itemstack1.func_77973_b() instanceof IBauble || itemstack1.func_77973_b() instanceof IManaItem) && !this.func_75135_a(itemstack1, 4, 28, false)) {
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
            slot.func_82870_a(p_82846_1_, itemstack1);
        }
        return itemstack;
    }
}

