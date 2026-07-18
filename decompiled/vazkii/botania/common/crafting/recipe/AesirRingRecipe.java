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
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.ModItems;

public class AesirRingRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundThorRing = false;
        boolean foundOdinRing = false;
        boolean foundLokiRing = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == ModItems.thorRing && !foundThorRing) {
                foundThorRing = true;
                continue;
            }
            if (stack.func_77973_b() == ModItems.odinRing && !foundOdinRing) {
                foundOdinRing = true;
                continue;
            }
            if (stack.func_77973_b() == ModItems.lokiRing && !foundLokiRing) {
                foundLokiRing = true;
                continue;
            }
            return false;
        }
        return foundThorRing && foundOdinRing && foundLokiRing;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        String soulbind = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof IRelic) {
                String bind = ((IRelic)stack.func_77973_b()).getSoulbindUsername(stack);
                if (soulbind == null) {
                    soulbind = bind;
                    continue;
                }
                if (soulbind.equals(bind)) continue;
                return null;
            }
            return null;
        }
        ItemStack stack = new ItemStack(ModItems.aesirRing);
        ((IRelic)ModItems.aesirRing).bindToUsername(soulbind, stack);
        return stack;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

