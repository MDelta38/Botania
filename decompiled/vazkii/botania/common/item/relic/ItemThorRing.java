/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.relic;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemThorRing
extends ItemRelicBauble {
    public ItemThorRing() {
        super("thorRing");
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    public static ItemStack getThorRing(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        ItemStack stack1 = baubles.func_70301_a(1);
        ItemStack stack2 = baubles.func_70301_a(2);
        return ItemThorRing.isThorRing(stack1) ? stack1 : (ItemThorRing.isThorRing(stack2) ? stack2 : null);
    }

    private static boolean isThorRing(ItemStack stack) {
        return stack != null && (stack.func_77973_b() == ModItems.thorRing || stack.func_77973_b() == ModItems.aesirRing);
    }
}

