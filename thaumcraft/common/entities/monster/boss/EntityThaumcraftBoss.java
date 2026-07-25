/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster.boss;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityThaumcraftBoss
extends EntityMob
implements IBossDisplayData {
    HashMap<Integer, Integer> aggro = new HashMap();
    int spawnTimer = 0;

    public EntityThaumcraftBoss(World world) {
        super(world);
        this.field_70728_aV = 50;
    }

    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        if (nbt.func_74764_b("HomeD")) {
            this.func_110171_b(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ"), nbt.func_74762_e("HomeD"));
        }
    }

    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        if (this.func_110172_bL() != null && this.func_110174_bM() > 0.0f) {
            nbt.func_74768_a("HomeD", (int)this.func_110174_bM());
            nbt.func_74768_a("HomeX", this.func_110172_bL().field_71574_a);
            nbt.func_74768_a("HomeY", this.func_110172_bL().field_71572_b);
            nbt.func_74768_a("HomeZ", this.func_110172_bL().field_71573_c);
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.95);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(14, (Object)new Short(0));
    }

    protected void func_70619_bc() {
        if (this.getSpawnTimer() == 0) {
            super.func_70619_bc();
        }
        if (this.func_70638_az() != null && this.func_70638_az().field_70128_L) {
            this.func_70624_b(null);
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData data) {
        this.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 24);
        return data;
    }

    public int getAnger() {
        return this.field_70180_af.func_75693_b(14);
    }

    public void setAnger(int par1) {
        this.field_70180_af.func_75692_b(14, (Object)((short)par1));
    }

    public int getSpawnTimer() {
        return this.spawnTimer;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.getSpawnTimer() > 0) {
            --this.spawnTimer;
        }
        if (this.getAnger() > 0) {
            this.setAnger(this.getAnger() - 1);
        }
        if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && this.getAnger() > 0) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N) - (double)this.field_70130_N / 2.0, this.field_70121_D.field_72338_b + (double)this.field_70131_O + (double)this.field_70146_Z.nextFloat() * 0.5, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N) - (double)this.field_70130_N / 2.0, d0, d1, d2);
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 30 == 0) {
                this.func_70691_i(1.0f);
            }
            if (this.func_70638_az() != null && this.field_70173_aa % 20 == 0) {
                int a;
                int ad;
                ArrayList<Integer> dl = new ArrayList<Integer>();
                int players = 0;
                int hei = this.func_70638_az().func_145782_y();
                int ld = ad = this.aggro.containsKey(hei) ? this.aggro.get(hei) : 0;
                Entity newTarget = null;
                for (Integer ei : this.aggro.keySet()) {
                    int ca = this.aggro.get(ei);
                    if (ca <= ad + 25 || !((double)ca > (double)ad * 1.1) || ca <= ld) continue;
                    newTarget = this.field_70170_p.func_73045_a(hei);
                    if (newTarget == null || newTarget.field_70128_L || this.func_70068_e(newTarget) > 16384.0) {
                        dl.add(ei);
                        continue;
                    }
                    hei = ei;
                    ld = ei;
                    if (!(newTarget instanceof EntityPlayer)) continue;
                    ++players;
                }
                for (Integer ei : dl) {
                    this.aggro.remove(ei);
                }
                if (newTarget != null && hei != this.func_70638_az().func_145782_y()) {
                    this.func_70624_b((EntityLivingBase)newTarget);
                }
                float om = this.func_110138_aP();
                IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111267_a);
                IAttributeInstance iattributeinstance2 = this.func_110148_a(SharedMonsterAttributes.field_111264_e);
                for (a = 0; a < 5; ++a) {
                    iattributeinstance2.func_111124_b(EntityUtils.DMGBUFF[a]);
                    iattributeinstance.func_111124_b(EntityUtils.HPBUFF[a]);
                }
                for (a = 0; a < Math.min(5, players - 1); ++a) {
                    iattributeinstance.func_111121_a(EntityUtils.HPBUFF[a]);
                    iattributeinstance2.func_111121_a(EntityUtils.DMGBUFF[a]);
                }
                double mm = this.func_110138_aP() / om;
                this.func_70606_j((float)((double)this.func_110143_aJ() * mm));
            }
        }
    }

    public boolean func_85032_ar() {
        return super.func_85032_ar() || this.getSpawnTimer() > 0;
    }

    public boolean func_70648_aU() {
        return true;
    }

    public boolean func_70104_M() {
        return super.func_70104_M() && !this.func_85032_ar();
    }

    protected int func_70682_h(int air) {
        return air;
    }

    public void func_70110_aj() {
    }

    public boolean func_98052_bS() {
        return false;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_82164_bB() {
    }

    protected void func_82162_bC() {
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_142014_c(EntityLivingBase el) {
        return el instanceof IEldritchMob;
    }

    protected void func_70628_a(boolean flag, int fortune) {
        EntityUtils.entityDropSpecialItem((Entity)this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0f);
        this.func_70099_a(new ItemStack(ConfigItems.itemLootbag, 1, 2), 1.5f);
    }

    protected void func_70600_l(int fortune) {
        super.func_70600_l(fortune);
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        if (!this.field_70170_p.field_72995_K) {
            if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityLivingBase) {
                int target = source.func_76346_g().func_145782_y();
                int ad = (int)damage;
                if (this.aggro.containsKey(target)) {
                    ad += this.aggro.get(target).intValue();
                }
                this.aggro.put(target, ad);
            }
            if (damage > 35.0f) {
                if (this.getAnger() == 0) {
                    try {
                        this.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, (int)(damage / 15.0f)));
                        this.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 200, (int)(damage / 40.0f)));
                        this.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 200, (int)(damage / 40.0f)));
                        this.setAnger(200);
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayer) {
                        ((EntityPlayer)source.func_76346_g()).func_145747_a((IChatComponent)new ChatComponentText(this.func_70005_c_() + " " + StatCollector.func_74838_a((String)"tc.boss.enrage")));
                    }
                }
                damage = 35.0f;
            }
        }
        return super.func_70097_a(source, damage);
    }

    public void generateName() {
    }
}

