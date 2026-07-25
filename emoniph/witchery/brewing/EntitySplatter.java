/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

public class EntitySplatter
extends Entity {
    private int field_145788_c = -1;
    private int field_145786_d = -1;
    private int field_145787_e = -1;
    private Block field_145785_f;
    protected boolean inGround;
    public int throwableShake;
    private int ticksInGround;
    private int ticksInAir;
    private int effectID;
    private int color;
    private int level;

    public EntitySplatter(World world) {
        super(world);
        this.func_70105_a(0.25f, 0.25f);
    }

    public EntitySplatter(World world, double x, double y, double z, int effectID, int color, int level) {
        super(world);
        this.ticksInGround = 0;
        this.func_70105_a(0.25f, 0.25f);
        this.func_70107_b(x, y, z);
        this.field_70129_M = 0.0f;
        this.effectID = effectID;
        this.level = level;
        this.setColor(color);
        if (effectID == 1) {
            this.func_70015_d(1000);
        }
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
                if (!entity1.func_70067_L() || this.ticksInAir < 5 || entity instanceof EntitySplatter || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 0.3f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
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
        if (!this.field_70170_p.field_72995_K && mop != null) {
            Coord coord = new Coord(mop, new EntityPosition(this), true);
            switch (mop.field_72313_a) {
                case BLOCK: {
                    ForgeDirection side = ForgeDirection.getOrientation((int)mop.field_72310_e);
                    int x = mop.field_72311_b + side.offsetX;
                    int y = mop.field_72312_c + side.offsetY;
                    int z = mop.field_72309_d + side.offsetZ;
                    if (BlockUtil.isReplaceableBlock(this.field_70170_p, x, y, z, (EntityLivingBase)FakePlayerFactory.getMinecraft((WorldServer)((WorldServer)this.field_70170_p)))) {
                        this.field_70170_p.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);
                    }
                    if (this.level - 1 > 0) {
                        EntitySplatter.splatter(this.field_70170_p, coord, this.level - 1);
                    }
                    this.func_70106_y();
                    break;
                }
                case ENTITY: {
                    if (!(mop.field_72308_g instanceof EntityLivingBase)) break;
                    mop.field_72308_g.func_70015_d(5);
                    break;
                }
                case MISS: {
                    this.func_70106_y();
                }
            }
        }
    }

    public static void splatter(World world, Coord coord, int level) {
        if (!world.field_72995_K) {
            for (int i = 0; i < 3 + level; ++i) {
                EntitySplatter splatter = new EntitySplatter(world, 0.5 + (double)coord.x, 0.5 + (double)coord.y, 0.5 + (double)coord.z, 1, 0x990000, level);
                double maxSpeed = 0.1;
                double doubleSpeed = 0.2;
                splatter.func_70016_h(world.field_73012_v.nextDouble() * 0.2 - 0.1, world.field_73012_v.nextDouble() * 0.05 + 0.3, world.field_73012_v.nextDouble() * 0.2 - 0.1);
                EntityUtil.spawnEntityInWorld(world, splatter);
            }
        }
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        nbtRoot.func_74777_a("xTile", (short)this.field_145788_c);
        nbtRoot.func_74777_a("yTile", (short)this.field_145786_d);
        nbtRoot.func_74777_a("zTile", (short)this.field_145787_e);
        nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.field_145785_f));
        nbtRoot.func_74774_a("shake", (byte)this.throwableShake);
        nbtRoot.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        nbtRoot.func_74768_a("Color", this.color);
        nbtRoot.func_74768_a("Level", this.level);
        nbtRoot.func_74768_a("Effect", this.effectID);
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        this.field_145788_c = nbtRoot.func_74765_d("xTile");
        this.field_145786_d = nbtRoot.func_74765_d("yTile");
        this.field_145787_e = nbtRoot.func_74765_d("zTile");
        this.field_145785_f = Block.func_149729_e((int)(nbtRoot.func_74771_c("inTile") & 0xFF));
        this.throwableShake = nbtRoot.func_74771_c("shake") & 0xFF;
        this.inGround = nbtRoot.func_74771_c("inGround") == 1;
        this.effectID = nbtRoot.func_74762_e("Effect");
        this.level = nbtRoot.func_74762_e("Level");
        this.setColor(nbtRoot.func_74762_e("Color"));
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }
}

