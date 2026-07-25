/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockLoot;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.projectile.EntityGolemOrb;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityEldritchGolem
extends EntityThaumcraftBoss
implements IEldritchMob,
IRangedAttackMob {
    int beamCharge = 0;
    boolean chargingBeam = false;
    int arcing = 0;
    int ax = 0;
    int ay = 0;
    int az = 0;
    private int attackTimer;

    public EntityEldritchGolem(World p_i1745_1_) {
        super(p_i1745_1_);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.1, false));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.func_70105_a(1.75f, 3.5f);
        this.field_70178_ae = true;
    }

    @Override
    public void generateName() {
        int t = (int)this.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
        if (t >= 0) {
            this.func_94058_c(String.format(StatCollector.func_74838_a((String)"entity.Thaumcraft.EldritchGolem.name"), ChampionModifier.mods[t].getModNameLocalized()));
        }
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(12, (Object)0);
    }

    public boolean isHeadless() {
        return this.func_70096_w().func_75683_a(12) == 1;
    }

    public void setHeadless(boolean par1) {
        this.field_70180_af.func_75692_b(12, (Object)(par1 ? (byte)1 : 0));
    }

    @Override
    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74757_a("headless", this.isHeadless());
    }

    @Override
    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        this.setHeadless(nbt.func_74767_n("headless"));
        if (this.isHeadless()) {
            this.makeHeadless();
        }
    }

    public float func_70047_e() {
        return this.isHeadless() ? 3.33f : 3.0f;
    }

    public int func_70658_aO() {
        return super.func_70658_aO() + 6;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(250.0);
    }

    protected String func_70621_aR() {
        return "mob.irongolem.hit";
    }

    protected String func_70673_aS() {
        return "mob.irongolem.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.func_85030_a("mob.irongolem.walk", 1.0f, 1.0f);
    }

    @Override
    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        this.spawnTimer = 100;
        return super.func_110161_a(p_110161_1_);
    }

    public void func_70636_d() {
        float h;
        Block block;
        int k;
        int j;
        int i;
        super.func_70636_d();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7 && this.field_70146_Z.nextInt(5) == 0) {
            i = MathHelper.func_76128_c((double)this.field_70165_t);
            block = this.field_70170_p.func_147439_a(i, j = MathHelper.func_76128_c((double)(this.field_70163_u - (double)0.2f - (double)this.field_70129_M)), k = MathHelper.func_76128_c((double)this.field_70161_v));
            if (block.func_149688_o() != Material.field_151579_a) {
                this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b((Block)block) + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + ((double)this.field_70146_Z.nextFloat() - 0.5) * (double)this.field_70130_N, this.field_70121_D.field_72338_b + 0.1, this.field_70161_v + ((double)this.field_70146_Z.nextFloat() - 0.5) * (double)this.field_70130_N, 4.0 * ((double)this.field_70146_Z.nextFloat() - 0.5), 0.5, ((double)this.field_70146_Z.nextFloat() - 0.5) * 4.0);
            }
            if (!this.field_70170_p.field_72995_K && block instanceof BlockLoot) {
                this.field_70170_p.func_147480_a(i, j, k, true);
            }
        }
        if (!this.field_70170_p.field_72995_K && (h = (block = this.field_70170_p.func_147439_a(i = MathHelper.func_76128_c((double)(this.field_70165_t + this.field_70159_w)), j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b), k = MathHelper.func_76128_c((double)(this.field_70161_v + this.field_70179_y)))).func_149712_f(this.field_70170_p, i, j, k)) >= 0.0f && h <= 0.15f) {
            this.field_70170_p.func_147480_a(i, j, k, true);
        }
    }

    @Override
    public boolean func_70097_a(DamageSource source, float damage) {
        if (!this.field_70170_p.field_72995_K && damage > this.func_110143_aJ() && !this.isHeadless()) {
            this.setHeadless(true);
            this.spawnTimer = 100;
            double xx = MathHelper.func_76134_b((float)(this.field_70177_z % 360.0f / 180.0f * (float)Math.PI)) * 0.75f;
            double zz = MathHelper.func_76126_a((float)(this.field_70177_z % 360.0f / 180.0f * (float)Math.PI)) * 0.75f;
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t + xx, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v + zz, 2.0f, false);
            this.makeHeadless();
            return false;
        }
        return super.func_70097_a(source, damage);
    }

    void makeHeadless() {
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILongRangeAttack(this, 3.0, 1.0, 5, 5, 24.0f));
    }

    public boolean func_70652_k(Entity target) {
        if (this.attackTimer > 0) {
            return false;
        }
        this.attackTimer = 10;
        this.field_70170_p.func_72960_a((Entity)this, (byte)4);
        boolean flag = target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.75f);
        if (flag) {
            target.field_70181_x += 0.2000000059604645;
            if (this.isHeadless()) {
                target.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 1.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 1.5f));
            }
        }
        return flag;
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        if (this.func_70685_l((Entity)entitylivingbase) && !this.chargingBeam && this.beamCharge > 0) {
            this.beamCharge -= 15 + this.field_70146_Z.nextInt(5);
            this.func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f), entitylivingbase.field_70161_v, 30.0f, 30.0f);
            Vec3 v = this.func_70676_i(1.0f);
            EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, (EntityLivingBase)this, entitylivingbase, false);
            blast.field_70165_t += v.field_72450_a;
            blast.field_70161_v += v.field_72449_c;
            blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
            double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
            double d1 = entitylivingbase.field_70163_u - this.field_70163_u - (double)(entitylivingbase.field_70131_O / 2.0f);
            double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
            blast.func_70186_c(d0, d1, d2, 0.66f, 5.0f);
            this.func_85030_a("thaumcraft:egattack", 1.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte p_70103_1_) {
        if (p_70103_1_ == 4) {
            this.attackTimer = 10;
            this.func_85030_a("mob.irongolem.throw", 1.0f, 1.0f);
        } else if (p_70103_1_ == 18) {
            this.spawnTimer = 150;
        } else if (p_70103_1_ == 19) {
            if (this.arcing == 0) {
                float radius = 2.0f + this.field_70146_Z.nextFloat() * 2.0f;
                double radians = Math.toRadians(this.field_70146_Z.nextInt(360));
                double deltaX = (double)radius * Math.cos(radians);
                double deltaZ = (double)radius * Math.sin(radians);
                int bx = MathHelper.func_76128_c((double)(this.field_70165_t + deltaX));
                int by = MathHelper.func_76128_c((double)this.field_70163_u);
                int bz = MathHelper.func_76128_c((double)(this.field_70161_v + deltaZ));
                int c = 0;
                while (c < 5 && this.field_70170_p.func_147437_c(bx, by, bz)) {
                    ++c;
                    --by;
                }
                if (this.field_70170_p.func_147437_c(bx, by + 1, bz) && !this.field_70170_p.func_147437_c(bx, by, bz)) {
                    this.ax = bx;
                    this.ay = by;
                    this.az = bz;
                    this.arcing = 8 + this.field_70146_Z.nextInt(5);
                    this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:jacobs", 0.8f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05f, false);
                }
            }
        } else {
            super.func_70103_a(p_70103_1_);
        }
    }

    @Override
    public void func_70071_h_() {
        if (this.getSpawnTimer() == 150) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)18);
        }
        if (this.getSpawnTimer() > 0) {
            this.func_70691_i(2.0f);
        }
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            if (this.isHeadless()) {
                this.field_70125_A = 0.0f;
                float f1 = MathHelper.func_76134_b((float)(-this.field_70761_aq * ((float)Math.PI / 180) - (float)Math.PI));
                float f2 = MathHelper.func_76126_a((float)(-this.field_70761_aq * ((float)Math.PI / 180) - (float)Math.PI));
                float f3 = -MathHelper.func_76134_b((float)(-this.field_70125_A * ((float)Math.PI / 180)));
                float f4 = MathHelper.func_76126_a((float)(-this.field_70125_A * ((float)Math.PI / 180)));
                Vec3 v = Vec3.func_72443_a((double)(f2 * f3), (double)f4, (double)(f1 * f3));
                if (this.field_70146_Z.nextInt(20) == 0) {
                    float a = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0f;
                    float b = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 2.0f;
                    Thaumcraft.proxy.spark((float)(this.field_70165_t + v.field_72450_a + (double)a), (float)this.field_70163_u + this.func_70047_e() - 0.25f, (float)(this.field_70161_v + v.field_72449_c + (double)b), 0.3f, 0.65f + this.field_70146_Z.nextFloat() * 0.1f, 1.0f, 1.0f, 0.8f);
                }
                Thaumcraft.proxy.drawVentParticles(this.field_70170_p, (double)((float)this.field_70165_t) + v.field_72450_a * 0.66, (float)this.field_70163_u + this.func_70047_e() - 0.75f, (double)((float)this.field_70161_v) + v.field_72449_c * 0.66, 0.0, 0.001, 0.0, 0x555555, 4.0f);
                if (this.arcing > 0) {
                    Thaumcraft.proxy.arcLightning(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0f), this.field_70161_v, (double)this.ax + 0.5, this.ay + 1, (double)this.az + 0.5, 0.65f + this.field_70146_Z.nextFloat() * 0.1f, 1.0f, 1.0f, 1.0f - (float)this.arcing / 10.0f);
                    --this.arcing;
                }
            }
        } else {
            if (this.isHeadless() && this.beamCharge <= 0) {
                this.chargingBeam = true;
            }
            if (this.isHeadless() && this.chargingBeam) {
                ++this.beamCharge;
                this.field_70170_p.func_72960_a((Entity)this, (byte)19);
                if (this.beamCharge == 150) {
                    this.chargingBeam = false;
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    protected void func_70600_l(int fortune) {
        super.func_70600_l(fortune);
    }
}

