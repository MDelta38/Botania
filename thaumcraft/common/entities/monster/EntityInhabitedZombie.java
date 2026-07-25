/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityEldritchCrab;

public class EntityInhabitedZombie
extends EntityZombie {
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0);
        this.func_110148_a(field_110186_bp).func_111128_a(0.0);
    }

    public EntityInhabitedZombie(World world) {
        super(world);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, 0, true));
    }

    public void func_70074_a(EntityLivingBase par1EntityLivingBase) {
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        float diff = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.9f : 0.6f;
        this.func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistPlate));
        if (this.field_70146_Z.nextFloat() <= diff) {
            this.func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistPlate));
        }
        if (this.field_70146_Z.nextFloat() <= diff) {
            this.func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistPlate));
        }
        return p_110161_1_;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    }

    protected void func_70609_aI() {
        if (!this.field_70170_p.field_72995_K) {
            EntityEldritchCrab crab = new EntityEldritchCrab(this.field_70170_p);
            crab.func_70080_a(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v, this.field_70177_z, this.field_70125_A);
            crab.setHelm(true);
            this.field_70170_p.func_72838_d((Entity)crab);
            if ((this.field_70718_bc > 0 || this.func_70684_aJ()) && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
                int j;
                for (int i = this.func_70693_a(this.field_70717_bb); i > 0; i -= j) {
                    j = EntityXPOrb.func_70527_a((int)i);
                    this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                }
            }
        }
        for (int i = 0; i < 20; ++i) {
            double d2 = this.field_70146_Z.nextGaussian() * 0.02;
            double d0 = this.field_70146_Z.nextGaussian() * 0.02;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a("explode", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d2, d0, d1);
        }
        this.func_70106_y();
    }

    public void func_70645_a(DamageSource p_70645_1_) {
    }

    protected String func_70639_aQ() {
        return "thaumcraft:crabtalk";
    }

    protected String func_70621_aR() {
        return "game.hostile.hurt";
    }

    public boolean func_70601_bi() {
        List ents = this.field_70170_p.func_72872_a(EntityInhabitedZombie.class, AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)(this.field_70165_t + 1.0), (double)(this.field_70163_u + 1.0), (double)(this.field_70161_v + 1.0)).func_72314_b(32.0, 16.0, 32.0));
        return ents.size() > 0 ? false : super.func_70601_bi();
    }
}

