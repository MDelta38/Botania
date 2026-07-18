/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package vazkii.botania.api.wand;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public interface IWireframeAABBProvider {
    public AxisAlignedBB getWireframeAABB(World var1, int var2, int var3, int var4);
}

