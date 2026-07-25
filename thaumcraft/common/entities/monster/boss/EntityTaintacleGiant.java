/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityTaintacleGiant
extends EntityTaintacle
implements ITaintedMob,
IBossDisplayData {
    public EntityTaintacleGiant(World par1World) {
        super(par1World);
        this.func_70105_a(1.1f, 6.0f);
        this.field_70728_aV = 20;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(125.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9.0);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData data) {
        EntityUtils.makeChampion(this, true);
        return data;
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.getAnger() > 0) {
            this.setAnger(this.getAnger() - 1);
        }
        if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && this.getAnger() > 0) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N) - (double)this.field_70130_N / 2.0, this.field_70121_D.field_72338_b + (double)this.field_70131_O + (double)this.field_70146_Z.nextFloat() * 0.5, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N) - (double)this.field_70130_N / 2.0, d0, d1, d2);
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 30 == 0) {
            this.func_70691_i(1.0f);
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(14, (Object)new Short(0));
    }

    public int getAnger() {
        return this.field_70180_af.func_75693_b(14);
    }

    public void setAnger(int par1) {
        this.field_70180_af.func_75692_b(14, (Object)((short)par1));
    }

    @Override
    public boolean func_70601_bi() {
        return false;
    }

    @Override
    protected void func_70628_a(boolean flag, int i) {
        ArrayList<Entity> ents = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (Entity)this, EntityTaintacleGiant.class, 48.0);
        if (ents == null || ents.size() <= 0) {
            EntityUtils.entityDropSpecialItem((Entity)this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0f);
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70648_aU() {
        return true;
    }

    protected int func_70682_h(int air) {
        return air;
    }

    @Override
    public boolean func_70097_a(DamageSource source, float damage) {
        if (!this.field_70170_p.field_72995_K && damage > 35.0f) {
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
        return super.func_70097_a(source, damage);
    }
}

