/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  org.apache.commons.lang3.StringUtils
 *  thaumcraft.common.lib.research.ResearchManager
 */
package thaumic.tinkerer.common.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import thaumcraft.common.lib.research.ResearchManager;
import thaumic.tinkerer.common.research.ResearchHelper;

public class KamiUnlockedCommand
extends CommandBase {
    public String func_71517_b() {
        return "iskamiunlocked";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "iskamiunlocked";
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    public int func_82362_a() {
        return 0;
    }

    public void func_71515_b(ICommandSender icommandsender, String[] astring) {
        List<String> parents = Arrays.asList(ResearchHelper.kamiResearch.parentsHidden);
        ArrayList unlocked = ResearchManager.getResearchForPlayer((String)icommandsender.func_70005_c_());
        if (unlocked.containsAll(parents)) {
            ((EntityPlayer)icommandsender).func_146105_b((IChatComponent)new ChatComponentText("Yes"));
        } else {
            ((EntityPlayer)icommandsender).func_146105_b((IChatComponent)new ChatComponentText("No"));
            ArrayList<String> list = new ArrayList<String>(parents);
            list.removeAll(unlocked);
            ((EntityPlayer)icommandsender).func_146105_b((IChatComponent)new ChatComponentText("Remaining: " + StringUtils.join(list, (char)',')));
        }
    }

    public int compareTo(Object o) {
        return 0;
    }
}

