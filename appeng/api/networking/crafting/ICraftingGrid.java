/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableCollection
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.world.World
 */
package appeng.api.networking.crafting;

import appeng.api.networking.IGrid;
import appeng.api.networking.IGridCache;
import appeng.api.networking.crafting.ICraftingCPU;
import appeng.api.networking.crafting.ICraftingCallback;
import appeng.api.networking.crafting.ICraftingJob;
import appeng.api.networking.crafting.ICraftingLink;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingRequester;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.data.IAEItemStack;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.util.concurrent.Future;
import net.minecraft.world.World;

public interface ICraftingGrid
extends IGridCache {
    public ImmutableCollection<ICraftingPatternDetails> getCraftingFor(IAEItemStack var1, ICraftingPatternDetails var2, int var3, World var4);

    public Future<ICraftingJob> beginCraftingJob(World var1, IGrid var2, BaseActionSource var3, IAEItemStack var4, ICraftingCallback var5);

    public ICraftingLink submitJob(ICraftingJob var1, ICraftingRequester var2, ICraftingCPU var3, boolean var4, BaseActionSource var5);

    public ImmutableSet<ICraftingCPU> getCpus();

    public boolean canEmitFor(IAEItemStack var1);

    public boolean isRequesting(IAEItemStack var1);
}

