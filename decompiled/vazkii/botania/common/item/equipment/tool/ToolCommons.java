/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.elementium.ItemElementiumPick;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public final class ToolCommons {
    public static Material[] materialsPick = new Material[]{Material.field_151576_e, Material.field_151573_f, Material.field_151588_w, Material.field_151592_s, Material.field_76233_E, Material.field_151574_g};
    public static Material[] materialsShovel = new Material[]{Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B};
    public static Material[] materialsAxe = new Material[]{Material.field_151589_v, Material.field_151584_j, Material.field_151585_k, Material.field_151575_d, Material.field_151572_C};

    public static void damageItem(ItemStack stack, int dmg, EntityLivingBase entity, int manaPerDamage) {
        boolean manaRequested;
        int manaToRequest = dmg * manaPerDamage;
        boolean bl = manaRequested = entity instanceof EntityPlayer ? ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)entity, manaToRequest, true) : false;
        if (!manaRequested) {
            stack.func_77972_a(dmg, entity);
        }
    }

    public static void removeBlocksInIteration(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int xs, int ys, int zs, int xe, int ye, int ze, Block block, Material[] materialsListing, boolean silk, int fortune, boolean dispose) {
        float blockHardness = block == null ? 1.0f : block.func_149712_f(world, x, y, z);
        for (int x1 = xs; x1 < xe; ++x1) {
            for (int y1 = ys; y1 < ye; ++y1) {
                for (int z1 = zs; z1 < ze; ++z1) {
                    ToolCommons.removeBlockWithDrops(player, stack, world, x1 + x, y1 + y, z1 + z, x, y, z, block, materialsListing, silk, fortune, blockHardness, dispose);
                }
            }
        }
    }

    public static boolean isRightMaterial(Material material, Material[] materialsListing) {
        for (Material mat : materialsListing) {
            if (material != mat) continue;
            return true;
        }
        return false;
    }

    public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose) {
        ToolCommons.removeBlockWithDrops(player, stack, world, x, y, z, bx, by, bz, block, materialsListing, silk, fortune, blockHardness, dispose, true);
    }

    public static void removeBlockWithDrops(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int bx, int by, int bz, Block block, Material[] materialsListing, boolean silk, int fortune, float blockHardness, boolean dispose, boolean particles) {
        if (!world.func_72899_e(x, y, z)) {
            return;
        }
        Block blk = world.func_147439_a(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        if (block != null && blk != block) {
            return;
        }
        Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!world.field_72995_K && blk != null && !blk.isAir((IBlockAccess)world, x, y, z) && blk.func_149737_a(player, world, x, y, z) > 0.0f) {
            if (!blk.canHarvestBlock(player, meta) || !ToolCommons.isRightMaterial(mat, materialsListing)) {
                return;
            }
            if (!player.field_71075_bZ.field_75098_d) {
                int localMeta = world.func_72805_g(x, y, z);
                blk.func_149681_a(world, x, y, z, localMeta, player);
                if (blk.removedByPlayer(world, player, x, y, z, true)) {
                    blk.func_149664_b(world, x, y, z, localMeta);
                    if (!dispose || !ItemElementiumPick.isDisposable(blk)) {
                        blk.func_149636_a(world, player, x, y, z, localMeta);
                    }
                }
                ToolCommons.damageItem(stack, 1, (EntityLivingBase)player, 80);
            } else {
                world.func_147468_f(x, y, z);
            }
            if (particles && !world.field_72995_K && ConfigHandler.blockBreakParticles && ConfigHandler.blockBreakParticlesTool) {
                world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)blk) + (meta << 12));
            }
        }
    }

    public static int getToolPriority(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        Item item = stack.func_77973_b();
        if (!(item instanceof ItemTool)) {
            return 0;
        }
        ItemTool tool = (ItemTool)item;
        Item.ToolMaterial material = tool.func_150913_i();
        int materialLevel = 0;
        if (material == BotaniaAPI.manasteelToolMaterial) {
            materialLevel = 10;
        }
        if (material == BotaniaAPI.elementiumToolMaterial) {
            materialLevel = 11;
        }
        if (material == BotaniaAPI.terrasteelToolMaterial) {
            materialLevel = 20;
        }
        int modifier = 0;
        if (item == ModItems.terraPick) {
            modifier = ItemTerraPick.getLevel(stack);
        }
        int efficiency = EnchantmentHelper.func_77506_a((int)Enchantment.field_77349_p.field_77352_x, (ItemStack)stack);
        return materialLevel * 100 + modifier * 10 + efficiency;
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
        float f = 1.0f;
        float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
        float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
        double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
        double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f;
        if (!world.field_72995_K && player instanceof EntityPlayer) {
            d1 += (double)((EntityPlayer)player).eyeHeight;
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
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.func_72901_a(vec3, vec31, par3);
    }
}

