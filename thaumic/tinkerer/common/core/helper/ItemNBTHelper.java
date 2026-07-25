/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package thaumic.tinkerer.common.core.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public final class ItemNBTHelper {
    public static boolean detectNBT(ItemStack stack) {
        return stack.func_77942_o();
    }

    public static void initNBT(ItemStack stack) {
        if (!ItemNBTHelper.detectNBT(stack)) {
            ItemNBTHelper.injectNBT(stack, new NBTTagCompound());
        }
    }

    public static void injectNBT(ItemStack stack, NBTTagCompound nbt) {
        stack.func_77982_d(nbt);
    }

    public static NBTTagCompound getNBT(ItemStack stack) {
        ItemNBTHelper.initNBT(stack);
        return stack.func_77978_p();
    }

    public static void setBoolean(ItemStack stack, String tag, boolean b) {
        ItemNBTHelper.getNBT(stack).func_74757_a(tag, b);
    }

    public static void setByte(ItemStack stack, String tag, byte b) {
        ItemNBTHelper.getNBT(stack).func_74774_a(tag, b);
    }

    public static void setShort(ItemStack stack, String tag, short s) {
        ItemNBTHelper.getNBT(stack).func_74777_a(tag, s);
    }

    public static void setInt(ItemStack stack, String tag, int i) {
        ItemNBTHelper.getNBT(stack).func_74768_a(tag, i);
    }

    public static void setLong(ItemStack stack, String tag, long l) {
        ItemNBTHelper.getNBT(stack).func_74772_a(tag, l);
    }

    public static void setFloat(ItemStack stack, String tag, float f) {
        ItemNBTHelper.getNBT(stack).func_74776_a(tag, f);
    }

    public static void setDouble(ItemStack stack, String tag, double d) {
        ItemNBTHelper.getNBT(stack).func_74780_a(tag, d);
    }

    public static void setCompound(ItemStack stack, String tag, NBTTagCompound cmp) {
        if (!tag.equalsIgnoreCase("ench")) {
            ItemNBTHelper.getNBT(stack).func_74782_a(tag, (NBTBase)cmp);
        }
    }

    public static void setString(ItemStack stack, String tag, String s) {
        ItemNBTHelper.getNBT(stack).func_74778_a(tag, s);
    }

    public static boolean verifyExistance(ItemStack stack, String tag) {
        return ItemNBTHelper.getNBT(stack).func_74764_b(tag);
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74767_n(tag) : defaultExpected;
    }

    public static byte getByte(ItemStack stack, String tag, byte defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74771_c(tag) : defaultExpected;
    }

    public static short getShort(ItemStack stack, String tag, short defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74765_d(tag) : defaultExpected;
    }

    public static int getInt(ItemStack stack, String tag, int defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74762_e(tag) : defaultExpected;
    }

    public static long getLong(ItemStack stack, String tag, long defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74763_f(tag) : defaultExpected;
    }

    public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74760_g(tag) : defaultExpected;
    }

    public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74769_h(tag) : defaultExpected;
    }

    public static NBTTagCompound getCompound(ItemStack stack, String tag, boolean nullifyOnFail) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74775_l(tag) : (nullifyOnFail ? null : new NBTTagCompound());
    }

    public static String getString(ItemStack stack, String tag, String defaultExpected) {
        return ItemNBTHelper.verifyExistance(stack, tag) ? ItemNBTHelper.getNBT(stack).func_74779_i(tag) : defaultExpected;
    }
}

