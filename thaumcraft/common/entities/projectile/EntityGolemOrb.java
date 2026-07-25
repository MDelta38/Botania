/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntityGolemOrb
extends EntityThrowable
implements IEntityAdditionalSpawnData {
    int targetID = 0;
    EntityLivingBase target;
    public boolean red = false;

    public EntityGolemOrb(World par1World) {
        super(par1World);
    }

    public EntityGolemOrb(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase t, boolean r) {
        super(par1World, par2EntityLiving);
        this.target = t;
        this.red = r;
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    public void writeSpawnData(ByteBuf data) {
        int id = -1;
        if (this.target != null) {
            id = this.target.func_145782_y();
        }
        data.writeInt(id);
        data.writeBoolean(this.red);
    }

    public void readSpawnData(ByteBuf data) {
        int id = data.readInt();
        try {
            if (id >= 0) {
                this.target = (EntityLivingBase)this.field_70170_p.func_73045_a(id);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.red = data.readBoolean();
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K && this.func_85052_h() != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            mop.field_72308_g.func_70097_a(DamageSource.func_76354_b((Entity)this, (Entity)this.func_85052_h()), (float)this.func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * (this.red ? 1.0f : 0.6f));
        }
        this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f);
        Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f);
        this.func_70106_y();
    }

    public float func_70053_R() {
        return 0.1f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > (this.red ? 240 : 160)) {
            this.func_70106_y();
        }
        if (this.target != null) {
            double d = this.func_70068_e((Entity)this.target);
            double dx = this.target.field_70165_t - this.field_70165_t;
            double dy = this.target.field_70121_D.field_72338_b + (double)this.target.field_70131_O * 0.6 - this.field_70163_u;
            double dz = this.target.field_70161_v - this.field_70161_v;
            double d13 = 0.2;
            this.field_70159_w += (dx /= d) * d13;
            this.field_70181_x += (dy /= d) * d13;
            this.field_70179_y += (dz /= d) * d13;
            this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.25f, (float)0.25f);
            this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.25f, (float)0.25f);
            this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.25f, (float)0.25f);
        }
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.func_85032_ar()) {
            return false;
        }
        this.func_70018_K();
        if (p_70097_1_.func_76346_g() != null) {
            Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
            if (vec3 != null) {
                this.field_70159_w = vec3.field_72450_a;
                this.field_70181_x = vec3.field_72448_b;
                this.field_70179_y = vec3.field_72449_c;
                this.field_70159_w *= 0.9;
                this.field_70181_x *= 0.9;
                this.field_70179_y *= 0.9;
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:zap", 1.0f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f);
            }
            return true;
        }
        return false;
    }
}

