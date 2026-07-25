/*
 * Decompiled with CFR 0.152.
 */
package thaumic.tinkerer.common.research;

import java.util.ArrayList;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.TTResearchItem;

public class TTResearchItemMulti
implements IRegisterableResearch {
    private ArrayList<TTResearchItem> researches = new ArrayList();

    public TTResearchItemMulti(ArrayList<TTResearchItem> researches) {
        this.researches = researches;
    }

    public TTResearchItemMulti() {
        this.researches = new ArrayList();
    }

    @Override
    public void registerResearch() {
        for (TTResearchItem researchItem : this.researches) {
            researchItem.registerResearch();
        }
    }

    public void addResearch(TTResearchItem researchItem) {
        this.researches.add(researchItem);
    }
}

