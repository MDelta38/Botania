/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.Village
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIVillagerMateNow
extends EntityAIBase {
    private EntityVillager villagerObj;
    private EntityVillager mate;
    private World worldObj;
    private int matingTimeout;
    private boolean begin;
    Village villageObj;

    public EntityAIVillagerMateNow(EntityVillager p_i1634_1_) {
        this.villagerObj = p_i1634_1_;
        this.worldObj = p_i1634_1_.field_70170_p;
        this.func_75248_a(3);
    }

    public void beginMating() {
        this.begin = true;
    }

    public boolean func_75250_a() {
        if (this.villagerObj.func_70874_b() != 0 || !this.begin) {
            return false;
        }
        this.villageObj = this.worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)this.villagerObj.field_70165_t), MathHelper.func_76128_c((double)this.villagerObj.field_70163_u), MathHelper.func_76128_c((double)this.villagerObj.field_70161_v), 0);
        if (this.villageObj == null) {
            return false;
        }
        if (!this.checkSufficientDoorsPresentForNewVillager()) {
            return false;
        }
        Entity entity = this.worldObj.func_72857_a(EntityVillager.class, this.villagerObj.field_70121_D.func_72314_b(8.0, 3.0, 8.0), (Entity)this.villagerObj);
        if (entity == null) {
            return false;
        }
        this.mate = (EntityVillager)entity;
        return this.mate.func_70874_b() == 0;
    }

    public void func_75249_e() {
        this.matingTimeout = 300;
        this.villagerObj.func_70947_e(true);
        this.begin = false;
    }

    public void func_75251_c() {
        this.villageObj = null;
        this.mate = null;
        this.villagerObj.func_70947_e(false);
        this.begin = false;
    }

    public boolean func_75253_b() {
        boolean keepGoing = this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.func_70874_b() == 0;
        return keepGoing;
    }

    public void func_75246_d() {
        --this.matingTimeout;
        this.villagerObj.func_70671_ap().func_75651_a((Entity)this.mate, 10.0f, 30.0f);
        if (this.villagerObj.func_70068_e((Entity)this.mate) > 2.25) {
            this.villagerObj.func_70661_as().func_75497_a((Entity)this.mate, 0.25);
        } else if (this.matingTimeout == 0 && this.mate.func_70941_o()) {
            this.giveBirth();
        }
        if (this.villagerObj.func_70681_au().nextInt(35) == 0) {
            this.worldObj.func_72960_a((Entity)this.villagerObj, (byte)12);
        }
    }

    private boolean checkSufficientDoorsPresentForNewVillager() {
        return true;
    }

    private void giveBirth() {
        EntityVillager entityvillager = this.villagerObj.func_90011_a((EntityAgeable)this.mate);
        this.mate.func_70873_a(500);
        this.villagerObj.func_70873_a(500);
        entityvillager.func_70873_a(-24000);
        entityvillager.func_70012_b(this.villagerObj.field_70165_t, this.villagerObj.field_70163_u, this.villagerObj.field_70161_v, 0.0f, 0.0f);
        this.worldObj.func_72838_d((Entity)entityvillager);
        this.worldObj.func_72960_a((Entity)entityvillager, (byte)12);
    }
}

