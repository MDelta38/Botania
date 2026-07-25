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
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block.transvector;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockCamo;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorDislocator;
import thaumic.tinkerer.common.block.transvector.BlockTransvectorInterface;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockTransvectorDislocator
extends BlockCamo {
    IIcon[] icons = new IIcon[2];

    public BlockTransvectorDislocator() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
    }

    @Override
    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        TileTransvectorDislocator dislocator = (TileTransvectorDislocator)tile;
        ItemStack currentStack = par5EntityPlayer.func_71045_bC();
        if (currentStack != null && currentStack.func_77973_b() == ConfigItems.itemWandCasting) {
            dislocator.orientation = par6;
            par1World.func_72908_a((double)par2, (double)par3, (double)par4, "thaumcraft:tool", 0.6f, 1.0f);
            par1World.func_147471_g(par2, par3, par4);
            return true;
        }
        return super.func_149727_a(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
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

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile != null && tile instanceof TileTransvectorDislocator) {
            TileTransvectorDislocator dislocator = (TileTransvectorDislocator)tile;
            dislocator.receiveRedstonePulse();
        }
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons[0] = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.icons[1] = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    @Override
    public IIcon getIconFromSideAfterCheck(TileEntity tile, int meta, int side) {
        return this.icons[((TileTransvectorDislocator)tile).orientation == side ? 1 : 0];
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[0];
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        ((TileTransvectorDislocator)tile).orientation = BlockPistonBase.func_150071_a((World)par1World, (int)par2, (int)par3, (int)par4, (EntityLivingBase)par5EntityLivingBase);
        par1World.func_147471_g(par2, par3, par4);
    }

    public TileCamo createNewTileEntity(World world, int var2) {
        return new TileTransvectorDislocator();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "dislocator";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileTransvectorDislocator.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (IRegisterableResearch)new TTResearchItem("DISLOCATOR", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.MECHANISM, 1).add(Aspect.ELDRITCH, 1), -6, 1, 3, new ItemStack((Block)this), new ResearchPage[0]).setConcealed().setParents(new String[]{"INTERFACE"}).setParentsHidden(new String[]{"MIRROR"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("DISLOCATOR")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return new ThaumicTinkererArcaneRecipe("DISLOCATOR", "DISLOCATOR", new ItemStack((Block)this), new AspectList().add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 5), " M ", " I ", " C ", Character.valueOf('M'), new ItemStack(ConfigItems.itemResource, 1, 10), Character.valueOf('I'), new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorInterface.class)), Character.valueOf('C'), new ItemStack(Items.field_151132_bS));
    }
}

