/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteBindFamiliar
extends Rite {
    private final int radius;

    public RiteBindFamiliar(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepBindFamiliar(this));
    }

    private static class StepBindFamiliar
    extends RitualStep {
        private final RiteBindFamiliar rite;

        public StepBindFamiliar(RiteBindFamiliar rite) {
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
                boolean bound = false;
                ArrayList<EntityPlayer> boundPlayers = new ArrayList<EntityPlayer>();
                for (Object obj : world.func_72872_a(EntityTameable.class, bounds)) {
                    EntityLivingBase player;
                    EntityTameable tameable = (EntityTameable)obj;
                    if (!tameable.func_70909_n() || !Familiar.canBecomeFamiliar(tameable) || !(Coord.distance(tameable.field_70165_t, tameable.field_70163_u, tameable.field_70161_v, posX, posY, posZ) <= (double)r) || (player = tameable.func_70902_q()) == null || !(player instanceof EntityPlayer) || !(Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= (double)r) || boundPlayers.contains(player)) continue;
                    Familiar.bindToPlayer((EntityPlayer)player, tameable);
                    boundPlayers.add((EntityPlayer)player);
                    bound = true;
                }
                if (bound) {
                    ParticleEffect.HEART.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0, 3.0, 16);
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

