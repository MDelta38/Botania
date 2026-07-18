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

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;

public class GuiButtonViewOnline
extends GuiButtonLexicon {
    public GuiButtonViewOnline(int id, int x, int y) {
        super(id, x, y, 11, 11, "");
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        par1Minecraft.field_71446_o.func_110577_a(GuiLexicon.texture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, k == 2 ? 41 : 30, 200, 11, 11);
        List<String> tooltip = Arrays.asList(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)"botaniamisc.viewOnline"));
        int tooltipY = (tooltip.size() - 1) * 10;
        if (k == 2) {
            RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
        }
    }
}

