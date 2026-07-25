/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package thaumcraft.common.config;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.EntityFallingTaint;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.entities.EntityItemGrate;
import thaumcraft.common.entities.EntityPermanentItem;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.entities.ItemSpawnerEgg;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityGolemBobber;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.EntityTaintChicken;
import thaumcraft.common.entities.monster.EntityTaintCow;
import thaumcraft.common.entities.monster.EntityTaintCreeper;
import thaumcraft.common.entities.monster.EntityTaintPig;
import thaumcraft.common.entities.monster.EntityTaintSheep;
import thaumcraft.common.entities.monster.EntityTaintSpider;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
import thaumcraft.common.entities.monster.EntityTaintSwarm;
import thaumcraft.common.entities.monster.EntityTaintVillager;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.entities.monster.EntityTaintacleSmall;
import thaumcraft.common.entities.monster.EntityThaumicSlime;
import thaumcraft.common.entities.monster.EntityWatcher;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.entities.projectile.EntityBottleTaint;
import thaumcraft.common.entities.projectile.EntityDart;
import thaumcraft.common.entities.projectile.EntityEldritchOrb;
import thaumcraft.common.entities.projectile.EntityEmber;
import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
import thaumcraft.common.entities.projectile.EntityFrostShard;
import thaumcraft.common.entities.projectile.EntityGolemOrb;
import thaumcraft.common.entities.projectile.EntityPechBlast;
import thaumcraft.common.entities.projectile.EntityPrimalArrow;
import thaumcraft.common.entities.projectile.EntityPrimalOrb;
import thaumcraft.common.entities.projectile.EntityShockOrb;

public class ConfigEntities {
    public static int entWizardId = 190;
    public static int entBankerId = 191;
    public static HashMap<Class, Integer> championModWhitelist = new HashMap();

