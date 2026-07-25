/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.ai.misc.AIAltarFocus;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.projectile.EntityGolemOrb;

public class EntityCultistCleric
extends EntityCultist
implements IRangedAttackMob,
IEntityAdditionalSpawnData {
    public EntityCultistCleric(World p_i1745_1_) {
        super(p_i1745_1_);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIAltarFocus(this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILongRangeAttack(this, 2.0, 1.0, 20, 40, 24.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.0, false));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AICultistHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
    }

    @Override
    protected void func_82164_bB() {
        this.func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistRobe));
        this.func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistRobe));
        this.func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistRobe));
        float f = this.field_70146_Z.nextFloat();
        float f2 = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.3f : 0.1f;
        if (f < f2) {
            this.func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
        }
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
        double d1 = entitylivingbase.field_70121_D.field_72338_b + (double)(entitylivingbase.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
        double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
        this.func_71038_i();
        if (this.field_70146_Z.nextFloat() > 0.66f) {
            EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, (EntityLivingBase)this, entitylivingbase, true);
            blast.field_70165_t += blast.field_70159_w / 2.0;
            blast.field_70161_v += blast.field_70179_y / 2.0;
            blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
            blast.func_70186_c(d0, d1 + 2.0, d2, 0.66f, 3.0f);
            this.func_85030_a("thaumcraft:egattack", 1.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        } else {
            float f1 = MathHelper.func_76129_c((float)f) * 0.5f;
            this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            for (int i = 0; i < 3; ++i) {
                EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.field_70170_p, (EntityLivingBase)this, d0 + this.field_70146_Z.nextGaussian() * (double)f1, d1, d2 + this.field_70146_Z.nextGaussian() * (double)f1);
                entitysmallfireball.field_70163_u = this.field_70163_u + (double)(this.field_70131_O / 2.0f) + 0.5;
                this.field_70170_p.func_72838_d((Entity)entitysmallfireball);
            }
        }
    }

    @Override
    protected boolean func_70692_ba() {
        return !this.getIsRitualist();
    }

    @Override
    public void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
    }

    public boolean getIsRitualist() {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void setIsRitualist(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 & 0xFFFFFFFE)));
        }
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.func_85032_ar()) {
            return false;
        }
        this.setIsRitualist(false);
        return super.func_70097_a(p_70097_1_, p_70097_2_);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70180_af.func_75692_b(16, (Object)par1NBTTagCompound.func_74771_c("Flags"));
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.func_110172_bL().field_71574_a);
        data.writeInt(this.func_110172_bL().field_71572_b);
        data.writeInt(this.func_110172_bL().field_71573_c);
    }

    public void readSpawnData(ByteBuf data) {
        this.func_110171_b(data.readInt(), data.readInt(), data.readInt(), 8);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.getIsRitualist()) {
            double d0 = (double)this.func_110172_bL().field_71574_a + 0.5 - this.field_70165_t;
            double d1 = (double)this.func_110172_bL().field_71572_b + 1.5 - (this.field_70163_u + (double)this.func_70047_e());
            double d2 = (double)this.func_110172_bL().field_71573_c + 0.5 - this.field_70161_v;
            double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
            float f = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
            float f1 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
            this.field_70125_A = this.updateRotation(this.field_70125_A, f1, 10.0f);
            this.field_70759_as = this.updateRotation(this.field_70759_as, f, this.func_70646_bf());
        }
    }

    private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
        float f3 = MathHelper.func_76142_g((float)(p_75652_2_ - p_75652_1_));
        if (f3 > p_75652_3_) {
            f3 = p_75652_3_;
        }
        if (f3 < -p_75652_3_) {
            f3 = -p_75652_3_;
        }
        return p_75652_1_ + f3;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:chant";
    }

    public int func_70627_aG() {
        return 500;
    }
}

