/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class DLexiconEntry
extends BLexiconEntry {
    public DLexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
    }

    @Override
    public boolean isVisible() {
        return !PersistentVariableHelper.dog;
    }
}

