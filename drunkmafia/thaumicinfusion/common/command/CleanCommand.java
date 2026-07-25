/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.command;

import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import java.util.ArrayList;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CleanCommand
extends CommandBase {
    ArrayList<String> players = new ArrayList();

    public String func_71517_b() {
        return ThaumicInfusion.translate("clean.data", new Object[0]);
    }

    public String func_71518_a(ICommandSender iCommandSender) {
        return "clean.data.usage";
    }

    public void func_71515_b(ICommandSender sender, String[] strings) {
        World world = sender.func_130014_f_();
        String playerName = sender.func_70005_c_().toLowerCase();
        if (this.players.contains(playerName) && strings.length > 0 && (strings[0].toLowerCase().contains("y") || strings[0].toLowerCase().contains("yes"))) {
            BlockSavable[] savables;
            TIWorldData data = TIWorldData.getWorldData(world);
            for (BlockSavable savable : savables = data.getAllStoredData()) {
                data.removeData(savable.getClass(), savable.getCoords(), true);
            }
            sender.func_145747_a((IChatComponent)new ChatComponentText("World data has been wiped in dim: " + world.field_73011_w.field_76574_g));
            this.players.remove(playerName);
        } else {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Are you sure you want to do this? All TI blocks placed down will be removed. Type Y or Yes to continue"));
            this.players.add(playerName);
        }
    }
}

