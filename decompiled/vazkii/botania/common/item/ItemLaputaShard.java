/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILaputaImmobile;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.ITinyPlanetExcempt;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ItemMod;

public class ItemLaputaShard
extends ItemMod
implements ILensEffect,
ITinyPlanetExcempt {
    private static final String TAG_BLOCK = "_block";
    private static final String TAG_META = "_meta";
    private static final String TAG_TILE = "_tile";
    private static final String TAG_X = "_x";
    private static final String TAG_Y = "_y";
    private static final String TAG_Y_START = "_yStart";
    private static final String TAG_Z = "_z";
    private static final String TAG_POINTY = "_pointy";
    private static final String TAG_HEIGHTSCALE = "_heightscale";
    private static final String TAG_ITERATION_I = "iterationI";
    private static final String TAG_ITERATION_J = "iterationJ";
    private static final String TAG_ITERATION_K = "iterationK";
    private static final int BASE_RANGE = 14;
    private static final int BASE_OFFSET = 42;

    public ItemLaputaShard() {
        this.func_77655_b("laputaShard");
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        super.func_150895_a(item, tab, list);
        for (int i = 0; i < 4; ++i) {
            list.add(new ItemStack(item, 1, (i + 1) * 5 - 1));
        }
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        list.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.shardLevel"), StatCollector.func_74838_a((String)("botania.roman" + (stack.func_77960_j() + 1)))));
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par5 < 160 && !par3World.field_73011_w.field_76575_d) {
            par3World.func_72980_b((double)par4 + 0.5, (double)par5 + 0.5, (double)par6 + 0.5, "botania:laputaStart", 1.0f + par3World.field_73012_v.nextFloat(), par3World.field_73012_v.nextFloat() * 0.7f + 1.3f, false);
            this.spawnBurstFirst(par3World, par4, par5, par6, par1ItemStack);
            --par1ItemStack.field_77994_a;
            if (par1ItemStack.func_77960_j() == 19) {
                par2EntityPlayer.func_71064_a((StatBase)ModAchievements.l20ShardUse, 1);
            }
        }
        return true;
    }

    public void spawnBurstFirst(World world, int srcx, int srcy, int srcz, ItemStack lens) {
        int range = 14 + lens.func_77960_j();
        boolean pointy = world.field_73012_v.nextDouble() < 0.25;
        double heightscale = (world.field_73012_v.nextDouble() + 0.5) * (14.0 / (double)range);
        this.spawnBurst(world, srcx, srcy, srcz, lens, pointy, heightscale);
    }

    public void spawnBurst(World world, int srcx, int srcy, int srcz, ItemStack lens) {
        boolean pointy = ItemNBTHelper.getBoolean(lens, TAG_POINTY, false);
        double heightscale = ItemNBTHelper.getDouble(lens, TAG_HEIGHTSCALE, 1.0);
        this.spawnBurst(world, srcx, srcy, srcz, lens, pointy, heightscale);
    }

    public void spawnBurst(World world, int srcx, int srcy, int srcz, ItemStack lens, boolean pointy, double heightscale) {
        int range = 14 + lens.func_77960_j();
        int i = ItemNBTHelper.getInt(lens, TAG_ITERATION_I, 0);
        int j = ItemNBTHelper.getInt(lens, TAG_ITERATION_J, 35);
        int k = ItemNBTHelper.getInt(lens, TAG_ITERATION_K, 0);
        if (j <= -28) {
            j = 35;
        }
        if (k >= range * 2 + 1) {
            k = 0;
        }
        if (!world.field_72995_K) {
            while (i < range * 2 + 1) {
                while (j > -28) {
                    while (k < range * 2 + 1) {
                        Block block;
                        int x = srcx - range + i;
                        int y = srcy - 14 + j;
                        int z = srcz - range + k;
                        if (!(!this.inRange(x, y, z, srcx, srcy, srcz, range, heightscale, pointy) || (block = world.func_147439_a(x, y, z)).isAir((IBlockAccess)world, x, y, z) || block.isReplaceable((IBlockAccess)world, x, y, z) || block instanceof BlockFalling || block instanceof ILaputaImmobile && !((ILaputaImmobile)block).canMove(world, x, y, z) || block.func_149712_f(world, x, y, z) == -1.0f)) {
                            int id = Block.func_149682_b((Block)block);
                            int meta = world.func_72805_g(x, y, z);
                            TileEntity tile = world.func_147438_o(x, y, z);
                            if (tile != null) {
                                TileEntity newTile = block.createTileEntity(world, meta);
                                world.func_147455_a(x, y, z, newTile);
                            }
                            world.func_147468_f(x, y, z);
                            world.func_72926_e(2001, x, y, z, id + (meta << 12));
                            ItemStack copyLens = new ItemStack((Item)this, 1, lens.func_77960_j());
                            ItemNBTHelper.setInt(copyLens, TAG_BLOCK, id);
                            ItemNBTHelper.setInt(copyLens, TAG_META, meta);
                            NBTTagCompound cmp = new NBTTagCompound();
                            if (tile != null) {
                                tile.func_145841_b(cmp);
                            }
                            ItemNBTHelper.setCompound(copyLens, TAG_TILE, cmp);
                            ItemNBTHelper.setInt(copyLens, TAG_X, srcx);
                            ItemNBTHelper.setInt(copyLens, TAG_Y, srcy);
                            ItemNBTHelper.setInt(copyLens, TAG_Y_START, y);
                            ItemNBTHelper.setInt(copyLens, TAG_Z, srcz);
                            ItemNBTHelper.setBoolean(copyLens, TAG_POINTY, pointy);
                            ItemNBTHelper.setDouble(copyLens, TAG_HEIGHTSCALE, heightscale);
                            ItemNBTHelper.setInt(copyLens, TAG_ITERATION_I, i);
                            ItemNBTHelper.setInt(copyLens, TAG_ITERATION_J, j);
                            ItemNBTHelper.setInt(copyLens, TAG_ITERATION_K, k);
                            EntityManaBurst burst = this.getBurst(world, x, y, z, copyLens);
                            world.func_72838_d((Entity)burst);
                            return;
                        }
                        ++k;
                    }
                    k = 0;
                    --j;
                }
                j = 35;
                ++i;
            }
        }
    }

    private boolean inRange(int x, int y, int z, int srcx, int srcy, int srcz, int range, double heightscale, boolean pointy) {
        if (y >= srcy) {
            return vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(x, 0.0, z, srcx, 0.0, srcz) < (float)range;
        }
        if (!pointy) {
            return vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(x, (double)y / heightscale, z, srcx, (double)srcy / heightscale, srcz) < (float)range;
        }
        return (double)vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(x, 0.0, z, srcx, 0.0, srcz) < (double)range - (double)(srcy - y) / heightscale;
    }

    public EntityManaBurst getBurst(World world, int x, int y, int z, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(world);
        burst.field_70165_t = (double)x + 0.5;
        burst.field_70163_u = (double)y + 0.5;
        burst.field_70161_v = (double)z + 0.5;
        burst.setColor(60159);
        burst.setMana(1);
        burst.setStartingMana(1);
        burst.setMinManaLoss(0);
        burst.setManaLossPerTick(0.0f);
        burst.setGravity(0.0f);
        burst.setMotion(0.0, 0.5, 0.0);
        burst.setSourceLens(stack);
        return burst;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
    }

    @Override
    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return false;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        double speed = 0.35;
        int targetDistance = 42;
        EntityThrowable entity = (EntityThrowable)burst;
        if (!entity.field_70170_p.field_72995_K) {
            entity.field_70159_w = 0.0;
            entity.field_70181_x = speed;
            entity.field_70179_y = 0.0;
            int spawnTicks = 2;
            int placeTicks = MathHelper.func_76128_c((double)((double)targetDistance / speed));
            ItemStack lens = burst.getSourceLens();
            if (burst.getTicksExisted() == 2) {
                int x = ItemNBTHelper.getInt(lens, TAG_X, 0);
                int y = ItemNBTHelper.getInt(lens, TAG_Y, -1);
                int z = ItemNBTHelper.getInt(lens, TAG_Z, 0);
                if (y != -1) {
                    this.spawnBurst(entity.field_70170_p, x, y, z, lens);
                }
            } else if (burst.getTicksExisted() == placeTicks) {
                int z;
                int y;
                int x = MathHelper.func_76128_c((double)entity.field_70165_t);
                if (entity.field_70170_p.func_147437_c(x, y = ItemNBTHelper.getInt(lens, TAG_Y_START, -1) + targetDistance, z = MathHelper.func_76128_c((double)entity.field_70161_v))) {
                    int id = ItemNBTHelper.getInt(lens, TAG_BLOCK, 0);
                    Block block = Block.func_149729_e((int)id);
                    int meta = ItemNBTHelper.getInt(lens, TAG_META, 0);
                    TileEntity tile = null;
                    NBTTagCompound tilecmp = ItemNBTHelper.getCompound(lens, TAG_TILE, false);
                    if (tilecmp.func_74764_b("id")) {
                        tile = TileEntity.func_145827_c((NBTTagCompound)tilecmp);
                    }
                    entity.field_70170_p.func_147465_d(x, y, z, block, meta, 3);
                    entity.field_70170_p.func_72926_e(2001, x, y, z, id + (meta << 12));
                    if (tile != null) {
                        tile.field_145851_c = x;
                        tile.field_145848_d = y;
                        tile.field_145849_e = z;
                        entity.field_70170_p.func_147455_a(x, y, z, tile);
                    }
                }
                entity.func_70106_y();
            }
        }
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable)burst;
        ItemStack lens = burst.getSourceLens();
        int id = ItemNBTHelper.getInt(lens, TAG_BLOCK, 0);
        Block.func_149729_e((int)id);
        int meta = ItemNBTHelper.getInt(lens, TAG_META, 0);
        entity.field_70170_p.func_72869_a("blockcrack_" + id + "_" + meta, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70159_w, entity.field_70181_x, entity.field_70179_y);
        return true;
    }

    @Override
    public boolean shouldPull(ItemStack stack) {
        return false;
    }
}

