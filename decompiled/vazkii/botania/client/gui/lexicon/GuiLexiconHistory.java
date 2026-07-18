/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.gui.lexicon;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconIndex;

public class GuiLexiconHistory
extends GuiLexiconIndex {
    public static List<LexiconEntry> history = new ArrayList<LexiconEntry>();

    public GuiLexiconHistory() {
        super(null);
        this.title = StatCollector.func_74838_a((String)"botaniamisc.historyLong");
    }

    @Override
    void buildEntries() {
        this.entriesToDisplay.clear();
        ILexicon lex = (ILexicon)stackUsed.func_77973_b();
        for (int i = history.size() - 1; i >= 0; --i) {
            LexiconEntry entry = history.get(i);
            if (!lex.isKnowledgeUnlocked(stackUsed, entry.getKnowledgeType()) || !StatCollector.func_74838_a((String)entry.getUnlocalizedName()).toLowerCase().contains(this.searchField.func_146179_b().toLowerCase().trim())) continue;
            this.entriesToDisplay.add(entry);
        }
    }

    public static void visit(LexiconEntry entry) {
        if (history.contains(entry)) {
            history.remove(entry);
        }
        history.add(entry);
    }

    @Override
    public GuiLexicon copy() {
        return new GuiLexiconHistory();
    }

    @Override
    public void load(NBTTagCompound cmp) {
    }

    @Override
    public String getNotesKey() {
        return "history";
    }
}

