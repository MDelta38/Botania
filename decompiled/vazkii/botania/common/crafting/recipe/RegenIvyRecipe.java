/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public class RegenIvyRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        Item item;
        ItemStack stack;
        int i;
        ItemStack tool = null;
        boolean foundIvy = false;
        int materialsFound = 0;
        for (i = 0; i < var1.func_70302_i_(); ++i) {
            stack = var1.func_70301_a(i);
            if (stack == null) continue;
            item = stack.func_77973_b();
            if (!(!item.isRepairable() || ItemNBTHelper.detectNBT(stack) && ItemNBTHelper.getBoolean(stack, "Botania_regenIvy", false))) {
                tool = stack;
                continue;
            }
            if (item != ModItems.regenIvy) continue;
            foundIvy = true;
        }
        for (i = 0; i < var1.func_70302_i_(); ++i) {
            stack = var1.func_70301_a(i);
            if (stack == null) continue;
            item = stack.func_77973_b();
            if (tool != null && tool.func_77973_b().func_82789_a(tool, stack)) {
                ++materialsFound;
                continue;
            }
            if (stack == tool || item == ModItems.regenIvy) continue;
            return false;
        }
        return tool != null && foundIvy && materialsFound == 3;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack tool = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !stack.func_77973_b().func_77645_m()) continue;
            tool = stack;
        }
        if (tool == null) {
            return null;
        }
        ItemStack toolCopy = tool.func_77946_l();
        ItemNBTHelper.setBoolean(toolCopy, "Botania_regenIvy", true);
        return toolCopy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

