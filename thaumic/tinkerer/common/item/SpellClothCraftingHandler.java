/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.api.INoRemoveEnchant;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.ItemSpellCloth;

public class SpellClothCraftingHandler {
    @SubscribeEvent
    public void ItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        boolean foundCloth = false;
        boolean foundEnchanted = false;
        int slot = 0;
        for (int i = 0; i < event.craftMatrix.func_70302_i_(); ++i) {
            ItemStack stack = event.craftMatrix.func_70301_a(i);
            if (stack == null) continue;
            Item item = ThaumicTinkerer.registry.getFirstItemFromClass(ItemSpellCloth.class);
            if (stack.func_77948_v() && !(stack.func_77973_b() instanceof INoRemoveEnchant) && !foundEnchanted) {
                foundEnchanted = true;
                slot = i;
                continue;
            }
            if (stack.func_77973_b() == item && !foundCloth) {
                foundCloth = true;
                continue;
            }
            return;
        }
        if (foundCloth && foundEnchanted) {
            event.craftMatrix.func_70299_a(slot, null);
        }
    }
}

