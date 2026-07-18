/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.lexicon.page;

import java.awt.Desktop;
import java.net.URI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class PageGuide
extends PageText {
    GuiButton button;

    public PageGuide(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void onOpened(IGuiLexiconEntry gui) {
        this.button = new GuiButton(101, gui.getLeft() + 30, gui.getTop() + gui.getHeight() - 50, gui.getWidth() - 60, 20, StatCollector.func_74838_a((String)"botaniamisc.playVideo"));
        gui.getButtonList().add(this.button);
    }

    @Override
    public void onClosed(IGuiLexiconEntry gui) {
        gui.getButtonList().remove(this.button);
    }

    @Override
    public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
        if (button == this.button && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=rx0xyejC6fI"));
                if (Math.random() < 0.01) {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }
}

