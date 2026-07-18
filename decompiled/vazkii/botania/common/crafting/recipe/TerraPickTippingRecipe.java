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
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public class TerraPickTippingRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundTerraPick = false;
        boolean foundElementiumPick = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() instanceof ItemTerraPick && !ItemTerraPick.isTipped(stack)) {
                foundTerraPick = true;
                continue;
            }
            if (stack.func_77973_b() == ModItems.elementiumPick) {
                foundElementiumPick = true;
                continue;
            }
            return false;
        }
        return foundTerraPick && foundElementiumPick;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack terraPick = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemTerraPick)) continue;
            terraPick = stack;
        }
        if (terraPick == null) {
            return null;
        }
        ItemStack terraPickCopy = terraPick.func_77946_l();
        ItemTerraPick.setTipped(terraPickCopy);
        return terraPickCopy;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

