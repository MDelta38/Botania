/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Desktop;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.common.lexicon.page.PageText;

public class PageTutorial
extends PageText {
    private static final boolean VIDEO_ENABLED = false;
    GuiButton buttonText;
    GuiButton buttonVideo;

    public PageTutorial(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void onOpened(IGuiLexiconEntry gui) {
        this.buttonText = new GuiButton(101, gui.getLeft() + 20, gui.getTop() + gui.getHeight() - 40, 50, 20, StatCollector.func_74838_a((String)"botaniamisc.tutorialText"));
        gui.getButtonList().add(this.buttonText);
    }

    @Override
    public void onClosed(IGuiLexiconEntry gui) {
        gui.getButtonList().remove(this.buttonText);
    }

    @Override
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        super.renderScreen(gui, mx, my);
        PageText.renderText(this.buttonText.field_146128_h + this.buttonText.field_146120_f + 4, this.buttonText.field_146129_i - 14, 65, 100, "botaniamisc.noVideo");
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
        if (button == this.buttonText) {
            GuiLexicon.startTutorial();
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiLexicon());
            Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentTranslation("botaniamisc.tutorialStarted", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GREEN)));
        } else if (button == this.buttonVideo && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=rx0xyejC6fI"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

