/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntitySelfShearingSheep
extends EntitySheep {
    public EntitySelfShearingSheep(World p_i1691_1_) {
        super(p_i1691_1_);
    }

    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && !this.func_70892_o() && this.field_70173_aa % 100 == 0) {
            ArrayList drops = this.onSheared(new ItemStack((Item)Items.field_151097_aZ), (IBlockAccess)this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            Random rand = new Random();
            for (ItemStack stack : drops) {
                EntityItem ent = this.func_70099_a(stack, 1.0f);
                ent.field_70181_x += (double)(rand.nextFloat() * 0.05f);
                ent.field_70159_w += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                ent.field_70179_y += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
            }
        }
        super.func_70636_d();
    }
}

