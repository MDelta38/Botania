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
import vazkii.botania.common.item.ModItems;

public class ManaGunClipRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundGun = false;
        boolean foundClip = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ItemManaGun && !ItemManaGun.hasClip(stack)) {
                foundGun = true;
                continue;
            }
            if (stack.func_77973_b() == ModItems.clip) {
                foundClip = true;
                continue;
            }
            return false;
        }
        return foundGun && foundClip;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack gun = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemManaGun)) continue;
            gun = stack;
        }
        if (gun == null) {
            return null;
        }
        ItemStack lens = ItemManaGun.getLens(gun);
        ItemManaGun.setLens(gun, null);
        ItemStack gunCopy = gun.func_77946_l();
        ItemManaGun.setClip(gunCopy, true);
        ItemManaGun.setLensAtPos(gunCopy, lens, 0);
        return gunCopy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

