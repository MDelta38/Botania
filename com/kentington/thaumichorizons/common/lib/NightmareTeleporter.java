/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 */
package com.kentington.thaumichorizons.common.lib;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class NightmareTeleporter
extends Teleporter {
    int dim;
    private WorldServer worldServerInstance;
    private Random random;

    public NightmareTeleporter(WorldServer p_i1963_1_) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.random = new Random(p_i1963_1_.func_72905_C());
    }

    public void func_77185_a(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
        this.moveToHole(p_77185_1_);
    }

    public void moveToHole(Entity p_85188_1_) {
        double d4;
        double d3;
        int i5;
        int l4;
        int k4;
        int j4;
        int i4;
        int l3;
        int k3;
        int j3;
        int i3;
        double d2;
        int k2;
        double d1;
        int i2;
        int b0 = 16;
        double d0 = -1.0;
        int i = MathHelper.func_76128_c((double)p_85188_1_.field_70165_t);
        int j = MathHelper.func_76128_c((double)p_85188_1_.field_70163_u);
        int k = MathHelper.func_76128_c((double)p_85188_1_.field_70161_v);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        for (i2 = i - b0; i2 <= i + b0; ++i2) {
            d1 = (double)i2 + 0.5 - p_85188_1_.field_70165_t;
            for (k2 = k - b0; k2 <= k + b0; ++k2) {
                d2 = (double)k2 + 0.5 - p_85188_1_.field_70161_v;
                block2: for (i3 = this.worldServerInstance.func_72940_L() - 1; i3 >= 0; --i3) {
                    if (!this.worldServerInstance.func_147437_c(i2, i3, k2)) continue;
                    while (i3 > 0 && this.worldServerInstance.func_147437_c(i2, i3 - 1, k2)) {
                        --i3;
                    }
                    for (j3 = l1; j3 < l1 + 4; ++j3) {
                        k3 = j3 % 2;
                        l3 = 1 - k3;
                        if (j3 % 4 >= 2) {
                            k3 = -k3;
                            l3 = -l3;
                        }
                        for (i4 = 0; i4 < 3; ++i4) {
                            for (j4 = 0; j4 < 4; ++j4) {
                                for (k4 = -1; k4 < 4; ++k4) {
                                    l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                                    i5 = i3 + k4;
                                    int j5 = k2 + (j4 - 1) * l3 - i4 * k3;
                                    if (k4 < 0 && !this.worldServerInstance.func_147439_a(l4, i5, j5).func_149688_o().func_76220_a() || k4 >= 0 && !this.worldServerInstance.func_147437_c(l4, i5, j5)) continue block2;
                                }
                            }
                        }
                        d3 = (double)i3 + 0.5 - p_85188_1_.field_70163_u;
                        d4 = d1 * d1 + d3 * d3 + d2 * d2;
                        if (!(d0 < 0.0) && !(d4 < d0)) continue;
                        d0 = d4;
                        l = i2;
                        i1 = i3;
                        j1 = k2;
                        k1 = j3 % 4;
                    }
                }
            }
        }
        if (d0 < 0.0) {
            for (i2 = i - b0; i2 <= i + b0; ++i2) {
                d1 = (double)i2 + 0.5 - p_85188_1_.field_70165_t;
                for (k2 = k - b0; k2 <= k + b0; ++k2) {
                    d2 = (double)k2 + 0.5 - p_85188_1_.field_70161_v;
                    block10: for (i3 = this.worldServerInstance.func_72940_L() - 1; i3 >= 0; --i3) {
                        if (!this.worldServerInstance.func_147437_c(i2, i3, k2)) continue;
                        while (i3 > 0 && this.worldServerInstance.func_147437_c(i2, i3 - 1, k2)) {
                            --i3;
                        }
                        for (j3 = l1; j3 < l1 + 2; ++j3) {
                            k3 = j3 % 2;
                            l3 = 1 - k3;
                            for (i4 = 0; i4 < 4; ++i4) {
                                for (j4 = -1; j4 < 4; ++j4) {
                                    k4 = i2 + (i4 - 1) * k3;
                                    l4 = i3 + j4;
                                    i5 = k2 + (i4 - 1) * l3;
                                    if (j4 < 0 && !this.worldServerInstance.func_147439_a(k4, l4, i5).func_149688_o().func_76220_a() || j4 >= 0 && !this.worldServerInstance.func_147437_c(k4, l4, i5)) continue block10;
                                }
                            }
                            d3 = (double)i3 + 0.5 - p_85188_1_.field_70163_u;
                            d4 = d1 * d1 + d3 * d3 + d2 * d2;
                            if (!(d0 < 0.0) && !(d4 < d0)) continue;
                            d0 = d4;
                            l = i2;
                            i1 = i3;
                            j1 = k2;
                            k1 = j3 % 2;
                        }
                    }
                }
            }
        }
        int k5 = l;
        int j2 = i1;
        k2 = j1;
        int l5 = k1 % 2;
        int l2 = 1 - l5;
        if (k1 % 4 >= 2) {
            l5 = -l5;
            l2 = -l2;
        }
        p_85188_1_.func_70107_b((double)k5, (double)j2, (double)k2);
    }
}

