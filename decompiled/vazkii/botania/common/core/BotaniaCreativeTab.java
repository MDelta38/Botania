/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.core;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;

public final class BotaniaCreativeTab
extends CreativeTabs {
    public static BotaniaCreativeTab INSTANCE = new BotaniaCreativeTab();
    List list;

    public BotaniaCreativeTab() {
        super("Botania");
        this.func_78014_h();
        this.func_78025_a("botania.png");
    }

    public ItemStack func_151244_d() {
        return new ItemStack(ModItems.lexicon);
    }

    public Item func_78016_d() {
        return this.func_151244_d().func_77973_b();
    }

    public boolean hasSearchBar() {
        return true;
    }

    public void func_78018_a(List list) {
        int i;
        this.list = list;
        this.addItem(ModItems.lexicon);
        this.addBlock(ModBlocks.flower);
        this.addBlock(ModBlocks.specialFlower);
        this.addItem(ModItems.petal);
        this.addItem(ModItems.pestleAndMortar);
        this.addItem(ModItems.dye);
        this.addItem(ModItems.fertilizer);
        this.addItem(ModItems.flowerBag);
        this.addItem(ModItems.blackLotus);
        this.addItem(ModItems.twigWand);
        this.addItem(ModItems.obedienceStick);
        this.addItem(ModItems.manaResource);
        this.addBlock(ModBlocks.storage);
        this.addItem(ModItems.manaCookie);
        this.addItem(ModItems.rune);
        this.addBlock(ModBlocks.avatar);
        this.addItem(ModItems.dirtRod);
        this.addItem(ModItems.skyDirtRod);
        this.addItem(ModItems.cobbleRod);
        this.addItem(ModItems.terraformRod);
        this.addItem(ModItems.laputaShard);
        this.addItem(ModItems.grassHorn);
        this.addItem(ModItems.waterRod);
        this.addItem(ModItems.openBucket);
        this.addItem(ModItems.rainbowRod);
        this.addBlock(ModBlocks.bifrostPerm);
        this.addBlock(ModFluffBlocks.bifrostPane);
        this.addBlock(ModBlocks.shimmerrock);
        this.addBlock(ModBlocks.shimmerwoodPlanks);
        this.addItem(ModItems.tornadoRod);
        this.addItem(ModItems.fireRod);
        this.addItem(ModItems.smeltRod);
        this.addItem(ModItems.exchangeRod);
        this.addItem(ModItems.diviningRod);
        this.addItem(ModItems.gravityRod);
        this.addItem(ModItems.missileRod);
        this.addItem(ModItems.virus);
        this.addItem(ModItems.slingshot);
        this.addItem(ModItems.vineBall);
        this.addItem(ModItems.regenIvy);
        this.addItem(ModItems.keepIvy);
        this.addItem(ModItems.worldSeed);
        this.addItem(ModItems.overgrowthSeed);
        this.addBlock(ModBlocks.enchantedSoil);
        this.addItem(ModItems.grassSeeds);
        this.addBlock(ModBlocks.altGrass);
        if (Botania.thaumcraftLoaded) {
            this.addItem(ModItems.manaInkwell);
        }
        this.addBlock(ModBlocks.forestDrum);
        this.addBlock(ModBlocks.forestEye);
        this.addBlock(ModBlocks.enderEye);
        this.addItem(ModItems.enderHand);
        this.addItem(ModItems.spellCloth);
        this.addItem(ModItems.craftingHalo);
        this.addItem(ModItems.autocraftingHalo);
        this.addItem(ModItems.spawnerMover);
        this.addBlock(ModBlocks.spawnerClaw);
        this.addBlock(ModBlocks.cocoon);
        this.addBlock(ModBlocks.teruTeruBozu);
        this.addItem(ModItems.slimeBottle);
        this.addItem(ModItems.sextant);
        this.addItem(ModItems.blackHoleTalisman);
        if (Botania.gardenOfGlassLoaded) {
            this.addBlock(ModBlocks.root);
            this.addItem(ModItems.waterBowl);
        }
        this.addBlock(ModBlocks.livingrock);
        this.addBlock(ModBlocks.livingwood);
        this.addBlock(ModBlocks.openCrate);
        this.addItem(ModItems.craftPattern);
        this.addBlock(ModBlocks.platform);
        this.addBlock(ModBlocks.alfPortal);
        this.addBlock(ModBlocks.altar);
        this.addBlock(ModBlocks.runeAltar);
        this.addBlock(ModBlocks.terraPlate);
        this.addBlock(ModBlocks.brewery);
        this.addItem(ModItems.vial);
        this.addItem(ModItems.brewVial);
        this.addItem(ModItems.brewFlask);
        this.addBlock(ModBlocks.incensePlate);
        this.addItem(ModItems.incenseStick);
        this.addItem(ModItems.bloodPendant);
        this.addBlock(ModBlocks.felPumpkin);
        this.addBlock(ModBlocks.pylon);
        this.addBlock(ModBlocks.pistonRelay);
        this.addBlock(ModBlocks.hourglass);
        this.addBlock(ModBlocks.redStringContainer);
        this.addBlock(ModBlocks.redStringDispenser);
        this.addBlock(ModBlocks.redStringFertilizer);
        this.addBlock(ModBlocks.redStringComparator);
        this.addBlock(ModBlocks.redStringRelay);
        this.addBlock(ModBlocks.redStringInterceptor);
        this.addBlock(ModBlocks.tinyPotato);
        this.addBlock(ModBlocks.starfield);
        this.addBlock(ModBlocks.dreamwood);
        this.addBlock(ModBlocks.manaGlass);
        this.addBlock(ModFluffBlocks.managlassPane);
        this.addBlock(ModBlocks.elfGlass);
        this.addBlock(ModFluffBlocks.alfglassPane);
        this.addItem(ModItems.glassPick);
        this.addItem(ModItems.manasteelPick);
        this.addItem(ModItems.manasteelShovel);
        this.addItem(ModItems.manasteelAxe);
        this.addItem(ModItems.manasteelShears);
        this.addItem(ModItems.manasteelSword);
        this.addItem(ModItems.enderDagger);
        this.addItem(ModItems.livingwoodBow);
        this.addItem(ModItems.manasteelHelm);
        if (Botania.thaumcraftLoaded) {
            this.addItem(ModItems.manasteelHelmRevealing);
        }
        this.addItem(ModItems.manasteelChest);
        this.addItem(ModItems.manasteelLegs);
        this.addItem(ModItems.manasteelBoots);
        this.addItem(ModItems.manaweaveHelm);
        this.addItem(ModItems.manaweaveChest);
        this.addItem(ModItems.manaweaveLegs);
        this.addItem(ModItems.manaweaveBoots);
        this.addItem(ModItems.elementiumPick);
        this.addItem(ModItems.elementiumShovel);
        this.addItem(ModItems.elementiumAxe);
        this.addItem(ModItems.elementiumShears);
        this.addItem(ModItems.elementiumSword);
        this.addItem(ModItems.starSword);
        this.addItem(ModItems.thunderSword);
        this.addItem(ModItems.crystalBow);
        this.addItem(ModItems.elementiumHelm);
        if (Botania.thaumcraftLoaded) {
            this.addItem(ModItems.elementiumHelmRevealing);
        }
        this.addItem(ModItems.elementiumChest);
        this.addItem(ModItems.elementiumLegs);
        this.addItem(ModItems.elementiumBoots);
        this.addItem(ModItems.terraSword);
        this.addItem(ModItems.thornChakram);
        this.addItem(ModItems.terraPick);
        this.addItem(ModItems.terraAxe);
        this.addItem(ModItems.temperanceStone);
        this.addItem(ModItems.terrasteelHelm);
        if (Botania.thaumcraftLoaded) {
            this.addItem(ModItems.terrasteelHelmRevealing);
        }
        this.addItem(ModItems.terrasteelChest);
        this.addItem(ModItems.terrasteelLegs);
        this.addItem(ModItems.terrasteelBoots);
        this.addItem(ModItems.phantomInk);
        this.addItem(ModItems.cacophonium);
        this.addItem(ModItems.recordGaia1);
        this.addItem(ModItems.recordGaia2);
        this.addItem(ModItems.ancientWill);
        this.addItem(ModItems.pinkinator);
        this.addItem(ModItems.gaiaHead);
        if (ConfigHandler.relicsEnabled) {
            this.addItem(ModItems.dice);
            this.addItem(ModItems.infiniteFruit);
            this.addItem(ModItems.kingKey);
            this.addItem(ModItems.flugelEye);
            this.addItem(ModItems.thorRing);
            this.addItem(ModItems.odinRing);
            this.addItem(ModItems.lokiRing);
            this.addItem(ModItems.aesirRing);
        }
        this.addItem(ModItems.baubleBox);
        this.addItem(ModItems.tinyPlanet);
        this.addBlock(ModBlocks.tinyPlanet);
        this.addItem(ModItems.manaRing);
        this.addItem(ModItems.auraRing);
        this.addItem(ModItems.manaRingGreater);
        this.addItem(ModItems.auraRingGreater);
        this.addItem(ModItems.waterRing);
        this.addItem(ModItems.miningRing);
        this.addItem(ModItems.magnetRing);
        this.addItem(ModItems.magnetRingGreater);
        this.addItem(ModItems.swapRing);
        this.addItem(ModItems.reachRing);
        this.addItem(ModItems.pixieRing);
        this.addItem(ModItems.travelBelt);
        this.addItem(ModItems.superTravelBelt);
        this.addItem(ModItems.speedUpBelt);
        this.addItem(ModItems.knockbackBelt);
        this.addItem(ModItems.itemFinder);
        this.addItem(ModItems.monocle);
        this.addItem(ModItems.icePendant);
        this.addItem(ModItems.lavaPendant);
        this.addItem(ModItems.superLavaPendant);
        this.addItem(ModItems.holyCloak);
        this.addItem(ModItems.unholyCloak);
        this.addItem(ModItems.goldLaurel);
        this.addItem(ModItems.divaCharm);
        this.addItem(ModItems.flightTiara);
        this.addItem(ModItems.manaTablet);
        this.addItem(ModItems.manaMirror);
        this.addItem(ModItems.manaBottle);
        this.addBlock(ModBlocks.pool);
        this.addBlock(ModBlocks.alchemyCatalyst);
        this.addBlock(ModBlocks.conjurationCatalyst);
        this.addBlock(ModBlocks.distributor);
        this.addBlock(ModBlocks.manaVoid);
        this.addBlock(ModBlocks.bellows);
        this.addBlock(ModBlocks.manaDetector);
        this.addBlock(ModBlocks.manaBomb);
        this.addBlock(ModBlocks.ghostRail);
        this.addItem(ModItems.poolMinecart);
        this.addBlock(ModBlocks.pump);
        this.addBlock(ModBlocks.rfGenerator);
        this.addBlock(ModBlocks.spreader);
        this.addBlock(ModBlocks.turntable);
        this.addBlock(ModBlocks.prism);
        this.addItem(ModItems.lens);
        this.addItem(ModItems.manaGun);
        this.addItem(ModItems.clip);
        this.addItem(ModItems.spark);
        this.addItem(ModItems.sparkUpgrade);
        this.addBlock(ModBlocks.sparkChanger);
        this.addItem(ModItems.corporeaSpark);
        this.addBlock(ModBlocks.corporeaIndex);
        this.addBlock(ModBlocks.corporeaFunnel);
        this.addBlock(ModBlocks.corporeaInterceptor);
        this.addBlock(ModBlocks.corporeaRetainer);
        this.addBlock(ModBlocks.corporeaCrystalCube);
        this.addBlock(ModBlocks.lightRelay);
        this.addBlock(ModBlocks.lightLauncher);
        this.addBlock(ModBlocks.cellBlock);
        this.addBlock(ModBlocks.doubleFlower1);
        this.addBlock(ModBlocks.doubleFlower2);
        this.addBlock(ModBlocks.shinyFlower);
        this.addBlock(ModBlocks.floatingFlower);
        this.addBlock(ModBlocks.floatingSpecialFlower);
        this.addBlock(ModBlocks.petalBlock);
        this.addBlock(ModBlocks.mushroom);
        this.addBlock(ModBlocks.unstableBlock);
        this.addBlock(ModBlocks.manaBeacon);
        this.addItem(ModItems.signalFlare);
        this.addStack(new ItemStack(Blocks.field_150346_d, 1, 1));
        this.addBlock(ModBlocks.dirtPath);
        this.addBlock(ModFluffBlocks.dirtPathSlab);
        this.addBlock(ModBlocks.prismarine);
        this.addBlock(ModBlocks.seaLamp);
        this.addBlock(ModFluffBlocks.prismarineStairs);
        this.addBlock(ModFluffBlocks.prismarineSlab);
        this.addBlock(ModFluffBlocks.prismarineWall);
        this.addBlock(ModFluffBlocks.prismarineBrickStairs);
        this.addBlock(ModFluffBlocks.prismarineBrickSlab);
        this.addBlock(ModFluffBlocks.darkPrismarineStairs);
        this.addBlock(ModFluffBlocks.darkPrismarineSlab);
        this.addBlock(ModBlocks.blazeBlock);
        this.addBlock(ModBlocks.reedBlock);
        this.addBlock(ModFluffBlocks.reedStairs);
        this.addBlock(ModFluffBlocks.reedSlab);
        this.addBlock(ModFluffBlocks.reedWall);
        this.addBlock(ModBlocks.thatch);
        this.addBlock(ModFluffBlocks.thatchStairs);
        this.addBlock(ModFluffBlocks.thatchSlab);
        this.addBlock(ModBlocks.customBrick);
        this.addBlock(ModFluffBlocks.netherBrickStairs);
        this.addBlock(ModFluffBlocks.netherBrickSlab);
        this.addBlock(ModFluffBlocks.soulBrickStairs);
        this.addBlock(ModFluffBlocks.soulBrickSlab);
        this.addBlock(ModFluffBlocks.snowBrickStairs);
        this.addBlock(ModFluffBlocks.snowBrickSlab);
        this.addBlock(ModFluffBlocks.tileStairs);
        this.addBlock(ModFluffBlocks.tileSlab);
        this.addBlock(ModFluffBlocks.livingwoodStairs);
        this.addBlock(ModFluffBlocks.livingwoodSlab);
        this.addBlock(ModFluffBlocks.livingwoodWall);
        this.addBlock(ModFluffBlocks.livingwoodPlankStairs);
        this.addBlock(ModFluffBlocks.livingwoodPlankSlab);
        this.addBlock(ModFluffBlocks.livingrockStairs);
        this.addBlock(ModFluffBlocks.livingrockSlab);
        this.addBlock(ModFluffBlocks.livingrockWall);
        this.addBlock(ModFluffBlocks.livingrockBrickStairs);
        this.addBlock(ModFluffBlocks.livingrockBrickSlab);
        this.addBlock(ModFluffBlocks.dreamwoodStairs);
        this.addBlock(ModFluffBlocks.dreamwoodSlab);
        this.addBlock(ModFluffBlocks.dreamwoodWall);
        this.addBlock(ModFluffBlocks.dreamwoodPlankStairs);
        this.addBlock(ModFluffBlocks.dreamwoodPlankSlab);
        this.addBlock(ModFluffBlocks.shimmerwoodPlankStairs);
        this.addBlock(ModFluffBlocks.shimmerwoodPlankSlab);
        this.addBlock(ModFluffBlocks.shimmerrockStairs);
        this.addBlock(ModFluffBlocks.shimmerrockSlab);
        this.addItem(ModItems.quartz);
        if (ConfigHandler.darkQuartzEnabled) {
            this.addBlock(ModFluffBlocks.darkQuartz);
            this.addBlock(ModFluffBlocks.darkQuartzSlab);
            this.addBlock(ModFluffBlocks.darkQuartzStairs);
        }
        this.addBlock(ModFluffBlocks.manaQuartz);
        this.addBlock(ModFluffBlocks.manaQuartzSlab);
        this.addBlock(ModFluffBlocks.manaQuartzStairs);
        this.addBlock(ModFluffBlocks.blazeQuartz);
        this.addBlock(ModFluffBlocks.blazeQuartzSlab);
        this.addBlock(ModFluffBlocks.blazeQuartzStairs);
        this.addBlock(ModFluffBlocks.lavenderQuartz);
        this.addBlock(ModFluffBlocks.lavenderQuartzSlab);
        this.addBlock(ModFluffBlocks.lavenderQuartzStairs);
        this.addBlock(ModFluffBlocks.redQuartz);
        this.addBlock(ModFluffBlocks.redQuartzSlab);
        this.addBlock(ModFluffBlocks.redQuartzStairs);
        this.addBlock(ModFluffBlocks.elfQuartz);
        this.addBlock(ModFluffBlocks.elfQuartzSlab);
        this.addBlock(ModFluffBlocks.elfQuartzStairs);
        this.addBlock(ModFluffBlocks.sunnyQuartz);
        this.addBlock(ModFluffBlocks.sunnyQuartzSlab);
        this.addBlock(ModFluffBlocks.sunnyQuartzStairs);
        if (ConfigHandler.stones18Enabled) {
            this.addBlock(ModFluffBlocks.stone);
            for (i = 0; i < 8; ++i) {
                this.addBlock(ModFluffBlocks.stoneStairs[i]);
            }
            for (i = 0; i < 8; ++i) {
                this.addBlock(ModFluffBlocks.stoneSlabs[i]);
            }
            this.addBlock(ModFluffBlocks.stoneWall);
        }
        this.addBlock(ModFluffBlocks.biomeStoneA);
        this.addBlock(ModFluffBlocks.biomeStoneB);
        for (i = 0; i < 24; ++i) {
            this.addBlock(ModFluffBlocks.biomeStoneStairs[i]);
        }
        for (i = 0; i < 24; ++i) {
            this.addBlock(ModFluffBlocks.biomeStoneSlabs[i]);
        }
        this.addBlock(ModFluffBlocks.biomeStoneWall);
        this.addBlock(ModFluffBlocks.pavement);
        for (Block pavementStair : ModFluffBlocks.pavementStairs) {
            this.addBlock(pavementStair);
        }
        for (Block pavementSlab : ModFluffBlocks.pavementSlabs) {
            this.addBlock(pavementSlab);
        }
        if (ConfigHandler.enderStuff19Enabled) {
            this.addBlock(ModBlocks.endStoneBrick);
            this.addBlock(ModFluffBlocks.endStoneSlab);
            this.addBlock(ModFluffBlocks.endStoneStairs);
            this.addBlock(ModFluffBlocks.enderBrickSlab);
            this.addBlock(ModFluffBlocks.enderBrickStairs);
        }
        this.addItem(ModItems.cosmetic);
    }

    private void addItem(Item item) {
        item.func_150895_a(item, (CreativeTabs)this, this.list);
    }

    private void addBlock(Block block) {
        ItemStack stack = new ItemStack(block);
        block.func_149666_a(stack.func_77973_b(), (CreativeTabs)this, this.list);
    }

    private void addStack(ItemStack stack) {
        this.list.add(stack);
    }
}

