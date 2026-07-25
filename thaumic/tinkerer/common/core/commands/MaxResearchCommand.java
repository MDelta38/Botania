/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.Thaumcraft
 */
package thaumic.tinkerer.common.core.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public class MaxResearchCommand
extends CommandBase {
    public String func_71517_b() {
        return "maxresearch";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "Adds 99 to all aspects of the player using it";
    }

    public void func_71515_b(ICommandSender icommandsender, String[] astring) {
        if (icommandsender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)icommandsender;
            for (Aspect as : Aspect.aspects.values()) {
                Thaumcraft.proxy.getResearchManager().completeAspect(player, as, (short)99);
            }
            player.func_146105_b((IChatComponent)new ChatComponentText("Added 99 research to all aspects"));
        }
    }

    public boolean func_71519_b(ICommandSender par1iCommandSender) {
        return ConfigHandler.enableDebugCommands;
    }

    public int compareTo(Object o) {
        return 0;
    }
}

