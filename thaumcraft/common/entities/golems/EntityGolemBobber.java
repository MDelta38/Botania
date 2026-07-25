/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class EntityGolemBobber
extends Entity
implements IEntityAdditionalSpawnData {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int inData = 0;
    private boolean inGround = false;
    private boolean inBlock = false;
    public EntityGolemBase fisher = null;
    private int field_146045_ax;
    private int field_146040_ay;
    private float field_146054_aA;

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.field_70159_w);
        data.writeDouble(this.field_70181_x);
        data.writeDouble(this.field_70179_y);
        data.writeInt(this.fisher != null ? this.fisher.func_145782_y() : -1);
    }

    public void readSpawnData(ByteBuf data) {
        this.field_70159_w = data.readDouble();
        this.field_70181_x = data.readDouble();
        this.field_70179_y = data.readDouble();
        int fid = data.readInt();
        if (fid >= 0) {
            this.fisher = (EntityGolemBase)this.field_70170_p.func_73045_a(fid);
        }
    }

    public EntityGolemBobber(World par1World) {
        super(par1World);
        this.func_70105_a(0.25f, 0.25f);
        this.field_70158_ak = true;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
    }

    public EntityGolemBobber(World par1World, EntityGolemBase par2EntityLiving, int x, int y, int z) {
        super(par1World);
        this.func_70105_a(0.25f, 0.25f);
        this.fisher = par2EntityLiving;
        this.field_70158_ak = true;
        double d1 = (double)x + 0.5 - this.fisher.field_70165_t;
        double d3 = (double)(y + 1) - this.fisher.field_70163_u;
        double d5 = (double)z + 0.5 - this.fisher.field_70161_v;
        double d7 = MathHelper.func_76133_a((double)(d1 * d1 + d3 * d3 + d5 * d5));
        double d9 = 0.1;
        this.field_70159_w = d1 * d9;
        this.field_70181_x = d3 * d9 + (double)MathHelper.func_76133_a((double)d7) * 0.08;
        this.field_70179_y = d5 * d9;
        this.func_70107_b(this.fisher.field_70165_t, this.fisher.field_70163_u, this.fisher.field_70161_v);
        this.field_70129_M = 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double par1) {
        double d1 = this.field_70121_D.func_72320_b() * 4.0;
        return par1 < (d1 *= 64.0) * d1;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.fisher == null || !this.fisher.func_70089_S()) {
                this.func_70106_y();
                return;
            }
            if (this.field_70146_Z.nextFloat() < 0.02f) {
                ((WorldServer)this.field_70170_p).func_147487_a("splash", this.field_70165_t + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat(), this.field_70163_u + (double)this.field_70146_Z.nextFloat(), this.field_70161_v + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat(), 2 + this.field_70146_Z.nextInt(2), (double)0.1f, 0.0, (double)0.1f, 0.0);
            }
        }
        if (this.field_70173_aa++ > 4000) {
            this.func_70106_y();
            return;
        }
        if (this.inBlock) {
            this.inBlock = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
        }
        Vec3 vec31 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        Vec3 vec3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
        MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec31, vec3);
        vec31 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        vec3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
        if (movingobjectposition != null) {
            vec3 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
            if (movingobjectposition.field_72308_g == null) {
                this.inBlock = true;
                if (this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d).func_149688_o() != Material.field_151586_h) {
                    this.func_70106_y();
                }
            }
        }
        if (!this.inBlock) {
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            float f5 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f5) * 180.0 / Math.PI);
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
            float f6 = 0.92f;
            if (this.field_70122_E || this.field_70123_F) {
                f6 = 0.5f;
            }
            int b0 = 5;
            double d10 = 0.0;
            for (int j = 0; j < b0; ++j) {
                double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(j + 0) / (double)b0 - 0.125 + 0.125;
                double d4 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(j + 1) / (double)b0 - 0.125 + 0.125;
                AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a((double)this.field_70121_D.field_72340_a, (double)d3, (double)this.field_70121_D.field_72339_c, (double)this.field_70121_D.field_72336_d, (double)d4, (double)this.field_70121_D.field_72334_f);
                if (!this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h)) continue;
                d10 += 1.0 / (double)b0;
            }
            if (!this.field_70170_p.field_72995_K && d10 > 0.0) {
                WorldServer worldserver = (WorldServer)this.field_70170_p;
                int k = 1;
                if (this.field_70146_Z.nextFloat() < 0.25f && this.field_70170_p.func_72951_B(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u) + 1, MathHelper.func_76128_c((double)this.field_70161_v))) {
                    k = 2;
                }
                if (this.field_70146_Z.nextFloat() < 0.5f && !this.field_70170_p.func_72937_j(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u) + 1, MathHelper.func_76128_c((double)this.field_70161_v))) {
                    --k;
                }
                if (this.field_146045_ax > 0) {
                    --this.field_146045_ax;
                    if (this.field_146045_ax <= 0) {
                        this.field_146040_ay = 0;
                    }
                } else if (this.field_146040_ay > 0) {
                    this.field_146040_ay -= k;
                    float f1 = 0.15f;
                    if (this.field_146040_ay < 20) {
                        f1 = (float)((double)f1 + (double)(20 - this.field_146040_ay) * 0.05);
                    } else if (this.field_146040_ay < 40) {
                        f1 = (float)((double)f1 + (double)(40 - this.field_146040_ay) * 0.02);
                    } else if (this.field_146040_ay < 60) {
                        f1 = (float)((double)f1 + (double)(60 - this.field_146040_ay) * 0.01);
                    }
                    if (this.field_70146_Z.nextFloat() < f1) {
                        float f7 = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)0.0f, (float)360.0f) * ((float)Math.PI / 180);
                        float f2 = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)25.0f, (float)60.0f);
                        double d11 = this.field_70165_t + (double)(MathHelper.func_76126_a((float)f7) * f2 * 0.1f);
                        double d5 = (float)MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b) + 1.0f;
                        double d6 = this.field_70161_v + (double)(MathHelper.func_76134_b((float)f7) * f2 * 0.1f);
                        worldserver.func_147487_a("splash", d11, d5, d6, 2 + this.field_70146_Z.nextInt(2), (double)0.1f, 0.0, (double)0.1f, 0.0);
                    }
                    if (this.field_146040_ay <= 0) {
                        this.field_146054_aA = MathHelper.func_151240_a((Random)this.field_70146_Z, (float)0.0f, (float)360.0f);
                    }
                }
                if (this.field_146045_ax > 0) {
                    this.field_70181_x -= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2;
                }
            }
            double d2 = d10 * 2.0 - 1.0;
            this.field_70181_x += (double)0.04f * d2;
            if (d10 > 0.0) {
                f6 = (float)((double)f6 * 0.9);
                this.field_70181_x *= 0.8;
            }
            this.field_70159_w *= (double)f6;
            this.field_70181_x *= (double)f6;
            this.field_70179_y *= (double)f6;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }
}

