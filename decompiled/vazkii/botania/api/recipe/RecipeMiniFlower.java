/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.recipe;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;

public class RecipeMiniFlower
extends RecipeManaInfusion {
    public RecipeMiniFlower(String flower, String mini, int mana) {
        super(BotaniaAPI.internalHandler.getSubTileAsStack(flower), BotaniaAPI.internalHandler.getSubTileAsStack(mini), mana);
        this.setAlchemy(true);
    }

    @Override
    public boolean matches(ItemStack stack) {
        String key = BotaniaAPI.internalHandler.getStackSubTileKey(stack);
        String input = this.input instanceof String ? (String)this.input : BotaniaAPI.internalHandler.getStackSubTileKey((ItemStack)this.input);
        return key != null && key.equals(input);
    }
}

