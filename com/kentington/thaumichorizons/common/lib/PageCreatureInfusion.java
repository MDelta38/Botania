/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.CreatureInfusionRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchPage;

public class PageCreatureInfusion
extends ResearchPage {
    public PageCreatureInfusion(CreatureInfusionRecipe recipe) {
        super(new ResourceLocation("thaumichorizons", "textures/gui/infusion.png"), "");
    }
}

