/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public class HelmRevealingRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        Item goggles = (Item)Item.field_150901_e.func_82594_a("Thaumcraft:ItemGoggles");
        if (goggles == null) {
            return false;
        }
        boolean foundGoggles = false;
        boolean foundHelm = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (this.checkHelm(stack)) {
                foundHelm = true;
                continue;
            }
            if (stack.func_77973_b() == goggles) {
                foundGoggles = true;
                continue;
            }
            return false;
        }
        return foundGoggles && foundHelm;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack newHelm;
        ItemStack helm = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || !this.checkHelm(stack)) continue;
            helm = stack;
        }
        if (helm == null) {
            return null;
        }
        ItemStack helmCopy = helm.func_77946_l();
        Item helmItem = helmCopy.func_77973_b();
        if (helmItem == ModItems.manasteelHelm) {
            newHelm = new ItemStack(ModItems.manasteelHelmRevealing);
        } else if (helmItem == ModItems.terrasteelHelm) {
            newHelm = new ItemStack(ModItems.terrasteelHelmRevealing);
        } else if (helmItem == ModItems.elementiumHelm) {
            newHelm = new ItemStack(ModItems.elementiumHelmRevealing);
        } else {
            return null;
        }
        for (int i = 0; i < 6; ++i) {
            if (!ItemNBTHelper.getBoolean(helmCopy, "AncientWill" + i, false)) continue;
            ItemNBTHelper.setBoolean(newHelm, "AncientWill" + i, true);
        }
        NBTTagList enchList = ItemNBTHelper.getList(helmCopy, "ench", 10, true);
        if (enchList != null) {
            ItemNBTHelper.setList(newHelm, "ench", enchList);
        }
        byte runicHardening = ItemNBTHelper.getByte(helmCopy, "RS.HARDEN", (byte)0);
        ItemNBTHelper.setByte(newHelm, "RS.HARDEN", runicHardening);
        return newHelm;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return new ItemStack(ModItems.manasteelHelmRevealing);
    }

    private boolean checkHelm(ItemStack helmStack) {
        Item helmItem = helmStack.func_77973_b();
        return helmItem == ModItems.manasteelHelm || helmItem == ModItems.terrasteelHelm || helmItem == ModItems.elementiumHelm;
    }
}

