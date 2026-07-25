/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGoblinMog
extends EntityMob
implements IBossDisplayData,
IRangedAttackMob,
IHandleDT {
    private int attackTimer;
    long ticksSinceTeleport = 0L;
    private static final double INVULNRABLE = 9.0;
    private static final double PERCENT_20 = 36.0;
    private static final double PERCENT_50 = 81.0;
    private static final double PERCENT_80 = 256.0;

    public EntityGoblinMog(World world) {
        super(world);
        this.func_70105_a(0.8f, 1.8f);
        this.field_70178_ae = true;
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 40, 80, 30.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70728_aV = 35;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(17, (Object)0);
        this.field_70180_af.func_75682_a(20, (Object)new Integer(0));
    }

    public int func_70658_aO() {
        return 5;
    }

    public void func_70110_aj() {
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.goblinmog.name");
    }

    public void func_70604_c(EntityLivingBase entity) {
        if (!(entity instanceof EntityGoblinMog || entity instanceof EntityGoblin || entity instanceof EntityGoblinGulg)) {
            super.func_70604_c(entity);
        }
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
            if (!this.field_70170_p.field_72995_K && this.func_70661_as().func_75500_f() && this.func_70638_az() != null && (long)this.field_70173_aa - this.ticksSinceTeleport > 300L) {
                this.ticksSinceTeleport = this.field_70173_aa;
                this.teleportToEntity((Entity)this.func_70638_az());
            }
            if (this.func_70694_bm() == null && this.field_70146_Z.nextInt(100) == 0) {
                this.func_70062_b(0, new ItemStack((Item)Items.field_151031_f));
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, (Entity)this, 0.5, 0.5, 16);
            }
        }
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vec3 vec3 = Vec3.func_72443_a((double)(this.field_70165_t - par1Entity.field_70165_t), (double)(this.field_70121_D.field_72338_b + (double)(this.field_70131_O / 2.0f) - par1Entity.field_70163_u + (double)par1Entity.func_70047_e()), (double)(this.field_70161_v - par1Entity.field_70161_v));
        vec3 = vec3.func_72432_b();
        double d0 = 16.0;
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

    public boolean func_70097_a(DamageSource source, float damage) {
        double distance = this.getDistanceSqToPartner();
        double scale = 1.0;
        if (distance <= 9.0) {
            return false;
        }
        if (distance <= 36.0) {
            scale = 0.2;
        } else if (distance <= 81.0) {
            scale = 0.5;
        } else if (distance <= 256.0) {
            scale = 0.8;
        }
        return super.func_70097_a(source, (float)Math.min((double)damage * scale, 15.0));
    }

    @Override
    public float getCapDT(DamageSource source, float damage) {
        return 15.0f;
    }

    private double getDistanceSqToPartner() {
        double R = 16.0;
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 16.0), (double)(this.field_70163_u - 16.0), (double)(this.field_70161_v - 16.0), (double)(this.field_70165_t + 16.0), (double)(this.field_70163_u + 16.0), (double)(this.field_70161_v + 16.0));
        List mogs = this.field_70170_p.func_72872_a(EntityGoblinGulg.class, bb);
        double minDistance = Double.MAX_VALUE;
        for (Object obj : mogs) {
            EntityGoblinGulg mog = (EntityGoblinGulg)obj;
            double distance = this.func_70068_e((Entity)mog);
            if (!(distance < minDistance)) continue;
            minDistance = distance;
        }
        return minDistance;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("Invul", this.func_82212_n());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.func_82215_s(par1NBTTagCompound.func_74762_e("Invul"));
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
        return "witchery:mob.goblin.mog_idle";
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
        this.func_70099_a(Witchery.Items.GENERIC.itemKobolditeNugget.createStack(this.field_70146_Z.nextInt(3) + 1), 0.0f);
        ItemStack armor = null;
        switch (this.field_70146_Z.nextInt(4)) {
            case 0: {
                armor = new ItemStack((Item)Items.field_151029_X);
                break;
            }
            case 1: {
                armor = new ItemStack((Item)Items.field_151022_W);
                break;
            }
            case 2: {
                armor = new ItemStack((Item)Items.field_151023_V);
                break;
            }
            case 3: {
                armor = new ItemStack((Item)Items.field_151020_U);
            }
        }
        if (armor != null) {
            EnchantmentHelper.func_77504_a((Random)this.field_70170_p.field_73012_v, (ItemStack)armor, (int)30);
            this.func_70099_a(armor, 0.0f);
        }
        if (this.field_70170_p.field_73012_v.nextInt(2) == 0) {
            this.func_70099_a(new ItemStack(Witchery.Items.MOGS_QUIVER), 0.0f);
        }
    }

    protected Item func_146068_u() {
        return null;
    }

    public EntityItem func_70099_a(ItemStack stack, float distance) {
        if (stack != null && stack.func_77973_b() == Items.field_151031_f) {
            EntityItem item = super.func_70099_a(stack, distance);
            item.field_145804_b = 100;
            item.lifespan = 100;
            return item;
        }
        return super.func_70099_a(stack, distance);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        this.func_110163_bv();
        this.func_70062_b(0, new ItemStack((Item)Items.field_151031_f));
        return super.func_110161_a(par1EntityLivingData);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_82196_d(EntityLivingBase targetEntity, float par2) {
        if (this.func_70694_bm() != null) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, (EntityLivingBase)this, targetEntity, 1.6f, (float)(14 - this.field_70170_p.field_73013_u.func_151525_a() * 4));
            double factor = targetEntity.field_70160_al ? 2.5 : 1.5;
            entityarrow.field_70159_w *= factor;
            entityarrow.field_70181_x *= factor;
            entityarrow.field_70179_y *= factor;
            entityarrow.func_70239_b((double)(par2 * 8.0f) + this.field_70146_Z.nextGaussian() * 0.25 + (double)((float)this.field_70170_p.field_73013_u.func_151525_a() * 0.11f));
            entityarrow.func_70240_a(0);
            this.func_85030_a("random.bow", 1.0f, 1.0f / (this.func_70681_au().nextFloat() * 0.4f + 0.8f));
            this.field_70170_p.func_72838_d((Entity)entityarrow);
        }
    }
}

