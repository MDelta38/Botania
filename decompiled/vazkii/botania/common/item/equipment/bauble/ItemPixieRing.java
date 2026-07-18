/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.item.IPixieSpawner;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemPixieRing
extends ItemBauble
implements IPixieSpawner {
    public ItemPixieRing() {
        super("pixieRing");
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0.075f;
    }
}

