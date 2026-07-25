/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.config.ConfigItems;

public class EntityTaintSpider
extends EntitySpider
implements ITaintedMob {
    public EntityTaintSpider(World par1World) {
        super(par1World);
        this.func_70105_a(0.4f, 0.3f);
        this.field_70728_aV = 2;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0);
    }

    protected float func_70647_i() {
        return 0.7f;
    }

    protected Entity func_70782_k() {
        double d0 = 12.0;
        return this.field_70170_p.func_72856_b((Entity)this, d0);
    }

    @SideOnly(value=Side.CLIENT)
    public float spiderScaleAmount() {
        return 0.4f;
    }

    public float func_70053_R() {
        return 0.1f;
    }

    protected Item func_146068_u() {
        return ConfigItems.itemResource;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70170_p.field_73012_v.nextInt(6) == 0) {
            if (this.field_70170_p.field_73012_v.nextBoolean()) {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 11), this.field_70131_O / 2.0f);
            } else {
                this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 12), this.field_70131_O / 2.0f);
            }
        }
    }
}

