/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIWander
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityTreefyd;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;

public class EntityAITreefydWander
extends EntityAIWander {
    private final EntityTreefyd treefyd;

    public EntityAITreefydWander(EntityTreefyd treefyd, double speed) {
        super((EntityCreature)treefyd, speed);
        this.treefyd = treefyd;
    }

    public boolean func_75250_a() {
        return !this.treefyd.isSentinal() && super.func_75250_a();
    }

    public boolean func_75253_b() {
        return !this.treefyd.isSentinal() && super.func_75253_b();
    }
}

