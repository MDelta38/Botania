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
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 *  net.minecraftforge.common.util.EnumHelper
 *  thaumcraft.api.IVisDiscountGear
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item.kami.armor;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
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

public class ItemIchorclothArmor
extends ItemArmor
implements IVisDiscountGear,
ISpecialArmor,
ITTinkererItem {
    static ItemArmor.ArmorMaterial material = EnumHelper.addArmorMaterial((String)"ICHOR", (int)0, (int[])new int[]{3, 8, 6, 3}, (int)20);

    public ItemIchorclothArmor(int par2) {
        super(material, 0, par2);
        this.func_77637_a(ModCreativeTab.INSTANCE);
    }

    public ItemIchorclothArmor(Integer par2) {
        this((int)par2);
    }

    public ItemIchorclothArmor() {
        this(0);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? "ttinkerer:textures/model/ichor2.png" : "ttinkerer:textures/model/ichor1.png";
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + (this.field_77881_a == 3 ? 3 : 4) + "%");
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    public boolean func_77616_k(ItemStack par1ItemStack) {
        return true;
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ISpecialArmor.ArmorProperties(0, (double)this.func_82812_d().func_78044_b(slot) * 0.0425, Integer.MAX_VALUE);
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return this.func_82812_d().func_78044_b(slot);
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
    }

    public int getVisDiscount(ItemStack arg0, EntityPlayer arg1, Aspect arg2) {
        return this.field_77881_a == 3 ? 3 : 4;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        ArrayList<Object> result = new ArrayList<Object>();
        result.add(1);
        result.add(2);
        result.add(3);
        return result;
    }

    @Override
    public String getItemName() {
        switch (this.field_77881_a) {
            case 3: {
                return "ichorclothBoots";
            }
            case 2: {
                return "ichorclothLegs";
            }
            case 1: {
                return "ichorclothChest";
            }
            case 0: {
                return "ichorclothHelm";
            }
        }
        return "INVAlID ARMOR TYPE";
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
    public IRegisterableResearch getResearchItem() {
        return this.field_77881_a != 0 ? null : (IRegisterableResearch)new KamiResearchItem("ICHORCLOTH_ARMOR", new AspectList().add(Aspect.ARMOR, 2).add(Aspect.CLOTH, 1).add(Aspect.LIGHT, 1).add(Aspect.CRAFT, 1), 17, 5, 5, new ItemStack((Item)this)).setWarp(4).setConcealed().setParents(new String[]{"ICHOR_CLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ICHORCLOTH_HELM"), ResearchHelper.arcaneRecipePage("ICHORCLOTH_CHEST"), ResearchHelper.arcaneRecipePage("ICHORCLOTH_LEGS"), ResearchHelper.arcaneRecipePage("ICHORCLOTH_BOOTS")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        switch (this.field_77881_a) {
            case 0: {
                return new ThaumicTinkererArcaneRecipe("ICHORCLOTH_HELM", "ICHORCLOTH_ARMOR", new ItemStack((Item)this), new AspectList().add(Aspect.WATER, 75), "CCC", "C C", Character.valueOf('C'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
            }
            case 1: {
                return new ThaumicTinkererArcaneRecipe("ICHORCLOTH_CHEST", "ICHORCLOTH_ARMOR", new ItemStack((Item)this), new AspectList().add(Aspect.AIR, 75), "C C", "CCC", "CCC", Character.valueOf('C'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
            }
            case 2: {
                return new ThaumicTinkererArcaneRecipe("ICHORCLOTH_LEGS", "ICHORCLOTH_ARMOR", new ItemStack((Item)this), new AspectList().add(Aspect.FIRE, 75), "CCC", "C C", "C C", Character.valueOf('C'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
            }
            case 3: {
                return new ThaumicTinkererArcaneRecipe("ICHORCLOTH_BOOTS", "ICHORCLOTH_ARMOR", new ItemStack((Item)this), new AspectList().add(Aspect.EARTH, 75), "C C", "C C", Character.valueOf('C'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1));
            }
        }
        return null;
    }
}

