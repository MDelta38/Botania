/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.container.InventoryFocusPouch
 *  thaumcraft.common.items.wands.ItemFocusPouch
 */
package thaumic.tinkerer.common.block.tile.container.kami;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.InventoryFocusPouch;
import thaumcraft.common.items.wands.ItemFocusPouch;
import thaumic.tinkerer.common.block.tile.container.ContainerPlayerInv;
import thaumic.tinkerer.common.block.tile.container.slot.kami.SlotNoPouches;

public class ContainerIchorPouch
extends ContainerPlayerInv {
    public IInventory inv = new InventoryIchorPouch(this);
    EntityPlayer player;
    ItemStack pouch;
    int blockSlot;

    public ContainerIchorPouch(EntityPlayer player) {
        super(player.field_71071_by);
        this.player = player;
        this.pouch = player.func_71045_bC();
        this.blockSlot = player.field_71071_by.field_70461_c + 27 + 117;
        for (int y = 0; y < 9; ++y) {
            for (int x = 0; x < 13; ++x) {
                this.func_75146_a(new SlotNoPouches(this.inv, y * 13 + x, 12 + x * 18, 8 + y * 18));
            }
        }
        this.initPlayerInv();
        if (!player.field_70170_p.field_72995_K) {
            try {
                ((InventoryIchorPouch)this.inv).stackList = ((ItemFocusPouch)this.pouch.func_77973_b()).getInventory(this.pouch);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        if (slot == this.blockSlot) {
            return null;
        }
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < 117 ? !this.inv.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 117, this.field_75151_b.size(), true) : !this.inv.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 0, 117, false)) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
        }
        return stack;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockSlot) {
            return null;
        }
        return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.player.field_70170_p.field_72995_K) {
            ((ItemFocusPouch)this.pouch.func_77973_b()).setInventory(this.pouch, ((InventoryIchorPouch)this.inv).stackList);
            if (this.player == null) {
                return;
            }
            if (this.player.func_70694_bm() != null && this.player.func_70694_bm().func_77969_a(this.pouch)) {
                this.player.func_70062_b(0, this.pouch);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public int getInvXStart() {
        return 48;
    }

    @Override
    public int getInvYStart() {
        return 177;
    }

    private static class InventoryIchorPouch
    extends InventoryFocusPouch {
        public InventoryIchorPouch(Container par1Container) {
            super(par1Container);
            this.stackList = new ItemStack[117];
        }

        public int func_70297_j_() {
            return 64;
        }

        public boolean func_94041_b(int i, ItemStack itemstack) {
            return itemstack != null && !(itemstack.func_77973_b() instanceof ItemFocusPouch);
        }
    }
}

