/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.common.item.ModItems;

public class AncientWillRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundWill = false;
        boolean foundItem = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == ModItems.ancientWill && !foundWill) {
                foundWill = true;
                continue;
            }
            if (foundItem) continue;
            if (stack.func_77973_b() instanceof IAncientWillContainer) {
                foundItem = true;
                continue;
            }
            return false;
        }
        return foundWill && foundItem;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack item = null;
        int will = -1;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof IAncientWillContainer && item == null) {
                item = stack;
                continue;
            }
            will = stack.func_77960_j();
        }
        IAncientWillContainer container = (IAncientWillContainer)item.func_77973_b();
        if (container.hasAncientWill(item, will)) {
            return null;
        }
        ItemStack copy = item.func_77946_l();
        container.addAncientWill(copy, will);
        return copy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

