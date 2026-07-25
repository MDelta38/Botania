/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 */
package thaumic.tinkerer.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket
implements IMessage {
    public abstract void handleClientSide(EntityPlayer var1);

    public abstract void handleServerSide(EntityPlayer var1);
}

