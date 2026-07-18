/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.gui.lexicon.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;

public class GuiButtonChallengeInfo
extends GuiButtonLexicon {
    GuiLexicon gui;

    public GuiButtonChallengeInfo(int par1, int par2, int par3, String str, GuiLexicon gui) {
        super(par1, par2, par3, gui.bookmarkWidth(str) + 5, 11, str);
        this.gui = gui;
    }

    public void func_146112_a(Minecraft mc, int par2, int par3) {
        this.gui.drawBookmark(this.field_146128_h, this.field_146129_i, this.field_146126_j, false);
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(StatCollector.func_74838_a((String)"botaniamisc.challengeInfo"));
        int tooltipY = (tooltip.size() + 1) * 5;
        if (k == 2) {
            RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
        }
    }
}

