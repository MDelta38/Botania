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
 *  net.minecraft.world.World
 */
package witchinggadgets.common.gui;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import witchinggadgets.common.gui.InventoryPrimordialGlove;
import witchinggadgets.common.gui.SlotInfusedGem;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;

public class ContainerPrimordialGlove
extends Container {
    private World worldObj;
    private int blockedSlot;
    public IInventory input = new InventoryPrimordialGlove(this);
    ItemStack bracelet = null;
    EntityPlayer player = null;
    private int slotAmount = 5;
    public static HashMap<Integer, ContainerPrimordialGlove> map = new HashMap();

    public ContainerPrimordialGlove(InventoryPlayer iinventory, World world, int x, int y, int z) {
        this.worldObj = world;
        this.player = iinventory.field_70458_d;
        this.bracelet = iinventory.func_70448_g();
        this.blockedSlot = iinventory.field_70461_c + 45;
        this.func_75146_a((Slot)new SlotInfusedGem(this.input, 0, 60, 6));
        this.func_75146_a((Slot)new SlotInfusedGem(this.input, 1, 100, 6));
        this.func_75146_a((Slot)new SlotInfusedGem(this.input, 2, 57, 42));
        this.func_75146_a((Slot)new SlotInfusedGem(this.input, 3, 80, 53));
        this.func_75146_a((Slot)new SlotInfusedGem(this.input, 4, 103, 42));
        this.bindPlayerInventory(iinventory);
        if (!world.field_72995_K) {
            try {
                map.put(this.player.func_145782_y(), this);
                ((InventoryPrimordialGlove)this.input).stackList = ItemPrimordialGlove.getSetGems(this.bracelet);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.func_75130_a(this.input);
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

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < this.slotAmount ? !this.func_75135_a(stackInSlot, this.slotAmount, this.field_75151_b.size(), true) : !this.func_75135_a(stackInSlot, 0, this.slotAmount, false)) {
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

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockedSlot || par2 == 0 && par3 == this.blockedSlot) {
            return null;
        }
        ItemPrimordialGlove.setSetGems(this.bracelet, ((InventoryPrimordialGlove)this.input).stackList);
        return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            map.remove(this.player.func_145782_y());
            ItemPrimordialGlove.setSetGems(this.bracelet, ((InventoryPrimordialGlove)this.input).stackList);
            if (!this.bracelet.equals(this.player.func_71045_bC())) {
                this.player.func_70062_b(0, this.bracelet);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }
}

