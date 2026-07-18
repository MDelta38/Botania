/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.challenge;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.challenge.Challenge;
import vazkii.botania.client.challenge.EnumChallengeLevel;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public final class ModChallenges {
    public static final EnumMap<EnumChallengeLevel, List<Challenge>> challenges = new EnumMap(EnumChallengeLevel.class);
    public static final HashMap<String, Challenge> challengeLookup = new HashMap();

    public static void init() {
        for (EnumChallengeLevel level : (EnumChallengeLevel[])EnumChallengeLevel.class.getEnumConstants()) {
            challenges.put(level, new ArrayList());
        }
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "flowerFarm", new ItemStack(ModBlocks.flower, 1, 6));
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "recordFarm", new ItemStack(Items.field_151096_cd));
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "reedFarm", new ItemStack(Items.field_151120_aE));
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "cobbleGen", new ItemStack(Blocks.field_150347_e));
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "pureDaisy", ItemBlockSpecialFlower.ofType("puredaisy"));
        ModChallenges.addChallenge(EnumChallengeLevel.EASY, "battery", new ItemStack(ModBlocks.pool));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "apothecaryRefill", new ItemStack(ModBlocks.altar));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "treeFarm", new ItemStack(Blocks.field_150345_g));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "fullCropFarm", new ItemStack(Items.field_151014_N));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "animalFarm", new ItemStack(Items.field_151116_aA));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "boneMealFarm", new ItemStack(Items.field_151100_aR, 1, 15));
        ModChallenges.addChallenge(EnumChallengeLevel.NORMAL, "orechid", ItemBlockSpecialFlower.ofType("orechid"));
        ModChallenges.addChallenge(EnumChallengeLevel.HARD, "mobTower", new ItemStack(Items.field_151103_aS));
        ModChallenges.addChallenge(EnumChallengeLevel.HARD, "entropinnyumSetup", ItemBlockSpecialFlower.ofType("entropinnyum"));
        ModChallenges.addChallenge(EnumChallengeLevel.HARD, "spectrolusSetup", ItemBlockSpecialFlower.ofType("spectrolus"));
        ModChallenges.addChallenge(EnumChallengeLevel.HARD, "potionBrewer", new ItemStack(ModBlocks.brewery));
        ModChallenges.addChallenge(EnumChallengeLevel.LUNATIC, "kekimurusSetup", ItemBlockSpecialFlower.ofType("kekimurus"));
        ModChallenges.addChallenge(EnumChallengeLevel.LUNATIC, "autoQuarry", new ItemStack(Items.field_151046_w));
        ModChallenges.addChallenge(EnumChallengeLevel.LUNATIC, "runeCrafter", new ItemStack(ModItems.rune));
    }

    public static void addChallenge(EnumChallengeLevel level, String name, ItemStack icon) {
        Challenge c = new Challenge("botania.challenge." + name, icon, level);
        challenges.get((Object)level).add(c);
        challengeLookup.put(c.unlocalizedName, c);
    }
}

