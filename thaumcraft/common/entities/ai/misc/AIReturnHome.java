/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIReturnHome
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private int pathingDelay = 0;
    private int pathingDelayInc = 5;
    int count = 0;
    int prevX = 0;
    int prevY = 0;
    int prevZ = 0;

    public AIReturnHome(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        if (this.pathingDelay > 0) {
            --this.pathingDelay;
        }
        if (this.pathingDelay > 0 || this.theGolem.func_70092_e((float)home.field_71574_a + 0.5f, (float)home.field_71572_b + 0.5f, (float)home.field_71573_c + 0.5f) < 3.0) {
            return false;
        }
        this.movePosX = (double)home.field_71574_a + 0.5;
        this.movePosY = (double)home.field_71572_b + 0.5;
        this.movePosZ = (double)home.field_71573_c + 0.5;
        return true;
    }

    public boolean func_75253_b() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        return this.pathingDelay <= 0 && this.count > 0 && !this.theGolem.func_70661_as().func_75500_f() && this.theGolem.func_70092_e((float)home.field_71574_a + 0.5f, (float)home.field_71572_b + 0.5f, (float)home.field_71573_c + 0.5f) >= 3.0;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        Vec3 var2;
        --this.count;
        if (this.count == 0 && this.prevX == MathHelper.func_76128_c((double)this.theGolem.field_70165_t) && this.prevY == MathHelper.func_76128_c((double)this.theGolem.field_70163_u) && this.prevZ == MathHelper.func_76128_c((double)this.theGolem.field_70161_v) && (var2 = RandomPositionGenerator.func_75463_a((EntityCreature)this.theGolem, (int)2, (int)1)) != null) {
            this.count = 20;
            boolean path = this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a + 0.5, var2.field_72448_b + 0.5, var2.field_72449_c + 0.5, (double)this.theGolem.func_70689_ay());
            if (!path) {
                this.pathingDelay = this.pathingDelayInc;
                if (this.pathingDelayInc < 50) {
                    this.pathingDelayInc += 5;
                }
            } else {
                this.pathingDelayInc = 5;
            }
        }
        super.func_75246_d();
    }

    public void func_75249_e() {
        this.count = 20;
        this.prevX = MathHelper.func_76128_c((double)this.theGolem.field_70165_t);
        this.prevY = MathHelper.func_76128_c((double)this.theGolem.field_70163_u);
        this.prevZ = MathHelper.func_76128_c((double)this.theGolem.field_70161_v);
        boolean path = this.theGolem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, (double)this.theGolem.func_70689_ay());
        if (!path) {
            this.pathingDelay = this.pathingDelayInc;
            if (this.pathingDelayInc < 50) {
                this.pathingDelayInc += 5;
            }
        } else {
            this.pathingDelayInc = 5;
        }
    }
}

