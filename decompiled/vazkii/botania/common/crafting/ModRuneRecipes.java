/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.crafting;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.crafting.recipe.HeadRecipe;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public final class ModRuneRecipes {
    public static RecipeRuneAltar recipeWaterRune;
    public static RecipeRuneAltar recipeFireRune;
    public static List<RecipeRuneAltar> recipesEarthRune;
    public static List<RecipeRuneAltar> recipesAirRune;
    public static RecipeRuneAltar recipeSpringRune;
    public static RecipeRuneAltar recipeSummerRune;
    public static RecipeRuneAltar recipeAutumnRune;
    public static List<RecipeRuneAltar> recipesWinterRune;
    public static RecipeRuneAltar recipeManaRune;
    public static RecipeRuneAltar recipeLustRune;
    public static RecipeRuneAltar recipeGluttonyRune;
    public static RecipeRuneAltar recipeGreedRune;
    public static RecipeRuneAltar recipeSlothRune;
    public static RecipeRuneAltar recipeWrathRune;
    public static RecipeRuneAltar recipeEnvyRune;
    public static RecipeRuneAltar recipePrideRune;
    public static RecipeRuneAltar recipeHead;

    public static void init() {
        int i;
        int costTier1 = 5200;
        int costTier2 = 8000;
        int costTier3 = 12000;
        recipeWaterRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 2, 0), 5200, "powderMana", "ingotManasteel", new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(Items.field_151120_aE), new ItemStack((Item)Items.field_151112_aM));
        recipeFireRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 2, 1), 5200, "powderMana", "ingotManasteel", new ItemStack(Items.field_151130_bT), new ItemStack(Items.field_151016_H), new ItemStack(Items.field_151075_bm));
        recipesEarthRune = new ArrayList<RecipeRuneAltar>();
        recipesEarthRune.add(BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 2, 2), 5200, "powderMana", "ingotManasteel", "stone", new ItemStack(Blocks.field_150402_ci), new ItemStack((Block)Blocks.field_150338_P)));
        recipesEarthRune.add(BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 2, 2), 5200, "powderMana", "ingotManasteel", "stone", new ItemStack(Blocks.field_150402_ci), new ItemStack((Block)Blocks.field_150337_Q)));
        recipesAirRune = new ArrayList<RecipeRuneAltar>();
        for (i = 0; i < 16; ++i) {
            recipesAirRune.add(BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 2, 3), 5200, "powderMana", "ingotManasteel", new ItemStack(Blocks.field_150404_cg, 1, i), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151007_F)));
        }
        recipeSpringRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 4), 8000, LibOreDict.RUNE[0], LibOreDict.RUNE[1], "treeSapling", "treeSapling", "treeSapling", new ItemStack(Items.field_151015_O));
        recipeSummerRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 5), 8000, LibOreDict.RUNE[2], LibOreDict.RUNE[3], new ItemStack(Block.func_149684_b((String)"sand")), new ItemStack(Block.func_149684_b((String)"sand")), new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151127_ba));
        recipeAutumnRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 6), 8000, LibOreDict.RUNE[1], LibOreDict.RUNE[3], "treeLeaves", "treeLeaves", "treeLeaves", new ItemStack(Items.field_151070_bp));
        recipesWinterRune = new ArrayList<RecipeRuneAltar>();
        for (i = 0; i < 16; ++i) {
            recipesWinterRune.add(BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 7), 8000, LibOreDict.RUNE[0], LibOreDict.RUNE[2], new ItemStack(Blocks.field_150433_aE), new ItemStack(Blocks.field_150433_aE), new ItemStack(Blocks.field_150325_L, 1, i), new ItemStack(Items.field_151105_aU)));
        }
        recipeManaRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 8), 8000, "ingotManasteel", "ingotManasteel", "ingotManasteel", "ingotManasteel", "ingotManasteel", "manaPearl");
        recipeLustRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 9), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[5], LibOreDict.RUNE[3]);
        recipeGluttonyRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 10), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[7], LibOreDict.RUNE[1]);
        recipeGreedRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 11), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[4], LibOreDict.RUNE[0]);
        recipeSlothRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 12), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[6], LibOreDict.RUNE[3]);
        recipeWrathRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 13), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[7], LibOreDict.RUNE[2]);
        recipeEnvyRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 14), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[7], LibOreDict.RUNE[0]);
        recipePrideRune = BotaniaAPI.registerRuneAltarRecipe(new ItemStack(ModItems.rune, 1, 15), 12000, "manaDiamond", "manaDiamond", LibOreDict.RUNE[5], LibOreDict.RUNE[1]);
        recipeHead = new HeadRecipe(new ItemStack(Items.field_151144_bL, 1, 3), 22500, new Object[]{new ItemStack(Items.field_151144_bL), "elvenPixieDust", "shardPrismarine", new ItemStack(Items.field_151057_cb), new ItemStack(Items.field_151153_ao)});
        BotaniaAPI.runeAltarRecipes.add(recipeHead);
    }
}

