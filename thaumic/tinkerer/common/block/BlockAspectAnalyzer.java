/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.common.Loader;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileAspectAnalyzer;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockAspectAnalyzer
extends BlockModContainer {
    IIcon[] icons = new IIcon[5];
    Random random;

    public BlockAspectAnalyzer() {
        super(Material.field_151575_d);
        this.func_149711_c(1.7f);
        this.func_149752_b(1.0f);
        this.func_149672_a(Block.field_149766_f);
        this.random = new Random();
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileAspectAnalyzer tile;
        if (!par1World.field_72995_K && (tile = (TileAspectAnalyzer)par1World.func_147438_o(par2, par3, par4)) != null) {
            par5EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 3, par1World, par2, par3, par4);
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileAspectAnalyzer analyzer = (TileAspectAnalyzer)par1World.func_147438_o(par2, par3, par4);
        if (analyzer != null) {
            for (int j1 = 0; j1 < analyzer.func_70302_i_(); ++j1) {
                ItemStack itemstack = analyzer.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < 5; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[par1 == 0 || par1 == 1 ? 0 : par1 - 1];
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileAspectAnalyzer();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "aspectAnalyzer";
    }

    @Override
    public boolean shouldRegister() {
        return Loader.isModLoaded((String)"ComputerCraft");
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
        return TileAspectAnalyzer.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("ASPECT_ANALYZER", new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.SENSES, 1).add(Aspect.MIND, 1), 0, 1, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"PERIPHERALS"}).setParentsHidden(new String[]{"GOGGLES", "THAUMIUM"}).setConcealed().setRound().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ASPECT_ANALYZER")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("ASPECT_ANALYZER", "ASPECT_ANALYZER", new ItemStack((Block)this), new AspectList().add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), "TWT", "WMW", "TWT", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('M'), new ItemStack(ConfigItems.itemThaumometer), Character.valueOf('T'), new ItemStack(ConfigItems.itemResource, 1, 2));
    }
}

