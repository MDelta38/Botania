/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S2BPacketChangeGameState
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.damagesource.DamageSourceIndirectThaumcraftEntity;

public class EntityPrimalArrow
extends EntityArrow
implements IProjectile,
IEntityAdditionalSpawnData {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile = Blocks.field_150350_a;
    private int inData = 0;
    private boolean inGround = false;
    public int field_70252_j;
    private int ticksInAir = 0;
    private double damage = 2.1;
    public int shootingEntityId;
    private int knockbackStrength;
    public int type = 0;

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.field_70159_w);
        data.writeDouble(this.field_70181_x);
        data.writeDouble(this.field_70179_y);
        data.writeFloat(this.field_70177_z);
        data.writeFloat(this.field_70125_A);
        data.writeByte(this.type);
        data.writeInt(this.shootingEntityId);
    }

    public void readSpawnData(ByteBuf data) {
        this.field_70159_w = data.readDouble();
        this.field_70181_x = data.readDouble();
        this.field_70179_y = data.readDouble();
        this.field_70177_z = data.readFloat();
        this.field_70125_A = data.readFloat();
        this.field_70126_B = this.field_70177_z;
        this.field_70127_C = this.field_70125_A;
        this.type = data.readByte();
        this.shootingEntityId = data.readInt();
    }

    public EntityPrimalArrow(World par1World) {
        super(par1World);
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
    }

    public EntityPrimalArrow(World par1World, double par2, double par4, double par6) {
        super(par1World);
        this.field_70155_l = 10.0;
        this.func_70105_a(0.25f, 0.25f);
        this.func_70107_b(par2, par4, par6);
        this.field_70129_M = 0.0f;
    }

    public EntityPrimalArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3, int type) {
        super(par1World);
        this.field_70155_l = 10.0;
        this.field_70250_c = par2EntityLivingBase;
        this.type = type;
        this.field_70251_a = 0;
        this.shootingEntityId = this.field_70250_c.func_145782_y();
        this.func_70105_a(0.5f, 0.5f);
        this.func_70012_b(par2EntityLivingBase.field_70165_t, par2EntityLivingBase.field_70163_u + (double)par2EntityLivingBase.func_70047_e(), par2EntityLivingBase.field_70161_v, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        this.field_70163_u -= 0.10000000014901161;
        this.field_70161_v -= (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        Vec3 vec3d = par2EntityLivingBase.func_70676_i(1.0f);
        this.field_70165_t += vec3d.field_72450_a;
        this.field_70163_u += vec3d.field_72448_b;
        this.field_70161_v += vec3d.field_72449_c;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, par3 * 1.5f, 1.0f);
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
    }

    public void func_70071_h_() {
        Block i;
        super.func_70071_h_();
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0 / Math.PI);
        }
        if (!(i = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile)).isAir((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile)) {
            i.func_149719_a((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = i.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v))) {
                this.inGround = true;
            }
        }
        if (this.field_70249_b > 0) {
            --this.field_70249_b;
        }
        if (this.inGround) {
            Block j = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
            int k = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (j == this.inTile && k == this.inData) {
                ++this.field_70252_j;
                if (this.field_70252_j == 100) {
                    this.func_70106_y();
                }
            } else {
                this.inGround = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70252_j = 0;
                this.ticksInAir = 0;
            }
        } else {
            float f1;
            int l;
            ++this.ticksInAir;
            Vec3 vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3, vec31, false, true, false);
            vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (movingobjectposition != null) {
                vec31 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
            }
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (l = 0; l < list.size(); ++l) {
                double d1;
                AxisAlignedBB axisalignedbb1;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(l);
                if (!entity1.func_70067_L() || entity1.func_145782_y() == this.shootingEntityId && this.ticksInAir < 5 || (movingobjectposition1 = (axisalignedbb1 = entity1.field_70121_D.func_72314_b((double)(f1 = 0.3f), (double)f1, (double)f1)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
                if (entityplayer.field_71075_bZ.field_75102_a || this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer)) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                if (movingobjectposition.field_72308_g != null) {
                    if (this.inflictDamage(movingobjectposition)) {
                        if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                            float f3;
                            EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
                            if (this.knockbackStrength > 0 && (f3 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                                movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)f3, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)f3);
                            }
                            if (this.field_70250_c != null && this.field_70250_c instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a((EntityLivingBase)entitylivingbase, (Entity)this.field_70250_c);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)((EntityLivingBase)this.field_70250_c), (Entity)entitylivingbase);
                            }
                            if (this.field_70250_c != null && movingobjectposition.field_72308_g != this.field_70250_c && movingobjectposition.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                        if (!(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                            this.func_70106_y();
                        }
                    } else {
                        this.field_70159_w *= (double)-0.1f;
                        this.field_70181_x *= (double)-0.1f;
                        this.field_70179_y *= (double)-0.1f;
                        this.field_70177_z += 180.0f;
                        this.field_70126_B += 180.0f;
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = movingobjectposition.field_72311_b;
                    this.yTile = movingobjectposition.field_72312_c;
                    this.zTile = movingobjectposition.field_72309_d;
                    this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                    this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                    this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
                    this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
                    this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
                    float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    this.field_70165_t -= this.field_70159_w / (double)f2 * (double)0.05f;
                    this.field_70163_u -= this.field_70181_x / (double)f2 * (double)0.05f;
                    this.field_70161_v -= this.field_70179_y / (double)f2 * (double)0.05f;
                    this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.field_70249_b = 7;
                    this.func_70243_d(false);
                    if (this.inTile.isAir((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile)) {
                        this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                    }
                }
            }
            if (this.func_70241_g()) {
                for (l = 0; l < 4; ++l) {
                    this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * (double)l / 4.0, this.field_70163_u + this.field_70181_x * (double)l / 4.0, this.field_70161_v + this.field_70179_y * (double)l / 4.0, -this.field_70159_w, -this.field_70181_x + 0.2, -this.field_70179_y);
                }
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f2) * 180.0 / Math.PI);
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
            float f4 = 0.99f;
            f1 = 0.05f;
            if (this.func_70090_H()) {
                for (int j1 = 0; j1 < 4; ++j1) {
                    float f3 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f3, this.field_70163_u - this.field_70181_x * (double)f3, this.field_70161_v - this.field_70179_y * (double)f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                f4 = 0.8f;
            }
            this.field_70159_w *= (double)f4;
            this.field_70181_x *= (double)f4;
            this.field_70179_y *= (double)f4;
            this.field_70181_x -= (double)f1;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_145775_I();
        }
    }

    public boolean inflictDamage(MovingObjectPosition movingobjectposition) {
        int fire;
        float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
        int i1 = MathHelper.func_76143_f((double)((double)f2 * this.func_70242_d()));
        int n = fire = this.func_70027_ad() && this.type != 2 ? 5 : 0;
        if (this.func_70241_g()) {
            i1 += this.field_70146_Z.nextInt(i1 / 2 + 2);
        }
        DamageSource damagesource = null;
        switch (this.type) {
            case 0: {
                if (this.field_70250_c == null) {
                    damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", (Entity)this, (Entity)this).func_76348_h().func_82726_p().func_76349_b();
                    break;
                }
                damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", (Entity)this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
                break;
            }
            case 1: {
                fire += 5;
                if (this.field_70250_c == null) {
                    damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", (Entity)this, (Entity)this).func_76361_j().func_76349_b();
                    break;
                }
                damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", (Entity)this, this.field_70250_c).func_76361_j().func_76349_b();
                break;
            }
            case 4: {
                damagesource = this.field_70250_c == null ? new DamageSourceIndirectThaumcraftEntity("orderarrow", (Entity)this, (Entity)this).func_76348_h().func_82726_p().func_76349_b() : new DamageSourceIndirectThaumcraftEntity("orderarrow", (Entity)this, this.field_70250_c).func_76348_h().func_82726_p().func_76349_b();
                if (!(movingobjectposition.field_72308_g instanceof EntityLivingBase)) break;
                ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 200, 4));
                break;
            }
            case 2: {
                if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                    ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 4));
                }
            }
            case 5: {
                if (this.type == 5 && movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                    ((EntityLivingBase)movingobjectposition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100));
                }
            }
            default: {
                damagesource = this.field_70250_c == null ? new EntityDamageSourceIndirect("arrow", (Entity)this, (Entity)this).func_76349_b() : new EntityDamageSourceIndirect("arrow", (Entity)this, this.field_70250_c).func_76349_b();
            }
        }
        if (fire > 0 && !(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
            movingobjectposition.field_72308_g.func_70015_d(fire);
        }
        return movingobjectposition.field_72308_g.func_70097_a(damagesource, (float)i1);
    }

    public double func_70242_d() {
        switch (this.type) {
            case 3: {
                return this.damage * 1.5;
            }
            case 4: {
                return this.damage * 0.8;
            }
            case 5: {
                return this.damage * 0.8;
            }
        }
        return this.damage;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("type", (byte)this.type);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.type = par1NBTTagCompound.func_74771_c("type");
    }
}

