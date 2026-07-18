/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFireworkRocket
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.lens.Lens;

public class LensFirework
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!burst.isFake()) {
            ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
            if (!(entity.field_70170_p.field_72995_K || pos.field_72308_g != null || isManaBlock || pos.field_72311_b == coords.field_71574_a && pos.field_72312_c == coords.field_71572_b && pos.field_72309_d == coords.field_71573_c)) {
                ItemStack fireworkStack = this.generateFirework(burst.getColor());
                EntityFireworkRocket rocket = new EntityFireworkRocket(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, fireworkStack);
                entity.field_70170_p.func_72838_d((Entity)rocket);
            }
        } else {
            dead = false;
        }
        return dead;
    }

    public ItemStack generateFirework(int color) {
        ItemStack stack = new ItemStack(Items.field_151152_bP);
        NBTTagCompound explosion = new NBTTagCompound();
        explosion.func_74783_a("Colors", new int[]{color});
        int type = 1;
        double rand = Math.random();
        if (rand > 0.25) {
            type = rand > 0.9 ? 2 : 0;
        }
        explosion.func_74768_a("Type", type);
        if (Math.random() < 0.05) {
            if (Math.random() < 0.5) {
                explosion.func_74757_a("Flicker", true);
            } else {
                explosion.func_74757_a("Trail", true);
            }
        }
        ItemNBTHelper.setCompound(stack, "Explosion", explosion);
        NBTTagCompound fireworks = new NBTTagCompound();
        fireworks.func_74768_a("Flight", (int)Math.random() * 3 + 2);
        NBTTagList explosions = new NBTTagList();
        explosions.func_74742_a((NBTBase)explosion);
        fireworks.func_74782_a("Explosions", (NBTBase)explosions);
        ItemNBTHelper.setCompound(stack, "Fireworks", fireworks);
        return stack;
    }
}

