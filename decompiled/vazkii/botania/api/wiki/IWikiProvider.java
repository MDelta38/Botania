/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.api.wiki;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface IWikiProvider {
    public String getBlockName(World var1, MovingObjectPosition var2);

    public String getWikiURL(World var1, MovingObjectPosition var2);

    public String getWikiName(World var1, MovingObjectPosition var2);
}

