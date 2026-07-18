/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IChatComponent$Serializer
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class CommandShare
extends CommandBase {
    public String func_71517_b() {
        return "botania-share";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "<entry>";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        String json = StatCollector.func_74838_a((String)"botaniamisc.shareMsg");
        json = json.replaceAll("%name%", sender.func_70005_c_());
        json = json.replaceAll("%entry%", args[0]);
        json = json.replaceAll("%entryname%", StatCollector.func_74838_a((String)args[0]));
        IChatComponent component = IChatComponent.Serializer.func_150699_a((String)json);
        MinecraftServer.func_71276_C().func_71203_ab().func_148539_a(component);
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender p_71519_1_) {
        return p_71519_1_ instanceof EntityPlayer;
    }
}

