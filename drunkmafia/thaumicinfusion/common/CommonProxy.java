/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy
implements IGuiHandler {
    public static boolean isClient;

    public void initRenderers() {
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}

