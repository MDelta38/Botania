/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  powercrystals.minefactoryreloaded.api.FactoryRegistry
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDeathsHorse;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntityVampire;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.integration.ModHook;
import com.emoniph.witchery.util.Log;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;

public class ModHookMineFactoryReloaded
extends ModHook {
    @Override
    public String getModID() {
        return "MineFactoryReloaded";
    }

    @Override
    protected void doInit() {
    }

    @Override
    protected void doPostInit() {
        IntegrateMineFactoryReloaded.register();
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
    }

    public static class IntegrateMineFactoryReloaded {
        private static NBTTagCompound getFertilizableCrop(BlockWitchCrop crop) {
            NBTTagCompound nbtRoot = new NBTTagCompound();
            nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c((Object)crop));
            nbtRoot.func_74768_a("meta", crop.getNumGrowthStages());
            return nbtRoot;
        }

        private static NBTTagCompound getPlantableCrop(BlockWitchCrop crop, Item seeds) {
            NBTTagCompound nbtRoot = new NBTTagCompound();
            nbtRoot.func_74778_a("seed", Item.field_150901_e.func_148750_c((Object)seeds));
            nbtRoot.func_74778_a("crop", Block.field_149771_c.func_148750_c((Object)crop));
            return nbtRoot;
        }

        private static NBTTagCompound getPlantableSapling(Block sapling) {
            NBTTagCompound nbtRoot = new NBTTagCompound();
            nbtRoot.func_74778_a("sapling", Block.field_149771_c.func_148750_c((Object)sapling));
            return nbtRoot;
        }

        private static NBTTagCompound getFertilizableSapling(Block sapling) {
            NBTTagCompound nbtRoot = new NBTTagCompound();
            nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c((Object)sapling));
            return nbtRoot;
        }

        public static void register() {
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_ARTICHOKE, 1, Witchery.Blocks.CROP_ARTICHOKE.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_MANDRAKE, 1, Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_BELLADONNA, 1, Witchery.Blocks.CROP_BELLADONNA.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_SNOWBELL, 1, Witchery.Blocks.CROP_SNOWBELL.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_WORMWOOD, 1, Witchery.Blocks.CROP_WORMWOOD.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_MINDRAKE, 1, Witchery.Blocks.CROP_MINDRAKE.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_WOLFSBANE, 1, Witchery.Blocks.CROP_WOLFSBANE.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Crop", (Object)new ItemStack((Block)Witchery.Blocks.CROP_GARLIC, 1, Witchery.Blocks.CROP_GARLIC.getNumGrowthStages()));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Log", (Object)Block.field_149771_c.func_148750_c((Object)Witchery.Blocks.LOG));
            FactoryRegistry.sendMessage((String)"registerHarvestable_Leaves", (Object)Block.field_149771_c.func_148750_c((Object)Witchery.Blocks.LEAVES));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_ARTICHOKE));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_MANDRAKE));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_BELLADONNA));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_SNOWBELL));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_WORMWOOD));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_MINDRAKE));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_WOLFSBANE));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Crop", (Object)IntegrateMineFactoryReloaded.getFertilizableCrop(Witchery.Blocks.CROP_GARLIC));
            FactoryRegistry.sendMessage((String)"registerFertilizable_Standard", (Object)IntegrateMineFactoryReloaded.getFertilizableSapling(Witchery.Blocks.SAPLING));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_ARTICHOKE, Witchery.Items.SEEDS_ARTICHOKE));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_MANDRAKE, Witchery.Items.SEEDS_MANDRAKE));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_BELLADONNA, Witchery.Items.SEEDS_BELLADONNA));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_SNOWBELL, Witchery.Items.SEEDS_SNOWBELL));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_WORMWOOD, Witchery.Items.SEEDS_WORMWOOD));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_MINDRAKE, Witchery.Items.SEEDS_MINDRAKE));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_WOLFSBANE, Witchery.Items.SEEDS_WOLFSBANE));
            FactoryRegistry.sendMessage((String)"registerPlantable_Crop", (Object)IntegrateMineFactoryReloaded.getPlantableCrop(Witchery.Blocks.CROP_GARLIC, Witchery.Items.SEEDS_GARLIC));
            FactoryRegistry.sendMessage((String)"registerPlantable_Sapling", (Object)IntegrateMineFactoryReloaded.getPlantableSapling(Witchery.Blocks.SAPLING));
            try {
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityCovenWitch.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityNightmare.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityDemon.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityEnt.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityBabaYaga.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityHornedHuntsman.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityIllusionSpider.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityIllusionZombie.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityIllusionCreeper.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityFamiliar.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityCorpse.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntitySpirit.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntitySpectre.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityPoltergeist.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityBanshee.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityDeath.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityWitchHunter.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityImp.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityLordOfTorment.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityGoblinMog.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityGoblinGulg.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityDeathsHorse.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityLeonard.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityLostSoul.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityVampire.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityWolfman.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityLilith.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityFollower.class);
                FactoryRegistry.sendMessage((String)"registerGrinderBlacklist", EntityReflection.class);
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occurred setting up MFR grinder blacklist");
            }
            try {
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityCovenWitch.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityNightmare.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityDemon.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityEnt.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityBabaYaga.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityHornedHuntsman.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityIllusionCreeper.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityIllusionSpider.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityIllusionZombie.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityFamiliar.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityCorpse.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntitySpirit.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntitySpectre.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityBanshee.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityPoltergeist.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityDeath.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityLordOfTorment.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityGoblinMog.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityGoblinGulg.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityDeathsHorse.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityLeonard.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityLostSoul.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityLilith.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityFollower.class);
                FactoryRegistry.sendMessage((String)"registerSafariNetBlacklist", EntityReflection.class);
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occurred setting up MFR safari net blacklist");
            }
            try {
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityCovenWitch.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityNightmare.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityDemon.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityEnt.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityBabaYaga.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityHornedHuntsman.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityIllusionCreeper.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityIllusionSpider.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityIllusionZombie.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityFamiliar.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntitySpirit.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntitySpectre.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityBanshee.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityPoltergeist.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityDeath.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityWitchHunter.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityMindrake.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityImp.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityLordOfTorment.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityMindrake.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityDeathsHorse.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityGoblin.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityGoblinGulg.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityGoblinMog.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityLeonard.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityLostSoul.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityWolfman.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityVampire.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityLilith.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityFollower.class);
                FactoryRegistry.sendMessage((String)"registerAutoSpawnerBlacklist", EntityReflection.class);
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occurred setting up MFR autospawner blacklist");
            }
        }
    }
}

