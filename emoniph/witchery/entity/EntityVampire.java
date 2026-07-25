/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFleeSun
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictSun
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.village.Village
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBloodCrucible;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.village.Village;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityVampire
extends EntityCreature
implements IEntitySelector,
IHandleDT {
    private Village villageObj;
    private ChunkCoordinates coffinPos = new ChunkCoordinates(0, 0, 0);
    float damageDone = 0.0f;

    public EntityVampire(World world) {
        super(world);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75498_b(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.2, false));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(12, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCreature.class, 0, false, true, (IEntitySelector)this));
        this.field_70728_aV = 20;
    }

    public boolean func_82704_a(Entity entity) {
        return entity instanceof EntityVillager && this.villageObj != null || entity instanceof EntityPlayer && !ExtendedPlayer.get((EntityPlayer)entity).isVampire();
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.func_72935_r()) {
                if (this.func_70643_av() == null) {
                    this.func_70624_b(null);
                }
                if (this.field_70173_aa % 100 == 2) {
                    this.villageObj = null;
                    this.damageDone = 0.0f;
                    if (this.func_70092_e(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c) > 16.0) {
                        ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                        EntityUtil.moveToBlockPositionAndUpdate((EntityLiving)this, this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 8);
                        ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                        this.func_110171_b(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 4);
                    }
                }
                if (this.field_70173_aa % 20 == 2 && CreatureUtil.isInSunlight((EntityLivingBase)this)) {
                    this.func_70015_d(2);
                }
            } else if (this.damageDone >= 20.0f) {
                if (this.villageObj != null) {
                    this.func_70624_b(null);
                    this.func_70604_c(null);
                    this.villageObj = null;
                    ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                    EntityUtil.moveToBlockPositionAndUpdate((EntityLiving)this, this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 8);
                    ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                    this.func_110171_b(this.coffinPos.field_71574_a, this.coffinPos.field_71572_b, this.coffinPos.field_71573_c, 4);
                    this.tryFillBloodCrucible();
                }
            } else if (this.villageObj == null && this.field_70173_aa % 500 == 2) {
                this.villageObj = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), 128);
                if (this.villageObj != null) {
                    ChunkCoordinates townPos = this.villageObj.func_75577_a();
                    ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                    EntityUtil.moveToBlockPositionAndUpdate((EntityLiving)this, townPos.field_71574_a, townPos.field_71572_b, townPos.field_71573_c, 8);
                    ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)this, 0.8, 1.5, 16);
                    this.func_110171_b(townPos.field_71574_a, townPos.field_71572_b, townPos.field_71573_c, this.villageObj.func_75568_b());
                }
            }
        }
    }

    public void tryFillBloodCrucible() {
        int r = 6;
        for (int x = this.coffinPos.field_71574_a - 6; x <= this.coffinPos.field_71574_a + 6; ++x) {
            for (int z = this.coffinPos.field_71573_c - 6; z <= this.coffinPos.field_71573_c + 6; ++z) {
                for (int y = this.coffinPos.field_71572_b - 6; y <= this.coffinPos.field_71572_b + 6; ++y) {
                    if (this.field_70170_p.func_147439_a(x, y, z) != Witchery.Blocks.BLOOD_CRUCIBLE) continue;
                    BlockBloodCrucible.TileEntityBloodCrucible crucible = BlockUtil.getTileEntity((IBlockAccess)this.field_70170_p, x, y, z, BlockBloodCrucible.TileEntityBloodCrucible.class);
                    if (crucible != null) {
                        crucible.increaseBloodLevel();
                    }
                    return;
                }
            }
        }
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void setStalkingArea(int p_110171_1_, int p_110171_2_, int p_110171_3_) {
        this.coffinPos.func_71571_b(p_110171_1_, p_110171_2_, p_110171_3_);
    }

    protected String func_145776_H() {
        return "game.hostile.swim";
    }

    protected String func_145777_O() {
        return "game.hostile.swim.splash";
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(13, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(14, (Object)new Integer(500));
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected String func_70639_aQ() {
        return "mob.villager.idle";
    }

    protected String func_70621_aR() {
        return "mob.villager.hit";
    }

    protected String func_70673_aS() {
        return "mob.villager.death";
    }

    protected float func_70647_i() {
        return 0.6f;
    }

    public void func_70636_d() {
        this.func_82168_bl();
        float f = this.func_70013_c(1.0f);
        if (f > 0.5f) {
            this.field_70708_bq += 2;
        }
        super.func_70636_d();
    }

    public boolean func_70652_k(Entity entity) {
        boolean flag;
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int i = 0;
        if (entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity));
        }
        if (entity instanceof EntityVillager) {
            ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)entity);
            if (villagerEx != null && this.field_70170_p.field_73012_v.nextInt(10) == 0) {
                this.damageDone += 4.0f;
                int taken = villagerEx.takeBlood(30, (EntityLivingBase)this);
                if (taken > 0) {
                    this.func_70691_i(4.0f);
                    ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, this.field_70170_p, entity.field_70165_t, entity.field_70163_u + (double)entity.field_70131_O * 0.8, entity.field_70161_v, 0.5, 0.2, 16);
                }
            }
            flag = true;
        } else {
            boolean needsBlood = this.damageDone < 20.0f;
            flag = entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f);
            if (flag) {
                int j;
                if (i > 0) {
                    entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                    this.field_70159_w *= 0.6;
                    this.field_70179_y *= 0.6;
                }
                if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                    entity.func_70015_d(j * 4);
                }
                if (entity instanceof EntityLivingBase) {
                    EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)entity), (Entity)this);
                }
                EnchantmentHelper.func_151385_b((EntityLivingBase)this, (Entity)entity);
            }
        }
        return flag;
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_) {
        if (this.field_70724_aR <= 0 && p_70785_2_ < 2.0f && p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(p_70785_1_);
        }
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
    }

    public void func_70098_U() {
        super.func_70098_U();
        if (this.field_70154_o instanceof EntityCreature) {
            EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
            this.field_70761_aq = entitycreature.field_70761_aq;
        }
    }

    protected String func_146067_o(int p_146067_1_) {
        return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        return super.func_70097_a(source, damage);
    }

    @Override
    public float getCapDT(DamageSource source, float damage) {
        return 0.0f;
    }

    public void func_70645_a(DamageSource source) {
        if (!CreatureUtil.checkForVampireDeath((EntityLivingBase)this, source)) {
            return;
        }
        super.func_70645_a(source);
    }

    protected Item func_146068_u() {
        return Items.field_151097_aZ;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_70600_l(int p_70600_1_) {
    }

    protected void func_82164_bB() {
        this.func_70062_b(1, new ItemStack((Item)Witchery.Items.VAMPIRE_BOOTS));
        boolean male = this.field_70170_p.field_73012_v.nextBoolean();
        if (male) {
            this.func_70062_b(2, new ItemStack((Item)(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_LEGS_KILT : Witchery.Items.VAMPIRE_LEGS)));
            this.func_70062_b(3, new ItemStack((Item)(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_COAT_CHAIN : Witchery.Items.VAMPIRE_COAT)));
        } else {
            this.func_70062_b(2, new ItemStack((Item)(this.field_70170_p.field_73012_v.nextInt(4) != 0 ? Witchery.Items.VAMPIRE_LEGS_KILT : Witchery.Items.VAMPIRE_LEGS)));
            this.func_70062_b(3, new ItemStack((Item)(this.field_70170_p.field_73012_v.nextInt(3) == 0 ? Witchery.Items.VAMPIRE_COAT_FEMALE_CHAIN : Witchery.Items.VAMPIRE_COAT_FEMALE)));
        }
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.vampire.name");
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        p_110161_1_ = super.func_110161_a(p_110161_1_);
        this.func_82164_bB();
        this.coffinPos.func_71571_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
        return p_110161_1_;
    }

    public int getGuardType() {
        return this.field_70180_af.func_75683_a(13);
    }

    public void setGuardType(int p_82201_1_) {
        this.field_70180_af.func_75692_b(13, (Object)((byte)p_82201_1_));
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        if (nbtRoot.func_150297_b("GuardType", 99)) {
            byte b0 = nbtRoot.func_74771_c("GuardType");
            this.setGuardType(b0);
        }
        this.coffinPos.func_71571_b(nbtRoot.func_74762_e("BaseX"), nbtRoot.func_74762_e("BaseY"), nbtRoot.func_74762_e("BaseZ"));
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        nbtRoot.func_74774_a("GuardType", (byte)this.getGuardType());
        ChunkCoordinates home = this.func_110172_bL();
        nbtRoot.func_74768_a("BaseX", this.coffinPos.field_71574_a);
        nbtRoot.func_74768_a("BaseY", this.coffinPos.field_71572_b);
        nbtRoot.func_74768_a("BaseZ", this.coffinPos.field_71573_c);
    }

    public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
        super.func_70062_b(p_70062_1_, p_70062_2_);
    }

    public double func_70033_W() {
        return super.func_70033_W() - 0.5;
    }
}

