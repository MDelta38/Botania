/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  thaumcraft.common.container.SlotLimitedByClass
 */
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.container.SlotLimitedByClass;
import witchinggadgets.api.IInfusedGem;
import witchinggadgets.common.gui.InventoryPrimordialRing;

public class SlotInfusedGem
extends SlotLimitedByClass {
    public SlotInfusedGem(IInventory par2iInventory, int id, int x, int y) {
        super(IInfusedGem.class, 1, par2iInventory, id, x, y);
    }

    public boolean func_75214_a(ItemStack stack) {
        return super.func_75214_a(stack);
    }

    public void func_82870_a(EntityPlayer player, ItemStack stack) {
        super.func_82870_a(player, stack);
        if (stack != null) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74768_a("BraceletSlot", -1);
        }
    }

    public void func_75215_d(ItemStack stack) {
        super.func_75215_d(stack);
        if (stack != null) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74768_a("BraceletSlot", this.field_75224_c instanceof InventoryPrimordialRing && this.field_75222_d != 0 ? this.field_75222_d + 2 : this.field_75222_d);
        }
    }
}

