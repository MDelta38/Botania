/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.TextureCompass
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemEntityLocatorTexture
extends TextureCompass {
    public ItemEntityLocatorTexture() {
        super("witchery:entitylocator");
    }

    public void func_94241_a(World world, double playerX, double playerY, double playerZ, boolean p_94241_8_, boolean p_94241_9_) {
        if (!this.field_110976_a.isEmpty()) {
            double d3 = 0.0;
            if (world != null && !p_94241_8_) {
                ChunkCoordinates chunkcoordinates = world.func_72861_E();
                double d4 = (double)chunkcoordinates.field_71574_a - playerX;
                double d5 = (double)chunkcoordinates.field_71573_c - playerY;
                d3 = -(((playerZ %= 360.0) - 90.0) * Math.PI / 180.0 - Math.atan2(d5, d4));
                if (!world.field_73011_w.func_76569_d()) {
                    d3 = Math.random() * Math.PI * 2.0;
                }
            }
            if (p_94241_9_) {
                this.field_94244_i = d3;
            } else {
                double d6;
                for (d6 = d3 - this.field_94244_i; d6 < -Math.PI; d6 += Math.PI * 2) {
                }
                while (d6 >= Math.PI) {
                    d6 -= Math.PI * 2;
                }
                if (d6 < -1.0) {
                    d6 = -1.0;
                }
                if (d6 > 1.0) {
                    d6 = 1.0;
                }
                this.field_94242_j += d6 * 0.1;
                this.field_94242_j *= 0.8;
                this.field_94244_i += this.field_94242_j;
            }
            int i = (int)((this.field_94244_i / (Math.PI * 2) + 1.0) * (double)this.field_110976_a.size()) % this.field_110976_a.size();
            while (i < 0) {
                i = (i + this.field_110976_a.size()) % this.field_110976_a.size();
            }
            if (i != this.field_110973_g) {
                this.field_110973_g = i;
                TextureUtil.func_147955_a((int[][])((int[][])this.field_110976_a.get(this.field_110973_g)), (int)this.field_130223_c, (int)this.field_130224_d, (int)this.field_110975_c, (int)this.field_110974_d, (boolean)false, (boolean)false);
            }
        }
    }
}

