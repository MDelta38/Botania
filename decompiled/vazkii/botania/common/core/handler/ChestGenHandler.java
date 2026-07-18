/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraftforge.common.ChestGenHooks
 */
package vazkii.botania.common.core.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import vazkii.botania.common.item.ModItems;

public final class ChestGenHandler {
    public static void init() {
        String c = "bonusChest";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.lexicon), 1, 1, 7));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 1));
        c = "strongholdCorridor";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.manaResource, 1, 1), 1, 1, 8));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.manaResource, 1, 1), 1, 3, 2));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.overgrowthSeed), 1, 1, 2));
        c = "dungeonChest";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.manaResource, 1, 0), 1, 5, 9));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.lexicon), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.manaBottle), 1, 1, 5));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.overgrowthSeed), 1, 1, 2));
        c = "pyramidDesertyChest";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.overgrowthSeed), 1, 1, 2));
        c = "mineshaftCorridor";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.overgrowthSeed), 1, 1, 2));
        c = "pyramidJungleChest";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.overgrowthSeed), 1, 1, 2));
        c = "villageBlacksmith";
        ChestGenHooks.addItem((String)c, (WeightedRandomChestContent)new WeightedRandomChestContent(new ItemStack(ModItems.blackLotus), 1, 1, 6));
    }
}

