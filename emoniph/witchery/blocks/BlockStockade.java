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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBase;
import com.emoniph.witchery.util.MultiItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStockade
extends BlockBase {
    public static final String[] WOOD_TEXTURES = new String[]{"log_oak", "log_spruce", "log_birch", "log_jungle", "witchery:log_rowan", "witchery:log_alder", "witchery:log_hawthorn", "log_acacia", "log_big_oak"};
    public static final String[] WOOD_NAMES = new String[]{"oak", "spruce", "birch", "jungle", "rowan", "alder", "hawthorn", "acacia", "big_oak"};
    public static final String[] ICE_TEXTURES = new String[]{"ice"};
    public static final String[] ICE_NAMES = new String[]{"ice"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tree;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tree_top;
    private final boolean alpha;
    private boolean tipTexturing;

    public int func_149692_a(int metadata) {
        if (metadata < 0 || metadata >= (this.alpha ? ICE_NAMES.length : WOOD_NAMES.length)) {
            metadata = 0;
        }
        return metadata;
    }

    public BlockStockade(boolean alpha) {
        super(alpha ? Material.field_151588_w : Material.field_151575_d, ClassItemBlock.class);
        this.func_149711_c(25.0f);
        this.func_149752_b(20.0f);
        this.alpha = alpha;
    }

    public int func_149645_b() {
        return Witchery.proxy.getStockageRenderId();
    }

    public void func_149724_b(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase)entity;
            living.func_70097_a(DamageSource.field_76367_g, 3.0f);
        }
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
        boolean connectN = this.canConnectFenceTo((IBlockAccess)world, x, y, z - 1);
        boolean connectS = this.canConnectFenceTo((IBlockAccess)world, x, y, z + 1);
        boolean connectW = this.canConnectFenceTo((IBlockAccess)world, x - 1, y, z);
        boolean connectE = this.canConnectFenceTo((IBlockAccess)world, x + 1, y, z);
        float f = 0.375f;
        float f1 = 0.625f;
        float f2 = 0.375f;
        float f3 = 0.625f;
        if (connectN) {
            f2 = 0.0f;
        }
        if (connectS) {
            f3 = 1.0f;
        }
        if (!(connectN || connectS || connectE || connectW)) {
            this.func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 0.9f, 0.7f);
            super.func_149743_a(world, x, y, z, bb, list, entity);
        }
        if (connectN || connectS) {
            this.func_149676_a(0.3f, 0.0f, 0.05f, 0.7f, connectE || connectW ? 1.0f : 0.9f, 0.95f);
            super.func_149743_a(world, x, y, z, bb, list, entity);
        }
        if (connectE || connectW) {
            this.func_149676_a(0.05f, 0.0f, 0.3f, 0.55f, connectN || connectS ? 1.0f : 0.9f, 0.7f);
            super.func_149743_a(world, x, y, z, bb, list, entity);
        }
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        boolean connectN = this.canConnectFenceTo(world, x, y, z - 1);
        boolean connectS = this.canConnectFenceTo(world, x, y, z + 1);
        boolean connectW = this.canConnectFenceTo(world, x - 1, y, z);
        boolean connectE = this.canConnectFenceTo(world, x + 1, y, z);
        float f = 0.3f;
        float f1 = 0.3f;
        float f2 = 0.7f;
        float f3 = 0.7f;
        if (connectN || connectS) {
            f1 = 0.05f;
            f3 = 0.95f;
        }
        if (connectE || connectW) {
            f = 0.05f;
            f2 = 0.95f;
        }
        this.func_149676_a(f, 0.0f, f1, f2, !(!connectN && !connectS || !connectW && !connectE) ? 1.0f : 0.9f, f3);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149701_w() {
        return this.alpha ? 1 : super.func_149701_w();
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
        Block block = p_149826_1_.func_147439_a(p_149826_2_, p_149826_3_, p_149826_4_);
        return block == this || block == Blocks.field_150396_be || block == Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        if (world.func_147439_a(x, y, z) == this) {
            if (side == 1) {
                boolean sideZ;
                boolean aboveX = world.func_147439_a(x + 1, y, z) == this || world.func_147439_a(x - 1, y, z) == this;
                boolean aboveZ = world.func_147439_a(x, y, z + 1) == this || world.func_147439_a(x, y, z - 1) == this;
                boolean sideX = world.func_147439_a(x + 1, y - 1, z) == this || world.func_147439_a(x - 1, y - 1, z) == this;
                boolean bl = sideZ = world.func_147439_a(x, y - 1, z + 1) == this || world.func_147439_a(x, y - 1, z - 1) == this;
                if (aboveX && sideX && aboveZ && sideZ) {
                    return false;
                }
                if (sideX && !aboveX) {
                    return true;
                }
                return sideZ && !aboveZ;
            }
            if (side == 0) {
                boolean aboveZ;
                boolean sideX = world.func_147439_a(x + 1, y, z) == this || world.func_147439_a(x - 1, y, z) == this;
                boolean sideZ = world.func_147439_a(x, y, z + 1) == this || world.func_147439_a(x, y, z - 1) == this;
                boolean aboveX = world.func_147439_a(x + 1, y + 1, z) == this || world.func_147439_a(x - 1, y + 1, z) == this;
                boolean bl = aboveZ = world.func_147439_a(x, y + 1, z + 1) == this || world.func_147439_a(x, y + 1, z - 1) == this;
                if (aboveX && sideX && aboveZ && sideZ) {
                    return false;
                }
                if (sideX && !aboveX) {
                    return true;
                }
                return sideZ && !aboveZ;
            }
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta < 0 || meta >= (this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length)) {
            meta = 0;
        }
        return side == 1 || side == 0 || this.tipTexturing ? this.tree_top[meta] : this.tree[meta];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < (this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length); ++i) {
            list.add(new ItemStack((Block)this, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.tree = new IIcon[this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
        this.tree_top = new IIcon[this.alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
        if (this.alpha) {
            for (int i = 0; i < this.tree.length; ++i) {
                this.tree[i] = iconRegister.func_94245_a(ICE_TEXTURES[i]);
                this.tree_top[i] = iconRegister.func_94245_a(ICE_TEXTURES[i] + (ICE_TEXTURES[i].equals("ice") ? "" : "_top"));
            }
        } else {
            for (int i = 0; i < this.tree.length; ++i) {
                this.tree[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i]);
                this.tree_top[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i] + "_top");
            }
        }
    }

    public void setTipTexture(boolean b) {
        this.tipTexturing = b;
    }

    public static class ClassItemBlock
    extends MultiItemBlock {
        public ClassItemBlock(Block block) {
            super(block);
        }

        @Override
        protected String[] getNames() {
            return ((BlockStockade)this.field_150939_a).alpha ? ICE_NAMES : WOOD_NAMES;
        }
    }
}

