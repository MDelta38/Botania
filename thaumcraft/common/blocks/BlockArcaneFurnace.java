/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.tiles.TileArcaneFurnace;
import thaumcraft.common.tiles.TileArcaneFurnaceNozzle;

public class BlockArcaneFurnace
extends BlockContainer {
    public IIcon[] icon = new IIcon[27];

    public BlockArcaneFurnace() {
        super(Material.field_151576_e);
        this.func_149711_c(10.0f);
        this.func_149752_b(500.0f);
        this.func_149715_a(0.2f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public void func_149651_a(IIconRegister ir) {
        for (int a = 0; a < 27; ++a) {
            if (a == 8 || a == 24) continue;
            this.icon[a] = ir.func_94245_a("thaumcraft:furnace" + a);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return this.calculateTexture(world, x, y, z, side);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon calculateTexture(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        int level = this.calculateLevel(world, x, y, z);
        int add = 0;
        if (BlockUtils.isBlockTouchingOnSide(world, x, y, z, (Block)this, 10, side)) {
            add = 3;
        }
        switch (side) {
            case 0: 
            case 1: {
                if (side == 1 && level == 18) {
                    switch (meta) {
                        case 2: {
                            return this.icon[16];
                        }
                        case 4: {
                            return this.icon[17];
                        }
                        case 6: {
                            return this.icon[26];
                        }
                        case 8: {
                            return this.icon[25];
                        }
                    }
                }
                if (add == 3) break;
                if (meta == 5) {
                    return this.icon[10];
                }
                if ((meta - 1) % 3 + (meta - 1) / 3 * 9 < 0) {
                    return null;
                }
                return this.icon[(meta - 1) % 3 + (meta - 1) / 3 * 9];
            }
            case 2: {
                switch (meta) {
                    default: {
                        if (level != 9) {
                            return this.icon[7];
                        }
                        return this.icon[6];
                    }
                    case 1: {
                        return this.icon[2 + level + add];
                    }
                    case 2: {
                        return this.icon[1 + level + add];
                    }
                    case 3: 
                }
                return this.icon[0 + level + add];
            }
            case 3: {
                switch (meta) {
                    default: {
                        if (level != 9) {
                            return this.icon[7];
                        }
                        return this.icon[6];
                    }
                    case 7: {
                        return this.icon[0 + level + add];
                    }
                    case 8: {
                        return this.icon[1 + level + add];
                    }
                    case 9: 
                }
                return this.icon[2 + level + add];
            }
            case 4: {
                switch (meta) {
                    default: {
                        if (level != 9) {
                            return this.icon[7];
                        }
                        return this.icon[6];
                    }
                    case 1: {
                        return this.icon[0 + level + add];
                    }
                    case 4: {
                        return this.icon[1 + level + add];
                    }
                    case 7: 
                }
                return this.icon[2 + level + add];
            }
            case 5: {
                switch (meta) {
                    default: {
                        if (level != 9) {
                            return this.icon[7];
                        }
                        return this.icon[6];
                    }
                    case 3: {
                        return this.icon[2 + level + add];
                    }
                    case 6: {
                        return this.icon[1 + level + add];
                    }
                    case 9: 
                }
                return this.icon[0 + level + add];
            }
        }
        return add == 0 ? this.icon[7] : this.icon[6];
    }

    public int calculateLevel(IBlockAccess world, int x, int y, int z) {
        int metaB;
        int meta = world.func_72805_g(x, y, z);
        int metaA = world.func_72805_g(x, y + 1, z);
        if (metaA == 10 || metaA == 0) {
            metaA = meta;
        }
        if ((metaB = world.func_72805_g(x, y - 1, z)) == 10 || metaB == 0) {
            metaB = meta;
        }
        Block blockA = world.func_147439_a(x, y + 1, z);
        Block blockB = world.func_147439_a(x, y - 1, z);
        if (meta == metaA && meta == metaB && this == blockA && this == blockB) {
            return 9;
        }
        if (meta == metaA && this == blockA && (meta != metaB || this != blockB)) {
            return 18;
        }
        return 0;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0 || meta == 10) {
            return 13;
        }
        return super.getLightValue(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149633_g(World w, int i, int j, int k) {
        int meta = w.func_72805_g(i, j, k);
        if (meta == 0) {
            return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)1.0, (double)0.25, (double)1.0);
        }
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)1.0, (double)1.0, (double)1.0);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        int md = world.func_72805_g(i, j, k);
        if (md == 10) {
            if (world.func_147439_a(i - 1, j, k) == this && world.func_72805_g(i - 1, j, k) == 0) {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            } else if (world.func_147439_a(i + 1, j, k) == this && world.func_72805_g(i + 1, j, k) == 0) {
                this.func_149676_a(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            } else if (world.func_147439_a(i, j, k - 1) == this && world.func_72805_g(i, j, k - 1) == 0) {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            } else {
                this.func_149676_a(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            }
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (md != 0) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        }
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            if (entity.field_70165_t < (double)((float)x + 0.3f)) {
                entity.field_70159_w += (double)1.0E-4f;
            }
            if (entity.field_70165_t > (double)((float)x + 0.7f)) {
                entity.field_70159_w -= (double)1.0E-4f;
            }
            if (entity.field_70161_v < (double)((float)z + 0.3f)) {
                entity.field_70179_y += (double)1.0E-4f;
            }
            if (entity.field_70161_v > (double)((float)z + 0.7f)) {
                entity.field_70179_y -= (double)1.0E-4f;
            }
            if (entity instanceof EntityItem) {
                TileArcaneFurnace taf;
                entity.field_70181_x = 0.025f;
                if (entity.field_70122_E && (taf = (TileArcaneFurnace)world.func_147438_o(x, y, z)).addItemsToInventory(((EntityItem)entity).func_92059_d())) {
                    entity.func_70106_y();
                }
            } else if (entity instanceof EntityLivingBase && !entity.func_70045_F()) {
                entity.func_70097_a(DamageSource.field_76371_c, 3.0f);
                entity.func_70015_d(10);
            }
        }
    }

    private void restoreBlocks(World par1World, int par2, int par3, int par4) {
        for (int yy = -1; yy <= 1; ++yy) {
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = -1; zz <= 1; ++zz) {
                    Block block = par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz);
                    int md = par1World.func_72805_g(par2 + xx, par3 + yy, par4 + zz);
                    if (block != this) continue;
                    block = Block.func_149634_a((Item)this.func_149650_a(md, new Random(), 0));
                    par1World.func_147465_d(par2 + xx, par3 + yy, par4 + zz, block, 0, 3);
                    par1World.func_147459_d(par2 + xx, par3 + yy, par4 + zz, par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz));
                    par1World.func_147471_g(par2 + xx, par3 + yy, par4 + zz);
                }
            }
        }
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        if (meta == 0) {
            for (int yy = -1; yy <= 1; ++yy) {
                for (int xx = -1; xx <= 1; ++xx) {
                    for (int zz = -1; zz <= 1 && (yy != 1 && yy != 0 || zz != 0 || xx != 0); ++zz) {
                        Block block = par1World.func_147439_a(par2 + xx, par3 + yy, par4 + zz);
                        if (block == this) continue;
                        this.restoreBlocks(par1World, par2, par3, par4);
                        par1World.func_147468_f(par2, par3, par4);
                        par1World.func_147459_d(par2, par3, par4, par1World.func_147439_a(par2, par3, par4));
                        par1World.func_147471_g(par2, par3, par4);
                        return;
                    }
                }
            }
        }
        super.func_149695_a(par1World, par2, par3, par4, par5);
    }

    public void func_149725_f(World world, int x, int y, int z, int meta) {
        TileEntity te;
        if (meta == 0 && !world.field_72995_K && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileArcaneFurnace) {
            Entity newentity = EntityList.func_75620_a((String)"Blaze", (World)world);
            newentity.func_70012_b((double)((float)x + 0.5f), (double)((float)y + 1.0f), (double)((float)z + 0.5f), 0.0f, 0.0f);
            ((EntityLivingBase)newentity).func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 6000, 2));
            ((EntityLivingBase)newentity).func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 12000, 0));
            world.func_72838_d(newentity);
        }
        super.func_149725_f(world, x, y, z, meta);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        if (par1World.func_72805_g(par2, par3, par4) == 0) {
            this.restoreBlocks(par1World, par2, par3, par4);
        }
        for (int yy = -1; yy <= 1; ++yy) {
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = -1; zz <= 1; ++zz) {
                    par1World.func_147459_d(par2 + xx, par3 + yy, par4 + zz, (Block)this);
                }
            }
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public Item func_149650_a(int meta, Random par2Random, int par3) {
        return meta == 0 ? Item.func_150899_d((int)0) : (meta == 10 ? Item.func_150898_a((Block)Blocks.field_150411_aY) : (meta % 2 == 0 || meta == 5 ? Item.func_150898_a((Block)Blocks.field_150343_Z) : Item.func_150898_a((Block)Blocks.field_150385_bj)));
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return world.func_72805_g(x, y, z) != 0;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockArcaneFurnaceRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.func_72805_g(par2, par3, par4) == 0 && par1World.func_147439_a(par2, par3 + 1, par4).func_149688_o() == Material.field_151579_a && !par1World.func_147439_a(par2, par3 + 1, par4).func_149662_c()) {
            for (int a = 0; a < 3; ++a) {
                double var7 = (float)par2 + par5Random.nextFloat();
                double var8 = (float)par3 + 1.0f + par5Random.nextFloat() * 0.5f;
                double var9 = (float)par4 + par5Random.nextFloat();
                par1World.func_72869_a("largesmoke", var7, var8, var9, 0.0, 0.0, 0.0);
            }
        }
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileArcaneFurnace();
        }
        if (metadata == 2 || metadata == 4 || metadata == 5 || metadata == 6 || metadata == 8) {
            return new TileArcaneFurnaceNozzle();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        if (par5 == 1) {
            if (par1World.field_72995_K) {
                Thaumcraft.proxy.blockSparkle(par1World, par2, par3, par4, 0xFF6000, 5);
            }
            return true;
        }
        return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
    }
}

