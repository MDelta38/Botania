/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package com.kentington.thaumichorizons.common.entities.ai;

import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGeneratorTH {
    private static Vec3 staticVector = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);
    private static final String __OBFID = "CL_00001629";

    public static Vec3 findRandomTarget(EntityLiving p_75463_0_, int p_75463_1_, int p_75463_2_) {
        return RandomPositionGeneratorTH.findRandomTargetBlock(p_75463_0_, p_75463_1_, p_75463_2_, null);
    }

    public static Vec3 findRandomTargetBlockTowards(EntityLiving p_75464_0_, int p_75464_1_, int p_75464_2_, Vec3 p_75464_3_) {
        RandomPositionGeneratorTH.staticVector.field_72450_a = p_75464_3_.field_72450_a - p_75464_0_.field_70165_t;
        RandomPositionGeneratorTH.staticVector.field_72448_b = p_75464_3_.field_72448_b - p_75464_0_.field_70163_u;
        RandomPositionGeneratorTH.staticVector.field_72449_c = p_75464_3_.field_72449_c - p_75464_0_.field_70161_v;
        return RandomPositionGeneratorTH.findRandomTargetBlock(p_75464_0_, p_75464_1_, p_75464_2_, staticVector);
    }

    public static Vec3 findRandomTargetBlockAwayFrom(EntityLiving p_75461_0_, int p_75461_1_, int p_75461_2_, Vec3 p_75461_3_) {
        RandomPositionGeneratorTH.staticVector.field_72450_a = p_75461_0_.field_70165_t - p_75461_3_.field_72450_a;
        RandomPositionGeneratorTH.staticVector.field_72448_b = p_75461_0_.field_70163_u - p_75461_3_.field_72448_b;
        RandomPositionGeneratorTH.staticVector.field_72449_c = p_75461_0_.field_70161_v - p_75461_3_.field_72449_c;
        return RandomPositionGeneratorTH.findRandomTargetBlock(p_75461_0_, p_75461_1_, p_75461_2_, staticVector);
    }

    private static Vec3 findRandomTargetBlock(EntityLiving p_75462_0_, int p_75462_1_, int p_75462_2_, Vec3 p_75462_3_) {
        Random random = p_75462_0_.func_70681_au();
        boolean flag = false;
        int k = 0;
        int l = 0;
        int i1 = 0;
        float f = -99999.0f;
        boolean flag1 = false;
        for (int l1 = 0; l1 < 10; ++l1) {
            int j1 = random.nextInt(2 * p_75462_1_) - p_75462_1_;
            int i2 = random.nextInt(2 * p_75462_2_) - p_75462_2_;
            int k1 = random.nextInt(2 * p_75462_1_) - p_75462_1_;
            if (p_75462_3_ != null && !((double)j1 * p_75462_3_.field_72450_a + (double)k1 * p_75462_3_.field_72449_c >= 0.0)) continue;
            j1 += MathHelper.func_76128_c((double)p_75462_0_.field_70165_t);
            i2 += MathHelper.func_76128_c((double)p_75462_0_.field_70163_u);
            k1 += MathHelper.func_76128_c((double)p_75462_0_.field_70161_v);
            if (flag1) continue;
            k = j1;
            l = i2;
            i1 = k1;
            flag = true;
        }
        if (flag) {
            return Vec3.func_72443_a((double)k, (double)l, (double)i1);
        }
        return null;
    }
}

