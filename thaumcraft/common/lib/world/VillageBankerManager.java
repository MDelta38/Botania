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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 */
package thaumcraft.common.lib.world;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.world.ComponentBankerHome;

public class VillageBankerManager
implements VillagerRegistry.IVillageCreationHandler,
VillagerRegistry.IVillageTradeHandler {
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if (villager.func_70946_n() == ConfigEntities.entBankerId) {
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 20 + random.nextInt(3), 18), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 2 + random.nextInt(2), 18), Items.field_151032_g));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 6 + random.nextInt(3), 18), Item.func_150898_a((Block)Blocks.field_150325_L)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 3 + random.nextInt(2), 18), Items.field_151121_aF));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 7 + random.nextInt(3), 18), Items.field_151122_aG));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 16 + random.nextInt(5), 18), Items.field_151062_by));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 9 + random.nextInt(4), 18), Item.func_150898_a((Block)Blocks.field_150426_aN)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 2 + random.nextInt(2), 18), Items.field_151044_h));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 22 + random.nextInt(3), 18), Items.field_151045_i));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 6 + random.nextInt(3), 18), Items.field_151042_j));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 10 + random.nextInt(3), 18), new ItemStack(ConfigItems.itemResource, 1, 2)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 25 + random.nextInt(8), 18), Items.field_151141_av));
        }
    }

    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(ComponentBankerHome.class, 25, MathHelper.func_76136_a((Random)random, (int)(0 + i), (int)(1 + i)));
    }

    public Class<?> getComponentClass() {
        return ComponentBankerHome.class;
    }

    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentBankerHome.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }
}

