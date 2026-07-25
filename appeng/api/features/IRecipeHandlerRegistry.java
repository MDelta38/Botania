/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.features;

import appeng.api.recipes.ICraftHandler;
import appeng.api.recipes.IRecipeHandler;
import appeng.api.recipes.ISubItemResolver;

public interface IRecipeHandlerRegistry {
    public void addNewCraftHandler(String var1, Class<? extends ICraftHandler> var2);

    public void addNewSubItemResolver(ISubItemResolver var1);

    public ICraftHandler getCraftHandlerFor(String var1);

    public IRecipeHandler createNewRecipehandler();

    public Object resolveItem(String var1, String var2);
}

