/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteCallCreatures
extends Rite {
    private final float radius;
    private final List<Class<? extends EntityCreature>> creatureTypes;

    public RiteCallCreatures(float radius, Class<? extends EntityCreature>[] creatureTypes) {
        this.radius = radius;
        this.creatureTypes = Arrays.asList(creatureTypes);
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int initialStage) {
        steps.add(new StepCallCreatures(this, initialStage));
    }

    private static class StepCallCreatures
    extends RitualStep {
        private final RiteCallCreatures rite;
        private int stage = 0;

        public StepCallCreatures(RiteCallCreatures rite, int stage) {
            super(false);
            this.rite = rite;
            this.stage = stage;
        }

        private void allure(World world, double posX, double posY, double posZ, int quad) {
            try {
                float r = 128.0f;
                float dy = 10.0f;
                AxisAlignedBB bounds = null;
                switch (quad) {
                    case 0: {
                        bounds = AxisAlignedBB.func_72330_a((double)posX, (double)(posY - 10.0), (double)(posZ - 128.0), (double)(posX + 128.0), (double)posY, (double)posZ);
                        break;
                    }
                    case 1: {
                        bounds = AxisAlignedBB.func_72330_a((double)(posX - 128.0), (double)(posY - 10.0), (double)(posZ - 128.0), (double)posX, (double)posY, (double)posZ);
                        break;
                    }
                    case 2: {
                        bounds = AxisAlignedBB.func_72330_a((double)posX, (double)(posY - 10.0), (double)posZ, (double)(posX + 128.0), (double)posY, (double)(posZ + 128.0));
                        break;
                    }
                    case 3: {
                        bounds = AxisAlignedBB.func_72330_a((double)(posX - 128.0), (double)(posY - 10.0), (double)posZ, (double)posX, (double)posY, (double)(posZ + 128.0));
                        break;
                    }
                    case 4: {
                        bounds = AxisAlignedBB.func_72330_a((double)(posX - 128.0), (double)(posY + 1.0), (double)(posZ - 128.0), (double)posX, (double)(posY + 10.0), (double)posZ);
                        break;
                    }
                    case 5: {
                        bounds = AxisAlignedBB.func_72330_a((double)posX, (double)(posY + 1.0), (double)posZ, (double)(posX + 128.0), (double)(posY + 10.0), (double)(posZ + 128.0));
                        break;
                    }
                    case 6: {
                        bounds = AxisAlignedBB.func_72330_a((double)(posX - 128.0), (double)(posY + 1.0), (double)posZ, (double)posX, (double)(posY + 10.0), (double)(posZ + 128.0));
                        break;
                    }
                    default: {
                        bounds = AxisAlignedBB.func_72330_a((double)posX, (double)(posY + 1.0), (double)(posZ - 128.0), (double)(posX + 128.0), (double)(posY + 10.0), (double)posZ);
                    }
                }
                int count = 0;
                int minDistanceSq = 32;
                for (Object obj : world.func_72872_a(EntityCreature.class, bounds)) {
                    EntityCreature creature = (EntityCreature)obj;
                    if (!this.rite.creatureTypes.contains(creature.getClass()) || !(creature.func_70092_e(posX, posY, posZ) > 32.0) || PotionEnderInhibition.isActive((Entity)creature, 0)) continue;
                    ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                    ItemGeneral.teleportToLocation(world, posX - 2.0 + (double)world.field_73012_v.nextInt(5), posY, posZ - 2.0 + (double)world.field_73012_v.nextInt(5), world.field_73011_w.field_76574_g, (Entity)creature, true);
                    if (++count < 2) continue;
                    break;
                }
            }
            catch (Exception e) {
                Log.instance().debug(String.format("Exception occurred alluring with a ritual! %s", e.toString()));
            }
        }

        @Override
        public int getCurrentStage() {
            return this.stage;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 60L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                if (ritual.covenSize < 3) {
                    EntityPlayer player = ritual.getInitiatingPlayer(world);
                    SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                    if (player != null) {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.coventoosmall", new Object[0]);
                    }
                    return RitualStep.Result.ABORTED_REFUND;
                }
                this.allure(world, posX, posY, posZ, ++this.stage % 8);
            }
            return this.stage < 250 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
        }
    }
}

