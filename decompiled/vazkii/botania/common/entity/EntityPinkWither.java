/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPinkWither
extends EntityWither {
    public EntityPinkWither(World p_i1701_1_) {
        super(p_i1701_1_);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (Math.random() < 0.1) {
            for (int j = 0; j < 3; ++j) {
                double d10 = this.func_82214_u(j);
                double d2 = this.func_82208_v(j);
                double d4 = this.func_82213_w(j);
                this.field_70170_p.func_72869_a("heart", d10 + this.field_70146_Z.nextGaussian() * (double)0.3f, d2 + this.field_70146_Z.nextGaussian() * (double)0.3f, d4 + this.field_70146_Z.nextGaussian() * (double)0.3f, 0.0, 0.0, 0.0);
            }
        }
    }

    public void func_70624_b(EntityLivingBase p_70624_1_) {
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_) {
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        return false;
    }

    protected boolean func_70085_c(EntityPlayer player) {
        if (!player.func_70093_af()) {
            player.func_70078_a((Entity)this);
            return true;
        }
        return false;
    }

    protected boolean func_70650_aV() {
        return false;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    }

    private double func_82214_u(int p_82214_1_) {
        if (p_82214_1_ <= 0) {
            return this.field_70165_t;
        }
        float f = (this.field_70761_aq + (float)(180 * (p_82214_1_ - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.func_76134_b((float)f);
        return this.field_70165_t + (double)f1 * 1.3;
    }

    private double func_82208_v(int p_82208_1_) {
        return p_82208_1_ <= 0 ? this.field_70163_u + 3.0 : this.field_70163_u + 2.2;
    }

    private double func_82213_w(int p_82213_1_) {
        if (p_82213_1_ <= 0) {
            return this.field_70161_v;
        }
        float f = (this.field_70761_aq + (float)(180 * (p_82213_1_ - 1))) / 180.0f * (float)Math.PI;
        float f1 = MathHelper.func_76126_a((float)f);
        return this.field_70161_v + (double)f1 * 1.3;
    }
}

