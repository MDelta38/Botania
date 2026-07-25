/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.enchantment.Enchantment
 */
package thaumic.tinkerer.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.Enchantment;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketEnchanterAddEnchant
extends PacketTile<TileEnchanter>
implements IMessageHandler<PacketEnchanterAddEnchant, IMessage> {
    private static final long serialVersionUID = -2182522429849764376L;
    int enchant;
    int level;

    public PacketEnchanterAddEnchant() {
    }

    public PacketEnchanterAddEnchant(TileEnchanter tile, int enchant, int level) {
        super(tile);
        this.enchant = enchant;
        this.level = level;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeInt(this.enchant);
        byteBuf.writeInt(this.level);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.enchant = byteBuf.readInt();
        this.level = byteBuf.readInt();
    }

    public IMessage onMessage(PacketEnchanterAddEnchant message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketEnchanterAddEnchant " + message + "on client side!");
        }
        if (((TileEnchanter)message.tile).working) {
            return null;
        }
        if (message.level == -1) {
            int index = ((TileEnchanter)message.tile).enchantments.indexOf(message.enchant);
            ((TileEnchanter)message.tile).removeLevel(index);
            ((TileEnchanter)message.tile).removeEnchant(index);
        } else if (!((TileEnchanter)message.tile).enchantments.contains(message.enchant)) {
            if (message.player != null && EnchantmentManager.canApply(((TileEnchanter)message.tile).func_70301_a(0), Enchantment.field_77331_b[message.enchant], ((TileEnchanter)message.tile).enchantments) && EnchantmentManager.canEnchantmentBeUsed(message.player.func_146103_bH().getName(), Enchantment.field_77331_b[message.enchant])) {
                ((TileEnchanter)message.tile).appendEnchant(message.enchant);
                ((TileEnchanter)message.tile).appendLevel(1);
            }
        } else {
            int maxLevel = Enchantment.field_77331_b[message.enchant].func_77325_b();
            ((TileEnchanter)message.tile).setLevel(((TileEnchanter)message.tile).enchantments.indexOf(message.enchant), Math.max(1, Math.min(maxLevel, message.level)));
        }
        ((TileEnchanter)message.tile).updateAspectList();
        ((TileEnchanter)message.tile).func_145831_w().func_147471_g(((TileEnchanter)message.tile).field_145851_c, ((TileEnchanter)message.tile).field_145848_d, ((TileEnchanter)message.tile).field_145849_e);
        return null;
    }
}

