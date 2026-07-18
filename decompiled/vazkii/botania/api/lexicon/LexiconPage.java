/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.lexicon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;

public abstract class LexiconPage {
    public String unlocalizedName;
    public boolean skipRegistry;

    public LexiconPage(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    @SideOnly(value=Side.CLIENT)
    public abstract void renderScreen(IGuiLexiconEntry var1, int var2, int var3);

    @SideOnly(value=Side.CLIENT)
    public void updateScreen() {
    }

    @SideOnly(value=Side.CLIENT)
    public void updateScreen(IGuiLexiconEntry gui) {
        this.updateScreen();
    }

    @SideOnly(value=Side.CLIENT)
    public void onOpened(IGuiLexiconEntry gui) {
    }

    @SideOnly(value=Side.CLIENT)
    public void onClosed(IGuiLexiconEntry gui) {
    }

    @SideOnly(value=Side.CLIENT)
    public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
    }

    @SideOnly(value=Side.CLIENT)
    public void onKeyPressed(char c, int key) {
    }

    public void onPageAdded(LexiconEntry entry, int index) {
    }

    public List<ItemStack> getDisplayedRecipes() {
        return null;
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    public LexiconPage setSkipRegistry() {
        this.skipRegistry = true;
        return this;
    }
}

