/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.RegenIvyRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemRegenIvy
extends ItemMod {
    public static final String TAG_REGEN = "Botania_regenIvy";
    private static final int MANA_PER_DAMAGE = 200;

    public ItemRegenIvy() {
        this.func_77655_b("regenIvy");
        GameRegistry.addRecipe((IRecipe)new RegenIvyRecipe());
        RecipeSorter.register((String)"botania:regenIvy", RegenIvyRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.field_70170_p.field_72995_K) {
            for (int i = 0; i < event.player.field_71071_by.func_70302_i_(); ++i) {
                ItemStack stack = event.player.field_71071_by.func_70301_a(i);
                if (stack == null || !ItemNBTHelper.detectNBT(stack) || !ItemNBTHelper.getBoolean(stack, TAG_REGEN, false) || stack.func_77960_j() <= 0 || !ManaItemHandler.requestManaExact(stack, event.player, 200, true)) continue;
                stack.func_77964_b(stack.func_77960_j() - 1);
            }
        }
    }
}

