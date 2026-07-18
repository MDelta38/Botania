/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;

public class EntityFlameRing
extends Entity {
    public EntityFlameRing(World world) {
        super(world);
    }

    protected void func_70088_a() {
        this.func_70105_a(0.0f, 0.0f);
    }

    public void func_70030_z() {
        super.func_70030_z();
        float radius = 5.0f;
        float renderRadius = (float)((double)radius - Math.random());
        for (int i = 0; i < Math.min(90, this.field_70173_aa); ++i) {
            float a = i;
            if (a % 2.0f == 0.0f) {
                a = 45.0f + a;
            }
            if (this.field_70170_p.field_73012_v.nextInt(this.field_70173_aa < 90 ? 8 : 20) != 0) continue;
            float rad = (float)((double)(a * 4.0f) * Math.PI / 180.0);
            double x = Math.cos(rad) * (double)renderRadius;
            double z = Math.sin(rad) * (double)renderRadius;
            Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t + x, this.field_70163_u - 0.2, this.field_70161_v + z, 1.0f, (float)Math.random() * 0.25f, (float)Math.random() * 0.25f, 0.65f + (float)Math.random() * 0.45f, (float)(Math.random() - 0.5) * 0.15f, 0.055f + (float)Math.random() * 0.025f, (float)(Math.random() - 0.5) * 0.15f);
            float gs = (float)Math.random() * 0.15f;
            float smokeRadius = (float)((double)renderRadius - Math.random() * (double)renderRadius * 0.9);
            x = Math.cos(rad) * (double)smokeRadius;
            z = Math.sin(rad) * (double)smokeRadius;
            Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t + x, this.field_70163_u - 0.2, this.field_70161_v + z, gs, gs, gs, 0.65f + (float)Math.random() * 0.45f, -0.155f - (float)Math.random() * 0.025f);
        }
        if (this.field_70170_p.field_73012_v.nextInt(20) == 0) {
            this.field_70170_p.func_72956_a((Entity)this, "fire.fire", 1.0f, 1.0f);
        }
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.field_70173_aa >= 300) {
            this.func_70106_y();
            return;
        }
        if (this.field_70173_aa > 45) {
            AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v).func_72314_b((double)radius, (double)radius, (double)radius);
            List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, boundingBox);
            if (entities.isEmpty()) {
                return;
            }
            for (EntityLivingBase entity : entities) {
                if (entity == null || MathHelper.pointDistancePlane(this.field_70165_t, this.field_70163_u, entity.field_70165_t, entity.field_70163_u) > radius) continue;
                entity.func_70015_d(4);
            }
        }
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return false;
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }
}

