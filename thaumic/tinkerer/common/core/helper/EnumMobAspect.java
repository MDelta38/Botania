/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCaveSpider
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityMagmaCube
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySilverfish
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.EntitySnowman
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.entities.monster.EntityBrainyZombie
 *  thaumcraft.common.entities.monster.EntityFireBat
 *  thaumcraft.common.entities.monster.EntityWisp
 */
package thaumic.tinkerer.common.core.helper;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockSummon;
import thaumic.tinkerer.common.item.ItemMobAspect;
import thaumic.tinkerer.common.item.ItemMobDisplay;

public enum EnumMobAspect {
    SnowMan(EntitySnowman.class, new Aspect[]{Aspect.WATER, Aspect.WATER, Aspect.MAN}),
    Bat(EntityBat.class, new Aspect[]{Aspect.AIR, Aspect.AIR, Aspect.FLIGHT}, 1.9f, -0.3f),
    Blaze(EntityBlaze.class, new Aspect[]{Aspect.FIRE, Aspect.FIRE, Aspect.FIRE}),
    BrainyZombie(EntityBrainyZombie.class, new Aspect[]{Aspect.MAGIC, Aspect.UNDEAD, Aspect.FLESH}, "Thaumcraft."),
    Firebat(EntityFireBat.class, new Aspect[]{Aspect.FLIGHT, Aspect.FIRE, Aspect.MAGIC}, 1.9f, -0.3f),
    CaveSpider(EntityCaveSpider.class, new Aspect[]{Aspect.BEAST, Aspect.POISON, Aspect.POISON}),
    Chicken(EntityChicken.class, new Aspect[]{Aspect.CROP, Aspect.FLIGHT, Aspect.BEAST}),
    Cow(EntityCow.class, new Aspect[]{Aspect.BEAST, Aspect.EARTH, Aspect.BEAST}),
    Creeper(EntityCreeper.class, new Aspect[]{Aspect.MAGIC, Aspect.BEAST, Aspect.ELDRITCH}),
    Enderman(EntityEnderman.class, new Aspect[]{Aspect.ELDRITCH, Aspect.ELDRITCH, Aspect.MAN}, 0.3f, 0.0f),
    Ghast(EntityGhast.class, new Aspect[]{Aspect.FIRE, Aspect.FLIGHT, Aspect.FLIGHT}, 0.1f, 0.2f),
    EntityHorse(EntityHorse.class, new Aspect[]{Aspect.BEAST, Aspect.BEAST, Aspect.TRAVEL}),
    VillagerGolem(EntityIronGolem.class, new Aspect[]{Aspect.METAL, Aspect.METAL, Aspect.MAN}, 0.3f, 0.0f),
    LavaSlime(EntityMagmaCube.class, new Aspect[]{Aspect.FIRE, Aspect.SLIME, Aspect.SLIME}, 0.6f, 0.0f){

        @Override
        protected Entity createEntity(World worldObj) {
            return EnumMobAspect.setSlimeSize(super.createEntity(worldObj), 1);
        }
    }
    ,
    MushroomCow(EntityMooshroom.class, new Aspect[]{Aspect.BEAST, Aspect.EARTH, Aspect.CROP}),
    Ozelot(EntityOcelot.class, new Aspect[]{Aspect.BEAST, Aspect.EARTH, Aspect.ELDRITCH}),
    Pig(EntityPig.class, new Aspect[]{Aspect.BEAST, Aspect.EARTH, Aspect.TRAVEL}),
    PigZombie(EntityPigZombie.class, new Aspect[]{Aspect.UNDEAD, Aspect.FLESH, Aspect.FIRE}),
    Sheep(EntitySheep.class, new Aspect[]{Aspect.EARTH, Aspect.CLOTH, Aspect.BEAST}),
    Silverfish(EntitySilverfish.class, new Aspect[]{Aspect.METAL, Aspect.METAL, Aspect.EARTH}),
    Skeleton(EntitySkeleton.class, new Aspect[]{Aspect.UNDEAD, Aspect.MAN, Aspect.UNDEAD}),
    Slime(EntitySlime.class, new Aspect[]{Aspect.SLIME, Aspect.SLIME, Aspect.BEAST}, 0.6f, 0.0f){

        @Override
        protected Entity createEntity(World worldObj) {
            return EnumMobAspect.setSlimeSize(super.createEntity(worldObj), 1);
        }
    }
    ,
    Spider(EntitySpider.class, new Aspect[]{Aspect.BEAST, Aspect.UNDEAD, Aspect.UNDEAD}),
    Squid(EntitySquid.class, new Aspect[]{Aspect.WATER, Aspect.WATER, Aspect.WATER}, 0.3f, 0.5f),
    Villager(EntityVillager.class, new Aspect[]{Aspect.MAN, Aspect.MAN, Aspect.MAN}),
    Wisp(EntityWisp.class, new Aspect[]{Aspect.AIR, Aspect.MAGIC, Aspect.MAGIC}, "Thaumcraft."),
    Witch(EntityWitch.class, new Aspect[]{Aspect.MAGIC, Aspect.UNDEAD, Aspect.ELDRITCH}, 0.35f, 0.0f),
    Wolf(EntityWolf.class, new Aspect[]{Aspect.BEAST, Aspect.BEAST, Aspect.BEAST}),
    Zombie(EntityZombie.class, new Aspect[]{Aspect.FLESH, Aspect.FLESH, Aspect.UNDEAD});

