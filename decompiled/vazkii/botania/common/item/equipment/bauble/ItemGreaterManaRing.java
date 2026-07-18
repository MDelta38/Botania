/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.bauble;

import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.equipment.bauble.ItemManaRing;

public class ItemGreaterManaRing
extends ItemManaRing {
    private static final int MAX_MANA = 2000000;

    public ItemGreaterManaRing() {
        super("manaRingGreater");
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return 2000000;
    }
}

