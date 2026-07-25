/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.entities.monster.EntityTaintacle;

public class EntityTaintacleSmall
extends EntityTaintacle
implements ITaintedMob {
    int lifetime = 200;

    public EntityTaintacleSmall(World par1World) {
        super(par1World);
        this.func_70105_a(0.22f, 1.0f);
        this.field_70728_aV = 0;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.lifetime-- <= 0) {
            this.func_70665_d(DamageSource.field_76376_m, 10.0f);
        }
    }

    @Override
    public boolean func_70601_bi() {
        return false;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    @Override
    protected void func_70628_a(boolean flag, int i) {
    }
}

