/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.RecipeSorter;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.item.SpellClothRecipe;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemSpellCloth
extends ItemBase {
    public ItemSpellCloth() {
        this.func_77656_e(35);
        this.func_77625_d(1);
        this.setNoRepair();
        SpellClothRecipe recipe = new SpellClothRecipe(this);
        CraftingManager.func_77594_a().func_77592_b().add(recipe);
        RecipeSorter.register((String)"ttinkers:spellcloth", SpellClothRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:minecraft:shapeless");
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        TTResearchItem research = (TTResearchItem)new TTResearchItem("SPELL_CLOTH", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CLOTH, 1), 3, 2, 2, new ItemStack((Item)this), new ResearchPage[0]).setParentsHidden(new String[]{"ENCHFABRIC"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("SPELL_CLOTH")});
        return research;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererCrucibleRecipe("SPELL_CLOTH", new ItemStack((Item)this), new ItemStack(ConfigItems.itemResource, 0, 7), new AspectList().add(Aspect.MAGIC, 10).add(Aspect.ENTROPY, 6).add(Aspect.EXCHANGE, 4));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return Color.HSBtoRGB(0.75f, ((float)par1ItemStack.func_77958_k() - (float)par1ItemStack.func_77960_j()) / (float)par1ItemStack.func_77958_k() * 0.5f, 1.0f);
    }

    public boolean func_77634_r() {
        return true;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        itemStack.func_77964_b(itemStack.func_77960_j() + 1);
        return itemStack;
    }

    public boolean func_77630_h(ItemStack par1ItemStack) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77636_d(ItemStack par1ItemStack) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    @Override
    public String getItemName() {
        return "spellCloth";
    }
}

