/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAISit
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.util.Waypoint;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlyingTameable
extends EntityTameable {
    protected EntityAISit field_70911_d = new EntityAISit((EntityTameable)this);
    public ItemStack waypoint = null;
    public double homeX;
    public double homeY;
    public double homeZ;

    public Waypoint getWaypoint() {
        return new Waypoint(this.field_70170_p, this.waypoint, this.homeX, this.homeY, this.homeZ);
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        if (this.waypoint != null) {
            NBTTagCompound nbtWaypoint = new NBTTagCompound();
            this.waypoint.func_77955_b(nbtWaypoint);
            nbtRoot.func_74782_a("WITCWaypoint", (NBTBase)nbtWaypoint);
        }
        nbtRoot.func_74780_a("HomeX", this.homeX);
        nbtRoot.func_74780_a("HomeY", this.homeY);
        nbtRoot.func_74780_a("HomeZ", this.homeZ);
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        if (nbtRoot.func_74764_b("WITCWaypoint")) {
            NBTTagCompound nbtWaypoint = nbtRoot.func_74775_l("WITCWaypoint");
            this.waypoint = ItemStack.func_77949_a((NBTTagCompound)nbtWaypoint);
        } else {
            this.waypoint = null;
        }
        this.homeX = nbtRoot.func_74769_h("HomeX");
        this.homeY = nbtRoot.func_74769_h("HomeY");
        this.homeZ = nbtRoot.func_74769_h("HomeZ");
    }

    public EntityFlyingTameable(World par1World) {
        super(par1World);
    }

    protected void func_70069_a(float par1) {
    }

    protected void func_70064_a(double par1, boolean par3) {
    }

    public void func_70612_e(float par1, float par2) {
        if (this.func_70090_H()) {
            this.func_70060_a(par1, par2, 0.02f);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)0.8f;
            this.field_70181_x *= (double)0.8f;
            this.field_70179_y *= (double)0.8f;
        } else if (this.func_70058_J()) {
            this.func_70060_a(par1, par2, 0.02f);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.5;
            this.field_70181_x *= 0.5;
            this.field_70179_y *= 0.5;
        } else {
            float f2 = 0.91f;
            if (this.field_70122_E) {
                f2 = 0.54600006f;
                Block i = this.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c((double)this.field_70161_v));
                if (i != Blocks.field_150350_a) {
                    f2 = i.field_149765_K * 0.91f;
                }
            }
            float f3 = 0.16277136f / (f2 * f2 * f2);
            this.func_70060_a(par1, par2, this.field_70122_E ? 0.1f * f3 : 0.02f);
            f2 = 0.91f;
            if (this.field_70122_E) {
                f2 = 0.54600006f;
                Block j = this.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c((double)this.field_70161_v));
                if (j != Blocks.field_150350_a) {
                    f2 = j.field_149765_K * 0.91f;
                }
            }
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)f2;
            this.field_70181_x *= (double)f2;
            this.field_70179_y *= (double)f2;
        }
        this.field_70722_aY = this.field_70721_aZ;
        double d0 = this.field_70165_t - this.field_70169_q;
        double d1 = this.field_70161_v - this.field_70166_s;
        float f4 = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1)) * 4.0f;
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4f;
        this.field_70754_ba += this.field_70721_aZ;
    }

    public boolean func_70617_f_() {
        return false;
    }
}

