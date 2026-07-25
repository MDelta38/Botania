/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.API
 *  codechicken.nei.api.IConfigureNEI
 *  codechicken.nei.api.IHighlightHandler
 *  codechicken.nei.recipe.ICraftingHandler
 *  codechicken.nei.recipe.IUsageHandler
 *  cpw.mods.fml.common.Mod
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package com.emoniph.witchery.integration;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.integration.NEICauldronRecipeHandler;
import com.emoniph.witchery.integration.NEIDistilleryRecipeHandler;
import com.emoniph.witchery.integration.NEIHighlightHandler;
import com.emoniph.witchery.integration.NEIKettleRecipeHandler;
import com.emoniph.witchery.integration.NEISpinningWheelRecipeHandler;
import com.emoniph.witchery.integration.NEIWitchesOvenRecipeHandler;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class NEIWitcheryConfig
implements IConfigureNEI {
    public void loadConfig() {
        if (Config.instance().allowModIntegration && Config.instance().allowNotEnoughItems) {
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.OVEN_BURNING));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.DISTILLERY_BURNING));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.BARRIER));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.FORCE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.CIRCLE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.GLYPH_RITUAL));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.GLYPH_INFERNAL));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.GLYPH_OTHERWHERE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_BELLADONNA));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_MANDRAKE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_ARTICHOKE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_SNOWBELL));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_WORMWOOD));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_MINDRAKE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.CHALICE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.CANDELABRA));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.DREAM_CATCHER));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.DOOR_ALDER));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.DOOR_ROWAN));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.PERPETUAL_ICE_DOOR));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.GLOW_GLOBE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.PLACED_ITEMSTACK));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.DEMON_HEART));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.FORCE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.WEB));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.VINE));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.CACTUS));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.LILY));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.BREW_GAS));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.BREW_LIQUID));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.BREW));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.SLURP));
            API.hideItem((ItemStack)new ItemStack(Witchery.Items.BREW));
            API.hideItem((ItemStack)new ItemStack(Witchery.Items.BUCKET_BREW));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_BUTTON_STONE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_BUTTON_WOOD));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_LEVER));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_SNOW_PRESSURE_PLATE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_STONE_PRESSURE_PLATE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_WOODEN_DOOR));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CURSED_WOODEN_PRESSURE_PLATE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_WOLFSBANE));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.CROP_GARLIC));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.WALLGEN));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.LIGHT));
            API.hideItem((ItemStack)new ItemStack(Witchery.Blocks.SHADED_GLASS_ON));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.MIRROR));
            API.hideItem((ItemStack)new ItemStack((Block)Witchery.Blocks.MIRROR_UNBREAKABLE));
            API.registerRecipeHandler((ICraftingHandler)new NEIWitchesOvenRecipeHandler());
            API.registerUsageHandler((IUsageHandler)new NEIWitchesOvenRecipeHandler());
            API.registerRecipeHandler((ICraftingHandler)new NEIDistilleryRecipeHandler());
            API.registerUsageHandler((IUsageHandler)new NEIDistilleryRecipeHandler());
            API.registerRecipeHandler((ICraftingHandler)new NEIKettleRecipeHandler());
            API.registerRecipeHandler((ICraftingHandler)new NEICauldronRecipeHandler());
            API.registerUsageHandler((IUsageHandler)new NEICauldronRecipeHandler());
            API.registerRecipeHandler((ICraftingHandler)new NEISpinningWheelRecipeHandler());
            API.registerUsageHandler((IUsageHandler)new NEISpinningWheelRecipeHandler());
            API.registerHighlightIdentifier((Block)Witchery.Blocks.TRAPPED_PLANT, (IHighlightHandler)new NEIHighlightHandler(Witchery.Blocks.TRAPPED_PLANT));
            API.registerHighlightIdentifier((Block)Witchery.Blocks.DOOR_ALDER, (IHighlightHandler)new NEIHighlightHandler(Witchery.Blocks.DOOR_ALDER));
            API.registerHighlightIdentifier((Block)Witchery.Blocks.PIT_DIRT, (IHighlightHandler)new NEIHighlightHandler(Witchery.Blocks.PIT_DIRT));
            API.registerHighlightIdentifier((Block)Witchery.Blocks.PIT_GRASS, (IHighlightHandler)new NEIHighlightHandler(Witchery.Blocks.PIT_GRASS));
        }
    }

    public String getName() {
        return Witchery.class.getAnnotation(Mod.class).name();
    }

    public String getVersion() {
        return Witchery.class.getAnnotation(Mod.class).version();
    }
}

