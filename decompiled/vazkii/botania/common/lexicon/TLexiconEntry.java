/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.lexicon;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.lexicon.BLexiconEntry;
import vazkii.botania.common.lexicon.page.PageTutorial;

public class TLexiconEntry
extends BLexiconEntry {
    public TLexiconEntry() {
        super("tutorial", BotaniaAPI.categoryBasics);
        this.setPriority();
        this.setIcon(new ItemStack(Items.field_151122_aG));
        this.setLexiconPages(new PageTutorial("0"));
    }
}

