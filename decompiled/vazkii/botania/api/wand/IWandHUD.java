/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.world.World
 */
package vazkii.botania.api.wand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.World;

public interface IWandHUD {
    public void renderHUD(Minecraft var1, ScaledResolution var2, World var3, int var4, int var5, int var6);
}

