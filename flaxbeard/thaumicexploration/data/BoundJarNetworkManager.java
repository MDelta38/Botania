/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.packet.PacketCustom
 *  codechicken.lib.packet.PacketCustom$IClientPacketHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.INetHandlerPlayClient
 *  net.minecraft.util.Tuple
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSavedData
 *  thaumcraft.api.aspects.AspectList
 */
package flaxbeard.thaumicexploration.data;

import codechicken.lib.packet.PacketCustom;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.data.BoundJarNetworkData;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import thaumcraft.api.aspects.AspectList;

public class BoundJarNetworkManager {
    private static BoundJarNetworkData data = new BoundJarNetworkData();

    public static BoundJarNetworkData getData() {
        if (data == null) {
            BoundJarNetworkManager.loadData();
        }
        return data;
    }

    private static void loadData() {
        World world = ThaumicExploration.proxy.getOverworld();
        if (world != null && (data = (BoundJarNetworkData)world.field_72988_C.func_75742_a(BoundJarNetworkData.class, BoundJarNetworkData.IDENTIFIER)) == null) {
            data = new BoundJarNetworkData();
            data.func_76185_a();
            world.field_72988_C.func_75745_a(BoundJarNetworkData.IDENTIFIER, (WorldSavedData)data);
        }
    }

    public static Packet getPacket(Tuple data) {
        PacketCustom packetCustom = new PacketCustom((Object)ThaumicExploration.instance, 2);
        packetCustom.writeString((String)data.func_76341_a());
        NBTTagCompound compound = new NBTTagCompound();
        ((AspectList)data.func_76340_b()).writeToNBT(compound);
        packetCustom.writeNBTTagCompound(compound);
        return packetCustom.toPacket();
    }

    public static void markDirty() {
        data.func_76185_a();
        World world = ThaumicExploration.proxy.getOverworld();
        if (world != null) {
            world.field_72988_C.func_75744_a();
        }
    }

    public static AspectList getAspect(String network) {
        if (!BoundJarNetworkManager.getData().networks.containsKey(network)) {
            BoundJarNetworkManager.getData().networks.put(network, new AspectList());
        }
        return BoundJarNetworkManager.getData().networks.get(network);
    }

    public static void markDirty(String network) {
        BoundJarNetworkManager.markDirty();
        PacketCustom.sendToClients((Packet)BoundJarNetworkManager.getPacket(new Tuple((Object)network, (Object)BoundJarNetworkManager.getData().networks.get(network))));
    }

    public class BoundJarHandler
    implements PacketCustom.IClientPacketHandler {
        public void handlePacket(PacketCustom packetCustom, Minecraft minecraft, INetHandlerPlayClient iNetHandlerPlayClient) {
            String id = packetCustom.readString();
            if (!data.networks.containsKey(id)) {
                data.networks.put(id, new AspectList());
            }
            data.networks.get(id).readFromNBT(packetCustom.readNBTTagCompound());
        }
    }
}

