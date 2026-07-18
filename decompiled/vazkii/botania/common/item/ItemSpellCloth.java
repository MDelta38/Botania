/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.common.crafting.recipe.SpellClothRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemSpellCloth
extends ItemMod {
    public ItemSpellCloth() {
        this.func_77656_e(35);
        this.func_77625_d(1);
        this.setNoRepair();
        this.func_77655_b("spellCloth");
        GameRegistry.addRecipe((IRecipe)new SpellClothRecipe());
        RecipeSorter.register((String)"botania:spellCloth", SpellClothRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return Color.HSBtoRGB(0.55f, ((float)par1ItemStack.func_77958_k() - (float)par1ItemStack.func_77960_j()) / (float)par1ItemStack.func_77958_k() * 0.5f, 1.0f);
    }

    public boolean func_77634_r() {
        return true;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.func_77946_l();
        stack.func_77964_b(stack.func_77960_j() + 1);
        return stack;
    }

    public boolean func_77630_h(ItemStack par1ItemStack) {
        return false;
    }
}

