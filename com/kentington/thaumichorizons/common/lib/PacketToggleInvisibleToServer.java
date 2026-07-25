/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class PacketToggleInvisibleToServer
implements IMessage,
IMessageHandler<PacketToggleInvisibleToServer, IMessage> {
    private int playerid;
    private int dim;

    public PacketToggleInvisibleToServer() {
    }

    public PacketToggleInvisibleToServer(EntityPlayer player, int dim) {
        this.playerid = player.func_145782_y();
        this.dim = dim;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.dim);
    }

    public void fromBytes(ByteBuf buffer) {
        this.playerid = buffer.readInt();
        this.dim = buffer.readInt();
    }

    public IMessage onMessage(PacketToggleInvisibleToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        EntityPlayer player = (EntityPlayer)world.func_73045_a(message.playerid);
        boolean bl = ((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb = !((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb;
        if (((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb) {
            player.func_82170_o(Potion.field_76441_p.field_76415_H);
            player.func_82142_c(false);
        } else {
            PotionEffect effect = new PotionEffect(Potion.field_76441_p.field_76415_H, Integer.MAX_VALUE, 0, true);
            effect.setCurativeItems(new ArrayList());
            player.func_70690_d(effect);
            player.func_82142_c(true);
        }
        return null;
    }
}

