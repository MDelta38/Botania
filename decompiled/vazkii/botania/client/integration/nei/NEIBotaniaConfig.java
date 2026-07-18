/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.API
 *  codechicken.nei.api.IConfigureNEI
 *  codechicken.nei.guihook.GuiContainerManager
 *  codechicken.nei.guihook.IContainerInputHandler
 *  codechicken.nei.recipe.ICraftingHandler
 *  codechicken.nei.recipe.IUsageHandler
 */
package vazkii.botania.client.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import vazkii.botania.client.integration.nei.NEIInputHandler;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerBrewery;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerElvenTrade;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerFloatingFlowers;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerLexicaBotania;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerManaPool;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerPetalApothecary;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerPureDaisy;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerRunicAltar;

public class NEIBotaniaConfig
implements IConfigureNEI {
    public static final String CORPOREA_KEY = "gui.botania_corporea_request";

    public String getName() {
        return "Botania";
    }

    public String getVersion() {
        return "r1.8-249";
    }

    public void loadConfig() {
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerFloatingFlowers());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerFloatingFlowers());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerPetalApothecary());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerPetalApothecary());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerRunicAltar());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerRunicAltar());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerManaPool());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerManaPool());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerElvenTrade());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerElvenTrade());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerBrewery());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerBrewery());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerPureDaisy());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerPureDaisy());
        API.registerRecipeHandler((ICraftingHandler)new RecipeHandlerLexicaBotania());
        API.registerUsageHandler((IUsageHandler)new RecipeHandlerLexicaBotania());
        API.addKeyBind((String)CORPOREA_KEY, (int)46);
        GuiContainerManager.addInputHandler((IContainerInputHandler)new NEIInputHandler());
    }
}

