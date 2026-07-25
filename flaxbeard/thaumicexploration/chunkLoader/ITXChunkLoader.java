/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 */
package flaxbeard.thaumicexploration.chunkLoader;

import net.minecraftforge.common.ForgeChunkManager;

public interface ITXChunkLoader {
    public void forceChunkLoading(ForgeChunkManager.Ticket var1);

    public void addTicket();

    public void removeTicket(ForgeChunkManager.Ticket var1);
}

