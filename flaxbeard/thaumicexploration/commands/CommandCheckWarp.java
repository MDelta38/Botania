/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  thaumcraft.common.Thaumcraft
 */
package flaxbeard.thaumicexploration.commands;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import thaumcraft.common.Thaumcraft;

public class CommandCheckWarp
implements ICommand {
    public String func_71517_b() {
        return "checkwarp";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "/checkwarp";
    }

    public List func_71514_a() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("checkwarp");
        return arr;
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
        p_71515_1_.func_145747_a((IChatComponent)new ChatComponentText("Permanent Warp: " + Thaumcraft.proxy.playerKnowledge.getWarpPerm(p_71515_1_.func_70005_c_())));
    }

    public boolean func_71519_b(ICommandSender p_71519_1_) {
        return true;
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    public int compareTo(Object o) {
        return 0;
    }
}

