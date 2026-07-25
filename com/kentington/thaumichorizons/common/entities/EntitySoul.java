/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityFlying
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.entities;

import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntitySoul
extends EntityFlying
implements IMob {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;

    public EntitySoul(World world) {
        super(world);
        this.func_70105_a(0.9f, 0.9f);
        this.field_70728_aV = 5;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0);
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public int func_70682_h(int par1) {
        return par1;
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        return false;
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (this.field_70170_p.field_72995_K) {
            Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f);
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.field_70170_p.field_73012_v.nextBoolean()) {
            Color color = Color.CYAN;
            Thaumcraft.proxy.wispFX(this.field_70170_p, this.field_70165_t + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), this.field_70163_u + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), this.field_70161_v + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), 0.1f, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f);
        }
    }

    protected void func_70626_be() {
        double attackrange = 16.0;
        double d = this.waypointX - this.field_70165_t;
        double d1 = this.waypointY - this.field_70163_u;
        double d2 = this.waypointZ - this.field_70161_v;
        double d3 = d * d + d1 * d1 + d2 * d2;
        if (d3 < 1.0 || d3 > 3600.0) {
            this.waypointX = this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
            this.waypointY = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
            this.waypointZ = this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3 = (double)MathHelper.func_76133_a((double)d3))) {
                this.field_70159_w += d / d3 * 0.1;
                this.field_70181_x += d1 / d3 * 0.1;
                this.field_70179_y += d2 / d3 * 0.1;
            } else {
                this.waypointX = this.field_70165_t;
                this.waypointY = this.field_70163_u;
                this.waypointZ = this.field_70161_v;
            }
        }
        this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0f / 3.141593f;
    }

    private boolean isCourseTraversable(double d, double d1, double d2, double d3) {
        double d4 = (this.waypointX - this.field_70165_t) / d3;
        double d5 = (this.waypointY - this.field_70163_u) / d3;
        double d6 = (this.waypointZ - this.field_70161_v) / d3;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
        int i = 1;
        while ((double)i < d3) {
            axisalignedbb.func_72317_d(d4, d5, d6);
            if (!this.field_70170_p.func_72945_a((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        int x = (int)this.waypointX;
        int y = (int)this.waypointY;
        int z = (int)this.waypointZ;
        if (this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        for (int a = 0; a < 11; ++a) {
            if (this.field_70170_p.func_147437_c(x, y - a, z)) continue;
            return true;
        }
        return false;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:wisplive";
    }

    protected String func_70621_aR() {
        return "random.fizz";
    }

    protected String func_70673_aS() {
        return "thaumcraft:wispdead";
    }

    protected Item func_146068_u() {
        return null;
    }

    protected void func_70628_a(boolean flag, int i) {
    }

    protected float func_70599_aP() {
        return 0.25f;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70601_bi() {
        return true;
    }

    protected boolean isValidLightLevel() {
        return true;
    }
}

