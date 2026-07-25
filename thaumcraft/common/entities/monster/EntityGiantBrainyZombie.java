/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityBrainyZombie;

public class EntityGiantBrainyZombie
extends EntityBrainyZombie {
    public EntityGiantBrainyZombie(World world) {
        super(world);
        this.field_70728_aV = 15;
        this.field_70129_M *= 1.2f + this.getAnger();
        this.func_70105_a(this.field_70130_N * (1.2f + this.getAnger()), this.field_70131_O * (1.2f + this.getAnger()));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.getAnger() > 1.0f) {
            this.setAnger(this.getAnger() - 0.002f);
            this.func_70105_a(0.6f * (1.2f + this.getAnger()), 1.8f * (1.2f + this.getAnger()));
        }
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((double)(7.0f + (this.getAnger() - 1.0f) * 5.0f));
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(20, (Object)new Float(1.0f));
    }

    public float getAnger() {
        return this.field_70180_af.func_111145_d(20);
    }

    public void setAnger(float par1) {
        this.field_70180_af.func_75692_b(20, (Object)Float.valueOf(par1));
    }

    @Override
    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        this.setAnger(Math.min(2.0f, this.getAnger() + 0.1f));
        return super.func_70097_a(par1DamageSource, par2);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(60.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0);
    }

    @Override
    protected void func_70628_a(boolean flag, int i) {
        int a;
        for (a = 0; a < 6; ++a) {
            if (!this.field_70170_p.field_73012_v.nextBoolean()) continue;
            this.func_145779_a(Items.field_151078_bh, 2);
        }
        for (a = 0; a < 6; ++a) {
            if (!this.field_70170_p.field_73012_v.nextBoolean()) continue;
            this.func_145779_a(Items.field_151078_bh, 2);
        }
        if (this.field_70170_p.field_73012_v.nextInt(10) - i <= 4) {
            this.func_70099_a(new ItemStack(ConfigItems.itemZombieBrain), 2.0f);
        }
    }

    protected void func_70600_l(int par1) {
        switch (this.field_70146_Z.nextInt(4)) {
            case 0: {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 2), 2.0f);
                break;
            }
            case 1: {
                this.func_145779_a(Items.field_151172_bF, 1);
                break;
            }
            case 2: {
                this.func_145779_a(Items.field_151174_bG, 1);
                break;
            }
            case 3: {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 6), 2.0f);
            }
        }
    }
}

