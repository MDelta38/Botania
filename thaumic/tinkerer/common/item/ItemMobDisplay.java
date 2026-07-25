/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.core.helper.EnumMobAspect;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemMobDisplay
extends ItemBase {
    public static final String TAG_TYPE = "type";

    public ItemMobDisplay() {
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return false;
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    public EnumMobAspect getEntityType(ItemStack stack) {
        return EnumMobAspect.getMobAspectForType(ItemNBTHelper.getString(stack, TAG_TYPE, ""));
    }

    public void setEntityType(ItemStack stack, String type) {
        ItemNBTHelper.setString(stack, TAG_TYPE, type);
    }

    public void func_150895_a(Item par1Item, CreativeTabs par2CreativeTabs, List list) {
        super.func_150895_a(par1Item, par2CreativeTabs, list);
        for (EnumMobAspect aspect : EnumMobAspect.values()) {
            Class aspClass = aspect.getEntityClass();
            String name = (String)EntityList.field_75626_c.get(aspClass);
            ItemStack item = new ItemStack((Item)this);
            this.setEntityType(item, name);
            list.add(item);
        }
    }

    @Override
    public String getItemName() {
        return "mobDisplay";
    }
}

