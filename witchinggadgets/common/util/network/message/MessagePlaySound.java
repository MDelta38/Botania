/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class MessagePlaySound
implements IMessage {
    int dim;
    double x;
    double y;
    double z;
    float volume;
    float pitch;
    String sound;

    public MessagePlaySound() {
    }

    public MessagePlaySound(World world, double xx, double yy, double zz, String s, float f, float g) {
        this.dim = world.field_73011_w.field_76574_g;
        this.x = xx;
        this.y = yy;
        this.z = zz;
        this.sound = s;
        this.volume = f;
        this.pitch = g;
    }

    public void fromBytes(ByteBuf buffer) {
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

    public void toBytes(ByteBuf buffer) {
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

    public static class HandlerClient
    implements IMessageHandler<MessagePlaySound, IMessage> {
        public IMessage onMessage(MessagePlaySound message, MessageContext ctx) {
            WorldServer world = DimensionManager.getWorld((int)message.dim);
            if (world != null) {
                world.func_72980_b(message.x, message.y, message.z, message.sound, message.volume, message.pitch, true);
            }
            return null;
        }
    }
}

