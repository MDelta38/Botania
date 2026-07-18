/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.crafting.recipe.AncientWillRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemAncientWill
extends ItemMod {
    private static final int SUBTYPES = 6;
    IIcon[] icons;

    public ItemAncientWill() {
        this.func_77655_b("ancientWill");
        this.func_77627_a(true);
        this.func_77625_d(1);
        GameRegistry.addRecipe((IRecipe)new AncientWillRecipe());
        RecipeSorter.register((String)"botania:ancientWill", AncientWillRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[6];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int dmg) {
        return this.icons[Math.min(this.icons.length - 1, dmg)];
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.craftToAddWill"), list);
        this.addStringToTooltip(StatCollector.func_74838_a((String)("botania.armorset.will" + stack.func_77960_j() + ".shortDesc")), list);
    }

    public void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }
}

