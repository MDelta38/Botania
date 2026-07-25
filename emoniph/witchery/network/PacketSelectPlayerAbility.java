/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSelectPlayerAbility
implements IMessage {
    private byte vampirePower;
    private boolean trigger;

    public PacketSelectPlayerAbility() {
    }

    public PacketSelectPlayerAbility(ExtendedPlayer playerEx, boolean trigger) {
        this.vampirePower = (byte)playerEx.getSelectedVampirePower().ordinal();
        this.trigger = trigger;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeByte((int)this.vampirePower);
        buffer.writeBoolean(this.trigger);
    }

    public void fromBytes(ByteBuf buffer) {
        this.vampirePower = buffer.readByte();
        this.trigger = buffer.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<PacketSelectPlayerAbility, IMessage> {
        public IMessage onMessage(PacketSelectPlayerAbility message, MessageContext ctx) {
            EntityPlayer player = Witchery.proxy.getPlayer(ctx);
            ExtendedPlayer playerEx = ExtendedPlayer.get(player);
            if (playerEx != null) {
                playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[message.vampirePower], false);
                if (message.trigger) {
                    playerEx.triggerSelectedVampirePower();
                }
            }
            return null;
        }
    }
}

