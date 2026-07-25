/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 */
package thaumic.tinkerer.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketEnchanterStartWorking
extends PacketTile<TileEnchanter>
implements IMessageHandler<PacketEnchanterStartWorking, IMessage> {
    private static final long serialVersionUID = -9086252088394185376L;

    public PacketEnchanterStartWorking() {
    }

    public PacketEnchanterStartWorking(TileEnchanter tile) {
        super(tile);
    }

    public void handle() {
    }

    public IMessage onMessage(PacketEnchanterStartWorking message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketEnchanterStartWorking " + message + "on client side!");
        }
        if (!(((TileEnchanter)message.tile).working || ((TileEnchanter)message.tile).enchantments.isEmpty() || ((TileEnchanter)message.tile).levels.isEmpty())) {
            ((TileEnchanter)message.tile).working = true;
            ((TileEnchanter)message.tile).func_145831_w().func_147471_g(((TileEnchanter)message.tile).field_145851_c, ((TileEnchanter)message.tile).field_145848_d, ((TileEnchanter)message.tile).field_145849_e);
        }
        return null;
    }
}

