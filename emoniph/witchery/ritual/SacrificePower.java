/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.Sacrifice;
import com.emoniph.witchery.util.Coord;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SacrificePower
extends Sacrifice {
    public final float powerRequired;
    public final int powerFrequencyInTicks;

    public SacrificePower(float powerRequired, int powerFrequencyInTicks) {
        this.powerRequired = powerRequired;
        this.powerFrequencyInTicks = powerFrequencyInTicks;
    }

    @Override
    public void addDescription(StringBuffer sb) {
        sb.append(String.format("\n\u00a78%s\u00a70 %s\n", Witchery.resource("witchery.book.altarpower"), MathHelper.func_76141_d((float)this.powerRequired)));
    }

    @Override
    public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks) {
        return true;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {
        steps.add(new SacrificePowerStep(this));
    }

    private static class SacrificePowerStep
    extends RitualStep {
        private final SacrificePower sacrifice;
        private static final int POWER_SOURCE_RADIUS = 16;

        public SacrificePowerStep(SacrificePower sacrifice) {
            super(false);
            this.sacrifice = sacrifice;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % (long)this.sacrifice.powerFrequencyInTicks != 0L) {
                return RitualStep.Result.STARTING;
            }
            IPowerSource powerSource = this.findNewPowerSource(world, posX, posY, posZ);
            if (powerSource == null) {
                RiteRegistry.RiteError("witchery.rite.missingpowersource", ritual.getInitiatingPlayerName(), world);
                return RitualStep.Result.ABORTED_REFUND;
            }
            if (powerSource.consumePower(this.sacrifice.powerRequired)) {
                return RitualStep.Result.COMPLETED;
            }
            RiteRegistry.RiteError("witchery.rite.insufficientpower", ritual.getInitiatingPlayerName(), world);
            return RitualStep.Result.ABORTED_REFUND;
        }

        private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ) {
            ArrayList<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
            return sources != null && sources.size() > 0 ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
        }
    }
}

