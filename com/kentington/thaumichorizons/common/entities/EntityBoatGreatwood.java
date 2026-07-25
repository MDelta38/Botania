/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoatGreatwood
extends EntityBoat {
    private boolean isBoatEmpty = true;
    private double speedMultiplier = 0.09;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    @SideOnly(value=Side.CLIENT)
    private double velocityX;
    @SideOnly(value=Side.CLIENT)
    private double velocityY;
    @SideOnly(value=Side.CLIENT)
    private double velocityZ;
    private static final String __OBFID = "CL_00001667";

    public EntityBoatGreatwood(World p_i1704_1_) {
        super(p_i1704_1_);
        this.field_70156_m = true;
        this.func_70105_a(1.5f, 0.6f);
        this.field_70129_M = this.field_70131_O / 2.0f;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(17, (Object)new Integer(0));
        this.field_70180_af.func_75682_a(18, (Object)new Integer(1));
        this.field_70180_af.func_75682_a(19, (Object)new Float(0.0f));
    }

    public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
        return p_70114_1_.field_70121_D;
    }

    public AxisAlignedBB func_70046_E() {
        return this.field_70121_D;
    }

    public boolean func_70104_M() {
        return true;
    }

    public EntityBoatGreatwood(World p_i1705_1_, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_) {
        this(p_i1705_1_);
        this.func_70107_b(p_i1705_2_, p_i1705_4_ + (double)this.field_70129_M, p_i1705_6_);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = p_i1705_2_;
        this.field_70167_r = p_i1705_4_;
        this.field_70166_s = p_i1705_6_;
    }

    public double func_70042_X() {
        return (double)this.field_70131_O * 0.0 - (double)0.3f;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.func_85032_ar()) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            boolean flag;
            this.func_70269_c(-this.func_70267_i());
            this.func_70265_b(10);
            this.func_70266_a(this.func_70271_g() + p_70097_2_ * 10.0f);
            this.func_70018_K();
            boolean bl = flag = p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d;
            if (flag || this.func_70271_g() > 40.0f) {
                if (this.field_70153_n != null) {
                    this.field_70153_n.func_70078_a((Entity)this);
                }
                if (!flag) {
                    this.func_145778_a(ThaumicHorizons.itemBoatGreatwood, 1, 0.0f);
                }
                this.func_70106_y();
            }
            return true;
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70057_ab() {
        this.func_70269_c(-this.func_70267_i());
        this.func_70265_b(10);
        this.func_70266_a(this.func_70271_g() * 11.0f);
    }

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
        if (this.isBoatEmpty) {
            this.boatPosRotationIncrements = p_70056_9_ + 5;
        } else {
            double d3 = p_70056_1_ - this.field_70165_t;
            double d4 = p_70056_3_ - this.field_70163_u;
            double d5 = p_70056_5_ - this.field_70161_v;
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;
            if (d6 <= 1.0) {
                return;
            }
            this.boatPosRotationIncrements = 3;
        }
        this.boatX = p_70056_1_;
        this.boatY = p_70056_3_;
        this.boatZ = p_70056_5_;
        this.boatYaw = p_70056_7_;
        this.boatPitch = p_70056_8_;
        this.field_70159_w = this.velocityX;
        this.field_70181_x = this.velocityY;
        this.field_70179_y = this.velocityZ;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        this.velocityX = this.field_70159_w = p_70016_1_;
        this.velocityY = this.field_70181_x = p_70016_3_;
        this.velocityZ = this.field_70179_y = p_70016_5_;
    }

    public void func_70071_h_() {
        double d12;
        double d11;
        int j;
        double d4;
        double d2;
        this.func_70030_z();
        if (this.func_70268_h() > 0) {
            this.func_70265_b(this.func_70268_h() - 1);
        }
        if (this.func_70271_g() > 0.0f) {
            this.func_70266_a(this.func_70271_g() - 1.0f);
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int b0 = 5;
        double d0 = 0.0;
        for (int i = 0; i < b0; ++i) {
            double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(i + 0) / (double)b0 - 0.125;
            double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(i + 1) / (double)b0 - 0.125;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.field_70121_D.field_72340_a, (double)d1, (double)this.field_70121_D.field_72339_c, (double)this.field_70121_D.field_72336_d, (double)d3, (double)this.field_70121_D.field_72334_f);
            if (!this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h)) continue;
            d0 += 1.0 / (double)b0;
        }
        double d10 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        if (d10 > 0.26249999999999996) {
            d2 = Math.cos((double)this.field_70177_z * Math.PI / 180.0);
            d4 = Math.sin((double)this.field_70177_z * Math.PI / 180.0);
            j = 0;
            while ((double)j < 1.0 + d10 * 60.0) {
                double d9;
                double d8;
                double d5 = this.field_70146_Z.nextFloat() * 2.0f - 1.0f;
                double d6 = (double)(this.field_70146_Z.nextInt(2) * 2 - 1) * 0.7;
                if (this.field_70146_Z.nextBoolean()) {
                    d8 = this.field_70165_t - d2 * d5 * 0.8 + d4 * d6;
                    d9 = this.field_70161_v - d4 * d5 * 0.8 - d2 * d6;
                    this.field_70170_p.func_72869_a("splash", d8, this.field_70163_u - 0.125, d9, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                } else {
                    d8 = this.field_70165_t + d2 + d4 * d5 * 0.7;
                    d9 = this.field_70161_v + d4 - d2 * d5 * 0.7;
                    this.field_70170_p.func_72869_a("splash", d8, this.field_70163_u - 0.125, d9, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                ++j;
            }
        }
        if (this.field_70170_p.field_72995_K && this.isBoatEmpty) {
            if (this.boatPosRotationIncrements > 0) {
                d2 = this.field_70165_t + (this.boatX - this.field_70165_t) / (double)this.boatPosRotationIncrements;
                d4 = this.field_70163_u + (this.boatY - this.field_70163_u) / (double)this.boatPosRotationIncrements;
                d11 = this.field_70161_v + (this.boatZ - this.field_70161_v) / (double)this.boatPosRotationIncrements;
                d12 = MathHelper.func_76138_g((double)(this.boatYaw - (double)this.field_70177_z));
                this.field_70177_z = (float)((double)this.field_70177_z + d12 / (double)this.boatPosRotationIncrements);
                this.field_70125_A = (float)((double)this.field_70125_A + (this.boatPitch - (double)this.field_70125_A) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.func_70107_b(d2, d4, d11);
                this.func_70101_b(this.field_70177_z, this.field_70125_A);
            } else {
                d2 = this.field_70165_t + this.field_70159_w;
                d4 = this.field_70163_u + this.field_70181_x;
                d11 = this.field_70161_v + this.field_70179_y;
                this.func_70107_b(d2, d4, d11);
                if (this.field_70122_E) {
                    this.field_70159_w *= 0.5;
                    this.field_70181_x *= 0.5;
                    this.field_70179_y *= 0.5;
                }
                this.field_70159_w *= (double)0.99f;
                this.field_70181_x *= (double)0.95f;
                this.field_70179_y *= (double)0.99f;
            }
        } else {
            double d7;
            if (d0 < 1.0) {
                d2 = d0 * 2.0 - 1.0;
                this.field_70181_x += (double)0.04f * d2;
            } else {
                if (this.field_70181_x < 0.0) {
                    this.field_70181_x /= 2.0;
                }
                this.field_70181_x += (double)0.007f;
            }
            if (this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_70153_n;
                float f = this.field_70153_n.field_70177_z + -entitylivingbase.field_70702_br * 90.0f;
                this.field_70159_w += -Math.sin(f * (float)Math.PI / 180.0f) * this.speedMultiplier * (double)entitylivingbase.field_70701_bs * (double)0.05f;
                this.field_70179_y += Math.cos(f * (float)Math.PI / 180.0f) * this.speedMultiplier * (double)entitylivingbase.field_70701_bs * (double)0.05f;
            }
            if ((d2 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y)) > 0.45) {
                d4 = 0.45 / d2;
                this.field_70159_w *= d4;
                this.field_70179_y *= d4;
                d2 = 0.45;
            }
            if (d2 > d10 && this.speedMultiplier < 0.45) {
                this.speedMultiplier += (0.45 - this.speedMultiplier) / 45.0;
                if (this.speedMultiplier > 0.45) {
                    this.speedMultiplier = 0.45;
                }
            } else {
                this.speedMultiplier -= (this.speedMultiplier - 0.09) / 45.0;
                if (this.speedMultiplier < 0.09) {
                    this.speedMultiplier = 0.09;
                }
            }
            for (int l = 0; l < 4; ++l) {
                int i1 = MathHelper.func_76128_c((double)(this.field_70165_t + ((double)(l % 2) - 0.5) * 0.8));
                j = MathHelper.func_76128_c((double)(this.field_70161_v + ((double)(l / 2) - 0.5) * 0.8));
                for (int j1 = 0; j1 < 2; ++j1) {
                    int k = MathHelper.func_76128_c((double)this.field_70163_u) + j1;
                    Block block = this.field_70170_p.func_147439_a(i1, k, j);
                    if (block == Blocks.field_150431_aC) {
                        this.field_70170_p.func_147468_f(i1, k, j);
                        this.field_70123_F = false;
                        continue;
                    }
                    if (block != Blocks.field_150392_bi) continue;
                    this.field_70170_p.func_147480_a(i1, k, j, true);
                    this.field_70123_F = false;
                }
            }
            if (this.field_70122_E) {
                this.field_70159_w *= 0.5;
                this.field_70181_x *= 0.5;
                this.field_70179_y *= 0.5;
            }
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)0.99f;
            this.field_70181_x *= (double)0.95f;
            this.field_70179_y *= (double)0.99f;
            this.field_70125_A = 0.0f;
            d4 = this.field_70177_z;
            d11 = this.field_70169_q - this.field_70165_t;
            d12 = this.field_70166_s - this.field_70161_v;
            if (d11 * d11 + d12 * d12 > 0.001) {
                d4 = (float)(Math.atan2(d12, d11) * 180.0 / Math.PI);
            }
            if ((d7 = MathHelper.func_76138_g((double)(d4 - (double)this.field_70177_z))) > 20.0) {
                d7 = 20.0;
            }
            if (d7 < -20.0) {
                d7 = -20.0;
            }
            this.field_70177_z = (float)((double)this.field_70177_z + d7);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            if (!this.field_70170_p.field_72995_K) {
                List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b((double)0.2f, 0.0, (double)0.2f));
                if (list != null && !list.isEmpty()) {
                    for (int k1 = 0; k1 < list.size(); ++k1) {
                        Entity entity = (Entity)list.get(k1);
                        if (entity == this.field_70153_n || !entity.func_70104_M() || !(entity instanceof EntityBoat)) continue;
                        entity.func_70108_f((Entity)this);
                    }
                }
                if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
                    this.field_70153_n = null;
                }
            }
        }
    }

    public void func_70043_V() {
        if (this.field_70153_n != null) {
            double d0 = Math.cos((double)this.field_70177_z * Math.PI / 180.0) * 0.4;
            double d1 = Math.sin((double)this.field_70177_z * Math.PI / 180.0) * 0.4;
            this.field_70153_n.func_70107_b(this.field_70165_t + d0, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
        }
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public boolean func_130002_c(EntityPlayer p_130002_1_) {
        if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != p_130002_1_) {
            return true;
        }
        if (!this.field_70170_p.field_72995_K) {
            p_130002_1_.func_70078_a((Entity)this);
        }
        return true;
    }

    protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        int j = MathHelper.func_76128_c((double)this.field_70163_u);
        int k = MathHelper.func_76128_c((double)this.field_70161_v);
        if (!p_70064_3_ && this.field_70170_p.func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151586_h && p_70064_1_ < 0.0) {
            this.field_70143_R = (float)((double)this.field_70143_R - p_70064_1_);
        }
    }

    public void func_70266_a(float p_70266_1_) {
        this.field_70180_af.func_75692_b(19, (Object)Float.valueOf(p_70266_1_));
    }

    public float func_70271_g() {
        return this.field_70180_af.func_111145_d(19);
    }

    public void func_70265_b(int p_70265_1_) {
        this.field_70180_af.func_75692_b(17, (Object)p_70265_1_);
    }

    public int func_70268_h() {
        return this.field_70180_af.func_75679_c(17);
    }

    public void func_70269_c(int p_70269_1_) {
        this.field_70180_af.func_75692_b(18, (Object)p_70269_1_);
    }

    public int func_70267_i() {
        return this.field_70180_af.func_75679_c(18);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70270_d(boolean p_70270_1_) {
        this.isBoatEmpty = p_70270_1_;
    }
}

