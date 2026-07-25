/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.IRepairable
 *  thaumcraft.api.IVisDiscountGear
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.IRevealer
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemRevealingHelm
extends ItemArmor
implements IRepairable,
IRevealer,
IGoggles,
IVisDiscountGear,
ITTinkererItem {
    public ItemRevealingHelm() {
        super(ThaumcraftApi.armorMatThaumium, 2, 0);
        this.func_77656_e(500);
        this.func_77637_a(ModCreativeTab.INSTANCE);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public boolean showNodes(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase) {
        return true;
    }

    public boolean showIngamePopups(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase) {
        return true;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + 5 + "%");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "ttinkerer:textures/model/revealing.png";
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) || super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public int getVisDiscount(ItemStack Itemstack, EntityPlayer Player, Aspect Aspect2) {
        return 5;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "revealingHelm";
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
    public IRegisterableResearch getResearchItem() {
        TTResearchItem research = (TTResearchItem)new TTResearchItem("REVEALING_HELM", new AspectList().add(Aspect.AURA, 2).add(Aspect.ARMOR, 1), 0, 0, 1, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"GOGGLES"}).setParentsHidden(new String[]{"THAUMIUM"});
        research.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage("REVEALING_HELM"));
        return research;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("REVEALING_HELM", "REVEALING_HELM", new ItemStack((Item)this), new AspectList().add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.WATER, 5).add(Aspect.AIR, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5), "GH", Character.valueOf('G'), new ItemStack(ConfigItems.itemGoggles), Character.valueOf('H'), new ItemStack(ConfigItems.itemHelmetThaumium));
    }
}

