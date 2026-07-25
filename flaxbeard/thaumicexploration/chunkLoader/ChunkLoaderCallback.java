/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$OrderedLoadingCallback
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 */
package flaxbeard.thaumicexploration.chunkLoader;

import flaxbeard.thaumicexploration.chunkLoader.ITXChunkLoader;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

public class ChunkLoaderCallback
implements ForgeChunkManager.OrderedLoadingCallback {
    public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount) {
        ArrayList<ForgeChunkManager.Ticket> warpChunks = new ArrayList<ForgeChunkManager.Ticket>();
        ArrayList<ForgeChunkManager.Ticket> restChunks = new ArrayList<ForgeChunkManager.Ticket>();
        for (ForgeChunkManager.Ticket ticket : tickets) {
            NBTTagCompound cmp = ticket.getModData();
            if (cmp != null && cmp.func_74764_b("warpChunk") && cmp.func_74767_n("warpChunk")) {
                warpChunks.add(ticket);
                continue;
            }
            restChunks.add(ticket);
        }
        ArrayList<ForgeChunkManager.Ticket> res = new ArrayList<ForgeChunkManager.Ticket>(warpChunks);
        res.addAll(restChunks);
        return res;
    }

    public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
        for (ForgeChunkManager.Ticket ticket : tickets) {
            int zPos;
            int yPos;
            int xPos = ticket.getModData().func_74762_e("xCoord");
            if (world.func_147438_o(xPos, yPos = ticket.getModData().func_74762_e("yCoord"), zPos = ticket.getModData().func_74762_e("zCoord")) != null && world.func_147438_o(xPos, yPos, zPos) instanceof ITXChunkLoader) {
                ((ITXChunkLoader)world.func_147438_o(xPos, yPos, zPos)).forceChunkLoading(ticket);
                continue;
            }
            ForgeChunkManager.releaseTicket((ForgeChunkManager.Ticket)ticket);
        }
    }
}

