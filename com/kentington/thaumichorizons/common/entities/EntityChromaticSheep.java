/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;

public class EntityChromaticSheep
extends EntitySheep {
    public EntityChromaticSheep(World p_i1691_1_) {
        super(p_i1691_1_);
    }

    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 30 == 0) {
            int color = this.func_70896_n();
            color = color >= 15 ? 0 : ++color;
            this.func_70891_b(color);
        }
        super.func_70636_d();
    }
}

