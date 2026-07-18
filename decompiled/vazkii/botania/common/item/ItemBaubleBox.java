/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemBaubleBox
extends ItemMod {
    private static final String TAG_ITEMS = "InvItems";
    private static final String TAG_SLOT = "Slot";

    public ItemBaubleBox() {
        this.func_77655_b("baubleBox");
        this.func_77625_d(1);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.openGui((Object)Botania.instance, 3, world, 0, 0, 0);
        return stack;
    }

    public static ItemStack[] loadStacks(ItemStack stack) {
        NBTTagList var2 = ItemNBTHelper.getList(stack, TAG_ITEMS, 10, false);
        ItemStack[] inventorySlots = new ItemStack[36];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c(TAG_SLOT);
            if (var5 < 0 || var5 >= inventorySlots.length) continue;
            inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
        return inventorySlots;
    }

    public static void setStacks(ItemStack stack, ItemStack[] inventorySlots) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < inventorySlots.length; ++var3) {
            if (inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a(TAG_SLOT, (byte)var3);
            inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        ItemNBTHelper.setList(stack, TAG_ITEMS, var2);
    }
}

