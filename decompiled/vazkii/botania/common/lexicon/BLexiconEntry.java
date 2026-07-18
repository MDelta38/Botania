/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.lexicon;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ITwoNamedPage;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.lexicon.WLexiconEntry;

public class BLexiconEntry
extends LexiconEntry {
    public BLexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
        BotaniaAPI.addEntry(this, category);
    }

    @Override
    public LexiconEntry setLexiconPages(LexiconPage ... pages) {
        for (LexiconPage page : pages) {
            page.unlocalizedName = "botania.page." + this.getLazyUnlocalizedName() + page.unlocalizedName;
            if (!(page instanceof ITwoNamedPage)) continue;
            ITwoNamedPage dou = (ITwoNamedPage)((Object)page);
            dou.setSecondUnlocalizedName("botania.page." + this.getLazyUnlocalizedName() + dou.getSecondUnlocalizedName());
        }
        return super.setLexiconPages(pages);
    }

    @Override
    public String getUnlocalizedName() {
        return "botania.entry." + super.getUnlocalizedName();
    }

    @Override
    public String getTagline() {
        return "botania.tagline." + super.getUnlocalizedName();
    }

    public String getLazyUnlocalizedName() {
        return super.getUnlocalizedName();
    }

    @Override
    public String getWebLink() {
        return "http://botaniamod.net/lexicon.php#" + this.unlocalizedName;
    }

    @Override
    public int compareTo(LexiconEntry o) {
        return o instanceof WLexiconEntry ? 1 : super.compareTo(o);
    }
}

