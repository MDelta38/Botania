/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.lib.utils.BlockUtils
 */
package thaumic.tinkerer.common.core.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumic.tinkerer.common.block.tile.TileInfusedGrain;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public class SetTendencyCommand
extends CommandBase {
    public String func_71517_b() {
        return "setCropTendency";
    }

    public String func_71518_a(ICommandSender var1) {
        return "/setCropTendency <Aspect> <Count>";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            MovingObjectPosition pos = BlockUtils.getTargetBlock((World)player.field_70170_p, (Entity)player, (boolean)true);
            if (player.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) instanceof TileInfusedGrain) {
                TileInfusedGrain tile = (TileInfusedGrain)player.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                for (Aspect a : Aspect.getPrimalAspects()) {
                    if (!args[0].toUpperCase().equals(a.getName().toUpperCase())) continue;
                    try {
                        tile.primalTendencies.merge(a, Integer.parseInt(args[1]));
                        tile.reduceSaturatedAspects();
                    }
                    catch (NumberFormatException e) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText("Invalid number"));
                    }
                }
            }
        }
    }

    public boolean func_71519_b(ICommandSender par1iCommandSender) {
        return ConfigHandler.enableDebugCommands;
    }
}

