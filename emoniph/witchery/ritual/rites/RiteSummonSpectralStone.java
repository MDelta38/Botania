/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.item.ItemSpectralStone;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSummonSpectralStone
extends Rite {
    private final int radius;

    public RiteSummonSpectralStone(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepSummonItem(this));
    }

    private static class StepSummonItem
    extends RitualStep {
        private final RiteSummonSpectralStone rite;

        public StepSummonItem(RiteSummonSpectralStone rite) {
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
                int r2 = r * r;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(posX - r), (double)(posY - r), (double)(posZ - r), (double)(posX + r), (double)(posY + r), (double)(posZ + r));
                List entities = world.func_72872_a(EntitySummonedUndead.class, bb);
                Class<?> entityType = null;
                int count = 0;
                for (Object obj : entities) {
                    EntitySummonedUndead entity = (EntitySummonedUndead)((Object)obj);
                    if (!(entity.func_70092_e(0.5 + (double)posX, posY, 0.5 + (double)posZ) <= (double)r2)) continue;
                    Class<?> foundType = ((Object)((Object)entity)).getClass();
                    if (entityType == null) {
                        entityType = foundType;
                    }
                    if (entityType != foundType) continue;
                    ++count;
                    if (!world.field_72995_K) {
                        entity.func_70106_y();
                        ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, (Entity)entity, 1.0, 2.0, 16);
                    }
                    if (count < 3) continue;
                    break;
                }
                if (count <= 0) {
                    RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), world);
                    return RitualStep.Result.ABORTED_REFUND;
                }
                ItemStack stack = new ItemStack(Witchery.Items.SPECTRAL_STONE, 1, ItemSpectralStone.metaFromCreature(entityType, count));
                EntityItem entity = new EntityItem(world, 0.5 + (double)posX, (double)posY + 1.5, 0.5 + (double)posZ, stack);
                entity.field_70159_w = 0.0;
                entity.field_70181_x = 0.3;
                entity.field_70179_y = 0.0;
                world.func_72838_d((Entity)entity);
                ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)entity, 0.5, 0.5, 16);
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

