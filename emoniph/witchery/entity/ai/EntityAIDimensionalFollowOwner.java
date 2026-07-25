/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityAIDimensionalFollowOwner
extends EntityAIBase {
    private EntityTameable thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private double field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;

    public EntityAIDimensionalFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5) {
        this.thePet = par1EntityTameable;
        this.theWorld = par1EntityTameable.field_70170_p;
        this.field_75336_f = par2;
        this.petPathfinder = par1EntityTameable.func_70661_as();
        this.minDist = par4;
        this.maxDist = par5;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.thePet.func_70902_q();
        if (entitylivingbase == null) {
            return false;
        }
        if (this.thePet.func_70906_o()) {
            return false;
        }
        if (this.thePet.field_71093_bK != entitylivingbase.field_71093_bK || this.thePet.func_70068_e((Entity)entitylivingbase) < (double)(this.minDist * this.minDist)) {
            return false;
        }
        this.theOwner = entitylivingbase;
        return true;
    }

    public boolean func_75253_b() {
        return !this.petPathfinder.func_75500_f() && this.thePet.func_70068_e((Entity)this.theOwner) > (double)(this.maxDist * this.maxDist) && !this.thePet.func_70906_o();
    }

    public void func_75249_e() {
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.func_70661_as().func_75486_a();
        this.thePet.func_70661_as().func_75491_a(false);
    }

    public void func_75251_c() {
        this.theOwner = null;
        this.petPathfinder.func_75499_g();
        this.thePet.func_70661_as().func_75491_a(this.field_75344_i);
    }

    public void func_75246_d() {
        this.thePet.func_70671_ap().func_75651_a((Entity)this.theOwner, 10.0f, (float)this.thePet.func_70646_bf());
        if (!this.thePet.func_70906_o() && --this.field_75343_h <= 0) {
            this.field_75343_h = 10;
            if (!(this.petPathfinder.func_75497_a((Entity)this.theOwner, this.field_75336_f) || this.thePet.func_110167_bD() || this.thePet.field_71093_bK == this.theOwner.field_71093_bK && !(this.thePet.func_70068_e((Entity)this.theOwner) >= 144.0))) {
                int i = MathHelper.func_76128_c((double)this.theOwner.field_70165_t) - 2;
                int j = MathHelper.func_76128_c((double)this.theOwner.field_70161_v) - 2;
                int k = MathHelper.func_76128_c((double)this.theOwner.field_70121_D.field_72338_b);
                for (int l = 0; l <= 4; ++l) {
                    for (int i1 = 0; i1 <= 4; ++i1) {
                        if (l >= 1 && i1 >= 1 && l <= 3 && i1 <= 3 || !this.theOwner.field_70170_p.func_147439_a(i + l, k - 1, j + i1).isSideSolid((IBlockAccess)this.theOwner.field_70170_p, i + l, k - 1, j + i1, ForgeDirection.UP) || this.theOwner.field_70170_p.func_147439_a(i + l, k, j + i1).func_149721_r() || this.theOwner.field_70170_p.func_147439_a(i + l, k + 1, j + i1).func_149721_r()) continue;
                        if (this.thePet.field_71093_bK == this.theOwner.field_71093_bK) {
                            this.thePet.func_70012_b((double)((float)(i + l) + 0.5f), (double)k, (double)((float)(j + i1) + 0.5f), this.thePet.field_70177_z, this.thePet.field_70125_A);
                        }
                        this.petPathfinder.func_75499_g();
                        return;
                    }
                }
            }
        }
    }
}

