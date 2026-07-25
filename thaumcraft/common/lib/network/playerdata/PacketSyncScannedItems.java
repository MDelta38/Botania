/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.Thaumcraft;

public class PacketSyncScannedItems
implements IMessage,
IMessageHandler<PacketSyncScannedItems, IMessage> {
    protected ArrayList<String> data = new ArrayList();

    public PacketSyncScannedItems() {
    }

    public PacketSyncScannedItems(EntityPlayer player) {
        this.data = Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
    }

    public void toBytes(ByteBuf buffer) {
        if (this.data != null && this.data.size() > 0) {
            buffer.writeShort(this.data.size());
            for (String s : this.data) {
                if (s == null) continue;
                ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)s);
            }
        } else {
            buffer.writeShort(0);
        }
    }

    public void fromBytes(ByteBuf buffer) {
        int size = buffer.readShort();
        this.data = new ArrayList();
        for (int a = 0; a < size; ++a) {
            this.data.add(ByteBufUtils.readUTF8String((ByteBuf)buffer));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketSyncScannedItems message, MessageContext ctx) {
        for (String key : message.data) {
            Thaumcraft.proxy.getResearchManager().completeScannedObject((EntityPlayer)Minecraft.func_71410_x().field_71439_g, key);
        }
        return null;
    }
}

