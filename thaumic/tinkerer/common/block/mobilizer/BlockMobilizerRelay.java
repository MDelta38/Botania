/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block.mobilizer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.block.tile.TileEntityRelay;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockMobilizerRelay
extends BlockMod {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSide;

    public BlockMobilizerRelay() {
        super(Material.field_151573_f);
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityRelay();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.iconBottom = IconHelper.forBlock(iconRegister, (Block)this, 0);
        this.iconTop = IconHelper.forBlock(iconRegister, (Block)this, 1);
        this.iconSide = IconHelper.forBlock(iconRegister, (Block)this, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return par1 == ForgeDirection.UP.ordinal() ? this.iconTop : (par1 == ForgeDirection.DOWN.ordinal() ? this.iconBottom : this.iconSide);
    }

    @Override
    public String getBlockName() {
        return "Levitational Locomotive Relay";
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileEntityRelay.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("LEVITATOR_RELAY", "LEVITATOR", new ItemStack((Block)this), new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.EARTH, 15), "WFW", "SIs", "WFW", Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('s'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('S'), new ItemStack(ConfigItems.itemShard), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog), Character.valueOf('F'), new ItemStack(Blocks.field_150359_w));
    }
}

