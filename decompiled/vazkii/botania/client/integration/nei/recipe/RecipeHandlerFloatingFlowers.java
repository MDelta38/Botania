/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.integration.nei.recipe;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.ModBlocks;

public class RecipeHandlerFloatingFlowers
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.floatingFlowers");
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(84, 23, 24, 18), "crafting", new Object[0]));
    }

    public String getGuiTexture() {
        return "textures/gui/container/crafting_table.png";
    }

    public void loadCraftingRecipes(ItemStack result) {
        if (Block.func_149634_a((Item)result.func_77973_b()) instanceof BlockFloatingSpecialFlower) {
            ItemStack floatingFlower = new ItemStack(ModBlocks.floatingFlower, 1, Short.MAX_VALUE);
            ItemStack specialFlower = new ItemStack(ModBlocks.specialFlower);
            specialFlower.func_77982_d((NBTTagCompound)result.func_77978_p().func_74737_b());
            this.arecipes.add(new CachedFloatingFlowerRecipe(floatingFlower, specialFlower, result.func_77946_l()));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        if (Block.func_149634_a((Item)ingredient.func_77973_b()) instanceof BlockSpecialFlower) {
            ItemStack floatingFlower = new ItemStack(ModBlocks.floatingFlower, 1, Short.MAX_VALUE);
            ItemStack result = new ItemStack(ModBlocks.floatingSpecialFlower);
            result.func_77982_d((NBTTagCompound)ingredient.func_77978_p().func_74737_b());
            this.arecipes.add(new CachedFloatingFlowerRecipe(floatingFlower, ingredient.func_77946_l(), result));
        }
    }

    public class CachedFloatingFlowerRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;

        public CachedFloatingFlowerRecipe(ItemStack floatingFlower, ItemStack specialFlower, ItemStack output) {
            super((TemplateRecipeHandler)RecipeHandlerFloatingFlowers.this);
            this.inputs = new ArrayList<PositionedStack>();
            this.inputs.add(new PositionedStack((Object)floatingFlower, 25, 6));
            this.inputs.add(new PositionedStack((Object)specialFlower, 43, 6));
            this.output = new PositionedStack((Object)output, 119, 24);
            this.output.setMaxSize(1);
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerFloatingFlowers.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}

