/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySpellEffect
extends Entity {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private boolean inGround;
    public EntityLivingBase shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    private int lifetime = -1;
    private int effectLevel;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    private int effectID;

    public EntitySpellEffect(World par1World) {
        super(par1World);
        this.func_70105_a(0.5f, 0.5f);
        this.field_70145_X = true;
    }

    public EntitySpellEffect setLifeTime(int ticks) {
        this.lifetime = ticks;
        return this;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(6, (Object)0);
        this.field_70180_af.func_75682_a(15, (Object)0);
    }

    public void setShooter(EntityLivingBase entity) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(15, (Object)entity.func_145782_y());
        }
    }

    public int getShooterID() {
        int id = this.field_70180_af.func_75679_c(15);
        return id;
    }

    public boolean isShooter(Entity entity) {
        int us;
        int idOther = entity.func_145782_y();
        return idOther == (us = this.getShooterID());
    }

    public void setEffectID(int effectID) {
        this.effectID = effectID;
        this.func_70096_w().func_75692_b(6, (Object)effectID);
    }

    public int getEffectID() {
        return this.func_70096_w().func_75679_c(6);
    }

    public int getEffectLevel() {
        return this.effectLevel;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double par1) {
        double d1 = this.field_70121_D.func_72320_b() * 4.0;
        return par1 < (d1 *= 64.0) * d1;
    }

    public EntitySpellEffect(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, SymbolEffect effect, int effectLevel) {
        super(par1World);
        this.effectLevel = effectLevel;
        this.func_70105_a(1.0f, 1.0f);
        this.func_70012_b(par2, par4, par6, this.field_70177_z, this.field_70125_A);
        this.func_70107_b(par2, par4, par6);
        double d6 = MathHelper.func_76133_a((double)(par8 * par8 + par10 * par10 + par12 * par12));
        this.accelerationX = par8 / d6 * 0.1;
        this.accelerationY = par10 / d6 * 0.1;
        this.accelerationZ = par12 / d6 * 0.1;
        this.setEffectID(effect.getEffectID());
    }

    public EntitySpellEffect(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7, SymbolEffect effect, int effectLevel) {
        super(par1World);
        this.shootingEntity = par2EntityLivingBase;
        this.effectLevel = effectLevel;
        this.func_70105_a(1.0f, 1.0f);
        this.func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u, par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        double d3 = MathHelper.func_76133_a((double)((par3 += this.field_70146_Z.nextGaussian() * 0.4) * par3 + (par5 += this.field_70146_Z.nextGaussian() * 0.4) * par5 + (par7 += this.field_70146_Z.nextGaussian() * 0.4) * par7));
        this.accelerationX = par3 / d3 * 0.1;
        this.accelerationY = par5 / d3 * 0.1;
        this.accelerationZ = par7 / d3 * 0.1;
        this.setEffectID(effect.getEffectID());
    }

    public void func_70071_h_() {
        if (!this.field_70170_p.field_72995_K && (this.shootingEntity != null && this.shootingEntity.field_70128_L || !this.field_70170_p.func_72899_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v))) {
            this.func_70106_y();
        } else {
            SymbolEffect effect;
            super.func_70071_h_();
            if (this.inGround) {
                Block i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                if (i == this.inTile) {
                    ++this.ticksAlive;
                    if (this.ticksAlive == 600) {
                        this.func_70106_y();
                    }
                    return;
                }
                this.inGround = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            } else {
                ++this.ticksInAir;
                if (this.ticksInAir == 200) {
                    this.func_70106_y();
                }
            }
            Vec3 vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
            vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (movingobjectposition != null) {
                vec31 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
            }
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            boolean remote = this.field_70170_p.field_72995_K;
            for (int j = 0; j < list.size(); ++j) {
                double d1;
                float f;
                AxisAlignedBB axisalignedbb;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(j);
                if (!entity1.func_70067_L() || this.isShooter(entity1) && this.ticksInAir < 25 || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 0.3f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null || this.lifetime != -1 && Math.max(--this.lifetime, 0) == 0) {
                this.onImpact(movingobjectposition);
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            float f1 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70177_z = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0 / Math.PI) + 90.0f;
            this.field_70125_A = (float)(Math.atan2(f1, this.field_70181_x) * 180.0 / Math.PI) - 90.0f;
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
            float f2 = this.getMotionFactor();
            if (this.func_70090_H()) {
                for (int k = 0; k < 4; ++k) {
                    float f3 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f3, this.field_70163_u - this.field_70181_x * (double)f3, this.field_70161_v - this.field_70179_y * (double)f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                f2 = 0.8f;
            }
            if ((effect = EffectRegistry.instance().getEffect(this.getEffectID())) == null) {
                this.func_70106_y();
            } else {
                if (effect.fallsToEarth() && this.getEffectLevel() == 1) {
                    this.accelerationX *= 0.8;
                    this.accelerationY *= 0.8;
                    this.accelerationZ *= 0.8;
                    this.field_70159_w += this.accelerationX;
                    this.field_70181_x += this.accelerationY;
                    this.field_70179_y += this.accelerationZ;
                    this.field_70181_x -= 0.05;
                } else {
                    this.field_70159_w += this.accelerationX;
                    this.field_70181_x += this.accelerationY;
                    this.field_70179_y += this.accelerationZ;
                    this.field_70159_w *= (double)f2;
                    this.field_70181_x *= (double)f2;
                    this.field_70179_y *= (double)f2;
                }
                this.field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.MOB_SPELL.toString() : ParticleEffect.SLIME.toString(), this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, 0.0, 0.0, 0.0);
                if (effect.isCurse()) {
                    this.field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.FLAME.toString() : ParticleEffect.SLIME.toString(), this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, 0.0, 0.0, 0.0);
                }
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            }
        }
    }

    protected float getMotionFactor() {
        return 0.95f;
    }

    protected void onImpact(MovingObjectPosition mop) {
        SymbolEffect effect;
        if (!this.field_70170_p.field_72995_K && (effect = EffectRegistry.instance().getEffect(this.getEffectID())) != null && effect instanceof SymbolEffectProjectile) {
            if (effect.isCurse()) {
                ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_HIT, this, 1.0, 1.0, 16);
            } else {
                ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_SMALL, this, 1.0, 1.0, 16);
            }
            ((SymbolEffectProjectile)effect).onCollision(this.field_70170_p, this.shootingEntity, mop, this);
        }
        this.func_70106_y();
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        nbtRoot.func_74777_a("xTile", (short)this.xTile);
        nbtRoot.func_74777_a("yTile", (short)this.yTile);
        nbtRoot.func_74777_a("zTile", (short)this.zTile);
        nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        nbtRoot.func_74782_a("direction", (NBTBase)this.func_70087_a(new double[]{this.field_70159_w, this.field_70181_x, this.field_70179_y}));
        nbtRoot.func_74768_a("EffectID", this.effectID);
        nbtRoot.func_74768_a("lifetime", this.lifetime);
        nbtRoot.func_74768_a("effectLevel", this.effectLevel);
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        this.xTile = nbtRoot.func_74765_d("xTile");
        this.yTile = nbtRoot.func_74765_d("yTile");
        this.zTile = nbtRoot.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(nbtRoot.func_74771_c("inTile") & 0xFF));
        this.inGround = nbtRoot.func_74771_c("inGround") == 1;
        this.lifetime = nbtRoot.func_74764_b("lifetime") ? nbtRoot.func_74762_e("lifetime") : -1;
        if (nbtRoot.func_74764_b("direction") && nbtRoot.func_74764_b("EffectID")) {
            this.effectID = nbtRoot.func_74762_e("EffectID");
            this.setEffectID(this.effectID);
            NBTTagList nbttaglist = nbtRoot.func_150295_c("direction", 6);
            this.field_70159_w = nbttaglist.func_150309_d(0);
            this.field_70181_x = nbttaglist.func_150309_d(1);
            this.field_70179_y = nbttaglist.func_150309_d(2);
        } else {
            this.func_70106_y();
        }
        this.effectLevel = Math.max(nbtRoot.func_74762_e("effectLevel"), 1);
    }

    public boolean func_70067_L() {
        return true;
    }

    public float func_70111_Y() {
        return 1.0f;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        boolean canDeflect;
        if (this.func_85032_ar()) {
            return false;
        }
        this.func_70018_K();
        Entity entity = par1DamageSource.func_76346_g();
        boolean bl = canDeflect = entity != null && this.getEffectID() != 5 && entity instanceof EntityPlayer && ((EntityPlayer)entity).func_70694_bm() != null && ((EntityPlayer)entity).func_70694_bm().func_77973_b() == Witchery.Items.MYSTIC_BRANCH;
        if (canDeflect) {
            Vec3 vec3 = par1DamageSource.func_76346_g().func_70040_Z();
            if (vec3 != null) {
                this.field_70159_w = vec3.field_72450_a;
                this.field_70181_x = vec3.field_72448_b;
                this.field_70179_y = vec3.field_72449_c;
                this.accelerationX = this.field_70159_w * 0.1;
                this.accelerationY = this.field_70181_x * 0.1;
                this.accelerationZ = this.field_70179_y * 0.1;
            }
            if (par1DamageSource.func_76346_g() instanceof EntityLivingBase) {
                this.shootingEntity = (EntityLivingBase)par1DamageSource.func_76346_g();
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        return 0xF000F0;
    }
}

