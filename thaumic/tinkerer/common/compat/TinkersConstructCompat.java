/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  tconstruct.library.tools.AbilityHelper
 *  tconstruct.library.tools.ToolCore
 */
package thaumic.tinkerer.common.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tconstruct.library.tools.AbilityHelper;
import tconstruct.library.tools.ToolCore;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;

public class TinkersConstructCompat {
    private static final String TAG_INFINITOOL = "InfiTool";
    private static final String TAG_BROKEN = "Broken";
    private static final String TAG_DAMAGE = "Damage";
    private static final String TAG_DURABILITY = "TotalDurability";
    private static final String TAG_CHARGE = "charge";
    private static final String TAG_ENERGY = "Energy";
    private static final String TAG_REPAIRCOUNT = "RepairCount";

    public static boolean isTConstructTool(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        Item item = stack.func_77973_b();
        if (item instanceof ToolCore) {
            return !ItemNBTHelper.verifyExistance(stack, TAG_ENERGY) && !ItemNBTHelper.verifyExistance(stack, TAG_CHARGE);
        }
        return false;
    }

    public static int getDamage(ItemStack stack) {
        if (stack == null) {
            return -1;
        }
        if (!TinkersConstructCompat.isTConstructTool(stack)) {
            return -1;
        }
        if (ItemNBTHelper.verifyExistance(stack, TAG_ENERGY) || ItemNBTHelper.verifyExistance(stack, TAG_CHARGE)) {
            return -1;
        }
        if (ItemNBTHelper.getNBT(stack).func_74764_b(TAG_INFINITOOL)) {
            NBTTagCompound InfiniTool = ItemNBTHelper.getNBT(stack).func_74775_l(TAG_INFINITOOL);
            if (InfiniTool.func_74764_b(TAG_BROKEN) && InfiniTool.func_74767_n(TAG_BROKEN)) {
                if (InfiniTool.func_74764_b(TAG_DURABILITY)) {
                    return InfiniTool.func_74762_e(TAG_DURABILITY);
                }
                return -1;
            }
            if (InfiniTool.func_74764_b(TAG_DAMAGE)) {
                return InfiniTool.func_74762_e(TAG_DAMAGE);
            }
        }
        return -1;
    }

    public static boolean fixDamage(ItemStack stack, int amount) {
        if (stack == null) {
            return false;
        }
        if (!TinkersConstructCompat.isTConstructTool(stack)) {
            return false;
        }
        if (ItemNBTHelper.verifyExistance(stack, TAG_ENERGY) || ItemNBTHelper.verifyExistance(stack, TAG_CHARGE)) {
            return false;
        }
        if (ItemNBTHelper.getNBT(stack).func_74764_b(TAG_INFINITOOL)) {
            NBTTagCompound InfiniTool = ItemNBTHelper.getNBT(stack).func_74775_l(TAG_INFINITOOL);
            InfiniTool.func_74757_a(TAG_BROKEN, false);
            int damage = InfiniTool.func_74762_e(TAG_DAMAGE);
            int itemsUsed = 0;
            int increase = TinkersConstructCompat.calculateIncrease(stack, amount);
            int repair = InfiniTool.func_74762_e(TAG_REPAIRCOUNT);
            InfiniTool.func_74768_a(TAG_REPAIRCOUNT, repair += itemsUsed);
            if ((damage -= increase) < 0) {
                damage = 0;
            }
            InfiniTool.func_74768_a(TAG_DAMAGE, damage);
            AbilityHelper.damageTool((ItemStack)stack, (int)0, null, (boolean)true);
            ItemNBTHelper.setCompound(stack, TAG_INFINITOOL, InfiniTool);
            AbilityHelper.damageTool((ItemStack)stack, (int)0, null, (boolean)true);
        }
        return false;
    }

    private static int calculateIncrease(ItemStack tool, int amount) {
        NBTTagCompound tags = tool.func_77978_p().func_74775_l(TAG_INFINITOOL);
        int increase = amount * 2;
        int modifiers = tags.func_74762_e("Modifiers");
        float mods = 1.0f;
        if (modifiers == 2) {
            mods = 1.0f;
        } else if (modifiers == 1) {
            mods = 0.75f;
        } else if (modifiers == 0) {
            mods = 0.5f;
        }
        increase = (int)((float)increase * mods);
        int repair = tags.func_74762_e(TAG_REPAIRCOUNT);
        float repairCount = (float)(100 - repair) / 100.0f;
        if (repairCount < 0.5f) {
            repairCount = 0.5f;
        }
        increase = (int)((float)increase * repairCount);
        increase = (int)((float)increase / ((ToolCore)tool.func_77973_b()).getRepairCost());
        return increase;
    }
}

