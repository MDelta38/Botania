/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteRemoveVampirism
extends Rite {
    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepCurseCreature(this));
    }

    private static class StepCurseCreature
    extends RitualStep {
        private final RiteRemoveVampirism rite;

        public StepCurseCreature(RiteRemoveVampirism rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                boolean complete = false;
                boolean cursed = false;
                EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
                if (!Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.wolfcurse.requirescat", new Object[0]);
                    return RitualStep.Result.ABORTED_REFUND;
                }
                if (ritual.covenSize < 6) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullcoven", new Object[0]);
                    return RitualStep.Result.ABORTED_REFUND;
                }
                for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                    if (item.itemstack.func_77973_b() != Witchery.Items.TAGLOCK_KIT || item.itemstack.func_77960_j() != 1) continue;
                    EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, 1);
                    if (entity == null) break;
                    if (entity instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)entity;
                        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                        if (playerEx.isVampire()) {
                            double MAX_RANGE_SQ = 64.0;
                            if (player.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ) <= 64.0) {
                                if (world.field_73012_v.nextInt(4) != 0) {
                                    playerEx.setVampireLevel(0);
                                } else {
                                    cursed = true;
                                }
                                complete = true;
                                break;
                            }
                            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.wolfcurse.toofar", new Object[0]);
                            break;
                        }
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
                        break;
                    }
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
                    break;
                }
                if (complete) {
                    if (cursed) {
                        ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5 + (double)posX, 0.1 + (double)posY, 0.5 + (double)posZ, 1.0, 2.0, 16);
                    } else {
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5 + (double)posX, 0.1 + (double)posY, 0.5 + (double)posZ, 1.0, 2.0, 16);
                    }
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

