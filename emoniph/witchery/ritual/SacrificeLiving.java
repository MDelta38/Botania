/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.Sacrifice;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SacrificeLiving
extends Sacrifice {
    final Class<? extends EntityLiving> entityLivingClass;

    public SacrificeLiving(Class<? extends EntityLiving> entityLivingClass) {
        this.entityLivingClass = entityLivingClass;
    }

    @Override
    public void addDescription(StringBuffer sb) {
        String s = (String)EntityList.field_75626_c.get(this.entityLivingClass);
        if (s == null) {
            s = "generic";
        }
        sb.append("\u00a78>\u00a70 ");
        sb.append(StatCollector.func_74838_a((String)("entity." + s + ".name")));
        sb.append(Const.BOOK_NEWLINE);
    }

    @Override
    public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks) {
        return true;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {
        steps.add(new StepSacrificeLiving(this, bounds, maxDistance));
    }

    private static class StepSacrificeLiving
    extends RitualStep {
        private final SacrificeLiving sacrifice;
        private final AxisAlignedBB bounds;
        private final int maxDistance;

        public StepSacrificeLiving(SacrificeLiving sacrifice, AxisAlignedBB bounds, int maxDistance) {
            super(false);
            this.sacrifice = sacrifice;
            this.bounds = bounds;
            this.maxDistance = maxDistance + 1;
        }

        @Override
        public RitualStep.Result process(World worldObj, int xCoord, int yCoord, int zCoord, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            for (Object obj : worldObj.func_72872_a(EntityLiving.class, this.bounds)) {
                EntityLiving entity = (EntityLiving)obj;
                if (!this.sacrifice.entityLivingClass.isInstance(entity) || !(Sacrifice.distance(xCoord, yCoord, zCoord, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) <= (double)this.maxDistance)) continue;
                if (!worldObj.field_72995_K) {
                    entity.func_70106_y();
                    ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, (Entity)entity, 1.0, 2.0, 16);
                }
                return RitualStep.Result.COMPLETED;
            }
            RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), worldObj);
            return RitualStep.Result.ABORTED_REFUND;
        }
    }
}

