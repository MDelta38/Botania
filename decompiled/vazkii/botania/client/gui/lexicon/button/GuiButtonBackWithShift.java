/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.gui.lexicon.button;

import java.util.Arrays;
import java.util.List;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBack;

public class GuiButtonBackWithShift
extends GuiButtonBack {
    public GuiButtonBackWithShift(int par1, int par2, int par3) {
        super(par1, par2, par3);
    }

    @Override
    public List<String> getTooltip() {
        return Arrays.asList(StatCollector.func_74838_a((String)"botaniamisc.back"), EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"botaniamisc.clickToIndex"));
    }
}

