/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package appeng.api.networking.crafting;

import appeng.api.config.Actionable;
import appeng.api.networking.crafting.ICraftingLink;
import appeng.api.networking.security.IActionHost;
import appeng.api.storage.data.IAEItemStack;
import com.google.common.collect.ImmutableSet;

public interface ICraftingRequester
extends IActionHost {
    public ImmutableSet<ICraftingLink> getRequestedJobs();

    public IAEItemStack injectCraftedItems(ICraftingLink var1, IAEItemStack var2, Actionable var3);

    public void jobStateChange(ICraftingLink var1);
}

