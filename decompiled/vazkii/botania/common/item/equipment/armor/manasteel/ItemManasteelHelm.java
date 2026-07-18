/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.armor.manasteel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;

public class ItemManasteelHelm
extends ItemManasteelArmor
implements IManaDiscountArmor {
    public ItemManasteelHelm() {
        this("manasteelHelm");
    }

    public ItemManasteelHelm(String name) {
        super(0, name);
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.1f : 0.0f;
    }
}

