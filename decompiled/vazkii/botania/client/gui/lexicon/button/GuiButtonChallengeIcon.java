/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon.button;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.challenge.Challenge;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;

public class GuiButtonChallengeIcon
extends GuiButtonLexicon {
    public Challenge challenge;

    public GuiButtonChallengeIcon(int id, int x, int y, Challenge challenge) {
        super(id, x, y, 16, 16, "");
        this.challenge = challenge;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().func_77015_a(par1Minecraft.field_71466_p, par1Minecraft.field_71446_o, this.challenge.icon, this.field_146128_h, this.field_146129_i);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glEnable((int)3042);
        if (this.challenge.complete) {
            GL11.glDisable((int)2929);
            par1Minecraft.field_71466_p.func_78261_a("\u2714", this.field_146128_h + 10, this.field_146129_i + 9, 19456);
            par1Minecraft.field_71466_p.func_78261_a("\u2714", this.field_146128_h + 10, this.field_146129_i + 8, 774669);
            GL11.glEnable((int)2929);
        }
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)this.challenge.unlocalizedName));
        int tooltipY = (tooltip.size() - 1) * 10;
        if (k == 2) {
            RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
        }
    }
}

