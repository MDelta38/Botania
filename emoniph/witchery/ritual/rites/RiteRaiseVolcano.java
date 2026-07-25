/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteRaiseVolcano
extends Rite {
    private final int radius;
    private final int height;

    public RiteRaiseVolcano(int radius, int height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepRaiseVolcano(this, intialStage));
    }

    private static class StepRaiseVolcano
    extends RitualStep {
        private final RiteRaiseVolcano rite;
        private int stage = 0;

        public StepRaiseVolcano(RiteRaiseVolcano rite, int initialStage) {
            super(true);
            this.rite = rite;
            this.stage = initialStage;
        }

        @Override
        public int getCurrentStage() {
            return (byte)this.stage;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 15L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                if (++this.stage == 1) {
                    boolean lavaFound = false;
                    for (int y = posY; y > 0 && !lavaFound; --y) {
                        Block blockID = world.func_147439_a(posX, y, posZ);
                        if (blockID == Blocks.field_150353_l && this.surroundedByBlocks(world, posX, y, posZ, Blocks.field_150353_l, 2)) {
                            lavaFound = true;
                            continue;
                        }
                        if (blockID == Blocks.field_150357_h) break;
                    }
                    if (lavaFound) {
                        ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5, 1.0, 16);
                    } else {
                        SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                        RiteRegistry.RiteError("witchery.rite.missinglava", ritual.getInitiatingPlayerName(), world);
                        return RitualStep.Result.ABORTED_REFUND;
                    }
                }
                int height = this.rite.height + 4 * ritual.covenSize;
                float radius = this.rite.radius + 2 * ritual.covenSize;
                if (this.stage <= height) {
                    for (int y = 1; y <= this.stage; ++y) {
                        float r = radius - (float)(height - this.stage - 1 + y) * radius / (float)height;
                        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)((float)posX - r), (double)(y + posY), (double)((float)posZ - r), (double)((float)posX + r), (double)(y + posY), (double)((float)posZ + r));
                        this.drawFilledCircle(world, posX, posZ, y + posY - 1, Math.max((int)Math.ceil(r), 1), y, true);
                        if (this.stage == height) {
                            int minusY = posY - 1;
                            int reduce = 0;
                            while (minusY > posY - 5) {
                                this.drawFilledCircle(world, posX, posZ, minusY, Math.max((int)radius - reduce, 2), y, false);
                                --minusY;
                                ++reduce;
                            }
                        }
                        for (Object obj : world.func_72872_a(Entity.class, bounds)) {
                            Material material;
                            Entity entity = (Entity)obj;
                            if (!(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, y + posY, posZ) <= (double)r) || !(material = world.func_147439_a((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v).func_149688_o()).func_76220_a()) continue;
                            if (entity instanceof EntityLivingBase) {
                                ((EntityLivingBase)entity).func_70634_a(entity.field_70165_t, entity.field_70163_u + 1.0, entity.field_70161_v);
                                continue;
                            }
                            entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 1.0, entity.field_70161_v);
                        }
                    }
                } else if (this.stage < height * 2) {
                    if (this.stage == height * 2 - 1) {
                        world.func_147449_b(posX, posY + this.stage - height, posZ, (Block)Blocks.field_150356_k);
                        world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150353_l);
                        if (this.rite.radius == 16) {
                            if (world.field_73012_v.nextInt(4) == 0) {
                                world.func_147449_b(posX, posY + 1 + this.stage - height, posZ, (Block)Blocks.field_150356_k);
                            }
                        } else {
                            switch (world.field_73012_v.nextInt(8)) {
                                case 0: {
                                    world.func_147468_f(posX + 1, posY + height - 1, posZ);
                                    break;
                                }
                                case 1: {
                                    world.func_147468_f(posX, posY + height - 1, posZ + 1);
                                    break;
                                }
                                case 2: {
                                    world.func_147468_f(posX - 1, posY + height - 1, posZ);
                                    break;
                                }
                                case 3: {
                                    world.func_147468_f(posX, posY + height - 1, posZ - 1);
                                }
                            }
                        }
                    } else {
                        world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150348_b);
                        world.func_147449_b(posX, posY + this.stage - height, posZ, Blocks.field_150353_l);
                    }
                } else {
                    for (int y = posY; y > 0; --y) {
                        Block blockID = world.func_147439_a(posX, y, posZ);
                        if (blockID == Blocks.field_150353_l || blockID == Blocks.field_150356_k || blockID == Blocks.field_150357_h) {
                            while (blockID == Blocks.field_150353_l || blockID == Blocks.field_150356_k) {
                                this.setToAirIfLava(world, posX, y, posZ);
                                this.setToAirIfLava(world, posX + 1, y, posZ);
                                this.setToAirIfLava(world, posX - 1, y, posZ);
                                this.setToAirIfLava(world, posX, y, posZ + 1);
                                this.setToAirIfLava(world, posX, y, posZ - 1);
                                blockID = world.func_147439_a(posX, --y, posZ);
                            }
                            break;
                        }
                        world.func_147468_f(posX, y, posZ);
                    }
                    return RitualStep.Result.COMPLETED;
                }
                return RitualStep.Result.UPKEEP;
            }
            return RitualStep.Result.COMPLETED;
        }

        private boolean surroundedByBlocks(World world, int x, int y, int z, Block blockID, int minCount) {
            int count = 0;
            count += world.func_147439_a(x, y - 1, z) == blockID ? 1 : 0;
            count += world.func_147439_a(x - 1, y, z) == blockID ? 1 : 0;
            count += world.func_147439_a(x + 1, y - 1, z) == blockID ? 1 : 0;
            count += world.func_147439_a(x, y, z - 1) == blockID ? 1 : 0;
            count += world.func_147439_a(x, y, z + 1) == blockID ? 1 : 0;
            return (count += world.func_147439_a(x, y + 1, z + 1) == blockID ? 1 : 0) >= minCount;
        }

        private void setToAirIfLava(World world, int posX, int posY, int posZ) {
            Block blockID = world.func_147439_a(posX, posY, posZ);
            if (blockID == Blocks.field_150353_l || blockID == Blocks.field_150356_k) {
                world.func_147468_f(posX, posY, posZ);
            }
        }

        protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int height, boolean replaceBlocks) {
            int x = radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius, height, replaceBlocks);
                this.drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius, height, replaceBlocks);
                this.drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius, height, replaceBlocks);
                this.drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius, height, replaceBlocks);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }

        protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius, int height, boolean replaceBlocks) {
            int modX1 = radius > 1 && world.field_73012_v.nextInt(5) == 0 ? x1 + 1 : x1;
            int modX2 = radius > 1 && world.field_73012_v.nextInt(5) == 0 ? x2 - 1 : x2;
            boolean edgeZ = midZ + radius == z || midZ - radius == z;
            for (int x = modX1; x <= modX2; ++x) {
                if (x == midX && z == midZ) continue;
                this.drawPixel(world, x, z, y, (x == modX1 || x == modX2 || edgeZ) && height < 3, replaceBlocks);
            }
            boolean done = true;
        }

        protected void drawPixel(World world, int x, int z, int y, boolean lower, boolean replaceBlocks) {
            if (replaceBlocks && BlockProtect.canBreak(x, y, z, world) || world.func_147437_c(x, y, z)) {
                world.func_147449_b(x, y, z, (Block)(lower && world.field_73012_v.nextInt(5) != 0 ? Blocks.field_150349_c : Blocks.field_150348_b));
            }
        }
    }
}

