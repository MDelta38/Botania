/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block.quartz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartzSlab;
import thaumic.tinkerer.common.item.quartz.ItemDarkQuartz;
import thaumic.tinkerer.common.item.quartz.ItemDarkQuartzBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockDarkQuartz
extends BlockMod {
    private static final String[] iconNames = new String[]{"darkQuartz0", "chiseledDarkQuartz0", "pillarDarkQuartz0", null, null};
    private IIcon[] darkQuartzIcons;
    private IIcon chiseledDarkQuartzIcon;
    private IIcon pillarDarkQuartzIcon;
    private IIcon darkQuartzTopIcon;

    public BlockDarkQuartz() {
        super(Material.field_151576_e);
        this.func_149711_c(0.8f);
        this.func_149752_b(10.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 != 2 && par2 != 3 && par2 != 4) {
            if (par1 != 1 && (par1 != 0 || par2 != 1)) {
                if (par1 == 0) {
                    return this.darkQuartzTopIcon;
                }
                if (par2 < 0 || par2 >= this.darkQuartzIcons.length) {
                    par2 = 0;
                }
                return this.darkQuartzIcons[par2];
            }
            return par2 == 1 ? this.chiseledDarkQuartzIcon : this.darkQuartzTopIcon;
        }
        return par2 == 2 && (par1 == 1 || par1 == 0) ? this.pillarDarkQuartzIcon : (par2 == 3 && (par1 == 5 || par1 == 4) ? this.pillarDarkQuartzIcon : (par2 == 4 && (par1 == 2 || par1 == 3) ? this.pillarDarkQuartzIcon : this.darkQuartzIcons[par2]));
    }

    public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        if (par9 == 2) {
            switch (par5) {
                case 0: 
                case 1: {
                    par9 = 2;
                    break;
                }
                case 2: 
                case 3: {
                    par9 = 4;
                    break;
                }
                case 4: 
                case 5: {
                    par9 = 3;
                }
            }
        }
        return par9;
    }

    public int func_149692_a(int par1) {
        return par1 != 3 && par1 != 4 ? par1 : 2;
    }

    public ItemStack func_149644_j(int par1) {
        return par1 != 3 && par1 != 4 ? super.func_149644_j(par1) : new ItemStack((Block)this, 1, 2);
    }

    public int func_149645_b() {
        return 39;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tab, List par3List) {
        par3List.add(new ItemStack((Block)this, 1, 0));
        par3List.add(new ItemStack((Block)this, 1, 1));
        par3List.add(new ItemStack((Block)this, 1, 2));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.darkQuartzIcons = new IIcon[iconNames.length];
        for (int i = 0; i < this.darkQuartzIcons.length; ++i) {
            this.darkQuartzIcons[i] = iconNames[i] == null ? this.darkQuartzIcons[i - 1] : IconHelper.forName(par1IconRegister, iconNames[i]);
        }
        this.darkQuartzTopIcon = IconHelper.forName(par1IconRegister, "darkQuartz1");
        this.chiseledDarkQuartzIcon = IconHelper.forName(par1IconRegister, "chiseledDarkQuartz1");
        this.pillarDarkQuartzIcon = IconHelper.forName(par1IconRegister, "pillarDarkQuartz1");
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "darkQuartz";
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
        return ItemDarkQuartzBlock.class;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ1", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class)), "QQ", "QQ", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ3", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), 2, 2), "Q", "Q", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class)), new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ4", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), 1, 1), "Q", "Q", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class)));
    }
}

