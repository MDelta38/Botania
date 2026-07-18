/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.api.lexicon;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.lexicon.LexiconEntry;

public class LexiconCategory
implements Comparable<LexiconCategory> {
    private static int count = 0;
    public final String unlocalizedName;
    public final List<LexiconEntry> entries = new ArrayList<LexiconEntry>();
    private final int sortingId;
    private ResourceLocation icon;
    private int priority = 5;

    public LexiconCategory(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        this.sortingId = count++;
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    public LexiconCategory setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public int getSortingPriority() {
        return this.priority;
    }

    public final int getSortingId() {
        return this.sortingId;
    }

    public LexiconCategory setIcon(ResourceLocation icon) {
        this.icon = icon;
        return this;
    }

    public ResourceLocation getIcon() {
        return this.icon;
    }

    @Override
    public int compareTo(LexiconCategory category) {
        return this.priority == category.priority ? this.sortingId - category.sortingId : category.priority - this.priority;
    }
}

