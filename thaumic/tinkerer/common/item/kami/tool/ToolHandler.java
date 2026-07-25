/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C07PacketPlayerDigging
 *  net.minecraft.network.play.server.S23PacketBlockChange
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package thaumic.tinkerer.common.item.kami.tool;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.kami.BlockBedrockPortal;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.dim.WorldProviderBedrock;
import thaumic.tinkerer.common.item.kami.tool.IAdvancedTool;

public final class ToolHandler {
    public static Material[] materialsPick = new Material[]{Material.field_151576_e, Material.field_151573_f, Material.field_151588_w, Material.field_151592_s, Material.field_76233_E, Material.field_151574_g};
    public static Material[] materialsShovel = new Material[]{Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B};
    public static Material[] materialsAxe = new Material[]{Material.field_151589_v, Material.field_151584_j, Material.field_151585_k, Material.field_151575_d};

    public static int getMode(ItemStack tool) {
        return tool.func_77960_j();
    }

    public static int getNextMode(int mode) {
        return mode == 2 ? 0 : mode + 1;
    }

    public static void changeMode(ItemStack tool) {
        int mode = ToolHandler.getMode(tool);
        tool.func_77964_b(ToolHandler.getNextMode(mode));
    }

    public static boolean isRightMaterial(Material material, Material[] materialsListing) {
        if (material.func_76229_l()) {
            return true;
        }
        for (Material mat : materialsListing) {
            if (material != mat) continue;
            return true;
        }
        return false;
    }

