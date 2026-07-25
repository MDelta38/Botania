/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageTradeHandler
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.init.Items
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.world.ComponentWizardTower;

public class VillageWizardManager
implements VillagerRegistry.IVillageCreationHandler,
VillagerRegistry.IVillageTradeHandler {
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if (villager.func_70946_n() == ConfigEntities.entWizardId) {
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 20 + random.nextInt(3), 18), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 9)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 3), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 0)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemResource, 4 + random.nextInt(3), 6), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemResource, 1, 1)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetChicken, 24 + random.nextInt(8), 0), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151122_aG, 4 + random.nextInt(3), 0), new ItemStack(ConfigItems.itemResource, 1, 9)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(ConfigItems.itemNuggetBeef, 24 + random.nextInt(8), 0), new ItemStack(Items.field_151166_bC)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemShard, 2 + random.nextInt(2), random.nextInt(6))));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC), new ItemStack(ConfigItems.itemManaBean, 1 + random.nextInt(2), 0)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBathSalts, 1, 0)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemRingRunic, 1, 0)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemAmuletVis, 1, 0)));
            recipeList.add((Object)new MerchantRecipe(new ItemStack(Items.field_151166_bC, 5 + random.nextInt(3)), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3 + random.nextInt(6))));
        }
    }

    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(ComponentWizardTower.class, 15, MathHelper.func_76136_a((Random)random, (int)(0 + i), (int)(1 + i)));
    }

    public Class<?> getComponentClass() {
        return ComponentWizardTower.class;
    }

    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentWizardTower.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }
}

