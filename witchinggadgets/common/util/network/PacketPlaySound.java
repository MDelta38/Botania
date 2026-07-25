/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import witchinggadgets.common.util.network.AbstractPacket;

public class PacketPlaySound
extends AbstractPacket {
    int dim;
    double x;
    double y;
    double z;
    float volume;
    float pitch;
    String sound;

    public PacketPlaySound() {
    }

    public PacketPlaySound(World world, double xx, double yy, double zz, String s, float f, float g) {
        this.dim = world.field_73011_w.field_76574_g;
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.sound = s;
        this.volume = f;
        this.pitch = g;
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
        this.volume = buffer.readFloat();
        this.pitch = buffer.readFloat();
        String tempString = "";
        int charLength = buffer.readInt();
        for (int i = 0; i < charLength; ++i) {
            for (char c : Character.toChars(buffer.readInt())) {
                tempString = tempString + Character.valueOf(c);
            }
        }
        this.sound = tempString;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
        buffer.writeFloat(this.volume);
        buffer.writeFloat(this.pitch);
        char[] chars = this.sound.toCharArray();
        buffer.writeInt(chars.length);
        for (int i = 0; i < chars.length; ++i) {
            buffer.writeInt(Character.codePointAt(chars, i));
        }
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        WorldServer world = DimensionManager.getWorld((int)this.dim);
        if (world == null) {
            return;
        }
        world.func_72980_b(this.x, this.y, this.z, this.sound, this.volume, this.pitch, true);
    }

    @Override
    public void handleServerSide(EntityPlayer player2) {
    }
}

