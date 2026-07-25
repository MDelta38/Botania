/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class EntityThaumicSlime
extends EntityMob
implements IMob,
ITaintedMob {
    private static final float[] spawnChances = new float[]{1.0f, 0.75f, 0.5f, 0.25f, 0.0f, 0.25f, 0.5f, 0.75f};
    public float field_70813_a;
    public float field_70811_b;
    public float field_70812_c;
    private int slimeJumpDelay = 0;
    int launched = 10;
    int spitCounter = 100;

    public EntityThaumicSlime(World par1World) {
        super(par1World);
        int i = 1 << this.field_70146_Z.nextInt(3);
        this.field_70129_M = 0.0f;
        this.slimeJumpDelay = this.field_70146_Z.nextInt(20) + 10;
        this.setSlimeSize(i);
    }

    public EntityThaumicSlime(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        super(par1World);
        this.setSlimeSize(1);
        this.field_70163_u = (par2EntityLiving.field_70121_D.field_72338_b + par2EntityLiving.field_70121_D.field_72337_e) / 2.0;
        double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t;
        double var8 = par3EntityLiving.field_70121_D.field_72338_b + (double)(par3EntityLiving.field_70131_O / 3.0f) - this.field_70163_u;
        double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v;
        double var12 = MathHelper.func_76133_a((double)(var6 * var6 + var10 * var10));
        if (var12 >= 1.0E-7) {
            float var14 = (float)(Math.atan2(var10, var6) * 180.0 / Math.PI) - 90.0f;
            float var15 = (float)(-(Math.atan2(var8, var12) * 180.0 / Math.PI));
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.func_70012_b(par2EntityLiving.field_70165_t + var16, this.field_70163_u, par2EntityLiving.field_70161_v + var18, var14, var15);
            this.field_70129_M = 0.0f;
            float var20 = (float)var12 * 0.2f;
            this.setThrowableHeading(var6, var8 + (double)var20, var10, 1.5f, 1.0f);
        }
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float var9 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        par3 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        par5 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        this.field_70159_w = par1 *= (double)par7;
        this.field_70181_x = par3 *= (double)par7;
        this.field_70179_y = par5 *= (double)par7;
        float var10 = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
        this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
        this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0 / Math.PI);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)new Byte(1));
    }

    public void setSlimeSize(int par1) {
        this.field_70180_af.func_75692_b(16, (Object)new Byte((byte)par1));
        float ss = (float)Math.sqrt(par1);
        this.func_70105_a(0.25f * ss + 0.25f, 0.25f * ss + 0.25f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)par1);
        this.func_70606_j(this.func_110138_aP());
        this.field_70728_aV = (int)ss;
    }

    protected int getAttackStrength() {
        return this.getSlimeSize();
    }

    public int getSlimeSize() {
        return this.field_70180_af.func_75683_a(16);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("Size", this.getSlimeSize() - 1);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setSlimeSize(par1NBTTagCompound.func_74762_e("Size") + 1);
    }

    protected String getSlimeParticle() {
        return "slime";
    }

    protected String getJumpSound() {
        return "mob.slime." + (this.getSlimeSize() > 3 ? "big" : "small");
    }

    public void func_70071_h_() {
        int j;
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u.func_151525_a() == 0 && this.getSlimeSize() > 0) {
            this.field_70128_L = true;
        }
        this.field_70811_b += (this.field_70813_a - this.field_70811_b) * 0.5f;
        this.field_70812_c = this.field_70811_b;
        boolean flag = this.field_70122_E;
        super.func_70071_h_();
        int i = (int)Math.sqrt(this.getSlimeSize());
        if (this.launched > 0) {
            --this.launched;
            if (this.field_70170_p.field_72995_K) {
                for (j = 0; j < i * (this.launched + 1); ++j) {
                    Thaumcraft.proxy.slimeJumpFX((Entity)this, i);
                }
            }
        }
        if (this.field_70122_E && !flag) {
            if (this.field_70170_p.field_72995_K) {
                for (j = 0; j < i * 8; ++j) {
                    Thaumcraft.proxy.slimeJumpFX((Entity)this, i);
                }
            }
            if (this.makesSoundOnLand()) {
                this.func_85030_a(this.getJumpSound(), this.func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            }
            this.field_70813_a = -0.5f;
        } else if (!this.field_70122_E && flag) {
            this.field_70813_a = 1.0f;
        }
        this.func_70808_l();
        if (this.field_70170_p.field_72995_K) {
            float ff = (float)Math.sqrt(this.getSlimeSize());
            this.func_70105_a(0.6f * ff, 0.6f * ff);
        }
    }

    protected EntityThaumicSlime getClosestMergableSlime() {
        EntityThaumicSlime closest = null;
        double distance = Double.MAX_VALUE;
        List ents = this.field_70170_p.func_72872_a(EntityThaumicSlime.class, AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v).func_72314_b(16.0, 8.0, 16.0));
        if (ents != null && ents.size() > 0) {
            for (Object s : ents) {
                EntityThaumicSlime slime = (EntityThaumicSlime)s;
                if (slime.func_145782_y() != this.func_145782_y() && slime.field_70173_aa > 100 && slime.getSlimeSize() < 100 && this.func_70068_e((Entity)slime) < distance) {
                    closest = slime;
                }
                distance = this.func_70068_e((Entity)slime);
            }
        }
        return closest;
    }

    protected void func_70626_be() {
        this.func_70623_bb();
        EntityPlayer entityplayer = this.field_70170_p.func_72856_b((Entity)this, 16.0);
        if (entityplayer != null) {
            if (this.spitCounter > 0) {
                --this.spitCounter;
            }
            this.func_70625_a((Entity)entityplayer, 10.0f, 20.0f);
            if (this.func_70032_d((Entity)entityplayer) > 4.0f && this.spitCounter <= 0 && this.getSlimeSize() > 3) {
                this.spitCounter = 101;
                if (!this.field_70170_p.field_72995_K) {
                    EntityThaumicSlime flyslime = new EntityThaumicSlime(this.field_70170_p, (EntityLivingBase)this, (EntityLivingBase)entityplayer);
                    this.field_70170_p.func_72838_d((Entity)flyslime);
                }
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:gore", 1.0f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f) * 0.8f);
                this.setSlimeSize(this.getSlimeSize() - 1);
            }
        } else {
            EntityThaumicSlime slime = this.getClosestMergableSlime();
            if (slime != null) {
                this.func_70625_a((Entity)slime, 10.0f, 20.0f);
                if (this.func_70032_d((Entity)slime) < this.field_70130_N + slime.field_70130_N) {
                    slime.setSlimeSize(Math.min(100, slime.getSlimeSize() + this.getSlimeSize()));
                    this.func_70106_y();
                }
            }
        }
        if (this.field_70122_E && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.getJumpDelay();
            if (entityplayer != null) {
                this.slimeJumpDelay /= 3;
            }
            this.field_70703_bu = true;
            if (this.makesSoundOnJump()) {
                this.func_85030_a(this.getJumpSound(), this.func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f) * 0.8f);
            }
            this.field_70702_br = 1.0f - this.field_70146_Z.nextFloat() * 2.0f;
            this.field_70701_bs = (float)(1.0 * Math.sqrt(this.getSlimeSize()));
        } else {
            this.field_70703_bu = false;
            if (this.field_70122_E) {
                this.field_70701_bs = 0.0f;
                this.field_70702_br = 0.0f;
            }
        }
    }

    protected void func_70808_l() {
        this.field_70813_a *= 0.6f;
    }

    protected int getJumpDelay() {
        return this.field_70146_Z.nextInt(16) + 8;
    }

    protected EntityThaumicSlime createInstance() {
        return new EntityThaumicSlime(this.field_70170_p);
    }

    public void func_70106_y() {
        int i = (int)Math.sqrt(this.getSlimeSize());
        if (!this.field_70170_p.field_72995_K && i > 1 && this.func_110143_aJ() <= 0.0f) {
            for (int k = 0; k < i; ++k) {
                float f = ((float)(k % 2) - 0.5f) * (float)i / 4.0f;
                float f1 = ((float)(k / 2) - 0.5f) * (float)i / 4.0f;
                EntityThaumicSlime entityslime = this.createInstance();
                entityslime.setSlimeSize(1);
                entityslime.func_70012_b(this.field_70165_t + (double)f, this.field_70163_u + 0.5, this.field_70161_v + (double)f1, this.field_70146_Z.nextFloat() * 360.0f, 0.0f);
                this.field_70170_p.func_72838_d((Entity)entityslime);
            }
        }
        super.func_70106_y();
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
        if (this.canDamagePlayer()) {
            int i = (int)Math.max(1.0, Math.sqrt(this.getSlimeSize()));
            if (this.launched > 0 && i == 2) {
                i = 3;
            }
            if (this.func_70685_l((Entity)par1EntityPlayer) && this.func_70068_e((Entity)par1EntityPlayer) < 0.8 * (double)i * 0.8 * (double)i && par1EntityPlayer.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)this.getAttackStrength())) {
                this.func_85030_a("mob.attack", 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
            }
        }
    }

    protected boolean canDamagePlayer() {
        return this.getSlimeSize() > 0;
    }

    protected String func_70621_aR() {
        return "mob.slime." + (this.getSlimeSize() > 3 ? "big" : "small");
    }

    protected String func_70673_aS() {
        return "mob.slime." + (this.getSlimeSize() > 3 ? "big" : "small");
    }

    protected Item func_146068_u() {
        return this.getSlimeSize() < 3 ? ConfigItems.itemResource : Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.getSlimeSize() < 3 && this.field_70146_Z.nextInt(3) == 0) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
        }
    }

    protected float func_70599_aP() {
        return 0.1f * (float)Math.sqrt(this.getSlimeSize());
    }

    public int func_70646_bf() {
        return 0;
    }

    protected boolean makesSoundOnJump() {
        return this.getSlimeSize() > 3;
    }

    protected boolean makesSoundOnLand() {
        return this.getSlimeSize() > 5;
    }
}

