/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.mana;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaItem;

public final class ManaItemHandler {
    public static int requestMana(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
        int invSize;
        if (stack == null) {
            return 0;
        }
        InventoryPlayer mainInv = player.field_71071_by;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(player);
        int size = invSize = mainInv.func_70302_i_();
        if (baublesInv != null) {
            size += baublesInv.func_70302_i_();
        }
        for (int i = 0; i < size; ++i) {
            IManaItem manaItem;
            int slot;
            boolean useBaubles = i >= invSize;
            Object inv = useBaubles ? baublesInv : mainInv;
            ItemStack stackInSlot = inv.func_70301_a(slot = i - (useBaubles ? invSize : 0));
            if (stackInSlot == stack || stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IManaItem) || !(manaItem = (IManaItem)stackInSlot.func_77973_b()).canExportManaToItem(stackInSlot, stack) || manaItem.getMana(stackInSlot) <= 0 || stack.func_77973_b() instanceof IManaItem && !((IManaItem)stack.func_77973_b()).canReceiveManaFromItem(stack, stackInSlot)) continue;
            int mana = Math.min(manaToGet, manaItem.getMana(stackInSlot));
            if (remove) {
                manaItem.addMana(stackInSlot, -mana);
            }
            if (useBaubles) {
                BotaniaAPI.internalHandler.sendBaubleUpdatePacket(player, slot);
            }
            return mana;
        }
        return 0;
    }

    public static boolean requestManaExact(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
        int invSize;
        if (stack == null) {
            return false;
        }
        InventoryPlayer mainInv = player.field_71071_by;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(player);
        int size = invSize = mainInv.func_70302_i_();
        if (baublesInv != null) {
            size += baublesInv.func_70302_i_();
        }
        for (int i = 0; i < size; ++i) {
            IManaItem manaItemSlot;
            int slot;
            boolean useBaubles = i >= invSize;
            Object inv = useBaubles ? baublesInv : mainInv;
            ItemStack stackInSlot = inv.func_70301_a(slot = i - (useBaubles ? invSize : 0));
            if (stackInSlot == stack || stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IManaItem) || !(manaItemSlot = (IManaItem)stackInSlot.func_77973_b()).canExportManaToItem(stackInSlot, stack) || manaItemSlot.getMana(stackInSlot) <= manaToGet || stack.func_77973_b() instanceof IManaItem && !((IManaItem)stack.func_77973_b()).canReceiveManaFromItem(stack, stackInSlot)) continue;
            if (remove) {
                manaItemSlot.addMana(stackInSlot, -manaToGet);
            }
            if (useBaubles) {
                BotaniaAPI.internalHandler.sendBaubleUpdatePacket(player, slot);
            }
            return true;
        }
        return false;
    }

    public static int dispatchMana(ItemStack stack, EntityPlayer player, int manaToSend, boolean add) {
        int invSize;
        if (stack == null) {
            return 0;
        }
        InventoryPlayer mainInv = player.field_71071_by;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(player);
        int size = invSize = mainInv.func_70302_i_();
        if (baublesInv != null) {
            size += baublesInv.func_70302_i_();
        }
        for (int i = 0; i < size; ++i) {
            IManaItem manaItemSlot;
            int slot;
            boolean useBaubles = i >= invSize;
            Object inv = useBaubles ? baublesInv : mainInv;
            ItemStack stackInSlot = inv.func_70301_a(slot = i - (useBaubles ? invSize : 0));
            if (stackInSlot == stack || stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IManaItem) || !(manaItemSlot = (IManaItem)stackInSlot.func_77973_b()).canReceiveManaFromItem(stackInSlot, stack) || stack.func_77973_b() instanceof IManaItem && !((IManaItem)stack.func_77973_b()).canExportManaToItem(stack, stackInSlot)) continue;
            int received = 0;
            received = manaItemSlot.getMana(stackInSlot) + manaToSend <= manaItemSlot.getMaxMana(stackInSlot) ? manaToSend : manaToSend - (manaItemSlot.getMana(stackInSlot) + manaToSend - manaItemSlot.getMaxMana(stackInSlot));
            if (add) {
                manaItemSlot.addMana(stackInSlot, manaToSend);
            }
            if (useBaubles) {
                BotaniaAPI.internalHandler.sendBaubleUpdatePacket(player, slot);
            }
            return received;
        }
        return 0;
    }

    public static boolean dispatchManaExact(ItemStack stack, EntityPlayer player, int manaToSend, boolean add) {
        int invSize;
        if (stack == null) {
            return false;
        }
        InventoryPlayer mainInv = player.field_71071_by;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(player);
        int size = invSize = mainInv.func_70302_i_();
        if (baublesInv != null) {
            size += baublesInv.func_70302_i_();
        }
        for (int i = 0; i < size; ++i) {
            IManaItem manaItemSlot;
            int slot;
            boolean useBaubles = i >= invSize;
            Object inv = useBaubles ? baublesInv : mainInv;
            ItemStack stackInSlot = inv.func_70301_a(slot = i - (useBaubles ? invSize : 0));
            if (stackInSlot == stack || stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IManaItem) || (manaItemSlot = (IManaItem)stackInSlot.func_77973_b()).getMana(stackInSlot) + manaToSend > manaItemSlot.getMaxMana(stackInSlot) || !manaItemSlot.canReceiveManaFromItem(stackInSlot, stack) || stack.func_77973_b() instanceof IManaItem && !((IManaItem)stack.func_77973_b()).canExportManaToItem(stack, stackInSlot)) continue;
            if (add) {
                manaItemSlot.addMana(stackInSlot, manaToSend);
            }
            if (useBaubles) {
                BotaniaAPI.internalHandler.sendBaubleUpdatePacket(player, slot);
            }
            return true;
        }
        return false;
    }

    public static int requestManaForTool(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
        float multiplier = Math.max(0.0f, 1.0f - ManaItemHandler.getFullDiscountForTools(player));
        int cost = (int)((float)manaToGet * multiplier);
        return (int)((float)ManaItemHandler.requestMana(stack, player, cost, remove) / multiplier);
    }

    public static boolean requestManaExactForTool(ItemStack stack, EntityPlayer player, int manaToGet, boolean remove) {
        float multiplier = Math.max(0.0f, 1.0f - ManaItemHandler.getFullDiscountForTools(player));
        int cost = (int)((float)manaToGet * multiplier);
        return ManaItemHandler.requestManaExact(stack, player, cost, remove);
    }

    public static float getFullDiscountForTools(EntityPlayer player) {
        float discount = 0.0f;
        for (int i = 0; i < player.field_71071_by.field_70460_b.length; ++i) {
            ItemStack armor = player.field_71071_by.field_70460_b[i];
            if (armor == null || !(armor.func_77973_b() instanceof IManaDiscountArmor)) continue;
            discount += ((IManaDiscountArmor)armor.func_77973_b()).getDiscount(armor, i, player);
        }
        return discount;
    }
}

