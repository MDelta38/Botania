/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ServerCustomPacketEvent
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidContainerItem
 *  org.apache.commons.lang3.tuple.MutablePair
 */
package flaxbeard.thaumicexploration.packet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.data.TXWorldData;
import flaxbeard.thaumicexploration.event.DamageSourceTX;
import flaxbeard.thaumicexploration.misc.SortingInventory;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import org.apache.commons.lang3.tuple.MutablePair;

public class TXServerPacketHandler {
    private void handleTypeChangePacket(ByteBufInputStream dat, EntityPlayerMP player) {
        try {
            dat.readByte();
            int dim = dat.readInt();
            WorldServer world = DimensionManager.getWorld((int)dim);
            int x = dat.readInt();
            int y = dat.readInt();
            int z = dat.readInt();
            int x2 = dat.readInt();
            int y2 = dat.readInt();
            int z2 = dat.readInt();
            int type = dat.readInt();
            int side = dat.readInt();
            TileEntityAutoSorter switcher = (TileEntityAutoSorter)world.func_147438_o(x, y, z);
            SortingInventory inv = switcher.chestSorts.get(MutablePair.of((Object)new ChunkCoordinates(x2, y2, z2), (Object)side));
            inv.type = type;
            switcher.chestSorts.put(MutablePair.of((Object)new ChunkCoordinates(x2, y2, z2), (Object)side), inv);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) {
        EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).field_147369_b;
        ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
        try {
            NBTTagCompound par1NBTTagCompound;
            ItemStack item;
            int readInt;
            byte packetID = bbis.readByte();
            int dimension = bbis.readInt();
            WorldServer world = DimensionManager.getWorld((int)dimension);
            if (packetID == 44) {
                this.handleTypeChangePacket(bbis, player);
            }
            if (packetID == 2 && world != null && world.func_73045_a(readInt = bbis.readInt()) != null) {
                EntityLivingBase target = (EntityLivingBase)world.func_73045_a(readInt);
                readInt = bbis.readInt();
                if (world.func_73045_a(readInt) != null && player.func_82169_q(3) != null) {
                    player.func_82169_q(3).func_77972_a(1, (EntityLivingBase)player);
                    if (player.func_82169_q(3).func_77960_j() == player.func_82169_q(3).func_77958_k()) {
                        player.field_71071_by.field_70460_b[3] = null;
                    }
                    target.func_70097_a(DamageSourceTX.witherPlayerDamage((EntityLivingBase)player), 1.0f);
                    ByteBuf buf = Unpooled.buffer();
                    ByteBufOutputStream out = new ByteBufOutputStream(buf);
                    try {
                        out.writeByte(3);
                        out.writeInt(world.field_73011_w.field_76574_g);
                        out.writeInt(target.func_145782_y());
                        out.writeInt(player.func_145782_y());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    FMLProxyPacket packet = new FMLProxyPacket(buf, "tExploration");
                    ThaumicExploration.channel.sendToAll(packet);
                    out.close();
                }
            }
            if (packetID == 4 && world != null && world.func_73045_a(readInt = bbis.readInt()) != null) {
                item = player.field_71071_by.func_70440_f(0);
                if (!player.field_70122_E && item != null) {
                    if (!item.func_77942_o()) {
                        par1NBTTagCompound = new NBTTagCompound();
                        item.func_77982_d(par1NBTTagCompound);
                        item.field_77990_d.func_74757_a("IsSmashing", true);
                        item.field_77990_d.func_74768_a("smashTicks", 0);
                        item.field_77990_d.func_74768_a("airTicks", 0);
                    }
                    if (item.field_77990_d.func_74762_e("airTicks") > 5) {
                        item.field_77990_d.func_74757_a("IsSmashing", true);
                    }
                    System.out.println("SMOOSH");
                }
            }
            if (packetID == 5 && world != null && world.func_73045_a(readInt = bbis.readInt()) != null && player.field_71071_by.func_70440_f(0) != null) {
                item = player.field_71071_by.func_70440_f(0);
                if (!player.field_70122_E && item != null) {
                    if (!item.func_77942_o()) {
                        par1NBTTagCompound = new NBTTagCompound();
                        item.func_77982_d(par1NBTTagCompound);
                        item.field_77990_d.func_74757_a("IsSmashing", true);
                        item.field_77990_d.func_74768_a("smashTicks", 0);
                        item.field_77990_d.func_74768_a("airTicks", 0);
                    }
                    item.field_77990_d.func_74768_a("airTicks", 10);
                }
            }
            if (packetID == 6) {
                int x2 = bbis.readInt();
                int y2 = bbis.readInt();
                int z2 = bbis.readInt();
                int x = bbis.readInt();
                int y = bbis.readInt();
                int z = bbis.readInt();
                int n = bbis.readInt();
            }
            if (packetID == 1 && world != null) {
                int nextID;
                int x = bbis.readInt();
                int y = bbis.readInt();
                int z = bbis.readInt();
                byte type = bbis.readByte();
                int readInt2 = bbis.readInt();
                TileEntity te = world.func_147438_o(x, y, z);
                if (type == 1) {
                    world.func_147465_d(x, y, z, ThaumicExploration.boundChest, world.func_72805_g(x, y, z), 1);
                    ((TileEntityBoundChest)world.func_147438_o((int)x, (int)y, (int)z)).id = nextID = TXWorldData.get((World)world).getNextBoundChestID();
                    ((TileEntityBoundChest)world.func_147438_o(x, y, z)).setColor(15 - player.field_71071_by.func_70448_g().func_77960_j());
                    if (!player.field_71075_bZ.field_75098_d) {
                        player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                    }
                    world.func_147471_g(x, y, z);
                } else if (type == 2) {
                    world.func_147465_d(x, y, z, ThaumicExploration.boundChest, world.func_72805_g(x, y, z), 1);
                    ((TileEntityBoundChest)world.func_147438_o((int)x, (int)y, (int)z)).id = nextID = player.field_71071_by.func_70448_g().field_77990_d.func_74762_e("ID");
                    ((TileEntityBoundChest)world.func_147438_o(x, y, z)).setColor(15 - player.field_71071_by.func_70448_g().func_77960_j());
                    world.func_147471_g(x, y, z);
                    if (!player.field_71075_bZ.field_75098_d) {
                        player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                    }
                } else if (type == 3) {
                    int color = ((TileEntityBoundChest)world.func_147438_o(x, y, z)).getSealColor();
                    if (15 - player.field_71071_by.func_70448_g().func_77960_j() == color) {
                        int nextID2 = ((TileEntityBoundChest)world.func_147438_o((int)x, (int)y, (int)z)).id;
                        ItemStack linkedSeal = new ItemStack(ThaumicExploration.chestSealLinked, 1, player.field_71071_by.func_70448_g().func_77960_j());
                        NBTTagCompound tag = new NBTTagCompound();
                        tag.func_74768_a("ID", nextID2);
                        tag.func_74768_a("x", x);
                        tag.func_74768_a("y", y);
                        tag.func_74768_a("z", z);
                        tag.func_74768_a("dim", world.field_73011_w.field_76574_g);
                        linkedSeal.func_77982_d(tag);
                        player.field_71071_by.func_70441_a(linkedSeal);
                        if (!player.field_71075_bZ.field_75098_d) {
                            player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                        }
                    }
                } else if (type == 7) {
                    if (player.field_71071_by.func_70448_g().func_77973_b() instanceof IFluidContainerItem) {
                        ((IFluidContainerItem)player.field_71071_by.func_70448_g().func_77973_b()).fill(player.field_71071_by.func_70448_g(), new FluidStack(FluidRegistry.WATER, 1000), true);
                    } else if (player.field_71071_by.func_70448_g().func_77973_b() == Items.field_151133_ar) {
                        player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                        player.field_71071_by.func_70441_a(new ItemStack(Items.field_151131_as, 1));
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}

