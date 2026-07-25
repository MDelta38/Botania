/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class Waypoint {
    public final boolean valid;
    public final double X;
    public final double Y;
    public final double Z;
    public final double D;

    public Waypoint(World world, double homeX, double homeY, double homeZ) {
        this.X = homeX;
        this.Y = homeY;
        this.Z = homeZ;
        this.D = world.field_73011_w.field_76574_g;
        this.valid = true;
    }

    public Waypoint(World world, ChunkPosition pos) {
        this.X = pos.field_151329_a;
        this.Y = pos.field_151327_b;
        this.Z = pos.field_151328_c;
        this.D = world.field_73011_w.field_76574_g;
        this.valid = true;
    }

    public Waypoint(World world, ItemStack stack, double homeX, double homeY, double homeZ) {
        if (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(stack)) {
            NBTTagCompound nbtWaystone = stack.func_77978_p();
            int x = nbtWaystone.func_74762_e("PosX");
            int z = nbtWaystone.func_74762_e("PosZ");
            if (world.func_72938_d((int)x, (int)z).field_76636_d) {
                this.X = (double)x + 0.5;
                this.Y = (double)nbtWaystone.func_74762_e("PosY") + 1.5;
                this.Z = (double)z + 0.5;
                this.D = nbtWaystone.func_74762_e("PosD");
                this.valid = true;
            } else {
                this.X = homeX;
                this.Y = homeY;
                this.Z = homeZ;
                this.D = world.field_73011_w.field_76574_g;
                this.valid = false;
            }
        } else if (Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(stack)) {
            EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, stack, 1);
            if (entity != null) {
                this.X = entity.field_70165_t;
                this.Y = entity.field_70163_u + 1.0;
                this.Z = entity.field_70161_v;
                this.D = entity.field_71093_bK;
                this.valid = true;
            } else {
                this.X = homeX;
                this.Y = homeY;
                this.Z = homeZ;
                this.D = world.field_73011_w.field_76574_g;
                this.valid = false;
            }
        } else if (stack != null && stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
            EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, stack, 1);
            if (entity != null) {
                this.X = entity.field_70165_t;
                this.Y = entity.field_70163_u + 1.0;
                this.Z = entity.field_70161_v;
                this.D = entity.field_71093_bK;
                this.valid = true;
            } else {
                this.X = homeX;
                this.Y = homeY;
                this.Z = homeZ;
                this.D = world.field_73011_w.field_76574_g;
                this.valid = false;
            }
        } else {
            this.X = homeX;
            this.Y = homeY;
            this.Z = homeZ;
            this.D = world.field_73011_w.field_76574_g;
            this.valid = false;
        }
    }
}

