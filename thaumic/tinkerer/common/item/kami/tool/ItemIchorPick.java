/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemIchorPick
extends ItemPickaxe
implements ITTinkererItem {
    public ItemIchorPick() {
        super(ThaumicTinkerer.proxy.toolMaterialIchor);
        this.func_77637_a(ModCreativeTab.INSTANCE);
        this.setHarvestLevel("pickaxe", 4);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    public boolean func_77616_k(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "ichorPick";
    }

    @Override
    public boolean shouldRegister() {
        return ConfigHandler.enableKami;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return ConfigHandler.enableKami;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("ICHOR_TOOLS", new AspectList().add(Aspect.TOOL, 2).add(Aspect.WEAPON, 1).add(Aspect.METAL, 1).add(Aspect.CRAFT, 1), 13, 12, 5, new ItemStack((Item)this)).setWarp(2).setConcealed().setParents(new String[]{"ICHORIUM"}).setParentsHidden(new String[]{"ROD_ICHORCLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ICHOR_PICK"), ResearchHelper.arcaneRecipePage("ICHOR_SHOVEL"), ResearchHelper.arcaneRecipePage("ICHOR_AXE"), ResearchHelper.arcaneRecipePage("ICHOR_SWORD")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("ICHOR_PICK", "ICHOR_TOOLS", new ItemStack((Item)this), new AspectList().add(Aspect.FIRE, 75), "III", " R ", " R ", Character.valueOf('R'), new ItemStack(ConfigItems.itemWandRod, 1, 2), Character.valueOf('I'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 2));
    }
}

