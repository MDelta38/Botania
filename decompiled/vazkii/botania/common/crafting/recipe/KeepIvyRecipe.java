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
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public class KeepIvyRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundIvy = false;
        boolean foundItem = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == ModItems.keepIvy) {
                foundIvy = true;
                continue;
            }
            if (!(foundItem || ItemNBTHelper.detectNBT(stack) && ItemNBTHelper.getBoolean(stack, "Botania_keepIvy", false) || stack.func_77973_b().hasContainerItem(stack))) {
                foundItem = true;
                continue;
            }
            return false;
        }
        return foundIvy && foundItem;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack item = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || stack.func_77973_b() == ModItems.keepIvy) continue;
            item = stack;
        }
        ItemStack copy = item.func_77946_l();
        ItemNBTHelper.setBoolean(copy, "Botania_keepIvy", true);
        copy.field_77994_a = 1;
        return copy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

