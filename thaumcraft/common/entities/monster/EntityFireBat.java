/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;

public class EntityFireBat
extends EntityMob {
    private ChunkCoordinates currentFlightTarget;
    public EntityPlayer owner = null;
    public int damBonus = 0;

    public EntityFireBat(World par1World) {
        super(par1World);
        this.func_70105_a(0.5f, 0.9f);
        this.setIsBatHanging(true);
        this.field_70178_ae = true;
    }

    public void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        return 0xF000F0;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    protected float func_70599_aP() {
        return 0.1f;
    }

    protected float func_70647_i() {
        return super.func_70647_i() * 0.95f;
    }

    protected String func_70639_aQ() {
        return this.getIsBatHanging() && this.field_70146_Z.nextInt(4) != 0 ? null : "mob.bat.idle";
    }

    protected String func_70621_aR() {
        return "mob.bat.hurt";
    }

    protected String func_70673_aS() {
        return "mob.bat.death";
    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.getIsDevil() ? 15.0 : 5.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.getIsSummoned() ? (double)((this.getIsDevil() ? 3 : 2) + this.damBonus) : 1.0);
    }

    public boolean getIsBatHanging() {
        return Utils.getBit(this.field_70180_af.func_75683_a(16), 0);
    }

    public void setIsBatHanging(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.setBit(var2, 0)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.clearBit(var2, 0)));
        }
    }

    public boolean getIsSummoned() {
        return Utils.getBit(this.field_70180_af.func_75683_a(16), 1);
    }

    public void setIsSummoned(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.setBit(var2, 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.clearBit(var2, 1)));
        }
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(par1 ? (double)((this.getIsDevil() ? 3 : 2) + this.damBonus) : 1.0);
    }

    public boolean getIsExplosive() {
        return Utils.getBit(this.field_70180_af.func_75683_a(16), 2);
    }

    public void setIsExplosive(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.setBit(var2, 2)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.clearBit(var2, 2)));
        }
    }

    public boolean getIsDevil() {
        return Utils.getBit(this.field_70180_af.func_75683_a(16), 3);
    }

    public void setIsDevil(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.setBit(var2, 3)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.clearBit(var2, 3)));
        }
        if (par1) {
            this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.getIsSummoned() ? (double)((par1 ? 3 : 2) + this.damBonus) : 1.0);
        }
    }

    public boolean getIsVampire() {
        return Utils.getBit(this.field_70180_af.func_75683_a(16), 4);
    }

    public void setIsVampire(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.setBit(var2, 4)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)Utils.clearBit(var2, 4)));
        }
    }

    protected boolean func_70650_aV() {
        return false;
    }

    public void func_70636_d() {
        if (this.func_70026_G()) {
            this.func_70097_a(DamageSource.field_76369_e, 1.0f);
        }
        super.func_70636_d();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.getIsExplosive()) {
            Thaumcraft.proxy.drawGenericParticles(this.field_70170_p, this.field_70169_q + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f), this.field_70167_r + (double)(this.field_70131_O / 2.0f) + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f), this.field_70166_s + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f), 0.0, 0.0, 0.0, 1.0f, 1.0f, 1.0f, 0.8f, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 1.0f + this.field_70146_Z.nextFloat() * 0.5f);
        }
        if (this.getIsBatHanging()) {
            this.field_70179_y = 0.0;
            this.field_70181_x = 0.0;
            this.field_70159_w = 0.0;
            this.field_70163_u = (double)MathHelper.func_76128_c((double)this.field_70163_u) + 1.0 - (double)this.field_70131_O;
        } else {
            this.field_70181_x *= (double)0.6f;
        }
        if (this.field_70170_p.field_72995_K && !this.getIsVampire()) {
            this.field_70170_p.func_72869_a("smoke", this.field_70169_q + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70167_r + (double)(this.field_70131_O / 2.0f) + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70166_s + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), 0.0, 0.0, 0.0);
            this.field_70170_p.func_72869_a("flame", this.field_70169_q + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70167_r + (double)(this.field_70131_O / 2.0f) + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70166_s + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), 0.0, 0.0, 0.0);
        }
    }

    protected void func_70626_be() {
        super.func_70626_be();
        if (this.getIsBatHanging()) {
            if (!this.field_70170_p.func_147445_c(MathHelper.func_76128_c((double)this.field_70165_t), (int)this.field_70163_u + 1, MathHelper.func_76128_c((double)this.field_70161_v), false)) {
                this.setIsBatHanging(false);
                this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            } else {
                if (this.field_70146_Z.nextInt(200) == 0) {
                    this.field_70759_as = this.field_70146_Z.nextInt(360);
                }
                if (this.field_70170_p.func_72890_a((Entity)this, 4.0) != null) {
                    this.setIsBatHanging(false);
                    this.field_70170_p.func_72889_a((EntityPlayer)null, 1015, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                }
            }
        } else {
            if (this.field_70789_a == null) {
                if (this.getIsSummoned()) {
                    this.func_70097_a(DamageSource.field_76377_j, 2.0f);
                }
                if (!(this.currentFlightTarget == null || this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c) && this.currentFlightTarget.field_71572_b >= 1)) {
                    this.currentFlightTarget = null;
                }
                if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || this.currentFlightTarget.func_71569_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0f) {
                    this.currentFlightTarget = new ChunkCoordinates((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
                }
                double var1 = (double)this.currentFlightTarget.field_71574_a + 0.5 - this.field_70165_t;
                double var3 = (double)this.currentFlightTarget.field_71572_b + 0.1 - this.field_70163_u;
                double var5 = (double)this.currentFlightTarget.field_71573_c + 0.5 - this.field_70161_v;
                this.field_70159_w += (Math.signum(var1) * 0.5 - this.field_70159_w) * (double)0.1f;
                this.field_70181_x += (Math.signum(var3) * (double)0.7f - this.field_70181_x) * (double)0.1f;
                this.field_70179_y += (Math.signum(var5) * 0.5 - this.field_70179_y) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.func_76142_g((float)(var7 - this.field_70177_z));
                this.field_70701_bs = 0.5f;
                this.field_70177_z += var8;
                if (this.field_70146_Z.nextInt(100) == 0 && this.field_70170_p.func_147445_c(MathHelper.func_76128_c((double)this.field_70165_t), (int)this.field_70163_u + 1, MathHelper.func_76128_c((double)this.field_70161_v), false)) {
                    this.setIsBatHanging(true);
                }
            } else if (this.field_70789_a != null) {
                double var1 = this.field_70789_a.field_70165_t - this.field_70165_t;
                double var3 = this.field_70789_a.field_70163_u + (double)(this.field_70789_a.func_70047_e() * 0.66f) - this.field_70163_u;
                double var5 = this.field_70789_a.field_70161_v - this.field_70161_v;
                this.field_70159_w += (Math.signum(var1) * 0.5 - this.field_70159_w) * (double)0.1f;
                this.field_70181_x += (Math.signum(var3) * (double)0.7f - this.field_70181_x) * (double)0.1f;
                this.field_70179_y += (Math.signum(var5) * 0.5 - this.field_70179_y) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.func_76142_g((float)(var7 - this.field_70177_z));
                this.field_70701_bs = 0.5f;
                this.field_70177_z += var8;
            }
            if (this.field_70789_a instanceof EntityPlayer && ((EntityPlayer)this.field_70789_a).field_71075_bZ.field_75102_a) {
                this.field_70789_a = null;
            }
        }
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70069_a(float par1) {
    }

    protected void func_70064_a(double par1, boolean par3) {
    }

    public boolean func_145773_az() {
        return true;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar() || par1DamageSource.func_76347_k() || par1DamageSource.func_94541_c()) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && this.getIsBatHanging()) {
            this.setIsBatHanging(false);
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    protected void func_70785_a(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < Math.max(2.5f, par1Entity.field_70130_N * 1.1f) && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            if (this.getIsSummoned()) {
                EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
            }
            if (this.getIsVampire()) {
                if (this.owner != null && !this.owner.func_82165_m(Potion.field_76428_l.field_76415_H)) {
                    this.owner.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 26, 1));
                }
                this.func_70691_i(1.0f);
            }
            this.field_70724_aR = 20;
            if (!(!this.getIsExplosive() && this.field_70170_p.field_73012_v.nextInt(10) != 0 || this.field_70170_p.field_72995_K || this.getIsDevil())) {
                par1Entity.field_70172_ad = 0;
                this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5f + (this.getIsExplosive() ? (float)this.damBonus * 0.33f : 0.0f), false, false);
                this.func_70106_y();
            } else if (this.getIsVampire() || this.field_70170_p.field_73012_v.nextBoolean()) {
                double mx = par1Entity.field_70159_w;
                double my = par1Entity.field_70181_x;
                double mz = par1Entity.field_70179_y;
                this.func_70652_k(par1Entity);
                par1Entity.field_70160_al = false;
                par1Entity.field_70159_w = mx;
                par1Entity.field_70181_x = my;
                par1Entity.field_70179_y = mz;
            } else {
                par1Entity.func_70015_d(this.getIsSummoned() ? 4 : 2);
            }
            this.field_70170_p.func_72956_a((Entity)this, "mob.bat.hurt", 0.5f, 0.9f + this.field_70170_p.field_73012_v.nextFloat() * 0.2f);
        }
    }

    protected Entity func_70782_k() {
        double var1 = 12.0;
        return this.getIsSummoned() ? null : this.field_70170_p.func_72856_b((Entity)this, var1);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70180_af.func_75692_b(16, (Object)par1NBTTagCompound.func_74771_c("BatFlags"));
        this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("BatFlags", this.field_70180_af.func_75683_a(16));
        par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
    }

    public boolean func_70601_bi() {
        int var5;
        int var3;
        int var1 = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b);
        int var2 = MathHelper.func_76128_c((double)this.field_70165_t);
        int var4 = this.field_70170_p.func_72957_l(var2, var1, var3 = MathHelper.func_76128_c((double)this.field_70161_v));
        return var4 > this.field_70146_Z.nextInt(var5 = 7) ? false : super.func_70601_bi();
    }

    protected Item func_146068_u() {
        if (!this.getIsSummoned()) {
            return Items.field_151016_H;
        }
        return Item.func_150899_d((int)0);
    }

    protected boolean func_70814_o() {
        return true;
    }
}

