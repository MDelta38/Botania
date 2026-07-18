/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class CLexiconEntry
extends BLexiconEntry
implements IAddonEntry {
    String mod;

    public CLexiconEntry(String unlocalizedName, LexiconCategory category, String mod) {
        super(unlocalizedName, category);
        this.mod = mod;
    }

    @Override
    public String getSubtitle() {
        return "[Botania x " + this.mod + "]";
    }
}