    public static void removeBlocksInIteration(EntityPlayer player, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean silk, int fortune) {
        MovingObjectPosition mop = ToolHandler.raytraceFromEntity(player.field_70170_p, (Entity)player, false, 4.5);
        if (mop == null) {
            return;
        }
        int sideHit = mop.field_72310_e;
        for (int x1 = xs; x1 < xe; ++x1) {
            for (int y1 = ys; y1 < ye; ++y1) {
                for (int z1 = zs; z1 < ze; ++z1) {
                    if (x == x1 + x && y == y1 + y && z == z1 + z) continue;
                    Block lock2 = world.func_147439_a(x1 + x, y1 + y, z1 + z);
                    ToolHandler.breakExtraBlock(player.field_70170_p, x1 + x, y1 + y, z1 + z, player, x, y, z, materialsListing);
                }
            }
        }
        List list = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(x + xs), (double)(y + ys), (double)(z + zs), (double)(x + xe), (double)(y + ye), (double)(z + ze)));
        for (Object entity : list) {
            EntityItem item = (EntityItem)entity;
            item.func_70107_b(player.field_70165_t, player.field_70163_u + 1.0, player.field_70161_v);
        }
    }

    protected static void breakExtraBlock(World world, int x, int y, int z, EntityPlayer playerEntity, int refX, int refY, int refZ, Material[] materialsListing) {
        int meta;
        if (world.func_147437_c(x, y, z)) {
            return;
        }
        if (!(playerEntity instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP player = (EntityPlayerMP)playerEntity;
        Block block = world.func_147439_a(x, y, z);
        if (!block.canHarvestBlock((EntityPlayer)player, meta = world.func_72805_g(x, y, z)) || !ToolHandler.isRightMaterial(block.func_149688_o(), materialsListing)) {
            return;
        }
        Block refBlock = world.func_147439_a(refX, refY, refZ);
        float refStrength = ForgeHooks.blockStrength((Block)refBlock, (EntityPlayer)player, (World)world, (int)refX, (int)refY, (int)refZ);
        float strength = ForgeHooks.blockStrength((Block)block, (EntityPlayer)player, (World)world, (int)x, (int)y, (int)z);
        if (!ForgeHooks.canHarvestBlock((Block)block, (EntityPlayer)player, (int)meta) || refStrength / strength > 10.0f) {
            return;
        }
        BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent((World)world, (WorldSettings.GameType)player.field_71134_c.func_73081_b(), (EntityPlayerMP)player, (int)x, (int)y, (int)z);
        if (event.isCanceled()) {
            return;
        }
        if (player.field_71075_bZ.field_75098_d) {
            block.func_149681_a(world, x, y, z, meta, (EntityPlayer)player);
            if (block.removedByPlayer(world, (EntityPlayer)player, x, y, z, false)) {
                block.func_149664_b(world, x, y, z, meta);
            }
            if (!world.field_72995_K) {
                player.field_71135_a.func_147359_a((Packet)new S23PacketBlockChange(x, y, z, world));
            }
            return;
        }
        player.func_71045_bC().func_150999_a(world, block, x, y, z, (EntityPlayer)player);
        if (!world.field_72995_K) {
            block.func_149681_a(world, x, y, z, meta, (EntityPlayer)player);
            if (block.removedByPlayer(world, (EntityPlayer)player, x, y, z, true)) {
                block.func_149664_b(world, x, y, z, meta);
                block.func_149636_a(world, (EntityPlayer)player, x, y, z, meta);
                block.func_149657_c(world, x, y, z, event.getExpToDrop());
            }
            player.field_71135_a.func_147359_a((Packet)new S23PacketBlockChange(x, y, z, world));
        } else {
            ItemStack itemstack;
            world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (meta << 12));
            if (block.removedByPlayer(world, (EntityPlayer)player, x, y, z, true)) {
                block.func_149664_b(world, x, y, z, meta);
            }
            if ((itemstack = player.func_71045_bC()) != null) {
                itemstack.func_150999_a(world, block, x, y, z, (EntityPlayer)player);
                if (itemstack.field_77994_a == 0) {
                    player.func_71028_bD();
                }
            }
            Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)new C07PacketPlayerDigging(2, x, y, z, Minecraft.func_71410_x().field_71476_x.field_72310_e));
        }
    }

    public static void removeBlockWithDrops(EntityPlayer player, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, int metadata) {
        if (!world.func_72899_e(x, y, z)) {
            return;
        }
        Block blk = world.func_147439_a(x, y, z);
        if (block != null && blk != block) {
            return;
        }
        int meta = world.func_72805_g(x, y, z);
        Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (blk != null && !blk.isAir((IBlockAccess)world, x, y, z) && (blk.func_149737_a(player, world, x, y, z) != 0.0f || blk == Blocks.field_150357_h && y <= 253 && world.field_73011_w instanceof WorldProviderBedrock)) {
            if (!blk.canHarvestBlock(player, meta) || !ToolHandler.isRightMaterial(mat, materialsListing)) {
                return;
            }
            if (ConfigHandler.bedrockDimensionID != 0 && block == Blocks.field_150357_h && (world.field_73011_w.func_76569_d() && y < 5 || y > 253 && world.field_73011_w instanceof WorldProviderBedrock)) {
                world.func_147449_b(x, y, z, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockBedrockPortal.class));
            }
            if (ConfigHandler.bedrockDimensionID != 0 && world.field_73011_w.field_76574_g == ConfigHandler.bedrockDimensionID && blk == Blocks.field_150357_h && y <= 253) {
                world.func_147449_b(x, y, z, Blocks.field_150350_a);
            }
            if (player.field_71075_bZ.field_75098_d || blk == Blocks.field_150357_h) {
                world.func_147468_f(x, y, z);
            }
        }
    }

    public static String getToolModeStr(IAdvancedTool tool, ItemStack stack) {
        return StatCollector.func_74838_a((String)("ttmisc.mode." + tool.getType() + "." + ToolHandler.getMode(stack)));
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
        float f = 1.0f;
        float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
        float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
        double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
        double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f;
        if (!world.field_72995_K && player instanceof EntityPlayer) {
            d1 += 1.62;
        }
        double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)player).field_71134_c.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.func_72901_a(vec3, vec31, par3);
    }
}

