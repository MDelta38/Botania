/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class EntityBottleTaint
extends EntityThrowable {
    public EntityBottleTaint(World p_i1788_1_) {
        super(p_i1788_1_);
    }

    public EntityBottleTaint(World p_i1790_1_, EntityLivingBase p_i1790_2) {
        super(p_i1790_1_, p_i1790_2);
    }

    protected float func_70185_h() {
        return 0.05f;
    }

    protected float func_70182_d() {
        return 0.5f;
    }

    protected float func_70183_g() {
        return -20.0f;
    }

    protected void func_70184_a(MovingObjectPosition p_70184_1_) {
        if (!this.field_70170_p.field_72995_K) {
            List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v).func_72314_b(5.0, 5.0, 5.0));
            if (ents.size() > 0) {
                for (Object ent : ents) {
                    EntityLivingBase el = (EntityLivingBase)ent;
                    if (el instanceof ITaintedMob || el.func_70662_br()) continue;
                    el.func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 100, 0, false));
                }
            }
            int x = (int)this.field_70165_t;
            int y = (int)this.field_70163_u;
            int z = (int)this.field_70161_v;
            for (int a = 0; a < 10; ++a) {
                int xx = x + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0f);
                int zz = z + (int)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 5.0f);
                if (!this.field_70170_p.field_73012_v.nextBoolean() || this.field_70170_p.func_72807_a(xx, zz) == ThaumcraftWorldGenerator.biomeTaint) continue;
                Utils.setBiomeAt(this.field_70170_p, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
                if (!this.field_70170_p.func_147445_c(xx, y - 1, zz, false) || !this.field_70170_p.func_147439_a(xx, y, zz).isReplaceable((IBlockAccess)this.field_70170_p, xx, y, zz)) continue;
                this.field_70170_p.func_147465_d(xx, y, zz, ConfigBlocks.blockTaintFibres, 0, 3);
            }
            this.func_70106_y();
        } else {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(100); ++a) {
                Thaumcraft.proxy.taintsplosionFX((Entity)this);
            }
            Thaumcraft.proxy.bottleTaintBreak(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }
}

