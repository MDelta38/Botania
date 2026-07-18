/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.tool.elementium;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IPixieSpawner;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;

public class ItemElementiumSword
extends ItemManasteelSword
implements IPixieSpawner {
    public ItemElementiumSword() {
        super(BotaniaAPI.elementiumToolMaterial, "elementiumSword");
    }

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0.05f;
    }
}

