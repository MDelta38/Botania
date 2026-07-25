/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteGlyphicTransformation
extends Rite {
    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepGlyphicTransformation(this));
    }

    private static class StepGlyphicTransformation
    extends RitualStep {
        private final RiteGlyphicTransformation rite;

        public StepGlyphicTransformation(RiteGlyphicTransformation rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 30L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                double RADIUS = 4.0;
                List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)((double)posX - 4.0), (double)(posY - 2), (double)((double)posZ - 4.0), (double)((double)posX + 4.0), (double)(posY + 2), (double)((double)posZ + 4.0)));
                int whiteChalk = 0;
                int purpleChalk = 0;
                int redChalk = 0;
                for (Object obj : items) {
                    boolean first;
                    EntityItem item = (EntityItem)obj;
                    ItemStack stack = item.func_92059_d();
                    if (redChalk == 0 && purpleChalk == 0 && stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_RITUAL, 1, 0))) {
                        first = whiteChalk == 0;
                        whiteChalk += stack.field_77994_a;
                        if (first) {
                            --stack.field_77994_a;
                            if (stack.field_77994_a <= 0) {
                                world.func_72900_e((Entity)item);
                            }
                        }
                    } else if (redChalk == 0 && whiteChalk == 0 && stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_OTHERWHERE, 1, 0))) {
                        first = purpleChalk == 0;
                        purpleChalk += stack.field_77994_a;
                        if (first) {
                            --stack.field_77994_a;
                            if (stack.field_77994_a <= 0) {
                                world.func_72900_e((Entity)item);
                            }
                        }
                    } else {
                        if (purpleChalk != 0 || whiteChalk != 0 || !stack.func_77969_a(new ItemStack(Witchery.Items.CHALK_INFERNAL, 1, 0))) continue;
                        first = redChalk == 0;
                        redChalk += stack.field_77994_a;
                        if (first) {
                            --stack.field_77994_a;
                            if (stack.field_77994_a <= 0) {
                                world.func_72900_e((Entity)item);
                            }
                        }
                    }
                    ParticleEffect.SMOKE.send(SoundEffect.RANDOM_POP, (Entity)item, 1.0, 1.0, 16);
                }
                Block blockID = Blocks.field_150350_a;
                int size = 0;
                if (whiteChalk == 0 && redChalk == 0 && purpleChalk == 0) {
                    return RitualStep.Result.ABORTED_REFUND;
                }
                if (redChalk > 0) {
                    blockID = Witchery.Blocks.GLYPH_INFERNAL;
                    size = Math.min(redChalk, 3);
                } else if (purpleChalk > 0) {
                    blockID = Witchery.Blocks.GLYPH_OTHERWHERE;
                    size = Math.min(purpleChalk, 3);
                } else if (whiteChalk > 0) {
                    blockID = Witchery.Blocks.GLYPH_RITUAL;
                    size = Math.min(whiteChalk, 3);
                }
                boolean a = true;
                int b = 2;
                int c = 3;
                boolean _ = false;
                int[][] PATTERN = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0}, {0, 0, 0, 3, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 0, 0, 0}, {0, 0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 3, 0, 0}, {0, 3, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 3, 0}, {0, 3, 0, 2, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 3, 0}, {0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0}, {0, 3, 0, 2, 0, 1, 0, 0, 4, 0, 0, 1, 0, 2, 0, 3, 0}, {0, 3, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0}, {0, 3, 0, 2, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2, 0, 3, 0}, {0, 3, 0, 0, 2, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 3, 0}, {0, 0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 3, 0, 0}, {0, 0, 0, 3, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
                int offsetZ = (PATTERN.length - 1) / 2;
                for (int z = 0; z < PATTERN.length - 1; ++z) {
                    int worldZ = posZ - offsetZ + z;
                    int offsetX = (PATTERN[z].length - 1) / 2;
                    for (int x = 0; x < PATTERN[z].length; ++x) {
                        Block currentBlockID;
                        int worldX = posX - offsetX + x;
                        int item = PATTERN[PATTERN.length - 1 - z][x];
                        if (item != size || (currentBlockID = world.func_147439_a(worldX, posY, worldZ)) != Witchery.Blocks.GLYPH_INFERNAL && currentBlockID != Witchery.Blocks.GLYPH_OTHERWHERE && currentBlockID != Witchery.Blocks.GLYPH_RITUAL || currentBlockID == blockID) continue;
                        int meta = world.func_72805_g(worldX, posY, worldZ);
                        world.func_147465_d(worldX, posY, worldZ, blockID, meta, 3);
                        ParticleEffect.SMOKE.send(SoundEffect.NONE, world, worldX, posY + 1, worldZ, 0.5, 1.0, 16);
                    }
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

