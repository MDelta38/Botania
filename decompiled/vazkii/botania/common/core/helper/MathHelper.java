/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package vazkii.botania.common.core.helper;

import net.minecraft.entity.Entity;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.core.helper.Vector3;

public final class MathHelper {
    private static final String[] ORDINAL_SUFFIXES = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};

    public static float pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (float)Math.sqrt(Math.pow(x1 - x2, 2.0) + Math.pow(y1 - y2, 2.0) + Math.pow(z1 - z2, 2.0));
    }

    public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
        return VanillaPacketDispatcher.pointDistancePlane(x1, y1, x2, y2);
    }

    public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
        Vector3 entityVector = Vector3.fromEntityCenter(entity);
        Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
        if (finalVector.mag() > 1.0) {
            finalVector.normalize();
        }
        entity.field_70159_w = finalVector.x * (double)modifier;
        entity.field_70181_x = finalVector.y * (double)modifier;
        entity.field_70179_y = finalVector.z * (double)modifier;
    }

    public static String numberToOrdinal(int i) {
        return i % 100 == 11 || i % 100 == 12 || i % 100 == 13 ? i + "th" : i + ORDINAL_SUFFIXES[i % 10];
    }
}

