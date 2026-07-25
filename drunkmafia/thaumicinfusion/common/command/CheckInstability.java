/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.ChunkCoordIntPair
 */
package drunkmafia.thaumicinfusion.common.command;

import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.world.ChunkData;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ChunkCoordIntPair;

public class CheckInstability
extends CommandBase {
    public String func_71517_b() {
        return ThaumicInfusion.translate("check.instability", new Object[0]);
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "check.instability.usage";
    }

    public void func_71515_b(ICommandSender sender, String[] strings) {
        TIWorldData worldData = TIWorldData.getWorldData(sender.func_130014_f_());
        ChunkCoordIntPair playerChunk = new ChunkCoordIntPair(sender.func_82114_b().field_71574_a >> 4, sender.func_82114_b().field_71573_c >> 4);
        ChunkData chunkData = worldData.chunkDatas.get(playerChunk.func_77273_a(), playerChunk.func_77274_b(), null);
        sender.func_145747_a((IChatComponent)new ChatComponentText("Instability in chunk (" + playerChunk.func_77273_a() + ", " + playerChunk.func_77274_b() + " is " + (chunkData != null ? chunkData.instability : 0)));
    }
}

