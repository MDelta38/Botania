/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import java.util.ArrayList;
import net.minecraft.world.World;

public abstract class RiteTeleportation
extends Rite {
    protected final int radius;

    public RiteTeleportation(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepTeleportation(this));
    }

    protected abstract boolean teleport(World var1, int var2, int var3, int var4, BlockCircle.TileEntityCircle.ActivatedRitual var5);

    private static class StepTeleportation
    extends RitualStep {
        private final RiteTeleportation rite;

        public StepTeleportation(RiteTeleportation rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (this.rite.teleport(world, posX, posY, posZ, ritual)) {
                return RitualStep.Result.COMPLETED;
            }
            return RitualStep.Result.ABORTED_REFUND;
        }
    }
}

