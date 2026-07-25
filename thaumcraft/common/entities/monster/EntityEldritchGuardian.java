/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
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
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.projectile.EntityEldritchOrb;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXSonic;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;

public class EntityEldritchGuardian
extends EntityMob
implements IRangedAttackMob,
IEldritchMob {
    public float armLiftL = 0.0f;
    public float armLiftR = 0.0f;
    boolean lastBlast = false;

    public EntityEldritchGuardian(World p_i1745_1_) {
        super(p_i1745_1_);
        this.func_70661_as().func_75498_b(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILongRangeAttack(this, 8.0, 1.0, 20, 40, 24.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.0, false));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, 0, true));
        this.func_70105_a(0.8f, 2.25f);
        this.field_70728_aV = 20;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(12, (Object)0);
        this.func_70096_w().func_75682_a(13, (Object)0);
        this.func_70096_w().func_75682_a(14, (Object)0);
    }

    public int func_70658_aO() {
        return 4;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public boolean func_98052_bS() {
        return false;
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        if (source.func_82725_o()) {
            damage /= 2.0f;
        }
        return super.func_70097_a(source, damage);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            if (this.armLiftL > 0.0f) {
                this.armLiftL -= 0.05f;
            }
            if (this.armLiftR > 0.0f) {
                this.armLiftR -= 0.05f;
            }
            float x = (float)(this.field_70165_t + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f));
            float z = (float)(this.field_70161_v + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f));
            Thaumcraft.proxy.wispFXEG(this.field_70170_p, x, (float)(this.field_70163_u + 0.22 * (double)this.field_70131_O), z, (Entity)this);
        } else if (this.field_70170_p.field_73011_w.field_76574_g != Config.dimensionOuterId && (this.field_70173_aa == 0 || this.field_70173_aa % 100 == 0) && this.field_70170_p.field_73013_u != EnumDifficulty.EASY) {
            double d6 = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 576.0 : 256.0;
            for (int i = 0; i < this.field_70170_p.field_73010_i.size(); ++i) {
                double d5;
                EntityPlayer entityplayer1 = (EntityPlayer)this.field_70170_p.field_73010_i.get(i);
                if (!entityplayer1.func_70089_S() || !((d5 = entityplayer1.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v)) < d6)) continue;
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketMiscEvent(2), (EntityPlayerMP)entityplayer1);
            }
        }
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        boolean flag = super.func_70652_k(p_70652_1_);
        if (flag) {
            int i = this.field_70170_p.field_73013_u.func_151525_a();
            if (this.func_70694_bm() == null && this.func_70027_ad() && this.field_70146_Z.nextFloat() < (float)i * 0.3f) {
                p_70652_1_.func_70015_d(2 * i);
            }
        }
        return flag;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:egidle";
    }

    protected String func_70673_aS() {
        return "thaumcraft:egdeath";
    }

    public int func_70627_aG() {
        return 500;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean flag, int i) {
        AspectList al;
        ItemStack ess;
        if (this.field_70146_Z.nextBoolean()) {
            ess = new ItemStack(ConfigItems.itemWispEssence);
            al = new AspectList();
            ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.UNDEAD, 2));
            this.func_70099_a(ess, 1.0f);
        }
        if (this.field_70146_Z.nextBoolean()) {
            ess = new ItemStack(ConfigItems.itemWispEssence);
            al = new AspectList();
            ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.ELDRITCH, 2));
            this.func_70099_a(ess, 1.0f);
        }
        super.func_70628_a(flag, i);
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected void func_70600_l(int p_70600_1_) {
        this.func_145779_a(ConfigItems.itemEldritchObject, 1);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        if (this.func_110172_bL() != null && this.func_110174_bM() > 0.0f) {
            p_70014_1_.func_74768_a("HomeD", (int)this.func_110174_bM());
            p_70014_1_.func_74768_a("HomeX", this.func_110172_bL().field_71574_a);
            p_70014_1_.func_74768_a("HomeY", this.func_110172_bL().field_71572_b);
            p_70014_1_.func_74768_a("HomeZ", this.func_110172_bL().field_71573_c);
        }
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        if (p_70037_1_.func_74764_b("HomeD")) {
            this.func_110171_b(p_70037_1_.func_74762_e("HomeX"), p_70037_1_.func_74762_e("HomeY"), p_70037_1_.func_74762_e("HomeZ"), p_70037_1_.func_74762_e("HomeD"));
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        IEntityLivingData p_110161_1_1 = super.func_110161_a(p_110161_1_);
        float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (this.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId) {
            int bh = (int)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
            this.func_110149_m(this.func_110139_bj() + (float)bh);
        }
        return p_110161_1_1;
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId && this.field_70172_ad <= 0 && this.field_70173_aa % 25 == 0) {
            int bh = (int)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
            if (this.func_110139_bj() < (float)bh) {
                this.func_110149_m(this.func_110139_bj() + 1.0f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte p_70103_1_) {
        if (p_70103_1_ == 15) {
            this.armLiftL = 0.5f;
        } else if (p_70103_1_ == 16) {
            this.armLiftR = 0.5f;
        } else if (p_70103_1_ == 17) {
            this.armLiftL = 0.9f;
            this.armLiftR = 0.9f;
        } else {
            super.func_70103_a(p_70103_1_);
        }
    }

    protected boolean func_70692_ba() {
        return !this.func_110175_bO();
    }

    public float func_70047_e() {
        return 2.1f;
    }

    public boolean func_70601_bi() {
        List ents = this.field_70170_p.func_72872_a(EntityEldritchGuardian.class, AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)(this.field_70165_t + 1.0), (double)(this.field_70163_u + 1.0), (double)(this.field_70161_v + 1.0)).func_72314_b(32.0, 16.0, 32.0));
        return ents.size() > 0 ? false : super.func_70601_bi();
    }

    protected boolean func_70814_o() {
        return true;
    }

    protected float func_70599_aP() {
        return 1.5f;
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        if (this.field_70146_Z.nextFloat() > 0.1f) {
            EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, (EntityLivingBase)this);
            this.lastBlast = !this.lastBlast;
            this.field_70170_p.func_72960_a((Entity)this, this.lastBlast ? (byte)16 : 15);
            int rr = this.lastBlast ? 90 : 180;
            double xx = MathHelper.func_76134_b((float)((this.field_70177_z + (float)rr) % 360.0f / 180.0f * (float)Math.PI)) * 0.5f;
            double yy = 0.057777777 * (double)this.field_70131_O;
            double zz = MathHelper.func_76126_a((float)((this.field_70177_z + (float)rr) % 360.0f / 180.0f * (float)Math.PI)) * 0.5f;
            blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u - yy, blast.field_70161_v - zz);
            double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
            double d1 = entitylivingbase.field_70163_u - this.field_70163_u - (double)(entitylivingbase.field_70131_O / 2.0f);
            double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
            blast.func_70186_c(d0, d1, d2, 1.0f, 2.0f);
            this.func_85030_a("thaumcraft:egattack", 2.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        } else if (this.func_70685_l((Entity)entitylivingbase)) {
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXSonic(this.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0));
            try {
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (entitylivingbase instanceof EntityPlayer) {
                Thaumcraft.addWarpToPlayer((EntityPlayer)entitylivingbase, 1 + this.field_70170_p.field_73012_v.nextInt(3), true);
            }
            this.func_85030_a("thaumcraft:egscreech", 3.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
        }
    }

    public boolean func_142014_c(EntityLivingBase el) {
        return el instanceof IEldritchMob;
    }
}

