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
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.ICosmeticBauble;

public class CosmeticAttachRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundCosmetic = false;
        boolean foundAttachable = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ICosmeticBauble && !foundCosmetic) {
                foundCosmetic = true;
                continue;
            }
            if (foundAttachable) continue;
            if (stack.func_77973_b() instanceof ICosmeticAttachable && !(stack.func_77973_b() instanceof ICosmeticBauble) && ((ICosmeticAttachable)stack.func_77973_b()).getCosmeticItem(stack) == null) {
                foundAttachable = true;
                continue;
            }
            return false;
        }
        return foundCosmetic && foundAttachable;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack cosmeticItem = null;
        ItemStack attachableItem = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ICosmeticBauble && cosmeticItem == null) {
                cosmeticItem = stack;
                continue;
            }
            attachableItem = stack;
        }
        ICosmeticAttachable attachable = (ICosmeticAttachable)attachableItem.func_77973_b();
        if (attachable.getCosmeticItem(attachableItem) != null) {
            return null;
        }
        ItemStack copy = attachableItem.func_77946_l();
        attachable.setCosmeticItem(copy, cosmeticItem);
        return copy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

