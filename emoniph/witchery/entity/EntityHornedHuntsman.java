/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsTarget
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityHornedHuntsman
extends EntityMob
implements IBossDisplayData,
IRangedAttackMob,
IHandleDT {
    private int attackTimer;
    private boolean explosiveEntrance;
    long ticksSinceTeleport = 0L;

    public EntityHornedHuntsman(World par1World) {
        super(par1World);
        this.func_70105_a(1.4f, 3.2f);
        this.field_70178_ae = true;
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 1.0, 48.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 20, 60, 30.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70728_aV = 70;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(17, (Object)0);
        this.field_70180_af.func_75682_a(20, (Object)new Integer(0));
    }

    public void causeExplosiveEntrance() {
        this.explosiveEntrance = true;
    }

    public int func_70658_aO() {
        return 4;
    }

    public void func_70110_aj() {
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.hornedHuntsman.name");
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
    }

    public int func_82212_n() {
        return this.field_70180_af.func_75679_c(20);
    }

    public void func_82215_s(int par1) {
        this.field_70180_af.func_75692_b(20, (Object)par1);
    }

    public void func_82206_m() {
        this.func_82215_s(150);
        this.func_70606_j(this.func_110138_aP() / 4.0f);
    }

    protected void func_70619_bc() {
        if (this.func_82212_n() > 0) {
            int i = this.func_82212_n() - 1;
            if (i <= 0) {
                if (this.explosiveEntrance) {
                    this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v, 6.0f, false, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
                }
                this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            }
            this.func_82215_s(i);
            if (this.field_70173_aa % 10 == 0) {
                this.func_70691_i(20.0f);
            }
        } else {
            super.func_70619_bc();
            if (this.field_70173_aa % 20 == 0) {
                this.func_70691_i(1.0f);
            }
            if (this.field_70173_aa % 20 == 0 && this.field_70170_p.field_73012_v.nextInt(5) == 0 && this.func_70638_az() != null && !this.field_70170_p.field_72995_K && this.func_70635_at().func_75522_a((Entity)this.func_70638_az())) {
                float f;
                double d0 = this.func_70092_e(this.func_70638_az().field_70165_t, this.func_70638_az().field_70121_D.field_72338_b, this.func_70638_az().field_70161_v);
                this.func_70671_ap().func_75651_a((Entity)this.func_70638_az(), 30.0f, 30.0f);
                float range = 30.0f;
                float f1 = f = MathHelper.func_76133_a((double)d0) / range;
                if (f < 0.1f) {
                    f1 = 0.1f;
                }
                if (f1 > 1.0f) {
                    f1 = 1.0f;
                }
                this.func_82196_d(this.func_70638_az(), f);
            }
            if (this.field_70173_aa % (200 + this.field_70170_p.field_73012_v.nextInt(4) * 100) == 0 && this.func_70638_az() != null && this.func_70068_e((Entity)this.func_70638_az()) <= 256.0 && !this.field_70170_p.field_72995_K && this.func_70635_at().func_75522_a((Entity)this.func_70638_az())) {
                EntityWolf wolf = new EntityWolf(this.field_70170_p);
                wolf.func_70012_b(this.field_70165_t - 0.5 + this.field_70170_p.field_73012_v.nextDouble(), this.field_70163_u, this.field_70161_v - 0.5 + this.field_70170_p.field_73012_v.nextDouble(), this.field_70759_as, this.field_70125_A);
                wolf.func_70916_h(true);
                wolf.func_70624_b(this.func_70638_az());
                wolf.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 20000, 1));
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)wolf, 2.0, 2.0, 10);
                this.field_70170_p.func_72838_d((Entity)wolf);
            }
            if (!this.field_70170_p.field_72995_K && this.func_70661_as().func_75500_f() && this.func_70638_az() != null && (long)this.field_70173_aa - this.ticksSinceTeleport > 200L) {
                this.ticksSinceTeleport = this.field_70173_aa;
                this.teleportToEntity((Entity)this.func_70638_az());
            }
        }
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vec3 vec3 = Vec3.func_72443_a((double)(this.field_70165_t - par1Entity.field_70165_t), (double)(this.field_70121_D.field_72338_b + (double)(this.field_70131_O / 2.0f) - par1Entity.field_70163_u + (double)par1Entity.func_70047_e()), (double)(this.field_70161_v - par1Entity.field_70161_v));
        vec3 = vec3.func_72432_b();
        double d0 = 8.0;
        double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 8.0 - vec3.field_72450_a * d0;
        double d2 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
        double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 8.0 - vec3.field_72449_c * d0;
        return this.teleportTo(d1, d2, d3);
    }

    protected boolean teleportTo(double par1, double par3, double par5) {
        int k;
        int j;
        double d3 = this.field_70165_t;
        double d4 = this.field_70163_u;
        double d5 = this.field_70161_v;
        this.field_70165_t = par1;
        this.field_70163_u = par3;
        this.field_70161_v = par5;
        boolean flag = false;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72899_e(i, j = MathHelper.func_76128_c((double)this.field_70163_u), k = MathHelper.func_76128_c((double)this.field_70161_v))) {
            boolean flag1 = false;
            while (!flag1 && j > 0) {
                Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
                if (block.func_149688_o().func_76230_c()) {
                    flag1 = true;
                    continue;
                }
                this.field_70163_u -= 1.0;
                --j;
            }
            if (flag1) {
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.func_70107_b(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * (double)this.field_70131_O;
            double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            this.field_70170_p.func_72869_a("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.func_85030_a("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    protected int func_70682_h(int par1) {
        return par1;
    }

    protected void func_82167_n(Entity par1Entity) {
        super.func_82167_n(par1Entity);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0f));
    }

    @Override
    public float getCapDT(DamageSource source, float damage) {
        return 15.0f;
    }

    public boolean func_70686_a(Class par1Class) {
        return super.func_70686_a(par1Class);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("PlayerCreated", this.isPlayerCreated());
        par1NBTTagCompound.func_74768_a("Invul", this.func_82212_n());
        par1NBTTagCompound.func_74757_a("explosiveEntrance", this.explosiveEntrance);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
        this.func_82215_s(par1NBTTagCompound.func_74762_e("Invul"));
        this.explosiveEntrance = par1NBTTagCompound.func_74764_b("explosiveEntrance") ? par1NBTTagCompound.func_74767_n("explosiveEntrance") : false;
    }

    public boolean func_70652_k(Entity par1Entity) {
        this.attackTimer = 10;
        this.field_70170_p.func_72960_a((Entity)this, (byte)4);
        boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)(7 + this.field_70146_Z.nextInt(15)));
        if (flag) {
            par1Entity.field_70181_x += (double)0.4f;
        }
        this.func_85030_a("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.func_85030_a("mob.irongolem.throw", 1.0f, 1.0f);
        } else {
            super.func_70103_a(par1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    protected String func_70639_aQ() {
        return "mob.enderdragon.growl";
    }

    protected String func_70621_aR() {
        return "mob.horse.zombie.hit";
    }

    protected String func_70673_aS() {
        return "mob.wither.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.irongolem.walk", 1.0f, 1.0f);
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.func_70099_a(new ItemStack(Items.field_151144_bL, this.field_70170_p.field_73012_v.nextInt(3) == 0 ? 3 : 2, 1), 0.0f);
        Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
        int k = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), (int)enchantment.func_77325_b());
        ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
        this.func_70099_a(itemstack, 0.0f);
        this.func_70099_a(Witchery.Items.GENERIC.itemInfernalBlood.createStack(), 0.0f);
        if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
            this.func_70099_a(new ItemStack(Witchery.Items.HUNTSMANS_SPEAR), 0.0f);
        }
    }

    protected Item func_146068_u() {
        return null;
    }

    public boolean isPlayerCreated() {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean par1) {
        this.func_110163_bv();
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 & 0xFFFFFFFE)));
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_82196_d(EntityLivingBase targetEntity, float par2) {
        EntityArrow entityarrow = new EntityArrow(this.field_70170_p, (EntityLivingBase)this, targetEntity, 1.6f, (float)(14 - this.field_70170_p.field_73013_u.func_151525_a() * 4));
        entityarrow.func_70239_b((double)(par2 * 8.0f) + this.field_70146_Z.nextGaussian() * 0.25 + (double)((float)this.field_70170_p.field_73013_u.func_151525_a() * 0.11f));
        entityarrow.func_70240_a(2);
        this.func_85030_a("random.bow", 1.0f, 1.0f / (this.func_70681_au().nextFloat() * 0.4f + 0.8f));
        this.field_70170_p.func_72838_d((Entity)entityarrow);
    }
}

