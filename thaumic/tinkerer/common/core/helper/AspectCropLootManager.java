/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 *  org.apache.commons.lang3.text.WordUtils
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemWispEssence
 */
package thaumic.tinkerer.common.core.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartz;
import thaumic.tinkerer.common.item.ItemBrightNitor;
import thaumic.tinkerer.common.item.ItemInfusedGrain;

public class AspectCropLootManager {
    private static HashMap<Aspect, HashMap<ItemStack, Integer>> lootMap = new HashMap();

    public static ItemStack getLootForAspect(Aspect aspect) {
        HashMap<ItemStack, Integer> aspectHashmap = lootMap.get(aspect);
        if (aspectHashmap == null) {
            return null;
        }
        int sum = 0;
        for (Integer i : aspectHashmap.values()) {
            sum += i.intValue();
        }
        Random rand = new Random();
        int randInt = rand.nextInt(sum);
        for (Map.Entry<ItemStack, Integer> pair : aspectHashmap.entrySet()) {
            if ((randInt -= pair.getValue().intValue()) > 0) continue;
            return pair.getKey().func_77946_l();
        }
        return null;
    }

    public static void addAspectLoot(Aspect aspect, ItemStack stack) {
        AspectCropLootManager.addAspectLoot(aspect, stack, 1);
    }

    public static void addAspectLoot(Aspect aspect, String target) {
        AspectCropLootManager.addAspectLoot(aspect, target, 1);
        for (String ore : OreDictionary.getOreNames()) {
            if (!ore.contains(WordUtils.capitalizeFully((String)target)) && !ore.contains(target)) continue;
            for (ItemStack stack : OreDictionary.getOres((String)ore)) {
                AspectCropLootManager.addAspectLoot(aspect, stack);
            }
        }
    }

    public static void addAspectLoot(Aspect aspect, String target, int count) {
        for (String ore : OreDictionary.getOreNames()) {
            if (!ore.contains(WordUtils.capitalizeFully((String)target)) && !ore.contains(target)) continue;
            for (ItemStack stack : OreDictionary.getOres((String)ore)) {
                ItemStack newStack = stack.func_77946_l();
                newStack.field_77994_a = count;
                AspectCropLootManager.addAspectLoot(aspect, newStack);
            }
        }
    }

    public static void addAspectLoot(Aspect aspect, ItemStack stack, int power) {
        lootMap.get(aspect).put(stack, power);
    }

