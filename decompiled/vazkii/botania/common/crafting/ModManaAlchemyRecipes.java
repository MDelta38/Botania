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
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;

public final class ModManaAlchemyRecipes {
    public static RecipeManaInfusion leatherRecipe;
    public static List<RecipeManaInfusion> woodRecipes;
    public static List<RecipeManaInfusion> saplingRecipes;
    public static RecipeManaInfusion glowstoneDustRecipe;
    public static List<RecipeManaInfusion> quartzRecipes;
    public static RecipeManaInfusion chiseledBrickRecipe;
    public static RecipeManaInfusion iceRecipe;
    public static List<RecipeManaInfusion> swampFolliageRecipes;
    public static List<RecipeManaInfusion> fishRecipes;
    public static List<RecipeManaInfusion> cropRecipes;
    public static RecipeManaInfusion potatoRecipe;
    public static RecipeManaInfusion netherWartRecipe;
    public static List<RecipeManaInfusion> gunpowderAndFlintRecipes;
    public static RecipeManaInfusion nameTagRecipe;
    public static List<RecipeManaInfusion> stringRecipes;
    public static List<RecipeManaInfusion> slimeballCactusRecipes;
    public static RecipeManaInfusion enderPearlRecipe;
    public static List<RecipeManaInfusion> redstoneToGlowstoneRecipes;
    public static RecipeManaInfusion sandRecipe;
    public static RecipeManaInfusion redSandRecipe;
    public static List<RecipeManaInfusion> clayBreakdownRecipes;
    public static RecipeManaInfusion coarseDirtRecipe;
    public static RecipeManaInfusion prismarineRecipe;
    public static List<RecipeManaInfusion> stoneRecipes;
    public static List<RecipeManaInfusion> tallgrassRecipes;
    public static List<RecipeManaInfusion> flowersRecipes;

