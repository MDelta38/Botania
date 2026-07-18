/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.API
 *  codechicken.nei.api.IOverlayHandler
 *  codechicken.nei.recipe.DefaultOverlayHandler
 */
package vazkii.botania.client.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.DefaultOverlayHandler;
import vazkii.botania.client.gui.crafting.GuiCraftingHalo;

public class NEIGuiHooks {
    public static void init() {
        API.registerGuiOverlay(GuiCraftingHalo.class, (String)"crafting");
        API.registerGuiOverlayHandler(GuiCraftingHalo.class, (IOverlayHandler)new DefaultOverlayHandler(), (String)"crafting");
    }
}

