/*
 * Decompiled with CFR 0.152.
 */
package thaumic.tinkerer.common.registry;

import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public interface ITTinkererRegisterable {
    public IRegisterableResearch getResearchItem();

    public ThaumicTinkererRecipe getRecipeItem();
}

