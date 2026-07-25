/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.ai.EntityAIDimensionalFollowOwner;
import com.emoniph.witchery.entity.ai.EntityAISitAndStay;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityToad
extends EntityTameable
implements IFamiliar {
    private int timeToLive = -1;
    private boolean poisoned = false;

    public EntityToad(World par1World) {
        super(par1World);
        this.func_70105_a(0.8f, 0.8f);
        this.func_70661_as().func_75495_e(true);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISitAndStay(this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIDimensionalFollowOwner(this, 1.0, 10.0f, 2.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.25, Items.field_151078_bh, false));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMate((EntityAnimal)this, 1.0));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.func_70903_f(false);
    }

    public void setTimeToLive(int i, boolean poisoned) {
        this.timeToLive = i;
        this.poisoned = poisoned;
    }

    public boolean isTemp() {
        return this.timeToLive != -1;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000001192092895);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0);
    }

    public int func_70658_aO() {
        return super.func_70658_aO() + (this.isFamiliar() ? 5 : 0);
    }

    public int func_70627_aG() {
        return super.func_70627_aG() * 2;
    }

    @Override
    public void setMaxHealth(float maxHealth) {
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)maxHealth);
        this.func_70606_j(maxHealth);
        this.setFamiliar(true);
    }

    public EntityLivingBase func_70902_q() {
        if (this.isFamiliar() && !this.field_70170_p.field_72995_K) {
            return TameableUtil.getOwnerAccrossDimensions(this);
        }
        return super.func_70902_q();
    }

    protected int func_70682_h(int par1) {
        return par1;
    }

    protected void func_70069_a(float par1) {
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
        this.field_70180_af.func_75692_b(18, (Object)Float.valueOf(this.func_110143_aJ()));
        if (this.field_70170_p != null && !this.field_70128_L && !this.field_70170_p.field_72995_K && this.timeToLive != -1 && --this.timeToLive == 0) {
            this.func_70106_y();
            if (this.poisoned) {
                AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(3.0, 2.0, 3.0);
                List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                if (list1 != null && !list1.isEmpty()) {
                    for (EntityLivingBase entitylivingbase : list1) {
                        double d0 = this.func_70068_e((Entity)entitylivingbase);
                        if (!(d0 < 9.0)) continue;
                        double d1 = 1.0 - Math.sqrt(d0) / 3.0;
                        entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 60, 0));
                    }
                }
                ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, (Entity)this, 1.0, 1.0, 16);
            }
            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, (Entity)this, 0.5, 0.5, 16);
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, (Object)new Float(this.func_110143_aJ()));
        this.field_70180_af.func_75682_a(19, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(20, (Object)new Byte((byte)BlockColored.func_150032_b((int)(this.field_70170_p != null ? this.field_70170_p.field_73012_v.nextInt(16) : new Random().nextInt(16)))));
        this.field_70180_af.func_75682_a(26, (Object)0);
    }

    @Override
    public boolean isFamiliar() {
        return this.field_70180_af.func_75683_a(26) > 0;
    }

    public void setFamiliar(boolean familiar) {
        this.field_70180_af.func_75692_b(26, (Object)((byte)(familiar ? 1 : 0)));
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.slime.small", 0.15f, 1.0f);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("SkinColor", (byte)this.getSkinColor());
        par1NBTTagCompound.func_74774_a("Familiar", (byte)(this.isFamiliar() ? 1 : 0));
        par1NBTTagCompound.func_74768_a("SuicideIn", this.timeToLive);
        par1NBTTagCompound.func_74757_a("Poisonous", this.poisoned);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        if (par1NBTTagCompound.func_74764_b("SkinColor")) {
            this.setSkinColor(par1NBTTagCompound.func_74771_c("SkinColor"));
        }
        if (par1NBTTagCompound.func_74764_b("Familiar")) {
            this.setFamiliar(par1NBTTagCompound.func_74771_c("Familiar") > 0);
        }
        this.timeToLive = par1NBTTagCompound.func_74764_b("SuicideIn") ? par1NBTTagCompound.func_74762_e("SuicideIn") : -1;
        this.poisoned = par1NBTTagCompound.func_74764_b("Poisonous") ? par1NBTTagCompound.func_74767_n("Poisonous") : false;
    }

    protected String func_70639_aQ() {
        return "witchery:mob.toad.toad_croak";
    }

    protected String func_70621_aR() {
        return "witchery:mob.toad.toad_hurt";
    }

    protected String func_70673_aS() {
        return "witchery:mob.toad.toad_hurt";
    }

    protected float func_70599_aP() {
        return 0.4f;
    }

    protected Item func_146068_u() {
        if (!this.isTemp()) {
            return Items.field_151123_aH;
        }
        return super.func_146068_u();
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public void func_70071_h_() {
        this.field_70178_ae = this.isFamiliar();
        super.func_70071_h_();
        if (!(this.func_70906_o() || this.field_70170_p.field_72995_K || this.field_70159_w == 0.0 && this.field_70179_y == 0.0 || this.func_70090_H())) {
            this.func_70683_ar().func_75660_a();
        }
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.8f;
    }

    public int func_70646_bf() {
        return this.func_70906_o() ? 20 : super.func_70646_bf();
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        Entity entity = par1DamageSource.func_76346_g();
        if (!this.isFamiliar()) {
            this.func_70904_g(false);
        }
        if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            par2 = (par2 + 1.0f) / 2.0f;
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    public void func_70903_f(boolean par1) {
        super.func_70903_f(par1);
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        if (this.isTemp()) {
            return true;
        }
        ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
        if (this.func_70909_n()) {
            if (TameableUtil.isOwner(this, par1EntityPlayer) && this.isFamiliar() && par1EntityPlayer.func_70093_af() && this.func_70906_o()) {
                if (!this.field_70170_p.field_72995_K) {
                    Familiar.dismissFamiliar(par1EntityPlayer, this);
                }
                return true;
            }
            if (itemstack != null) {
                if (itemstack.func_77973_b() == Items.field_151078_bh && this.func_110143_aJ() < this.func_110138_aP()) {
                    if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
                        --itemstack.field_77994_a;
                    }
                    this.func_70691_i(10.0f);
                    if (itemstack.field_77994_a <= 0) {
                        par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
                    }
                    return true;
                }
                if (itemstack.func_77973_b() == Items.field_151100_aR) {
                    int i = BlockColored.func_150032_b((int)itemstack.func_77960_j());
                    if (i != this.getSkinColor()) {
                        this.setSkinColor(i);
                        if (!par1EntityPlayer.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0) {
                            par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
                        }
                        return true;
                    }
                } else if (itemstack.func_77973_b() == Items.field_151057_cb || itemstack.func_77973_b() == Witchery.Items.POLYNESIA_CHARM || itemstack.func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM) {
                    return false;
                }
            }
            if (TameableUtil.isOwner(this, par1EntityPlayer) && !this.func_70877_b(itemstack)) {
                if (!this.field_70170_p.field_72995_K) {
                    this.func_70904_g(!this.func_70906_o());
                    this.func_70683_ar().func_75661_b();
                    this.field_70703_bu = false;
                    this.func_70778_a(null);
                    this.func_70784_b(null);
                    this.func_70624_b(null);
                }
                return true;
            }
        } else if (itemstack != null && itemstack.func_77973_b() == Items.field_151078_bh) {
            if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
            }
            if (itemstack.field_77994_a <= 0) {
                par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
            }
            if (!this.field_70170_p.field_72995_K) {
                if (this.field_70146_Z.nextInt(3) == 0) {
                    this.func_70903_f(true);
                    this.func_110163_bv();
                    this.func_70778_a(null);
                    this.func_70624_b(null);
                    this.func_70904_g(true);
                    TameableUtil.setOwner(this, par1EntityPlayer);
                    this.func_70908_e(true);
                    this.field_70170_p.func_72960_a((Entity)this, (byte)7);
                } else {
                    this.func_70908_e(false);
                    this.field_70170_p.func_72960_a((Entity)this, (byte)6);
                }
            }
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.toad.name");
    }

    public boolean func_70877_b(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.func_77973_b() == Items.field_151078_bh;
    }

    public int getSkinColor() {
        return this.field_70180_af.func_75683_a(20) & 0xF;
    }

    public void setSkinColor(int par1) {
        this.field_70180_af.func_75692_b(20, (Object)((byte)(par1 & 0xF)));
    }

    public EntityToad spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
        EntityToad entity = new EntityToad(this.field_70170_p);
        if (TameableUtil.hasOwner(this)) {
            entity.func_110163_bv();
            entity.setSkinColor(this.getSkinColor());
        }
        return entity;
    }

    public boolean func_70878_b(EntityAnimal par1EntityAnimal) {
        if (par1EntityAnimal == this) {
            return false;
        }
        if (!this.func_70909_n()) {
            return false;
        }
        if (!(par1EntityAnimal instanceof EntityToad)) {
            return false;
        }
        EntityToad entity = (EntityToad)par1EntityAnimal;
        return !entity.func_70909_n() ? false : (entity.func_70906_o() ? false : this.func_70880_s() && entity.func_70880_s());
    }

    public boolean func_70922_bv() {
        return this.field_70180_af.func_75683_a(19) == 1;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
        if (!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
            EntityToad entity;
            if (par1EntityLivingBase instanceof EntityToad && (entity = (EntityToad)par1EntityLivingBase).func_70909_n() && entity.func_70902_q() == par2EntityLivingBase) {
                return false;
            }
            return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).func_96122_a((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).func_110248_bS();
        }
        return false;
    }

    public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable) {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }

    @Override
    public void clearFamiliar() {
        this.setFamiliar(false);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0);
        this.func_70606_j(10.0f);
    }
}

