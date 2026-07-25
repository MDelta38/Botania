/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.Village
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIGoblinMate
extends EntityAIBase {
    private EntityGoblin goblinObj;
    private EntityGoblin mate;
    private World worldObj;
    private int matingTimeout;
    Village villageObj;

    public EntityAIGoblinMate(EntityGoblin goblin) {
        this.goblinObj = goblin;
        this.worldObj = goblin.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.goblinObj.func_70874_b() != 0) {
            return false;
        }
        if (this.goblinObj.func_70681_au().nextInt(500) != 0) {
            return false;
        }
        this.villageObj = this.worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)this.goblinObj.field_70165_t), MathHelper.func_76128_c((double)this.goblinObj.field_70163_u), MathHelper.func_76128_c((double)this.goblinObj.field_70161_v), 0);
        if (this.villageObj == null) {
            return false;
        }
        if (!this.checkSufficientDoorsPresentForNewVillager()) {
            return false;
        }
        Entity entity = this.worldObj.func_72857_a(EntityGoblin.class, this.goblinObj.field_70121_D.func_72314_b(8.0, 3.0, 8.0), (Entity)this.goblinObj);
        if (entity == null) {
            return false;
        }
        this.mate = (EntityGoblin)entity;
        return this.mate.func_70874_b() == 0;
    }

    public void func_75249_e() {
        this.matingTimeout = 300;
        this.goblinObj.setMating(true);
    }

    public void func_75251_c() {
        this.villageObj = null;
        this.mate = null;
        this.goblinObj.setMating(false);
    }

    public boolean func_75253_b() {
        return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.goblinObj.func_70874_b() == 0;
    }

    public void func_75246_d() {
        --this.matingTimeout;
        this.goblinObj.func_70671_ap().func_75651_a((Entity)this.mate, 10.0f, 30.0f);
        if (this.goblinObj.func_70068_e((Entity)this.mate) > 2.25) {
            this.goblinObj.func_70661_as().func_75497_a((Entity)this.mate, 0.25);
        } else if (this.matingTimeout == 0 && this.mate.isMating()) {
            this.giveBirth();
        }
        if (this.goblinObj.func_70681_au().nextInt(35) == 0) {
            this.worldObj.func_72960_a((Entity)this.goblinObj, (byte)12);
        }
    }

    private boolean checkSufficientDoorsPresentForNewVillager() {
        if (!this.villageObj.func_82686_i()) {
            return false;
        }
        int i = (int)((double)this.villageObj.func_75567_c() * 0.35);
        return this.getNumVillagers() < i;
    }

    private int getNumVillagers() {
        if (this.worldObj == null || this.goblinObj == null) {
            return 0;
        }
        List list = this.worldObj.func_72872_a(EntityGoblin.class, this.goblinObj.field_70121_D.func_72314_b(32.0, 3.0, 32.0));
        return list != null ? list.size() : 0;
    }

    private void giveBirth() {
        EntityGoblin entityvillager = this.goblinObj.createChild(this.mate);
        this.mate.func_70873_a(6000);
        this.goblinObj.func_70873_a(6000);
        entityvillager.func_70873_a(-24000);
        entityvillager.func_70012_b(this.goblinObj.field_70165_t, this.goblinObj.field_70163_u, this.goblinObj.field_70161_v, 0.0f, 0.0f);
        this.worldObj.func_72838_d((Entity)entityvillager);
        this.worldObj.func_72960_a((Entity)entityvillager, (byte)12);
    }
}

