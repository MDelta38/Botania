/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockAltar;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class RiteProtectionCircle
extends Rite {
    private final int radius;
    private final float upkeepPowerCost;
    private final int ticksToLive;

    public RiteProtectionCircle(int radius, float upkeepPowerCost, int ticksToLive) {
        this.radius = radius;
        this.upkeepPowerCost = upkeepPowerCost;
        this.ticksToLive = ticksToLive;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int initialStage) {
        steps.add(new ProtectionCircleStep(this, initialStage));
    }

    protected abstract void update(World var1, int var2, int var3, int var4, int var5, long var6);

    private static class ProtectionCircleStep
    extends RitualStep {
        private final RiteProtectionCircle rite;
        private boolean activated = false;
        protected int ticksSoFar;
        Coord powerSourceCoord;
        static final int POWER_SOURCE_RADIUS = 16;

        public ProtectionCircleStep(RiteProtectionCircle rite, int ticksSoFar) {
            super(true);
            this.rite = rite;
            this.ticksSoFar = ticksSoFar;
        }

        @Override
        public int getCurrentStage() {
            return this.ticksSoFar;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (!this.activated) {
                if (ticks % 20L != 0L) {
                    return RitualStep.Result.STARTING;
                }
                this.activated = true;
                SoundEffect.RANDOM_FIZZ.playAt(world, this.sourceX, this.sourceY, this.sourceZ);
            }
            if (this.rite.upkeepPowerCost > 0.0f) {
                IPowerSource powerSource = this.getPowerSource(world, this.sourceX, this.sourceY, this.sourceZ);
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
            this.rite.update(world, posX, posY, posZ, this.rite.radius, ticks);
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

