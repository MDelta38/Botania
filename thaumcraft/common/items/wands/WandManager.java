/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.common.items.wands;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.IArchitect;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemFocusPouch;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.foci.ItemFocusTrade;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileEldritchAltar;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumcraft.common.tiles.TileJarNode;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TilePedestal;
import thaumcraft.common.tiles.TileThaumatorium;

public class WandManager
implements IWandTriggerManager {
    static HashMap<Integer, Long> cooldownServer = new HashMap();
    static HashMap<Integer, Long> cooldownClient = new HashMap();

    public static float getTotalVisDiscount(EntityPlayer player, Aspect aspect) {
        int a;
        int total = 0;
        if (player == null) {
            return 0.0f;
        }
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (a = 0; a < 4; ++a) {
            if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof IVisDiscountGear)) continue;
            total += ((IVisDiscountGear)baubles.func_70301_a(a).func_77973_b()).getVisDiscount(baubles.func_70301_a(a), player, aspect);
        }
        for (a = 0; a < 4; ++a) {
            if (player.field_71071_by.func_70440_f(a) == null || !(player.field_71071_by.func_70440_f(a).func_77973_b() instanceof IVisDiscountGear)) continue;
            total += ((IVisDiscountGear)player.field_71071_by.func_70440_f(a).func_77973_b()).getVisDiscount(player.field_71071_by.func_70440_f(a), player, aspect);
        }
        if (player.func_82165_m(Config.potionVisExhaustID) || player.func_82165_m(Config.potionInfVisExhaustID)) {
            int level1 = 0;
            int level2 = 0;
            if (player.func_82165_m(Config.potionVisExhaustID)) {
                level1 = player.func_70660_b(Potion.field_76425_a[Config.potionVisExhaustID]).func_76458_c();
            }
            if (player.func_82165_m(Config.potionInfVisExhaustID)) {
                level2 = player.func_70660_b(Potion.field_76425_a[Config.potionInfVisExhaustID]).func_76458_c();
            }
            total -= (Math.max(level1, level2) + 1) * 10;
        }
        return (float)total / 100.0f;
    }

    public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost) {
        int a;
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (a = 0; a < 4; ++a) {
            boolean done;
            if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemAmuletVis) || !(done = ((ItemAmuletVis)baubles.func_70301_a(a).func_77973_b()).consumeAllVis(baubles.func_70301_a(a), player, cost, true, true))) continue;
            return true;
        }
        for (a = player.field_71071_by.field_70462_a.length - 1; a >= 0; --a) {
            boolean done;
            ItemStack item = player.field_71071_by.field_70462_a[a];
            if (item == null || !(item.func_77973_b() instanceof ItemWandCasting) || !(done = ((ItemWandCasting)item.func_77973_b()).consumeAllVis(item, player, cost, true, true))) continue;
            return true;
        }
        return false;
    }

    public static boolean createCrucible(ItemStack is, EntityPlayer player, World world, int x, int y, int z) {
        ItemWandCasting wand = (ItemWandCasting)is.func_77973_b();
        if (!world.field_72995_K) {
            world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
            world.func_147468_f(x, y, z);
            world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, 0, 3);
            world.func_147459_d(x, y, z, world.func_147439_a(x, y, z));
            world.func_147471_g(x, y, z);
            world.func_147452_c(x, y, z, ConfigBlocks.blockMetalDevice, 1, 1);
            return true;
        }
        return false;
    }

    public static boolean createInfusionAltar(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 2; yy <= y; ++yy) {
                for (int zz = z - 2; zz <= z; ++zz) {
                    if (!WandManager.fitInfusionAltar(world, xx, yy, zz) || !wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25).add(Aspect.ENTROPY, 25).add(Aspect.WATER, 25), true)) continue;
                    if (!world.field_72995_K) {
                        WandManager.replaceInfusionAltar(world, xx, yy, zz);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean fitInfusionAltar(World world, int x, int y, int z) {
        ItemStack br1 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
        ItemStack br2 = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7);
        ItemStack bs = new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2);
        ItemStack bp = new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1);
        ItemStack[][][] blueprint = new ItemStack[][][]{{{null, null, null}, {null, bs, null}, {null, null, null}}, {{br1, null, br1}, {null, null, null}, {br1, null, br1}}, {{br2, null, br2}, {null, null, null}, {br2, null, br2}}};
        for (int yy = 0; yy < 3; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    int md;
                    if (blueprint[yy][xx][zz] == null) {
                        if (xx == 1 && zz == 1 && yy == 2) {
                            TileEntity t = world.func_147438_o(x + xx, y - yy + 2, z + zz);
                            if (t != null && t instanceof TilePedestal) continue;
                            return false;
                        }
                        if (world.func_147437_c(x + xx, y - yy + 2, z + zz)) continue;
                        return false;
                    }
                    Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
                    if (new ItemStack(block, 1, md = world.func_72805_g(x + xx, y - yy + 2, z + zz)).func_77969_a(blueprint[yy][xx][zz])) continue;
                    return false;
                }
            }
        }
        return true;
    }

    public static void replaceInfusionAltar(World world, int x, int y, int z) {
        int[][][] blueprint = new int[][][]{new int[][]{{0, 0, 0}, {0, 9, 0}, {0, 0, 0}}, new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, new int[][]{{2, 0, 3}, {0, 0, 0}, {4, 0, 5}}};
        for (int yy = 0; yy < 3; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    if (blueprint[yy][xx][zz] == 0) continue;
                    if (blueprint[yy][xx][zz] == 1) {
                        world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 4, 3);
                        world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
                    }
                    if (blueprint[yy][xx][zz] > 1 && blueprint[yy][xx][zz] < 9) {
                        world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 3, 3);
                        TileInfusionPillar tip = (TileInfusionPillar)world.func_147438_o(x + xx, y - yy + 2, z + zz);
                        tip.orientation = (byte)blueprint[yy][xx][zz];
                        world.func_147471_g(x + xx, y - yy + 2, z + zz);
                        world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockStoneDevice, 1, 0);
                    }
                    if (blueprint[yy][xx][zz] != 9) continue;
                    TileInfusionMatrix tis = (TileInfusionMatrix)world.func_147438_o(x + xx, y - yy + 2, z + zz);
                    tis.active = true;
                    world.func_147471_g(x + xx, y - yy + 2, z + zz);
                }
            }
        }
        world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
    }

    public static boolean createNodeJar(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 3; yy <= y; ++yy) {
                for (int zz = z - 2; zz <= z; ++zz) {
                    if (!WandManager.fitNodeJar(world, xx, yy, zz) || !wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 70).add(Aspect.EARTH, 70).add(Aspect.ORDER, 70).add(Aspect.AIR, 70).add(Aspect.ENTROPY, 70).add(Aspect.WATER, 70), true)) continue;
                    if (!world.field_72995_K) {
                        WandManager.replaceNodeJar(world, xx, yy, zz);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean createThaumatorium(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockMetalDevice || world.func_72805_g(x, y + 1, z) != 9 || world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockMetalDevice || world.func_72805_g(x, y - 1, z) != 0) {
            if (world.func_147439_a(x, y - 1, z) == ConfigBlocks.blockMetalDevice && world.func_72805_g(x, y - 1, z) == 9 && world.func_147439_a(x, y - 2, z) == ConfigBlocks.blockMetalDevice && world.func_72805_g(x, y - 2, z) == 0) {
                --y;
            } else {
                return false;
            }
        }
        if (wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 15).add(Aspect.ORDER, 30).add(Aspect.WATER, 30), true) && !world.field_72995_K) {
            world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, 10, 0);
            world.func_147465_d(x, y + 1, z, ConfigBlocks.blockMetalDevice, 11, 0);
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileThaumatorium) {
                ((TileThaumatorium)tile).facing = ForgeDirection.getOrientation((int)side);
            }
            world.func_147471_g(x, y, z);
            world.func_147471_g(x, y + 1, z);
            world.func_147444_c(x, y, z, ConfigBlocks.blockMetalDevice);
            world.func_147444_c(x, y + 1, z, ConfigBlocks.blockMetalDevice);
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(x, y, z, -9999), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)x, (double)y, (double)z, 32.0));
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(x, y + 1, z, -9999), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)x, (double)y, (double)z, 32.0));
            world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    static boolean containsMatch(boolean strict, List<ItemStack> inputs, ItemStack ... targets) {
        for (ItemStack input : inputs) {
            for (ItemStack target : targets) {
                if (!OreDictionary.itemMatches((ItemStack)input, (ItemStack)target, (boolean)strict)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean fitNodeJar(World world, int x, int y, int z) {
        int[][][] blueprint = new int[][][]{new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, new int[][]{{2, 2, 2}, {2, 3, 2}, {2, 2, 2}}, new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}};
        for (int yy = 0; yy < 4; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    TileEntity tile;
                    Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
                    int md = world.func_72805_g(x + xx, y - yy + 2, z + zz);
                    if (blueprint[yy][xx][zz] == 1 && !WandManager.containsMatch(false, OreDictionary.getOres((String)"slabWood"), new ItemStack(block, 1, md))) {
                        return false;
                    }
                    if (blueprint[yy][xx][zz] == 2 && block != Blocks.field_150359_w) {
                        return false;
                    }
                    if (blueprint[yy][xx][zz] != 3 || (tile = world.func_147438_o(x + xx, y - yy + 2, z + zz)) != null && tile instanceof INode && !(tile instanceof TileJarNode)) continue;
                    return false;
                }
            }
        }
        return true;
    }

    public static void replaceNodeJar(World world, int x, int y, int z) {
        if (world.field_72995_K) {
            return;
        }
        int[][][] blueprint = new int[][][]{new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, new int[][]{{2, 2, 2}, {2, 3, 2}, {2, 2, 2}}, new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}};
        for (int yy = 0; yy < 4; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    if (blueprint[yy][xx][zz] == 3) {
                        TileEntity tile = world.func_147438_o(x + xx, y - yy + 2, z + zz);
                        INode node = (INode)tile;
                        AspectList na = node.getAspects().copy();
                        int nt = node.getNodeType().ordinal();
                        int nm = -1;
                        if (node.getNodeModifier() != null) {
                            nm = node.getNodeModifier().ordinal();
                        }
                        if (world.field_73012_v.nextFloat() < 0.75f) {
                            if (node.getNodeModifier() == null) {
                                nm = NodeModifier.PALE.ordinal();
                            } else if (node.getNodeModifier() == NodeModifier.BRIGHT) {
                                nm = -1;
                            } else if (node.getNodeModifier() == NodeModifier.PALE) {
                                nm = NodeModifier.FADING.ordinal();
                            }
                        }
                        String nid = node.getId();
                        node.setAspects(new AspectList());
                        world.func_147475_p(x + xx, y - yy + 2, z + zz);
                        world.func_147465_d(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockJar, 2, 3);
                        tile = world.func_147438_o(x + xx, y - yy + 2, z + zz);
                        TileJarNode jar = (TileJarNode)tile;
                        jar.setAspects(na);
                        if (nm >= 0) {
                            jar.setNodeModifier(NodeModifier.values()[nm]);
                        }
                        jar.setNodeType(NodeType.values()[nt]);
                        jar.setId(nid);
                        world.func_147452_c(x + xx, y - yy + 2, z + zz, ConfigBlocks.blockJar, 9, 0);
                        continue;
                    }
                    world.func_147468_f(x + xx, y - yy + 2, z + zz);
                }
            }
        }
        world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
    }

    public static boolean createArcaneFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 2; yy <= y; ++yy) {
                for (int zz = z - 2; zz <= z; ++zz) {
                    if (!WandManager.fitArcaneFurnace(world, xx, yy, zz) || !wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50), true)) continue;
                    if (!world.field_72995_K) {
                        WandManager.replaceArcaneFurnace(world, xx, yy, zz);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean fitArcaneFurnace(World world, int x, int y, int z) {
        Block bo = Blocks.field_150343_Z;
        Block bn = Blocks.field_150385_bj;
        Block bf = Blocks.field_150411_aY;
        Block bl = Blocks.field_150353_l;
        Block[][][] blueprint = new Block[][][]{{{bn, bo, bn}, {bo, Blocks.field_150350_a, bo}, {bn, bo, bn}}, {{bn, bo, bn}, {bo, bl, bo}, {bn, bo, bn}}, {{bn, bo, bn}, {bo, bo, bo}, {bn, bo, bn}}};
        boolean fencefound = false;
        for (int yy = 0; yy < 3; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    Block block = world.func_147439_a(x + xx, y - yy + 2, z + zz);
                    if (world.func_147437_c(x + xx, y - yy + 2, z + zz)) {
                        block = Blocks.field_150350_a;
                    }
                    if (block == blueprint[yy][xx][zz]) continue;
                    if (!(yy != 1 || fencefound || block != bf || xx == zz || xx != 1 && zz != 1)) {
                        fencefound = true;
                        continue;
                    }
                    return false;
                }
            }
        }
        return fencefound;
    }

    public static boolean replaceArcaneFurnace(World world, int x, int y, int z) {
        int yy;
        boolean fencefound = false;
        for (yy = 0; yy < 3; ++yy) {
            int step = 1;
            for (int zz = 0; zz < 3; ++zz) {
                for (int xx = 0; xx < 3; ++xx) {
                    int md = step;
                    if (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150353_l || world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150356_k) {
                        md = 0;
                    }
                    if (world.func_147439_a(x + xx, y + yy, z + zz) == Blocks.field_150411_aY) {
                        md = 10;
                    }
                    if (!world.func_147437_c(x + xx, y + yy, z + zz)) {
                        world.func_147465_d(x + xx, y + yy, z + zz, ConfigBlocks.blockArcaneFurnace, md, 0);
                        world.func_147452_c(x + xx, y + yy, z + zz, ConfigBlocks.blockArcaneFurnace, 1, 4);
                    }
                    ++step;
                }
            }
        }
        for (yy = 0; yy < 3; ++yy) {
            for (int zz = 0; zz < 3; ++zz) {
                for (int xx = 0; xx < 3; ++xx) {
                    world.func_147471_g(x + xx, y + yy, z + zz);
                }
            }
        }
        world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
        return fencefound;
    }

    public static boolean createThaumonomicon(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
        if (!world.field_72995_K) {
            ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
            if (wand.getFocus(itemstack) != null) {
                return false;
            }
            world.func_147468_f(x, y, z);
            EntitySpecialItem entityItem = new EntitySpecialItem(world, (float)x + 0.5f, (float)y + 0.3f, (float)z + 0.5f, new ItemStack(ConfigItems.itemThaumonomicon));
            entityItem.field_70181_x = 0.0;
            entityItem.field_70159_w = 0.0;
            entityItem.field_70179_y = 0.0;
            world.func_72838_d((Entity)entityItem);
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(x, y, z, -9999), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)x, (double)y, (double)z, 32.0));
            world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    private static boolean createOculus(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side) {
        if (!world.field_72995_K) {
            ItemWandCasting wand;
            TileEntity tile = world.func_147438_o(x, y, z);
            TileEntity node = world.func_147438_o(x, y + 1, z);
            if (tile != null && node != null && tile instanceof TileEldritchAltar && ((TileEldritchAltar)tile).getEyes() == 4 && !((TileEldritchAltar)tile).isOpen() && node instanceof TileNode && ((TileNode)node).getNodeType() == NodeType.DARK && ((TileEldritchAltar)tile).checkForMaze() && (wand = (ItemWandCasting)itemstack.func_77973_b()).consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.AIR, 100).add(Aspect.FIRE, 100).add(Aspect.EARTH, 100).add(Aspect.WATER, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), true)) {
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                ((TileEldritchAltar)tile).setOpen(true);
                world.func_147468_f(x, y + 1, z);
                world.func_147449_b(x, y + 1, z, ConfigBlocks.blockEldritchPortal);
                tile.func_70296_d();
                world.func_147471_g(x, y, z);
            }
        }
        return false;
    }

    public static void changeFocus(ItemStack is, World w, EntityPlayer player, String focus) {
        int q;
        ItemStack[] inv;
        int a;
        ItemWandCasting wand = (ItemWandCasting)is.func_77973_b();
        TreeMap<String, Integer> foci = new TreeMap<String, Integer>();
        HashMap<Integer, Integer> pouches = new HashMap<Integer, Integer>();
        int pouchcount = 0;
        ItemStack item = null;
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (a = 0; a < 4; ++a) {
            if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch)) continue;
            item = baubles.func_70301_a(a);
            pouches.put(++pouchcount, a - 4);
            inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
            for (q = 0; q < inv.length; ++q) {
                item = inv[q];
                if (item == null || !(item.func_77973_b() instanceof ItemFocusBasic)) continue;
                foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), q + pouchcount * 1000);
            }
        }
        for (a = 0; a < 36; ++a) {
            item = player.field_71071_by.field_70462_a[a];
            if (item != null && item.func_77973_b() instanceof ItemFocusBasic) {
                foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), a);
            }
            if (item == null || !(item.func_77973_b() instanceof ItemFocusPouch)) continue;
            pouches.put(++pouchcount, a);
            inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
            for (q = 0; q < inv.length; ++q) {
                item = inv[q];
                if (item == null || !(item.func_77973_b() instanceof ItemFocusBasic)) continue;
                foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), q + pouchcount * 1000);
            }
        }
        if (focus.equals("REMOVE") || foci.size() == 0) {
            if (wand.getFocus(is) != null && (WandManager.addFocusToPouch(player, wand.getFocusItem(is).func_77946_l(), pouches) || player.field_71071_by.func_70441_a(wand.getFocusItem(is).func_77946_l()))) {
                wand.setFocus(is, null);
                w.func_72956_a((Entity)player, "thaumcraft:cameraticks", 0.3f, 0.9f);
            }
            return;
        }
        if (foci != null && foci.size() > 0 && focus != null) {
            String newkey = focus;
            if (foci.get(newkey) == null) {
                newkey = foci.higherKey(newkey);
            }
            if (newkey == null || foci.get(newkey) == null) {
                newkey = (String)foci.firstKey();
            }
            if ((Integer)foci.get(newkey) < 1000 && (Integer)foci.get(newkey) >= 0) {
                item = player.field_71071_by.field_70462_a[(Integer)foci.get(newkey)].func_77946_l();
            } else {
                int pid = (Integer)foci.get(newkey) / 1000;
                if (pouches.containsKey(pid)) {
                    int pouchslot = pouches.get(pid);
                    int focusslot = (Integer)foci.get(newkey) - pid * 1000;
                    ItemStack tmp = null;
                    tmp = pouchslot >= 0 ? player.field_71071_by.field_70462_a[pouchslot].func_77946_l() : baubles.func_70301_a(pouchslot + 4).func_77946_l();
                    item = WandManager.fetchFocusFromPouch(player, focusslot, tmp, pouchslot);
                }
            }
            if (item != null) {
                if ((Integer)foci.get(newkey) < 1000 && (Integer)foci.get(newkey) >= 0) {
                    player.field_71071_by.func_70299_a(((Integer)foci.get(newkey)).intValue(), null);
                }
            } else {
                return;
            }
            w.func_72956_a((Entity)player, "thaumcraft:cameraticks", 0.3f, 1.0f);
            if (wand.getFocus(is) != null && (WandManager.addFocusToPouch(player, wand.getFocusItem(is).func_77946_l(), pouches) || player.field_71071_by.func_70441_a(wand.getFocusItem(is).func_77946_l()))) {
                wand.setFocus(is, null);
            }
            if (wand.getFocus(is) == null) {
                wand.setFocus(is, item);
            } else if (!WandManager.addFocusToPouch(player, item, pouches)) {
                player.field_71071_by.func_70441_a(item);
            }
        }
    }

    private static ItemStack fetchFocusFromPouch(EntityPlayer player, int focusid, ItemStack pouch, int pouchslot) {
        ItemStack focus = null;
        ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
        ItemStack contents = inv[focusid];
        if (contents != null && contents.func_77973_b() instanceof ItemFocusBasic) {
            focus = contents.func_77946_l();
            inv[focusid] = null;
            ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
            if (pouchslot >= 0) {
                player.field_71071_by.func_70299_a(pouchslot, pouch);
                player.field_71071_by.func_70296_d();
            } else {
                IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
                baubles.func_70299_a(pouchslot + 4, pouch);
                baubles.func_70296_d();
            }
        }
        return focus;
    }

    private static boolean addFocusToPouch(EntityPlayer player, ItemStack focus, HashMap<Integer, Integer> pouches) {
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (Integer pouchslot : pouches.values()) {
            ItemStack pouch = pouchslot >= 0 ? player.field_71071_by.field_70462_a[pouchslot] : baubles.func_70301_a(pouchslot + 4);
            ItemStack[] inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
            for (int q = 0; q < inv.length; ++q) {
                ItemStack contents = inv[q];
                if (contents != null) continue;
                inv[q] = focus.func_77946_l();
                ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
                if (pouchslot >= 0) {
                    player.field_71071_by.func_70299_a(pouchslot.intValue(), pouch);
                    player.field_71071_by.func_70296_d();
                } else {
                    baubles.func_70299_a(pouchslot + 4, pouch);
                    baubles.func_70296_d();
                }
                return true;
            }
        }
        return false;
    }

    public static void toggleMisc(ItemStack itemstack, World world, EntityPlayer player) {
        if (!(itemstack.func_77973_b() instanceof ItemWandCasting)) {
            return;
        }
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (wand.getFocus(itemstack) != null && wand.getFocus(itemstack) instanceof IArchitect && wand.getFocus(itemstack).isUpgradedWith(wand.getFocusItem(itemstack), FocusUpgradeType.architect)) {
            int dim = WandManager.getAreaDim(itemstack);
            IArchitect fa = (IArchitect)((Object)wand.getFocus(itemstack));
            if (player.func_70093_af()) {
                if (++dim > (wand.getFocusItem(itemstack).func_77973_b() instanceof ItemFocusTrade ? 2 : 3)) {
                    dim = 0;
                }
                WandManager.setAreaDim(itemstack, dim);
            } else {
                int areax = WandManager.getAreaX(itemstack);
                int areay = WandManager.getAreaY(itemstack);
                int areaz = WandManager.getAreaZ(itemstack);
                if (dim == 0) {
                    ++areax;
                    ++areaz;
                    ++areay;
                } else if (dim == 1) {
                    ++areax;
                } else if (dim == 2) {
                    ++areaz;
                } else if (dim == 3) {
                    ++areay;
                }
                if (areax > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
                    areax = 0;
                }
                if (areaz > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
                    areaz = 0;
                }
                if (areay > wand.getFocus(itemstack).getMaxAreaSize(wand.getFocusItem(itemstack))) {
                    areay = 0;
                }
                WandManager.setAreaX(itemstack, areax);
                WandManager.setAreaY(itemstack, areay);
                WandManager.setAreaZ(itemstack, areaz);
            }
        }
    }

    public static int getAreaDim(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("aread")) {
            return stack.field_77990_d.func_74762_e("aread");
        }
        return 0;
    }

    public static int getAreaX(ItemStack stack) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("areax")) {
            int a = stack.field_77990_d.func_74762_e("areax");
            if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
                a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
            }
            return a;
        }
        return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
    }

    public static int getAreaY(ItemStack stack) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("areay")) {
            int a = stack.field_77990_d.func_74762_e("areay");
            if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
                a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
            }
            return a;
        }
        return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
    }

    public static int getAreaZ(ItemStack stack) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("areaz")) {
            int a = stack.field_77990_d.func_74762_e("areaz");
            if (a > wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack))) {
                a = wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
            }
            return a;
        }
        return wand.getFocus(stack).getMaxAreaSize(wand.getFocusItem(stack));
    }

    public static void setAreaX(ItemStack stack, int area) {
        if (stack.func_77942_o()) {
            stack.field_77990_d.func_74768_a("areax", area);
        }
    }

    public static void setAreaY(ItemStack stack, int area) {
        if (stack.func_77942_o()) {
            stack.field_77990_d.func_74768_a("areay", area);
        }
    }

    public static void setAreaZ(ItemStack stack, int area) {
        if (stack.func_77942_o()) {
            stack.field_77990_d.func_74768_a("areaz", area);
        }
    }

    public static void setAreaDim(ItemStack stack, int dim) {
        if (stack.func_77942_o()) {
            stack.field_77990_d.func_74768_a("aread", dim);
        }
    }

    static boolean isOnCooldown(EntityLivingBase entityLiving) {
        if (entityLiving.field_70170_p.field_72995_K && cooldownClient.containsKey(entityLiving.func_145782_y())) {
            return cooldownClient.get(entityLiving.func_145782_y()) > System.currentTimeMillis();
        }
        if (!entityLiving.field_70170_p.field_72995_K && cooldownServer.containsKey(entityLiving.func_145782_y())) {
            return cooldownServer.get(entityLiving.func_145782_y()) > System.currentTimeMillis();
        }
        return false;
    }

    public static float getCooldown(EntityLivingBase entityLiving) {
        if (entityLiving.field_70170_p.field_72995_K && cooldownClient.containsKey(entityLiving.func_145782_y())) {
            return (float)(cooldownClient.get(entityLiving.func_145782_y()) - System.currentTimeMillis()) / 1000.0f;
        }
        return 0.0f;
    }

    public static void setCooldown(EntityLivingBase entityLiving, int cd) {
        if (cd == 0) {
            cooldownClient.remove(entityLiving.func_145782_y());
            cooldownServer.remove(entityLiving.func_145782_y());
        } else if (entityLiving.field_70170_p.field_72995_K) {
            cooldownClient.put(entityLiving.func_145782_y(), System.currentTimeMillis() + (long)cd);
        } else {
            cooldownServer.put(entityLiving.func_145782_y(), System.currentTimeMillis() + (long)cd);
        }
    }

    @Override
    public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
        switch (event) {
            case 0: {
                return WandManager.createThaumonomicon(wand, player, world, x, y, z);
            }
            case 1: {
                return WandManager.createCrucible(wand, player, world, x, y, z);
            }
            case 2: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "INFERNALFURNACE")) break;
                return WandManager.createArcaneFurnace(wand, player, world, x, y, z);
            }
            case 3: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "INFUSION")) break;
                return WandManager.createInfusionAltar(wand, player, world, x, y, z);
            }
            case 4: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "NODEJAR")) break;
                return WandManager.createNodeJar(wand, player, world, x, y, z);
            }
            case 5: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "THAUMATORIUM")) break;
                return WandManager.createThaumatorium(wand, player, world, x, y, z, side);
            }
            case 6: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "OCULUS")) break;
                return WandManager.createOculus(wand, player, world, x, y, z, side);
            }
            case 7: {
                if (!ResearchManager.isResearchComplete(player.func_70005_c_(), "ADVALCHEMYFURNACE")) break;
                return WandManager.createAdvancedAlchemicalFurnace(wand, player, world, x, y, z, side);
            }
        }
        return false;
    }

    private static boolean createAdvancedAlchemicalFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side) {
        if (world.field_72995_K) {
            return false;
        }
        int[][][] blueprint = new int[][][]{new int[][]{{4, 4, 4}, {4, 3, 4}, {4, 4, 4}}, new int[][]{{1, 2, 1}, {2, 0, 2}, {1, 2, 1}}};
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                for (int c = -1; c <= 1; ++c) {
                    if (world.func_147439_a(x + a, y + b, z + c) != ConfigBlocks.blockStoneDevice || world.func_72805_g(x + a, y + b, z + c) != 0) continue;
                    for (int aa = -1; aa <= 1; ++aa) {
                        for (int bb = 0; bb <= 1; ++bb) {
                            for (int cc = -1; cc <= 1; ++cc) {
                                if (blueprint[bb][aa + 1][cc + 1] == 1 && (world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice || world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 1)) {
                                    return false;
                                }
                                if (blueprint[bb][aa + 1][cc + 1] == 2 && (world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice || world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 9)) {
                                    return false;
                                }
                                if (blueprint[bb][aa + 1][cc + 1] == 4 && (world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) != ConfigBlocks.blockMetalDevice || world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) != 3)) {
                                    return false;
                                }
                                if (blueprint[bb][aa + 1][cc + 1] != 3 || world.func_147439_a(x + a + aa, y + b + bb, z + c + cc) == ConfigBlocks.blockStoneDevice && world.func_72805_g(x + a + aa, y + b + bb, z + c + cc) == 0) continue;
                                return false;
                            }
                        }
                    }
                    ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
                    if (!wand.consumeAllVisCrafting(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ORDER, 50), true)) {
                        return false;
                    }
                    world.func_147449_b(x + a, y + b, z + c, ConfigBlocks.blockAlchemyFurnace);
                    world.func_147465_d(x + a - 1, y + b, z + c, ConfigBlocks.blockAlchemyFurnace, 1, 3);
                    world.func_147465_d(x + a + 1, y + b, z + c, ConfigBlocks.blockAlchemyFurnace, 1, 3);
                    world.func_147465_d(x + a, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 1, 3);
                    world.func_147465_d(x + a, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 1, 3);
                    world.func_147465_d(x + a - 1, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
                    world.func_147465_d(x + a + 1, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
                    world.func_147465_d(x + a + 1, y + b, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
                    world.func_147465_d(x + a - 1, y + b, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 4, 3);
                    world.func_147465_d(x + a - 1, y + b + 1, z + c, ConfigBlocks.blockAlchemyFurnace, 3, 3);
                    world.func_147465_d(x + a + 1, y + b + 1, z + c, ConfigBlocks.blockAlchemyFurnace, 3, 3);
                    world.func_147465_d(x + a, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 3, 3);
                    world.func_147465_d(x + a, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 3, 3);
                    world.func_147465_d(x + a - 1, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
                    world.func_147465_d(x + a + 1, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
                    world.func_147465_d(x + a + 1, y + b + 1, z + c - 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
                    world.func_147465_d(x + a - 1, y + b + 1, z + c + 1, ConfigBlocks.blockAlchemyFurnace, 2, 3);
                    world.func_72908_a((double)(x + a) + 0.5, (double)(y + b) + 0.5, (double)(z + c) + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                    for (int aa = -1; aa <= 1; ++aa) {
                        for (int bb = 0; bb <= 1; ++bb) {
                            for (int cc = -1; cc <= 1; ++cc) {
                                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(x + a + aa, y + b + bb, z + c + cc, -9999), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)(x + a), (double)(y + b), (double)(z + c), 32.0));
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

