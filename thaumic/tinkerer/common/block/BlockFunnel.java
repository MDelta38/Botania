/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileFunnel;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockFunnel
extends BlockModContainer {
    IIcon sideIcon;
    IIcon topIcon;
    Random random;

    public BlockFunnel() {
        super(Material.field_151576_e);
        this.func_149711_c(3.0f);
        this.func_149752_b(8.0f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.random = new Random();
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.topIcon = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.sideIcon = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 > 1 ? this.sideIcon : this.topIcon;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.func_72330_a((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1));
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return par1World.func_147439_a(par2, par3 - 1, par4) == Block.func_149684_b((String)"hopper");
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        if (par1World.func_147439_a(par2, par3 - 1, par4) != Block.func_149684_b((String)"hopper")) {
            this.func_149697_b(par1World, par2, par3, par4, 0, 0);
            par1World.func_147468_f(par2, par3, par4);
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileFunnel funnel = (TileFunnel)par1World.func_147438_o(par2, par3, par4);
        if (funnel != null) {
            for (int j1 = 0; j1 < funnel.func_70302_i_(); ++j1) {
                ItemStack itemstack = funnel.func_70301_a(j1);
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

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileFunnel funnel = (TileFunnel)par1World.func_147438_o(par2, par3, par4);
        ItemStack stack = funnel.func_70301_a(0);
        if (stack == null) {
            ItemStack playerStack = par5EntityPlayer.func_71045_bC();
            if (funnel.func_102007_a(0, playerStack, 1)) {
                funnel.func_70299_a(0, playerStack.func_77979_a(1));
                if (playerStack.field_77994_a <= 0) {
                    par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
                }
                funnel.func_70296_d();
                return true;
            }
        } else {
            if (!par5EntityPlayer.field_71071_by.func_70441_a(stack)) {
                par5EntityPlayer.func_71019_a(stack, false);
            }
            funnel.func_70299_a(0, null);
            funnel.func_70296_d();
            return true;
        }
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileFunnel();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "funnel";
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
        return TileFunnel.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("FUNNEL", new AspectList().add(Aspect.TOOL, 1).add(Aspect.TRAVEL, 2), 0, -7, 1, new ItemStack((Block)this), new ResearchPage[0]).setParentsHidden(new String[]{"DISTILESSENTIA"}).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("FUNNEL")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("FUNNEL", "FUNNEL", new ItemStack((Block)this), new AspectList().add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), "STS", Character.valueOf('S'), new ItemStack(Blocks.field_150348_b), Character.valueOf('T'), new ItemStack(ConfigItems.itemResource, 1, 2));
    }
}

