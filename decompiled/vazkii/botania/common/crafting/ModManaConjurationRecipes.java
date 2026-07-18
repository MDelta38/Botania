/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.crafting;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;

public class ModManaConjurationRecipes {
    public static RecipeManaInfusion redstoneRecipe;
    public static RecipeManaInfusion glowstoneRecipe;
    public static RecipeManaInfusion quartzRecipe;
    public static RecipeManaInfusion coalRecipe;
    public static RecipeManaInfusion snowballRecipe;
    public static RecipeManaInfusion netherrackRecipe;
    public static RecipeManaInfusion soulSandRecipe;
    public static RecipeManaInfusion gravelRecipe;
    public static List<RecipeManaInfusion> leavesRecipes;
    public static RecipeManaInfusion grassRecipe;

    public static void init() {
        int i;
        redstoneRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Items.field_151137_ax, 2), new ItemStack(Items.field_151137_ax), 5000);
        glowstoneRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Items.field_151114_aO, 2), new ItemStack(Items.field_151114_aO), 5000);
        quartzRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Items.field_151128_bU, 2), new ItemStack(Items.field_151128_bU), 2500);
        coalRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Items.field_151044_h, 2), new ItemStack(Items.field_151044_h), 2100);
        snowballRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Items.field_151126_ay, 2), new ItemStack(Items.field_151126_ay), 200);
        netherrackRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Blocks.field_150424_aL, 2), new ItemStack(Blocks.field_150424_aL), 200);
        soulSandRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Blocks.field_150425_aM, 2), new ItemStack(Blocks.field_150425_aM), 1500);
        gravelRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack(Block.func_149684_b((String)"gravel"), 2), new ItemStack(Block.func_149684_b((String)"gravel")), 720);
        leavesRecipes = new ArrayList<RecipeManaInfusion>();
        for (i = 0; i < 4; ++i) {
            leavesRecipes.add(BotaniaAPI.registerManaConjurationRecipe(new ItemStack((Block)Blocks.field_150362_t, 2, i), new ItemStack((Block)Blocks.field_150362_t, 1, i), 2000));
        }
        for (i = 0; i < 2; ++i) {
            leavesRecipes.add(BotaniaAPI.registerManaConjurationRecipe(new ItemStack((Block)Blocks.field_150361_u, 2, i), new ItemStack((Block)Blocks.field_150361_u, 1, i), 2000));
        }
        grassRecipe = BotaniaAPI.registerManaConjurationRecipe(new ItemStack((Block)Blocks.field_150329_H, 2, 1), new ItemStack((Block)Blocks.field_150329_H, 1, 1), 800);
    }
}

