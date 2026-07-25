/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;

public class EntityHorseUndeadS
extends EntityHorse {
    public EntityHorseUndeadS(World p_i1685_1_) {
        super(p_i1685_1_);
        this.func_110214_p(4);
    }

    public int func_110265_bP() {
        return 4;
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public boolean func_110256_cu() {
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + 0.1);
    }
}

