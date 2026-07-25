/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.research.ResearchManager
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;

public class WandManagerTH
implements IWandTriggerManager {
    @Override
    public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
        switch (event) {
            case 0: {
                if (!ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"healingVat")) break;
                return this.constructVat(world, wand, player, x, y, z, side);
            }
        }
        return false;
    }

    boolean constructVat(World world, ItemStack itemstack, EntityPlayer player, int x, int y, int z, int side) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 3; yy <= y; ++yy) {
                for (int zz = z - 2; zz <= z; ++zz) {
                    if (!this.fitVat(world, xx, yy, zz) || !wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.WATER, 50).add(Aspect.EARTH, 50).add(Aspect.ORDER, 50), true)) continue;
                    if (!world.field_72995_K) {
                        this.replaceVat(world, xx, yy, zz);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    boolean fitVat(World world, int x, int y, int z) {
        Block g = Blocks.field_150359_w;
        Block w = Blocks.field_150355_j;
        Block p = ConfigBlocks.blockWoodenDevice;
        Block a = ConfigBlocks.blockMetalDevice;
        Block[][][] blueprint = new Block[][][]{{{p, p, p}, {p, a, p}, {p, p, p}}, {{g, g, g}, {g, w, g}, {g, g, g}}, {{g, g, g}, {g, w, g}, {g, g, g}}, {{p, p, p}, {p, a, p}, {p, p, p}}};
        for (int yy = 0; yy < 4; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    Block block = world.func_147439_a(x + xx, y - yy + 3, z + zz);
                    if (world.func_147437_c(x + xx, y - yy + 3, z + zz)) {
                        block = Blocks.field_150350_a;
                    }
                    if (block == blueprint[yy][xx][zz] && (block != p || world.func_72805_g(x + xx, y - yy + 3, z + zz) == 6) && (block != a || world.func_72805_g(x + xx, y - yy + 3, z + zz) == 9)) continue;
                    return false;
                }
            }
        }
        return true;
    }

    void replaceVat(World world, int x, int y, int z) {
        int xx;
        int zz;
        int yy;
        for (yy = 0; yy < 4; ++yy) {
            for (zz = 0; zz < 3; ++zz) {
                for (xx = 0; xx < 3; ++xx) {
                    int md = 0;
                    if (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150355_j || world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150358_i) {
                        md = 0;
                    } else if (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150359_w) {
                        md = 10;
                    } else if (world.func_147439_a(x + xx, y + yy, z + zz) == ConfigBlocks.blockWoodenDevice) {
                        md = yy == 0 && (xx == 1 && zz == 0 || xx == 1 && zz == 2 || xx == 0 && zz == 1 || xx == 2 && zz == 1) ? 4 : 5;
                    } else if (world.func_147439_a(x + xx, y + yy, z + zz) == ConfigBlocks.blockMetalDevice) {
                        md = yy == 0 ? 6 : 7;
                    }
                    if (world.func_147437_c(x + xx, y + yy, z + zz)) continue;
                    if (md == 4 || md == 5 || md == 6 || md == 7) {
                        world.func_147465_d(x + xx, y + yy, z + zz, ThaumicHorizons.blockVatSolid, md, 3);
                    } else if (md != 0) {
                        world.func_147465_d(x + xx, y + yy, z + zz, ThaumicHorizons.blockVat, md, 3);
                    } else {
                        world.func_147465_d(x + xx, y + yy, z + zz, ThaumicHorizons.blockVatInterior, md, 3);
                    }
                    world.func_147452_c(x + xx, y + yy, z + zz, ThaumicHorizons.blockVat, 1, 4);
                }
            }
        }
        for (yy = 0; yy < 4; ++yy) {
            for (zz = 0; zz < 3; ++zz) {
                for (xx = 0; xx < 3; ++xx) {
                    world.func_147471_g(x + xx, y + yy, z + zz);
                }
            }
        }
        world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
    }
}

