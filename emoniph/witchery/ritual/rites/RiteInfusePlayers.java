/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class RiteInfusePlayers
extends Rite {
    private final Infusion infusion;
    private final int charges;
    private final int radius;

    public RiteInfusePlayers(Infusion infusion, int charges, int radius) {
        this.infusion = infusion;
        this.charges = charges;
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepInfusePlayers(this));
    }

    private static class StepInfusePlayers
    extends RitualStep {
        private final RiteInfusePlayers rite;

        public StepInfusePlayers(RiteInfusePlayers rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                int r = this.rite.radius;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - r), (double)posY, (double)(posZ - r), (double)(posX + r), (double)(posY + 1), (double)(posZ + r));
                for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
                    EntityPlayer player = (EntityPlayer)obj;
                    if (!(Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= (double)r)) continue;
                    player.func_70097_a(DamageSource.field_76376_m, 100.0f);
                    if (!(player.func_110143_aJ() > 0.1f)) continue;
                    this.rite.infusion.infuse(player, this.rite.charges);
                }
                ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0, 3.0, 16);
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

