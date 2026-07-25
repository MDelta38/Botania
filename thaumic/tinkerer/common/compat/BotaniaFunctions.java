/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  vazkii.botania.api.internal.IManaBurst
 */
package thaumic.tinkerer.common.compat;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;
import vazkii.botania.api.internal.IManaBurst;

public class BotaniaFunctions {
    public static void AddBotaniaClasses() {
        ItemFocusDeflect.DeflectBlacklist.add(IManaBurst.class);
    }

    public static boolean isEntityHarmless(Entity entity) {
        if (entity instanceof IManaBurst) {
            ItemStack lens = ((IManaBurst)entity).getSourceLens();
            return lens.func_77960_j() != 8 && lens.func_77960_j() != 11;
        }
        return true;
    }
}

