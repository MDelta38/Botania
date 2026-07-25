/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster.boss;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.projectile.EntityGolemOrb;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityCultistLeader
extends EntityThaumcraftBoss
implements IRangedAttackMob {
    String[] titles = new String[]{"Alberic", "Anselm", "Bastian", "Beturian", "Chabier", "Chorache", "Chuse", "Dodorol", "Ebardo", "Ferrando", "Fertus", "Guillen", "Larpe", "Obano", "Zelipe"};

    public EntityCultistLeader(World p_i1745_1_) {
        super(p_i1745_1_);
        this.func_70105_a(0.75f, 2.25f);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILongRangeAttack(this, 16.0, 1.0, 30, 40, 24.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.1, false));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AICultistHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70728_aV = 40;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(125.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(16, (Object)0);
    }

    @Override
    public void generateName() {
        int t = (int)this.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
        if (t >= 0) {
            this.func_94058_c(String.format(StatCollector.func_74838_a((String)"entity.Thaumcraft.CultistLeader.name"), this.getTitle(), ChampionModifier.mods[t].getModNameLocalized()));
        }
    }

    private String getTitle() {
        return this.titles[this.func_70096_w().func_75683_a(16)];
    }

    private void setTitle(int title) {
        this.field_70180_af.func_75692_b(16, (Object)((byte)title));
    }

    @Override
    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74774_a("title", this.func_70096_w().func_75683_a(16));
    }

    @Override
    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        this.setTitle(nbt.func_74771_c("title"));
    }

    @Override
    protected void func_82164_bB() {
        this.func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistLeaderPlate));
        this.func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistLeaderPlate));
        this.func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistLeaderPlate));
        this.func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
        if (this.field_70170_p.field_73013_u == EnumDifficulty.EASY) {
            this.func_70062_b(0, new ItemStack(ConfigItems.itemSwordVoid));
        } else {
            this.func_70062_b(0, new ItemStack(ConfigItems.itemSwordCrimson));
        }
    }

    @Override
    protected void func_82162_bC() {
        float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (this.func_70694_bm() != null && this.field_70146_Z.nextFloat() < 0.5f * f) {
            EnchantmentHelper.func_77504_a((Random)this.field_70146_Z, (ItemStack)this.func_70694_bm(), (int)((int)(7.0f + f * (float)this.field_70146_Z.nextInt(22))));
        }
    }

    @Override
    public boolean func_142014_c(EntityLivingBase el) {
        return el instanceof EntityCultist || el instanceof EntityCultistLeader;
    }

    public boolean func_70686_a(Class clazz) {
        if (clazz == EntityCultistCleric.class || clazz == EntityCultistLeader.class || clazz == EntityCultistKnight.class) {
            return false;
        }
        return super.func_70686_a(clazz);
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    @Override
    protected void func_70628_a(boolean flag, int i) {
        this.func_70099_a(new ItemStack(ConfigItems.itemLootbag, 1, 2), 1.5f);
    }

    @Override
    protected void func_70600_l(int p_70600_1_) {
    }

    @Override
    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        this.func_82164_bB();
        this.func_82162_bC();
        this.setTitle(this.field_70146_Z.nextInt(this.titles.length));
        return super.func_110161_a(p_110161_1_);
    }

    @Override
    protected void func_70619_bc() {
        super.func_70619_bc();
        ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (Entity)this, EntityCultist.class, 8.0);
        for (Entity e : list) {
            try {
                if (!(e instanceof EntityCultist) || ((EntityCultist)e).func_82165_m(Potion.field_76428_l.field_76415_H)) continue;
                ((EntityCultist)e).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 60, 1));
            }
            catch (Exception e1) {}
        }
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        if (this.func_70685_l((Entity)entitylivingbase)) {
            this.func_71038_i();
            this.func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f), entitylivingbase.field_70161_v, 30.0f, 30.0f);
            EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, (EntityLivingBase)this, entitylivingbase, true);
            blast.field_70165_t += blast.field_70159_w / 2.0;
            blast.field_70161_v += blast.field_70179_y / 2.0;
            blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
            double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
            double d1 = entitylivingbase.field_70121_D.field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
            double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
            blast.func_70186_c(d0, d1 + 2.0, d2, 0.66f, 3.0f);
            this.func_85030_a("thaumcraft:egattack", 1.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        }
    }
}

