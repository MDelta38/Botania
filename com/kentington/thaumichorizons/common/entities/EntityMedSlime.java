/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMedSlime
extends EntitySlime {
    public EntityMedSlime(World p_i1742_1_) {
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
        if (!this.field_70170_p.field_72995_K && i > 1) {
            int j = 2 + this.field_70146_Z.nextInt(3);
            for (int k = 0; k < j; ++k) {
                float f = ((float)(k % 2) - 0.5f) * (float)i / 4.0f;
                float f1 = ((float)(k / 2) - 0.5f) * (float)i / 4.0f;
                EntityMedSlime entityslime = this.createInstance();
                entityslime.func_70799_a(i / 2);
                entityslime.func_70012_b(this.field_70165_t + (double)f, this.field_70163_u + 0.5, this.field_70161_v + (double)f1, this.field_70146_Z.nextFloat() * 360.0f, 0.0f);
                this.field_70170_p.func_72838_d((Entity)entityslime);
            }
        }
        super.func_70106_y();
    }

    protected EntityMedSlime createInstance() {
        return new EntityMedSlime(this.field_70170_p);
    }

    public void func_70100_b_(EntityPlayer p_70100_1_) {
        if (p_70100_1_.func_110143_aJ() < p_70100_1_.func_110138_aP()) {
            int i = this.func_70809_q();
            if (this.func_70685_l((Entity)p_70100_1_) && this.func_70068_e((Entity)p_70100_1_) < 0.6 * (double)i * 0.6 * (double)i) {
                p_70100_1_.func_70691_i((float)(this.func_70805_n() + 1));
                this.func_85030_a("mob.attack", 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0f), this.field_70161_v, 0.0f, false);
                this.func_70106_y();
            }
        }
    }
}

