/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.item;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumic.tinkerer.api.INoRemoveEnchant;

public class SpellClothRecipe
implements IRecipe {
    Item item;

    public SpellClothRecipe(Item item) {
        this.item = item;
    }

    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundCloth = false;
        boolean foundEnchanted = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77948_v() && !(stack.func_77973_b() instanceof INoRemoveEnchant) && !foundEnchanted) {
                foundEnchanted = true;
                continue;
            }
            if (stack.func_77973_b() == this.item && !foundCloth) {
                foundCloth = true;
                continue;
            }
            return false;
        }
        return foundCloth && foundEnchanted;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack stackToDisenchant = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !stack.func_77948_v()) continue;
            stackToDisenchant = stack.func_77946_l();
            break;
        }
        if (stackToDisenchant == null) {
            return null;
        }
        NBTTagCompound cmp = (NBTTagCompound)stackToDisenchant.func_77978_p().func_74737_b();
        cmp.func_82580_o("ench");
        stackToDisenchant.func_77982_d(cmp);
        return stackToDisenchant;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

