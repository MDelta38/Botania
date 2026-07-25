/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.research.TTResearchItem;

public class KamiResearchItem
extends TTResearchItem {
    public static List<String> Blacklist = new ArrayList<String>();

    public KamiResearchItem(String par1, AspectList tags, int par3, int par4, int par5, ItemStack icon) {
        super(par1, tags, par3, par4, par5, icon, new ResearchPage[0]);
        this.setConcealed();
    }

    @Override
    public ResearchItem setPages(ResearchPage ... par) {
        ArrayList<String> requirements;
        ArrayList<String> arrayList = requirements = this.parentsHidden == null || this.parentsHidden.length == 0 ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(this.parentsHidden));
        if (!this.isAutoUnlock()) {
            for (String categoryStr : ResearchCategories.researchCategories.keySet()) {
                ResearchCategoryList category = (ResearchCategoryList)ResearchCategories.researchCategories.get(categoryStr);
                for (String tag : category.research.keySet()) {
                    ResearchItem research = (ResearchItem)category.research.get(tag);
                    if (research.isLost() || research.parentsHidden == null && research.parents == null || research.isVirtual() || research instanceof KamiResearchItem || requirements.contains(tag) || research.getAspectTriggers() != null || research.getEntityTriggers() != null || research.getItemTriggers() != null || !research.category.equals("TT_CATEGORY") && !research.category.equals("BASICS") && !research.category.equals("GOLEMANCY") && !research.category.equals("ARTIFICE") && !research.category.equals("ALCHEMY") && !research.category.equals("THAUMATURGY")) continue;
                    boolean found = false;
                    for (String black : Blacklist) {
                        if (!tag.startsWith(black)) continue;
                        found = true;
                    }
                    if (tag.endsWith("KAMI")) {
                        found = true;
                    }
                    if (found) continue;
                    requirements.add(tag);
                }
            }
        }
        this.parentsHidden = requirements.toArray(new String[requirements.size()]);
        return super.setPages(par);
    }

    @Override
    String getPrefix() {
        return super.getPrefix() + ".kami";
    }

    @Override
    boolean checkInfusion() {
        return false;
    }

    static {
        Blacklist.add("MINILITH");
    }
}

