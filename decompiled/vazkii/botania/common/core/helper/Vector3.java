/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.vector.Vector3f
 *  org.lwjgl.util.vector.Vector4f
 */
package vazkii.botania.common.core.helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import vazkii.botania.common.core.helper.Quat;

public class Vector3 {
    public static Vector3 zero = new Vector3();
    public static Vector3 one = new Vector3(1.0, 1.0, 1.0);
    public static Vector3 center = new Vector3(0.5, 0.5, 0.5);
    public double x;
    public double y;
    public double z;

    public Vector3() {
    }

    public Vector3(double d, double d1, double d2) {
        this.x = d;
        this.y = d1;
        this.z = d2;
    }

    public Vector3(Vector3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public Vector3(Vec3 vec) {
        this.x = vec.field_72450_a;
        this.y = vec.field_72448_b;
        this.z = vec.field_72449_c;
    }

    public Vector3 copy() {
        return new Vector3(this);
    }

    public static Vector3 fromEntity(Entity e) {
        return new Vector3(e.field_70165_t, e.field_70163_u, e.field_70161_v);
    }

    public static Vector3 fromEntityCenter(Entity e) {
        return new Vector3(e.field_70165_t, e.field_70163_u - (double)e.field_70129_M + (double)(e.field_70131_O / 2.0f), e.field_70161_v);
    }

    public static Vector3 fromTileEntity(TileEntity e) {
        return new Vector3(e.field_145851_c, e.field_145848_d, e.field_145849_e);
    }

    public static Vector3 fromTileEntityCenter(TileEntity e) {
        return new Vector3((double)e.field_145851_c + 0.5, (double)e.field_145848_d + 0.5, (double)e.field_145849_e + 0.5);
    }

    public Vector3 set(double d, double d1, double d2) {
        this.x = d;
        this.y = d1;
        this.z = d2;
        return this;
    }

    public Vector3 set(Vector3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public double dotProduct(Vector3 vec) {
        double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
        if (d > 1.0 && d < 1.00001) {
            d = 1.0;
        } else if (d < -1.0 && d > -1.00001) {
            d = -1.0;
        }
        return d;
    }

    public double dotProduct(double d, double d1, double d2) {
        return d * this.x + d1 * this.y + d2 * this.z;
    }

    public Vector3 crossProduct(Vector3 vec) {
        double d = this.y * vec.z - this.z * vec.y;
        double d1 = this.z * vec.x - this.x * vec.z;
        double d2 = this.x * vec.y - this.y * vec.x;
        this.x = d;
        this.y = d1;
        this.z = d2;
        return this;
    }

    public Vector3 add(double d, double d1, double d2) {
        this.x += d;
        this.y += d1;
        this.z += d2;
        return this;
    }

    public Vector3 add(Vector3 vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    public Vector3 add(double d) {
        return this.add(d, d, d);
    }

    public Vector3 sub(Vector3 vec) {
        return this.subtract(vec);
    }

    public Vector3 subtract(Vector3 vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    public Vector3 negate(Vector3 vec) {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vector3 multiply(double d) {
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }

    public Vector3 multiply(Vector3 f) {
        this.x *= f.x;
        this.y *= f.y;
        this.z *= f.z;
        return this;
    }

    public Vector3 multiply(double fx, double fy, double fz) {
        this.x *= fx;
        this.y *= fy;
        this.z *= fz;
        return this;
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double magSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3 normalize() {
        double d = this.mag();
        if (d != 0.0) {
            this.multiply(1.0 / d);
        }
        return this;
    }

    public String toString() {
        MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
        return "Vector3(" + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
    }

    public Vector3 perpendicular() {
        if (this.z == 0.0) {
            return this.zCrossProduct();
        }
        return this.xCrossProduct();
    }

    public Vector3 xCrossProduct() {
        double d = this.z;
        double d1 = -this.y;
        this.x = 0.0;
        this.y = d;
        this.z = d1;
        return this;
    }

    public Vector3 zCrossProduct() {
        double d = this.y;
        double d1 = -this.x;
        this.x = d;
        this.y = d1;
        this.z = 0.0;
        return this;
    }

    public Vector3 yCrossProduct() {
        double d = -this.z;
        double d1 = this.x;
        this.x = d;
        this.y = 0.0;
        this.z = d1;
        return this;
    }

    public Vec3 toVec3D() {
        return Vec3.func_72443_a((double)this.x, (double)this.y, (double)this.z);
    }

    public double angle(Vector3 vec) {
        return Math.acos(this.copy().normalize().dotProduct(vec.copy().normalize()));
    }

    public boolean isInside(AxisAlignedBB aabb) {
        return this.x >= aabb.field_72340_a && this.y >= aabb.field_72337_e && this.z >= aabb.field_72339_c && this.x < aabb.field_72336_d && this.y < aabb.field_72337_e && this.z < aabb.field_72334_f;
    }

    public boolean isZero() {
        return this.x == 0.0 && this.y == 0.0 && this.z == 0.0;
    }

    public boolean isAxial() {
        return this.x == 0.0 ? this.y == 0.0 || this.z == 0.0 : this.y == 0.0 && this.z == 0.0;
    }

    @SideOnly(value=Side.CLIENT)
    public Vector3f vector3f() {
        return new Vector3f((float)this.x, (float)this.y, (float)this.z);
    }

    @SideOnly(value=Side.CLIENT)
    public Vector4f vector4f() {
        return new Vector4f((float)this.x, (float)this.y, (float)this.z, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void glVertex() {
        GL11.glVertex3d((double)this.x, (double)this.y, (double)this.z);
    }

    public Vector3 negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public double scalarProject(Vector3 b) {
        double l = b.mag();
        return l == 0.0 ? 0.0 : this.dotProduct(b) / l;
    }

    public Vector3 project(Vector3 b) {
        double l = b.magSquared();
        if (l == 0.0) {
            this.set(0.0, 0.0, 0.0);
            return this;
        }
        double m = this.dotProduct(b) / l;
        this.set(b).multiply(m);
        return this;
    }

    public Vector3 rotate(double angle, Vector3 axis) {
        Quat.aroundAxis(axis.copy().normalize(), angle).rotate(this);
        return this;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Vector3)) {
            return false;
        }
        Vector3 v = (Vector3)o;
        return this.x == v.x && this.y == v.y && this.z == v.z;
    }
}

