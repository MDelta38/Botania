/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteSphereEffect
extends Rite {
    protected final int maxRadius;
    protected final Block block;

    public RiteSphereEffect(int radius, Block block) {
        this.maxRadius = radius;
        this.block = block;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepExpansion(this, intialStage));
    }

    private static class StepExpansion
    extends RitualStep {
        private final RiteSphereEffect rite;
        private int stage = 0;
        private boolean activated;

        public StepExpansion(RiteSphereEffect rite, int initialStage) {
            super(false);
            this.rite = rite;
            this.stage = initialStage;
        }

        @Override
        public int getCurrentStage() {
            return (byte)this.stage;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (!this.activated) {
                if (ticks % 20L != 0L) {
                    return RitualStep.Result.STARTING;
                }
                this.activated = true;
                SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
            }
            if (!world.field_72995_K) {
                if (ticks % 5L == 0L) {
                    EntityPlayer player = ritual.getInitiatingPlayer(world);
                    if (ritual.covenSize < 2) {
                        SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                        if (player != null) {
                            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.coventoosmall", new Object[0]);
                        }
                        return RitualStep.Result.ABORTED_REFUND;
                    }
                    ++this.stage;
                    int currentRadius = this.stage + 4;
                    int maxRadius = (int)(ritual.covenSize <= 2 ? (double)this.rite.maxRadius : (ritual.covenSize <= 5 ? 1.5 * (double)this.rite.maxRadius : 2.0 * (double)this.rite.maxRadius));
                    if (currentRadius <= maxRadius) {
                        if (this.stage % 2 == 0) {
                            StepExpansion.drawSphere(world, posX, posY, posZ, currentRadius, this.rite.block);
                            StepExpansion.drawSphere(world, posX, posY, posZ, currentRadius - 2, Blocks.field_150350_a);
                        }
                        if (currentRadius == maxRadius) {
                            StepExpansion.fillWithAir(world, posX, posY, posZ, maxRadius, this.rite.block);
                        }
                    }
                    return this.stage > 250 || currentRadius >= maxRadius ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
                }
                return RitualStep.Result.UPKEEP;
            }
            return RitualStep.Result.COMPLETED;
        }

        private static void fillWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock) {
            StepExpansion.fillHalfWithAirY(world, posX, posY, posZ, 1, radius, removalBlock);
            StepExpansion.fillHalfWithAirY(world, posX, posY - 1, posZ, -1, radius, removalBlock);
        }

        private static void fillHalfWithAirY(World world, int posX, int posY, int posZ, int dy, int radius, Block removalBlock) {
            int realY;
            for (int y = 0; y <= radius && world.func_147439_a(posX, realY = posY + y * dy, posZ) != removalBlock; ++y) {
                StepExpansion.fillSliceWithAir(world, posX, realY, posZ, radius, removalBlock);
            }
        }

        private static void fillSliceWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock) {
            StepExpansion.fillHalfWithAirX(world, posX, posY, posZ, 1, radius, removalBlock);
            StepExpansion.fillHalfWithAirX(world, posX - 1, posY, posZ, -1, radius, removalBlock);
        }

        private static void fillHalfWithAirX(World world, int posX, int posY, int posZ, int dx, int radius, Block removalBlock) {
            int realX;
            for (int x = 0; x <= radius && world.func_147439_a(realX = posX + x * dx, x, posZ) != removalBlock; ++x) {
                StepExpansion.fillLineWithAir(world, realX, posY, posZ, radius, removalBlock);
            }
        }

        private static void fillLineWithAir(World world, int posX, int posY, int posZ, int radius, Block removalBlock) {
            StepExpansion.fillHalfWithAirZ(world, posX, posY, posZ, 1, radius, removalBlock);
            StepExpansion.fillHalfWithAirZ(world, posX, posY, posZ - 1, -1, radius, removalBlock);
        }

        private static void fillHalfWithAirZ(World world, int posX, int posY, int posZ, int dz, int radius, Block removalBlock) {
            int realZ;
            Block foundBlock;
            for (int z = 0; z <= radius && (foundBlock = world.func_147439_a(posX, posY, realZ = posZ + z * dz)) != removalBlock; ++z) {
                if (foundBlock != Blocks.field_150355_j && foundBlock != Blocks.field_150358_i) continue;
                world.func_147449_b(posX, posY, realZ, Blocks.field_150350_a);
            }
        }

        public static void drawSphere(World world, int x0, int y0, int z0, int radius, Block blockID) {
            int x = radius;
            int radiusError = 1 - x;
            for (int y = 0; x >= y; ++y) {
                StepExpansion.drawCircle(world, x0, y0, z0, y, x, radiusError, blockID);
                if (radiusError < 0) {
                    radiusError += 2 * y + 1;
                    continue;
                }
                radiusError += 2 * (y - --x + 1);
            }
        }

        protected static boolean drawCircle(World world, int x0, int y0, int z0, int y1, int radius, int error0, Block blockID) {
            int x = radius;
            int radiusError = error0;
            for (int z = 0; x >= z; ++z) {
                StepExpansion.drawPixel(world, x0 + x, z0 + z, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 - x, z0 + z, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 + x, z0 + z, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 - x, z0 + z, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 + x, z0 - z, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 - x, z0 - z, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 + x, z0 - z, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 - x, z0 - z, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 + x, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 + x, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 + x, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 + x, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 - x, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 - x, y0 + y1, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 - x, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 - x, y0 - y1, blockID);
                StepExpansion.drawPixel(world, x0 + y1, z0 + z, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 - y1, z0 + z, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 + y1, z0 + z, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 - y1, z0 + z, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 + y1, z0 - z, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 - y1, z0 - z, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 + y1, z0 - z, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 - y1, z0 - z, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 + y1, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 + y1, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 + y1, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 + y1, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 - y1, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 - y1, y0 + x, blockID);
                StepExpansion.drawPixel(world, x0 + z, z0 - y1, y0 - x, blockID);
                StepExpansion.drawPixel(world, x0 - z, z0 - y1, y0 - x, blockID);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
            return true;
        }

        protected static void drawPixel(World world, int x, int z, int y, Block replaceBlockID) {
            Block blockID = world.func_147439_a(x, y, z);
            if ((blockID == Blocks.field_150355_j || blockID == Blocks.field_150358_i || blockID == Blocks.field_150350_a || blockID == Blocks.field_150432_aD || blockID == Blocks.field_150433_aE || blockID == Blocks.field_150329_H || blockID == Blocks.field_150395_bd || blockID == Blocks.field_150392_bi || blockID == Blocks.field_150328_O || blockID == Blocks.field_150327_N || blockID == Blocks.field_150434_aF || blockID == Blocks.field_150330_I || blockID == Witchery.Blocks.PERPETUAL_ICE) && blockID != replaceBlockID) {
                world.func_147449_b(x, y, z, replaceBlockID);
            }
        }
    }
}

