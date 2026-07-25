/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.IWandable
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockCamo;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.block.tile.TileRPlacer;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockRPlacer
extends BlockCamo
implements IWandable {
    IIcon[] icons = new IIcon[2];

    public BlockRPlacer() {
        super(Material.field_151576_e);
    }

    @Override
    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        TileRPlacer dislocator = (TileRPlacer)tile;
        ItemStack currentStack = par5EntityPlayer.func_71045_bC();
        if (currentStack != null && currentStack.func_77973_b() == ConfigItems.itemWandCasting) {
            dislocator.orientation = par6;
            par1World.func_72908_a((double)par2, (double)par3, (double)par4, "thaumcraft:tool", 0.6f, 1.0f);
            par1World.func_147471_g(par2, par3, par4);
            return true;
        }
        return super.func_149727_a(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileRPlacer.class;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons[0] = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.icons[1] = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    public TileCamo createNewTileEntity(World world, int var2) {
        return new TileRPlacer();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "remotePlacer";
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("REMOTE_PLACER", new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1).add(Aspect.SENSES, 1), -6, 3, 3, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"ANIMATION_TABLET"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), ResearchHelper.arcaneRecipePage("REMOTE_PLACER")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("REMOTE_PLACER", "REMOTE_PLACER", new ItemStack((Block)this), new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.EARTH, 15).add(Aspect.ENTROPY, 5), "ses", "sds", "sss", Character.valueOf('s'), ConfigBlocks.blockStoneDevice, Character.valueOf('e'), Items.field_151079_bi, Character.valueOf('d'), Blocks.field_150367_z);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[0];
    }

    @Override
    public IIcon getIconFromSideAfterCheck(TileEntity tile, int meta, int side) {
        return this.icons[((TileRPlacer)tile).orientation == side ? 1 : 0];
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        ((TileRPlacer)tile).orientation = BlockPistonBase.func_150071_a((World)par1World, (int)par2, (int)par3, (int)par4, (EntityLivingBase)par5EntityLivingBase);
        par1World.func_147471_g(par2, par3, par4);
    }

    public int onWandRightClick(World world, ItemStack itemStack, EntityPlayer entityPlayer, int i, int i2, int i3, int i4, int i5) {
        if (!world.field_72995_K && entityPlayer.func_70093_af()) {
            entityPlayer.openGui((Object)ThaumicTinkerer.instance, 4, world, i, i2, i3);
        }
        return 0;
    }

    public ItemStack onWandRightClick(World world, ItemStack itemStack, EntityPlayer entityPlayer) {
        return itemStack;
    }

    public void onUsingWandTick(ItemStack itemStack, EntityPlayer entityPlayer, int i) {
    }

    public void onWandStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int i) {
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        boolean on;
        if (par1World.field_72995_K) {
            return;
        }
        boolean power = par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4);
        int meta = par1World.func_72805_g(par2, par3, par4);
        boolean bl = on = meta != 0;
        if (power && !on) {
            par1World.func_147464_a(par2, par3, par4, (Block)this, this.func_149738_a(par1World));
            par1World.func_72921_c(par2, par3, par4, 1, 4);
        } else if (!power && on) {
            par1World.func_72921_c(par2, par3, par4, 0, 4);
        }
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile != null && tile instanceof TileRPlacer) {
            TileRPlacer placer = (TileRPlacer)tile;
            placer.receiveRedstonePulse();
        }
    }
}

