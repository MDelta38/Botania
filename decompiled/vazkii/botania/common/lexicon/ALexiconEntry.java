/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class ALexiconEntry
extends BLexiconEntry {
    public ALexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
        this.setKnowledgeType(BotaniaAPI.elvenKnowledge);
    }
}

