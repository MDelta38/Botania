/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.entities.EntityOrePig;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIEatStone
extends EntityAIBase {
    private EntityOrePig thePig;
    private Entity targetEntity;
    int count = 0;

    public EntityAIEatStone(EntityOrePig par1EntityCreature) {
        this.thePig = par1EntityCreature;
    }

    public boolean func_75250_a() {
        return this.findItem();
    }

    private boolean findItem() {
        float dmod = 16.0f;
        List targets = this.thePig.field_70170_p.func_72839_b((Entity)this.thePig, AxisAlignedBB.func_72330_a((double)(this.thePig.field_70165_t - 16.0), (double)(this.thePig.field_70163_u - 16.0), (double)(this.thePig.field_70161_v - 16.0), (double)(this.thePig.field_70165_t + 16.0), (double)(this.thePig.field_70163_u + 16.0), (double)(this.thePig.field_70161_v + 16.0)));
        if (targets.size() == 0) {
            return false;
        }
        for (Entity e : targets) {
            double distance2;
            if (!(e instanceof EntityItem) || ((EntityItem)e).func_92059_d().func_77973_b() != Item.func_150898_a((Block)Blocks.field_150347_e) || ((EntityItem)e).field_145804_b >= 5 || !((distance2 = e.func_70092_e(this.thePig.field_70165_t, this.thePig.field_70163_u, this.thePig.field_70161_v)) < (double)(dmod * dmod))) continue;
            this.targetEntity = e;
        }
        return this.targetEntity != null;
    }

    public boolean func_75253_b() {
        return this.count-- > 0 && !this.thePig.func_70661_as().func_75500_f() && this.targetEntity.func_70089_S();
    }

    public void func_75251_c() {
        this.count = 0;
        this.targetEntity = null;
        this.thePig.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        this.thePig.func_70671_ap().func_75651_a(this.targetEntity, 30.0f, 30.0f);
        double dist = this.thePig.func_70068_e(this.targetEntity);
        if (dist <= 2.0) {
            this.pickUp();
        }
    }

    private void pickUp() {
        boolean amount = false;
        if (this.targetEntity instanceof EntityItem) {
            this.thePig.eatStone();
            --((EntityItem)this.targetEntity).func_92059_d().field_77994_a;
            if (((EntityItem)this.targetEntity).func_92059_d().field_77994_a <= 0) {
                this.targetEntity.func_70106_y();
            }
        }
        this.targetEntity.field_70170_p.func_72956_a(this.targetEntity, "random.burp", 0.2f, ((this.targetEntity.field_70170_p.field_73012_v.nextFloat() - this.targetEntity.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);
    }

    public void func_75249_e() {
        this.count = 500;
        this.thePig.func_70661_as().func_75497_a(this.targetEntity, (double)(this.thePig.func_70689_ay() + 1.0f));
    }
}

