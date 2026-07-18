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
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.ILensControl;
import vazkii.botania.common.item.ItemManaGun;

public class ManaGunLensRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundLens = false;
        boolean foundGun = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ItemManaGun && ItemManaGun.getLens(stack) == null) {
                foundGun = true;
                continue;
            }
            if (stack.func_77973_b() instanceof ILens) {
                if (!(stack.func_77973_b() instanceof ILensControl) || !((ILensControl)stack.func_77973_b()).isControlLens(stack)) {
                    foundLens = true;
                    continue;
                }
                return false;
            }
            return false;
        }
        return foundLens && foundGun;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack lens = null;
        ItemStack gun = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ItemManaGun) {
                gun = stack;
                continue;
            }
            if (!(stack.func_77973_b() instanceof ILens)) continue;
            lens = stack;
        }
        if (lens == null || gun == null) {
            return null;
        }
        ItemStack gunCopy = gun.func_77946_l();
        ItemManaGun.setLens(gunCopy, lens);
        return gunCopy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

