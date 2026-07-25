/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityChocolateCow
extends EntityCow {
    public EntityChocolateCow(World p_i1683_1_) {
        super(p_i1683_1_);
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_) {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
        if (itemstack != null && itemstack.func_77973_b() == Items.field_151133_ar) {
            if (itemstack.field_77994_a-- == 1) {
                p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, new ItemStack(ThaumicHorizons.itemBucketChocolate));
            } else if (!p_70085_1_.field_71071_by.func_70441_a(new ItemStack(ThaumicHorizons.itemBucketChocolate))) {
                p_70085_1_.func_71019_a(new ItemStack(ThaumicHorizons.itemBucketChocolate, 1, 0), false);
            }
            return true;
        }
        return super.func_70085_c(p_70085_1_);
    }
}