    public static void init() {
        int i;
        leatherRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151078_bh), 600);
        woodRecipes = new ArrayList<RecipeManaInfusion>();
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150364_r, 1, 0), new ItemStack(Blocks.field_150363_s, 1, 1), 40));
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150364_r, 1, 1), new ItemStack(Blocks.field_150364_r, 1, 0), 40));
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150364_r, 1, 2), new ItemStack(Blocks.field_150364_r, 1, 1), 40));
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150364_r, 1, 3), new ItemStack(Blocks.field_150364_r, 1, 2), 40));
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150363_s, 1, 0), new ItemStack(Blocks.field_150364_r, 1, 3), 40));
        woodRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150363_s, 1, 1), new ItemStack(Blocks.field_150363_s, 1, 0), 40));
        saplingRecipes = new ArrayList<RecipeManaInfusion>();
        for (i = 0; i < 6; ++i) {
            saplingRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150345_g, 1, i == 5 ? 0 : i + 1), new ItemStack(Blocks.field_150345_g, 1, i), 120));
        }
        glowstoneDustRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151114_aO, 4), new ItemStack(Blocks.field_150426_aN), 25);
        quartzRecipes = new ArrayList<RecipeManaInfusion>();
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151128_bU, 4), new ItemStack(Blocks.field_150371_ca, 1, Short.MAX_VALUE), 25));
        if (ConfigHandler.darkQuartzEnabled) {
            quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 0), new ItemStack(ModFluffBlocks.darkQuartz, 1, Short.MAX_VALUE), 25));
        }
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 1), new ItemStack(ModFluffBlocks.manaQuartz, 1, Short.MAX_VALUE), 25));
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 2), new ItemStack(ModFluffBlocks.blazeQuartz, 1, Short.MAX_VALUE), 25));
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 3), new ItemStack(ModFluffBlocks.lavenderQuartz, 1, Short.MAX_VALUE), 25));
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 4), new ItemStack(ModFluffBlocks.redQuartz, 1, Short.MAX_VALUE), 25));
        quartzRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.quartz, 4, 5), new ItemStack(ModFluffBlocks.elfQuartz, 1, Short.MAX_VALUE), 25));
        chiseledBrickRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150417_aV, 1, 3), new ItemStack(Blocks.field_150417_aV), 150);
        iceRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150432_aD), new ItemStack(Blocks.field_150433_aE), 2250);
        swampFolliageRecipes = new ArrayList<RecipeManaInfusion>();
        swampFolliageRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150392_bi), new ItemStack(Blocks.field_150395_bd), 320));
        swampFolliageRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150395_bd), new ItemStack(Blocks.field_150392_bi), 320));
        fishRecipes = new ArrayList<RecipeManaInfusion>();
        for (i = 0; i < 4; ++i) {
            fishRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151115_aP, 1, i == 3 ? 0 : i + 1), new ItemStack(Items.field_151115_aP, 1, i), 200));
        }
        cropRecipes = new ArrayList<RecipeManaInfusion>();
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151014_N), new ItemStack(Items.field_151100_aR, 1, 3), 6000));
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151015_O), 6000));
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151174_bG), 6000));
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151081_bc), new ItemStack(Items.field_151172_bF), 6000));
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151080_bb), new ItemStack(Items.field_151081_bc), 6000));
        cropRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151100_aR, 1, 3), new ItemStack(Items.field_151080_bb), 6000));
        potatoRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151170_bI), 1200);
        netherWartRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151072_bj), 4000);
        gunpowderAndFlintRecipes = new ArrayList<RecipeManaInfusion>();
        gunpowderAndFlintRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151145_ak), new ItemStack(Items.field_151016_H), 200));
        gunpowderAndFlintRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151016_H), new ItemStack(Items.field_151145_ak), 4000));
        nameTagRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151057_cb), new ItemStack(Items.field_151099_bA), 16000);
        stringRecipes = new ArrayList<RecipeManaInfusion>();
        for (i = 0; i < 16; ++i) {
            stringRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151007_F, 3), new ItemStack(Blocks.field_150325_L, 1, i), 100));
        }
        slimeballCactusRecipes = new ArrayList<RecipeManaInfusion>();
        slimeballCactusRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151123_aH), new ItemStack(Blocks.field_150434_aF), 1200));
        slimeballCactusRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151123_aH), 1200));
        enderPearlRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151073_bk), 28000);
        redstoneToGlowstoneRecipes = new ArrayList<RecipeManaInfusion>();
        redstoneToGlowstoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151137_ax), new ItemStack(Items.field_151114_aO), 300));
        redstoneToGlowstoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151137_ax), 300));
        sandRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.func_149684_b((String)"sand")), new ItemStack(Blocks.field_150347_e), 50);
        redSandRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Block.func_149684_b((String)"sand"), 1, 1), new ItemStack(Blocks.field_150405_ch), 50);
        clayBreakdownRecipes = new ArrayList<RecipeManaInfusion>();
        clayBreakdownRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151119_aD, 4), new ItemStack(Blocks.field_150435_aG), 25));
        clayBreakdownRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Items.field_151118_aC, 4), new ItemStack(Blocks.field_150336_V), 25));
        coarseDirtRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(Blocks.field_150346_d, 1, 1), new ItemStack(Blocks.field_150346_d), 120);
        prismarineRecipe = BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModItems.manaResource, 1, 10), new ItemStack(Items.field_151128_bU), 200);
        if (ConfigHandler.stones18Enabled) {
            stoneRecipes = new ArrayList<RecipeManaInfusion>();
            stoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModFluffBlocks.stone), "stone", 200));
            for (i = 0; i < 4; ++i) {
                stoneRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(ModFluffBlocks.stone, 1, i), new ItemStack(ModFluffBlocks.stone, 1, i == 0 ? 3 : i - 1), 200));
            }
        }
        tallgrassRecipes = new ArrayList<RecipeManaInfusion>();
        tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150330_I), new ItemStack((Block)Blocks.field_150329_H, 1, 2), 500));
        tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150329_H, 1, 1), new ItemStack((Block)Blocks.field_150330_I), 500));
        tallgrassRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150329_H, 1, 2), new ItemStack((Block)Blocks.field_150329_H, 1, 1), 500));
        flowersRecipes = new ArrayList<RecipeManaInfusion>();
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O), new ItemStack((Block)Blocks.field_150327_N), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 1), new ItemStack((Block)Blocks.field_150328_O), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 2), new ItemStack((Block)Blocks.field_150328_O, 1, 1), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 3), new ItemStack((Block)Blocks.field_150328_O, 1, 2), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 4), new ItemStack((Block)Blocks.field_150328_O, 1, 3), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 5), new ItemStack((Block)Blocks.field_150328_O, 1, 4), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 6), new ItemStack((Block)Blocks.field_150328_O, 1, 5), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 7), new ItemStack((Block)Blocks.field_150328_O, 1, 6), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150328_O, 1, 8), new ItemStack((Block)Blocks.field_150328_O, 1, 7), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150398_cm), new ItemStack((Block)Blocks.field_150328_O, 1, 8), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150398_cm, 1, 1), new ItemStack((Block)Blocks.field_150398_cm), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150398_cm, 1, 4), new ItemStack((Block)Blocks.field_150398_cm, 1, 1), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150398_cm, 1, 5), new ItemStack((Block)Blocks.field_150398_cm, 1, 4), 400));
        flowersRecipes.add(BotaniaAPI.registerManaAlchemyRecipe(new ItemStack((Block)Blocks.field_150327_N), new ItemStack((Block)Blocks.field_150398_cm, 1, 5), 400));
    }
}

