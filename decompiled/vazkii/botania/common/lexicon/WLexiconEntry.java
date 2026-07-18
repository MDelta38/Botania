/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.lexicon;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.BLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class WLexiconEntry
extends BLexiconEntry {
    private static final int PAGES = 7;

    public WLexiconEntry() {
        super("welcome", BotaniaAPI.categoryBasics);
        this.setPriority();
        this.setIcon(new ItemStack(ModItems.cosmetic, 1, 31));
        LexiconPage[] pages = new LexiconPage[7];
        for (int i = 0; i < 7; ++i) {
            pages[i] = new PageText("" + i);
        }
        this.setLexiconPages(pages);
    }

    @Override
    public int compareTo(LexiconEntry o) {
        return -1;
    }
}

