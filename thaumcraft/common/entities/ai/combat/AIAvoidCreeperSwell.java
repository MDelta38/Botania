/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package thaumcraft.common.entities.ai.combat;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIAvoidCreeperSwell
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private float farSpeed;
    private float nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;
    Vec3 targetBlock;

    public AIAvoidCreeperSwell(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.distanceFromEntity = 5.0f;
        this.entityPathNavigate = par1EntityCreature.func_70661_as();
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        List var1;
        if (this.farSpeed == 0.0f) {
            this.farSpeed = this.theGolem.func_70689_ay() * 1.125f;
            this.nearSpeed = this.theGolem.func_70689_ay() * 1.25f;
        }
        if ((var1 = this.theGolem.field_70170_p.func_72872_a(EntityCreeper.class, this.theGolem.field_70121_D.func_72314_b((double)this.distanceFromEntity, 3.0, (double)this.distanceFromEntity))).isEmpty()) {
            return false;
        }
        if (((EntityCreeper)var1.get(0)).func_70832_p() != 1) {
            return false;
        }
        this.closestLivingEntity = (Entity)var1.get(0);
        if (!this.theGolem.func_70635_at().func_75522_a(this.closestLivingEntity)) {
            return false;
        }
        Vec3 var2 = RandomPositionGenerator.func_75461_b((EntityCreature)this.theGolem, (int)16, (int)7, (Vec3)Vec3.func_72443_a((double)this.closestLivingEntity.field_70165_t, (double)this.closestLivingEntity.field_70163_u, (double)this.closestLivingEntity.field_70161_v));
        if (var2 == null) {
            return false;
        }
        if (this.closestLivingEntity.func_70092_e(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c) < this.closestLivingEntity.func_70068_e((Entity)this.theGolem)) {
            return false;
        }
        this.entityPathEntity = this.entityPathNavigate.func_75488_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c);
        this.targetBlock = var2;
        return this.entityPathEntity == null ? false : this.entityPathEntity.func_75880_b(var2);
    }

    public boolean func_75253_b() {
        return !this.entityPathNavigate.func_75500_f();
    }

    public void func_75249_e() {
        double var1 = this.targetBlock.field_72450_a + 0.5 - this.theGolem.field_70165_t;
        double var3 = this.targetBlock.field_72449_c + 0.5 - this.theGolem.field_70161_v;
        float var5 = MathHelper.func_76133_a((double)(var1 * var1 + var3 * var3));
        this.theGolem.field_70159_w += var1 / (double)var5 * 1.0 * (double)0.8f + this.theGolem.field_70159_w * (double)0.2f;
        this.theGolem.field_70179_y += var3 / (double)var5 * 1.0 * (double)0.8f + this.theGolem.field_70179_y * (double)0.2f;
        this.theGolem.field_70181_x = 0.3;
        this.entityPathNavigate.func_75484_a(this.entityPathEntity, (double)this.nearSpeed);
    }

    public void func_75251_c() {
        this.closestLivingEntity = null;
    }

    public void func_75246_d() {
        if (this.theGolem.func_70068_e(this.closestLivingEntity) < 49.0) {
            this.theGolem.func_70661_as().func_75489_a((double)this.nearSpeed);
        } else {
            this.theGolem.func_70661_as().func_75489_a((double)this.farSpeed);
        }
    }
}

