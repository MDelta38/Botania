/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package thaumcraft.client.lib;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class QuadHelper {
    public double x;
    public double y;
    public double z;
    public double angle;

    public QuadHelper(double ang, double xx, double yy, double zz) {
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.angle = ang;
    }

    public static QuadHelper setAxis(Vec3 vec, double angle) {
        double d4 = MathHelper.func_76126_a((float)((float)(angle *= 0.5)));
        return new QuadHelper(MathHelper.func_76134_b((float)((float)angle)), vec.field_72450_a * d4, vec.field_72448_b * d4, vec.field_72449_c * d4);
    }

    public void rotate(Vec3 vec) {
        double d = -this.x * vec.field_72450_a - this.y * vec.field_72448_b - this.z * vec.field_72449_c;
        double d1 = this.angle * vec.field_72450_a + this.y * vec.field_72449_c - this.z * vec.field_72448_b;
        double d2 = this.angle * vec.field_72448_b - this.x * vec.field_72449_c + this.z * vec.field_72450_a;
        double d3 = this.angle * vec.field_72449_c + this.x * vec.field_72448_b - this.y * vec.field_72450_a;
        vec.field_72450_a = d1 * this.angle - d * this.x - d2 * this.z + d3 * this.y;
        vec.field_72448_b = d2 * this.angle - d * this.y + d1 * this.z - d3 * this.x;
        vec.field_72449_c = d3 * this.angle - d * this.z - d1 * this.y + d2 * this.x;
    }
}

