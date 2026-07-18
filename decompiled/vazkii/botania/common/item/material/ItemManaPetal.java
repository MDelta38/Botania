/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.material;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.item.Item16Colors;

public class ItemManaPetal
extends Item16Colors
implements IFlowerComponent {
    public ItemManaPetal() {
        super("manaPetal");
    }

    @Override
    public boolean canFit(ItemStack stack, IInventory apothecary) {
        return true;
    }

    @Override
    public int getParticleColor(ItemStack stack) {
        return this.func_82790_a(stack, 0);
    }
}

