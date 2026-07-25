/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemSoulMould
extends ItemBase {
    private static final String TAG_PATTERN = "pattern";

    public ItemSoulMould() {
        this.func_77625_d(1);
    }

    private static void setPattern(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving) {
        ItemNBTHelper.setString(par1ItemStack, TAG_PATTERN, EntityList.func_75621_b((Entity)par2EntityLiving));
    }

    public static String getPatternName(ItemStack par1ItemStack) {
        return ItemNBTHelper.getString(par1ItemStack, TAG_PATTERN, "");
    }

    private static void clearPattern(ItemStack par1ItemStack) {
        ItemNBTHelper.getNBT(par1ItemStack).func_82580_o(TAG_PATTERN);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererCrucibleRecipe("MAGNETS", new ItemStack((Item)this), new ItemStack(Items.field_151079_bi), new AspectList().add(Aspect.BEAST, 4).add(Aspect.MIND, 8).add(Aspect.SENSES, 8));
    }

    public boolean func_111207_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase) {
        if (par3EntityLivingBase instanceof EntityPlayer) {
            return true;
        }
        if (par2EntityPlayer != null) {
            ItemSoulMould.setPattern(par2EntityPlayer.func_71045_bC(), par3EntityLivingBase);
        } else {
            ItemSoulMould.setPattern(par1ItemStack, par3EntityLivingBase);
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            ItemSoulMould.clearPattern(par1ItemStack);
        }
        return par1ItemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        String name = ItemSoulMould.getPatternName(par1ItemStack);
        if (name.isEmpty()) {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.soulMould.nonAssigned"));
        } else {
            par3List.add(String.format(StatCollector.func_74838_a((String)"ttmisc.soulMould.pattern"), StatCollector.func_74838_a((String)("entity." + name + ".name"))));
        }
    }

    @Override
    public String getItemName() {
        return "soulMould";
    }
}

