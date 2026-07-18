/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.crafting.recipe;

import java.util.Arrays;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.common.item.lens.ItemLens;

public class LensDyeingRecipe
implements IRecipe {
    private static final List<String> DYES = Arrays.asList("dyeWhite", "dyeOrange", "dyeMagenta", "dyeLightBlue", "dyeYellow", "dyeLime", "dyePink", "dyeGray", "dyeLightGray", "dyeCyan", "dyePurple", "dyeBlue", "dyeBrown", "dyeGreen", "dyeRed", "dyeBlack", "manaPearl");

    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundLens = false;
        boolean foundDye = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ILens && !foundLens) {
                foundLens = true;
                continue;
            }
            if (!foundDye) {
                int color = this.getStackColor(stack);
                if (color > -1) {
                    foundDye = true;
                    continue;
                }
                return false;
            }
            return false;
        }
        return foundLens && foundDye;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack lens = null;
        int color = -1;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ILens && lens == null) {
                lens = stack;
                continue;
            }
            color = this.getStackColor(stack);
        }
        if (lens.func_77973_b() instanceof ILens) {
            lens.func_77973_b();
            ItemStack lensCopy = lens.func_77946_l();
            ItemLens.setLensColor(lensCopy, color);
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

    int getStackColor(ItemStack stack) {
        int[] ids;
        for (int i : ids = OreDictionary.getOreIDs((ItemStack)stack)) {
            int index = DYES.indexOf(OreDictionary.getOreName((int)i));
            if (index < 0) continue;
            return index;
        }
        return -1;
    }
}

