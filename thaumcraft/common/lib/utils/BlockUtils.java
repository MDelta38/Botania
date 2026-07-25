/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S25PacketBlockBreakAnim
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.ForgeEventFactory
 */
package thaumcraft.common.lib.utils;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;

public class BlockUtils {
    static HashMap<Integer, ArrayList[]> blockEventCache = new HashMap();
    static int lastx = 0;
    static int lasty = 0;
    static int lastz = 0;
    static double lastdistance = 0.0;

    public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z) {
        return BlockUtils.harvestBlock(world, player, x, y, z, false, 0);
    }

    public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z, boolean followItem, int color) {
        Block block = world.func_147439_a(x, y, z);
        int i1 = world.func_72805_g(x, y, z);
        if (block.func_149712_f(world, x, y, z) < 0.0f) {
            return false;
        }
        world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (i1 << 12));
        boolean flag = false;
        if (player.field_71075_bZ.field_75098_d) {
            flag = BlockUtils.removeBlock(world, x, y, z, player);
        } else {
            boolean flag1 = false;
            if (block != null) {
                flag1 = block.canHarvestBlock(player, i1);
            }
            if ((flag = BlockUtils.removeBlock(world, x, y, z, player)) && flag1) {
                ArrayList<Entity> entities;
                block.func_149636_a(world, player, x, y, z, i1);
                if (followItem && (entities = EntityUtils.getEntitiesInRange(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, (Entity)player, EntityItem.class, 2.0)) != null && entities.size() > 0) {
                    for (Entity e : entities) {
                        if (e.field_70128_L || !(e instanceof EntityItem) || e.field_70173_aa != 0 || e instanceof EntityFollowingItem) continue;
                        EntityFollowingItem fi = new EntityFollowingItem(world, e.field_70165_t, e.field_70163_u, e.field_70161_v, ((EntityItem)e).func_92059_d().func_77946_l(), (Entity)player, color);
                        fi.field_70159_w = e.field_70159_w;
                        fi.field_70181_x = e.field_70181_x;
                        fi.field_70179_y = e.field_70179_y;
                        world.func_72838_d((Entity)fi);
                        e.func_70106_y();
                    }
                }
            }
        }
        return true;
    }

    public static ArrayList[] getBlockEventList(WorldServer world) {
        if (!blockEventCache.containsKey(world.field_73011_w.field_76574_g)) {
            try {
                blockEventCache.put(world.field_73011_w.field_76574_g, (ArrayList[])ReflectionHelper.getPrivateValue(WorldServer.class, (Object)world, (String[])new String[]{"field_147490_S"}));
            }
            catch (Exception e) {
                return null;
            }
        }
        return blockEventCache.get(world.field_73011_w.field_76574_g);
    }

    public static ItemStack createStackedBlock(Block block, int md) {
        ItemStack dropped = null;
        try {
            Method m = ReflectionHelper.findMethod(Block.class, (Object)block, (String[])new String[]{"createStackedBlock", "func_149644_j"}, (Class[])new Class[]{Integer.TYPE});
            dropped = (ItemStack)m.invoke(block, md);
        }
        catch (Exception e) {
            Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
        }
        return dropped;
    }

    public static void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack, Block block) {
        try {
            Method m = ReflectionHelper.findMethod(Block.class, (Object)block, (String[])new String[]{"dropBlockAsItem", "func_149642_a"}, (Class[])new Class[]{World.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class});
            m.invoke(block, world, x, y, z, stack);
        }
        catch (Exception e) {
            Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
        }
    }

    public static void dropBlockAsItemWithChance(World world, Block block, int x, int y, int z, int meta, float dropchance, int fortune, EntityPlayer player) {
        if (!world.field_72995_K && !world.restoringBlockSnapshots) {
            ArrayList items = block.getDrops(world, x, y, z, meta, fortune);
            dropchance = ForgeEventFactory.fireBlockHarvesting((ArrayList)items, (World)world, (Block)block, (int)x, (int)y, (int)z, (int)meta, (int)fortune, (float)dropchance, (boolean)false, (EntityPlayer)player);
            for (ItemStack item : items) {
                if (!(world.field_73012_v.nextFloat() <= dropchance)) continue;
                BlockUtils.dropBlockAsItem(world, x, y, z, item, block);
            }
        }
    }

    public static void destroyBlockPartially(World world, int par1, int par2, int par3, int par4, int par5) {
        for (EntityPlayerMP entityplayermp : MinecraftServer.func_71276_C().func_71203_ab().field_72404_b) {
            double d2;
            double d1;
            double d0;
            if (entityplayermp == null || entityplayermp.field_70170_p != MinecraftServer.func_71276_C().func_130014_f_() || entityplayermp.func_145782_y() == par1 || !((d0 = (double)par2 - entityplayermp.field_70165_t) * d0 + (d1 = (double)par3 - entityplayermp.field_70163_u) * d1 + (d2 = (double)par4 - entityplayermp.field_70161_v) * d2 < 1024.0)) continue;
            entityplayermp.field_71135_a.func_147359_a((Packet)new S25PacketBlockBreakAnim(par1, par2, par3, par4, par5));
        }
    }

    public static boolean removeBlock(World world, int par1, int par2, int par3, EntityPlayer player) {
        boolean flag;
        Block block = world.func_147439_a(par1, par2, par3);
        int l = world.func_72805_g(par1, par2, par3);
        if (block != null) {
            block.func_149681_a(world, par1, par2, par3, l, player);
        }
        boolean bl = flag = block != null && block.removedByPlayer(world, player, par1, par2, par3);
        if (block != null && flag) {
            block.func_149664_b(world, par1, par2, par3, l);
        }
        return flag;
    }

    public static void findBlocks(World world, int x, int y, int z, Block block) {
        boolean count = false;
        for (int xx = -2; xx <= 2; ++xx) {
            for (int yy = 2; yy >= -2; --yy) {
                for (int zz = -2; zz <= 2; ++zz) {
                    double zd;
                    double yd;
                    double xd;
                    double d;
                    if (Math.abs(lastx + xx - x) > 24) {
                        return;
                    }
                    if (Math.abs(lasty + yy - y) > 48) {
                        return;
                    }
                    if (Math.abs(lastz + zz - z) > 24) {
                        return;
                    }
                    if (world.func_147439_a(lastx + xx, lasty + yy, lastz + zz) != block || !Utils.isWoodLog((IBlockAccess)world, lastx + xx, lasty + yy, lastz + zz) || !(block.func_149712_f(world, lastx + xx, lasty + yy, lastz + zz) >= 0.0f) || !((d = (xd = (double)(lastx + xx - x)) * xd + (yd = (double)(lasty + yy - y)) * yd + (zd = (double)(lastz + zz - z)) * zd) > lastdistance)) continue;
                    lastdistance = d;
                    lastx += xx;
                    lasty += yy;
                    lastz += zz;
                    BlockUtils.findBlocks(world, x, y, z, block);
                    return;
                }
            }
        }
    }

    public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player) {
        return BlockUtils.breakFurthestBlock(world, x, y, z, block, player, false, 0);
    }

    public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player, boolean followitem, int color) {
        lastx = x;
        lasty = y;
        lastz = z;
        lastdistance = 0.0;
        BlockUtils.findBlocks(world, x, y, z, block);
        boolean worked = BlockUtils.harvestBlock(world, player, lastx, lasty, lastz, followitem, color);
        world.func_147471_g(x, y, z);
        if (worked) {
            world.func_147471_g(lastx, lasty, lastz);
            for (int xx = -3; xx <= 3; ++xx) {
                for (int yy = -3; yy <= 3; ++yy) {
                    for (int zz = -3; zz <= 3; ++zz) {
                        world.func_147464_a(lastx + xx, lasty + yy, lastz + zz, world.func_147439_a(lastx + xx, lasty + yy, lastz + zz), 150 + world.field_73012_v.nextInt(150));
                    }
                }
            }
        }
        return worked;
    }

    public static MovingObjectPosition getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean par3, double range) {
        Vec3 var13 = Vec3.func_72443_a((double)x, (double)y, (double)z);
        float var14 = MathHelper.func_76134_b((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float var15 = MathHelper.func_76126_a((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI));
        float var16 = -MathHelper.func_76134_b((float)(-pitch * ((float)Math.PI / 180)));
        float var17 = MathHelper.func_76126_a((float)(-pitch * ((float)Math.PI / 180)));
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = range;
        Vec3 var23 = var13.func_72441_c((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
        return world.func_147447_a(var13, var23, par3, !par3, false);
    }

    public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3) {
        float var4 = 1.0f;
        float var5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * var4;
        float var6 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * var4;
        double var7 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)var4;
        double var9 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)var4 + 1.62 - (double)entity.field_70129_M;
        double var11 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)var4;
        Vec3 var13 = Vec3.func_72443_a((double)var7, (double)var9, (double)var11);
        float var14 = MathHelper.func_76134_b((float)(-var6 * ((float)Math.PI / 180) - (float)Math.PI));
        float var15 = MathHelper.func_76126_a((float)(-var6 * ((float)Math.PI / 180) - (float)Math.PI));
        float var16 = -MathHelper.func_76134_b((float)(-var5 * ((float)Math.PI / 180)));
        float var17 = MathHelper.func_76126_a((float)(-var5 * ((float)Math.PI / 180)));
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 10.0;
        Vec3 var23 = var13.func_72441_c((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
        return world.func_147447_a(var13, var23, par3, !par3, false);
    }

    public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount) {
        return BlockUtils.isBlockAdjacentToAtleast(world, x, y, z, id, md, amount, 1);
    }

    public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount, int range) {
        int count = 0;
        for (int xx = -range; xx <= range; ++xx) {
            for (int yy = -range; yy <= range; ++yy) {
                for (int zz = -range; zz <= range; ++zz) {
                    if (xx == 0 && yy == 0 && zz == 0) continue;
                    if (world.func_147439_a(x + xx, y + yy, z + zz) == id && (md == Short.MAX_VALUE || world.func_72805_g(x + xx, y + yy, z + zz) == md)) {
                        ++count;
                    }
                    if (count < amount) continue;
                    return true;
                }
            }
        }
        return count >= amount;
    }

    public static List<EntityItem> getContentsOfBlock(World world, int x, int y, int z) {
        List list = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)((double)x + 1.0), (double)((double)y + 1.0), (double)((double)z + 1.0)));
        return list;
    }

    public static int countExposedSides(World world, int x, int y, int z) {
        int count = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (!world.func_147437_c(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) continue;
            ++count;
        }
        return count;
    }

    public static boolean isBlockExposed(World world, int x, int y, int z) {
        return !world.func_147439_a(x, y, z + 1).func_149662_c() || !world.func_147439_a(x, y, z - 1).func_149662_c() || !world.func_147439_a(x + 1, y, z).func_149662_c() || !world.func_147439_a(x - 1, y, z).func_149662_c() || !world.func_147439_a(x, y + 1, z).func_149662_c() || !world.func_147439_a(x, y - 1, z).func_149662_c();
    }

    public static boolean isAdjacentToSolidBlock(World world, int x, int y, int z) {
        for (int a = 0; a < 6; ++a) {
            ForgeDirection d = ForgeDirection.getOrientation((int)a);
            if (!world.isSideSolid(x + d.offsetX, y + d.offsetY, z + d.offsetZ, d.getOpposite())) continue;
            return true;
        }
        return false;
    }

    public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id) {
        return world.func_147439_a(x, y, z + 1) == id || world.func_147439_a(x, y, z - 1) == id || world.func_147439_a(x + 1, y, z) == id || world.func_147439_a(x - 1, y, z) == id || world.func_147439_a(x, y + 1, z) == id || world.func_147439_a(x, y - 1, z) == id;
    }

    public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id, int md) {
        return world.func_147439_a(x, y, z + 1) == id && world.func_72805_g(x, y, z + 1) == md || world.func_147439_a(x, y, z - 1) == id && world.func_72805_g(x, y, z - 1) == md || world.func_147439_a(x + 1, y, z) == id && world.func_72805_g(x + 1, y, z) == md || world.func_147439_a(x - 1, y, z) == id && world.func_72805_g(x - 1, y, z) == md || world.func_147439_a(x, y + 1, z) == id && world.func_72805_g(x, y + 1, z) == md || world.func_147439_a(x, y - 1, z) == id && world.func_72805_g(x, y - 1, z) == md;
    }

    public static boolean isBlockTouchingOnSide(IBlockAccess world, int x, int y, int z, Block id, int md, int side) {
        if (side > 3 && world.func_147439_a(x, y, z + 1) == id && world.func_72805_g(x, y, z + 1) == md || side > 3 && world.func_147439_a(x, y, z - 1) == id && world.func_72805_g(x, y, z - 1) == md || side > 1 && side < 4 && world.func_147439_a(x + 1, y, z) == id && world.func_72805_g(x + 1, y, z) == md || side > 1 && side < 4 && world.func_147439_a(x - 1, y, z) == id && world.func_72805_g(x - 1, y, z) == md || side > 1 && world.func_147439_a(x, y + 1, z) == id && world.func_72805_g(x, y + 1, z) == md || side > 1 && world.func_147439_a(x, y - 1, z) == id && world.func_72805_g(x, y - 1, z) == md) {
            return true;
        }
        if (side > 3 && world.func_147439_a(x, y + 1, z + 1) == id && world.func_72805_g(x, y + 1, z + 1) == md || side > 3 && world.func_147439_a(x, y + 1, z - 1) == id && world.func_72805_g(x, y + 1, z - 1) == md || side > 1 && side < 4 && world.func_147439_a(x + 1, y + 1, z) == id && world.func_72805_g(x + 1, y + 1, z) == md || side > 1 && side < 4 && world.func_147439_a(x - 1, y + 1, z) == id && world.func_72805_g(x - 1, y + 1, z) == md) {
            return true;
        }
        if (side > 3 && world.func_147439_a(x, y - 1, z + 1) == id && world.func_72805_g(x, y - 1, z + 1) == md || side > 3 && world.func_147439_a(x, y - 1, z - 1) == id && world.func_72805_g(x, y - 1, z - 1) == md || side > 1 && side < 4 && world.func_147439_a(x + 1, y - 1, z) == id && world.func_72805_g(x + 1, y - 1, z) == md || side > 1 && side < 4 && world.func_147439_a(x - 1, y - 1, z) == id && world.func_72805_g(x - 1, y - 1, z) == md) {
            return true;
        }
        switch (side) {
            case 0: {
                if (world.func_147439_a(x, y - 1, z) != id || world.func_72805_g(x, y - 1, z) != md) break;
                return true;
            }
            case 1: {
                if (world.func_147439_a(x, y + 1, z) != id || world.func_72805_g(x, y + 1, z) != md) break;
                return true;
            }
        }
        return false;
    }
}

