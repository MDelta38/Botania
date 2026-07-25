/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  thaumcraft.api.IScribeTools
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemInfusedInkwell
extends ItemBase
implements IScribeTools {
    public ItemInfusedInkwell() {
        this.func_77656_e(800);
        this.field_77777_bU = 1;
        this.canRepair = true;
        this.func_77627_a(false);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return false;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererCraftingBenchRecipe("INFUSED_INKWELL0", new ItemStack((Item)this), "QQQ", "QCQ", "QQQ", Character.valueOf('Q'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('C'), new ItemStack((Item)this, 1, Short.MAX_VALUE)), new ThaumicTinkererInfusionRecipe("INFUSED_INKWELL", new ItemStack((Item)this), 2, new AspectList().add(Aspect.VOID, 8).add(Aspect.DARKNESS, 8), new ItemStack(ConfigItems.itemInkwell), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigBlocks.blockJar), new ItemStack(ConfigItems.itemResource, 1, 3)));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return ConfigItems.itemInkwell.func_77617_a(par1);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public String getItemName() {
        return "infusedInkwell";
    }
}