    public static Map<EnumMobAspect, Entity> entityCache;
    public Aspect[] aspects;
    public Class entity;
    public String prefix;
    private float scale;
    private float offset;

    private EnumMobAspect(Class entity, Aspect[] aspects, float scale, float offset) {
        this.aspects = aspects;
        this.entity = entity;
        this.scale = scale;
        this.offset = offset;
    }

    private EnumMobAspect(Class entity, Aspect[] aspects, float scale, float offset, String prefix) {
        this(entity, aspects, scale, offset);
        this.prefix = prefix;
    }

    private EnumMobAspect(Class entity, Aspect[] aspects) {
        this.aspects = aspects;
        this.entity = entity;
        this.scale = 1.1f;
        this.offset = 0.0f;
    }

    private EnumMobAspect(Class entity, Aspect[] aspects, String prefix) {
        this(entity, aspects);
        this.prefix = prefix;
    }

    public static Entity getEntityFromCache(EnumMobAspect ent, World worldObj) {
        Entity entity = entityCache.get((Object)ent);
        if (entity == null) {
            entity = ent.createEntity(worldObj);
            entityCache.put(ent, entity);
        }
        return entity;
    }

    private static Entity setSlimeSize(Entity entity, int size) {
        if (entity instanceof EntitySlime) {
            ((EntitySlime)entity).func_70799_a(1);
        }
        return entity;
    }

    public static Aspect[] getAspectsForEntity(Entity e) {
        return EnumMobAspect.getAspectsForEntity(e.getClass());
    }

    public static EnumMobAspect getMobAspectForType(String name) {
        if (name.isEmpty()) {
            return null;
        }
        Class clazz = (Class)EntityList.field_75625_b.get(name);
        for (EnumMobAspect e : EnumMobAspect.values()) {
            if (!clazz.equals(e.entity)) continue;
            return e;
        }
        return null;
    }

    public static Aspect[] getAspectsForEntity(Class clazz) {
        for (EnumMobAspect e : EnumMobAspect.values()) {
            if (!clazz.equals(e.entity)) continue;
            return e.aspects;
        }
        return null;
    }

    public float getVerticalOffset() {
        return this.offset;
    }

    public float getScale() {
        return this.scale;
    }

    public Class getEntityClass() {
        return this.entity;
    }

    public Entity getEntity(World worldObj) {
        return EnumMobAspect.getEntityFromCache(this, worldObj);
    }

    protected Entity createEntity(World worldObj) {
        return EntityList.func_75620_a((String)this.toString(), (World)worldObj);
    }

    public ResearchPage GetRecepiePage() {
        ItemStack output = new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemMobAspect.class));
        ((ItemMobDisplay)output.func_77973_b()).setEntityType(output, this.toString());
        ItemStack[] inputs = new ItemStack[this.aspects.length];
        int i = 0;
        for (Aspect a : this.aspects) {
            inputs[i++] = ItemMobAspect.getStackFromAspect(a);
        }
        InfusionRecipe recepie = new InfusionRecipe("SUMMON", (Object)output, 0, new AspectList(), new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockSummon.class)), inputs);
        return new ResearchPage(recepie);
    }

    public String toString() {
        return this.prefix == null ? super.toString() : this.prefix + super.toString();
    }

    static {
        entityCache = Maps.newHashMap();
    }
}

