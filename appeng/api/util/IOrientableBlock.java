/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.IBlockAccess
 */
package appeng.api.util;

import appeng.api.util.IOrientable;
import net.minecraft.world.IBlockAccess;

public interface IOrientableBlock {
    public boolean usesMetadata();

    public IOrientable getOrientable(IBlockAccess var1, int var2, int var3, int var4);
}

