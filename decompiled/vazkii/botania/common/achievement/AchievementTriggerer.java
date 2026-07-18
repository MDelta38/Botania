/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemPickupEvent
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.StatBase
 */
package vazkii.botania.common.achievement;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public final class AchievementTriggerer {
    @SubscribeEvent
    public void onItemPickedUp(PlayerEvent.ItemPickupEvent event) {
        Achievement achievement;
        ItemStack stack = event.pickedUp.func_92059_d();
        if (stack != null && stack.func_77973_b() instanceof IPickupAchievement && (achievement = ((IPickupAchievement)stack.func_77973_b()).getAchievementOnPickup(stack, event.player, event.pickedUp)) != null) {
            event.player.func_71064_a((StatBase)achievement, 1);
        }
    }

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Achievement achievement;
        if (event.crafting != null && event.crafting.func_77973_b() instanceof ICraftAchievement && (achievement = ((ICraftAchievement)event.crafting.func_77973_b()).getAchievementOnCraft(event.crafting, event.player, event.craftMatrix)) != null) {
            event.player.func_71064_a((StatBase)achievement, 1);
        }
    }
}

