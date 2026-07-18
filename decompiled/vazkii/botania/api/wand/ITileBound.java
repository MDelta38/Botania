/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.api.wand;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;

public interface ITileBound {
    @SideOnly(value=Side.CLIENT)
    public ChunkCoordinates getBinding();
}

