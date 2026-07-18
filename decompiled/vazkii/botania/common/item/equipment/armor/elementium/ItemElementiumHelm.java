/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.armor.elementium;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumArmor;

public class ItemElementiumHelm
extends ItemElementiumArmor
implements IManaDiscountArmor {
    public ItemElementiumHelm() {
        this("elementiumHelm");
    }

    public ItemElementiumHelm(String name) {
        super(0, name);
    }

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0.11f;
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.1f : 0.0f;
    }
}

