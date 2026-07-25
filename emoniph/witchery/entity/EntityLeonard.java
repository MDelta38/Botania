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
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.util.BlockActionSphere;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.RandomCollection;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityLeonard
extends EntityMob
implements IBossDisplayData,
IRangedAttackMob,
IHandleDT {
    private int attackTimer;
    private boolean isImmune;
    private int spawnDelay;
    private static final RandomCollection<SymbolEffect> SPELLS = EntityLeonard.createSpells();

    public EntityLeonard(World world) {
        super(world);
        this.func_70105_a(0.6f, 1.8f);
        this.field_70178_ae = true;
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 20, 60, 30.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70728_aV = 100;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(17, (Object)0);
        this.field_70180_af.func_75682_a(20, (Object)new Integer(0));
        this.field_70180_af.func_75682_a(21, (Object)new Integer(0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(600.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    public int func_70658_aO() {
        return 0;
    }

    public void func_70110_aj() {
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.leonard.name");
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
    }

    public int getInvulnerableStartTicks() {
        return this.field_70180_af.func_75679_c(20);
    }

    public void setInvulnerableStartTicks(int par1) {
        this.field_70180_af.func_75692_b(20, (Object)par1);
    }

    public int getLifetime() {
        return this.field_70180_af.func_75679_c(21);
    }

    public void setLifetime(int par1) {
        this.field_70180_af.func_75692_b(21, (Object)par1);
    }

    public void setInvulnerableStart() {
        this.setInvulnerableStartTicks(150);
        this.func_70606_j(this.func_110138_aP() / 4.0f);
    }

    protected void func_70619_bc() {
        if (this.getInvulnerableStartTicks() > 0) {
            int i = this.getInvulnerableStartTicks() - 1;
            if (i <= 0) {
                this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            }
            this.setInvulnerableStartTicks(i);
            if (this.field_70173_aa % 10 == 0) {
                this.func_70691_i(this.func_110138_aP() * 0.75f / 15.0f);
            }
        } else {
            super.func_70619_bc();
            this.setLifetime(this.getLifetime() + 1);
            if (this.field_70173_aa % 20 == 0) {
                this.func_70691_i(1.0f);
            }
            if (!(this.field_70173_aa % 20 != 0 || this.field_70170_p.field_73012_v.nextInt(5) != 0 || this.func_70638_az() == null && this.func_110144_aD() == null || this.field_70170_p.field_72995_K)) {
                EntityPlayer player;
                int R = 40;
                double RY = 40.0;
                double RSQ = 1600.0;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 40.0), (double)(this.field_70163_u - 40.0), (double)(this.field_70161_v - 40.0), (double)(this.field_70165_t + 40.0), (double)(this.field_70163_u + 40.0), (double)(this.field_70161_v + 40.0));
                List players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
                boolean hexed = false;
                for (EntityPlayer player2 : players) {
                    if (!(this.func_70092_e(player2.field_70165_t, this.field_70163_u, player2.field_70161_v) <= 1600.0) || player2.field_70128_L || !(player2.func_110143_aJ() > 0.0f) || player2.func_70644_a(Witchery.Potions.MORTAL_COIL)) continue;
                    hexed = true;
                    ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)player2, 1.0, 2.0, 40);
                    player2.func_70690_d(new PotionEffect(Witchery.Potions.MORTAL_COIL.field_76415_H, TimeUtil.secsToTicks(90)));
                }
                if (hexed) {
                    ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, (Entity)this, 1.0, 1.0, 40, 39168);
                } else if (this.field_70170_p.field_73012_v.nextInt(5) == 1 && players.size() > 0 && (player = (EntityPlayer)players.get(this.field_70170_p.field_73012_v.nextInt(players.size()))) != null && this.func_70092_e(player.field_70165_t, this.field_70163_u, player.field_70161_v) <= 1600.0 && !player.field_70128_L && player.func_110143_aJ() > 0.0f) {
                    ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.0, 2.0, 40);
                    switch (this.field_70170_p.field_73012_v.nextInt(10)) {
                        case 0: 
                        case 1: 
                        case 2: {
                            ArrayList<Potion> effectsToRemove = new ArrayList<Potion>();
                            Collection effects = player.func_70651_bq();
                            for (PotionEffect effect : effects) {
                                Potion potion = Potion.field_76425_a[effect.func_76456_a()];
                                if (PotionBase.isDebuff(potion) || !PotionBase.isCurable(potion)) continue;
                                effectsToRemove.add(potion);
                            }
                            for (Potion potion : effectsToRemove) {
                                player.func_82170_o(potion.field_76415_H);
                            }
                            break;
                        }
                        case 3: 
                        case 4: 
                        case 5: {
                            player.func_70690_d(new PotionEffect(Witchery.Potions.SINKING.field_76415_H, TimeUtil.secsToTicks(60), 3));
                            ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, (Entity)this, 1.0, 1.0, 40, 0x990000);
                            break;
                        }
                        case 6: 
                        case 7: 
                        case 8: {
                            player.func_70690_d(new PotionEffect(Witchery.Potions.INSANITY.field_76415_H, TimeUtil.secsToTicks(60), 3));
                            ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, (Entity)this, 1.0, 1.0, 40, 153);
                            break;
                        }
                        case 9: {
                            player.func_70690_d(new PotionEffect(Witchery.Potions.OVERHEATING.field_76415_H, TimeUtil.secsToTicks(60), 3));
                            ParticleEffect.SPELL_COLORED.send(SoundEffect.NOTE_HARP, (Entity)this, 1.0, 1.0, 40, 39321);
                        }
                    }
                }
            }
            if (this.field_70173_aa % 20 == 2) {
                if (this.field_70170_p.field_73012_v.nextInt(5) == 0) {
                    new BlockActionSphere(){

                        @Override
                        protected void onBlock(World world, int x, int y, int z) {
                            Block block = world.func_147439_a(x, y, z);
                            if (block == Witchery.Blocks.BREW_GAS || block == Witchery.Blocks.BREW_LIQUID) {
                                world.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);
                            }
                        }
                    }.drawFilledSphere(this.field_70170_p, MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u) + 2, MathHelper.func_76128_c((double)this.field_70161_v), 4);
                }
                if ((double)this.func_110143_aJ() < (double)this.func_110138_aP() * 0.5) {
                    if (this.func_70638_az() != null || this.func_110144_aD() != null) {
                        if ((double)this.func_110143_aJ() < (double)this.func_110138_aP() * 0.25 && this.field_70170_p.field_73012_v.nextInt(3) == 1 && !this.func_70644_a(Witchery.Potions.RESIZING)) {
                            this.func_70690_d(new PotionEffect(Witchery.Potions.RESIZING.field_76415_H, TimeUtil.secsToTicks(60), 3));
                        }
                        int SPAWN_DELAY = 10;
                        int R = 15;
                        double RY = 5.0;
                        double RSQ = 225.0;
                        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 15.0), (double)(this.field_70163_u - 5.0), (double)(this.field_70161_v - 15.0), (double)(this.field_70165_t + 15.0), (double)(this.field_70163_u + 5.0), (double)(this.field_70161_v + 15.0));
                        List souls = this.field_70170_p.func_72872_a(EntityLostSoul.class, bounds);
                        if (souls.size() == 0) {
                            this.isImmune = false;
                            if (--this.spawnDelay <= 0) {
                                EntityLostSoul soul;
                                int i;
                                this.removeCoilEffects(15, 5.0);
                                this.spawnDelay = 10;
                                int spawned = 0;
                                for (i = 0; i < 4 + this.field_70170_p.field_73012_v.nextInt(2); ++i) {
                                    soul = (EntityLostSoul)Infusion.spawnCreature(this.field_70170_p, EntityLostSoul.class, (int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v, null, 1, 4, ParticleEffect.SMOKE, SoundEffect.RANDOM_POP);
                                    if (soul == null) continue;
                                    soul.setTimeToLive(TimeUtil.secsToTicks(60 + this.field_70170_p.field_73012_v.nextInt(30)));
                                    ++spawned;
                                }
                                for (i = spawned; i < 3; ++i) {
                                    soul = (EntityLostSoul)Infusion.spawnCreature(this.field_70170_p, EntityLostSoul.class, (int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v, null, 0, 0, ParticleEffect.SMOKE, SoundEffect.RANDOM_POP);
                                    if (soul == null) continue;
                                    soul.setTimeToLive(TimeUtil.secsToTicks(60 + this.field_70170_p.field_73012_v.nextInt(30)));
                                }
                            }
                        } else {
                            this.isImmune = true;
                        }
                    }
                } else {
                    this.isImmune = false;
                }
            }
        }
    }

    public void func_70645_a(DamageSource source) {
        super.func_70645_a(source);
        this.removeCoilEffects(40, 40.0);
    }

    private void removeCoilEffects(int R, double RY) {
        AxisAlignedBB bounds2 = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)R), (double)(this.field_70163_u - RY), (double)(this.field_70161_v - (double)R), (double)(this.field_70165_t + (double)R), (double)(this.field_70163_u + RY), (double)(this.field_70161_v + (double)R));
        List players = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds2);
        for (EntityPlayer player : players) {
            if (player.field_70128_L || !(player.func_110143_aJ() > 0.0f) || !player.func_70644_a(Witchery.Potions.MORTAL_COIL)) continue;
            player.func_82170_o(Witchery.Potions.MORTAL_COIL.field_76415_H);
            ExtendedPlayer playerEx = ExtendedPlayer.get(player);
            if (playerEx == null) continue;
            playerEx.clearCachedIncurablePotionEffect(Witchery.Potions.MORTAL_COIL);
        }
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
        boolean immune = this.isImmune;
        if (immune) {
            return false;
        }
        if (source.func_76355_l().equals("player")) {
            if ((double)this.func_110143_aJ() < (double)this.func_110138_aP() * 0.25) {
                boolean isLarge = this.func_70644_a(Witchery.Potions.RESIZING) && this.func_70660_b(Witchery.Potions.RESIZING).func_76458_c() >= 2;
                return super.func_70097_a(source, Math.min(damage, isLarge ? 1.0f : 4.0f));
            }
            return super.func_70097_a(source, Math.min(damage, 12.0f));
        }
        return false;
    }

    @Override
    public float getCapDT(DamageSource source, float damage) {
        return this.isImmune || !source.func_76355_l().equals("player") ? 0.0f : 2.0f;
    }

    public void attackEntityFromWeakness(int damage) {
        if ((double)this.func_110143_aJ() < (double)this.func_110138_aP() * 0.4) {
            boolean isLarge = this.func_70644_a(Witchery.Potions.RESIZING) && this.func_70660_b(Witchery.Potions.RESIZING).func_76458_c() >= 2;
            super.func_70097_a(DamageSource.field_76376_m, Math.min((float)damage, isLarge ? 8.0f : 15.0f));
        }
    }

    public boolean func_70686_a(Class par1Class) {
        return super.func_70686_a(par1Class);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("PlayerCreated", this.isPlayerCreated());
        par1NBTTagCompound.func_74768_a("Invul", this.getInvulnerableStartTicks());
        par1NBTTagCompound.func_74772_a("Lifetime", (long)this.getLifetime());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
        this.setInvulnerableStartTicks(par1NBTTagCompound.func_74762_e("Invul"));
        this.setLifetime(par1NBTTagCompound.func_74762_e("Lifetime"));
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
        return "witchery:mob.leonard.say";
    }

    protected String func_70621_aR() {
        return "witchery:mob.leonard.hit";
    }

    protected String func_70673_aS() {
        return "witchery:mob.leonard.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        super.func_145780_a(par1, par2, par3, par4);
    }

    protected void func_70628_a(boolean par1, int par2) {
        Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
        int k = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), (int)enchantment.func_77325_b());
        ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
        this.func_70099_a(itemstack, 0.0f);
        this.func_70099_a(Witchery.Items.GENERIC.itemDemonHeart.createStack(), 0.0f);
        this.func_70099_a(new ItemStack(Witchery.Items.LEONARDS_URN), 0.0f);
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

    private static RandomCollection<SymbolEffect> createSpells() {
        RandomCollection<SymbolEffect> spells = new RandomCollection<SymbolEffect>();
        EffectRegistry.instance();
        spells.add(14.0, EffectRegistry.Ignianima);
        EffectRegistry.instance();
        spells.add(2.0, EffectRegistry.Expelliarmus);
        EffectRegistry.instance();
        spells.add(2.0, EffectRegistry.Flipendo);
        EffectRegistry.instance();
        spells.add(2.0, EffectRegistry.Impedimenta);
        EffectRegistry.instance();
        spells.add(1.0, EffectRegistry.Confundus);
        return spells;
    }

    public void func_82196_d(EntityLivingBase targetEntity, float par2) {
        if (this.field_70170_p.field_73012_v.nextBoolean()) {
            this.attackTimer = 10;
            this.field_70170_p.func_72960_a((Entity)this, (byte)4);
            double d0 = targetEntity.field_70165_t - this.field_70165_t;
            double d1 = targetEntity.field_70121_D.field_72338_b + (double)(targetEntity.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
            double d2 = targetEntity.field_70161_v - this.field_70161_v;
            float f1 = MathHelper.func_76129_c((float)par2) * 0.5f;
            if (!this.field_70170_p.field_72995_K) {
                this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                int count = this.field_70146_Z.nextInt(10) == 0 ? 9 : 3;
                EntitySpellEffect effect = new EntitySpellEffect(this.field_70170_p, (EntityLivingBase)this, d0 + this.field_70146_Z.nextGaussian() * (double)f1, d1, d2 + this.field_70146_Z.nextGaussian() * (double)f1, SPELLS.next(), 1);
                double d8 = 1.0;
                effect.field_70165_t = this.field_70165_t;
                effect.field_70163_u = this.field_70163_u + (double)(this.field_70131_O / 2.0f);
                effect.field_70161_v = this.field_70161_v;
                this.field_70170_p.func_72838_d((Entity)effect);
                effect.setShooter((EntityLivingBase)this);
            }
        }
    }
}

