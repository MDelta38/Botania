/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package vazkii.botania.common.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.common.core.version.ThreadDownloadMod;
import vazkii.botania.common.core.version.VersionChecker;

public class CommandDownloadLatest
extends CommandBase {
    private static final boolean ENABLED = true;

    public String func_71517_b() {
        return "botania-download-latest";
    }

    public String func_71518_a(ICommandSender var1) {
        return "/botania-download-latest <version>";
    }

    public void func_71515_b(ICommandSender var1, String[] var2) {
        if (var2.length == 1) {
            if (VersionChecker.downloadedFile) {
                var1.func_145747_a(new ChatComponentTranslation("botania.versioning.downloadedAlready", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
            } else if (VersionChecker.startedDownload) {
                var1.func_145747_a(new ChatComponentTranslation("botania.versioning.downloadingAlready", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
            } else {
                new ThreadDownloadMod("Botania " + var2[0] + ".jar");
            }
        }
    }
}

