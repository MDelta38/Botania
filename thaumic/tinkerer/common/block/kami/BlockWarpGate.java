/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.block.kami;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.block.transvector.BlockTransvectorDislocator;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.kami.ItemBlockWarpGate;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class BlockWarpGate
extends BlockModContainer {
    public static IIcon[] icons = new IIcon[3];
    Random random;

    public BlockWarpGate() {
        super(Material.field_151576_e);
        this.func_149711_c(5.0f);
        this.func_149752_b(2000.0f);
        this.random = new Random();
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile;
        if (!par1World.field_72995_K && (tile = par1World.func_147438_o(par2, par3, par4)) != null) {
            par1World.func_147471_g(par2, par3, par4);
            par5EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 51, par1World, par2, par3, par4);
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileWarpGate warpGate = (TileWarpGate)par1World.func_147438_o(par2, par3, par4);
        if (warpGate != null) {
            for (int j1 = 0; j1 < warpGate.func_70302_i_(); ++j1) {
                ItemStack itemstack = warpGate.func_70301_a(j1);
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

    public TileEntity func_149915_a(World world, int in) {
        return new TileWarpGate();
    }

    public IIcon func_149691_a(int par1, int par2) {
        return icons[par1 == 1 ? 0 : 1];
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < icons.length; ++i) {
            BlockWarpGate.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public int func_149645_b() {
        return LibRenderIDs.idWarpGate;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "warpGate";
    }

    @Override
    public boolean shouldRegister() {
        return ConfigHandler.enableKami;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return ItemBlockWarpGate.class;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileWarpGate.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (IRegisterableResearch)new KamiResearchItem("WARP_GATE", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.ELDRITCH, 1).add(Aspect.FLIGHT, 1).add(Aspect.MECHANISM, 1), 19, 6, 5, new ItemStack((Block)this)).setParents(new String[]{"ICHORCLOTH_CHEST_GEM"}).setParentsHidden(new String[]{"ICHORCLOTH_BOOTS_GEM"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("WARP_GATE"), new ResearchPage("1"), ResearchHelper.infusionPage("SKY_PEARL")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return new ThaumicTinkererInfusionRecipe("WARP_GATE", new ItemStack((Block)this), 8, new AspectList().add(Aspect.TRAVEL, 64).add(Aspect.ELDRITCH, 50).add(Aspect.FLIGHT, 50), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 2), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 7), new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorDislocator.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 6), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151008_G));
    }
}

