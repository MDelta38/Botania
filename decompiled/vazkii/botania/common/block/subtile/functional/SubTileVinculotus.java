/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileVinculotus
extends SubTileFunctional {
    public static Set<SubTileVinculotus> existingFlowers = Collections.newSetFromMap(new WeakHashMap());
    private static boolean registered = false;
    private static final int RANGE = 64;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!existingFlowers.contains(this)) {
            existingFlowers.add(this);
            if (!registered) {
                MinecraftForge.EVENT_BUS.register((Object)new EndermanIntercepter());
                registered = true;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Circle(this.toChunkCoordinates(), 64.0);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 680017;
    }

    @Override
    public int getMaxMana() {
        return 500;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.vinculotus;
    }

    public static class EndermanIntercepter {
        @SubscribeEvent
        public void onEndermanTeleport(EnderTeleportEvent event) {
            if (event.entity.field_70170_p.field_72995_K) {
                return;
            }
            int cost = 50;
            if (event.entity instanceof EntityEnderman) {
                ArrayList<SubTileVinculotus> possibleFlowers = new ArrayList<SubTileVinculotus>();
                for (SubTileVinculotus flower : existingFlowers) {
                    double z;
                    double y;
                    double x;
                    if (flower.redstoneSignal > 0 || flower.mana <= cost || flower.supertile.func_145831_w() != event.entity.field_70170_p || flower.supertile.func_145831_w().func_147438_o(((SubTileVinculotus)flower).supertile.field_145851_c, ((SubTileVinculotus)flower).supertile.field_145848_d, ((SubTileVinculotus)flower).supertile.field_145849_e) != flower.supertile || !(MathHelper.pointDistanceSpace(x = (double)((SubTileVinculotus)flower).supertile.field_145851_c + 0.5, y = (double)((SubTileVinculotus)flower).supertile.field_145848_d + 1.5, z = (double)((SubTileVinculotus)flower).supertile.field_145849_e + 0.5, event.targetX, event.targetY, event.targetZ) < 64.0f)) continue;
                    possibleFlowers.add(flower);
                }
                if (!possibleFlowers.isEmpty()) {
                    SubTileVinculotus flower = (SubTileVinculotus)possibleFlowers.get(event.entity.field_70170_p.field_73012_v.nextInt(possibleFlowers.size()));
                    double x = (double)((SubTileVinculotus)flower).supertile.field_145851_c + 0.5;
                    double y = (double)((SubTileVinculotus)flower).supertile.field_145848_d + 1.5;
                    double z = (double)((SubTileVinculotus)flower).supertile.field_145849_e + 0.5;
                    event.targetX = x + Math.random() * 3.0 - 1.0;
                    event.targetY = y;
                    event.targetZ = z + Math.random() * 3.0 - 1.0;
                    flower.mana -= cost;
                    flower.sync();
                }
            }
        }
    }
}

