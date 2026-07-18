/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package vazkii.botania.api.mana.spark;

import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.mana.spark.ISparkEntity;

public final class SparkHelper {
    public static final int SPARK_SCAN_RANGE = 12;

    public static List<ISparkEntity> getSparksAround(World world, double x, double y, double z) {
        return SparkHelper.getEntitiesAround(ISparkEntity.class, world, x, y, z);
    }

    public static <T> List<T> getEntitiesAround(Class<? extends T> clazz, World world, double x, double y, double z) {
        int r = 12;
        List entities = world.func_72872_a(clazz, AxisAlignedBB.func_72330_a((double)(x - (double)r), (double)(y - (double)r), (double)(z - (double)r), (double)(x + (double)r), (double)(y + (double)r), (double)(z + (double)r)));
        return entities;
    }
}

