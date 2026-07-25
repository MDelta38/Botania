/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntitySpider$GroupData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.monster.EntityCultist;

public class EntityEldritchCrab
extends EntityMob {
    public EntityEldritchCrab(World par1World) {
        super(par1World);
        this.func_70105_a(0.8f, 0.6f);
        this.field_70728_aV = 6;
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.63f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.0, false));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, 0, true));
    }

    public double func_70033_W() {
        return this.func_70115_ae() ? 0.5 : 0.0;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.hasHelm() ? 0.275 : 0.3);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(22, (Object)new Byte(0));
    }

    public boolean func_98052_bS() {
        return false;
    }

    public int func_70658_aO() {
        return this.hasHelm() ? 5 : 0;
    }

    public IEntityLivingData func_110161_a(IEntityLivingData livingData) {
        int i;
        if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
            this.setHelm(true);
        } else {
            this.setHelm(this.field_70146_Z.nextFloat() < 0.33f);
        }
        if (livingData == null) {
            livingData = new EntitySpider.GroupData();
            if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD && this.field_70170_p.field_73012_v.nextFloat() < 0.1f * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)) {
                ((EntitySpider.GroupData)livingData).func_111104_a(this.field_70170_p.field_73012_v);
            }
        }
        if (livingData instanceof EntitySpider.GroupData && (i = ((EntitySpider.GroupData)livingData).field_111105_a) > 0 && Potion.field_76425_a[i] != null) {
            this.func_70690_d(new PotionEffect(i, Integer.MAX_VALUE));
        }
        return super.func_110161_a(livingData);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public boolean hasHelm() {
        return (this.field_70180_af.func_75683_a(22) & 1) != 0;
    }

    public void setHelm(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(22);
        if (par1) {
            this.field_70180_af.func_75692_b(22, (Object)((byte)(var2 | 1)));
        } else {
            this.field_70180_af.func_75692_b(22, (Object)((byte)(var2 & 0xFFFFFFFE)));
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa < 20) {
            this.field_70143_R = 0.0f;
        }
        if (this.field_70154_o == null && this.func_70643_av() != null && this.func_70643_av().field_70153_n == null && !this.field_70122_E && !this.hasHelm() && !this.func_70643_av().field_70128_L && this.field_70163_u - this.func_70643_av().field_70163_u >= (double)(this.func_70643_av().field_70131_O / 2.0f) && this.func_70068_e((Entity)this.func_70643_av()) < 4.0) {
            this.func_70078_a((Entity)this.func_70643_av());
        }
        if (!this.field_70170_p.field_72995_K && this.field_70154_o != null && this.field_70724_aR <= 0) {
            this.field_70724_aR = 10 + this.field_70146_Z.nextInt(10);
            this.func_70652_k(this.field_70154_o);
            if (this.field_70154_o != null && (double)this.field_70146_Z.nextFloat() < 0.2) {
                this.func_110145_l(this.field_70154_o);
            }
        }
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
        super.func_70628_a(p_70628_1_, p_70628_2_);
        if (p_70628_1_ && (this.field_70146_Z.nextInt(3) == 0 || this.field_70146_Z.nextInt(1 + p_70628_2_) > 0)) {
            this.func_145779_a(Items.field_151079_bi, 1);
        }
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        if (super.func_70652_k(p_70652_1_)) {
            this.func_85030_a("thaumcraft:crabclaw", 1.0f, 0.9f + this.field_70170_p.field_73012_v.nextFloat() * 0.2f);
            return true;
        }
        return false;
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        boolean b = super.func_70097_a(source, damage);
        if (this.hasHelm() && this.func_110143_aJ() / this.func_110138_aP() <= 0.5f) {
            this.setHelm(false);
            this.func_70669_a(new ItemStack(ConfigItems.itemChestCultistPlate));
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        }
        return b;
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70180_af.func_75692_b(22, (Object)par1NBTTagCompound.func_74771_c("Flags"));
        if (!this.hasHelm()) {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
        }
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(22));
    }

    public int func_70627_aG() {
        return 160;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:crabtalk";
    }

    protected String func_70621_aR() {
        return "game.hostile.hurt";
    }

    protected String func_70673_aS() {
        return "thaumcraft:crabdeath";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.func_85030_a("mob.spider.step", 0.15f, 1.0f);
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean func_70687_e(PotionEffect p_70687_1_) {
        return p_70687_1_.func_76456_a() == Potion.field_76436_u.field_76415_H ? false : super.func_70687_e(p_70687_1_);
    }

    public boolean func_142014_c(EntityLivingBase el) {
        return el instanceof EntityEldritchCrab;
    }
}

