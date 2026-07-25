/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.ArrayUtils
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.potions.PotionWarpWard
 */
package thaumic.tinkerer.common.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.potions.PotionWarpWard;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.item.quartz.ItemDarkQuartz;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemCleansingTalisman
extends ItemBase
implements IBauble {
    private static final String TAG_ENABLED = "enabled";
    private IIcon enabledIcon;

    public ItemCleansingTalisman() {
        this.func_77625_d(1);
        this.func_77656_e(100);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_ENABLED, false);
    }

    public static void flipEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, TAG_ENABLED, !ItemCleansingTalisman.isEnabled(stack));
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            ItemCleansingTalisman.flipEnabled(par1ItemStack);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
        }
        return par1ItemStack;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.enabledIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("CLEANSING_TALISMAN", new AspectList().add(Aspect.HEAL, 2).add(Aspect.ORDER, 1).add(Aspect.POISON, 1), -3, 4, 3, new ItemStack((Item)this), new ResearchPage[0]).setSecondary().setParents(new String[]{"DARK_QUARTZ"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("CLEANSING_TALISMAN")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("CLEANSING_TALISMAN", new ItemStack((Item)this), 5, new AspectList().add(Aspect.HEAL, 10).add(Aspect.TOOL, 10).add(Aspect.MAN, 20).add(Aspect.LIFE, 10), new ItemStack(Items.field_151079_bi), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemDarkQuartz.class)), new ItemStack(Items.field_151073_bk), new ItemStack(ConfigItems.itemResource, 1, 1));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (ItemCleansingTalisman.isEnabled(par1ItemStack)) {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.active"));
        } else {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.inactive"));
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return ItemCleansingTalisman.isEnabled(stack) ? this.enabledIcon : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack par1ItemStack, EntityLivingBase player) {
        World par2World = player.field_70170_p;
        if (ItemCleansingTalisman.isEnabled(par1ItemStack) && !par2World.field_72995_K && player.field_70173_aa % 20 == 0 && player instanceof EntityPlayer) {
            boolean removed = false;
            int damage = 1;
            Collection potions = player.func_70651_bq();
            if (player.func_70027_ad()) {
                player.func_70066_B();
                removed = true;
            } else {
                for (PotionEffect potion : potions) {
                    int id = potion.func_76456_a();
                    boolean badEffect = (Boolean)ReflectionHelper.getPrivateValue(Potion.class, (Object)Potion.field_76425_a[id], (String[])new String[]{"isBadEffect", "field_76418_K"});
                    if (Potion.field_76425_a[id] instanceof PotionWarpWard) {
                        badEffect = false;
                    }
                    if (!badEffect) continue;
                    player.func_82170_o(id);
                    removed = true;
                    int[] warpPotionIDs = new int[]{Config.potionBlurredID, Config.potionDeathGazeID, Config.potionInfVisExhaustID, Config.potionSunScornedID, Config.potionUnHungerID};
                    if (!ArrayUtils.contains((int[])warpPotionIDs, (int)potion.func_76456_a())) break;
                    damage = 10;
                    break;
                }
            }
            if (removed) {
                par1ItemStack.func_77972_a(damage, player);
                par2World.func_72956_a((Entity)player, "thaumcraft:wand", 0.3f, 0.1f);
            }
        }
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public String getItemName() {
        return "cleansingTalisman";
    }
}

