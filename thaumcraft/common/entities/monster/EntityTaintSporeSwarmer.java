/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.entities.monster.EntityTaintSwarm;

public class EntityTaintSporeSwarmer
extends EntityTaintSpore {
    int spawnCounter = 500;

    public EntityTaintSporeSwarmer(World par1World) {
        super(par1World);
        this.setSporeSize(10);
    }

    @Override
    public void setSporeSize(int par1) {
        this.field_70180_af.func_75692_b(16, (Object)new Byte((byte)par1));
        this.func_70105_a(1.0f, 1.0f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70728_aV = par1;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0);
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.field_70170_p.field_72995_K) {
            this.sploosh(10);
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    @Override
    protected void sporeOnUpdate() {
        this.func_145771_j(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (this.spawnCounter > 0) {
            --this.spawnCounter;
        }
        if (this.spawnCounter <= 0 && this.field_70170_p.func_72856_b((Entity)this, 16.0) != null) {
            this.spawnCounter = 500;
            this.swarmBurst(1);
        }
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < this.swarm.size(); ++a) {
                if (this.swarm.get(a) != null && !((Entity)this.swarm.get((int)a)).field_70128_L) continue;
                this.swarm.remove(a);
                break;
            }
            if (this.swarm.size() < (500 - this.spawnCounter) / 25) {
                this.swarm.add(Thaumcraft.proxy.swarmParticleFX(this.field_70170_p, (Entity)this, 0.1f, 10.0f, 0.0f));
            }
        }
        if (this.field_70725_aQ == 1) {
            this.swarmBurst(1);
        }
    }

    @Override
    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
    }

    protected void swarmBurst(int amt) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:gore", 1.0f, 0.9f + this.field_70170_p.field_73012_v.nextFloat() * 0.1f);
            for (int a = 0; a < amt; ++a) {
                EntityTaintSwarm swarm = new EntityTaintSwarm(this.field_70170_p);
                swarm.func_70012_b(this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, this.field_70170_p.field_73012_v.nextFloat() * 360.0f, 0.0f);
                this.field_70170_p.func_72838_d((Entity)swarm);
            }
            this.field_70170_p.func_72960_a((Entity)this, (byte)6);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 6) {
            this.spawnCounter = 500;
            this.sploosh(25);
            for (int a = 0; a < this.swarm.size(); ++a) {
                ((Entity)this.swarm.get(a)).func_70106_y();
            }
            this.swarm.clear();
        } else {
            super.func_70103_a(par1);
        }
    }

    @Override
    public int func_70627_aG() {
        return 200;
    }

    @Override
    protected String func_70639_aQ() {
        return "thaumcraft:roots";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72899_e(i, 0, j = MathHelper.func_76128_c((double)this.field_70161_v))) {
            double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66;
            int k = MathHelper.func_76128_c((double)(this.field_70163_u - (double)this.field_70129_M + d0));
            return this.field_70170_p.func_72802_i(i, k, j, 0);
        }
        return 0;
    }

    @Override
    public float func_70013_c(float par1) {
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72899_e(i, 0, j = MathHelper.func_76128_c((double)this.field_70161_v))) {
            double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66;
            int k = MathHelper.func_76128_c((double)(this.field_70163_u - (double)this.field_70129_M + d0));
            return this.field_70170_p.func_72801_o(i, k, j);
        }
        return 0.0f;
    }

    @Override
    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    @Override
    protected void func_70628_a(boolean flag, int i) {
        for (int a = 0; a <= 1; ++a) {
            if (this.field_70170_p.field_73012_v.nextBoolean()) {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
                continue;
            }
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0f);
        }
    }
}

