/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageTradeHandler
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.worldgen.ComponentVillageApothecary;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class WorldHandlerVillageApothecary
implements VillagerRegistry.IVillageCreationHandler,
VillagerRegistry.IVillageTradeHandler {
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieces.PieceWeight(ComponentVillageApothecary.class, 15, 1 + (size > 2 ? random.nextInt(2) : 0));
    }

    public Class getComponentClass() {
        return ComponentVillageApothecary.class;
    }

    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentVillageApothecary.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }

    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 2), Witchery.Items.GENERIC.itemDogTongue.createStack(2)));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 1), Witchery.Items.GENERIC.itemBatWool.createStack(3)));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 4), Witchery.Items.GENERIC.itemSpectralDust.createStack()));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5), new ItemStack(Witchery.Items.SEEDS_GARLIC)));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 6), Witchery.Items.GENERIC.itemArtichoke.createStack()));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 7), new ItemStack((Block)Blocks.field_150328_O, 5)));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 8), new ItemStack(Items.field_151123_aH)));
        recipeList.func_77205_a(new MerchantRecipe(new ItemStack(Items.field_151166_bC, 3), new ItemStack(Items.field_151119_aD, 5)));
    }
}

