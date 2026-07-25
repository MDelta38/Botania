/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Level
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchItem
 */
package witchinggadgets.common.util.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import witchinggadgets.WitchingGadgets;

public class WGResearchItem
extends ResearchItem {
    public WGResearchItem(String key, String category, AspectList tags, int displayX, int displayY, int complexity, ResourceLocation icon) {
        super(key, category, tags, displayX, displayY, complexity, icon);
    }

    public WGResearchItem(String key, String category, AspectList tags, int displayX, int displayY, int complexity, ItemStack icon) {
        super(key, category, tags, displayX, displayY, complexity, icon);
    }

    public String getName() {
        return StatCollector.func_74838_a((String)("witchinggadgets_research_name." + this.key));
    }

    public String getText() {
        return StatCollector.func_74838_a((String)("witchinggadgets_research_text." + this.key));
    }

    public ResearchItem setParents(String ... par) {
        for (String p : par) {
            if (ResearchCategories.getResearch((String)p) != null) continue;
            WitchingGadgets.logger.log(Level.ERROR, "Invalid Parent for Item " + this.key + ". Parent " + p + "doesn't exist!");
            return null;
        }
        this.parents = par;
        return this;
    }

    public ResearchItem setParentsHidden(String ... par) {
        for (String p : par) {
            if (ResearchCategories.getResearch((String)p) != null) continue;
            WitchingGadgets.logger.log(Level.ERROR, "Invalid HiddenParent for Item " + this.key + ". Parent " + p + "doesn't exist!");
            return null;
        }
        this.parentsHidden = par;
        return this;
    }

    public ResearchItem addWarp(int warp) {
        ThaumcraftApi.addWarpToResearch((String)this.key, (int)warp);
        return this;
    }
}

