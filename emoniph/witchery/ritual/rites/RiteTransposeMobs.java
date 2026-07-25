/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteTransposeMobs
extends Rite {
    protected final int radius;
    protected final int pulses;
    protected final int minDistance;

    public RiteTransposeMobs(int radius, int minDistance, int pulses) {
        this.radius = radius;
        this.pulses = pulses;
        this.minDistance = minDistance;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int initialStep) {
        steps.add(new StepTeleportation(this, initialStep));
    }

    private static class StepTeleportation
    extends RitualStep {
        private final RiteTransposeMobs rite;
        private int step;

        public StepTeleportation(RiteTransposeMobs rite, int initialStep) {
            super(false);
            this.rite = rite;
            this.step = initialStep;
        }

        @Override
        public int getCurrentStage() {
            return this.step;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            ++this.step;
            int r = this.rite.radius;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - r), (double)1.0, (double)(posZ - r), (double)(posX + r), (double)(posY - 1), (double)(posZ + r));
            for (Object obj : world.func_72872_a(EntityMob.class, bounds)) {
                EntityMob entity = (EntityMob)obj;
                world.func_72900_e((Entity)entity);
                entity.field_70128_L = false;
                int activeRadius = this.rite.radius;
                int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                if (ax > activeRadius) {
                    ax += this.rite.minDistance * 2;
                }
                int x = posX - this.rite.radius - this.rite.minDistance + ax;
                int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                if (az > activeRadius) {
                    az += this.rite.minDistance * 2;
                }
                int z = posZ - this.rite.radius - this.rite.minDistance + az;
                entity.func_70012_b((double)x, (double)posY, (double)z, 0.0f, 0.0f);
                world.func_72838_d((Entity)entity);
                ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, (Entity)entity, 0.5, 2.0, 16);
            }
            return this.step >= this.rite.pulses ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
        }
    }
}

