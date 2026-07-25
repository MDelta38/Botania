/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package thaumic.tinkerer.common.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileSummon;
import thaumic.tinkerer.common.core.helper.NumericAspectHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemMobAspect
extends ItemBase {
    public static final int aspectCount = 21;
    public static IIcon[] aspectIcons = new IIcon[63];

    public ItemMobAspect() {
        this.func_77625_d(16);
    }

    public static Aspect getAspect(ItemStack item) {
        if (item == null) {
            return null;
        }
        return NumericAspectHelper.getAspect(item.func_77960_j() % 21);
    }

    public static ItemStack getStackFromAspect(Aspect a) {
        ItemStack result = new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemMobAspect.class));
        result.func_77964_b(NumericAspectHelper.getNumber(a));
        return result;
    }

    public static ItemStack getInfusedStackFromAspect(Aspect a) {
        ItemStack result = new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemMobAspect.class));
        result.func_77964_b(42 + NumericAspectHelper.getNumber(a));
        return result;
    }

    public static boolean isCondensed(ItemStack item) {
        return item.func_77960_j() >= 21 && item.func_77960_j() < 42;
    }

    public static boolean isInfused(ItemStack item) {
        return item.func_77960_j() >= 42;
    }

    public static void markLastUsedTablet(ItemStack stack, TileSummon tablet) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        stack.field_77990_d.func_74768_a("LastX", tablet.field_145851_c);
        stack.field_77990_d.func_74768_a("LastY", tablet.field_145848_d);
        stack.field_77990_d.func_74768_a("LastZ", tablet.field_145849_e);
    }

    public static boolean lastUsedTabletMatches(ItemStack stack, TileSummon tablet) {
        if (stack.field_77990_d == null) {
            return true;
        }
        return stack.field_77990_d.func_74762_e("LastX") == tablet.field_145851_c && stack.field_77990_d.func_74762_e("LastY") == tablet.field_145848_d && stack.field_77990_d.func_74762_e("LastZ") == tablet.field_145849_e;
    }

    public boolean func_77614_k() {
        return true;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        for (NumericAspectHelper aspect : NumericAspectHelper.values) {
            ItemMobAspect.aspectIcons[aspect.num] = IconHelper.forName(par1IconRegister, aspect.getAspect().getName().toLowerCase());
            ItemMobAspect.aspectIcons[aspect.num + 21] = IconHelper.forName(par1IconRegister, aspect.getAspect().getName().toLowerCase() + "_condensed");
            ItemMobAspect.aspectIcons[aspect.num + 42] = IconHelper.forName(par1IconRegister, aspect.getAspect().getName().toLowerCase());
        }
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return ItemMobAspect.isInfused(par1ItemStack);
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
        ThaumicTinkererRecipeMulti recipeMulti = new ThaumicTinkererRecipeMulti();
        for (int i = 0; i < NumericAspectHelper.values.size(); ++i) {
            ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack((Item)this, 1, i), (int[])new int[]{i}, (AspectList)new AspectList().add(NumericAspectHelper.getAspect(i), 8));
            recipeMulti.addRecipe(new ThaumicTinkererCraftingBenchRecipe("SUMMON1", new ItemStack((Item)this, 1, i + 21), "XXX", "XXX", "XXX", Character.valueOf('X'), new ItemStack((Item)this, 1, i)));
            ItemStack input = new ItemStack((Item)this, 1, i + 21);
            recipeMulti.addRecipe(new ThaumicTinkererInfusionRecipe("SUMMON", new ItemStack((Item)this, 1, i + 42), 4, new AspectList().add(ItemMobAspect.getAspect(new ItemStack((Item)this, 1, i)), 10), input, input, input, input, input, input, input, input, input));
        }
        return recipeMulti;
    }

    public void func_150895_a(Item par1Item, CreativeTabs par2CreativeTabs, List par3List) {
        for (NumericAspectHelper aspect : NumericAspectHelper.values) {
            par3List.add(ItemMobAspect.getStackFromAspect(aspect.getAspect()));
            par3List.add(ItemMobAspect.getInfusedStackFromAspect(aspect.getAspect()));
        }
    }

    public IIcon func_77617_a(int par1) {
        return aspectIcons[par1];
    }

    public String func_77667_c(ItemStack stack) {
        if (ItemMobAspect.isCondensed(stack)) {
            return super.func_77667_c(stack) + ".condensed";
        }
        if (ItemMobAspect.isInfused(stack)) {
            return super.func_77667_c(stack) + ".infused";
        }
        return super.func_77667_c(stack);
    }

    public void func_77624_a(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4) {
        list.add(ItemMobAspect.getAspect(itemStack).getName());
    }

    @Override
    public String getItemName() {
        return "mobAspect";
    }
}

