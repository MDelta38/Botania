/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.net.packet.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.aspects.Aspect;

public class WandAspectPacketS
implements IMessage {
    private int playerName;
    private int slot;
    private int dim;
    private Aspect aspect;
    private boolean shouldOpenGUI;

    public WandAspectPacketS() {
    }

    public WandAspectPacketS(EntityPlayer player, int slotNumber, Aspect aspect, boolean shouldOpenGUI) {
        this.playerName = player.func_70005_c_().hashCode();
        this.dim = player.field_71093_bK;
        this.slot = slotNumber;
        this.shouldOpenGUI = shouldOpenGUI;
        this.aspect = aspect;
    }

    public void fromBytes(ByteBuf buf) {
        this.playerName = buf.readInt();
        this.slot = buf.readInt();
        if (buf.readInt() == 1) {
            int hash = buf.readInt();
            for (Aspect aspect : AspectHandler.getRegisteredAspects()) {
                if (aspect.getTag().hashCode() != hash) continue;
                this.aspect = aspect;
                break;
            }
        }
        this.dim = buf.readInt();
        this.shouldOpenGUI = buf.readByte() == 1;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.playerName);
        buf.writeInt(this.slot);
        buf.writeInt(this.aspect != null ? 1 : -1);
        if (this.aspect != null) {
            buf.writeInt(this.aspect != null ? this.aspect.getTag().hashCode() : -1);
        }
        buf.writeInt(this.dim);
        buf.writeByte(this.shouldOpenGUI ? 1 : 0);
    }

    public static class Handler
    implements IMessageHandler<WandAspectPacketS, IMessage> {
        public IMessage onMessage(WandAspectPacketS message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                return null;
            }
            WorldServer world = DimensionManager.getWorld((int)message.dim);
            for (EntityPlayer player : world.field_73010_i) {
                NBTTagCompound compound;
                if (player.func_70005_c_().hashCode() != message.playerName) continue;
                ItemStack stack = player.field_71071_by.field_70462_a[message.slot];
                NBTTagCompound nBTTagCompound = compound = stack.func_77978_p() != null ? stack.func_77978_p() : new NBTTagCompound();
                if (message.aspect != null) {
                    compound.func_74778_a("InfusionAspect", message.aspect.getTag());
                } else if (compound.func_74764_b("InfusionAspect")) {
                    compound.func_82580_o("InfusionAspect");
                }
                compound.func_74757_a("isSelected", message.shouldOpenGUI);
                stack.func_77982_d(compound);
                player.field_71071_by.field_70462_a[((WandAspectPacketS)message).slot] = stack;
                return null;
            }
            return null;
        }
    }
}

