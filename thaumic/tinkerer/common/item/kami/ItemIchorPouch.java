/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemFocusPouch
 */
package thaumic.tinkerer.common.item.kami;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemFocusPouch;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemIchorPouch
extends ItemFocusPouch
implements IBauble,
ITTinkererItem {
    public ItemIchorPouch() {
        this.func_77637_a(ModCreativeTab.INSTANCE);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 50, par2World, 0, 0, 0);
        return par1ItemStack;
    }

    public ItemStack[] getInventory(ItemStack item) {
        ItemStack[] stackList = new ItemStack[117];
        if (item.func_77942_o()) {
            NBTTagList var2 = item.field_77990_d.func_150295_c("Inventory", 10);
            for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
                NBTTagCompound var4 = var2.func_150305_b(var3);
                int var5 = var4.func_74771_c("Slot") & 0xFF;
                if (var5 < 0 || var5 >= stackList.length) continue;
                stackList[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
            }
        }
        return stackList;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
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
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "ichorPouch";
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
        return (IRegisterableResearch)new KamiResearchItem("ICHOR_POUCH", new AspectList().add(Aspect.VOID, 2).add(Aspect.CLOTH, 1).add(Aspect.ELDRITCH, 1).add(Aspect.MAN, 1), 13, 6, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHOR_CLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("ICHOR_POUCH")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("ICHOR_POUCH", new ItemStack((Item)this), 9, new AspectList().add(Aspect.VOID, 64).add(Aspect.MAN, 32).add(Aspect.CLOTH, 32).add(Aspect.ELDRITCH, 32).add(Aspect.AIR, 64), new ItemStack(ConfigItems.itemFocusPouch), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1), new ItemStack(ConfigItems.itemFocusPortableHole), new ItemStack(Items.field_151045_i), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 1), new ItemStack(ConfigBlocks.blockChestHungry), new ItemStack(ConfigBlocks.blockJar, 1, 3));
    }
}

