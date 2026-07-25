/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;
import thaumcraft.common.lib.world.WorldGenSilverwoodTrees;
import thaumcraft.common.tiles.TileEtherealBloom;

public class BlockCustomPlant
extends BlockBush {
    public IIcon[] icon = new IIcon[6];
    public IIcon iconLeaves;
    public IIcon iconStalk;
    IIcon blank;

    public BlockCustomPlant() {
        super(Material.field_151585_k);
        this.func_149672_a(field_149779_h);
        float var3 = 0.4f;
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149676_a(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, 0.8f, 0.5f + var3);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodsapling");
        this.icon[1] = ir.func_94245_a("thaumcraft:silverwoodsapling");
        this.icon[2] = ir.func_94245_a("thaumcraft:shimmerleaf");
        this.icon[3] = ir.func_94245_a("thaumcraft:cinderpearl");
        this.icon[4] = ir.func_94245_a("thaumcraft:purifier_seed");
        this.icon[5] = ir.func_94245_a("thaumcraft:manashroom");
        this.iconLeaves = ir.func_94245_a("thaumcraft:purifier_leaves");
        this.iconStalk = ir.func_94245_a("thaumcraft:purifier_stalk");
        this.blank = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 == 4 && par1 == 0) {
            return this.blank;
        }
        return par2 < this.icon.length ? this.icon[par2] : null;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 <= 5; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    public boolean hasTileEntity(int metadata) {
        if (metadata == 4) {
            return true;
        }
        return super.hasTileEntity(metadata);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 4) {
            return new TileEtherealBloom();
        }
        return super.createTileEntity(world, metadata);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a((Block)this);
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 3) {
            return EnumPlantType.Desert;
        }
        if (md == 4) {
            return EnumPlantType.Cave;
        }
        return EnumPlantType.Plains;
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return true;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        if (!world.field_72995_K) {
            super.func_149674_a(world, i, j, k, random);
            int l = world.func_72805_g(i, j, k);
            if (l == 0 && world.func_72957_l(i, j + 1, k) >= 9 && random.nextInt(25) == 0) {
                this.growGreatTree(world, i, j, k, random);
            } else if (l == 1 && world.func_72957_l(i, j + 1, k) >= 9 && random.nextInt(50) == 0) {
                this.growSilverTree(world, i, j, k, random);
            }
        }
    }

    public void growGreatTree(World world, int i, int j, int k, Random random) {
        if (world == null || world.field_73011_w == null) {
            return;
        }
        if (world.field_72995_K) {
            return;
        }
        world.func_147468_f(i, j, k);
        WorldGenGreatwoodTrees obj = new WorldGenGreatwoodTrees(true);
        if (!obj.generate(world, random, i, j, k, false)) {
            world.func_147465_d(i, j, k, (Block)this, 0, 0);
        }
    }

    public void growSilverTree(World world, int i, int j, int k, Random random) {
        if (world == null || world.field_73011_w == null) {
            return;
        }
        if (world.field_72995_K) {
            return;
        }
        world.func_147468_f(i, j, k);
        WorldGenSilverwoodTrees obj = new WorldGenSilverwoodTrees(true, 7, 5);
        if (!obj.func_76484_a(world, random, i, j, k)) {
            world.func_147465_d(i, j, k, (Block)this, 1, 0);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1 || md == 2 || md == 3 || md == 5) {
            return 8;
        }
        if (md == 4) {
            return 15;
        }
        return super.getLightValue(world, x, y, z);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int md = world.func_72805_g(x, y, z);
        if (md == 5 && entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
        }
        super.func_149670_a(world, x, y, z, entity);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int i, int j, int k, Random random) {
        int md = world.func_72805_g(i, j, k);
        if (md == 2 && random.nextInt(3) == 0) {
            float cr = 0.3f + world.field_73012_v.nextFloat() * 0.3f;
            float cg = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
            float cb = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
            float xr = (float)i + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f;
            float yr = (float)j + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.15f;
            float zr = (float)k + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f;
            FXWisp ef = new FXWisp(world, xr, yr, zr, 0.2f, cr, cg, cb);
            ef.tinkle = false;
            ParticleEngine.instance.addEffect(world, ef);
        } else if (md == 3 && random.nextBoolean()) {
            float xr = (float)i + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f;
            float yr = (float)j + 0.6f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f;
            float zr = (float)k + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f;
            world.func_72869_a("smoke", (double)xr, (double)yr, (double)zr, 0.0, 0.0, 0.0);
            world.func_72869_a("flame", (double)xr, (double)yr, (double)zr, 0.0, 0.0, 0.0);
        } else if (md == 5 && random.nextInt(3) == 0) {
            float xr = (float)i + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.4f;
            float yr = (float)j + 0.3f;
            float zr = (float)k + 0.5f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.4f;
            FXWisp ef = new FXWisp(world, xr, yr, zr, 0.1f, 0.5f, 0.3f, 0.8f);
            ef.tinkle = false;
            ef.shrink = true;
            ef.setGravity(0.015f);
            ParticleEngine.instance.addEffect(world, ef);
        }
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 100;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 60;
    }
}

