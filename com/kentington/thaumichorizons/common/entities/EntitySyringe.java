/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S2BPacketChangeGameState
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemSyringeInjection;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntitySyringe
extends Entity
implements IProjectile,
IEntityAdditionalSpawnData {
    public int color = Color.RED.getRGB();
    public NBTTagCompound effects;
    private boolean inGround;
    private int field_145791_d = -1;
    private int field_145792_e = -1;
    private int field_145789_f = -1;
    private Block field_145790_g;
    private int inData;
    private int ticksInGround;
    private int ticksInAir;
    private int knockbackStrength;
    private EntityLivingBase shootingEntity;
    private int canBePickedUp;
    private byte arrowShake;
    private double damage = 1.5;

    public EntitySyringe(World p_i1753_1_) {
        super(p_i1753_1_);
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
    }

    public EntitySyringe(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
        super(p_i1754_1_);
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
        this.func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
        this.field_70129_M = 0.0f;
    }

    public EntitySyringe(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_, NBTTagCompound tag) {
        super(p_i1755_1_);
        this.field_70155_l = 10.0;
        this.shootingEntity = p_i1755_2_;
        this.effects = (NBTTagCompound)tag.func_74737_b();
        this.color = this.effects.func_74762_e("color");
        if (p_i1755_2_ instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.field_70163_u = p_i1755_2_.field_70163_u + (double)p_i1755_2_.func_70047_e() - (double)0.1f;
        double d0 = p_i1755_3_.field_70165_t - p_i1755_2_.field_70165_t;
        double d1 = p_i1755_3_.field_70121_D.field_72338_b + (double)(p_i1755_3_.field_70131_O / 3.0f) - this.field_70163_u;
        double d2 = p_i1755_3_.field_70161_v - p_i1755_2_.field_70161_v;
        double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
        if (d3 >= 1.0E-7) {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.func_70012_b(p_i1755_2_.field_70165_t + d4, this.field_70163_u, p_i1755_2_.field_70161_v + d5, f2, f3);
            this.field_70129_M = 0.0f;
            float f4 = (float)d3 * 0.2f;
            this.func_70186_c(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
        }
    }

    public EntitySyringe(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_, NBTTagCompound tag) {
        super(p_i1756_1_);
        this.field_70155_l = 10.0;
        this.shootingEntity = p_i1756_2_;
        this.effects = (NBTTagCompound)tag.func_74737_b();
        this.color = this.effects.func_74762_e("color");
        if (p_i1756_2_ instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.func_70105_a(0.5f, 0.5f);
        this.func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + (double)p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, p_i1756_2_.field_70177_z, p_i1756_2_.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        this.field_70163_u -= (double)0.1f;
        this.field_70161_v -= (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5f, 1.0f);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        p_70014_1_.func_74782_a("effects", (NBTBase)this.effects);
        p_70014_1_.func_74777_a("xTile", (short)this.field_145791_d);
        p_70014_1_.func_74777_a("yTile", (short)this.field_145792_e);
        p_70014_1_.func_74777_a("zTile", (short)this.field_145789_f);
        p_70014_1_.func_74777_a("life", (short)this.ticksInGround);
        p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.field_145790_g));
        p_70014_1_.func_74774_a("inData", (byte)this.inData);
        p_70014_1_.func_74774_a("shake", this.arrowShake);
        p_70014_1_.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        p_70014_1_.func_74774_a("pickup", (byte)this.canBePickedUp);
        p_70014_1_.func_74780_a("damage", this.damage);
        p_70014_1_.func_74768_a("color", this.color);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        this.effects = p_70037_1_.func_74775_l("effects");
        this.field_145791_d = p_70037_1_.func_74765_d("xTile");
        this.field_145792_e = p_70037_1_.func_74765_d("yTile");
        this.field_145789_f = p_70037_1_.func_74765_d("zTile");
        this.ticksInGround = p_70037_1_.func_74765_d("life");
        this.field_145790_g = Block.func_149729_e((int)(p_70037_1_.func_74771_c("inTile") & 0xFF));
        this.inData = p_70037_1_.func_74771_c("inData") & 0xFF;
        this.arrowShake = (byte)(p_70037_1_.func_74771_c("shake") & 0xFF);
        boolean bl = this.inGround = p_70037_1_.func_74771_c("inGround") == 1;
        if (p_70037_1_.func_150297_b("damage", 99)) {
            this.damage = p_70037_1_.func_74769_h("damage");
        }
        if (p_70037_1_.func_150297_b("pickup", 99)) {
            this.canBePickedUp = p_70037_1_.func_74771_c("pickup");
        } else if (p_70037_1_.func_150297_b("player", 99)) {
            this.canBePickedUp = p_70037_1_.func_74767_n("player") ? 1 : 0;
        }
        this.color = p_70037_1_.func_74762_e("color");
    }

    void applyEffects(Entity entityHit) {
        if (entityHit instanceof EntityLivingBase && !this.field_70170_p.field_72995_K) {
            EntityLivingBase ent = (EntityLivingBase)entityHit;
            ItemStack psuedoPotion = new ItemStack(ThaumicHorizons.itemSyringeInjection);
            psuedoPotion.func_77982_d(this.effects);
            List list = ((ItemSyringeInjection)ThaumicHorizons.itemSyringeInjection).func_77832_l(psuedoPotion);
            if (list != null) {
                for (PotionEffect potioneffect : list) {
                    ent.func_70690_d(new PotionEffect(potioneffect));
                }
            }
        }
    }

    public void func_70100_b_(EntityPlayer p_70100_1_) {
        if (!this.field_70170_p.field_72995_K && this.inGround && this.arrowShake <= 0) {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && p_70100_1_.field_71075_bZ.field_75098_d;
            ItemStack psuedoPotion = new ItemStack(ThaumicHorizons.itemSyringeInjection);
            psuedoPotion.func_77982_d(this.effects);
            if (this.canBePickedUp == 1 && !p_70100_1_.field_71071_by.func_70441_a(psuedoPotion)) {
                flag = false;
            }
            if (flag) {
                this.func_85030_a("random.pop", 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                p_70100_1_.func_71001_a((Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, psuedoPotion), 1);
                this.func_70106_y();
            }
        }
    }

    public void func_70071_h_() {
        Block block;
        super.func_70071_h_();
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0 / Math.PI);
        }
        if ((block = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f)).func_149688_o() != Material.field_151579_a) {
            block.func_149719_a((IBlockAccess)this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            this.arrowShake = (byte)(this.arrowShake - 1);
        }
        if (this.inGround) {
            int j = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
            if (block == this.field_145790_g && j == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.func_70106_y();
                }
            } else {
                this.inGround = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            float f1;
            int i;
            ++this.ticksInAir;
            Vec3 vec31 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 vec3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
            vec31 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            vec3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (movingobjectposition != null) {
                vec3 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
            }
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (i = 0; i < list.size(); ++i) {
                double d1;
                AxisAlignedBB axisalignedbb1;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(i);
                if (!entity1.func_70067_L() || entity1 == this.shootingEntity && this.ticksInAir < 5 || (movingobjectposition1 = (axisalignedbb1 = entity1.field_70121_D.func_72314_b((double)(f1 = 0.3f), (double)f1, (double)f1)).func_72327_a(vec31, vec3)) == null || !((d1 = vec31.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
                if (entityplayer.field_71075_bZ.field_75102_a || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).func_96122_a(entityplayer)) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                if (movingobjectposition.field_72308_g != null) {
                    float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    int k = MathHelper.func_76143_f((double)((double)f2 * this.getDamage()));
                    if (this.getIsCritical()) {
                        k += this.field_70146_Z.nextInt(k / 2 + 2);
                    }
                    DamageSource damagesource = null;
                    damagesource = this.shootingEntity == null ? DamageSource.func_76356_a((Entity)this, (Entity)this) : DamageSource.func_76356_a((Entity)this, (Entity)this.shootingEntity);
                    if (this.func_70027_ad() && !(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                        movingobjectposition.field_72308_g.func_70015_d(5);
                    }
                    if (movingobjectposition.field_72308_g.func_70097_a(damagesource, (float)k)) {
                        if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                            float f4;
                            EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
                            if (!this.field_70170_p.field_72995_K) {
                                entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
                            }
                            if (this.knockbackStrength > 0 && (f4 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                                movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)f4, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)f4);
                            }
                            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a((EntityLivingBase)entitylivingbase, (Entity)this.shootingEntity);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)entitylivingbase);
                            }
                            if (this.shootingEntity != null && movingobjectposition.field_72308_g != this.shootingEntity && movingobjectposition.field_72308_g instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)this.shootingEntity).field_71135_a.func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                        if (!(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                            this.func_70106_y();
                            this.applyEffects(movingobjectposition.field_72308_g);
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
                    this.field_145791_d = movingobjectposition.field_72311_b;
                    this.field_145792_e = movingobjectposition.field_72312_c;
                    this.field_145789_f = movingobjectposition.field_72309_d;
                    this.field_145790_g = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.inData = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
                    this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
                    this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
                    this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
                    float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    this.field_70165_t -= this.field_70159_w / (double)f2 * (double)0.05f;
                    this.field_70163_u -= this.field_70181_x / (double)f2 * (double)0.05f;
                    this.field_70161_v -= this.field_70179_y / (double)f2 * (double)0.05f;
                    this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.arrowShake = (byte)7;
                    this.setIsCritical(false);
                    if (this.field_145790_g.func_149688_o() != Material.field_151579_a) {
                        this.field_145790_g.func_149670_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f, (Entity)this);
                    }
                }
            }
            if (this.getIsCritical()) {
                for (i = 0; i < 4; ++i) {
                    this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * (double)i / 4.0, this.field_70163_u + this.field_70181_x * (double)i / 4.0, this.field_70161_v + this.field_70179_y * (double)i / 4.0, -this.field_70159_w, -this.field_70181_x + 0.2, -this.field_70179_y);
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
            float f3 = 0.99f;
            f1 = 0.05f;
            if (this.func_70090_H()) {
                for (int l = 0; l < 4; ++l) {
                    float f4 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f4, this.field_70163_u - this.field_70181_x * (double)f4, this.field_70161_v - this.field_70179_y * (double)f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                f3 = 0.8f;
            }
            if (this.func_70026_G()) {
                this.func_70066_B();
            }
            this.field_70159_w *= (double)f3;
            this.field_70181_x *= (double)f3;
            this.field_70179_y *= (double)f3;
            this.field_70181_x -= (double)f1;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_145775_I();
        }
    }

    public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
        float f2 = MathHelper.func_76133_a((double)(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_));
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)p_70186_8_;
        p_70186_3_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)p_70186_8_;
        p_70186_5_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)p_70186_8_;
        this.field_70159_w = p_70186_1_ *= (double)p_70186_7_;
        this.field_70181_x = p_70186_3_ *= (double)p_70186_7_;
        this.field_70179_y = p_70186_5_ *= (double)p_70186_7_;
        float f3 = MathHelper.func_76133_a((double)(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_));
        this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0 / Math.PI);
        this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0 / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        this.field_70159_w = p_70016_1_;
        this.field_70181_x = p_70016_3_;
        this.field_70179_y = p_70016_5_;
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((double)(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, f) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A;
            this.field_70126_B = this.field_70177_z;
            this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.ticksInGround = 0;
        }
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(16, (Object)0);
    }

    protected boolean func_70041_e_() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public void setDamage(double p_70239_1_) {
        this.damage = p_70239_1_;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setKnockbackStrength(int p_70240_1_) {
        this.knockbackStrength = p_70240_1_;
    }

    public boolean func_70075_an() {
        return false;
    }

    public void setIsCritical(boolean p_70243_1_) {
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (p_70243_1_) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 & 0xFFFFFFFE)));
        }
    }

    public boolean getIsCritical() {
        byte b0 = this.field_70180_af.func_75683_a(16);
        return (b0 & 1) != 0;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.color);
        data.writeDouble(this.field_70159_w);
        data.writeDouble(this.field_70181_x);
        data.writeDouble(this.field_70179_y);
        data.writeFloat(this.field_70177_z);
        data.writeFloat(this.field_70125_A);
    }

    public void readSpawnData(ByteBuf data) {
        this.color = data.readInt();
        this.field_70159_w = data.readDouble();
        this.field_70181_x = data.readDouble();
        this.field_70179_y = data.readDouble();
        this.field_70177_z = data.readFloat();
        this.field_70125_A = data.readFloat();
        this.field_70126_B = this.field_70177_z;
        this.field_70127_C = this.field_70125_A;
    }
}

