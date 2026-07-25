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
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityDroplet
extends Entity {
    private int field_145788_c = -1;
    private int field_145786_d = -1;
    private int field_145787_e = -1;
    private Block field_145785_f;
    protected boolean inGround;
    public int throwableShake;
    private int ticksInGround;
    private int ticksInAir;
    private NBTTagCompound nbtBrew;
    private int color;

    public EntityDroplet(World world) {
        super(world);
        this.func_70105_a(0.25f, 0.25f);
    }

    public EntityDroplet(World world, double x, double y, double z, NBTTagCompound nbtBrew) {
        super(world);
        this.ticksInGround = 0;
        this.func_70105_a(0.25f, 0.25f);
        this.func_70107_b(x, y, z);
        this.field_70129_M = 0.0f;
        this.nbtBrew = nbtBrew;
        this.setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtBrew));
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(6, (Object)0);
    }

    protected void setColor(int color) {
        this.func_70096_w().func_75692_b(6, (Object)color);
    }

    public int getColor() {
        return this.func_70096_w().func_75679_c(6);
    }

    public NBTTagCompound getBrew() {
        return this.nbtBrew;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distSq) {
        double d1 = this.field_70121_D.func_72320_b() * 4.0;
        return distSq < (d1 *= 64.0) * d1;
    }

    public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
        float f2 = MathHelper.func_76133_a((double)(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_));
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)p_70186_8_;
        p_70186_3_ += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)p_70186_8_;
        p_70186_5_ += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)p_70186_8_;
        this.field_70159_w = p_70186_1_ *= (double)p_70186_7_;
        this.field_70181_x = p_70186_3_ *= (double)p_70186_7_;
        this.field_70179_y = p_70186_5_ *= (double)p_70186_7_;
        float f3 = MathHelper.func_76133_a((double)(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_));
        this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0 / Math.PI);
        this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0 / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double motionX, double motionY, double motionZ) {
        this.field_70159_w = motionX;
        this.field_70181_x = motionY;
        this.field_70179_y = motionZ;
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((double)(motionX * motionX + motionZ * motionZ));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(motionY, f) * 180.0 / Math.PI);
        }
    }

    public void func_70071_h_() {
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;
        this.field_70136_U = this.field_70161_v;
        super.func_70071_h_();
        if (this.throwableShake > 0) {
            --this.throwableShake;
        }
        if (this.inGround) {
            if (this.field_70170_p.func_147439_a(this.field_145788_c, this.field_145786_d, this.field_145787_e) == this.field_145785_f) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.func_70106_y();
                }
                return;
            }
            this.inGround = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }
        Vec3 vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        Vec3 vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
        MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
        vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
        if (movingobjectposition != null) {
            vec31 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
        }
        if (!this.field_70170_p.field_72995_K) {
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (int j = 0; j < list.size(); ++j) {
                double d1;
                float f;
                AxisAlignedBB axisalignedbb;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(j);
                if (!entity1.func_70067_L() || this.ticksInAir < 5 || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 0.3f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }
        if (movingobjectposition != null) {
            if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.field_150427_aO) {
                this.func_70063_aa();
            } else {
                this.onImpact(movingobjectposition);
            }
        }
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        float f1 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
        this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
        this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f1) * 180.0 / Math.PI);
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
        float f2 = 0.99f;
        float f3 = this.getGravityVelocity();
        if (this.func_70090_H()) {
            for (int i = 0; i < 4; ++i) {
                float f4 = 0.25f;
                this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f4, this.field_70163_u - this.field_70181_x * (double)f4, this.field_70161_v - this.field_70179_y * (double)f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }
            f2 = 0.8f;
        }
        this.field_70159_w *= (double)f2;
        this.field_70181_x *= (double)f2;
        this.field_70179_y *= (double)f2;
        this.field_70181_x -= (double)f3;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    protected float getGravityVelocity() {
        return 0.03f;
    }

    protected void onImpact(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72926_e(1016, MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), this.getColor());
            if (mop != null) {
                ModifiersEffect modifiers = new ModifiersEffect(1.0, 1.0, false, new EntityPosition(this), true, 1, EntityUtil.playerOrFake(this.field_70170_p, (EntityLivingBase)((EntityPlayer)null)));
                switch (mop.field_72313_a) {
                    case BLOCK: {
                        WitcheryBrewRegistry.INSTANCE.applyToBlock(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.getOrientation((int)mop.field_72310_e), 1, this.nbtBrew, modifiers);
                        break;
                    }
                    case ENTITY: {
                        if (!(mop.field_72308_g instanceof EntityLivingBase)) break;
                        WitcheryBrewRegistry.INSTANCE.applyToEntity(this.field_70170_p, (EntityLivingBase)mop.field_72308_g, this.nbtBrew, modifiers);
                        break;
                    }
                }
            }
        }
        this.func_70106_y();
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        nbtRoot.func_74777_a("xTile", (short)this.field_145788_c);
        nbtRoot.func_74777_a("yTile", (short)this.field_145786_d);
        nbtRoot.func_74777_a("zTile", (short)this.field_145787_e);
        nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.field_145785_f));
        nbtRoot.func_74774_a("shake", (byte)this.throwableShake);
        nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        if (this.nbtBrew != null) {
            nbtRoot.func_74782_a("Brew", this.nbtBrew.func_74737_b());
        }
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        this.field_145788_c = nbtRoot.func_74765_d("xTile");
        this.field_145786_d = nbtRoot.func_74765_d("yTile");
        this.field_145787_e = nbtRoot.func_74765_d("zTile");
        this.field_145785_f = Block.func_149729_e((int)(nbtRoot.func_74771_c("inTile") & 0xFF));
        this.throwableShake = nbtRoot.func_74771_c("shake") & 0xFF;
        boolean bl = this.inGround = nbtRoot.func_74771_c("inGround") == 1;
        if (nbtRoot.func_150297_b("Brew", 10)) {
            this.nbtBrew = (NBTTagCompound)nbtRoot.func_74775_l("Brew").func_74737_b();
            this.setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtRoot));
        }
        if (this.nbtBrew == null) {
            this.func_70106_y();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }
}

