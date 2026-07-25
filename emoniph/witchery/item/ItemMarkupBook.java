/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMarkupBook
extends ItemBase {
    private final int dialogID;
    private final int[] creativeMetaValues;

    public ItemMarkupBook(int dialogID) {
        this(dialogID, new int[]{0});
    }

    public ItemMarkupBook(int dialogID, int[] creativeMetaValues) {
        this.dialogID = dialogID;
        this.field_77787_bX = true;
        this.creativeMetaValues = creativeMetaValues;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        int posX = MathHelper.func_76128_c((double)player.field_70165_t);
        int posY = MathHelper.func_76128_c((double)player.field_70163_u);
        int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
        player.openGui((Object)Witchery.instance, this.dialogID, world, posX, posY, posZ);
        return stack;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expandedTooltip) {
        String itemName = Item.field_150901_e.func_148750_c((Object)stack.func_77973_b());
        for (String s : Witchery.resource("item." + itemName + ".tip").split("\n")) {
            if (s.isEmpty()) continue;
            list.add(s);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs creativeTabs, List itemList) {
        for (int meta : this.creativeMetaValues) {
            itemList.add(new ItemStack((Item)this, 1, meta));
        }
    }

    public void onBookRead(ItemStack stack, World world, EntityPlayer player) {
    }
}

