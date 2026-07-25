/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSetNBT
extends Rite {
    private final int radius;
    private final String nbtKey;
    private final int nbtValue;
    private final int nbtCovenBonus;

    public RiteSetNBT(int radius, String nbtKey, int value, int covenMemberBonus) {
        this.radius = radius;
        this.nbtKey = nbtKey;
        this.nbtValue = value;
        this.nbtCovenBonus = covenMemberBonus;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepSetNBT(this));
    }

    private static class StepSetNBT
    extends RitualStep {
        private final RiteSetNBT rite;

        public StepSetNBT(RiteSetNBT rite) {
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
                    NBTTagCompound nbtPlayer;
                    EntityPlayer player = (EntityPlayer)obj;
                    if (!(Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= (double)r) || (nbtPlayer = Infusion.getNBT((Entity)player)) == null) continue;
                    nbtPlayer.func_74768_a(this.rite.nbtKey, this.rite.nbtValue + ritual.covenSize * this.rite.nbtCovenBonus);
                    bound = true;
                }
                if (bound) {
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0, 3.0, 16);
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

