/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;

public class EntitySeawolf
extends EntityWolf {
    public EntitySeawolf(World p_i1696_1_) {
        super(p_i1696_1_);
        this.func_70661_as().func_75491_a(false);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.func_70090_H()) {
            float bonus = 0.025f;
            this.func_70060_a(0.0f, 1.0f, bonus);
            this.func_70050_g(300);
        }
    }
}

