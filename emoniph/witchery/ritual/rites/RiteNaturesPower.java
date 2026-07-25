/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.MutableBlock;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class RiteNaturesPower
extends Rite {
    private final int radius;
    private final int height;
    private final int duration;
    private final int expanse;

    public RiteNaturesPower(int radius, int height, int duration, int expanse) {
        this.radius = radius;
        this.height = height;
        this.duration = duration;
        this.expanse = expanse;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepNaturesPower(this, intialStage));
    }

    private static class StepNaturesPower
    extends RitualStep {
        private final RiteNaturesPower rite;
        private int stage = 0;
        private EntityPlayer fakePlayer = null;

        public StepNaturesPower(RiteNaturesPower rite, int initialStage) {
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
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                if (++this.stage < this.rite.duration + ritual.covenSize * 5) {
                    int modradius = this.rite.radius + ritual.covenSize * 2;
                    int x = posX - modradius + world.field_73012_v.nextInt(modradius * 2);
                    int z = posZ - modradius + world.field_73012_v.nextInt(modradius * 2);
                    int y = -1;
                    world.func_72926_e(2005, posX, --posY + 2, posZ, 0);
                    Material material = world.func_147439_a(x, posY, z).func_149688_o();
                    if (material == null || !material.func_76220_a() || !world.func_147437_c(x, posY + 1, z)) {
                        for (int h = 1; h < this.rite.height; ++h) {
                            material = world.func_147439_a(x, posY + h, z).func_149688_o();
                            if (material != null && material.func_76220_a() && world.func_147437_c(x, posY + h + 1, z)) {
                                y = posY + h;
                            } else {
                                material = world.func_147439_a(x, posY - h, z).func_149688_o();
                                if (material == null || !material.func_76220_a() || !world.func_147437_c(x, posY - h + 1, z) && world.func_147439_a(x, posY - h + 1, z) != Blocks.field_150433_aE) continue;
                                y = posY - h;
                            }
                            break;
                        }
                    } else {
                        y = posY;
                    }
                    if (y != -1) {
                        world.func_72926_e(2005, x, y + 1, z, 0);
                        this.drawFilledCircle(world, x, y, z, this.rite.expanse + 1);
                    }
                    return RitualStep.Result.UPKEEP;
                }
                return RitualStep.Result.COMPLETED;
            }
            return RitualStep.Result.COMPLETED;
        }

        protected void drawFilledCircle(World world, int x0, int y, int z0, int radius) {
            int x = radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius);
                this.drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius);
                this.drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius);
                this.drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }

        protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius) {
            int modX1 = radius > 1 && world.field_73012_v.nextInt(5) == 0 ? x1 + 1 : x1;
            int modX2 = radius > 1 && world.field_73012_v.nextInt(5) == 0 ? x2 - 1 : x2;
            boolean edgeZ = midZ + radius == z || midZ - radius == z;
            for (int x = modX1; x <= modX2; ++x) {
                this.drawPixel(world, x, z, y, x == modX1 || x == modX2 || edgeZ);
            }
            boolean done = true;
        }

        private boolean isNeighbourBlockID(World world, int x, int y, int z, Block blockID) {
            if (world.func_147439_a(x + 1, y, z) == blockID) {
                return true;
            }
            if (world.func_147439_a(x - 1, y, z) == blockID) {
                return true;
            }
            if (world.func_147439_a(x, y, z + 1) == blockID) {
                return true;
            }
            return world.func_147439_a(x, y, z - 1) == blockID;
        }

        /*
         * Unable to fully structure code
         */
        protected void drawPixel(World world, int x, int z, int y, boolean lower) {
            block6: {
                block7: {
                    blockID = world.func_147439_a(x, y, z);
                    meta = world.func_72805_g(x, y, z);
                    wasGrass = blockID == Blocks.field_150349_c;
                    materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
                    if (materialAbove == null || materialAbove.func_76220_a()) break block6;
                    if (blockID != Blocks.field_150348_b && blockID != Blocks.field_150354_m && blockID != Blocks.field_150351_n && !Config.instance().canReplaceNaturalBlock(blockID, meta) || world.field_73012_v.nextInt(8) == 0) break block7;
                    if (materialAbove == Material.field_151582_l) ** GOTO lbl-1000
                    v0 = world.field_73012_v.nextDouble();
                    v1 = this.isNeighbourBlockID(world, x, y, z, Blocks.field_150355_j) != false ? 0.7 : 0.02;
                    if (v0 <= v1) {
                        world.func_147449_b(x, y, z, Blocks.field_150355_j);
                    } else lbl-1000:
                    // 2 sources

                    {
                        world.func_147449_b(x, y, z, (Block)Blocks.field_150349_c);
                    }
                    blockID = Blocks.field_150349_c;
                }
                if (materialAbove != Material.field_151582_l && blockID != Blocks.field_150350_a && blockID != Blocks.field_150362_t && blockID != Witchery.Blocks.LEAVES && world.field_73012_v.nextInt(4) == 0) {
                    blocks = new MutableBlock[]{new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(Witchery.Blocks.SAPLING, 0), new MutableBlock(Witchery.Blocks.SAPLING, 1), new MutableBlock(Witchery.Blocks.SAPLING, 2), new MutableBlock(Witchery.Blocks.EMBER_MOSS, 0), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150338_P), new MutableBlock((Block)Blocks.field_150337_Q), new MutableBlock((Block)Blocks.field_150328_O), new MutableBlock((Block)Blocks.field_150327_N), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock((Block)Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150423_aK, 0), new MutableBlock(Blocks.field_150440_ba, 0), new MutableBlock(Witchery.Blocks.GLINT_WEED, 0)};
                    blocks[world.field_73012_v.nextInt(blocks.length)].mutate(world, x, y + 1, z, false);
                }
                if (world.field_73012_v.nextInt(3) == 0) {
                    count = 0;
                    if ((this.fakePlayer == null || this.fakePlayer.field_70170_p != world) && world instanceof WorldServer) {
                        this.fakePlayer = new FakePlayer((WorldServer)world, new GameProfile(UUID.randomUUID(), "[Minecraft]"));
                    }
                    ItemDye.applyBonemeal((ItemStack)Dye.BONE_MEAL.createStack(), (World)world, (int)x, (int)(y + 1), (int)z, (EntityPlayer)this.fakePlayer);
                    saplingBlockID = world.func_147439_a(x, y + 1, z);
                    while ((saplingBlockID == Blocks.field_150345_g || saplingBlockID == Witchery.Blocks.SAPLING) && count++ < 8) {
                        ItemDye.applyBonemeal((ItemStack)Dye.BONE_MEAL.createStack(), (World)world, (int)x, (int)(y + 1), (int)z, (EntityPlayer)this.fakePlayer);
                        saplingBlockID = world.func_147439_a(x, y + 1, z);
                    }
                }
            }
        }
    }
}

