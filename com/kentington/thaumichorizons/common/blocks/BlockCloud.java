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
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.research.ResearchManager
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityItemInvulnerable;
import com.kentington.thaumichorizons.common.tiles.TileCloud;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;

public class BlockCloud
extends BlockContainer {
    public IIcon[] icon = new IIcon[10];
    public IIcon[] icontop = new IIcon[10];
    boolean glow;

    public BlockCloud(boolean glowy) {
        super(Material.field_151580_n);
        this.func_149711_c(Float.MAX_VALUE);
        this.func_149752_b(Float.MAX_VALUE);
        this.func_149663_c("ThaumicHorizons_cloud");
        this.func_149658_d("ThaumicHorizons:cloud");
        this.func_149647_a(ThaumicHorizons.tabTH);
        if (glowy) {
            this.func_149715_a(1.0f);
        }
        this.glow = glowy;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.func_94245_a("thaumichorizons:cloud");
        this.icon[1] = par1IconRegister.func_94245_a("thaumichorizons:firecloud");
        this.icon[2] = par1IconRegister.func_94245_a("thaumichorizons:thundercloud");
        this.icon[3] = par1IconRegister.func_94245_a("thaumichorizons:acidcloud");
        this.icon[4] = par1IconRegister.func_94245_a("thaumichorizons:alloycloud");
        this.icon[5] = par1IconRegister.func_94245_a("thaumichorizons:fleshcloud");
        this.icon[6] = par1IconRegister.func_94245_a("thaumichorizons:viscloud");
        this.icon[7] = par1IconRegister.func_94245_a("thaumichorizons:glyphcloud");
        this.icon[8] = par1IconRegister.func_94245_a("thaumichorizons:sporecloud");
        this.icon[9] = par1IconRegister.func_94245_a("thaumichorizons:animuscloud");
        this.icontop[0] = par1IconRegister.func_94245_a("thaumichorizons:cloudtop");
        this.icontop[1] = par1IconRegister.func_94245_a("thaumichorizons:firecloudtop");
        this.icontop[2] = par1IconRegister.func_94245_a("thaumichorizons:thundercloudtop");
        this.icontop[3] = par1IconRegister.func_94245_a("thaumichorizons:acidcloudtop");
        this.icontop[4] = par1IconRegister.func_94245_a("thaumichorizons:alloycloudtop");
        this.icontop[5] = par1IconRegister.func_94245_a("thaumichorizons:fleshcloudtop");
        this.icontop[6] = par1IconRegister.func_94245_a("thaumichorizons:viscloudtop");
        this.icontop[7] = par1IconRegister.func_94245_a("thaumichorizons:glyphcloudtop");
        this.icontop[8] = par1IconRegister.func_94245_a("thaumichorizons:sporecloudtop");
        this.icontop[9] = par1IconRegister.func_94245_a("thaumichorizons:animuscloudtop");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par1 == 0 || par1 == 1) {
            return this.icontop[par2];
        }
        return this.icon[par2];
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
    }

    private void func_150186_m(World p_150186_1_, int p_150186_2_, int p_150186_3_, int p_150186_4_) {
        Random random = p_150186_1_.field_73012_v;
        double d0 = 0.0625;
        for (int l = 0; l < 6; ++l) {
            double d1 = (float)p_150186_2_ + random.nextFloat();
            double d2 = (float)p_150186_3_ + random.nextFloat();
            double d3 = (float)p_150186_4_ + random.nextFloat();
            if (l == 0 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_ + 1, p_150186_4_).func_149662_c()) {
                d2 = (double)(p_150186_3_ + 1) + d0;
            }
            if (l == 1 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_ - 1, p_150186_4_).func_149662_c()) {
                d2 = (double)(p_150186_3_ + 0) - d0;
            }
            if (l == 2 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_, p_150186_4_ + 1).func_149662_c()) {
                d3 = (double)(p_150186_4_ + 1) + d0;
            }
            if (l == 3 && !p_150186_1_.func_147439_a(p_150186_2_, p_150186_3_, p_150186_4_ - 1).func_149662_c()) {
                d3 = (double)(p_150186_4_ + 0) - d0;
            }
            if (l == 4 && !p_150186_1_.func_147439_a(p_150186_2_ + 1, p_150186_3_, p_150186_4_).func_149662_c()) {
                d1 = (double)(p_150186_2_ + 1) + d0;
            }
            if (l == 5 && !p_150186_1_.func_147439_a(p_150186_2_ - 1, p_150186_3_, p_150186_4_).func_149662_c()) {
                d1 = (double)(p_150186_2_ + 0) - d0;
            }
            if (random.nextInt(10) != 0 || !(d1 < (double)p_150186_2_ || d1 > (double)(p_150186_2_ + 1) || d2 < 0.0 || d2 > (double)(p_150186_3_ + 1) || d3 < (double)p_150186_4_) && !(d3 > (double)(p_150186_4_ + 1))) continue;
            p_150186_1_.func_72869_a("cloud", d1, d2, d3, 0.0, 0.0, 0.0);
        }
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileCloud();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.field_72995_K && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting && ResearchManager.isResearchComplete((String)player.func_70005_c_(), (String)"planarClouds") && ((ItemWandCasting)player.func_70694_bm().func_77973_b()).consumeVis(player.func_70694_bm(), player, Aspect.AIR, 100, false)) {
            world.func_72838_d((Entity)new EntityItemInvulnerable(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack((Block)this, 1, world.func_72805_g(x, y, z))));
            world.func_147468_f(x, y, z);
            world.func_147471_g(x, y, z);
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        if (!this.glow) {
            par3List.add(new ItemStack((Block)this, 1, 0));
            par3List.add(new ItemStack((Block)this, 1, 2));
            par3List.add(new ItemStack((Block)this, 1, 3));
            par3List.add(new ItemStack((Block)this, 1, 5));
            par3List.add(new ItemStack((Block)this, 1, 8));
        } else {
            par3List.add(new ItemStack((Block)this, 1, 1));
            par3List.add(new ItemStack((Block)this, 1, 4));
            par3List.add(new ItemStack((Block)this, 1, 6));
            par3List.add(new ItemStack((Block)this, 1, 7));
            par3List.add(new ItemStack((Block)this, 1, 9));
        }
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        Block block = p_149646_1_.func_147439_a(p_149646_2_, p_149646_3_, p_149646_4_);
        if (block == this) {
            return p_149646_1_.func_72805_g(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.func_72805_g(p_149646_2_ - Facing.field_71586_b[p_149646_5_], p_149646_3_ - Facing.field_71587_c[p_149646_5_], p_149646_4_ - Facing.field_71585_d[p_149646_5_]);
        }
        return true;
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity ent) {
        int md = p_149670_1_.func_72805_g(p_149670_2_, p_149670_3_, p_149670_4_);
        if (md == 1 || md == 4) {
            ent.func_70015_d(6);
        } else if (md == 3) {
            ent.func_70097_a(DamageSourceThaumcraft.dissolve, 1.0f);
        }
    }
}

