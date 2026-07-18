/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.nodes.IRevealer
 */
package vazkii.botania.common.item.interaction.thaumcraft;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.crafting.recipe.HelmRevealingRecipe;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelHelm;

@Optional.InterfaceList(value={@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IGoggles", striprefs=true), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.nodes.IRevealer", striprefs=true)})
public class ItemManasteelHelmRevealing
extends ItemManasteelHelm
implements IGoggles,
IRevealer {
    public ItemManasteelHelmRevealing() {
        super("manasteelHelmReveal");
        GameRegistry.addRecipe((IRecipe)new HelmRevealingRecipe());
        RecipeSorter.register((String)"botania:helmRevealing", HelmRevealingRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? "botania:textures/model/manasteelNew.png" : "botania:textures/model/manasteel2.png";
    }
}

