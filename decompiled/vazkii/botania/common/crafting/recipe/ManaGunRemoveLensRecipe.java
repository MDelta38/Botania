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
import vazkii.botania.common.item.ItemManaGun;

public class ManaGunRemoveLensRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundGun = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ItemManaGun && ItemManaGun.getLens(stack) != null) {
                foundGun = true;
                continue;
            }
            return false;
        }
        return foundGun;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack gun = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemManaGun)) continue;
            gun = stack;
        }
        ItemStack gunCopy = gun.func_77946_l();
        ItemManaGun.setLens(gunCopy, null);
        return gunCopy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

