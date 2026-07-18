/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package vazkii.botania.common.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;

public final class ModPureDaisyRecipes {
    public static void init() {
        BotaniaAPI.registerPureDaisyRecipe("stone", ModBlocks.livingrock, 0);
        BotaniaAPI.registerPureDaisyRecipe("logWood", ModBlocks.livingwood, 0);
        BotaniaAPI.registerPureDaisyRecipe("netherrack", Blocks.field_150347_e, 0);
        BotaniaAPI.registerPureDaisyRecipe("soulSand", (Block)Blocks.field_150354_m, 0);
        BotaniaAPI.registerPureDaisyRecipe("ice", Blocks.field_150403_cj, 0);
        BotaniaAPI.registerPureDaisyRecipe("blockBlaze", Blocks.field_150343_Z, 0);
        BotaniaAPI.registerPureDaisyRecipe(Blocks.field_150355_j, Blocks.field_150433_aE, 0);
    }
}

