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
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.common.item.ModItems;

public class PhantomInkRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundInk = false;
        boolean foundItem = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == ModItems.phantomInk && !foundInk) {
                foundInk = true;
                continue;
            }
            if (!foundItem) {
                if (stack.func_77973_b() instanceof IPhantomInkable && stack.func_77973_b().getContainerItem(stack) == null) {
                    foundItem = true;
                    continue;
                }
                return false;
            }
            return false;
        }
        return foundInk && foundItem;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack item = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof IPhantomInkable) || item != null) continue;
            item = stack;
        }
        IPhantomInkable inkable = (IPhantomInkable)item.func_77973_b();
        ItemStack copy = item.func_77946_l();
        inkable.setPhantomInk(copy, !inkable.hasPhantomInk(item));
        return copy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

