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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
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
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityTaintSwarm
extends EntityMob
implements ITaintedMob {
    private ChunkCoordinates currentFlightTarget;
    public int damBonus = 0;
    public ArrayList swarm = new ArrayList();

    public EntityTaintSwarm(World par1World) {
        super(par1World);
        this.func_70105_a(2.0f, 2.0f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        return 0xF000F0;
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    protected float func_70599_aP() {
        return 0.1f;
    }

    protected String func_70639_aQ() {
        return "";
    }

    protected String func_70621_aR() {
        return "thaumcraft:swarmattack";
    }

    protected String func_70673_aS() {
        return "thaumcraft:swarmattack";
    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((double)(2 + this.damBonus));
    }

    public boolean getIsSummoned() {
        return (this.field_70180_af.func_75683_a(16) & 2) != 0;
    }

    public void setIsSummoned(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 | 2)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 & 0xFFFFFFFC)));
        }
    }

    protected boolean func_70650_aV() {
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70181_x *= (double)0.6f;
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < this.swarm.size(); ++a) {
                if (this.swarm.get(a) != null && !((Entity)this.swarm.get((int)a)).field_70128_L) continue;
                this.swarm.remove(a);
                break;
            }
            if (this.swarm.size() < Math.max(Thaumcraft.proxy.particleCount(25), 10)) {
                this.swarm.add(Thaumcraft.proxy.swarmParticleFX(this.field_70170_p, (Entity)this, 0.22f, 15.0f, 0.08f));
            }
        }
    }

    protected void func_70626_be() {
        super.func_70626_be();
        if (this.field_70789_a == null) {
            if (this.getIsSummoned()) {
                this.func_70097_a(DamageSource.field_76377_j, 5.0f);
            }
            if (!(this.currentFlightTarget == null || this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c) && this.currentFlightTarget.field_71572_b >= 1 && this.currentFlightTarget.field_71572_b <= this.field_70170_p.func_72976_f(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71573_c) + 8 && this.field_70170_p.func_72807_a((int)this.currentFlightTarget.field_71574_a, (int)this.currentFlightTarget.field_71573_c).field_76756_M == Config.biomeTaintID)) {
                this.currentFlightTarget = null;
            }
            if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || this.currentFlightTarget.func_71569_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) < 4.0f) {
                this.currentFlightTarget = new ChunkCoordinates((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
            }
            double var1 = (double)this.currentFlightTarget.field_71574_a + 0.5 - this.field_70165_t;
            double var3 = (double)this.currentFlightTarget.field_71572_b + 0.1 - this.field_70163_u;
            double var5 = (double)this.currentFlightTarget.field_71573_c + 0.5 - this.field_70161_v;
            this.field_70159_w += (Math.signum(var1) * 0.5 - this.field_70159_w) * 0.015000000014901161;
            this.field_70181_x += (Math.signum(var3) * (double)0.7f - this.field_70181_x) * (double)0.1f;
            this.field_70179_y += (Math.signum(var5) * 0.5 - this.field_70179_y) * 0.015000000014901161;
            float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0 / Math.PI) - 90.0f;
            float var8 = MathHelper.func_76142_g((float)(var7 - this.field_70177_z));
            this.field_70701_bs = 0.1f;
            this.field_70177_z += var8;
        } else if (this.field_70789_a != null) {
            double var1 = this.field_70789_a.field_70165_t - this.field_70165_t;
            double var3 = this.field_70789_a.field_70163_u + (double)this.field_70789_a.func_70047_e() - this.field_70163_u;
            double var5 = this.field_70789_a.field_70161_v - this.field_70161_v;
            this.field_70159_w += (Math.signum(var1) * 0.5 - this.field_70159_w) * 0.025000000149011613;
            this.field_70181_x += (Math.signum(var3) * (double)0.7f - this.field_70181_x) * (double)0.1f;
            this.field_70179_y += (Math.signum(var5) * 0.5 - this.field_70179_y) * 0.02500000001490116;
            float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0 / Math.PI) - 90.0f;
            float var8 = MathHelper.func_76142_g((float)(var7 - this.field_70177_z));
            this.field_70701_bs = 0.1f;
            this.field_70177_z += var8;
        }
        if (this.field_70789_a instanceof EntityPlayer && ((EntityPlayer)this.field_70789_a).field_71075_bZ.field_75102_a) {
            this.field_70789_a = null;
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
        if (this.func_85032_ar()) {
            return false;
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    protected void func_70785_a(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < 3.0f && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            if (this.getIsSummoned()) {
                EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
            }
            this.field_70724_aR = 10 + this.field_70146_Z.nextInt(5);
            double mx = par1Entity.field_70159_w;
            double my = par1Entity.field_70181_x;
            double mz = par1Entity.field_70179_y;
            if (this.func_70652_k(par1Entity) && !this.field_70170_p.field_72995_K && par1Entity instanceof EntityLivingBase) {
                ((EntityLivingBase)par1Entity).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 0));
            }
            par1Entity.field_70160_al = false;
            par1Entity.field_70159_w = mx;
            par1Entity.field_70181_x = my;
            par1Entity.field_70179_y = mz;
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:swarmattack", 0.3f, 0.9f + this.field_70170_p.field_73012_v.nextFloat() * 0.2f);
        }
    }

    protected Entity func_70782_k() {
        double var1 = 12.0;
        return this.getIsSummoned() ? null : this.field_70170_p.func_72856_b((Entity)this, var1);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70180_af.func_75692_b(16, (Object)par1NBTTagCompound.func_74771_c("Flags"));
        this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("Flags", this.field_70180_af.func_75683_a(16));
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

    protected boolean func_70814_o() {
        return true;
    }

    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70170_p.field_73012_v.nextBoolean()) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
        }
    }
}

