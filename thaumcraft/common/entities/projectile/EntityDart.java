/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDart
extends EntityArrow
implements IProjectile,
IEntityAdditionalSpawnData {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int inData = 0;
    private boolean inGround = false;
    private int ticksInGround;
    private int ticksInAir = 0;
    private double damage = 1.0;
    private int knockbackStrength;
    private boolean first = true;

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.field_70159_w);
        data.writeDouble(this.field_70181_x);
        data.writeDouble(this.field_70179_y);
        data.writeFloat(this.field_70177_z);
        data.writeFloat(this.field_70125_A);
    }

    public void readSpawnData(ByteBuf data) {
        this.field_70159_w = data.readDouble();
        this.field_70181_x = data.readDouble();
        this.field_70179_y = data.readDouble();
        this.field_70177_z = data.readFloat();
        this.field_70125_A = data.readFloat();
    }

    public EntityDart(World par1World) {
        super(par1World);
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
    }

    public EntityDart(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving, float par4, float par5) {
        super(par1World);
        this.field_70155_l = 10.0;
        this.field_70250_c = par2EntityLiving;
        this.field_70163_u = par2EntityLiving.field_70163_u + (double)par2EntityLiving.func_70047_e() - (double)0.1f;
        double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t;
        double var8 = par3EntityLiving.field_70163_u + (double)par3EntityLiving.func_70047_e() - (double)0.7f - this.field_70163_u;
        double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v;
        double var12 = MathHelper.func_76133_a((double)(var6 * var6 + var10 * var10));
        if (var12 >= 1.0E-7) {
            float var14 = (float)(Math.atan2(var10, var6) * 180.0 / Math.PI) - 90.0f;
            float var15 = (float)(-(Math.atan2(var8, var12) * 180.0 / Math.PI));
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.func_70012_b(par2EntityLiving.field_70165_t + var16 / 5.0, this.field_70163_u, par2EntityLiving.field_70161_v + var18 / 5.0, var14, var15);
            this.field_70129_M = 0.0f;
            float var20 = (float)var12 * 0.2f;
            this.func_70186_c(var6, var8 + (double)var20, var10, par4, par5);
        }
    }

    public void func_70071_h_() {
        if (this.first && this.field_70170_p.field_72995_K) {
            this.first = false;
            for (int a = 0; a < 5; ++a) {
                this.field_70170_p.func_72869_a("smoke", this.field_70165_t - this.field_70159_w / 1.5, this.field_70163_u - this.field_70181_x / 1.5, this.field_70161_v - this.field_70179_y / 1.5, this.field_70159_w / 9.0 + this.field_70146_Z.nextGaussian() * 0.01, this.field_70181_x / 9.0 + this.field_70146_Z.nextGaussian() * 0.01, this.field_70179_y / 9.0 + this.field_70146_Z.nextGaussian() * 0.01);
            }
        }
        super.func_70071_h_();
    }
}

