/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.entities.EntityWizardCow;
import com.kentington.thaumichorizons.common.lib.PacketCowUpdate;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class PacketGetCowData
implements IMessage,
IMessageHandler<PacketGetCowData, IMessage> {
    int id;
    static Aspect[] sorted;

    public PacketGetCowData() {
    }

    public PacketGetCowData(int id) {
        this.id = id;
    }

    public IMessage onMessage(PacketGetCowData message, MessageContext ctx) {
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_();
        Entity ent = world.func_73045_a(message.id);
        if (ent instanceof EntityWizardCow) {
            EntityWizardCow cow = (EntityWizardCow)ent;
            AspectList ess = cow.getEssentia();
            int mod = cow.nodeMod;
            int type = cow.nodeType;
            int[] types = new int[ess.size()];
            int[] amounts = new int[ess.size()];
            int pointer = 0;
            block0: for (Aspect asp : ess.getAspects()) {
                amounts[pointer] = ess.getAmount(asp);
                for (int i = 0; i < sorted.length; ++i) {
                    if (!sorted[i].getTag().equals(asp.getTag())) continue;
                    types[pointer] = i;
                    ++pointer;
                    continue block0;
                }
            }
            return new PacketCowUpdate(types, amounts, type, mod, message.id);
        }
        return null;
    }

    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.id);
    }

    static {
        Set<String> keys = Aspect.aspects.keySet();
        Iterator<String> it = keys.iterator();
        AspectList list = new AspectList();
        while (it.hasNext()) {
            list.add(Aspect.aspects.get(it.next()), 1);
        }
        sorted = list.getAspectsSorted();
    }
}

