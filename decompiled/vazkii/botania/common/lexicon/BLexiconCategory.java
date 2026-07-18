/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.lexicon;

import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.lexicon.LexiconCategory;

public class BLexiconCategory
extends LexiconCategory {
    public BLexiconCategory(String unlocalizedName, int priority) {
        super("botania.category." + unlocalizedName);
        this.setIcon(new ResourceLocation("botania:textures/gui/categories/" + unlocalizedName + ".png"));
        this.setPriority(priority);
    }
}

