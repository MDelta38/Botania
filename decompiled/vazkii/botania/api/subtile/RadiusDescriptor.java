/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.api.subtile;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

public class RadiusDescriptor {
    final ChunkCoordinates subtileCoords;

    public RadiusDescriptor(ChunkCoordinates subtileCoords) {
        this.subtileCoords = subtileCoords;
    }

    public ChunkCoordinates getSubtileCoords() {
        return this.subtileCoords;
    }

    public boolean isCircle() {
        return false;
    }

    public double getCircleRadius() {
        return 0.0;
    }

    public AxisAlignedBB getAABB() {
        return null;
    }

    public static class Square
    extends Rectangle {
        public Square(ChunkCoordinates subtileCoords, int expand) {
            super(subtileCoords, AxisAlignedBB.func_72330_a((double)(subtileCoords.field_71574_a - expand), (double)subtileCoords.field_71572_b, (double)(subtileCoords.field_71573_c - expand), (double)(subtileCoords.field_71574_a + 1 + expand), (double)subtileCoords.field_71572_b, (double)(subtileCoords.field_71573_c + 1 + expand)));
        }
    }

    public static class Rectangle
    extends RadiusDescriptor {
        final AxisAlignedBB aabb;

        public Rectangle(ChunkCoordinates subtileCoords, AxisAlignedBB aabb) {
            super(subtileCoords);
            this.aabb = aabb;
        }

        @Override
        public AxisAlignedBB getAABB() {
            return this.aabb;
        }
    }

    public static class Circle
    extends RadiusDescriptor {
        final double radius;

        public Circle(ChunkCoordinates subtileCoords, double radius) {
            super(subtileCoords);
            this.radius = radius;
        }

        @Override
        public boolean isCircle() {
            return true;
        }

        @Override
        public double getCircleRadius() {
            return this.radius;
        }
    }
}

