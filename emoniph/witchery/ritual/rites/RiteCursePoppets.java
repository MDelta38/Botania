/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class RiteCursePoppets
extends Rite {
    private final int level;

    public RiteCursePoppets(int level) {
        this.level = level;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepCursePoppets(this));
    }

    private static class StepCursePoppets
    extends RitualStep {
        private final RiteCursePoppets rite;

        public StepCursePoppets(RiteCursePoppets rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                boolean curseMaster;
                boolean complete = false;
                EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
                boolean bl = curseMaster = curseMasterPlayer != null && Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer);
                if (curseMaster) {
                    Iterator<RitualStep.SacrificedItem> i$ = ritual.sacrificedItems.iterator();
                    if (i$.hasNext()) {
                        RitualStep.SacrificedItem item = i$.next();
                        if (item.itemstack.func_77973_b() == Witchery.Items.TAGLOCK_KIT && item.itemstack.func_77960_j() == 1) {
                            EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, 1);
                            if (entity != null && !Witchery.Items.POPPET.poppetProtectionActivated(curseMasterPlayer, null, entity, true)) {
                                Witchery.Items.POPPET.destroyAntiVoodooPoppets(curseMasterPlayer, entity, 10);
                            }
                            complete = true;
                        }
                    }
                } else if (curseMasterPlayer != null) {
                    ChatUtil.sendTranslated((ICommandSender)curseMasterPlayer, "witchery.rite.requirescursemastery", new Object[0]);
                }
                if (complete) {
                    ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5 + (double)posX, 0.1 + (double)posY, 0.5 + (double)posZ, 1.0, 2.0, 16);
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

