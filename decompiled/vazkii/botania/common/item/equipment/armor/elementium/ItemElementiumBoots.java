/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.armor.elementium;

import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumArmor;

public class ItemElementiumBoots
extends ItemElementiumArmor {
    public ItemElementiumBoots() {
        super(3, "elementiumBoots");
    }

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0.09f;
    }
}

