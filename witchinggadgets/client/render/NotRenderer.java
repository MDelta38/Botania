/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraftforge.client.IRenderHandler
 */
package witchinggadgets.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.IRenderHandler;

public class NotRenderer
extends IRenderHandler {
    @SideOnly(value=Side.CLIENT)
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
    }
}

