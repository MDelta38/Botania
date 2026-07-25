/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.Dispersal;
import com.emoniph.witchery.brewing.EntityDroplet;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.TileEntityBrewFluid;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DispersalLiquid
extends Dispersal {
    @Override
    public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers) {
        Coord coord = new Coord(mop, modifiers.impactPosition, true);
        boolean replaceable = BlockUtil.isReplaceableBlock(world, coord.x, coord.y, coord.z, (EntityLivingBase)modifiers.thrower);
        if (replaceable) {
            coord.setBlock(world, Witchery.Blocks.BREW_LIQUID);
            TileEntityBrewFluid gas = coord.getTileEntity((IBlockAccess)world, TileEntityBrewFluid.class);
            if (gas != null) {
                gas.initalise(modifiers, nbtBrew);
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "witchery:brew.dispersal.liquid";
    }

    @Override
    public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers) {
        int radius;
        BlockPosition target = modifiers.getTarget();
        World targetWorld = target.getWorld(MinecraftServer.func_71276_C());
        int maxQuantity = radius = 16 + 8 * impactModifiers.extent;
        int halfQuantity = maxQuantity / 4;
        int height = 100;
        double RSQ = radius * radius;
        int quantity = halfQuantity + world.field_73012_v.nextInt(halfQuantity);
        for (int i = 0; i < quantity; ++i) {
            int nz;
            int ny = 100 + world.field_73012_v.nextInt(20);
            int nx = x - radius + world.field_73012_v.nextInt(2 * radius);
            if (!(Coord.distanceSq(x, y, z, nx, y, nz = z - radius + world.field_73012_v.nextInt(2 * radius)) <= RSQ)) continue;
            EntityUtil.spawnEntityInWorld(targetWorld, new EntityDroplet(targetWorld, nx, ny, nz, nbtBrew));
        }
        return modifiers.pulses < 10 + impactModifiers.lifetime * 5 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
    }
}

