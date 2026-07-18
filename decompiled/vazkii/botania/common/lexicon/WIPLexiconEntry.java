/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class WIPLexiconEntry
extends BLexiconEntry
implements IAddonEntry {
    public WIPLexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
    }

    @Override
    public String getSubtitle() {
        return "botania.gui.lexicon.wip";
    }
}

