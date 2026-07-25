/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOwnerHurtByTarget
 *  net.minecraft.entity.ai.EntityAIOwnerHurtTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.ai.EntityAIWanderWithRestriction;
import com.emoniph.witchery.item.ItemGeneralContract;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.TimeUtil;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityImp
extends EntityTameable
implements IMob,
IEntitySelector,
EntityAIWanderWithRestriction.IHomeLocationProvider {
    private float field_70926_e;
    private float field_70924_f;
    private boolean field_70928_h;
    private static final int MAX_WANDER_RANGE = 16;
    private int secretsShared;
    private int homeX;
    private int homeY;
    private int homeZ;
    private long lastGiftTime;
    private long powerUpExpiry;
    private static final HashMap<Item, Integer> shinies = new HashMap();
    private static final int REWARD_AFFECTION_LEVEL = 20;
    private static final long GIFT_DELAY_TICKS;
    private static final ItemStack[] EXTRA_DROPS;
    private static final String[] DEMON_NAMES;

    public EntityImp(World par1World) {
        super(par1World);
        this.field_70178_ae = true;
        this.func_70105_a(0.4f, 1.3f);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWanderWithRestriction((EntityCreature)this, 1.0, this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIOwnerHurtByTarget((EntityTameable)this));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIOwnerHurtTarget((EntityTameable)this));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLivingBase.class, 0, false, true, (IEntitySelector)this));
        this.func_70903_f(false);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.3f);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, (Object)0);
        this.field_70180_af.func_75682_a(19, (Object)0);
    }

    private void setAffection(int affection) {
        this.field_70180_af.func_75692_b(18, (Object)affection);
    }

    private int getAffection() {
        return this.field_70180_af.func_75679_c(18);
    }

    private void setPowered(boolean powered) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(19, (Object)(powered ? 1 : 0));
        }
    }

    public boolean isPowered() {
        return this.field_70180_af.func_75679_c(19) == 1;
    }

    public boolean func_82704_a(Entity target) {
        if (!this.func_70909_n()) {
            return target instanceof EntityPlayer;
        }
        return target == this.func_70638_az();
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.imp.name");
    }

    public boolean func_70650_aV() {
        return true;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("Affection", this.getAffection());
        par1NBTTagCompound.func_74768_a("SecretsShared", this.secretsShared);
        par1NBTTagCompound.func_74772_a("LastGiftTime", this.lastGiftTime);
        par1NBTTagCompound.func_74772_a("PowerUpUntil2", this.powerUpExpiry);
        par1NBTTagCompound.func_74768_a("HomeLocX", this.homeX);
        par1NBTTagCompound.func_74768_a("HomeLocY", this.homeY);
        par1NBTTagCompound.func_74768_a("HomeLocZ", this.homeZ);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setAffection(par1NBTTagCompound.func_74762_e("Affection"));
        this.secretsShared = par1NBTTagCompound.func_74762_e("SecretsShared");
        this.lastGiftTime = par1NBTTagCompound.func_74763_f("LastGiftTime");
        long time = TimeUtil.getServerTimeInTicks();
        if (par1NBTTagCompound.func_74764_b("PowerUpUntil2")) {
            this.powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil2");
        } else if (par1NBTTagCompound.func_74764_b("PowerUpUntil")) {
            this.powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil");
            if (this.powerUpExpiry > 0L) {
                this.powerUpExpiry = time + (long)TimeUtil.minsToTicks(60);
            }
        }
        if (time < this.powerUpExpiry) {
            this.setPowered(true);
        }
        this.homeX = par1NBTTagCompound.func_74762_e("HomeLocX");
        this.homeY = par1NBTTagCompound.func_74762_e("HomeLocY");
        this.homeZ = par1NBTTagCompound.func_74762_e("HomeLocZ");
    }

    protected String func_70639_aQ() {
        return "witchery:mob.imp.laugh";
    }

    protected float func_70647_i() {
        return this.isPowered() ? (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 0.7f : (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.1f;
    }

    protected String func_70621_aR() {
        return "witchery:mob.imp.hit";
    }

    protected String func_70673_aS() {
        return "witchery:mob.imp.death";
    }

    protected float func_70599_aP() {
        return 0.5f;
    }

    public int func_70627_aG() {
        return TimeUtil.secsToTicks(40);
    }

    public void func_70636_d() {
        EntityLivingBase owner;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && TimeUtil.secondsElapsed(300, this.field_70173_aa) && TameableUtil.hasOwner(this) && (owner = this.func_70902_q()) instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)owner;
            this.setAffection(Math.max(0, this.getAffection() - 1));
            if (this.getAffection() == 0 && this.field_70173_aa > TimeUtil.minsToTicks(60) && this.field_70170_p.field_73012_v.nextDouble() < 0.01) {
                ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_IMP_LAUGH, (Entity)this, 1.0, 1.0, 16);
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.goodbye", this.func_70005_c_());
                this.func_70106_y();
            }
        }
        if (!this.field_70170_p.field_72995_K && this.powerUpExpiry > 0L && this.isPowerupExpired()) {
            this.setPowered(false);
            this.powerUpExpiry = 0L;
        }
        if (this.field_70173_aa % 20 == 0) {
            if (this.isPowered()) {
                if ((double)this.field_70130_N != 0.6) {
                    this.func_70105_a(0.6f, 1.3f);
                }
                if (!this.field_70170_p.field_72995_K) {
                    this.func_70691_i(1.0f);
                }
            } else if ((double)this.field_70130_N != 0.4) {
                this.func_70105_a(0.4f, 1.3f);
            }
        }
        if (this.field_70173_aa % 400 == 0) {
            this.func_70691_i(1.0f);
        }
    }

    private boolean isPowerupExpired() {
        return TimeUtil.getServerTimeInTicks() >= this.powerUpExpiry;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.isPowered()) {
            this.field_70170_p.func_72869_a(ParticleEffect.FLAME.toString(), this.field_70165_t - (double)this.field_70130_N * 0.5 + this.field_70170_p.field_73012_v.nextDouble() * (double)this.field_70130_N, 0.1 + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * 2.0, this.field_70161_v - (double)this.field_70130_N * 0.5 + this.field_70170_p.field_73012_v.nextDouble() * (double)this.field_70130_N, 0.0, 0.0, 0.0);
        }
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        return super.func_70097_a(source, Math.min(damage, this.isPowered() ? 5.0f : 15.0f));
    }

    public boolean func_70652_k(Entity par1Entity) {
        return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), this.isPowered() ? 8.0f : 4.0f);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean func_70085_c(EntityPlayer player) {
        ItemStack stack = player.field_71071_by.func_70448_g();
        if (stack == null) {
            return true;
        }
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        if (this.func_70909_n()) {
            if (Witchery.Items.GENERIC.itemDemonHeart.isMatch(stack)) {
                if (!player.field_71075_bZ.field_75098_d) {
                    --stack.field_77994_a;
                    if (stack.field_77994_a <= 0) {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    }
                }
                if (this.field_70170_p.field_72995_K) return super.func_70085_c(player);
                this.powerUpExpiry = TimeUtil.getServerTimeInTicks() + (long)TimeUtil.minsToTicks(60);
                this.setPowered(true);
                SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.power", this.func_70005_c_());
                return super.func_70085_c(player);
            }
            if (Witchery.Items.GENERIC.itemIcyNeedle.isMatch(stack)) {
                if (!player.field_71075_bZ.field_75098_d) {
                    --stack.field_77994_a;
                    if (stack.field_77994_a <= 0) {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    }
                }
                if (this.field_70170_p.field_72995_K) return super.func_70085_c(player);
                this.powerUpExpiry = 0L;
                this.setPowered(false);
                SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.powerloss", this.func_70005_c_());
                return super.func_70085_c(player);
            }
            if (ItemGeneralContract.isBoundContract(stack)) {
                if (this.field_70170_p.field_72995_K) return super.func_70085_c(player);
                SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
                if (this.isPowered()) {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.toomuchpower", this.func_70005_c_());
                    return super.func_70085_c(player);
                }
                if (this.getAffection() < 20) {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.notliked", this.func_70005_c_());
                    return super.func_70085_c(player);
                }
                long timeNow = TimeUtil.getServerTimeInTicks();
                if (timeNow <= this.lastGiftTime + GIFT_DELAY_TICKS && !player.field_71075_bZ.field_75098_d) {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.toooften", this.func_70005_c_());
                    return super.func_70085_c(player);
                }
                ItemGeneralContract contract = ItemGeneralContract.getContract(stack);
                EntityLivingBase targetEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(this.field_70170_p, (Entity)player, stack, 1);
                if (targetEntity == null) {
                    String name = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1);
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.cannotfind", this.func_70005_c_(), name);
                    return super.func_70085_c(player);
                }
                if (contract.activate(stack, targetEntity)) {
                    this.lastGiftTime = timeNow;
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.feelthefire", this.func_70005_c_(), targetEntity.func_70005_c_());
                    if (player.field_71075_bZ.field_75098_d) return super.func_70085_c(player);
                    --stack.field_77994_a;
                    if (stack.field_77994_a > 0) return super.func_70085_c(player);
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    return super.func_70085_c(player);
                }
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.spell.failed", this.func_70005_c_(), targetEntity.func_70005_c_());
                return super.func_70085_c(player);
            }
            if (this.field_70170_p.field_72995_K) return true;
            Integer affectionBoost = shinies.get(stack.func_77973_b());
            if (affectionBoost != null && stack.func_77960_j() == 0) {
                long timeNow = TimeUtil.getServerTimeInTicks();
                if (!player.field_71075_bZ.field_75098_d) {
                    --stack.field_77994_a;
                    if (stack.field_77994_a <= 0) {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    }
                }
                int affection = this.getAffection() + affectionBoost;
                SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
                if (affection >= 20 && (timeNow > this.lastGiftTime + GIFT_DELAY_TICKS || player.field_71075_bZ.field_75098_d) && this.field_70146_Z.nextInt(Math.max(1, 10 - Math.max(affection - 20, 0))) == 0) {
                    this.lastGiftTime = timeNow;
                    affection = 0;
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.reciprocate", this.func_70005_c_());
                    ItemStack stackForPlayer = null;
                    switch (this.secretsShared) {
                        case 0: {
                            stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulHunger.createStack();
                            ++this.secretsShared;
                            break;
                        }
                        case 1: {
                            stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulFear.createStack();
                            ++this.secretsShared;
                            break;
                        }
                        case 2: {
                            stackForPlayer = Witchery.Items.GENERIC.itemBrewSoulAnguish.createStack();
                            ++this.secretsShared;
                            break;
                        }
                        case 3: {
                            stackForPlayer = Witchery.Items.GENERIC.itemContractTorment.createStack();
                            ++this.secretsShared;
                            break;
                        }
                        default: {
                            stackForPlayer = EXTRA_DROPS[this.field_70146_Z.nextInt(EXTRA_DROPS.length)].func_77946_l();
                        }
                    }
                    if (stackForPlayer != null) {
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_HARP, (Entity)this, 1.0, 1.0, 16);
                        this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, stackForPlayer));
                    }
                } else if (timeNow < this.lastGiftTime + GIFT_DELAY_TICKS) {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.toomany", this.func_70005_c_());
                } else {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.like", this.func_70005_c_());
                }
                this.setAffection(affection);
                return true;
            }
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.gift.hate", this.func_70005_c_());
            return true;
        }
        if (!Witchery.Items.GENERIC.itemContractOwnership.isMatch(stack)) return super.func_70085_c(player);
        if (this.field_70170_p.field_72995_K) return true;
        EntityLivingBase boundEntity = ItemGeneralContract.getBoundEntity(this.field_70170_p, player, stack);
        if (boundEntity == player) {
            int EXPERIENCE_NEEDED = 25;
            if (player.field_71068_ca < 25 && !player.field_71075_bZ.field_75098_d) {
                SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.contract.noxp", this.func_70005_c_());
                return true;
            }
            if (!player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
                if (stack.field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
            player.func_82242_a(-25);
            this.func_70903_f(true);
            TameableUtil.setOwner(this, player);
            this.func_70624_b(null);
            this.func_70778_a(null);
            this.homeX = (int)this.field_70165_t;
            this.homeY = (int)this.field_70163_u;
            this.homeZ = (int)this.field_70161_v;
            this.func_110163_bv();
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.contract.deal", this.func_70005_c_());
            this.func_94058_c(EntityImp.getDemonName(this.field_70146_Z));
            return true;
        }
        if (boundEntity != null) {
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.contract.notowners", this.func_70005_c_());
            return true;
        }
        SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(this.field_70170_p, player, 0.5f, this.func_70647_i());
        ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "entity.witchery.imp.contract.unsigned", this.func_70005_c_());
        return true;
    }

    public EntityImp createChild(EntityAgeable par1EntityAgeable) {
        return null;
    }

    public boolean func_70878_b(EntityAnimal par1EntityAnimal) {
        return false;
    }

    protected boolean func_70692_ba() {
        return true;
    }

    private static String getDemonName(Random rand) {
        if (rand.nextInt(5) == 0) {
            return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
        }
        return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)] + " " + DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
    }

    @Override
    public double getHomeX() {
        return this.homeX;
    }

    @Override
    public double getHomeY() {
        return this.homeY;
    }

    @Override
    public double getHomeZ() {
        return this.homeZ;
    }

    @Override
    public double getHomeRange() {
        return 16.0;
    }

    static {
        shinies.put(new ItemStack(Items.field_151045_i).func_77973_b(), 8);
        shinies.put(new ItemStack(Items.field_151056_x).func_77973_b(), 24);
        shinies.put(new ItemStack(Items.field_151012_L).func_77973_b(), 16);
        shinies.put(new ItemStack(Items.field_151048_u).func_77973_b(), 16);
        shinies.put(new ItemStack(Items.field_151047_v).func_77973_b(), 8);
        shinies.put(new ItemStack(Items.field_151046_w).func_77973_b(), 24);
        shinies.put(new ItemStack(Items.field_151166_bC).func_77973_b(), 3);
        shinies.put(new ItemStack(Items.field_151043_k).func_77973_b(), 1);
        shinies.put(new ItemStack(Items.field_151156_bN).func_77973_b(), 16);
        shinies.put(new ItemStack(Items.field_151072_bj).func_77973_b(), 1);
        shinies.put(new ItemStack(Items.field_151073_bk).func_77973_b(), 4);
        shinies.put(new ItemStack(Items.field_151006_E).func_77973_b(), 3);
        shinies.put(new ItemStack(Items.field_151010_B).func_77973_b(), 2);
        shinies.put(new ItemStack(Items.field_151013_M).func_77973_b(), 2);
        shinies.put(new ItemStack(Items.field_151011_C).func_77973_b(), 1);
        shinies.put(new ItemStack(Items.field_151005_D).func_77973_b(), 3);
        shinies.put(new ItemStack(Blocks.field_150340_R).func_77973_b(), 9);
        shinies.put(new ItemStack(Blocks.field_150475_bE).func_77973_b(), 27);
        shinies.put(new ItemStack(Blocks.field_150484_ah).func_77973_b(), 72);
        shinies.put(new ItemStack(Blocks.field_150368_y).func_77973_b(), 7);
        shinies.put(new ItemStack(Blocks.field_150451_bX).func_77973_b(), 5);
        GIFT_DELAY_TICKS = TimeUtil.minsToTicks(3);
        EXTRA_DROPS = new ItemStack[]{Witchery.Items.GENERIC.itemBatWool.createStack(5), Witchery.Items.GENERIC.itemDogTongue.createStack(5), Witchery.Items.GENERIC.itemToeOfFrog.createStack(2), Witchery.Items.GENERIC.itemOwletsWing.createStack(2), Witchery.Items.GENERIC.itemBranchEnt.createStack(1), Witchery.Items.GENERIC.itemInfernalBlood.createStack(2), Witchery.Items.GENERIC.itemCreeperHeart.createStack(2)};
        DEMON_NAMES = new String[]{"Ppaironael", "Aethon", "Tyrnak", "Beelzebuth", "Botis", "Moloch", "Taet", "Epnanaet", "Unonom", "Hexpemsazon", "Thayax", "Ethahoat", "Pruslas", "Ahtuxies", "Laripael", "Elxar", "Tarihimal", "Sapanolr", "Sahaminapiel", "Honed", "Oghmus", "Zedeson", "Halmaneop", "Nopoz", "Ekarnahox", "Sacuhatakael", "Ticos", "Arametheus", "Azmodaeus", "Larhepeis", "Topriraiz", "Rarahaimzah", "Tedrahamael", "Osaselael", "Phlegon", "Nelokhiel", "Haristum", "Zul", "Larhepeis", "Aamon", "Tramater", "Ehhbes", "Kra`an", "Quarax", "Hotesiatrem", "Surgat", "Nu`uhn", "Litedabh", "Unonom", "Bolenoz", "Hilopael", "Haristum", "Uhn", "Hiepacth", "Pemcapso", "Ankou", "Pundohien", "Koit", "Montobulus", "Amsaset", "Aropet", "Isnal", "Solael", "Exroh", "Sidragrosam", "Pnecamob", "Malashim", "Beelzebuth", "Ehohit", "Izatap", "Olon", "Assoaz", "Agalierept", "Krakus", "Umlaboor", "Aknrar", "Damaz", "Rhysus", "Pundohien", "Ba`al", "Rasuniolpas", "Anhoor", "Nyarlathotep", "Krakus", "Larhepeis", "Itakup", "Erdok", "Umlaboor", "Ezon", "Krakus", "Glassyalabolas", "Kra`an", "Ehnnat", "Terxor", "Asramel", "Tadal", "Arpzih", "Azmodaeus", "Henbolaron", "Rhysus"};
    }
}

