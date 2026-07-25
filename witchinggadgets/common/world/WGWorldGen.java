/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.ChestGenHooks
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.world.ComponentWizardTower
 */
package witchinggadgets.common.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ComponentWizardTower;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;

public class WGWorldGen
implements IWorldGenerator {
    static int n = -1;
    static int[][][] tombShape = new int[][][]{new int[][]{{n, n, n, n, n, n, n, n, n, n, n, n, 0}, {n, n, n, n, n, n, 3, n, n, n, n, n, n}, {n, n, n, n, 1, 3, 1, 3, 1, n, n, n, n}, {n, n, n, 1, 3, 1, 2, 1, 3, 1, n, n, n}, {n, 3, 1, 3, 2, 2, 1, 2, 2, 3, 1, 3, n}, {n, 1, 3, 2, 3, 1, 3, 1, 3, 2, 3, 1, n}, {n, n, 2, 3, 1, 3, 1, 3, 1, 3, 2, n, n}, {n, n, 2, 1, 3, 1, 3, 1, 3, 1, 2, n, n}, {n, n, 1, 2, 1, 3, 1, 3, 1, 2, 1, n, n}, {n, n, 3, 1, 2, 1, 3, 1, 2, 1, 3, n, n}, {n, n, n, 3, 1, 2, 2, 2, 1, 3, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}}, new int[][]{{n, n, n, n, n, n, 1, n, n, n, n, n, n}, {n, n, n, n, 1, 1, 0, 1, 1, n, n, n, n}, {n, n, n, 1, 0, 0, 0, 0, 0, 1, n, n, n}, {n, 1, 1, 0, 4, n, 5, n, 4, 0, 1, 1, n}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 6, 0, 0, 0, 0, 0, 6, 0, 0, 1}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, 1, 0, 6, 0, 0, 0, 0, 0, 6, 0, 1, n}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, 1, 0, 0, 4, 0, 0, 0, 4, 0, 0, 1, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, n, 1, 1, 1, 1, 1, 1, 1, n, n, n}}, new int[][]{{n, n, n, n, n, n, 2, n, n, n, n, n, n}, {n, n, n, n, 2, 2, 0, 2, 2, n, n, n, n}, {n, n, n, 2, 0, 0, 0, 0, 0, 2, n, n, n}, {n, 2, 2, 0, 7, 0, 0, 0, 7, 0, 2, 2, n}, {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}, {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}, {n, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, n}, {n, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, n}, {n, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, n}, {n, 2, 0, 0, 7, 0, 0, 0, 7, 0, 0, 2, n}, {n, n, 2, 0, 0, 0, 0, 0, 0, 0, 2, n, n}, {n, n, n, 2, 2, 2, 2, 2, 2, 2, n, n, n}}, new int[][]{{n, n, n, n, n, n, 1, n, n, n, n, n, n}, {n, n, n, n, 1, 1, 0, 1, 1, n, n, n, n}, {n, n, n, 1, 0, 0, 0, 0, 0, 1, n, n, n}, {n, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, n}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, n, 1, 1, 1, 1, 1, 1, 1, n, n, n}}, new int[][]{{n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, 2, n, n, n, n, n, n}, {n, n, n, n, 1, 1, 0, 1, 1, n, n, n, n}, {n, n, 1, 1, 0, 0, 0, 0, 0, 1, 1, n, n}, {n, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, n}, {n, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, 1, 1, 0, 0, 0, 0, 0, 1, 1, n, n}, {n, n, 1, 2, 0, 0, 0, 0, 0, 2, 1, n, n}, {n, n, n, 1, 1, 1, 1, 1, 1, 1, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}}, new int[][]{{n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, 2, n, n, n, n, n, n}, {n, n, n, n, 1, 1, 0, 1, 1, 0, 0, n, n}, {n, n, 2, 1, 0, 0, 0, 0, 0, 1, 2, n, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, 1, 0, 0, 0, 0, 0, 0, 0, 1, n, n}, {n, n, n, 1, 0, 0, 0, 0, 0, 1, n, n, n}, {n, n, n, 1, 2, 0, 0, 0, 2, 1, n, n, n}, {n, n, n, n, 1, 1, 1, 1, 1, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}}, new int[][]{{n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, 2, n, n, n, n, n, n}, {n, n, n, n, 1, 1, 1, 1, 1, n, n, n, n}, {n, n, n, 1, 2, 0, 0, 0, 2, 1, n, n, n}, {n, n, n, 1, 1, 0, 0, 0, 1, 1, n, n, n}, {n, n, n, n, 1, 0, 0, 0, 1, n, n, n, n}, {n, n, n, n, n, 1, 1, 1, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}}, new int[][]{{n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, 1, 1, 1, n, n, n, n, n}, {n, n, n, n, n, 1, 8, 1, n, n, n, n, n}, {n, n, n, n, n, 1, 1, 1, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, n}}};

    void generateOverworld(World world, Random rand, int chunkX, int chunkZ) {
        this.generateTomb(world, rand, chunkX, chunkZ);
    }

    void generateNether(World world, Random rand, int chunkX, int chunkZ) {
    }

    void generateEnd(World world, Random rand, int chunkX, int chunkZ) {
    }

    void generateTomb(World world, Random rand, int chunkX, int chunkZ) {
        int z;
        int y;
        int x = chunkX * 16 + rand.nextInt(16);
        if (world.func_147439_a(x, y = world.func_72976_f(x, z = chunkZ * 16 + rand.nextInt(16)), z).func_149688_o() == Material.field_151577_b || world.func_147439_a(x, y, z).func_149688_o() == Material.field_151595_p) {
            y -= 8;
            for (int yy = 0; yy < tombShape.length; ++yy) {
                for (int xx = 0; xx < 12; ++xx) {
                    block13: for (int zz = 0; zz < 13; ++zz) {
                        switch (tombShape[yy][xx][zz]) {
                            case 0: {
                                world.func_147468_f(x + xx, y + yy, z + zz);
                                continue block13;
                            }
                            case 1: {
                                world.func_147465_d(x + xx, y + yy, z + zz, WGContent.BlockStoneDevice, 2, 0);
                                continue block13;
                            }
                            case 2: {
                                world.func_147465_d(x + xx, y + yy, z + zz, WGContent.BlockStoneDevice, 3 + rand.nextInt(3), 0);
                                continue block13;
                            }
                            case 3: {
                                world.func_147465_d(x + xx, y + yy, z + zz, ConfigBlocks.blockCosmeticSolid, 1, 0);
                                continue block13;
                            }
                            case 4: {
                                world.func_147465_d(x + xx, y + yy, z + zz, ConfigBlocks.blockCosmeticSolid, 8, 0);
                                continue block13;
                            }
                            case 5: {
                                world.func_147465_d(x + xx, y + yy, z + zz, WGContent.BlockStoneDevice, 6, 0);
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz))).facing = 5;
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz))).inv[0] = new ItemStack(WGContent.ItemMaterial, 2, 5);
                                world.func_147465_d(x + xx, y + yy, z + zz + 1, WGContent.BlockStoneDevice, 6, 0);
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz + 1))).facing = 5;
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz + 1))).dummyRight = true;
                                world.func_147465_d(x + xx, y + yy, z + zz - 1, WGContent.BlockStoneDevice, 6, 0);
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz - 1))).facing = 5;
                                ((TileEntitySarcophagus)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz - 1))).dummyLeft = true;
                                continue block13;
                            }
                            case 6: {
                                world.func_147465_d(x + xx, y + yy, z + zz, (Block)Blocks.field_150486_ae, 4, 0);
                                ChestGenHooks cgh = new ChestGenHooks("towerChestContents", ComponentWizardTower.towerChestContents, 4, 9);
                                WeightedRandomChestContent.func_76293_a((Random)rand, (WeightedRandomChestContent[])cgh.getItems(rand), (IInventory)((TileEntityChest)world.func_147438_o(x + xx, y + yy, z + zz)), (int)cgh.getCount(rand));
                                continue block13;
                            }
                            case 7: {
                                world.func_147465_d(x + xx, y + yy, z + zz, ConfigBlocks.blockAiry, 1, 0);
                                continue block13;
                            }
                            case 8: {
                                world.func_147465_d(x + xx, y + yy, z + zz, WGContent.BlockStoneDevice, 1, 0);
                                ((TileEntityMagicalTileLock)world.func_147438_o((int)(x + xx), (int)(y + yy), (int)(z + zz))).lockPreset = rand.nextInt(16);
                            }
                        }
                    }
                }
            }
        }
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.field_73011_w.field_76574_g) {
            case -1: {
                this.generateNether(world, random, chunkX, chunkZ);
                break;
            }
            case 1: {
                this.generateEnd(world, random, chunkX, chunkZ);
                break;
            }
            default: {
                this.generateOverworld(world, random, chunkX, chunkZ);
            }
        }
        world.func_72964_e(chunkX, chunkZ).func_76630_e();
    }
}

