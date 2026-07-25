/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.tool;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.tool.IAdvancedTool;
import thaumic.tinkerer.common.item.kami.tool.ItemIchorShovel;
import thaumic.tinkerer.common.item.kami.tool.ToolHandler;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemIchorShovelAdv
extends ItemIchorShovel
implements IAdvancedTool {
    IIcon[] specialIcons = new IIcon[3];

    public ItemIchorShovelAdv() {
        this.func_77627_a(true);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        for (int i = 0; i < this.specialIcons.length; ++i) {
            this.specialIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int par1) {
        return par1 >= this.specialIcons.length ? super.func_77617_a(par1) : this.specialIcons[par1];
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            ToolHandler.changeMode(par1ItemStack);
            ToolModeHUDHandler.setTooltip(ToolHandler.getToolModeStr(this, par1ItemStack));
        }
        return par1ItemStack;
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        World world = player.field_70170_p;
        Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!ToolHandler.isRightMaterial(mat, ToolHandler.materialsShovel)) {
            return false;
        }
        MovingObjectPosition block = ToolHandler.raytraceFromEntity(world, (Entity)player, true, 4.5);
        if (block == null) {
            return false;
        }
        ForgeDirection direction = ForgeDirection.getOrientation((int)block.field_72310_e);
        int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
        boolean silk = EnchantmentHelper.func_77502_d((EntityLivingBase)player);
        switch (ToolHandler.getMode(stack)) {
            case 0: {
                break;
            }
            case 1: {
                boolean doX = direction.offsetX == 0;
                boolean doY = direction.offsetY == 0;
                boolean doZ = direction.offsetZ == 0;
                ToolHandler.removeBlocksInIteration(player, world, x, y, z, doX ? -2 : 0, doY ? -1 : 0, doZ ? -2 : 0, doX ? 3 : 1, doY ? 4 : 1, doZ ? 3 : 1, null, ToolHandler.materialsShovel, silk, fortune);
                break;
            }
            case 2: {
                Block blk = world.func_147439_a(x, y, z);
                ToolHandler.removeBlocksInIteration(player, world, x, y, z, 0, -8, 0, 1, 8, 1, blk, ToolHandler.materialsShovel, silk, fortune);
                break;
            }
        }
        return false;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(ToolHandler.getToolModeStr(this, par1ItemStack));
    }

    @Override
    public String getType() {
        return "shovel";
    }

    @Override
    public String getItemName() {
        return "ichorShovelGem";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHOR_SHOVEL_GEM", new AspectList().add(Aspect.EARTH, 2).add(Aspect.TOOL, 1).add(Aspect.MINE, 1).add(Aspect.EARTH, 1), 15, 15, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHOR_TOOLS"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHOR_SHOVEL_GEM")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHOR_SHOVEL_GEM", new ItemStack((Item)this), 15, new AspectList().add(Aspect.EARTH, 50).add(Aspect.MINE, 64).add(Aspect.TOOL, 32).add(Aspect.EARTH, 32).add(Aspect.HARVEST, 32).add(Aspect.TRAP, 16).add(Aspect.SENSES, 16), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemIchorShovel.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 2), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemShovelElemental), new ItemStack(ConfigItems.itemFocusExcavation), new ItemStack(Blocks.field_150335_W), new ItemStack(ConfigItems.itemNugget, 1, 21), new ItemStack(ConfigItems.itemNugget, 1, 16), new ItemStack(ConfigItems.itemNugget, 1, 31), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemFocusExcavation), new ItemStack(ConfigItems.itemShovelElemental), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
    }
}

