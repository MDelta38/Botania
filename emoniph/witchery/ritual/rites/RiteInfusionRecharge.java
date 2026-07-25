/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockAltar;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteInfusionRecharge
extends Rite {
    private final float upkeepPowerCost;
    private final int charges;
    private final int radius;
    private final int ticksToLive;

    public RiteInfusionRecharge(int charges, int radius, float upkeepPowerCost, int ticksToLive) {
        this.charges = charges;
        this.radius = radius;
        this.upkeepPowerCost = upkeepPowerCost;
        this.ticksToLive = ticksToLive;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepInfusePlayers(this, intialStage));
    }

    private static class StepInfusePlayers
    extends RitualStep {
        private final RiteInfusionRecharge rite;
        private boolean activated = false;
        protected int ticksSoFar;
        Coord powerSourceCoord;
        static final int POWER_SOURCE_RADIUS = 16;

        public StepInfusePlayers(RiteInfusionRecharge rite, int ticksSoFar) {
            super(false);
            this.rite = rite;
            this.ticksSoFar = ticksSoFar;
        }

        @Override
        public int getCurrentStage() {
            return this.ticksSoFar;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                if (this.rite.upkeepPowerCost > 0.0f) {
                    IPowerSource powerSource = this.getPowerSource(world, posX, posY, posZ);
                    if (powerSource == null) {
                        return RitualStep.Result.ABORTED;
                    }
                    this.powerSourceCoord = powerSource.getLocation();
                    if (!powerSource.consumePower(this.rite.upkeepPowerCost)) {
                        return RitualStep.Result.ABORTED;
                    }
                }
                if (this.rite.ticksToLive > 0 && ticks % 20L == 0L && ++this.ticksSoFar >= this.rite.ticksToLive) {
                    return RitualStep.Result.COMPLETED;
                }
                int r = this.rite.radius;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - r), (double)posY, (double)(posZ - r), (double)(posX + r), (double)(posY + 1), (double)(posZ + r));
                for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
                    int maxEnergy;
                    int currentEnergy;
                    EntityPlayer player = (EntityPlayer)obj;
                    if (!(Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= (double)r) || (currentEnergy = Infusion.getCurrentEnergy(player)) >= (maxEnergy = Infusion.getMaxEnergy(player))) continue;
                    Infusion.setCurrentEnergy(player, Math.min(currentEnergy + this.rite.charges, maxEnergy));
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, (Entity)player, 1.0, 2.0, 8);
                }
            }
            return RitualStep.Result.UPKEEP;
        }

        IPowerSource getPowerSource(World world, int posX, int posY, int posZ) {
            if (this.powerSourceCoord == null || world.field_73012_v.nextInt(5) == 0) {
                return this.findNewPowerSource(world, posX, posY, posZ);
            }
            TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(world);
            if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
                return this.findNewPowerSource(world, posX, posY, posZ);
            }
            BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
            if (!altarTileEntity.isValid()) {
                return this.findNewPowerSource(world, posX, posY, posZ);
            }
            return altarTileEntity;
        }

        private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ) {
            ArrayList<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
            return sources != null && sources.size() > 0 ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
        }
    }
}

