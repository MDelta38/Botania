/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class EntityPrimalOrb
extends EntityThrowable
implements IEntityAdditionalSpawnData {
    int count = 0;
    boolean seeker = false;
    int oi = 0;

    public EntityPrimalOrb(World par1World) {
        super(par1World);
    }

    public EntityPrimalOrb(World par1World, EntityLivingBase par2EntityLiving, boolean seeker) {
        super(par1World, par2EntityLiving);
        this.seeker = seeker;
        this.oi = par2EntityLiving.func_145782_y();
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeBoolean(this.seeker);
        data.writeInt(this.oi);
    }

    public void readSpawnData(ByteBuf data) {
        this.seeker = data.readBoolean();
        this.oi = data.readInt();
    }

    protected float func_70185_h() {
        return 0.001f;
    }

    protected float func_70182_d() {
        return 0.5f;
    }

    public void func_70071_h_() {
        ++this.count;
        if (this.func_70055_a(Material.field_151567_E)) {
            this.func_70184_a(new MovingObjectPosition((Entity)this));
        }
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < 6; ++a) {
                Thaumcraft.proxy.wispFX4(this.field_70170_p, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f, (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f, (Entity)this, a, true, 0.0f);
            }
            Thaumcraft.proxy.wispFX2(this.field_70170_p, this.field_70165_t + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70163_u + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70161_v + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), 0.1f, this.field_70146_Z.nextInt(6), true, true, 0.0f);
        }
        Random rr = new Random(this.func_145782_y() + this.count);
        if (this.field_70173_aa > 20) {
            if (!this.seeker) {
                this.field_70159_w += (double)((rr.nextFloat() - rr.nextFloat()) * 0.01f);
                this.field_70181_x += (double)((rr.nextFloat() - rr.nextFloat()) * 0.01f);
                this.field_70179_y += (double)((rr.nextFloat() - rr.nextFloat()) * 0.01f);
            } else {
                ArrayList<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (Entity)this, EntityLivingBase.class, 16.0);
                double d = Double.MAX_VALUE;
                Entity t = null;
                for (Entity e : l) {
                    double dd;
                    if (e.func_145782_y() == this.oi || e.field_70128_L || !((dd = this.func_70068_e(e)) < d)) continue;
                    d = dd;
                    t = e;
                }
                if (t != null) {
                    double dx = t.field_70165_t - this.field_70165_t;
                    double dy = t.field_70121_D.field_72338_b + (double)t.field_70131_O * 0.9 - this.field_70163_u;
                    double dz = t.field_70161_v - this.field_70161_v;
                    double d13 = 0.2;
                    this.field_70159_w += (dx /= d) * d13;
                    this.field_70181_x += (dy /= d) * d13;
                    this.field_70179_y += (dz /= d) * d13;
                    this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.2f, (float)0.2f);
                    this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.2f, (float)0.2f);
                    this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.2f, (float)0.2f);
                }
            }
        }
        super.func_70071_h_();
        if (this.field_70173_aa > 5000) {
            this.func_70106_y();
        }
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < 6; ++a) {
                for (int b = 0; b < 6; ++b) {
                    float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5f;
                    float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5f;
                    float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.5f;
                    Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + (double)fx, this.field_70163_u + (double)fy, this.field_70161_v + (double)fz, this.field_70165_t + (double)(fx * 10.0f), this.field_70163_u + (double)(fy * 10.0f), this.field_70161_v + (double)(fz * 10.0f), 0.4f, b, true, 0.05f);
                }
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            float specialchance = 1.0f;
            float expl = 2.0f;
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.func_70055_a(Material.field_151567_E)) {
                expl = 4.0f;
                specialchance = 10.0f;
            }
            this.field_70170_p.func_72876_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, expl, true);
            if (!this.seeker && (float)this.field_70146_Z.nextInt(100) <= specialchance) {
                if (this.field_70146_Z.nextBoolean()) {
                    this.taintSplosion();
                } else {
                    ThaumcraftWorldGenerator.createRandomNodeAt(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, this.field_70146_Z, false, false, true);
                }
            }
            this.func_70106_y();
        }
    }

    public void taintSplosion() {
        int x = (int)this.field_70165_t;
        int y = (int)this.field_70163_u;
        int z = (int)this.field_70161_v;
        for (int a = 0; a < 10; ++a) {
            int xx = x + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0f);
            int zz = z + (int)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat() * 6.0f);
            if (!this.field_70146_Z.nextBoolean() || this.field_70170_p.func_72807_a(xx, zz) == ThaumcraftWorldGenerator.biomeTaint) continue;
            Utils.setBiomeAt(this.field_70170_p, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
            int yy = this.field_70170_p.func_72976_f(xx, zz);
            if (this.field_70170_p.func_147437_c(xx, yy - 1, zz)) continue;
            this.field_70170_p.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 3);
        }
    }

    public float func_70053_R() {
        return 0.1f;
    }
}

