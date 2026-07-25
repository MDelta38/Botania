/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ServerCommandManager
 */
package drunkmafia.thaumicinfusion.common.command;

import drunkmafia.thaumicinfusion.common.command.CheckInstability;
import drunkmafia.thaumicinfusion.common.command.CleanCommand;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;

public class TICommand {
    public static void init(ServerCommandManager manager) {
        manager.func_71560_a((ICommand)new CleanCommand());
        manager.func_71560_a((ICommand)new CheckInstability());
    }
}

