/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.CrucibleRecipe
 *  thaumcraft.api.crafting.InfusionEnchantmentRecipe
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.crafting.ShapedArcaneRecipe
 *  thaumcraft.api.crafting.ShapelessArcaneRecipe
 *  thaumcraft.api.wands.WandRod
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.config.ConfigResearch
 */
package flaxbeard.thaumicexploration.research;

import cpw.mods.fml.common.registry.GameRegistry;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.item.ItemBaubleDiscountRing;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;

public final class ModRecipes {
    static ItemStack empty = new ItemStack(ConfigBlocks.blockHole, 1, 15);

    public static void initRecipes() {
        ModRecipes.initCraftingRecipes();
        ModRecipes.initArcaneRecipes();
        ModRecipes.initInfusionRecipes();
        ModRecipes.initCrucibleRecipes();
        ModRecipes.initConstructRecipes();
        ModRecipes.initNecromanticRecipes();
    }

    private static void initConstructRecipes() {
        ModRecipes.registerResearchItemC("BUILDTHINKTANK", Arrays.asList(new AspectList(), 3, 3, 3, Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, new ItemStack(ThaumicExploration.thinkTankJar), empty, empty, empty, empty, empty, empty, empty, empty, new ItemStack(Blocks.field_150342_X), empty, empty, empty, empty)));
        ModRecipes.registerResearchItemC("BUILDNECROINFUSION", Arrays.asList(new AspectList(), 7, 1, 6, Arrays.asList(empty, new ItemStack(ConfigBlocks.blockCandle), empty, empty, empty, new ItemStack(ConfigBlocks.blockCandle), empty, empty, empty, empty, new ItemStack(Blocks.field_150465_bP), empty, empty, empty, empty, empty, new ItemStack(Blocks.field_150465_bP), new ItemStack(ThaumicExploration.itemAltar), new ItemStack(Blocks.field_150465_bP), empty, empty, new ItemStack(ConfigBlocks.blockCandle), empty, empty, new ItemStack(Blocks.field_150465_bP), empty, empty, new ItemStack(ConfigBlocks.blockCandle), empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, new ItemStack(ConfigBlocks.blockCandle), empty, empty, empty)));
    }

    private static void initInfusionRecipes() {
        ModRecipes.registerResearchItemI("SOULBRAZIER", new ItemStack(ThaumicExploration.soulBrazier), 7, new AspectList().add(Aspect.DEATH, 32).add(Aspect.DARKNESS, 32).add(Aspect.AURA, 32), new ItemStack(Items.field_151156_bN), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemResource, 1, 16));
        ModRecipes.registerResearchItemI("BRAINCURE", new ItemStack(ThaumicExploration.pureZombieBrain), 3, new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 6).add(Aspect.HEAL, 2), new ItemStack(ConfigItems.itemZombieBrain, 1), new ItemStack((Item)Items.field_151068_bn, 1, 8232), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151131_as));
        ModRecipes.registerResearchItemI("BRAINCURE", "BRAINCUREALT1", new ItemStack(ThaumicExploration.pureZombieBrain), 3, new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 6).add(Aspect.HEAL, 2), new ItemStack(ConfigItems.itemZombieBrain, 1), new ItemStack((Item)Items.field_151068_bn, 1, 8264), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151131_as));
        ModRecipes.registerResearchItemI("BRAINCURE", "BRAINCUREALT3", new ItemStack(ThaumicExploration.pureZombieBrain), 3, new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 6).add(Aspect.HEAL, 2), new ItemStack(ConfigItems.itemZombieBrain, 1), new ItemStack((Item)Items.field_151068_bn, 1, 16424), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151131_as));
        ModRecipes.registerResearchItemI("BRAINCURE", "BRAINCUREALT4", new ItemStack(ThaumicExploration.pureZombieBrain), 3, new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 6).add(Aspect.HEAL, 2), new ItemStack(ConfigItems.itemZombieBrain, 1), new ItemStack((Item)Items.field_151068_bn, 1, 16456), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151131_as));
        ModRecipes.registerResearchItemI("BRAINCURE", "BRAINCUREALT5", new ItemStack(ThaumicExploration.pureZombieBrain), 3, new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 6).add(Aspect.HEAL, 2), new ItemStack(ConfigItems.itemZombieBrain, 1), new ItemStack((Item)Items.field_151068_bn, 1, 8200), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151131_as));
        ModRecipes.registerResearchItemI("ROD_AMBER", new ItemStack(ThaumicExploration.amberCore), 5, new AspectList().add(Aspect.MAGIC, 14).add(Aspect.AURA, 4).add(Aspect.TRAP, 6), new ItemStack(ConfigBlocks.blockCosmeticOpaque), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14));
        ModRecipes.registerResearchItemI("ROD_TRANSMUTATION", new ItemStack(ThaumicExploration.transmutationCore), 6, new AspectList().add(Aspect.MAGIC, 32).add(Aspect.EXCHANGE, 16).add(Aspect.AURA, 4), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigItems.itemResource, 1, 14));
        if (ThaumicExploration.breadWand) {
            ModRecipes.registerResearchItemI("ROD_BREAD", new ItemStack(ThaumicExploration.breadCore), 3, new AspectList().add(Aspect.MAGIC, 8).add(Aspect.CROP, 8).add(Aspect.HUNGER, 4), new ItemStack(Items.field_151025_P), new ItemStack(ConfigItems.itemResource, 1, 14));
        }
        ModRecipes.registerResearchItemI("CRUCSOULS", new ItemStack(ThaumicExploration.crucibleSouls), 5, new AspectList().add(Aspect.DEATH, 40).add(Aspect.UNDEAD, 10).add(Aspect.HUNGER, 20).add(Aspect.TRAP, 20).add(Aspect.WEAPON, 5).add(Aspect.SOUL, 30), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 0), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151073_bk), new ItemStack(Blocks.field_150425_aM), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1));
        ModRecipes.registerResearchItemI("TRASHJAR", new ItemStack(ThaumicExploration.trashJar), 7, new AspectList().add(Aspect.ENTROPY, 16).add(Aspect.VOID, 12).add(Aspect.HUNGER, 12).add(Aspect.ELDRITCH, 12), new ItemStack(ConfigBlocks.blockJar, 1, 3), new ItemStack(ConfigBlocks.blockChestHungry), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 6, 2), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 6, 2), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 6, 2), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigBlocks.blockCosmeticOpaque, 6, 2));
        ModRecipes.registerResearchItemI("ROD_NECROMANCER_staff", new ItemStack(ThaumicExploration.necroStaffCore), 6, new AspectList().add(Aspect.DEATH, 32).add(Aspect.ENTROPY, 16).add(Aspect.SOUL, 16).add(Aspect.AURA, 8), new ItemStack(ConfigItems.itemWandRod, 1, 57), new ItemStack(Items.field_151144_bL, 1, 1), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151103_aS), new ItemStack(ThaumicExploration.pureZombieBrain), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151078_bh));
        ModRecipes.registerResearchItemI("TENTACLERING", new ItemStack(ThaumicExploration.tentacleRing), 4, new AspectList().add(Aspect.TAINT, 32).add(Aspect.WEAPON, 8).add(Aspect.ARMOR, 16).add(Aspect.BEAST, 8), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 11), new ItemStack(ConfigItems.itemResource, 1, 12), new ItemStack(ConfigItems.itemResource, 1, 11), new ItemStack(ConfigItems.itemResource, 1, 12));
        ModRecipes.registerResearchItemI("STABILIZERBELT", new ItemStack(ThaumicExploration.stabilizerBelt), 3, new AspectList().add(Aspect.ORDER, 12).add(Aspect.EARTH, 12).add(Aspect.ARMOR, 4).add(Aspect.TRAVEL, 8), new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 3));
        ModRecipes.registerResearchItemI("METEORBOOTS", new ItemStack(ThaumicExploration.bootsMeteor), 4, new AspectList().add(Aspect.FIRE, 25).add(Aspect.ENERGY, 25).add(Aspect.TRAVEL, 25).add(Aspect.FLIGHT, 25), new ItemStack(ConfigItems.itemBootsTraveller, 1, Short.MAX_VALUE), new ItemStack(ConfigBlocks.blockCrystal, 1, 1), new ItemStack(Blocks.field_150424_aL), new ItemStack(Blocks.field_150424_aL), new ItemStack(Blocks.field_150424_aL), new ItemStack(ConfigItems.itemFocusFire));
        ModRecipes.registerResearchItemIE("ENCHBINDING", "ENCHBINDING", Enchantment.field_77331_b[ThaumicExploration.enchantmentBinding.field_77352_x], 3, new AspectList().add(Aspect.TRAP, 8).add(Aspect.ENTROPY, 4).add(Aspect.TRAVEL, 4), new ItemStack(Items.field_151040_l), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Blocks.field_150425_aM));
        ModRecipes.registerResearchItemIE("ENCHDISARM", "ENCHDISARM", Enchantment.field_77331_b[ThaumicExploration.enchantmentDisarm.field_77352_x], 5, new AspectList().add(Aspect.WEAPON, 4).add(Aspect.SLIME, 8).add(Aspect.TRAP, 4), new ItemStack(Items.field_151040_l), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.field_151123_aH));
        ModRecipes.registerResearchItemIE("ENCHNIGHTVISION", "ENCHNIGHTVISION", Enchantment.field_77331_b[ThaumicExploration.enchantmentNightVision.field_77352_x], 5, new AspectList().add(Aspect.SENSES, 16).add(Aspect.DARKNESS, 8).add(Aspect.LIGHT, 16), new ItemStack(Items.field_151150_bK), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.field_151150_bK));
        ModRecipes.registerResearchItemI("TALISMANFOOD", new ItemStack(ThaumicExploration.talismanFood), 5, new AspectList().add(Aspect.HUNGER, 30).add(Aspect.FLESH, 25).add(Aspect.CROP, 25).add(Aspect.EXCHANGE, 10), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151083_be), new ItemStack(Items.field_151077_bg), new ItemStack(Items.field_151157_am), new ItemStack(Items.field_151101_aQ), new ItemStack(Items.field_151025_P));
        ModRecipes.registerResearchItemI("REPLICATOR", new ItemStack(ThaumicExploration.replicator), 9, new AspectList().add(Aspect.CRAFT, 50).add(Aspect.MECHANISM, 30).add(Aspect.TOOL, 30).add(Aspect.ORDER, 20), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2), new ItemStack(ConfigBlocks.blockTable, 1, 15), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemResource, 1, 2));
        ModRecipes.registerResearchItemI("COMETBOOTS", new ItemStack(ThaumicExploration.bootsComet), 4, new AspectList().add(Aspect.WATER, 25).add(Aspect.COLD, 25).add(Aspect.TRAVEL, 25).add(Aspect.MOTION, 25), new ItemStack(ConfigItems.itemBootsTraveller, 1, Short.MAX_VALUE), new ItemStack(ConfigBlocks.blockCrystal, 1, 2), new ItemStack(Blocks.field_150433_aE), new ItemStack(Blocks.field_150433_aE), new ItemStack(Blocks.field_150433_aE), new ItemStack(ConfigItems.itemFocusFrost));
        ModRecipes.registerResearchItemI("THINKTANK", new ItemStack(ThaumicExploration.thinkTankJar), 6, new AspectList().add(Aspect.MIND, 40).add(Aspect.SENSES, 20).add(Aspect.UNDEAD, 30), new ItemStack(ConfigBlocks.blockJar), new ItemStack(ThaumicExploration.pureZombieBrain), new ItemStack(ConfigItems.itemInkwell, 1, 0), new ItemStack(ThaumicExploration.pureZombieBrain), new ItemStack(ConfigItems.itemThaumonomicon), new ItemStack(ThaumicExploration.pureZombieBrain), new ItemStack(ConfigItems.itemThaumometer));
        ModRecipes.registerResearchItemI("URN", new ItemStack(ThaumicExploration.everfullUrn), 2, new AspectList().add(Aspect.WATER, 20).add(Aspect.VOID, 8).add(Aspect.MAGIC, 4), new ItemStack(Items.field_151162_bE), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151118_aC), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151118_aC), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151118_aC));
        ModRecipes.registerResearchItemI("BURN", new ItemStack(ThaumicExploration.everburnUrn), 2, new AspectList().add(Aspect.FIRE, 20).add(Aspect.VOID, 8).add(Aspect.MAGIC, 4), new ItemStack(Items.field_151162_bE), new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151118_aC), new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151118_aC), new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151118_aC));
        ModRecipes.registerResearchItemI("NECROINFUSION", new ItemStack(ThaumicExploration.itemAltar), 7, new AspectList().add(Aspect.MAGIC, 32).add(Aspect.DEATH, 16).add(Aspect.UNDEAD, 16).add(Aspect.CRAFT, 16), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1), new ItemStack(Blocks.field_150424_aL), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6));
        ModRecipes.registerResearchItemI("CAP_SOJOURNER", new ItemStack(ThaumicExploration.sojournerCap), 5, new AspectList().add(Aspect.AURA, 6).add(Aspect.MAGIC, 12).add(Aspect.EXCHANGE, 16).add(Aspect.ENERGY, 12), new ItemStack(ThaumicExploration.sojournerCapUncharged), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14));
        ModRecipes.registerResearchItemI("CAP_MECHANIST", new ItemStack(ThaumicExploration.mechanistCap), 5, new AspectList().add(Aspect.MECHANISM, 16).add(Aspect.MAGIC, 12).add(Aspect.AURA, 6).add(Aspect.ENERGY, 12), new ItemStack(ThaumicExploration.mechanistCapUncharged), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(ConfigItems.itemResource, 1, 14));
    }

    private static void initArcaneRecipes() {
        int i;
        for (i = 0; i < 15; ++i) {
            ModRecipes.registerResearchItemShapeless("FLOATCANDLE" + i, "FLOATCANDLE", new ItemStack(ThaumicExploration.floatCandle, 3, i), new AspectList().add(Aspect.AIR, 5), new ItemStack(ConfigBlocks.blockCandle, 1, i), new ItemStack(ConfigBlocks.blockCandle, 1, i), new ItemStack(ConfigBlocks.blockCandle, 1, i), new ItemStack(ConfigItems.itemShard, 1, 0));
        }
        ModRecipes.registerResearchItemShapeless("FLOATCANDLE", "FLOATCANDLE", new ItemStack(ThaumicExploration.floatCandle, 3, Short.MAX_VALUE), new AspectList().add(Aspect.AIR, 5), new ItemStack(ConfigBlocks.blockCandle, 1, Short.MAX_VALUE), new ItemStack(ConfigBlocks.blockCandle, 1, Short.MAX_VALUE), new ItemStack(ConfigBlocks.blockCandle, 1, Short.MAX_VALUE), new ItemStack(ConfigItems.itemShard, 1, 0));
        for (i = 0; i < 6; ++i) {
            ModRecipes.registerResearchItem("DISCOUNTRINGS" + i, "DISCOUNTRINGS", new ItemStack(ThaumicExploration.discountRing, 1, i), new AspectList().add((Aspect)Aspect.getPrimalAspects().get(i), 15), " F ", "F F", " F ", Character.valueOf('F'), new ItemStack(ConfigItems.itemShard, 1, ItemBaubleDiscountRing.correspondingShards[i]));
        }
        ModRecipes.registerResearchItem("DREAMCATCHER", "DREAMCATCHER", new ItemStack(ThaumicExploration.charmNoTaint), new AspectList().add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15), "GPG", "PSP", "FPF", Character.valueOf('G'), new ItemStack(ConfigItems.itemResource, 1, 11), Character.valueOf('P'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 12), Character.valueOf('F'), new ItemStack(Items.field_151008_G));
        ModRecipes.registerResearchItem("DREAMCATCHER2", "DREAMCATCHER", new ItemStack(ThaumicExploration.charmNoTaint), new AspectList().add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15), "GPG", "PSP", "FPF", Character.valueOf('G'), "gooTaint", Character.valueOf('P'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 12), Character.valueOf('F'), "tendrilTaint");
        ModRecipes.registerResearchItem("UNCHARGEDSOJOURNER", "CAP_SOJOURNER", new ItemStack(ThaumicExploration.sojournerCapUncharged), new AspectList().add(Aspect.AIR, 6).add(Aspect.ENTROPY, 6).add(Aspect.ORDER, 6), "ABA", "A A", Character.valueOf('A'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('B'), new ItemStack(Items.field_151045_i));
        ModRecipes.registerResearchItem("UNCHARGEDMECHANIST", "CAP_MECHANIST", new ItemStack(ThaumicExploration.mechanistCapUncharged), new AspectList().add(Aspect.FIRE, 6).add(Aspect.ENTROPY, 6).add(Aspect.ORDER, 6), "AAA", "ABA", " C ", Character.valueOf('A'), new ItemStack(Items.field_151137_ax), Character.valueOf('B'), new ItemStack((Block)Blocks.field_150331_J), Character.valueOf('C'), new ItemStack(Items.field_151107_aW));
        ModRecipes.registerResearchItem("ROD_AMBER_staff", "ROD_AMBER_staff", new ItemStack(ThaumicExploration.amberStaffCore), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("AMBER_staff")).getCraftCost()), "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ThaumicExploration.amberCore));
        ModRecipes.registerResearchItem("ROD_TRANSMUTATION_staff", "ROD_TRANSMUTATION_staff", new ItemStack(ThaumicExploration.transmutationStaffCore), new AspectList().add(Aspect.ORDER, ((WandRod)WandRod.rods.get("AMBER_staff")).getCraftCost()), "  S", " G ", "G  ", Character.valueOf('S'), new ItemStack(ConfigItems.itemResource, 1, 15), Character.valueOf('G'), new ItemStack(ThaumicExploration.transmutationCore));
    }

    private static void initCraftingRecipes() {
        for (int i = 0; i < 16; ++i) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ThaumicExploration.blankSeal, 1, i), (Object[])new Object[]{" X ", "XZX", " X ", Character.valueOf('X'), new ItemStack(ConfigItems.itemResource, 1, 4), Character.valueOf('Z'), new ItemStack(Items.field_151100_aR, 1, i)});
        }
        ModRecipes.registerCraftingRecipe("BLANKSEAL", new ItemStack(ThaumicExploration.blankSeal, 1, Short.MAX_VALUE), " X ", "XZX", " X ", Character.valueOf('X'), new ItemStack(ConfigItems.itemResource, 1, 4), Character.valueOf('Z'), new ItemStack(Items.field_151100_aR, 1, Short.MAX_VALUE));
    }

    private static void initCrucibleRecipes() {
        int i;
        ModRecipes.registerCrucibleRecipe("FLESHCURE", "FLESHCURE", new ItemStack(Items.field_151116_aA, 2), new ItemStack(Items.field_151078_bh), new AspectList().add(Aspect.FLESH, 2).add(Aspect.CLOTH, 1));
        for (i = 0; i < 16; ++i) {
            ModRecipes.registerCrucibleRecipe("CHESTSEAL", "CHESTSEAL", new ItemStack(ThaumicExploration.chestSeal, 1, i), new ItemStack(ThaumicExploration.blankSeal, 1, i), new AspectList().add(Aspect.ELDRITCH, 6).add(Aspect.EXCHANGE, 4).add(Aspect.VOID, 4).add(Aspect.TRAP, 4).add(Aspect.TRAVEL, 6));
        }
        for (i = 0; i < 16; ++i) {
            ModRecipes.registerCrucibleRecipe("JARSEAL", "JARSEAL", new ItemStack(ThaumicExploration.jarSeal, 1, i), new ItemStack(ThaumicExploration.blankSeal, 1, i), new AspectList().add(Aspect.ELDRITCH, 6).add(Aspect.EXCHANGE, 4).add(Aspect.CRYSTAL, 4).add(Aspect.TRAP, 4).add(Aspect.TRAVEL, 6));
        }
    }

    public static void initNecromanticRecipes() {
    }

    private static void registerResearchItem(String name, String research, ItemStack output, AspectList aspects, Object ... stuff) {
        ShapedArcaneRecipe recipe = ThaumcraftApi.addArcaneCraftingRecipe((String)research, (ItemStack)output, (AspectList)aspects, (Object[])stuff);
        ConfigResearch.recipes.put(name, recipe);
    }

    private static void registerCraftingRecipe(String name, ItemStack output, Object ... stuff) {
        GameRegistry.addRecipe((ItemStack)output, (Object[])stuff);
        List recipeList = CraftingManager.func_77594_a().func_77592_b();
        if (name != null && name.length() != 0) {
            ConfigResearch.recipes.put(name, recipeList.get(recipeList.size() - 1));
        }
    }

    private static void registerCrucibleRecipe(String name, String research, ItemStack output, ItemStack input, AspectList aspects) {
        CrucibleRecipe recipe = ThaumcraftApi.addCrucibleRecipe((String)name, (ItemStack)output, (Object)input, (AspectList)aspects);
        ConfigResearch.recipes.put(research, recipe);
    }

    private static void registerResearchItemI(String name, Object output, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        InfusionRecipe recipe = ThaumcraftApi.addInfusionCraftingRecipe((String)name, (Object)output, (int)instability, (AspectList)aspects, (ItemStack)input, (ItemStack[])stuff);
        ConfigResearch.recipes.put(name, recipe);
    }

    private static void registerResearchItemShapeless(String name, String research, ItemStack output, AspectList aspects, Object ... stuff) {
        ShapelessArcaneRecipe recipe = ThaumcraftApi.addShapelessArcaneCraftingRecipe((String)research, (ItemStack)output, (AspectList)aspects, (Object[])stuff);
        ConfigResearch.recipes.put(name, recipe);
    }

    private static void registerResearchItemI(String name, String research, Object output, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        InfusionRecipe recipe = ThaumcraftApi.addInfusionCraftingRecipe((String)name, (Object)output, (int)instability, (AspectList)aspects, (ItemStack)input, (ItemStack[])stuff);
        ConfigResearch.recipes.put(research, recipe);
    }

    private static void registerResearchItemIUI(String research, String name, Object output, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        InfusionRecipe recipe = ThaumcraftApi.addInfusionCraftingRecipe((String)name, (Object)output, (int)instability, (AspectList)aspects, (ItemStack)input, (ItemStack[])stuff);
        ConfigResearch.recipes.put(research, recipe);
    }

    private static void registerResearchItemIE(String name, String research, Enchantment output, int instability, AspectList aspects, ItemStack ... stuff) {
        InfusionEnchantmentRecipe recipe = ThaumcraftApi.addInfusionEnchantmentRecipe((String)name, (Enchantment)output, (int)instability, (AspectList)aspects, (ItemStack[])stuff);
        ConfigResearch.recipes.put(research, recipe);
    }

    private static void registerResearchItemIU(String research, String name, Object[] objects, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        InfusionRecipe recipe = ThaumcraftApi.addInfusionCraftingRecipe((String)name, (Object)objects, (int)instability, (AspectList)aspects, (ItemStack)input, (ItemStack[])stuff);
        ConfigResearch.recipes.put(research, recipe);
    }

    private static void registerResearchItemC(String string, List<Object> asList) {
        ConfigResearch.recipes.put(string, asList);
    }
}

