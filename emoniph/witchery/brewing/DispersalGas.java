/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.Dispersal;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.TileEntityBrewFluid;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DispersalGas
extends Dispersal {
    @Override
    public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers) {
        Coord coord = new Coord(mop, modifiers.impactPosition, true);
        boolean replaceable = BlockUtil.isReplaceableBlock(world, coord.x, coord.y, coord.z, (EntityLivingBase)modifiers.thrower);
        if (replaceable) {
            coord.setBlock(world, Witchery.Blocks.BREW_GAS);
            TileEntityBrewFluid gas = coord.getTileEntity((IBlockAccess)world, TileEntityBrewFluid.class);
            if (gas != null) {
                gas.initalise(modifiers, nbtBrew);
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "witchery:brew.dispersal.gas";
    }

    @Override
    public RitualStatus onUpdateRitual(World world, int x, int y, int z, final NBTTagCompound nbtBrew, final ModifiersRitual modifiers, ModifiersImpact impactModifiers) {
        BlockPosition target = modifiers.getTarget();
        World targetWorld = target.getWorld(MinecraftServer.func_71276_C());
        int height = 8;
        boolean blackMagic = false;
        new BlockActionCircle(){

            @Override
            public void onBlock(World world, int x, int y, int z) {
                BlockPosition coords = null;
                for (int i = 0; i < 8; ++i) {
                    if (world.func_147439_a(x, y + i, z).func_149688_o() != Material.field_151579_a && world.func_147437_c(x, y + i + 1, z)) {
                        coords = new BlockPosition(world, x, y, z);
                        break;
                    }
                    if (i <= 0 || world.func_147439_a(x, y - i, z).func_149688_o() == Material.field_151579_a || !world.func_147437_c(x, y - i + 1, z)) continue;
                    coords = new BlockPosition(world, x, y, z);
                    break;
                }
                if (coords != null) {
                    DispersalGas.showSpellParticles(world, coords.x, coords.y, coords.z, false);
                    EntityPlayer player = EntityUtil.playerOrFake(world, (EntityLivingBase)((EntityPlayer)null));
                    ModifiersEffect effectModifiers = new ModifiersEffect(0.0, 1.0, false, new EntityPosition(coords), true, modifiers.covenSize, player);
                    if (world.field_73012_v.nextDouble() < 0.01) {
                        WitcheryBrewRegistry.INSTANCE.applyToBlock(world, coords.x, coords.y, coords.z, ForgeDirection.UP, 1, nbtBrew, effectModifiers);
                    }
                    List<EntityLivingBase> entities = EntityUtil.getEntitiesInRadius(EntityLivingBase.class, world, coords.x, coords.y, coords.z, 1.5);
                    for (EntityLivingBase entity : entities) {
                        effectModifiers = new ModifiersEffect(1.0, 1.0, false, new EntityPosition(coords), true, modifiers.covenSize, player);
                        WitcheryBrewRegistry.INSTANCE.applyToEntity(world, entity, nbtBrew, effectModifiers);
                    }
                }
            }
        }.processHollowCircle(targetWorld, target.x, target.y, target.z, modifiers.pulses);
        return modifiers.pulses < 8 + impactModifiers.extent * 8 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
    }

    private static void showSpellParticles(World world, int x, int y, int z, boolean blackMagic) {
        if (blackMagic) {
            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, y + 1, 0.5 + (double)z, 1.0, 1.0, 16);
        } else {
            ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, y + 1, 0.5 + (double)z, 1.0, 1.0, 16);
        }
    }
}

