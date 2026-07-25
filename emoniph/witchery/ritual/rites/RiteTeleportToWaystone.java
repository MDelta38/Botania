/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.rites.RiteTeleportation;
import com.emoniph.witchery.util.Coord;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteTeleportToWaystone
extends RiteTeleportation {
    public RiteTeleportToWaystone(int radius) {
        super(radius);
    }

    @Override
    protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
        if (!world.field_72995_K) {
            ItemStack waystoneStack = null;
            for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                if (!Witchery.Items.GENERIC.itemWaystoneBound.isMatch(item.itemstack) && !Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(item.itemstack)) continue;
                waystoneStack = item.itemstack;
                break;
            }
            if (waystoneStack != null) {
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - this.radius), (double)(posY - this.radius), (double)(posZ - this.radius), (double)(posX + this.radius), (double)(posY + this.radius), (double)(posZ + this.radius));
                List list = world.func_72872_a(Entity.class, bounds);
                boolean sent = false;
                for (Entity entity : list) {
                    if (!(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < (double)this.radius) || PotionEnderInhibition.isActive(entity, 1) || !Witchery.Items.GENERIC.teleportToLocation(world, waystoneStack, entity, this.radius, true)) continue;
                    sent = true;
                }
                return sent;
            }
        }
        return false;
    }
}

