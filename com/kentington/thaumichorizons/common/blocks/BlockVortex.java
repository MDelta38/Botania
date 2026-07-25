/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.Config;

public class BlockVortex
extends BlockContainer {
    IIcon icon;

    public BlockVortex() {
        super(Config.airyMaterial);
        this.func_149711_c(-1.0f);
        this.func_149752_b(20000.0f);
        this.func_149663_c("ThaumicHorizons_vortex");
        this.func_149672_a(new Block.SoundType("cloth", 0.0f, 1.0f));
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return -1.0f;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 20000.0f;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    public void func_149719_a(IBlockAccess ba, int x, int y, int z) {
        this.func_149676_a(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockVortexRI;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileVortex();
    }

    public TileEntity func_149915_a(World var1, int md) {
        return this.createTileEntity(var1, md);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:vortex");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        TileVortex tile;
        if (entity instanceof EntityPlayer) {
            tile = (TileVortex)world.func_147438_o(x, y, z);
            tile.aspects = new AspectList();
            int numAspects = world.field_73012_v.nextInt(4) + 1;
            for (int a = 0; a < numAspects; ++a) {
                if (world.field_73012_v.nextInt(3) == 0) {
                    tile.aspects.add(Aspect.getCompoundAspects().get(world.field_73012_v.nextInt(Aspect.getCompoundAspects().size())), world.field_73012_v.nextInt(30));
                    continue;
                }
                tile.aspects.add(Aspect.getPrimalAspects().get(world.field_73012_v.nextInt(Aspect.getPrimalAspects().size())), world.field_73012_v.nextInt(30));
            }
        }
        if (stack.func_77960_j() == 1) {
            tile = (TileVortex)world.func_147438_o(x, y, z);
            tile.cheat = true;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Block)this, 1, 0));
        par3List.add(new ItemStack((Block)this, 1, 1));
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        TileVortex tco = (TileVortex)world.func_147438_o(x, y, z);
        MinecraftServer.func_71276_C().func_71218_a(ThaumicHorizons.dimensionPocketId).func_147468_f(0, 129, tco.dimensionID * 256);
    }
}

