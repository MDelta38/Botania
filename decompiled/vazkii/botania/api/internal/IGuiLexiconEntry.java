/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package vazkii.botania.api.internal;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import vazkii.botania.api.lexicon.LexiconEntry;

public interface IGuiLexiconEntry {
    public LexiconEntry getEntry();

    public int getPageOn();

    public int getLeft();

    public int getTop();

    public int getWidth();

    public int getHeight();

    public float getZLevel();

    public List<GuiButton> getButtonList();

    public float getElapsedTicks();

    public float getPartialTicks();

    public float getTickDelta();
}

