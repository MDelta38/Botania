/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.util.Vec3
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class AIWander
extends EntityAIBase {
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private boolean field_179482_g;
    private static final String __OBFID = "CL_00001608";

    public AIWander(EntityCreature p_i1648_1_, double p_i1648_2_) {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        Vec3 vec3;
        if (!this.field_179482_g) {
            if (this.entity.func_70654_ax() >= 100) {
                return false;
            }
            if (this.entity.func_70681_au().nextInt(120) != 0) {
                return false;
            }
        }
        if ((vec3 = RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)10, (int)7)) == null) {
            return false;
        }
        this.xPosition = vec3.field_72450_a;
        this.yPosition = vec3.field_72448_b;
        this.zPosition = vec3.field_72449_c;
        this.field_179482_g = false;
        return true;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f();
    }

    public void setWander() {
        this.field_179482_g = true;
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}

