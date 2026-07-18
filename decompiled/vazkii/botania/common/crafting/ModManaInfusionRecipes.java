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
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public final class ModManaInfusionRecipes {
    public static List<RecipeManaInfusion> manasteelRecipes;
    public static RecipeManaInfusion manaPearlRecipe;
    public static List<RecipeManaInfusion> manaDiamondRecipes;
    public static List<RecipeManaInfusion> manaPowderRecipes;
    public static RecipeManaInfusion pistonRelayRecipe;
    public static RecipeManaInfusion manaCookieRecipe;
    public static RecipeManaInfusion grassSeedsRecipe;
    public static RecipeManaInfusion podzolSeedsRecipe;
    public static List<RecipeManaInfusion> mycelSeedsRecipes;
    public static RecipeManaInfusion manaQuartzRecipe;
    public static RecipeManaInfusion tinyPotatoRecipe;
    public static RecipeManaInfusion manaInkwellRecipe;
    public static RecipeManaInfusion managlassRecipe;
    public static RecipeManaInfusion manaStringRecipe;
    public static RecipeManaInfusion sugarCaneRecipe;

    public static void init() {
        manasteelRecipes = new ArrayList<RecipeManaInfusion>();
        manasteelRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 0), "ingotIron", 3000));
        manasteelRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModBlocks.storage, 1, 0), new ItemStack(Blocks.field_150339_S), 27000));
        manaPearlRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 1), new ItemStack(Items.field_151079_bi), 6000);
        manaDiamondRecipes = new ArrayList<RecipeManaInfusion>();
        manaDiamondRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 2), "gemDiamond", 10000));
        manaDiamondRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModBlocks.storage, 1, 3), new ItemStack(Blocks.field_150484_ah), 90000));
        manaPowderRecipes = new ArrayList<RecipeManaInfusion>();
        manaPowderRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(Items.field_151016_H), 500));
        manaPowderRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(Items.field_151137_ax), 500));
        manaPowderRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(Items.field_151114_aO), 500));
        manaPowderRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(Items.field_151102_aT), 500));
        for (int i = 0; i < 16; ++i) {
            manaPowderRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(ModItems.dye, 1, i), 400));
        }
        pistonRelayRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModBlocks.pistonRelay), new ItemStack((Block)Blocks.field_150331_J), 15000);
        manaCookieRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaCookie), new ItemStack(Items.field_151106_aX), 20000);
        grassSeedsRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.grassSeeds), new ItemStack((Block)Blocks.field_150329_H, 1, 1), 2500);
        podzolSeedsRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.grassSeeds, 1, 1), new ItemStack((Block)Blocks.field_150330_I), 2500);
        mycelSeedsRecipes = new ArrayList<RecipeManaInfusion>();
        mycelSeedsRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.grassSeeds, 1, 2), new ItemStack((Block)Blocks.field_150337_Q), 6500));
        mycelSeedsRecipes.add(BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.grassSeeds, 1, 2), new ItemStack((Block)Blocks.field_150338_P), 6500));
        manaQuartzRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.quartz, 1, 1), new ItemStack(Items.field_151128_bU), 250);
        tinyPotatoRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModBlocks.tinyPotato), new ItemStack(Items.field_151174_bG), 1337);
        if (Botania.thaumcraftLoaded) {
            Item inkwell = (Item)Item.field_150901_e.func_82594_a("Thaumcraft:ItemInkwell");
            manaInkwellRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaInkwell, 1, ModItems.manaInkwell.func_77612_l()), new ItemStack(inkwell), 35000);
        }
        managlassRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModBlocks.manaGlass), new ItemStack(Blocks.field_150359_w), 150);
        manaStringRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaResource, 1, 16), new ItemStack(Items.field_151007_F), 5000);
        if (Botania.gardenOfGlassLoaded) {
            sugarCaneRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(Items.field_151120_aE), new ItemStack(Blocks.field_150407_cf), 2000);
        }
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.manaBottle), new ItemStack(Items.field_151069_bo), 5000);
    }
}

