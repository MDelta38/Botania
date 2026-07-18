/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class HeadRecipe
extends RecipeRuneAltar {
    String name = "";

    public HeadRecipe(ItemStack output, int mana, Object ... inputs) {
        super(output, mana, inputs);
    }

    @Override
    public boolean matches(IInventory inv) {
        boolean matches = super.matches(inv);
        if (matches) {
            ItemStack stack;
            for (int i = 0; i < inv.func_70302_i_() && (stack = inv.func_70301_a(i)) != null; ++i) {
                if (stack.func_77973_b() != Items.field_151057_cb) continue;
                this.name = stack.func_82833_r();
                if (!this.name.equals(StatCollector.func_74838_a((String)"item.nameTag.name"))) continue;
                return false;
            }
        }
        return matches;
    }

    @Override
    public ItemStack getOutput() {
        ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
        if (!this.name.isEmpty()) {
            ItemNBTHelper.setString(stack, "SkullOwner", this.name);
        }
        return stack;
    }
}

