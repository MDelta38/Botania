/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.Coord;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class BlockActionSphere {
    protected abstract void onBlock(World var1, int var2, int var3, int var4);

    protected void onComplete() {
    }

    public void drawHollowSphere(World world, int x0, int y0, int z0, int radius) {
        if (radius == 1) {
            this.drawPixel(world, x0, z0, y0);
        } else {
            int x = --radius;
            int radiusError = 1 - x;
            for (int y = 0; x >= y; ++y) {
                this.drawCircle(world, x0, y0, z0, y, x, radiusError);
                if (radiusError < 0) {
                    radiusError += 2 * y + 1;
                    continue;
                }
                radiusError += 2 * (y - --x + 1);
            }
        }
        this.onComplete();
    }

    private boolean drawCircle(World world, int x0, int y0, int z0, int y1, int radius, int error0) {
        int x = radius;
        int radiusError = error0;
        for (int z = 0; x >= z; ++z) {
            this.drawPixel(world, x0 + x, z0 + z, y0 + y1);
            this.drawPixel(world, x0 - x, z0 + z, y0 + y1);
            this.drawPixel(world, x0 + x, z0 + z, y0 - y1);
            this.drawPixel(world, x0 - x, z0 + z, y0 - y1);
            this.drawPixel(world, x0 + x, z0 - z, y0 + y1);
            this.drawPixel(world, x0 - x, z0 - z, y0 + y1);
            this.drawPixel(world, x0 + x, z0 - z, y0 - y1);
            this.drawPixel(world, x0 - x, z0 - z, y0 - y1);
            this.drawPixel(world, x0 + z, z0 + x, y0 + y1);
            this.drawPixel(world, x0 - z, z0 + x, y0 + y1);
            this.drawPixel(world, x0 + z, z0 + x, y0 - y1);
            this.drawPixel(world, x0 - z, z0 + x, y0 - y1);
            this.drawPixel(world, x0 + z, z0 - x, y0 + y1);
            this.drawPixel(world, x0 - z, z0 - x, y0 + y1);
            this.drawPixel(world, x0 + z, z0 - x, y0 - y1);
            this.drawPixel(world, x0 - z, z0 - x, y0 - y1);
            this.drawPixel(world, x0 + y1, z0 + z, y0 + x);
            this.drawPixel(world, x0 - y1, z0 + z, y0 + x);
            this.drawPixel(world, x0 + y1, z0 + z, y0 - x);
            this.drawPixel(world, x0 - y1, z0 + z, y0 - x);
            this.drawPixel(world, x0 + y1, z0 - z, y0 + x);
            this.drawPixel(world, x0 - y1, z0 - z, y0 + x);
            this.drawPixel(world, x0 + y1, z0 - z, y0 - x);
            this.drawPixel(world, x0 - y1, z0 - z, y0 - x);
            this.drawPixel(world, x0 + z, z0 + y1, y0 + x);
            this.drawPixel(world, x0 - z, z0 + y1, y0 + x);
            this.drawPixel(world, x0 + z, z0 + y1, y0 - x);
            this.drawPixel(world, x0 - z, z0 + y1, y0 - x);
            this.drawPixel(world, x0 + z, z0 - y1, y0 + x);
            this.drawPixel(world, x0 - z, z0 - y1, y0 + x);
            this.drawPixel(world, x0 + z, z0 - y1, y0 - x);
            this.drawPixel(world, x0 - z, z0 - y1, y0 - x);
            if (radiusError < 0) {
                radiusError += 2 * z + 1;
                continue;
            }
            radiusError += 2 * (z - --x + 1);
        }
        return true;
    }

    public void drawFilledSphere(World world, int x0, int y0, int z0, int radius) {
        if (radius == 1) {
            this.drawPixel(world, x0, z0, y0);
        } else {
            int radiusSq = radius * radius;
            for (int x = x0 - radius; x <= x0 + radius; ++x) {
                for (int z = z0 - radius; z <= z0 + radius; ++z) {
                    for (int y = y0 - radius; y <= y0 + radius; ++y) {
                        if (!(Coord.distanceSq(x, y, z, x0, y0, z0) < (double)(radiusSq - 1))) continue;
                        this.drawPixel(world, x, z, y);
                    }
                }
            }
        }
        this.onComplete();
    }

    private void drawPixel(World world, int x, int z, int y) {
        this.onBlock(world, x, y, z);
    }

    protected void fillWith(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
        this.fillHalfWithAirY(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
        this.fillHalfWithAirY(world, posX, posY - 1, posZ, -1, radius, fillBlock, edgeBlock);
    }

    private void fillHalfWithAirY(World world, int posX, int posY, int posZ, int dy, int radius, Block fillBlock, Block edgeBlock) {
        int realY;
        for (int y = 0; y <= radius && world.func_147439_a(posX, realY = posY + y * dy, posZ) != edgeBlock; ++y) {
            this.fillSliceWithAir(world, posX, realY, posZ, radius, fillBlock, edgeBlock);
        }
    }

    private void fillSliceWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
        this.fillHalfWithAirX(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
        this.fillHalfWithAirX(world, posX - 1, posY, posZ, -1, radius, fillBlock, edgeBlock);
    }

    private void fillHalfWithAirX(World world, int posX, int posY, int posZ, int dx, int radius, Block fillBlock, Block edgeBlock) {
        int realX;
        for (int x = 0; x <= radius && world.func_147439_a(realX = posX + x * dx, x, posZ) != edgeBlock; ++x) {
            this.fillLineWithAir(world, realX, posY, posZ, radius, fillBlock, edgeBlock);
        }
    }

    private void fillLineWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
        this.fillHalfWithAirZ(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
        this.fillHalfWithAirZ(world, posX, posY, posZ - 1, -1, radius, fillBlock, edgeBlock);
    }

    private void fillHalfWithAirZ(World world, int posX, int posY, int posZ, int dz, int radius, Block fillBlock, Block edgeBlock) {
        int realZ;
        Block foundBlock;
        for (int z = 0; z <= radius && (foundBlock = world.func_147439_a(posX, posY, realZ = posZ + z * dz)) != edgeBlock; ++z) {
            if (foundBlock == fillBlock || foundBlock != Blocks.field_150355_j && foundBlock != Blocks.field_150358_i && foundBlock != Witchery.Blocks.BREW_GAS && foundBlock != Witchery.Blocks.BREW_LIQUID) continue;
            world.func_147449_b(posX, posY, realZ, fillBlock);
        }
    }
}

