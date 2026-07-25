/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMercurialSlime
extends EntitySlime {
    public EntityMercurialSlime(World p_i1742_1_) {
        super(p_i1742_1_);
    }

    protected String func_70801_i() {
        return "snowshovel";
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    public void func_70106_y() {
        int i = this.func_70809_q();
        if (!this.field_70170_p.field_72995_K && i > 1 && this.func_110143_aJ() <= 0.0f) {
            int j = 2 + this.field_70146_Z.nextInt(3);
            for (int k = 0; k < j; ++k) {
                float f = ((float)(k % 2) - 0.5f) * (float)i / 4.0f;
                float f1 = ((float)(k / 2) - 0.5f) * (float)i / 4.0f;
                EntityMercurialSlime entityslime = this.createInstance();
                entityslime.func_70799_a(i / 2);
                entityslime.func_70012_b(this.field_70165_t + (double)f, this.field_70163_u + 0.5, this.field_70161_v + (double)f1, this.field_70146_Z.nextFloat() * 360.0f, 0.0f);
                this.field_70170_p.func_72838_d((Entity)entityslime);
            }
        }
        super.func_70106_y();
    }

    protected EntityMercurialSlime createInstance() {
        return new EntityMercurialSlime(this.field_70170_p);
    }
}

