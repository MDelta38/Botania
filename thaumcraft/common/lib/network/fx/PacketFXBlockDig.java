/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumcraft.client.fx.particles.FXBoreParticles;
import thaumcraft.common.Thaumcraft;

public class PacketFXBlockDig
implements IMessage,
IMessageHandler<PacketFXBlockDig, IMessage> {
    private int x;
    private int y;
    private int z;
    private int bi;
    private int md;
    private byte dx;
    private byte dy;
    private byte dz;

    public PacketFXBlockDig() {
    }

    public PacketFXBlockDig(int x, int y, int z, byte xd, byte xy, byte xz, int bi, int md) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.bi = bi;
        this.md = md;
        this.dx = xd;
        this.dy = xy;
        this.dz = xz;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.bi);
        buffer.writeInt(this.md);
        buffer.writeByte((int)this.dx);
        buffer.writeByte((int)this.dy);
        buffer.writeByte((int)this.dz);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.bi = buffer.readInt();
        this.md = buffer.readInt();
        this.dx = buffer.readByte();
        this.dy = buffer.readByte();
        this.dz = buffer.readByte();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXBlockDig message, MessageContext ctx) {
        Item item = Item.func_150899_d((int)message.bi);
        if (new ItemStack(item, 1, message.md).func_94608_d() == 0 && item instanceof ItemBlock) {
            Block block = Block.func_149729_e((int)message.bi);
            if (block != null) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(20); ++a) {
                    FXBoreParticles fb = new FXBoreParticles(Thaumcraft.proxy.getClientWorld(), (double)((float)message.dx + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)((float)message.dy + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)((float)message.dz + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)message.x + 0.5, (double)message.y + 0.5, (double)message.z + 0.5, block, Thaumcraft.proxy.getClientWorld().field_73012_v.nextInt(6), message.md).func_70596_a(message.x, message.y, message.z);
                    FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
                }
                Thaumcraft.proxy.getClientWorld().func_72980_b((double)((float)message.dx + 0.5f), (double)((float)message.dy + 0.5f), (double)((float)message.dz + 0.5f), block.field_149762_H.func_150495_a(), (block.field_149762_H.func_150497_c() + 1.0f) / 2.0f, block.field_149762_H.func_150494_d() * 0.8f, false);
            }
        } else {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(20); ++a) {
                FXBoreParticles fb = new FXBoreParticles(Thaumcraft.proxy.getClientWorld(), (double)((float)message.dx + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)((float)message.dy + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)((float)message.dz + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat()), (double)message.x + 0.5, (double)message.y + 0.5, (double)message.z + 0.5, item, Thaumcraft.proxy.getClientWorld().field_73012_v.nextInt(6), message.md).func_70596_a(message.x, message.y, message.z);
                FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
            }
            Thaumcraft.proxy.getClientWorld().func_72980_b((double)((float)message.dx + 0.5f), (double)((float)message.dy + 0.5f), (double)((float)message.dz + 0.5f), Blocks.field_150348_b.field_149762_H.func_150495_a(), (Blocks.field_150348_b.field_149762_H.func_150497_c() + 1.0f) / 2.0f, Blocks.field_150348_b.field_149762_H.func_150494_d() * 0.8f, false);
        }
        return null;
    }
}

