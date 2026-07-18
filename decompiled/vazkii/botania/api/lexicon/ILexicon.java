/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.lexicon;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.lexicon.KnowledgeType;

public interface ILexicon {
    public boolean isKnowledgeUnlocked(ItemStack var1, KnowledgeType var2);

    public void unlockKnowledge(ItemStack var1, KnowledgeType var2);
}

