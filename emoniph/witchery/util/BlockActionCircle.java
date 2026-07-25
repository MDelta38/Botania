/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import net.minecraft.world.World;

public abstract class BlockActionCircle {
    public abstract void onBlock(World var1, int var2, int var3, int var4);

    public void onComplete() {
    }

    public void processHollowCircle(World world, int x0, int y0, int z0, int radius) {
        if (radius == 1) {
            this.drawPixel(world, x0, z0, y0);
        } else {
            int x = --radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawPixel(world, x + x0, z + z0, y0);
                this.drawPixel(world, z + x0, x + z0, y0);
                this.drawPixel(world, -x + x0, z + z0, y0);
                this.drawPixel(world, -z + x0, x + z0, y0);
                this.drawPixel(world, -x + x0, -z + z0, y0);
                this.drawPixel(world, -z + x0, -x + z0, y0);
                this.drawPixel(world, x + x0, -z + z0, y0);
                this.drawPixel(world, z + x0, -x + z0, y0);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }
        this.onComplete();
    }

    public void processFilledCircle(World world, int x0, int y0, int z0, int radius) {
        if (radius == 1) {
            this.drawPixel(world, x0, z0, y0);
        } else {
            int x = --radius;
            int radiusError = 1 - x;
            boolean obsidianMelted = false;
            for (int z = 0; x >= z; ++z) {
                this.drawLine(world, -x + x0, x + x0, z + z0, y0);
                this.drawLine(world, -z + x0, z + x0, x + z0, y0);
                this.drawLine(world, -x + x0, x + x0, -z + z0, y0);
                this.drawLine(world, -z + x0, z + x0, -x + z0, y0);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }
        this.onComplete();
    }

    private void drawLine(World world, int x1, int x2, int z, int y) {
        for (int x = x1; x <= x2; ++x) {
            this.drawPixel(world, x, z, y);
        }
    }

    private void drawPixel(World world, int x, int z, int y) {
        this.onBlock(world, x, y, z);
    }
}

