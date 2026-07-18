/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.entity.Entity;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileSolegnolia
extends SubTileFunctional {
    private static final double RANGE = 5.0;
    private static final double RANGE_MINI = 1.0;
    public static Set<SubTileSolegnolia> existingFlowers = Collections.newSetFromMap(new WeakHashMap());
    private static boolean registered = false;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!existingFlowers.contains(this)) {
            existingFlowers.add(this);
            if (!registered) {
                registered = true;
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    public static boolean hasSolegnoliaAround(Entity e) {
        for (SubTileSolegnolia flower : existingFlowers) {
            if (flower.redstoneSignal > 0 || flower.supertile.func_145831_w() != e.field_70170_p || flower.supertile.func_145831_w().func_147438_o(flower.supertile.field_145851_c, flower.supertile.field_145848_d, flower.supertile.field_145849_e) != flower.supertile) continue;
            double range = flower.getRange();
            if (!((double)MathHelper.pointDistanceSpace(e.field_70165_t, e.field_70163_u, e.field_70161_v, (double)flower.supertile.field_145851_c + 0.5, (double)flower.supertile.field_145848_d + 0.5, (double)flower.supertile.field_145849_e + 0.5) <= range)) continue;
            return true;
        }
        return false;
    }

    @Override
    public int getMaxMana() {
        return 1;
    }

    @Override
    public int getColor() {
        return 13212749;
    }

    public double getRange() {
        return 5.0;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Circle(this.toChunkCoordinates(), this.getRange());
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.solegnolia;
    }

    public static class Mini
    extends SubTileSolegnolia {
        @Override
        public double getRange() {
            return 1.0;
        }
    }
}

