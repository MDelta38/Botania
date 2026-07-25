/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityList$EntityEggInfo
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.emoniph.witchery;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.EntityDroplet;
import com.emoniph.witchery.brewing.EntitySplatter;
import com.emoniph.witchery.entity.EntityAttackBat;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDarkMark;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDeathsHorse;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityEye;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.entity.EntityHellhound;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityItemWaystone;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntityMandrake;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityMirrorFace;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityParasyticLouse;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntitySoulfire;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityVampire;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.entity.EntityWitchCat;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class WitcheryEntities {
    private final ArrayList<EntityRef> entities = new ArrayList();
    public final EntityRef DEMON = new LivingRef(92, (Class<? extends EntityLiving>)EntityDemon.class, "demon", this.entities).setEgg(0x910000, 11430927);
    public final EntityRef BROOM = new EntityRef(93, EntityBroom.class, "broom", this.entities);
    public final EntityRef BREW = new EntityRef(94, EntityWitchProjectile.class, "brew", 64, 3, this.entities);
    public final EntityRef SPECTRAL_FAMILIAR = new LivingRef(95, (Class<? extends EntityLiving>)EntityFamiliar.class, "familiar", this.entities);
    public final EntityRef MANDRAKE = new LivingRef(96, (Class<? extends EntityLiving>)EntityMandrake.class, "mandrake", this.entities).setEgg(128271104, 311408);
    public final EntityRef TREEFYD = new LivingRef(97, (Class<? extends EntityLiving>)EntityTreefyd.class, "treefyd", this.entities).setEgg(5781801, 11217964);
    public final EntityRef HUNTSMAN = new LivingRef(98, (Class<? extends EntityLiving>)EntityHornedHuntsman.class, "hornedHuntsman", this.entities).setEgg(11523, 4007964);
    public final EntityRef SPELL = new EntityRef(99, EntitySpellEffect.class, "spellEffect", 64, 3, this.entities);
    public final EntityRef ENT = new LivingRef(100, (Class<? extends EntityLiving>)EntityEnt.class, "ent", this.entities).setEgg(0x517755, 5724240);
    public final EntityRef ILLUSION_CREEPER = new LivingRef(101, (Class<? extends EntityLiving>)EntityIllusionCreeper.class, "illusionCreeper", this.entities);
    public final EntityRef ILLUSION_SPIDER = new LivingRef(102, (Class<? extends EntityLiving>)EntityIllusionSpider.class, "illusionSpider", this.entities);
    public final EntityRef ILLUSION_ZOMBIE = new LivingRef(103, (Class<? extends EntityLiving>)EntityIllusionZombie.class, "illusionZombie", this.entities);
    public final EntityRef OWL = new LivingRef(104, (Class<? extends EntityLiving>)EntityOwl.class, "owl", this.entities).setEgg(0xE2E2E2, 6049609);
    public final EntityRef TOAD = new LivingRef(105, (Class<? extends EntityLiving>)EntityToad.class, "toad", this.entities).setEgg(5780254, 3090974);
    public final EntityRef CAT_FAMILIAR = new LivingRef(106, (Class<? extends EntityLiving>)EntityWitchCat.class, "cat", this.entities);
    public final EntityRef LOUSE = new LivingRef(107, (Class<? extends EntityLiving>)EntityParasyticLouse.class, "louse", this.entities);
    public final EntityRef EYE = new LivingRef(108, (Class<? extends EntityLiving>)EntityEye.class, "eye", 150, 1, this.entities);
    public final EntityRef BABA_YAGA = new LivingRef(109, (Class<? extends EntityLiving>)EntityBabaYaga.class, "babayaga", this.entities).setEgg(7232598, 0x3B3B3B);
    public final EntityRef COVEN_WITCH = new LivingRef(110, (Class<? extends EntityLiving>)EntityCovenWitch.class, "covenwitch", this.entities).addSpawn(2, 1, 1, EnumCreatureType.creature, BiomeGenBase.field_76780_h).addSpawn(1, 1, 1, EnumCreatureType.creature, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.FOREST)).setEgg(0x111111, 11523);
    public final EntityRef PLAYER_CORPSE = new LivingRef(111, (Class<? extends EntityLiving>)EntityCorpse.class, "corpse", this.entities);
    public final EntityRef NIGHTMARE = new LivingRef(112, (Class<? extends EntityLiving>)EntityNightmare.class, "nightmare", this.entities).setEgg(983101, 0);
    public final EntityRef SPECTRE = new LivingRef(113, (Class<? extends EntityLiving>)EntitySpectre.class, "spectre", this.entities).setEgg(0x101010, 16299031);
    public final EntityRef POLTERGEIST = new LivingRef(114, (Class<? extends EntityLiving>)EntityPoltergeist.class, "poltergeist", this.entities).setEgg(12844917, 12844917);
    public final EntityRef BANSHEE = new LivingRef(115, (Class<? extends EntityLiving>)EntityBanshee.class, "banshee", this.entities).setEgg(13683116, 10136945);
    private static final int MIN_SPIRIT_GROUP = 2;
    private static final int MAX_SPIRIT_GROUP = 5;
    public final EntityRef SPIRIT;
    public final EntityRef DEATH;
    public final EntityRef CROSSBOW_BOLT;
    public final EntityRef WITCH_HUNTER;
    public final EntityRef BINKY_HORSE;
    public final EntityRef LORD_OF_TORMENT;
    public final EntityRef SOULFIRE;
    public final EntityRef IMP;
    public final EntityRef DARK_MARK;
    public final EntityRef MINDRAKE;
    public final EntityRef GOBLIN;
    public final EntityRef GOBLIN_MOG;
    public final EntityRef GOBLIN_GULG;
    public final EntityRef BREW2;
    public final EntityRef ITEM_WAYSTONE;
    public final EntityRef DROPLET;
    public final EntityRef SPLATTER;
    public final EntityRef LEONARD;
    public final EntityRef LOST_SOUL;
    public final EntityRef WOLFMAN;
    public final EntityRef HELLHOUND;
    public final EntityRef WERE_VILLAGER;
    public final EntityRef VILLAGE_GUARD;
    public final EntityRef VAMPIRE_VILLAGER;
    public final EntityRef GRENADE;
    public final EntityRef LILITH;
    public final EntityRef FOLLOWER;
    public final EntityRef WINGED_MONKEY;
    public final EntityRef ATTACK_BAT;
    public final EntityRef MIRROR_FACE;
    public final EntityRef REFLECTION;

    public WitcheryEntities() {
        this.SPIRIT = new LivingRef(116, (Class<? extends EntityLiving>)EntitySpirit.class, "spirit", this.entities).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.MESA)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.FOREST)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.PLAINS)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.MOUNTAIN)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.HILLS)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.SWAMP)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.SANDY)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.SNOWY)).addSpawn(Config.instance().spawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.WASTELAND)).setEgg(16753968, 15649280);
        this.DEATH = new LivingRef(117, (Class<? extends EntityLiving>)EntityDeath.class, "death", this.entities).setEgg(0, 0);
        this.CROSSBOW_BOLT = new EntityRef(118, EntityBolt.class, "bolt", 64, 10, this.entities);
        this.WITCH_HUNTER = new LivingRef(119, (Class<? extends EntityLiving>)EntityWitchHunter.class, "witchhunter", this.entities).setEgg(7893099, 2300953);
        this.BINKY_HORSE = new LivingRef(120, (Class<? extends EntityLiving>)EntityDeathsHorse.class, "deathhorse", this.entities);
        this.LORD_OF_TORMENT = new LivingRef(121, (Class<? extends EntityLiving>)EntityLordOfTorment.class, "lordoftorment", this.entities).setEgg(0x910000, 0x331111);
        this.SOULFIRE = new EntityRef(122, EntitySoulfire.class, "soulfire", 64, 3, this.entities);
        this.IMP = new LivingRef(123, (Class<? extends EntityLiving>)EntityImp.class, "imp", 64, 3, this.entities).setEgg(5776143, 16738816);
        this.DARK_MARK = new LivingRef(124, (Class<? extends EntityLiving>)EntityDarkMark.class, "darkmark", 64, 3, this.entities);
        this.MINDRAKE = new LivingRef(125, (Class<? extends EntityLiving>)EntityMindrake.class, "mindrake", 64, 3, this.entities).setEgg(19463, 4200704);
        this.GOBLIN = new LivingRef(126, (Class<? extends EntityLiving>)EntityGoblin.class, "goblin", 64, 3, this.entities).addSpawn(Math.max(Config.instance().goblinSpawnRate, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.SWAMP)).addSpawn(Math.max(Config.instance().goblinSpawnRate, 1), 1, 3, EnumCreatureType.creature, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.FOREST)).addSpawn(Math.max(Config.instance().goblinSpawnRate - 1, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.PLAINS)).setEgg(10752, 15616);
        this.GOBLIN_MOG = new LivingRef(127, (Class<? extends EntityLiving>)EntityGoblinMog.class, "goblinmog", 64, 3, this.entities).setEgg(10752, 15616);
        this.GOBLIN_GULG = new LivingRef(128, (Class<? extends EntityLiving>)EntityGoblinGulg.class, "goblingulg", 64, 3, this.entities).setEgg(10752, 15616);
        this.BREW2 = new EntityRef(129, EntityBrew.class, "brew2", 64, 1, this.entities);
        this.ITEM_WAYSTONE = new EntityRef(130, EntityItemWaystone.class, "item", 64, 20, this.entities);
        this.DROPLET = new EntityRef(131, EntityDroplet.class, "droplet", 64, 20, this.entities);
        this.SPLATTER = new EntityRef(132, EntitySplatter.class, "splatter", 64, 20, this.entities);
        this.LEONARD = new LivingRef(133, (Class<? extends EntityLiving>)EntityLeonard.class, "leonard", this.entities).setEgg(12152634, 0x2B0000);
        this.LOST_SOUL = new LivingRef(134, (Class<? extends EntityLiving>)EntityLostSoul.class, "lostsoul", this.entities).setEgg(12152634, 2818116);
        this.WOLFMAN = new LivingRef(135, (Class<? extends EntityLiving>)EntityWolfman.class, "wolfman", this.entities).setEgg(0x2D2D2D, 0x606060);
        this.HELLHOUND = new LivingRef(136, (Class<? extends EntityLiving>)EntityHellhound.class, "hellhound", this.entities).addSpawn(Math.max(Config.instance().hellhoundSpawnRate, 1), 1, 3, EnumCreatureType.monster, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.NETHER)).setEgg(14181632, 5968392);
        this.WERE_VILLAGER = new LivingRef(137, (Class<? extends EntityLiving>)EntityVillagerWere.class, "werevillager", this.entities).setEgg(5651507, 12422002);
        this.VILLAGE_GUARD = new LivingRef(138, (Class<? extends EntityLiving>)EntityVillageGuard.class, "villageguard", this.entities).setEgg(0x222222, 5322800);
        this.VAMPIRE_VILLAGER = new LivingRef(139, (Class<? extends EntityLiving>)EntityVampire.class, "vampire", this.entities).setEgg(5322800, 0xCC0000);
        this.GRENADE = new EntityRef(140, EntityGrenade.class, "grenade", 64, 1, this.entities);
        this.LILITH = new LivingRef(141, (Class<? extends EntityLiving>)EntityLilith.class, "lilith", this.entities).setEgg(0, 0x2B0000);
        this.FOLLOWER = new LivingRef(142, (Class<? extends EntityLiving>)EntityFollower.class, "follower", this.entities);
        this.WINGED_MONKEY = new LivingRef(143, (Class<? extends EntityLiving>)EntityWingedMonkey.class, "wingedmonkey", this.entities).setEgg(6846848, 7574709);
        this.ATTACK_BAT = new LivingRef(144, (Class<? extends EntityLiving>)EntityAttackBat.class, "attackbat", this.entities);
        this.MIRROR_FACE = new LivingRef(145, (Class<? extends EntityLiving>)EntityMirrorFace.class, "mirrorface", this.entities);
        this.REFLECTION = new LivingRef(146, (Class<? extends EntityLiving>)EntityReflection.class, "reflection", this.entities).setEgg(0x5566AA, 0x6677FF);
    }

    public List<EntityRef> getEntites() {
        return this.entities;
    }

    public void init() {
    }

    public static class LivingRef
    extends EntityRef {
        public final Class<? extends EntityLiving> living_class;

        public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, ArrayList<EntityRef> registry) {
            super(id, clazz, name, 80, 3, registry);
            this.living_class = clazz;
        }

        public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, int range, int updates, ArrayList<EntityRef> registry) {
            super(id, clazz, name, range, updates, registry);
            this.living_class = clazz;
        }

        public LivingRef addSpawn(int weight, int min, int max, EnumCreatureType type, BiomeGenBase ... biomes) {
            EntityRegistry.addSpawn(this.living_class, (int)weight, (int)min, (int)max, (EnumCreatureType)type, (BiomeGenBase[])biomes);
            return this;
        }
    }

    public static class EntityRef {
        public final Class<? extends Entity> entity_class;
        public final String entity_name;
        public boolean can_capture;
        public boolean can_spawn;
        public boolean can_grind;
        private static int eggRoot = 6395;

        public EntityRef(int id, Class<? extends Entity> clazz, String name, ArrayList<EntityRef> registry) {
            this(id, clazz, name, 80, 3, registry);
        }

        public EntityRef(int id, Class<? extends Entity> clazz, String name, int range, int updates, ArrayList<EntityRef> registry) {
            this.entity_class = clazz;
            this.entity_name = name;
            EntityRegistry.registerModEntity(clazz, (String)name, (int)id, (Object)Witchery.instance, (int)range, (int)updates, (boolean)true);
            registry.add(this);
        }

        public EntityRef setPropsMFR(boolean canCapture, boolean canSpawn, boolean canGrind) {
            this.can_capture = canCapture;
            this.can_spawn = canSpawn;
            this.can_grind = canGrind;
            return this;
        }

        public EntityRef setEgg(int color1, int color2) {
            int eggID = EntityRef.getUniqueEggId();
            EntityList.field_75623_d.put(eggID, this.entity_class);
            EntityList.field_75627_a.put(eggID, new EntityList.EntityEggInfo(eggID, color1, color2));
            return this;
        }

        private static int getUniqueEggId() {
            while (EntityList.func_75617_a((int)(++eggRoot)) != null) {
            }
            return eggRoot;
        }
    }
}