    public static void init() {
        int id = 0;
        EntityRegistry.registerModEntity(EntitySpecialItem.class, (String)"SpecialItem", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityPermanentItem.class, (String)"PermanentItem", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityFollowingItem.class, (String)"FollowItem", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        EntityRegistry.registerModEntity(EntityAspectOrb.class, (String)"AspectOrb", (int)id++, (Object)Thaumcraft.instance, (int)120, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityFallingTaint.class, (String)"FallingTaint", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityAlumentum.class, (String)"Alumentum", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityPrimalOrb.class, (String)"PrimalOrb", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityFrostShard.class, (String)"FrostShard", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityDart.class, (String)"Dart", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        EntityRegistry.registerModEntity(EntityPrimalArrow.class, (String)"PrimalArrow", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        EntityRegistry.registerModEntity(EntityPechBlast.class, (String)"PechBlast", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityEldritchOrb.class, (String)"EldritchOrb", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityBottleTaint.class, (String)"BottleTaint", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityGolemOrb.class, (String)"GolemOrb", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityShockOrb.class, (String)"ShockOrb", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityExplosiveOrb.class, (String)"ExplosiveOrb", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityEmber.class, (String)"Ember", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityGolemBase.class, (String)"Golem", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityTravelingTrunk.class, (String)"TravelingTrunk", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityBrainyZombie.class, (String)"BrainyZombie", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("BrainyZombie", 0xFFC0FF, 32768);
        EntityRegistry.registerModEntity(EntityGiantBrainyZombie.class, (String)"GiantBrainyZombie", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("GiantBrainyZombie", 0xFFC0FF, 16384);
        EntityRegistry.registerModEntity(EntityWisp.class, (String)"Wisp", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("Wisp", 0xFFC0FF, 0xFFFFFF);
        EntityRegistry.registerModEntity(EntityFireBat.class, (String)"Firebat", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("Firebat", 0xFFC0FF, 0xC00000);
        EntityRegistry.registerModEntity(EntityPech.class, (String)"Pech", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("Pech", 0xFFC0FF, 0x400040);
        EntityRegistry.registerModEntity(EntityMindSpider.class, (String)"MindSpider", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("MindSpider", 0xAAAAAA, 0x404040);
        EntityRegistry.registerModEntity(EntityEldritchGuardian.class, (String)"EldritchGuardian", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("EldritchGuardian", 0x222222, 0x404040);
        EntityRegistry.registerModEntity(EntityEldritchWarden.class, (String)"EldritchWarden", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("EldritchWarden", 0x552222, 0x404040);
        EntityRegistry.registerModEntity(EntityCultistKnight.class, (String)"CultistKnight", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("CultistKnight", 0xFF5055, 128);
        EntityRegistry.registerModEntity(EntityCultistCleric.class, (String)"CultistCleric", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("CultistCleric", 0xFF5055, 0x800000);
        EntityRegistry.registerModEntity(EntityCultistLeader.class, (String)"CultistLeader", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("CultistLeader", 0xFF5055, 0x505050);
        EntityRegistry.registerModEntity(EntityCultistPortal.class, (String)"CultistPortal", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        ItemSpawnerEgg.addMapping("CultistPortal", 0xFF5055, 0xFF50FF);
        EntityRegistry.registerModEntity(EntityEldritchGolem.class, (String)"EldritchGolem", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("EldritchGolem", 0x555555, 0x404040);
        EntityRegistry.registerModEntity(EntityEldritchCrab.class, (String)"EldritchCrab", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("EldritchCrab", 0x555555, 0x550000);
        EntityRegistry.registerModEntity(EntityInhabitedZombie.class, (String)"InhabitedZombie", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("InhabitedZombie", 0x557755, 0x550000);
        EntityRegistry.registerModEntity(EntityThaumicSlime.class, (String)"ThaumSlime", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("ThaumSlime", 0xFFC0FF, 0xFF80FF);
        EntityRegistry.registerModEntity(EntityTaintSpider.class, (String)"TaintSpider", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintSpider", 0xFFC0FF, 0x404040);
        EntityRegistry.registerModEntity(EntityTaintacle.class, (String)"Taintacle", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)false);
        ItemSpawnerEgg.addMapping("Taintacle", 0xFFC0FF, 0x800080);
        EntityRegistry.registerModEntity(EntityTaintacleSmall.class, (String)"TaintacleTiny", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)false);
        ItemSpawnerEgg.addMapping("TaintacleTiny", 0xFFC0FF, 0x800090);
        EntityRegistry.registerModEntity(EntityTaintSpore.class, (String)"TaintSpore", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        ItemSpawnerEgg.addMapping("TaintSpore", 0xFFC0FF, 0x800070);
        EntityRegistry.registerModEntity(EntityTaintSporeSwarmer.class, (String)"TaintSwarmer", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)false);
        ItemSpawnerEgg.addMapping("TaintSwarmer", 0xFFC0FF, 0x800060);
        EntityRegistry.registerModEntity(EntityTaintSwarm.class, (String)"TaintSwarm", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintSwarm", 0xFFC0FF, 0x800050);
        EntityRegistry.registerModEntity(EntityTaintChicken.class, (String)"TaintedChicken", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedChicken", 0xFFC0FF, 0xC0C0C0);
        EntityRegistry.registerModEntity(EntityTaintCow.class, (String)"TaintedCow", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedCow", 0xFFC0FF, 8272443);
        EntityRegistry.registerModEntity(EntityTaintCreeper.class, (String)"TaintedCreeper", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedCreeper", 0xFFC0FF, 65280);
        EntityRegistry.registerModEntity(EntityTaintPig.class, (String)"TaintedPig", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedPig", 0xFFC0FF, 0xEF99EF);
        EntityRegistry.registerModEntity(EntityTaintSheep.class, (String)"TaintedSheep", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedSheep", 0xFFC0FF, 0x808080);
        EntityRegistry.registerModEntity(EntityTaintVillager.class, (String)"TaintedVillager", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)true);
        ItemSpawnerEgg.addMapping("TaintedVillager", 0xFFC0FF, 65535);
        EntityRegistry.registerModEntity(EntityTaintacleGiant.class, (String)"TaintacleGiant", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)3, (boolean)false);
        ItemSpawnerEgg.addMapping("TaintacleGiant", 0xFFC0FF, 0x808080);
        EntityRegistry.registerModEntity(EntityItemGrate.class, (String)"SpecialItemGrate", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityGolemBobber.class, (String)"GolemBobber", (int)id++, (Object)Thaumcraft.instance, (int)64, (int)64, (boolean)false);
    }

    public static void initEntitySpawns() {
        ArrayList biomes = WorldChunkManager.allowedBiomes;
        BiomeGenBase[] allBiomes = biomes.toArray(new BiomeGenBase[]{null});
        if (Config.spawnAngryZombie) {
            for (BiomeGenBase bgb : biomes) {
                if (!(bgb.func_76747_a(EnumCreatureType.monster) != null & bgb.func_76747_a(EnumCreatureType.monster).size() > 0)) continue;
                EntityRegistry.addSpawn(EntityBrainyZombie.class, (int)10, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{bgb});
            }
        }
        if (Config.spawnPech) {
            for (BiomeGenBase bgb : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL)) {
                if (!(bgb.func_76747_a(EnumCreatureType.monster) != null & bgb.func_76747_a(EnumCreatureType.monster).size() > 0)) continue;
                EntityRegistry.addSpawn(EntityPech.class, (int)10, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{bgb});
            }
        }
        if (Config.spawnFireBat) {
            EntityRegistry.addSpawn(EntityFireBat.class, (int)10, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.NETHER));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
                EntityRegistry.addSpawn(EntityFireBat.class, (int)5, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])biomes.toArray(allBiomes));
            }
        }
        if (Config.spawnWisp) {
            EntityRegistry.addSpawn(EntityWisp.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.NETHER));
        }
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Zombie:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Spider:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Blaze:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Enderman:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Skeleton:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Witch:1");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Thaumcraft.EldritchCrab:0");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Thaumcraft.Taintacle:2");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Thaumcraft.Wisp:1");
        FMLInterModComms.sendMessage((String)"Thaumcraft", (String)"championWhiteList", (String)"Thaumcraft.InhabitedZombie:3");
        championModWhitelist.put(EntityCultist.class, 1);
        championModWhitelist.put(EntityWatcher.class, 2);
        championModWhitelist.put(EntityPech.class, 1);
        championModWhitelist.put(EntityThaumcraftBoss.class, 200);
    }
}

