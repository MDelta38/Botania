/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteSummonCreature
extends Rite {
    private final Class<? extends EntityCreature> creatureToSummon;
    private boolean bindTameable;

    public RiteSummonCreature(Class<? extends EntityCreature> creatureToSummon, boolean bindTameable) {
        this.creatureToSummon = creatureToSummon;
        this.bindTameable = bindTameable;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepSummonCreature(this));
    }

    private static class StepSummonCreature
    extends RitualStep {
        private final RiteSummonCreature rite;

        public StepSummonCreature(RiteSummonCreature rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                int[][] PATTERN = new int[][]{{0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 0, 0}};
                int obstructions = 0;
                for (int y = posY + 1; y <= posY + 3; ++y) {
                    int offsetZ = (PATTERN.length - 1) / 2;
                    for (int z = 0; z < PATTERN.length - 1; ++z) {
                        int worldZ = posZ - offsetZ + z;
                        int offsetX = (PATTERN[z].length - 1) / 2;
                        for (int x = 0; x < PATTERN[z].length; ++x) {
                            Material material;
                            int worldX = posX - offsetX + x;
                            int val = PATTERN[PATTERN.length - 1 - z][x];
                            if (val == 1) {
                                material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
                                if (material == null || !material.func_76220_a()) continue;
                                ++obstructions;
                                continue;
                            }
                            if (val != 2 || (material = world.func_147439_a(worldX, y, worldZ).func_149688_o()) == null || !material.func_76220_a()) continue;
                            obstructions += 100;
                        }
                    }
                }
                boolean MAX_OBSTRUCTIONS = true;
                if (obstructions <= 1) {
                    try {
                        Constructor ctor = this.rite.creatureToSummon.getConstructor(World.class);
                        EntityLiving entity = (EntityLiving)ctor.newInstance(world);
                        if (entity instanceof EntityDemon) {
                            ((EntityDemon)entity).setPlayerCreated(true);
                        } else {
                            if (entity instanceof EntityImp && ritual.covenSize == 0) {
                                EntityPlayer player = ritual.getInitiatingPlayer(world);
                                SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                                if (player != null) {
                                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.coventoosmall", new Object[0]);
                                }
                                return RitualStep.Result.ABORTED_REFUND;
                            }
                            if (this.rite.bindTameable && entity instanceof EntityTameable) {
                                ((EntityTameable)entity).func_70903_f(true);
                                TameableUtil.setOwner((EntityTameable)entity, ritual.getInitiatingPlayer(world));
                            }
                        }
                        entity.func_70012_b(0.5 + (double)posX, 1.0 + (double)posY, 0.5 + (double)posZ, 1.0f, 0.0f);
                        world.func_72838_d((Entity)entity);
                        IEntityLivingData entitylivingData = null;
                        entitylivingData = entity.func_110161_a(entitylivingData);
                        ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, (Entity)entity, 0.5, 1.0, 16);
                    }
                    catch (NoSuchMethodException ex) {
                    }
                    catch (InvocationTargetException ex) {
                    }
                    catch (InstantiationException ex) {
                    }
                    catch (IllegalAccessException ex) {}
                } else {
                    ParticleEffect.LARGE_SMOKE.send(SoundEffect.NOTE_SNARE, world, posX, posY, posZ, 0.5, 2.0, 16);
                    RiteRegistry.RiteError("witchery.rite.obstructedcircle", ritual.getInitiatingPlayerName(), world);
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

