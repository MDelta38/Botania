/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;

public class GuiButtonUpdateWarning
extends GuiButtonLexicon {
    public GuiButtonUpdateWarning(int id, int x, int y) {
        super(id, x, y, 11, 11, "");
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (!this.field_146125_m || !this.field_146124_l) {
            return;
        }
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        boolean red = k == 2 || ClientTickHandler.ticksInGame % 10 < 5;
        par1Minecraft.field_71446_o.func_110577_a(GuiLexicon.texture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, red ? 153 : 142, 180, 11, 11);
        ArrayList<String> tooltip = new ArrayList<String>();
        String version = PersistentVariableHelper.lastBotaniaVersion;
        for (int i = 0; i < 6; ++i) {
            tooltip.add(EnumChatFormatting.GRAY + String.format(StatCollector.func_74838_a((String)("botaniamisc.changes" + i)), version).replaceAll("&", "\u00a7"));
            if (i != 3) continue;
            tooltip.add("");
        }
        int tooltipY = (tooltip.size() - 1) * 10 - 25;
        if (k == 2) {
            RenderHelper.renderTooltip(par2 - 125, par3 + tooltipY, tooltip);
        }
    }
}

