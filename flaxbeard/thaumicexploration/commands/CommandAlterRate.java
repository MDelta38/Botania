/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 */
package flaxbeard.thaumicexploration.commands;

import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class CommandAlterRate
implements ICommand {
    private List<String> aliases = new ArrayList<String>();

    public CommandAlterRate() {
        this.aliases.add("AlterRate");
        this.aliases.add("ar");
    }

    public String func_71517_b() {
        return "AlterRate";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "/AlterRate <essentia rate> <vis rate>";
    }

    public List func_71514_a() {
        return this.aliases;
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
        int essentia = Integer.parseInt(p_71515_2_[0]);
        int vis = Integer.parseInt(p_71515_2_[1]);
        TileEntitySoulBrazier.EssentiaRate = essentia;
        TileEntitySoulBrazier.VisRate = vis;
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

