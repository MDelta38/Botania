/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.crafting;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class ModElvenTradeRecipes {
    public static RecipeElvenTrade dreamwoodRecipe;
    public static List<RecipeElvenTrade> elementiumRecipes;
    public static RecipeElvenTrade pixieDustRecipe;
    public static List<RecipeElvenTrade> dragonstoneRecipes;
    public static RecipeElvenTrade elvenQuartzRecipe;
    public static RecipeElvenTrade alfglassRecipe;

    public static void init() {
        dreamwoodRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.dreamwood), "livingwood");
        elementiumRecipes = new ArrayList<RecipeElvenTrade>();
        elementiumRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 7), "ingotManasteel", "ingotManasteel"));
        elementiumRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.storage, 1, 2), new ItemStack(ModBlocks.storage), new ItemStack(ModBlocks.storage)));
        pixieDustRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 8), "manaPearl");
        dragonstoneRecipes = new ArrayList<RecipeElvenTrade>();
        dragonstoneRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource, 1, 9), "manaDiamond"));
        dragonstoneRecipes.add(BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.storage, 1, 4), new ItemStack(ModBlocks.storage, 1, 3)));
        elvenQuartzRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.quartz, 1, 5), new ItemStack(Items.field_151128_bU));
        alfglassRecipe = BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModBlocks.elfGlass), new ItemStack(ModBlocks.manaGlass));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Blocks.field_150339_S), new ItemStack(Blocks.field_150339_S));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151079_bi));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(Blocks.field_150484_ah), new ItemStack(Blocks.field_150484_ah));
    }
}

