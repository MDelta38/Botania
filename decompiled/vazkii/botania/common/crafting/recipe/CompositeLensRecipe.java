/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ICompositableLens;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.ItemLens;

public class CompositeLensRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundLens = false;
        boolean foundSecondLens = false;
        boolean foundSlimeball = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ICompositableLens && !foundSecondLens) {
                if (foundLens) {
                    foundSecondLens = true;
                    continue;
                }
                foundLens = true;
                continue;
            }
            if (stack.func_77973_b() == Items.field_151123_aH) {
                foundSlimeball = true;
                continue;
            }
            return false;
        }
        return foundSecondLens && foundSlimeball;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack lens = null;
        ItemStack secondLens = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ICompositableLens)) continue;
            if (lens == null) {
                lens = stack;
                continue;
            }
            secondLens = stack;
        }
        if (lens.func_77973_b() instanceof ICompositableLens) {
            ICompositableLens lensItem = (ICompositableLens)lens.func_77973_b();
            if (secondLens == null || !lensItem.canCombineLenses(lens, secondLens) || lensItem.getCompositeLens(lens) != null || lensItem.getCompositeLens(secondLens) != null) {
                return null;
            }
            ItemStack lensCopy = lens.func_77946_l();
            ((ItemLens)ModItems.lens).setCompositeLens(lensCopy, secondLens);
            return lensCopy;
        }
        return null;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

