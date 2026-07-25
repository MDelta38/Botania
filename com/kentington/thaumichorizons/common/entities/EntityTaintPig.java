/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.potion.Potion
 *  net.minecraft.world.World
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.entities.ai.EntityAIEatTaint;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;

public class EntityTaintPig
extends EntityPig {
    public EntityTaintPig(World p_i1689_1_) {
        super(p_i1689_1_);
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIEatTaint(this));
    }

    public void func_70629_bd() {
        super.func_70629_bd();
        if (this.func_70660_b(Potion.field_76425_a[Config.potionTaintPoisonID]) != null) {
            this.func_82170_o(Config.potionTaintPoisonID);
        }
        if (this.func_70660_b(Potion.field_76425_a[Config.potionInfVisExhaustID]) != null) {
            this.func_82170_o(Config.potionInfVisExhaustID);
        }
        if (this.func_70660_b(Potion.field_76425_a[Config.potionVisExhaustID]) != null) {
            this.func_82170_o(Config.potionVisExhaustID);
        }
    }
}

