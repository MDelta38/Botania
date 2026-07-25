/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.ShapelessArcaneRecipe
 */
package witchinggadgets.common.util.recipe;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.util.Utilities;

public class PhotoDevelopingRecipe
extends ShapelessArcaneRecipe {
    public PhotoDevelopingRecipe() {
        super("SCANCAMERA", new ItemStack(WGContent.ItemMaterial, 1, 10), new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5), new Object[]{new ItemStack(WGContent.ItemMaterial, 1, 9), "dyeBlack", Items.field_151121_aF});
    }

    public AspectList getAspects(IInventory iinventoryCrafting) {
        ArrayList<ItemStack> paper = new ArrayList<ItemStack>();
        for (int i = 0; i < iinventoryCrafting.func_70302_i_(); ++i) {
            ItemStack stackInSlot = iinventoryCrafting.func_70301_a(i);
            if (stackInSlot == null || !stackInSlot.func_77973_b().equals(Items.field_151121_aF)) continue;
            paper.add(stackInSlot);
        }
        return new AspectList().add(Aspect.AIR, 5 * paper.size()).add(Aspect.WATER, 5 * paper.size()).add(Aspect.ORDER, 5 * paper.size());
    }

    public ItemStack getCraftingResult(IInventory iinventoryCrafting) {
        ItemStack photoplate = null;
        ArrayList<ItemStack> paper = new ArrayList<ItemStack>();
        for (int i = 0; i < iinventoryCrafting.func_70302_i_() && i != 10 && i != 9; ++i) {
            ItemStack stackInSlot = iinventoryCrafting.func_70301_a(i);
            if (stackInSlot == null) continue;
            if (stackInSlot.func_77973_b().equals(WGContent.ItemMaterial) && stackInSlot.func_77960_j() == 9) {
                photoplate = stackInSlot;
                continue;
            }
            if (!stackInSlot.func_77973_b().equals(Items.field_151121_aF)) continue;
            paper.add(stackInSlot);
        }
        ItemStack developed = new ItemStack(WGContent.ItemMaterial, paper.size(), 10);
        developed.func_77982_d(photoplate.func_77978_p());
        return developed;
    }

    public boolean matches(IInventory iinventoryCrafting, World world, EntityPlayer player) {
        ItemStack photoplate = null;
        ArrayList<ItemStack> paper = new ArrayList<ItemStack>();
        ArrayList<ItemStack> ink = new ArrayList<ItemStack>();
        for (int i = 0; i < iinventoryCrafting.func_70302_i_() && i != 10 && i != 9; ++i) {
            ItemStack stackInSlot = iinventoryCrafting.func_70301_a(i);
            if (stackInSlot == null) continue;
            if (stackInSlot.func_77973_b().equals(WGContent.ItemMaterial) && stackInSlot.func_77960_j() == 9) {
                if (!stackInSlot.func_77942_o()) {
                    return false;
                }
                if (photoplate != null) {
                    return false;
                }
                photoplate = stackInSlot;
                continue;
            }
            if (stackInSlot.func_77973_b().equals(Items.field_151121_aF)) {
                paper.add(stackInSlot);
                continue;
            }
            if (Utilities.compareToOreName(stackInSlot, "dyeBlack")) {
                ink.add(stackInSlot);
                continue;
            }
            return false;
        }
        return photoplate != null && !paper.isEmpty() && !ink.isEmpty() && paper.size() == ink.size();
    }
}

