/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.common.item.ItemBlackHoleTalisman;
import vazkii.botania.common.item.ModItems;

public class BlackHoleTalismanExtractRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundTalisman = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == ModItems.blackHoleTalisman && !foundTalisman) {
                foundTalisman = true;
                continue;
            }
            return false;
        }
        return foundTalisman;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack talisman = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            talisman = stack;
        }
        int count = ItemBlackHoleTalisman.getBlockCount(talisman);
        if (count > 0) {
            Block block = ItemBlackHoleTalisman.getBlock(talisman);
            int meta = ItemBlackHoleTalisman.getBlockMeta(talisman);
            return new ItemStack(block, Math.min(64, count), meta);
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

