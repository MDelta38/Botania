/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IManaProficiencyArmor {
    public boolean shouldGiveProficiency(ItemStack var1, int var2, EntityPlayer var3);

    public static final class Helper {
        public static boolean hasProficiency(EntityPlayer player) {
            for (int i = 0; i < 4; ++i) {
                Item item;
                ItemStack armor = player.func_82169_q(i);
                if (armor == null || !((item = armor.func_77973_b()) instanceof IManaProficiencyArmor) || !((IManaProficiencyArmor)item).shouldGiveProficiency(armor, i, player)) continue;
                return true;
            }
            return false;
        }
    }
}

