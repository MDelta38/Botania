/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.village.Village
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.network.PacketSound;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.village.Village;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityWitchHunter
extends EntityCreature
implements IRangedAttackMob,
IEntitySelector {
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 20, 60, 15.0f);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.2, false);
    private String targetPlayerName;
    private static final double HUNTER_NOTICE_CHANCE = 0.1;
    private static final long HUNTER_DELAY = TimeUtil.minsToTicks(2);
    private static final double HUNTER_TRIGGER_CHANCE = 0.01;

    public EntityWitchHunter(World par1World) {
        super(par1World);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLivingBase.class, 0, false, true, (IEntitySelector)this));
        this.field_70728_aV = 5;
        this.targetPlayerName = "";
        if (par1World != null && !par1World.field_72995_K) {
            this.setCombatTask();
        }
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.witchhunter.name");
    }

    public boolean func_82704_a(Entity entity) {
        if (CreatureUtil.isUndead(entity) || CreatureUtil.isDemonic(entity) || entity instanceof EntityWitch || CreatureUtil.isWerewolf(entity)) {
            return true;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            return CreatureUtil.isWitch(entity) || CreatureUtil.isWerewolf(entity) || CreatureUtil.isVampire(entity) || this.targetPlayerName != null && !this.targetPlayerName.isEmpty() && player.func_70005_c_().equals(this.targetPlayerName);
        }
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(13, (Object)new Byte(0));
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return "mob.villager.hit";
    }

    protected String func_70673_aS() {
        return "mob.villager.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("step.grass", 0.15f, 1.0f);
    }

    public boolean func_70652_k(Entity targetEntity) {
        boolean flag;
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int i = 0;
        if (targetEntity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)targetEntity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)targetEntity));
        }
        if (flag = targetEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f)) {
            int j;
            if (i > 0) {
                targetEntity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                targetEntity.func_70015_d(j * 4);
            }
            if (targetEntity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)targetEntity), (Entity)this);
            }
            EnchantmentHelper.func_151385_b((EntityLivingBase)this, (Entity)targetEntity);
        }
        return flag;
    }

    public void func_70636_d() {
        this.func_82168_bl();
        float f = this.func_70013_c(1.0f);
        if (f > 0.5f) {
            this.field_70708_bq += 2;
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 2 && this.func_70644_a(Potion.field_76436_u)) {
            this.func_82170_o(Potion.field_76436_u.field_76415_H);
        }
        super.func_70636_d();
    }

    protected String func_145776_H() {
        return "game.hostile.swim";
    }

    protected String func_145777_O() {
        return "game.hostile.swim.splash";
    }

    public void func_70098_U() {
        super.func_70098_U();
        if (this.field_70154_o instanceof EntityCreature) {
            EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
            this.field_70761_aq = entitycreature.field_70761_aq;
        }
    }

    public boolean func_70097_a(DamageSource damageSource, float damage) {
        if (damageSource.func_76346_g() != null && (damageSource.func_76346_g() instanceof EntityVillageGuard || damageSource.func_76346_g() instanceof EntityWitchHunter)) {
            return false;
        }
        if (this.func_85032_ar()) {
            return false;
        }
        if (super.func_70097_a(damageSource, Math.min(damage, 9.0f))) {
            Entity entity = damageSource.func_76346_g();
            if (this.field_70153_n != entity && this.field_70154_o != entity) {
                if (entity != this) {
                    this.field_70789_a = entity;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    protected String func_146067_o(int distance) {
        return distance > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    protected void func_70628_a(boolean par1, int par2) {
        int j = this.field_70146_Z.nextInt(3 + par2);
        for (int k = 0; k < j; ++k) {
            this.func_70099_a(Witchery.Items.GENERIC.itemBoltStake.createStack(), 0.0f);
        }
    }

    protected void func_70600_l(int par1) {
        this.func_70099_a(Witchery.Items.GENERIC.itemBoltAntiMagic.createStack(2), 0.0f);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
        this.setHunterType(this.field_70170_p.field_73012_v.nextInt(3));
        this.func_70062_b(0, new ItemStack(Witchery.Items.CROSSBOW_PISTOL));
        this.func_82162_bC();
        return par1EntityLivingData;
    }

    public void setCombatTask() {
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiAttackOnCollide);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiArrowAttack);
        ItemStack itemstack = this.func_70694_bm();
        if (itemstack != null && itemstack.func_77973_b() == Witchery.Items.CROSSBOW_PISTOL) {
            this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiArrowAttack);
        } else {
            this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAttackOnCollide);
        }
    }

    public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityBolt entityarrow = new EntityBolt(this.field_70170_p, (EntityLivingBase)this, par1EntityLivingBase, 1.6f, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
        int i = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)this.func_70694_bm());
        int j = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)this.func_70694_bm());
        entityarrow.setDamage((double)(par2 * 2.0f) + this.field_70146_Z.nextGaussian() * 0.25 + (double)((float)this.field_70170_p.field_73013_u.func_151525_a() * 0.11f));
        if (i > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5 + 0.5);
        }
        if (j > 0) {
            entityarrow.setKnockbackStrength(j);
        }
        if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)this.func_70694_bm()) > 0 || CreatureUtil.isVampire((Entity)this.func_70638_az()) && this.field_70170_p.field_73012_v.nextInt(3) == 0) {
            entityarrow.func_70015_d(100);
        }
        if (this.func_70638_az() != null) {
            if (CreatureUtil.isWerewolf((Entity)this.func_70638_az())) {
                entityarrow.setBoltType(4);
            } else if (CreatureUtil.isUndead((Entity)this.func_70638_az())) {
                entityarrow.setBoltType(3);
            } else if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
                entityarrow.setBoltType(2);
            }
        }
        this.func_85030_a("random.bow", 1.0f, 1.0f / (this.func_70681_au().nextFloat() * 0.4f + 0.8f));
        this.field_70170_p.func_72838_d((Entity)entityarrow);
    }

    public int getHunterType() {
        return this.field_70180_af.func_75683_a(13);
    }

    public void setHunterType(int par1) {
        this.field_70180_af.func_75692_b(13, (Object)((byte)par1));
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        if (nbtRoot.func_74764_b("HunterType")) {
            byte b0 = nbtRoot.func_74771_c("HunterType");
            this.setHunterType(b0);
        }
        this.targetPlayerName = nbtRoot.func_74779_i("HunterTarget");
        this.setCombatTask();
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        nbtRoot.func_74774_a("HunterType", (byte)this.getHunterType());
        nbtRoot.func_74778_a("HunterTarget", this.targetPlayerName);
    }

    public void func_70062_b(int slot, ItemStack stack) {
        super.func_70062_b(slot, stack);
        if (!this.field_70170_p.field_72995_K && slot == 0) {
            this.setCombatTask();
        }
    }

    public double func_70033_W() {
        return super.func_70033_W() - 0.5;
    }

    public static void blackMagicPerformed(EntityPlayer player) {
        NBTTagCompound nbtPlayer;
        if (player != null && player.field_70170_p != null && !player.field_70170_p.field_72995_K && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74763_f("WITCHunterTrigger") <= 0L && player.field_70170_p.field_73012_v.nextDouble() < 0.1) {
            long totalWorldTicks = TimeUtil.getServerTimeInTicks();
            nbtPlayer.func_74772_a("WITCHunterTrigger", totalWorldTicks);
        }
    }

    public static void handleWitchHunterEffects(EntityPlayer player, long totalWorldTicks) {
        long triggerTimeTicks;
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && ((triggerTimeTicks = nbtPlayer.func_74763_f("WITCHunterTrigger")) > 0L && totalWorldTicks >= triggerTimeTicks + HUNTER_DELAY && player.field_70170_p.field_73012_v.nextDouble() < 0.01 || EntityWitchHunter.isVampireActive(player, totalWorldTicks))) {
            nbtPlayer.func_82580_o("WITCHunterTrigger");
            int MAX_SPAWNS = 2;
            int tries = 3;
            int spawned = 0;
            for (int i = 0; i < 3 && spawned < 2; ++i) {
                EntityWitchHunter creature = (EntityWitchHunter)Infusion.spawnCreature(player.field_70170_p, EntityWitchHunter.class, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), (EntityLivingBase)player, 3, 8, ParticleEffect.SMOKE, null);
                if (creature == null) continue;
                ++spawned;
                creature.targetPlayerName = player.func_70005_c_();
                creature.func_110161_a(null);
                EntityUtil.setTarget((EntityLiving)creature, (EntityLivingBase)player);
            }
            if (spawned > 0) {
                Witchery.packetPipeline.sendTo((IMessage)new PacketSound(SoundEffect.WITCHERY_RANDOM_THEYCOME, (Entity)player, 1.0f, 1.0f), player);
            }
        }
    }

    private static boolean isVampireActive(EntityPlayer player, long totalWorldTicks) {
        Village village;
        if (Config.instance().vampireHunterSpawnChance <= 0.0 || player.field_71075_bZ.field_75098_d) {
            return false;
        }
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if (playerEx.getVampireLevel() < 10) {
            return false;
        }
        if (player.field_70170_p.field_73012_v.nextDouble() < Config.instance().vampireHunterSpawnChance && (village = player.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), 128)) != null && village.func_82684_a(player.func_70005_c_()) < -1) {
            List hunters = player.field_70170_p.func_72872_a(EntityWitchHunter.class, player.field_70121_D.func_72314_b(64.0, 16.0, 64.0));
            return hunters == null || hunters.size() == 0;
        }
        return false;
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_) {
        if (this.field_70724_aR <= 0 && p_70785_2_ < 2.0f && p_70785_1_.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && p_70785_1_.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(p_70785_1_);
        }
    }

    public float func_70783_a(int p_70783_1_, int p_70783_2_, int p_70783_3_) {
        return 0.5f - this.field_70170_p.func_72801_o(p_70783_1_, p_70783_2_, p_70783_3_);
    }

    protected boolean isValidLightLevel() {
        int k;
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b), k = MathHelper.func_76128_c((double)this.field_70161_v)) > this.field_70146_Z.nextInt(32)) {
            return false;
        }
        int l = this.field_70170_p.func_72957_l(i, j, k);
        if (this.field_70170_p.func_72911_I()) {
            int i1 = this.field_70170_p.field_73008_k;
            this.field_70170_p.field_73008_k = 10;
            l = this.field_70170_p.func_72957_l(i, j, k);
            this.field_70170_p.field_73008_k = i1;
        }
        return l <= this.field_70146_Z.nextInt(8);
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.func_70601_bi();
    }

    protected boolean func_146066_aG() {
        return true;
    }
}

