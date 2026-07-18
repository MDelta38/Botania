/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.world.SkyblockWorldEvents;

public class CommandSkyblockSpread
extends CommandBase {
    public String func_71517_b() {
        return "botania-skyblock-spread";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "<player> [<range>]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        int maxAllowed = 1000000;
        int minAllowed = 250;
        int minDist = 100;
        int maxrange = 200000;
        if (args.length == 2) {
            maxrange = CommandSkyblockSpread.func_71526_a((ICommandSender)sender, (String)args[1]);
        }
        if (maxrange > maxAllowed) {
            throw new CommandException("botaniamisc.skyblockRangeTooHigh", new Object[0]);
        }
        if (maxrange < minAllowed) {
            throw new CommandException(StatCollector.func_74838_a((String)"botaniamisc.skyblockRangeTooLow"), new Object[0]);
        }
        EntityPlayerMP player = CommandSkyblockSpread.func_82359_c((ICommandSender)sender, (String)args[0]);
        if (player != null) {
            int z;
            int x;
            ChunkCoordinates spawn = player.field_70170_p.func_72861_E();
            while (MathHelper.pointDistancePlane(x = player.field_70170_p.field_73012_v.nextInt(maxrange) - maxrange / 2 + spawn.field_71574_a, z = player.field_70170_p.field_73012_v.nextInt(maxrange) - maxrange / 2 + spawn.field_71573_c, spawn.field_71574_a, spawn.field_71573_c) < (float)minDist) {
            }
            SkyblockWorldEvents.spawnPlayer((EntityPlayer)player, x, spawn.field_71572_b, z, true);
        }
    }
}

