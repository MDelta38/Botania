/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.API
 *  codechicken.nei.api.IConfigureNEI
 *  codechicken.nei.recipe.ICraftingHandler
 *  codechicken.nei.recipe.IUsageHandler
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.client.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import net.minecraft.item.ItemStack;
import witchinggadgets.client.nei.NEIInfernalBlastfurnaceHandler;
import witchinggadgets.client.nei.NEISpinningWheelHandler;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.ItemClusters;

public class NEIWGConfig
implements IConfigureNEI {
    public void loadConfig() {
        API.registerRecipeHandler((ICraftingHandler)new NEISpinningWheelHandler());
        API.registerUsageHandler((IUsageHandler)new NEISpinningWheelHandler());
        API.registerRecipeHandler((ICraftingHandler)new NEIInfernalBlastfurnaceHandler());
        API.registerUsageHandler((IUsageHandler)new NEIInfernalBlastfurnaceHandler());
        if (WGConfig.allowClusters && ItemClusters.materialMap.isEmpty()) {
            API.hideItem((ItemStack)new ItemStack(WGContent.ItemCluster, 1, Short.MAX_VALUE));
        }
        API.hideItem((ItemStack)new ItemStack(WGContent.BlockWallMirror));
        API.hideItem((ItemStack)new ItemStack(WGContent.BlockVoidWalkway));
    }

    public String getName() {
        return "Witching Gadgets NEI";
    }

    public String getVersion() {
        return "1.1.10";
    }
}

