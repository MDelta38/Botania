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
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thaumcraft.client.fx.other.FXShieldRunes;
import thaumcraft.common.Thaumcraft;

public class PacketFXShield
implements IMessage,
IMessageHandler<PacketFXShield, IMessage> {
    private int source;
    private int target;

    public PacketFXShield() {
    }

    public PacketFXShield(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.source);
        buffer.writeInt(this.target);
    }

    public void fromBytes(ByteBuf buffer) {
        this.source = buffer.readInt();
        this.target = buffer.readInt();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXShield message, MessageContext ctx) {
        Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
        if (p == null || Thaumcraft.proxy.getClientWorld() == null) {
            return null;
        }
        float pitch = 0.0f;
        float yaw = 0.0f;
        if (message.target >= 0) {
            Entity t = Thaumcraft.proxy.getClientWorld().func_73045_a(message.target);
            if (t != null) {
                float f1;
                double d0 = p.field_70165_t - t.field_70165_t;
                double d1 = (p.field_70121_D.field_72338_b + p.field_70121_D.field_72337_e) / 2.0 - (t.field_70121_D.field_72338_b + t.field_70121_D.field_72337_e) / 2.0;
                double d2 = p.field_70161_v - t.field_70161_v;
                double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
                float f = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
                pitch = f1 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
                yaw = f;
            } else {
                pitch = 90.0f;
                yaw = 0.0f;
            }
            FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, yaw, pitch);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        } else if (message.target == -1) {
            FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0f, 90.0f);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
            fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0f, 270.0f);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        } else if (message.target == -2) {
            FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0f, 270.0f);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        } else if (message.target == -3) {
            FXShieldRunes fb = new FXShieldRunes(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 8, 0.0f, 90.0f);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        }
        return null;
    }
}

