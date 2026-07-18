/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.ALexiconEntry;

public class HLexiconEntry
extends ALexiconEntry {
    public HLexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
    }

    @Override
    public String getWebLink() {
        return "http://heads.freshcoal.com/usernames.php";
    }
}

