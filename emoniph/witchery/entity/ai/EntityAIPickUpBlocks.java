/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.util.AxisAlignedBB
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIPickUpBlocks
extends EntityAIBase {
    protected final EntityGoblin entity;
    protected final double range;

    public EntityAIPickUpBlocks(EntityGoblin entity, double range) {
        this.entity = entity;
        this.range = range;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        return this.entity != null && !this.entity.isWorshipping() && this.entity.func_70694_bm() == null && this.entity.func_110167_bD() && this.isItemInReachableDistance();
    }

    public void func_75249_e() {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.entity.field_70165_t - this.range), (double)(this.entity.field_70163_u - this.range), (double)(this.entity.field_70161_v - this.range), (double)(this.entity.field_70165_t + this.range), (double)(this.entity.field_70163_u + this.range), (double)(this.entity.field_70161_v + this.range));
        List items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
        double SPEED = 0.6;
        for (EntityItem item : items) {
            if (!this.entity.func_70661_as().func_75497_a((Entity)item, 0.6)) continue;
            break;
        }
    }

    public boolean func_75253_b() {
        return this.entity != null && !this.entity.isWorshipping() && this.entity.func_70694_bm() == null && this.entity.func_110167_bD() && this.isItemInReachableDistance();
    }

    public void func_75246_d() {
        if (this.entity.func_70661_as().func_75500_f()) {
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.entity.field_70165_t - this.range), (double)(this.entity.field_70163_u - this.range), (double)(this.entity.field_70161_v - this.range), (double)(this.entity.field_70165_t + this.range), (double)(this.entity.field_70163_u + this.range), (double)(this.entity.field_70161_v + this.range));
            List items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
            double SPEED = 0.6;
            for (EntityItem item : items) {
                if (!this.entity.func_70661_as().func_75497_a((Entity)item, 0.6)) continue;
                break;
            }
        } else {
            double PICKUP_RANGE = 1.5;
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.entity.field_70165_t - 1.5), (double)(this.entity.field_70163_u - 1.5), (double)(this.entity.field_70161_v - 1.5), (double)(this.entity.field_70165_t + 1.5), (double)(this.entity.field_70163_u + 1.5), (double)(this.entity.field_70161_v + 1.5));
            List items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
            if (!items.isEmpty()) {
                this.entity.func_70062_b(0, ((EntityItem)items.get(0)).func_92059_d());
                if (!this.entity.field_70170_p.field_72995_K) {
                    ((EntityItem)items.get(0)).func_70106_y();
                }
            }
        }
    }

    protected boolean isItemInReachableDistance() {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.entity.field_70165_t - this.range), (double)(this.entity.field_70163_u - this.range), (double)(this.entity.field_70161_v - this.range), (double)(this.entity.field_70165_t + this.range), (double)(this.entity.field_70163_u + this.range), (double)(this.entity.field_70161_v + this.range));
        List items = this.entity.field_70170_p.func_72872_a(EntityItem.class, bb);
        double SPEED = 0.1;
        for (EntityItem item : items) {
            if (this.entity.func_70661_as().func_75494_a((Entity)item) == null) continue;
            return true;
        }
        return false;
    }
}

