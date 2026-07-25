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
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
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
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.item.ItemSpellCloth;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockEnchanter
extends BlockModContainer {
    Random random;
    IIcon iconBottom;
    IIcon iconTop;
    IIcon iconSides;

    public BlockEnchanter() {
        super(Material.field_151576_e);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        this.func_149711_c(5.0f);
        this.func_149752_b(2000.0f);
        this.random = new Random();
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile;
        if (!par1World.field_72995_K && (tile = par1World.func_147438_o(par2, par3, par4)) != null) {
            par1World.func_147471_g(par2, par3, par4);
            par5EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 2, par1World, par2, par3, par4);
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEnchanter enchanter = (TileEnchanter)par1World.func_147438_o(par2, par3, par4);
        if (enchanter != null) {
            for (int j1 = 0; j1 < enchanter.func_70302_i_(); ++j1) {
                ItemStack itemstack = enchanter.func_70301_a(j1);
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
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconBottom = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconTop = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        this.iconSides = IconHelper.forBlock(par1IconRegister, (Block)this, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == ForgeDirection.UP.ordinal() ? this.iconTop : (par1 == ForgeDirection.DOWN.ordinal() ? this.iconBottom : this.iconSides);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEnchanter();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "enchanter";
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
        return TileEnchanter.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("ENCHANTER", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.AURA, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DARKNESS, 1).add(Aspect.MIND, 1), 5, 4, 5, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"SPELL_CLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), ResearchHelper.infusionPage("ENCHANTER")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ENCHANTER", new ItemStack((Block)this), 15, new AspectList().add(Aspect.MAGIC, 50).add(Aspect.ENERGY, 20).add(Aspect.ELDRITCH, 20).add(Aspect.VOID, 20).add(Aspect.MIND, 10), new ItemStack(Blocks.field_150381_bn), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemSpellCloth.class)));
    }
}

