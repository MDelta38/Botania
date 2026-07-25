/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseRotatedPillar;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.util.MultiItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWickerBundle
extends BlockBaseRotatedPillar {
    private static final String[] bundleType = new String[]{"plain", "bloodied"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] block_side;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] block_top;

    public BlockWickerBundle() {
        super(Material.field_151575_d, ClassItemBlock.class);
        this.func_149711_c(0.5f);
        this.func_149672_a(field_149779_h);
    }

    @Override
    public Block func_149663_c(String blockName) {
        super.func_149663_c(blockName);
        Blocks.field_150480_ab.setFireInfo((Block)this, 20, 20);
        return this;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset) {
        ItemStack heldItem = player.func_70694_bm();
        if (heldItem != null && heldItem.func_77973_b() == Items.field_151033_d) {
            return BlockWickerBundle.tryIgniteMan(world, x, y, z, player.field_70177_z);
        }
        return false;
    }

    public static boolean tryIgniteMan(World world, int x, int y, int z, float rotationYaw) {
        boolean xleft = world.func_147439_a(x - 1, y, z) == Witchery.Blocks.WICKER_BUNDLE;
        boolean xright = world.func_147439_a(x + 1, y, z) == Witchery.Blocks.WICKER_BUNDLE;
        boolean zleft = world.func_147439_a(x, y, z - 1) == Witchery.Blocks.WICKER_BUNDLE;
        boolean zright = world.func_147439_a(x, y, z + 1) == Witchery.Blocks.WICKER_BUNDLE;
        int dx = x;
        int dy = y;
        int dz = z;
        int fz = 0;
        int fx = 0;
        if ((xleft || xright) && (zleft || zright) || !xleft && !xright && !zleft && !zright) {
            return false;
        }
        if (xleft || xright) {
            if (xleft && !xright) {
                --dx;
            } else if (!xleft) {
                ++dx;
            }
            while (world.func_147439_a(dx, dy - 1, dz) == Witchery.Blocks.WICKER_BUNDLE) {
                --dy;
            }
            while (world.func_147439_a(dx - 1, dy, dz) == Witchery.Blocks.WICKER_BUNDLE) {
                --dx;
            }
            fx = 1;
        } else {
            if (zleft && !zright) {
                --dz;
            } else if (!zleft) {
                ++dz;
            }
            while (world.func_147439_a(dx, dy - 1, dz) == Witchery.Blocks.WICKER_BUNDLE) {
                --dy;
            }
            while (world.func_147439_a(dx, dy, dz - 1) == Witchery.Blocks.WICKER_BUNDLE) {
                --dz;
            }
            fz = 1;
        }
        World w = world;
        if (!BlockWickerBundle.wicker(w, dx, dy + 7, dz) && !BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 7, dz + 1 * fz) && !BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 6, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 6, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 6, dz + 1 * fz) && !BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 6, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 5, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 5, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 5, dz + 1 * fz) && !BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 5, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx - 2 * fx, dy + 4, dz - 2 * fz) && BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 4, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 4, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 4, dz + 1 * fz) && BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 4, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx + 3 * fx, dy + 4, dz + 3 * fz) && !BlockWickerBundle.wicker(w, dx - 2 * fx, dy + 3, dz - 2 * fz) && BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 3, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 3, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 3, dz + 1 * fz) && BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 3, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx + 3 * fx, dy + 3, dz + 3 * fz) && !BlockWickerBundle.wicker(w, dx - 2 * fx, dy + 2, dz - 2 * fz) && BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 2, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 2, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy + 2, dz + 1 * fz) && BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 2, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx + 3 * fx, dy + 2, dz + 3 * fz) && !BlockWickerBundle.wicker(w, dx - 1 * fx, dy + 1, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy + 1, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy, dz + 1 * fz) && !BlockWickerBundle.wicker(w, dx + 2 * fx, dy + 1, dz + 2 * fz) && !BlockWickerBundle.wicker(w, dx - 1 * fx, dy, dz - 1 * fz) && BlockWickerBundle.wicker(w, dx, dy, dz) && BlockWickerBundle.wicker(w, dx + 1 * fx, dy, dz + 1 * fz) && !BlockWickerBundle.wicker(w, dx + 2 * fx, dy, dz + 2 * fz)) {
            world.func_147449_b(dx, dy + 6, dz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 1 * fx, dy + 6, dz + 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx, dy + 3, dz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 1 * fx, dy + 3, dz + 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx, dy + 2, dz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 1 * fx, dy + 2, dz + 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx, dy + 1, dz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 1 * fx, dy + 1, dz + 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx, dy + 0, dz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 1 * fx, dy + 0, dz + 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx - 1 * fx, dy + 4, dz - 1 * fz, (Block)Blocks.field_150480_ab);
            world.func_147449_b(dx + 2 * fx, dy + 4, dz + 2 * fz, (Block)Blocks.field_150480_ab);
            EntityHornedHuntsman entity = new EntityHornedHuntsman(world);
            entity.func_70012_b((double)dx + 1.0 * (double)fx + 0.5 * (double)fz, (double)dy + 0.1, (double)dz + 1.0 * (double)fz + 0.5 * (double)fx, 180.0f + rotationYaw, 0.0f);
            entity.field_70759_as = entity.field_70177_z;
            entity.field_70761_aq = entity.field_70177_z;
            entity.func_110163_bv();
            entity.func_82206_m();
            entity.func_70642_aH();
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)entity);
            }
            for (int j1 = 0; j1 < 120; ++j1) {
                world.func_72869_a("snowballpoof", (double)dx + world.field_73012_v.nextDouble(), (double)(dy - 2) + world.field_73012_v.nextDouble() * 3.9, (double)(dz + 1) + world.field_73012_v.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
        return true;
    }

    private static boolean wicker(World world, int x, int y, int z) {
        return world.func_147439_a(x, y, z) == Witchery.Blocks.WICKER_BUNDLE && BlockWickerBundle.limitToValidMetadata(world.func_72805_g(x, y, z)) == 1;
    }

    public int func_149645_b() {
        return 31;
    }

    public int func_149692_a(int meta) {
        return BlockWickerBundle.limitToValidMetadata(meta);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < bundleType.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int metadata = world.func_72805_g(x, y, z);
        return new ItemStack((Block)this, 1, metadata >= 0 ? BlockWickerBundle.limitToValidMetadata(metadata) : 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected IIcon func_150163_b(int meta) {
        return this.block_side[MathHelper.func_76125_a((int)meta, (int)0, (int)1)];
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon func_150161_d(int meta) {
        return this.block_top[MathHelper.func_76125_a((int)meta, (int)0, (int)1)];
    }

    public static int limitToValidMetadata(int par0) {
        return par0 & bundleType.length - 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.block_side = new IIcon[bundleType.length];
        this.block_top = new IIcon[bundleType.length];
        int i = 0;
        while (true) {
            if (i >= bundleType.length) break;
            this.block_side[i] = iconRegister.func_94245_a(this.func_149641_N() + "_" + bundleType[i] + "_side");
            this.block_top[i] = iconRegister.func_94245_a(this.func_149641_N() + "_" + bundleType[i] + "_top");
            ++i;
        }
    }

    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        boolean flammable = super.isFlammable(world, x, y, z, face);
        return flammable;
    }

    public static class ClassItemBlock
    extends MultiItemBlock {
        public ClassItemBlock(Block block) {
            super(block);
        }

        @Override
        protected String[] getNames() {
            return bundleType;
        }
    }
}

