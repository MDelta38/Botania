/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteCallFamiliar
extends Rite {
    private final int radius;

    public RiteCallFamiliar(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepCallFamiliar(this));
    }

    private static class StepCallFamiliar
    extends RitualStep {
        private final RiteCallFamiliar rite;

        public StepCallFamiliar(RiteCallFamiliar rite) {
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
                ArrayList boundPlayers = new ArrayList();
                for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
                    EntityPlayer player = (EntityPlayer)obj;
                    EntityTameable entity = Familiar.getFamiliarEntity(player);
                    if (entity != null) {
                        ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                        ItemGeneral.teleportToLocation(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_71093_bK, (Entity)entity, false);
                        bound = true;
                        continue;
                    }
                    EntityTameable familiar = Familiar.summonFamiliar(player, 0.5 + (double)posX, 0.001 + (double)posY, 0.5 + (double)posZ);
                    if (familiar == null) continue;
                    bound = true;
                }
                if (bound) {
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0, 2.0, 16);
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

