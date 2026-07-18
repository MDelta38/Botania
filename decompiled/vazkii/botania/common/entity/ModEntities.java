/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 */
package vazkii.botania.common.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.entity.EntityFallingStar;
import vazkii.botania.common.entity.EntityFlameRing;
import vazkii.botania.common.entity.EntityMagicLandmine;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.entity.EntityManaStorm;
import vazkii.botania.common.entity.EntityPinkWither;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.entity.EntityPoolMinecart;
import vazkii.botania.common.entity.EntitySignalFlare;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.entity.EntityThrownItem;
import vazkii.botania.common.entity.EntityVineBall;

public final class ModEntities {
    public static void init() {
        int id = 0;
        EntityRegistry.registerModEntity(EntityManaBurst.class, (String)"botania:manaBurst", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntitySignalFlare.class, (String)"botania:signalFlare", (int)id++, (Object)Botania.instance, (int)2048, (int)10, (boolean)false);
        EntityRegistry.registerModEntity(EntityPixie.class, (String)"botania:pixie", (int)id++, (Object)Botania.instance, (int)16, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityFlameRing.class, (String)"botania:flameRing", (int)id++, (Object)Botania.instance, (int)32, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityVineBall.class, (String)"botania:vineBall", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityDoppleganger.class, (String)"botania:doppleganger", (int)id++, (Object)Botania.instance, (int)128, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicLandmine.class, (String)"botania:magicLandmine", (int)id++, (Object)Botania.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntitySpark.class, (String)"botania:spark", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)false);
        EntityRegistry.registerModEntity(EntityThrownItem.class, (String)"botania:thrownItem", (int)id++, (Object)Botania.instance, (int)64, (int)20, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicMissile.class, (String)"botania:magicMissile", (int)id++, (Object)Botania.instance, (int)64, (int)2, (boolean)true);
        EntityRegistry.registerModEntity(EntityThornChakram.class, (String)"botania:thornChakram", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityCorporeaSpark.class, (String)"botania:corporeaSpark", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)false);
        EntityRegistry.registerModEntity(EntityEnderAirBottle.class, (String)"botania:enderAirBottle", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityPoolMinecart.class, (String)"botania:poolMinecart", (int)id++, (Object)Botania.instance, (int)80, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityPinkWither.class, (String)"botania:pinkWither", (int)id++, (Object)Botania.instance, (int)80, (int)3, (boolean)false);
        EntityRegistry.registerModEntity(TileLightRelay.EntityPlayerMover.class, (String)"botania:playerMover", (int)id++, (Object)Botania.instance, (int)40, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityManaStorm.class, (String)"botania:manaStorm", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)false);
        EntityRegistry.registerModEntity(EntityBabylonWeapon.class, (String)"botania:babylonWeapon", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityFallingStar.class, (String)"botania:fallingStar", (int)id++, (Object)Botania.instance, (int)64, (int)10, (boolean)true);
    }
}