    public static void populateLootMap() {
        int i;
        for (Aspect a : Aspect.aspects.values()) {
            lootMap.put(a, new HashMap());
        }
        AspectCropLootManager.addAspectLoot(Aspect.AIR, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, ItemInfusedGrain.getMetaForAspect(Aspect.AIR)));
        AspectCropLootManager.addAspectLoot(Aspect.FIRE, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, ItemInfusedGrain.getMetaForAspect(Aspect.FIRE)));
        AspectCropLootManager.addAspectLoot(Aspect.EARTH, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, ItemInfusedGrain.getMetaForAspect(Aspect.EARTH)));
        AspectCropLootManager.addAspectLoot(Aspect.WATER, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedGrain.class), 1, ItemInfusedGrain.getMetaForAspect(Aspect.WATER)));
        AspectCropLootManager.addAspectLoot(Aspect.ORDER, new ItemStack(Blocks.field_150359_w, 64));
        AspectCropLootManager.addAspectLoot(Aspect.ENTROPY, new ItemStack((Block)Blocks.field_150354_m, 64));
        AspectCropLootManager.addAspectLoot(Aspect.ELDRITCH, new ItemStack(Items.field_151079_bi, 4), 10);
        AspectCropLootManager.addAspectLoot(Aspect.ELDRITCH, new ItemStack(Items.field_151061_bv, 4), 5);
        AspectCropLootManager.addAspectLoot(Aspect.ELDRITCH, "bucketEnder");
        AspectCropLootManager.addAspectLoot(Aspect.TREE, "wood");
        AspectCropLootManager.addAspectLoot(Aspect.TREE, new ItemStack(Blocks.field_150364_r));
        for (Aspect tag : Aspect.aspects.values()) {
            ItemStack i2 = new ItemStack(ConfigItems.itemWispEssence, 1, 0);
            ((ItemWispEssence)ConfigItems.itemWispEssence).setAspects(i2, new AspectList().add(tag, 2));
            AspectCropLootManager.addAspectLoot(Aspect.AURA, i2);
        }
        for (int i3 = 0; i3 < 24; ++i3) {
            AspectCropLootManager.addAspectLoot(Aspect.BEAST, new ItemStack(Items.field_151063_bx, 1, i3));
        }
        AspectCropLootManager.addAspectLoot(Aspect.MIND, new ItemStack(Items.field_151121_aF, 64), 15);
        AspectCropLootManager.addAspectLoot(Aspect.MIND, new ItemStack(Items.field_151122_aG, 32), 10);
        AspectCropLootManager.addAspectLoot(Aspect.MIND, new ItemStack(Blocks.field_150342_X, 16), 5);
        for (Enchantment enchant : Enchantment.field_92090_c) {
            AspectCropLootManager.addAspectLoot(Aspect.MIND, Items.field_151134_bR.func_92111_a(new EnchantmentData(enchant, 1)), 1);
        }
        AspectCropLootManager.addAspectLoot(Aspect.FLESH, new ItemStack(ConfigItems.itemResource, 16, 5), 4);
        AspectCropLootManager.addAspectLoot(Aspect.FLESH, new ItemStack(ConfigItems.itemResource, 16, 5), 1);
        AspectCropLootManager.addAspectLoot(Aspect.UNDEAD, new ItemStack(Items.field_151078_bh, 32));
        AspectCropLootManager.addAspectLoot(Aspect.CRAFT, new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), 32));
        AspectCropLootManager.addAspectLoot(Aspect.CRAFT, new ItemStack(ConfigBlocks.blockStoneDevice, 16));
        AspectCropLootManager.addAspectLoot(Aspect.HUNGER, new ItemStack(Items.field_151075_bm, 16));
        AspectCropLootManager.addAspectLoot(Aspect.COLD, new ItemStack(Items.field_151126_ay, 16));
        AspectCropLootManager.addAspectLoot(Aspect.COLD, "rodBlizz");
        AspectCropLootManager.addAspectLoot(Aspect.PLANT, "sapling");
        for (i = 0; i < 6; ++i) {
            AspectCropLootManager.addAspectLoot(Aspect.PLANT, new ItemStack(Blocks.field_150345_g, 1, i));
        }
        for (i = 0; i < 12; ++i) {
            AspectCropLootManager.addAspectLoot(Aspect.MAN, new ItemStack(ConfigItems.itemGolemCore, 1, i));
        }
        AspectCropLootManager.addAspectLoot(Aspect.ARMOR, new ItemStack((Item)Items.field_151175_af));
        AspectCropLootManager.addAspectLoot(Aspect.ARMOR, new ItemStack((Item)Items.field_151173_ae));
        AspectCropLootManager.addAspectLoot(Aspect.ARMOR, new ItemStack((Item)Items.field_151163_ad));
        AspectCropLootManager.addAspectLoot(Aspect.ARMOR, new ItemStack((Item)Items.field_151161_ac));
        AspectCropLootManager.addAspectLoot(Aspect.MINE, new ItemStack(ConfigItems.itemPickThaumium));
        AspectCropLootManager.addAspectLoot(Aspect.HARVEST, new ItemStack(ConfigItems.itemHoeThaumium));
        AspectCropLootManager.addAspectLoot(Aspect.WEAPON, new ItemStack(ConfigItems.itemSwordThaumium));
        AspectCropLootManager.addAspectLoot(Aspect.TOOL, new ItemStack(ConfigItems.itemShovelThaumium));
        AspectCropLootManager.addAspectLoot(Aspect.TOOL, new ItemStack(ConfigItems.itemAxeThaumium));
        AspectCropLootManager.addAspectLoot(Aspect.TRAVEL, new ItemStack(ConfigBlocks.blockCosmeticSolid, 8, 7));
        AspectCropLootManager.addAspectLoot(Aspect.SLIME, new ItemStack(Items.field_151123_aH, 16));
        AspectCropLootManager.addAspectLoot(Aspect.SLIME, "slime");
        AspectCropLootManager.addAspectLoot(Aspect.GREED, new ItemStack(Items.field_151043_k, 4));
        AspectCropLootManager.addAspectLoot(Aspect.LIGHT, new ItemStack(Items.field_151114_aO, 16), 5);
        AspectCropLootManager.addAspectLoot(Aspect.LIGHT, new ItemStack(ConfigItems.itemResource, 4, 1));
        AspectCropLootManager.addAspectLoot(Aspect.LIGHT, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemBrightNitor.class)));
        AspectCropLootManager.addAspectLoot(Aspect.MECHANISM, new ItemStack((Block)Blocks.field_150331_J, 8));
        AspectCropLootManager.addAspectLoot(Aspect.MECHANISM, "gear");
        AspectCropLootManager.addAspectLoot(Aspect.CROP, new ItemStack(Items.field_151015_O, 32));
        AspectCropLootManager.addAspectLoot(Aspect.METAL, new ItemStack(Items.field_151042_j, 4), 100);
        AspectCropLootManager.addAspectLoot(Aspect.METAL, "iron");
        AspectCropLootManager.addAspectLoot(Aspect.DEATH, new ItemStack(Items.field_151103_aS, 32));
        AspectCropLootManager.addAspectLoot(Aspect.MOTION, new ItemStack(Blocks.field_150448_aq), 10);
        AspectCropLootManager.addAspectLoot(Aspect.MOTION, new ItemStack(Blocks.field_150408_cc));
        AspectCropLootManager.addAspectLoot(Aspect.CLOTH, new ItemStack(Blocks.field_150325_L, 16), 30);
        AspectCropLootManager.addAspectLoot(Aspect.CLOTH, new ItemStack(Items.field_151007_F, 15), 10);
        for (i = 0; i < 16; ++i) {
            AspectCropLootManager.addAspectLoot(Aspect.CLOTH, new ItemStack(Blocks.field_150325_L, 4, i));
        }
        AspectCropLootManager.addAspectLoot(Aspect.EXCHANGE, "ingotCopper", 4);
        AspectCropLootManager.addAspectLoot(Aspect.EXCHANGE, new ItemStack(ConfigBlocks.blockCustomOre, 4));
        AspectCropLootManager.addAspectLoot(Aspect.ENERGY, new ItemStack(ConfigItems.itemResource, 12));
        AspectCropLootManager.addAspectLoot(Aspect.MAGIC, "shard");
        AspectCropLootManager.addAspectLoot(Aspect.HEAL, new ItemStack(Items.field_151153_ao));
        AspectCropLootManager.addAspectLoot(Aspect.HEAL, new ItemStack(Blocks.field_150414_aQ));
        AspectCropLootManager.addAspectLoot(Aspect.SENSES, new ItemStack(Items.field_151100_aR, 20, 4));
        AspectCropLootManager.addAspectLoot(Aspect.SOUL, new ItemStack(Blocks.field_150425_aM, 64), 2);
        AspectCropLootManager.addAspectLoot(Aspect.SOUL, new ItemStack(Blocks.field_150424_aL, 64), 2);
        AspectCropLootManager.addAspectLoot(Aspect.SOUL, new ItemStack(Blocks.field_150385_bj));
        AspectCropLootManager.addAspectLoot(Aspect.WEATHER, new ItemStack(Blocks.field_150350_a));
        AspectCropLootManager.addAspectLoot(Aspect.WEATHER, "cloud", 100);
        AspectCropLootManager.addAspectLoot(Aspect.DARKNESS, new ItemStack(Blocks.field_150343_Z, 10));
        AspectCropLootManager.addAspectLoot(Aspect.VOID, new ItemStack(Items.field_151133_ar));
        AspectCropLootManager.addAspectLoot(Aspect.VOID, "bucket");
        AspectCropLootManager.addAspectLoot(Aspect.POISON, new ItemStack(ConfigItems.itemResource, 16, 3));
        AspectCropLootManager.addAspectLoot(Aspect.LIFE, new ItemStack(Items.field_151110_aK, 8));
        AspectCropLootManager.addAspectLoot(Aspect.TRAP, new ItemStack(Blocks.field_150321_G, 4));
        AspectCropLootManager.addAspectLoot(Aspect.TAINT, new ItemStack(ConfigItems.itemResource, 4, 11));
        AspectCropLootManager.addAspectLoot(Aspect.CRYSTAL, new ItemStack(Items.field_151045_i));
    }
}

